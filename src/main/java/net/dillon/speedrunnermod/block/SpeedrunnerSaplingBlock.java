package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.tag.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/**
 * <p>The {@code Speedrunner Sapling} block.</p>
 * <p>Can be planted on grass and/or sand.</p>
 */
public class SpeedrunnerSaplingBlock extends SaplingBlock {

    protected SpeedrunnerSaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator, settings);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return (double)world.random.nextFloat() < 0.99;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(ModBlockTags.SPEEDRUNNER_SAPLING_PLACEABLES) || super.canPlantOnTop(floor, world, pos);
    }
}