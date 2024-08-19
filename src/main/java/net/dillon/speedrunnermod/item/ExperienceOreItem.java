package net.dillon.speedrunnermod.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Experience ore block items.
 */
public class ExperienceOreItem extends BlockItem {

    public ExperienceOreItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("block.speedrunnermod.experience_ore.tooltip.line1").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.speedrunnermod.experience_ore.tooltip.line2").formatted(Formatting.GRAY));
        }
        if (options().client.textureTooltips) {
            tooltip.add(Text.translatable("speedrunnermod.texture_creator.krevikus"));
        }
    }
}