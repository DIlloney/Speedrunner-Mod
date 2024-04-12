package net.dillon8775.speedrunnermod.block;

import net.dillon8775.speedrunnermod.tag.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

/**
 * <p>The {@code Speedrunner Sapling} block.</p>
 * <p>Can be planted on grass and/or sand.</p>
 */
public class SpeedrunnerSaplingBlock extends SaplingBlock {

    protected SpeedrunnerSaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator, settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(ModBlockTags.SPEEDRUNNER_SAPLING_BLOCKS) || super.canPlantOnTop(floor, world, pos);
    }
}