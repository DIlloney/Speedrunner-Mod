package net.dillon8775.speedrunnermod.mixin.client.fix;

import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.item.SpeedrunnerCrossbowItem;
import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        return stack.isIn(ModItemTags.BOWS) || stack.isIn(ModItemTags.CROSSBOWS);
    }

    @Redirect(method = "getUsingItemHandRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean getUsingItemHandRenderType(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.BOWS) || stack.isIn(ModItemTags.CROSSBOWS);
    }

    @Redirect(method = "isChargedCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean isChargedCrossbow(ItemStack stack, Item item) {
        return stack.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(stack) || stack.isOf(ModItems.SPEEDRUNNER_CROSSBOW) && SpeedrunnerCrossbowItem.isCharged(stack);
    }

    @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
    private boolean renderFirstPersonItem(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.CROSSBOWS);
    }
}