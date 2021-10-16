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
            return state.isIn(BlockTags.WOOL) ? 7.5F : super.getMiningSpeedMultiplier(stack, state);
        } else {
            return 17.0F;
        }
    }
}