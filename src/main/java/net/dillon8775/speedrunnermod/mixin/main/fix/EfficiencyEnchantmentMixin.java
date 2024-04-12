package net.dillon8775.speedrunnermod.mixin.main.fix;

import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EfficiencyEnchantment.class)
public class EfficiencyEnchantmentMixin {

    /**
     * Fixes the efficiency enchantment not being able to be applied to shears.
     */
    @Redirect(method = "isAcceptableItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean isAcceptableItem(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.SHEARS);
    }
}