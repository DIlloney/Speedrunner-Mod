package net.dillon.speedrunnermod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

/**
 * Increases player movement speed.
 */
public class DashEnchantment extends Enchantment {

    public DashEnchantment(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.ARMOR_FEET, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}