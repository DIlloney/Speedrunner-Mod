package net.dilloney.speedrunnermod.mixin.client;

import com.google.common.base.MoreObjects;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.client.gui.screen.option.ModOption;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.item.SpeedrunnerCrossbowItem;
import net.dilloney.speedrunnermod.util.UniqueItemRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.Option;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.FluidTags;
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
                final int fog = 2147483647;
                final float lavaVisionDistance = 35.0F;
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
                    if (!SpeedrunnerModClient.clOptions().fog) {
                        v = fog;
                    } else {
                        v = Math.min(viewDistance, 192.0F) * 0.5F;
                    }
                } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                    s = 0.0F;
                    v = viewDistance;
                } else {
                    s = viewDistance * 0.75F;
                    if (!SpeedrunnerModClient.clOptions().fog) {
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

    @Mixin(net.minecraft.client.network.AbstractClientPlayerEntity.class)
    public static class AbstractClientPlayerEntityMixin {

        @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item getSpeed(ItemStack stack) {
            return UniqueItemRegistry.BOW.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(net.minecraft.client.render.entity.PlayerEntityRenderer.class)
    public static class PlayerEntityRendererMixin {

        @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
        private static void getArmPose(AbstractClientPlayerEntity abstractClientPlayerEntity, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
            ItemStack stack = abstractClientPlayerEntity.getStackInHand(hand);
            if (!abstractClientPlayerEntity.handSwinging && UniqueItemRegistry.CROSSBOW.isItemInRegistry(stack.getItem()) && CrossbowItem.isCharged(stack)) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            }
        }
    }

    @Mixin(net.minecraft.client.render.item.HeldItemRenderer.class)
    public abstract static class HeldItemRendererMixin {
        @Shadow
        private ItemStack mainHand;
        @Shadow
        private ItemStack offHand;
        @Shadow
        private float equipProgressMainHand;
        @Shadow
        private float prevEquipProgressMainHand;
        @Shadow
        private float equipProgressOffHand;
        @Shadow
        private float prevEquipProgressOffHand;
        @Shadow
        abstract void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light);

        @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item renderFirstPersonItem(ItemStack stack) {
            return UniqueItemRegistry.CROSSBOW.getDefaultItem(stack.getItem());
        }

        @Overwrite
        public void renderItem(float tickDelta, MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, ClientPlayerEntity player, int light) {
            float f = player.getHandSwingProgress(tickDelta);
            Hand hand = (Hand) MoreObjects.firstNonNull(player.preferredHand, Hand.MAIN_HAND);
            float g = MathHelper.lerp(tickDelta, player.prevPitch, player.pitch);
            boolean bl = true;
            boolean bl2 = true;
            ItemStack itemStack3;
            if (player.isUsingItem()) {
                itemStack3 = player.getActiveItem();
                if (itemStack3.getItem() == Items.BOW || itemStack3.getItem() == Items.CROSSBOW || itemStack3.getItem() == ModItems.SPEEDRUNNER_BOW || itemStack3.getItem() == ModItems.SPEEDRUNNER_CROSSBOW) {
                    bl = player.getActiveHand() == Hand.MAIN_HAND;
                    bl2 = !bl;
                }

                Hand hand2 = player.getActiveHand();
                if (hand2 == Hand.MAIN_HAND) {
                    ItemStack itemStack2 = player.getOffHandStack();
                    if (itemStack2.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemStack2) || itemStack2.getItem() == ModItems.SPEEDRUNNER_CROSSBOW && SpeedrunnerCrossbowItem.isCharged(itemStack2)) {
                        bl2 = false;
                    }
                }
            } else {
                itemStack3 = player.getMainHandStack();
                ItemStack itemStack4 = player.getOffHandStack();
                if (itemStack3.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemStack3) || itemStack3.getItem() == ModItems.SPEEDRUNNER_CROSSBOW && SpeedrunnerCrossbowItem.isCharged(itemStack3)) {
                    bl2 = !bl;
                }

                if (itemStack4.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemStack4) || itemStack4.getItem() == ModItems.SPEEDRUNNER_CROSSBOW && SpeedrunnerCrossbowItem.isCharged(itemStack4)) {
                    bl = !itemStack3.isEmpty();
                    bl2 = !bl;
                }
            }

            float h = MathHelper.lerp(tickDelta, player.lastRenderPitch, player.renderPitch);
            float i = MathHelper.lerp(tickDelta, player.lastRenderYaw, player.renderYaw);
            matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion((player.getPitch(tickDelta) - h) * 0.1F));
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((player.getYaw(tickDelta) - i) * 0.1F));
            float m;
            float l;
            if (bl) {
                l = hand == Hand.MAIN_HAND ? f : 0.0F;
                m = 1.0F - MathHelper.lerp(tickDelta, this.prevEquipProgressMainHand, this.equipProgressMainHand);
                this.renderFirstPersonItem(player, tickDelta, g, Hand.MAIN_HAND, l, this.mainHand, m, matrices, vertexConsumers, light);
            }

            if (bl2) {
                l = hand == Hand.OFF_HAND ? f : 0.0F;
                m = 1.0F - MathHelper.lerp(tickDelta, this.prevEquipProgressOffHand, this.equipProgressOffHand);
                this.renderFirstPersonItem(player, tickDelta, g, Hand.OFF_HAND, l, this.offHand, m, matrices, vertexConsumers, light);
            }

            vertexConsumers.draw();
        }
    }

    @Mixin(net.minecraft.client.gui.screen.VideoOptionsScreen.class)
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