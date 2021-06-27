package com.dilloney.speedrunnermod.world.feature;

import com.dilloney.speedrunnermod.blocks.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class OreGeneration {

    public static final ConfiguredFeature<?, ?> SPEEDRUNNER_ORE_OVERWORLD = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.SPEEDRUNNER_ORE.getDefaultState(), 9))
            .uniformRange(YOffset.getBottom(), YOffset.fixed(128))
            .spreadHorizontally()
            .repeat(22);

    public static final ConfiguredFeature<?, ?> DEEPSLATE_SPEEDRUNNER_ORE_OVERWORLD = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.getDefaultState(), 6))
            .uniformRange(YOffset.getBottom(), YOffset.fixed(16))
            .spreadHorizontally()
            .repeat(10);

    public static final ConfiguredFeature<?, ?> NETHER_SPEEDRUNNER_ORE_NETHER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.SPEEDRUNNER_NETHER_ORE.getDefaultState(), 9))
            .uniformRange(YOffset.getBottom(), YOffset.fixed(124))
            .spreadHorizontally()
            .repeat(15);

    public static final ConfiguredFeature<?, ?> IGNEOUS_ORE_OVERWORLD = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.IGNEOUS_ORE.getDefaultState(), 5))
            .uniformRange(YOffset.getBottom(), YOffset.fixed(32))
            .spreadHorizontally()
            .repeat(15);

    public static final ConfiguredFeature<?, ?> DEEPSLATE_IGNEOUS_ORE_OVERWORLD = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_IGNEOUS_ORE.getDefaultState(), 3))
            .uniformRange(YOffset.getBottom(), YOffset.fixed(16))
            .spreadHorizontally()
            .repeat(15);

    public static final ConfiguredFeature<?, ?> NETHER_IGNEOUS_ORE_NETHER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.IGNEOUS_NETHER_ORE.getDefaultState(), 5))
            .uniformRange(YOffset.getBottom(), YOffset.fixed(64))
            .spreadHorizontally()
            .repeat(18);

    public static final ConfiguredFeature<?, ?> DIAMOND_ORE_MESA_JUNGLE_MOUNTAINS = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, Blocks.DIAMOND_ORE.getDefaultState(), 8))
            .uniformRange(YOffset.getBottom(), YOffset.fixed(21))
            .spreadHorizontally()
            .repeat(9);

    public static final ConfiguredFeature<?, ?> DEEPSLATE_DIAMOND_ORE_MESA_JUNGLE_MOUNTAINS = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_DIAMOND_ORE.getDefaultState(), 8))
            .uniformRange(YOffset.getBottom(), YOffset.fixed(16))
            .spreadHorizontally()
            .repeat(7);
}
