package net.dilloney.speedrunnermod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dilloney.speedrunnermod.client.render.SpeedrunnerShieldRenderer;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.item.SpeedrunnerCrossbowItem;
import net.dilloney.speedrunnermod.option.ModOption;
import net.dilloney.speedrunnermod.timer.TickHandler;
import net.dilloney.speedrunnermod.timer.data.DataStorage;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.Option;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;

import java.io.File;

@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {

    public static double minBrightness = 1.0;
    public static double maxBrightness = 5.0;

    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW.asItem(), new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW.asItem(), new Identifier("pulling"), (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW.asItem(), new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return SpeedrunnerCrossbowItem.isCharged(stack) ? 0.0F : (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (float)SpeedrunnerCrossbowItem.getPullTime(stack);
            }
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW.asItem(), new Identifier("pulling"), (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !SpeedrunnerCrossbowItem.isCharged(stack) ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW.asItem(), new Identifier("charged"), (stack, world, entity, seed) -> {
            return entity != null && SpeedrunnerCrossbowItem.isCharged(stack) ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW.asItem(), new Identifier("firework"), (stack, world, entity, seed) -> {
            return entity != null && SpeedrunnerCrossbowItem.isCharged(stack) && SpeedrunnerCrossbowItem.hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_SHIELD, new Identifier("blocking"), (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });

        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SPEEDRUNNER_SHIELD, new SpeedrunnerShieldRenderer());

        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            if (atlasTexture.getId() == SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE) {
                registry.register(new Identifier("speedrunnermod:entity/speedrunner_shield_base"));
            }
        });

        if (SpeedrunnerMod.OPTIONS.timer) {
            MinecraftClient client = MinecraftClient.getInstance();
            File configDir = FabricLoader.getInstance().getConfigDir().toFile();
            DataStorage store = DataStorage.of(new File(configDir, "speedrunnermod-timer_storage.json"));
            store.refreshBests("");
            TickHandler tickHandler = new TickHandler(client, store);
            HudRenderCallback.EVENT.register((__, ___) -> tickHandler.tick());
        }
    }

    public static void injectNewFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        Entity entity = camera.getFocusedEntity();
        float y;
        if (cameraSubmersionType == CameraSubmersionType.WATER) {
            y = 192.0F;
            if (entity instanceof ClientPlayerEntity) {
                ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
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
                } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                    y = 0.0F;
                    ab = lavaVisionDistance;
                } else {
                    y = 0.25F;
                    ab = 1.0F;
                }
            } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
                int m = ((LivingEntity)entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
                float n = MathHelper.lerp(Math.min(1.0F, (float)m / 20.0F), viewDistance, 5.0F);
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
                if (!SpeedrunnerMod.OPTIONS.fog) {
                    ab = fog;
                } else {
                    ab = Math.min(viewDistance, 192.0F) * 0.5F;
                }
            } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                y = 0.0F;
                ab = viewDistance;
            } else {
                y = viewDistance * 0.75F;
                if (!SpeedrunnerMod.OPTIONS.fog) {
                    ab = fog;
                } else {
                    ab = viewDistance;
                }
            }

            RenderSystem.setShaderFogStart(y);
            RenderSystem.setShaderFogEnd(ab);
        }
    }

    public static Option[] getModOptions() {
        return new Option[]{ModOption.MAKE_STRUCTURES_MORE_COMMON, ModOption.MAKE_BIOMES_MORE_COMMON, ModOption.ICARUS_MODE, ModOption.INFINITY_PEARL_MODE, ModOption.FOG, ModOption.TIMER, ModOption.DOOM_MODE, ModOption.KILL_GHAST_UPON_FIREBALL, ModOption.STRONGHOLD_COUNT, ModOption.DRAGON_PERCH_TIME, ModOption.AUTO_CREATE_WORLD, ModOption.WORLD_DIFFICULTY};
    }

    public static Option[] newVideoOptions() {
        return new Option[]{Option.GRAPHICS, Option.RENDER_DISTANCE, Option.AO, Option.FRAMERATE_LIMIT, Option.VSYNC, Option.VIEW_BOBBING, Option.GUI_SCALE, Option.ATTACK_INDICATOR, Option.GAMMA, Option.CLOUDS, Option.FULLSCREEN, Option.PARTICLES, ModOption.FOG, Option.MIPMAP_LEVELS, Option.ENTITY_SHADOWS, Option.DISTORTION_EFFECT_SCALE, Option.ENTITY_DISTANCE_SCALING, Option.FOV_EFFECT_SCALE};
    }
}