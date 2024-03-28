package net.dillon8775.speedrunnermod.enchantment;

import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

/**
 * Decreases the cooldown time on when a shield gets disabled.
 */
public class CooldownEnchantment extends Enchantment {

    public CooldownEnchantment(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isIn(ModItemTags.COOLDOWN_ENCHANTMENT_ITEMS);
    }
}