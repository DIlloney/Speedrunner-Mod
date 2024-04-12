package net.dillon.speedrunnermod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Boats that can be ridden in lava.
 */
public class SpeedrunnerBoatItem extends BoatItem {
    private final boolean isFastBoat;

    public SpeedrunnerBoatItem(boolean chest, BoatEntity.Type type, boolean isFastBoat, Settings settings) {
        super(chest, type, settings.maxCount(options().main.stackUnstackables ? 16 : 1).fireproof());
        this.isFastBoat = isFastBoat;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.boat.tooltip").formatted(Formatting.GRAY));
            if (isFastBoat) {
                tooltip.add(Text.translatable("item.speedrunnermod.boat.tooltip.fast").formatted(Formatting.GRAY));
            }
        }
    }
}