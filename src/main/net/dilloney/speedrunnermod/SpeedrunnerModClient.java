package net.dilloney.speedrunnermod;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.platform.GlStateManager;
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
import net.minecraft.client.options.Option;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
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
        FluidState fluidState = camera.getSubmergedFluidState();
        Entity entity = camera.getFocusedEntity();
        float s;
        if (fluidState.isIn(FluidTags.WATER)) {
            s = 1.0F;
            s = 0.05F;
            if (entity instanceof ClientPlayerEntity) {
                ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
                s -= clientPlayerEntity.getUnderwaterVisibility() * clientPlayerEntity.getUnderwaterVisibility() * 0.03F;
                Biome biome = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
                if (biome.getCategory() == Biome.Category.SWAMP) {
                    s += 0.005F;
                }
            }

            RenderSystem.fogDensity(s);
            RenderSystem.fogMode(GlStateManager.FogMode.EXP2);
        } else {
            float v;
            int fog = 2147483647;
            float lavaVisionDistance = 35.0F;
            if (fluidState.isIn(FluidTags.LAVA)) {
                if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                    s = 0.0F;
                    v = lavaVisionDistance;
                } else {
                    s = 0.25F;
                    v = 1.0F;
                }
            } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
                int k = ((LivingEntity)entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
                float l = MathHelper.lerp(Math.min(1.0F, (float)k / 20.0F), viewDistance, 5.0F);
                if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                    s = 0.0F;
                    v = l * 0.8F;
                } else {
                    s = l * 0.25F;
                    v = l;
                }
            } else if (thickFog) {
                s = viewDistance * 0.05F;
                if (!SpeedrunnerModClient.CLOPTIONS.fog) {
                    v = fog;
                } else {
                    v = Math.min(viewDistance, 192.0F) * 0.5F;
                }
            } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                s = 0.0F;
                v = viewDistance;
            } else {
                s = viewDistance * 0.75F;
                if (!SpeedrunnerModClient.CLOPTIONS.fog) {
                    v = fog;
                } else {
                    v = viewDistance;
                }
            }

            RenderSystem.fogStart(s);
            RenderSystem.fogEnd(v);
            RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
            RenderSystem.setupNvFogDistance();
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
            writer.write(GSON.toJson(SpeedrunnerModClient.CLOPTIONS));
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