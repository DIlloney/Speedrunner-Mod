package net.dillon.speedrunnermod.item;

import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

/**
 * Literally a knockback stick. Nothing more to it.
 */
public class KnockbackStickItem extends Item {

    public KnockbackStickItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(10).rarity(Rarity.EPIC));
        ItemEnchantmentsComponent itemComponent = getDefaultStack().getEnchantments();
        ItemEnchantmentsComponent.Builder enchantments = new ItemEnchantmentsComponent.Builder(itemComponent);
    }

    /**
     * The Knockback stick always has an enchantment glint.
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}