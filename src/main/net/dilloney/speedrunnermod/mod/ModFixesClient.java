package net.dilloney.speedrunnermod.mod;

import com.google.common.base.MoreObjects;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.item.SpeedrunnerCrossbowItem;
import net.dilloney.speedrunnermod.util.UniqueItemRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.util.List;

/**
 * This class fixes anything that the mod adds, and needs to be implemented, by use, into the game using {@link Mixin}s, Client-side only.
 * <p> {@linkplain BowPullingFix}, {@linkplain CrossbowRendererFix}, {@linkplain HeldItemRendererFix} </p>
 * <p> {@linkplain SodiumBrightnessSliderFix} </p>
 */
@Environment(EnvType.CLIENT)
public class ModFixesClient {

    @Mixin(AbstractClientPlayerEntity.class)
    public static class BowPullingFix {

        @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
        private Item getSpeed(ItemStack stack) {
            return UniqueItemRegistry.BOW.getDefaultItem(stack.getItem());
        }
    }

    @Mixin(PlayerEntityRenderer.class)
    public static class CrossbowRendererFix {

        @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
        private static void getArmPose(AbstractClientPlayerEntity abstractClientPlayerEntity, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
            ItemStack stack = abstractClientPlayerEntity.getStackInHand(hand);
            if (!abstractClientPlayerEntity.handSwinging && UniqueItemRegistry.CROSSBOW.isItemInRegistry(stack.getItem()) && CrossbowItem.isCharged(stack)) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            }
        }
    }

    @Mixin(HeldItemRenderer.class)
    public abstract static class HeldItemRendererFix {

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

    @Mixin(MinecraftClient.class)
    public static class SodiumBrightnessSliderFix {

        @Shadow
        private GameOptions options;

        @Inject(at = @At("HEAD"), method = "close")
        private void close(CallbackInfo info) {
            options.write();
        }

        @Inject(at = @At("HEAD"), method = "openScreen")
        private void openScreen(Screen screen, CallbackInfo info) {
            if (screen != null && screen.getClass().getSimpleName().equals("SodiumOptionsGUI")) {
                try {
                    List<?> optionPages = (List<?>) get(screen, "me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI", "pages");
                    List<?> optionGroups = (List<?>) get(optionPages.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionPage", "groups");
                    List<?> options = (List<?>) get(optionGroups.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionGroup", "options");
                    Object sliderControl = get(options.get(1), "me.jellysquid.mods.sodium.client.gui.options.OptionImpl", "control");
                    Class<?> sliderControlClass = Class.forName("me.jellysquid.mods.sodium.client.gui.options.control.SliderControl");
                    setInt(sliderControl, sliderControlClass, "min", (int) (SpeedrunnerModClient.minBrightness * 100));
                    setInt(sliderControl, sliderControlClass, "max", (int) (SpeedrunnerModClient.maxBrightness * 100));
                }
                catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private Object get(Object instance, String className, String name) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
            Field f = Class.forName(className).getDeclaredField(name);
            f.setAccessible(true);
            return f.get(instance);
        }

        private void setInt(Object instance, Class<?> clazz, String field, int value) throws NoSuchFieldException, IllegalAccessException {
            Field f = clazz.getDeclaredField(field);
            f.setAccessible(true);
            f.setInt(instance, value);
        }
    }
}