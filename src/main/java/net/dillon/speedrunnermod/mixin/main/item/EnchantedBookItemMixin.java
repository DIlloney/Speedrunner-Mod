package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.Map;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(EnchantedBookItem.class)
public class EnchantedBookItemMixin extends Item {

    public EnchantedBookItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
            if (enchantments.containsKey(ModEnchantments.DASH)) {
                tooltip.add(Text.translatable("enchantment.speedrunnermod.dash.tooltip").formatted(Formatting.GRAY));
            }
            if (enchantments.containsKey(ModEnchantments.COOLDOWN)) {
                tooltip.add(Text.translatable("enchantment.speedrunnermod.cooldown.tooltip").formatted(Formatting.GRAY));
            }
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}