package net.dillon8775.speedrunnermod.item;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * Experience ore block items.
 */
public class ExperienceOreItem extends BlockItem {

    public ExperienceOreItem(Block block, Settings settings) {
        super(block, settings.group(ItemGroup.BUILDING_BLOCKS));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            tooltip.add(new TranslatableText("block.speedrunnermod.experience_ore.tooltip.line1").formatted(Formatting.GRAY));
            tooltip.add(new TranslatableText("block.speedrunnermod.experience_ore.tooltip.line2").formatted(Formatting.GRAY));
        }
    }
}