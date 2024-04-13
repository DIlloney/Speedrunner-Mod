package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.world.feature.ModConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class SpeedrunnerSaplingGenerator extends SaplingGenerator {

    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        if (random.nextInt(10) == 0) {
            return ModConfiguredFeatures.FANCY_SPEEDRUNNER;
        }
        return ModConfiguredFeatures.DEFAULT_SPEEDRUNNER;
    }
}