package com.dilloney.speedrunnermod.mixins.client.render.item;

import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Redirect(method = "getHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean renderSpeedrunnerBowsAndCrossbowsCorrectly(ItemStack stack, Item item, ClientPlayerEntity player) {
        return stack.isIn(ModItemTags.BOWS) || stack.isIn(ModItemTags.CROSSBOWS);
    }

    @Redirect(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean renderSpeedrunnerBowsAndCrossbowsCorrectly(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.BOWS) || stack.isIn(ModItemTags.CROSSBOWS);
    }

    @Redirect(method = "isChargedCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean addSpeedrunnerCrossbowToIsChargedCrossbow(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.CROSSBOWS) && CrossbowItem.isCharged(stack);
    }

    @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
    private boolean renderSpeedrunnerCrossbowCorrectlyInFirstPerson(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.CROSSBOWS);
    }
}