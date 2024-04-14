package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.world.feature.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator SPEEDRUNNER = new SaplingGenerator("speedrunnermod:speedrunner", 0.1F, Optional.empty(), Optional.empty(), Optional.of(ModConfiguredFeatures.DEFAULT_SPEEDRUNNER), Optional.of(ModConfiguredFeatures.FANCY_SPEEDRUNNER), Optional.empty(), Optional.empty());
}