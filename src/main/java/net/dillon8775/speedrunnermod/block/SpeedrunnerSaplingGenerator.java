<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.block;

import net.dillon8775.speedrunnermod.world.feature.ModConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class SpeedrunnerSaplingGenerator extends SaplingGenerator {

    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        if (random.nextInt(10) == 0) {
            return ModConfiguredFeatures.FANCY_SPEEDRUNNER;
        }
        return ModConfiguredFeatures.DEFAULT_SPEEDRUNNER;
    }
=======
package net.dillon8775.speedrunnermod.block;

import net.dillon8775.speedrunnermod.world.feature.ModConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class SpeedrunnerSaplingGenerator extends SaplingGenerator {

    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        if (random.nextInt(10) == 0) {
            return ModConfiguredFeatures.FANCY_SPEEDRUNNER;
        }
        return ModConfiguredFeatures.DEFAULT_SPEEDRUNNER;
    }
>>>>>>> Stashed changes
}