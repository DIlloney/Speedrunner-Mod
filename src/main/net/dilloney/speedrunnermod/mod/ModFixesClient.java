package net.dilloney.speedrunnermod.mod;

import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
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

        @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean getSpeed(ItemStack stack, Item item) {
            return stack.isOf(Items.BOW) || stack.isOf(ModItems.SPEEDRUNNER_BOW);
        }
    }

    @Mixin(PlayerEntityRenderer.class)
    public static class CrossbowRendererFix {

        @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
        private static void getArmPose(AbstractClientPlayerEntity abstractClientPlayerEntity, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
            ItemStack stack = abstractClientPlayerEntity.getStackInHand(hand);
            if (!abstractClientPlayerEntity.handSwinging && stack.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(stack) || !abstractClientPlayerEntity.handSwinging && stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW) && CrossbowItem.isCharged(stack)) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            }
        }
    }

    @Mixin(HeldItemRenderer.class)
    public static class HeldItemRendererFix {

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

    @Mixin(MinecraftClient.class)
    public static class SodiumBrightnessSliderFix {

        @Shadow
        GameOptions options;

        @Inject(method = "close", at = @At("HEAD"))
        private void writeOptions(CallbackInfo info) {
            options.write();
        }

        @Inject(method = "openScreen", at = @At("HEAD"))
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