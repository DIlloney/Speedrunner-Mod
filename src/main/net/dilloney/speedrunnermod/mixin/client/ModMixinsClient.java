package net.dilloney.speedrunnermod.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.client.gui.screen.option.ModOption;
import net.dilloney.speedrunnermod.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
public class ModMixinsClient {

    @Mixin(net.minecraft.client.render.BackgroundRenderer.class)
    public static class BackgroundRendererMixin {

        @Overwrite
        public static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
            CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
            Entity entity = camera.getFocusedEntity();
            float f;
            if (cameraSubmersionType == CameraSubmersionType.WATER) {
                f = 192.0F;
                if (entity instanceof ClientPlayerEntity) {
                    ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
                    f *= Math.max(0.25F, clientPlayerEntity.getUnderwaterVisibility());
                    Biome biome = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
                    if (biome.getCategory() == Biome.Category.SWAMP) {
                        f *= 0.85F;
                    }
                }

                RenderSystem.setShaderFogStart(-8.0F);
                RenderSystem.setShaderFogEnd(f * 0.5F);
            } else {
                float clientPlayerEntity;
                final int fog = 2147483647;
                final float lavaVisionDistance = 35.0F;
                if (cameraSubmersionType == CameraSubmersionType.LAVA) {
                    if (entity.isSpectator()) {
                        f = -8.0F;
                        clientPlayerEntity = viewDistance * 0.5F;
                    } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                        f = 0.0F;
                        clientPlayerEntity = lavaVisionDistance;
                    } else {
                        f = 0.25F;
                        clientPlayerEntity = 1.0F;
                    }
                } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
                    int biome = ((LivingEntity)entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
                    float g = MathHelper.lerp(Math.min(1.0F, (float)biome / 20.0F), viewDistance, 5.0F);
                    if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                        f = 0.0F;
                        clientPlayerEntity = g * 0.8F;
                    } else {
                        f = g * 0.25F;
                        clientPlayerEntity = g;
                    }
                } else if (cameraSubmersionType == CameraSubmersionType.POWDER_SNOW) {
                    if (entity.isSpectator()) {
                        f = -8.0F;
                        clientPlayerEntity = viewDistance * 0.5F;
                    } else {
                        f = 0.0F;
                        clientPlayerEntity = 2.0F;
                    }
                } else if (thickFog) {
                    f = viewDistance * 0.05F;
                    if (!SpeedrunnerModClient.clOptions().fog) {
                        clientPlayerEntity = fog;
                    } else {
                        clientPlayerEntity = Math.min(viewDistance, 192.0F) * 0.5F;
                    }
                } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                    f = 0.0F;
                    clientPlayerEntity = viewDistance;
                } else {
                    float biome = MathHelper.clamp(viewDistance / 10.0F, 4.0F, 64.0F);
                    f = viewDistance - biome;
                    if (!SpeedrunnerModClient.clOptions().fog) {
                        clientPlayerEntity = fog;
                    } else {
                        clientPlayerEntity = viewDistance;
                    }
                }

                RenderSystem.setShaderFogStart(f);
                RenderSystem.setShaderFogEnd(clientPlayerEntity);
            }

        }
    }

    @Mixin(net.minecraft.client.network.AbstractClientPlayerEntity.class)
    public static class AbstractClientPlayerEntityMixin {

        @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean getSpeed(ItemStack stack, Item item) {
            return stack.isOf(Items.BOW) || stack.isOf(ModItems.SPEEDRUNNER_BOW);
        }
    }

    @Mixin(net.minecraft.client.render.entity.PlayerEntityRenderer.class)
    public static class PlayerEntityRendererMixin {

        @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
        private static void getArmPose(AbstractClientPlayerEntity abstractClientPlayerEntity, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
            ItemStack stack = abstractClientPlayerEntity.getStackInHand(hand);
            if (!abstractClientPlayerEntity.handSwinging && stack.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(stack) || !abstractClientPlayerEntity.handSwinging && stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW) && CrossbowItem.isCharged(stack)) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            }
        }
    }

    @Mixin(net.minecraft.client.render.item.HeldItemRenderer.class)
    public static class HeldItemRendererMixin {

        @Redirect(method = "getHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private static boolean getHandRenderType(ItemStack stack, Item item, ClientPlayerEntity player) {
            return stack.isOf(Items.BOW) || stack.isOf(ModItems.SPEEDRUNNER_BOW) || stack.isOf(Items.CROSSBOW) || stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW);
        }

        @Redirect(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private static boolean getUsingItemHandRenderType(ItemStack stack, Item item) {
            return stack.isOf(Items.BOW) || stack.isOf(ModItems.SPEEDRUNNER_BOW) || stack.isOf(Items.CROSSBOW) || stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW);
        }

        @Redirect(method = "isChargedCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private static boolean isChargedCrossbow(ItemStack stack, Item item) {
            return stack.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(stack) || stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW) && CrossbowItem.isCharged(stack);
        }

        @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
        private boolean renderFirstPersonItem(ItemStack stack, Item item) {
            return stack.isOf(Items.CROSSBOW) || stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW);
        }
    }

    @Mixin(net.minecraft.client.gui.screen.option.VideoOptionsScreen.class)
    public static class VideoOptionsScreenMixin {
        @Shadow
        private static final Option[] OPTIONS = new Option[]{Option.GRAPHICS, Option.RENDER_DISTANCE, Option.AO, Option.FRAMERATE_LIMIT, Option.VSYNC, Option.VIEW_BOBBING, Option.GUI_SCALE, Option.ATTACK_INDICATOR, ModOption.GAMMA, Option.CLOUDS, Option.FULLSCREEN, Option.PARTICLES, ModOption.FOG, Option.MIPMAP_LEVELS, Option.ENTITY_SHADOWS, Option.DISTORTION_EFFECT_SCALE, Option.ENTITY_DISTANCE_SCALING, Option.FOV_EFFECT_SCALE};
    }

    @Mixin(net.minecraft.client.MinecraftClient.class)
    public interface GameOptionsAccessor {
        @Accessor("options")
        GameOptions getGameOptions();
    }

    @Mixin(net.minecraft.server.network.ServerPlayerEntity.class)
    public interface SeenCreditsAccessor {
        @Accessor("seenCredits")
        boolean seenCredits();
    }
}