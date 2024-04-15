package net.dillon.speedrunnermod.mixin.main.item;

import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(TerraformBoatItem.class)
public class TerraformBoatItemMixin extends Item {

    public TerraformBoatItemMixin(Settings settings) {
        super(settings);
    }

    /**
     * Adds the corresponding tooltip to the speedrunner mod boats.
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            if (stack.isIn(ModItemTags.FIREPROOF_BOATS) || stack.isIn(ModItemTags.FIREPROOF_CHEST_BOATS)) {
                tooltip.add(Text.translatable("item.speedrunnermod.boat.tooltip").formatted(Formatting.GRAY));
            }
            if (stack.isIn(ModItemTags.FASTER_BOATS) || stack.isIn(ModItemTags.FASTER_CHEST_BOATS)) {
                tooltip.add(Text.translatable("item.speedrunnermod.boat.tooltip.fast").formatted(Formatting.GRAY));
            }
        }
    }
}