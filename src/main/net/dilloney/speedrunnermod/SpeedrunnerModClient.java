package net.dilloney.speedrunnermod;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dilloney.speedrunnermod.client.timer.Timer;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.option.ModOption;
import net.dilloney.speedrunnermod.option.clModOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.Option;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {
    public static double minBrightness = 1.0;
    public static double maxBrightness = 5.0;
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static final String CONFIG = "speedrunnermod-cloptions.json";
    private static File file;
    private static clModOptions CLOPTIONS = getClientConfig();

    public void onInitializeClient() {
        loadClientConfig();

        ModItems.clinit();
        Timer.init();
    }

    public static void applyNewFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        Entity entity = camera.getFocusedEntity();
        float y;
        if (cameraSubmersionType == CameraSubmersionType.WATER) {
            y = 192.0F;
            if (entity instanceof ClientPlayerEntity) {
                ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity) entity;
                y *= Math.max(0.25F, clientPlayerEntity.getUnderwaterVisibility());
                Biome biome = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
                if (biome.getCategory() == Biome.Category.SWAMP) {
                    y *= 0.85F;
                }
            }

            RenderSystem.setShaderFogStart(-8.0F);
            RenderSystem.setShaderFogEnd(y * 0.5F);
        } else {
            float ab;
            int fog = 2147483647;
            float lavaVisionDistance = 35.0F;
            if (cameraSubmersionType == CameraSubmersionType.LAVA) {
                if (entity.isSpectator()) {
                    y = -8.0F;
                    ab = viewDistance * 0.5F;
                } else if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                    y = 0.0F;
                    ab = lavaVisionDistance;
                } else {
                    y = 0.25F;
                    ab = 1.0F;
                }
            } else if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
                int m = ((LivingEntity) entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
                float n = MathHelper.lerp(Math.min(1.0F, (float) m / 20.0F), viewDistance, 5.0F);
                if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                    y = 0.0F;
                    ab = n * 0.8F;
                } else {
                    y = n * 0.25F;
                    ab = n;
                }
            } else if (cameraSubmersionType == CameraSubmersionType.POWDER_SNOW) {
                if (entity.isSpectator()) {
                    y = -8.0F;
                    ab = viewDistance * 0.5F;
                } else {
                    y = 0.0F;
                    ab = 2.0F;
                }
            } else if (thickFog) {
                y = viewDistance * 0.05F;
                if (!SpeedrunnerModClient.CLOPTIONS.fog) {
                    ab = fog;
                } else {
                    ab = Math.min(viewDistance, 192.0F) * 0.5F;
                }
            } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                y = 0.0F;
                ab = viewDistance;
            } else {
                y = viewDistance * 0.75F;
                if (!SpeedrunnerModClient.CLOPTIONS.fog) {
                    ab = fog;
                } else {
                    ab = viewDistance;
                }
            }

            RenderSystem.setShaderFogStart(y);
            RenderSystem.setShaderFogEnd(ab);
        }
    }

    public static Option[] newVideoOptions() {
        return new Option[]{Option.GRAPHICS, Option.RENDER_DISTANCE, Option.AO, Option.FRAMERATE_LIMIT, Option.VSYNC, Option.VIEW_BOBBING, Option.GUI_SCALE, Option.ATTACK_INDICATOR, ModOption.GAMMA, Option.CLOUDS, Option.FULLSCREEN, Option.PARTICLES, ModOption.FOG, Option.MIPMAP_LEVELS, Option.ENTITY_SHADOWS, Option.DISTORTION_EFFECT_SCALE, Option.ENTITY_DISTANCE_SCALING, Option.FOV_EFFECT_SCALE};
    }

    private static void loadClientConfig() {
        File file = getClientConfigFile();
        if (!file.exists()) {
            SpeedrunnerModClient.CLOPTIONS = new clModOptions();
        } else {
            sanitize();
            readClientConfig();
        }
        saveClientConfig();
    }

    private static void readClientConfig() {
        SpeedrunnerModClient.CLOPTIONS = getClientConfig();
    }

    public static void saveClientConfig() {
        File file = getClientConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(SpeedrunnerModClient.clOptions()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setClientConfig(clModOptions config) {
        SpeedrunnerModClient.CLOPTIONS = config;
        saveClientConfig();
    }

    private static clModOptions getClientConfig() {
        File file = getClientConfigFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, clModOptions.class);
        } catch (Exception e) {
            clModOptions newconfig = new clModOptions();
            setClientConfig(newconfig);
            return newconfig;
        }
    }

    private static File getClientConfigFile() {
        if (file == null) {
            file = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG);
        }
        return file;
    }

    private static void sanitize() {
        if (SpeedrunnerModClient.clOptions().worldDifficulty == null) {
            SpeedrunnerModClient.clOptions().worldDifficulty = clModOptions.WorldDifficulty.EASY;
        }
    }

    public static clModOptions clOptions() {
        return CLOPTIONS;
    }
}