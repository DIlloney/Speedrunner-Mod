package net.dilloney.speedrunnermod.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;

public class SpeedrunnerCrossbowItem extends CrossbowItem {

    public SpeedrunnerCrossbowItem(Settings settings) {
        super(settings);
    }

    public boolean isUsedOnRelease(ItemStack stack) {
        return stack.getItem() == this;
    }

    public int getMaxUseTime(ItemStack stack) {
        return getPullTime(stack) + 3;
    }

    public static int getPullTime(ItemStack stack) {
        int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
        return i == 0 ? 20 : 20 - 5 * i;
    }
}