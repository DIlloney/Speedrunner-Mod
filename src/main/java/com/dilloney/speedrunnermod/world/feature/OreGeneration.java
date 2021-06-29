package com.dilloney.speedrunnermod.world.feature;

import com.dilloney.speedrunnermod.blocks.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class OreGeneration {

    public static final ConfiguredFeature<?, ?> SPEEDRUNNER_ORE_OVERWORLD = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.SPEEDRUNNER_ORE.getDefaultState(), 9))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 128)))
            .spreadHorizontally()
            .repeat(22);

    public static final ConfiguredFeature<?, ?> NETHER_SPEEDRUNNER_ORE_NETHER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.getDefaultState(), 9))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 124)))
            .spreadHorizontally()
            .repeat(15);

    public static final ConfiguredFeature<?, ?> IGNEOUS_ORE_OVERWORLD = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.IGNEOUS_ORE.getDefaultState(), 5))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 32)))
            .spreadHorizontally()
            .repeat(15);

    public static final ConfiguredFeature<?, ?> NETHER_IGNEOUS_ORE_NETHER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.getDefaultState(), 5))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 64)))
            .spreadHorizontally()
            .repeat(15);

    public static final ConfiguredFeature<?, ?> DIAMOND_ORE_MESA_JUNGLE_MOUNTAINS = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, Blocks.DIAMOND_ORE.getDefaultState(), 8))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 21)))
            .spreadHorizontally()
            .repeat(5);
}
