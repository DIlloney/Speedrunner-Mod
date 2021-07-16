package com.dilloney.speedrunnermod.mixins.client;

import com.dilloney.speedrunnermod.util.UniqueItemRegistry;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Redirect(method = "getHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean getHandRenderTypeMod(ItemStack stack, Item item, ClientPlayerEntity player) {
        return UniqueItemRegistry.BOW.isItemInRegistry(stack.getItem()) || UniqueItemRegistry.CROSSBOW.isItemInRegistry(stack.getItem());
    }

    @Redirect(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean getUsingItemHandRenderTypeMod(ItemStack stack, Item item) {
        return UniqueItemRegistry.BOW.isItemInRegistry(stack.getItem()) || UniqueItemRegistry.CROSSBOW.isItemInRegistry(stack.getItem());
    }

    @Redirect(method = "isChargedCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean isChargedCrossbow(ItemStack stack, Item item) {
        return UniqueItemRegistry.CROSSBOW.isItemInRegistry(stack.getItem()) && CrossbowItem.isCharged(stack);
    }

    @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
    private boolean renderFirstPersonItemMod(ItemStack stack, Item item) {
        return UniqueItemRegistry.CROSSBOW.isItemInRegistry(stack.getItem());
    }
}