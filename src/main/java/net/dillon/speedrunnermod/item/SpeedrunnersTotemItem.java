package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.component.SpeedrunnersDeathProtectionComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A better totem of undying. Stacks to 16, and has better effects upon use.
 */
public class SpeedrunnersTotemItem extends Item {

    public SpeedrunnersTotemItem(Settings settings) {
        super(settings.maxCount(16).rarity(Rarity.RARE).component(ModDataComponentTypes.DEATH_PROTECTION, SpeedrunnersDeathProtectionComponent.SPEEDRUNNERS_TOTEM));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line1").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line2").formatted(Formatting.GRAY));
        }
    }
}