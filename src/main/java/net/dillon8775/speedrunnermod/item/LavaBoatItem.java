package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Boats that can be ridden in lava.
 */
public class LavaBoatItem extends BoatItem {

    public LavaBoatItem(BoatEntity.Type type, Settings settings) {
        super(type, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (SpeedrunnerModClient.clientOptions().itemTooltips) {
            tooltip.add(new TranslatableText("item.speedrunnermod.boat.tooltip"));
        }
    }
}