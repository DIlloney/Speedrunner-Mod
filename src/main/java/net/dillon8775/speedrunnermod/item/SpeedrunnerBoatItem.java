package net.dillon8775.speedrunnermod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * Boats that can be ridden in lava.
 */
public class SpeedrunnerBoatItem extends BoatItem {
    private final boolean isFastBoat;

    public SpeedrunnerBoatItem(BoatEntity.Type type, boolean isFastBoat, Settings settings) {
        super(type, settings.maxCount(options().main.stackUnstackables ? 16 : 1).fireproof().group(ItemGroup.TRANSPORTATION));
        this.isFastBoat = isFastBoat;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            tooltip.add(new TranslatableText("item.speedrunnermod.boat.tooltip"));
            if (isFastBoat) {
                tooltip.add(new TranslatableText("item.speedrunnermod.boat.tooltip.fast"));
            }
        }
    }
}