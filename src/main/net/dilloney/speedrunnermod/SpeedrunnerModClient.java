package net.dilloney.speedrunnermod;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dilloney.speedrunnermod.client.render.SpeedrunnerShieldRenderer;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.util.timer.TickHandler;
import net.dilloney.speedrunnermod.util.timer.data.DataStorage;
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
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Items;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;

import java.io.File;

@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {
    public static double minBrightness = 1.0;
    public static double maxBrightness = 5.0;

    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW, new Identifier("pull"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getActiveItem() != itemStack ? 0.0F : (float)(itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
            }
        });
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW, new Identifier("pulling"), (itemStack, clientWorld, livingEntity) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("pull"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return CrossbowItem.isCharged(itemStack) ? 0.0F : (float)(itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(itemStack);
            }
        });
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("pulling"), (itemStack, clientWorld, livingEntity) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack && !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F;
        });
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("charged"), (itemStack, clientWorld, livingEntity) -> {
            return livingEntity != null && CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F;
        });
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("firework"), (itemStack, clientWorld, livingEntity) -> {
            return livingEntity != null && CrossbowItem.isCharged(itemStack) && CrossbowItem.hasProjectile(itemStack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_SHIELD, new Identifier("blocking"), (itemStack, clientWorld, livingEntity) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
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

    public static void injectFogFunction(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
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
                if (!SpeedrunnerMod.OPTIONS.fog) {
                    v = fog;
                } else {
                    v = Math.min(viewDistance, 192.0F) * 0.5F;
                }
            } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                s = 0.0F;
                v = viewDistance;
            } else {
                s = viewDistance * 0.75F;
                if (!SpeedrunnerMod.OPTIONS.fog) {
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
}