package net.dilloney.speedrunnermod.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.tag.BlockTags;

public class SpeedrunnerShearsItem extends ShearsItem {

    public SpeedrunnerShearsItem(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, new ShearsDispenserBehavior());
    }

    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (!state.isOf(Blocks.COBWEB) && !state.isIn(BlockTags.LEAVES)) {
            if (state.isIn(BlockTags.WOOL)) {
                return 7.5F;
            } else {
                return !state.isOf(Blocks.VINE) && !state.isOf(Blocks.GLOW_LICHEN) ? super.getMiningSpeedMultiplier(stack, state) : 2.0F;
            }
        } else {
            return 17.0F;
        }
    }
}