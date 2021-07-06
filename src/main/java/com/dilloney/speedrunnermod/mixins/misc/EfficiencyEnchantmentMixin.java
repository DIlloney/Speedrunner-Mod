package com.dilloney.speedrunnermod.mixins.misc;

import com.dilloney.speedrunnermod.util.UniqueItemRegistry;
import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EfficiencyEnchantment.class)
public class EfficiencyEnchantmentMixin {

    @Redirect(method = "isAcceptableItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean isAcceptableItemMod(ItemStack stack, Item isOfItem) {
        return UniqueItemRegistry.SHEARS.isItemInRegistry(stack.getItem());
    }
}