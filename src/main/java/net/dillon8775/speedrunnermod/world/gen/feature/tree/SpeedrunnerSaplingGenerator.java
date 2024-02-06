package net.dillon8775.speedrunnermod.world.gen.feature.tree;

import net.dillon8775.speedrunnermod.world.gen.feature.ModConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

public class SpeedrunnerSaplingGenerator extends SaplingGenerator {

    protected ConfiguredFeature<?, ?> getTreeFeature(Random random, boolean bees) {
        if (random.nextInt(10) == 0) {
            return ModConfiguredFeatures.FANCY_SPEEDRUNNER;
        }
        return ModConfiguredFeatures.DEFAULT_SPEEDRUNNER;
    }
}