package net.dillon.speedrunnermod.mixin.client.fix;

import net.dillon.speedrunnermod.item.SpeedrunnerCrossbowItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fixes some incorrect rendering with speedrunner bows and crossbows.
 */
@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Redirect(method = "getHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean getHandRenderType(ItemStack stack, Item item, ClientPlayerEntity player) {
        return stack.isIn(ConventionalItemTags.BOW_TOOLS) || stack.isIn(ConventionalItemTags.CROSSBOW_TOOLS);
    }

    @Redirect(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean getUsingItemHandRenderType(ItemStack stack, Item item) {
        return stack.isIn(ConventionalItemTags.BOW_TOOLS) || stack.isIn(ConventionalItemTags.CROSSBOW_TOOLS);
    }

    @Redirect(method = "isChargedCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean isChargedCrossbow(ItemStack stack, Item item) {
        if (stack.isIn(ConventionalItemTags.CROSSBOW_TOOLS)) {
            return CrossbowItem.isCharged(stack) || SpeedrunnerCrossbowItem.isCharged(stack);
        }
        return false;
    }

    @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean renderFirstPersonItem(ItemStack stack, Item item) {
        return stack.isIn(ConventionalItemTags.CROSSBOW_TOOLS);
    }
}