package net.dilloney.speedrunnermod.mixin.client;

import net.dilloney.speedrunnermod.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
public class ModFixesClient {

    @Mixin(net.minecraft.client.network.AbstractClientPlayerEntity.class)
    public static class BowPullingFix {

        @Redirect(method = "getSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean getSpeed(ItemStack stack, Item item) {
            return stack.isOf(Items.BOW) || stack.isOf(ModItems.SPEEDRUNNER_BOW);
        }
    }

    @Mixin(net.minecraft.client.render.entity.PlayerEntityRenderer.class)
    public static class CrossbowRendererFix {

        @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
        private static void getArmPose(AbstractClientPlayerEntity abstractClientPlayerEntity, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
            ItemStack stack = abstractClientPlayerEntity.getStackInHand(hand);
            if (!abstractClientPlayerEntity.handSwinging && stack.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(stack) || !abstractClientPlayerEntity.handSwinging && stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW) && CrossbowItem.isCharged(stack)) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            }
        }
    }

    @Mixin(net.minecraft.client.render.item.HeldItemRenderer.class)
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
}