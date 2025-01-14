package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.List;
import java.util.stream.IntStream;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Literally a knockback stick. Nothing more to it.
 */
public class KnockbackStickItem extends Item {

    public KnockbackStickItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(10).rarity(Rarity.EPIC));
        ItemEnchantmentsComponent enchantments = EnchantmentHelper.getEnchantments(this.getDefaultStack());
        ItemEnchantmentsComponent.Builder enchantmentsBuilder = new ItemEnchantmentsComponent.Builder(enchantments);
//        enchantmentsBuilder.getEnchantments().stream().filter(
//                enchantmentRegistryEntry -> enchantmentRegistryEntry.matchesKey(Enchantments.KNOCKBACK)
//        ).flatMap(enchantmentRegistryEntry -> IntStream.rangeClosed(10, 10).mapToObj(
//                level -> ItemUtil.itemWithEnchantment(this, enchantmentRegistryEntry.getKey)
//        )).forEach(stack -> stack.notify());
    }

    /**
     * The Knockback stick always has an enchantment glint.
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.knockback_stick.tooltip").formatted(Formatting.GRAY));
        }
    }
}