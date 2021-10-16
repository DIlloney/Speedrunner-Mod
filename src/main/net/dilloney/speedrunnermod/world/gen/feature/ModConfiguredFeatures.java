package net.dilloney.speedrunnermod.world.gen.feature;

import net.dilloney.speedrunnermod.block.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> SPEEDRUNNER_ORE = Feature.ORE.configure(new OreFeatureConfig(
            ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, ModBlocks.SPEEDRUNNER_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.getDefaultState())), 9))
                .uniformRange(YOffset.getBottom(), YOffset.fixed(128))
                .spreadHorizontally()
                .repeat(22);

    public static final ConfiguredFeature<?, ?> NETHER_SPEEDRUNNER_ORE = Feature.ORE.configure(new OreFeatureConfig(
            ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.getDefaultState())), 9))
                .uniformRange(YOffset.getBottom(), YOffset.fixed(124))
                .spreadHorizontally()
                .repeat(13);

    public static final ConfiguredFeature<?, ?> IGNEOUS_ORE = Feature.ORE.configure(new OreFeatureConfig(
            ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, ModBlocks.IGNEOUS_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_IGNEOUS_ORE.getDefaultState())), 5))
                .uniformRange(YOffset.getBottom(), YOffset.fixed(24))
                .spreadHorizontally()
                .repeat(10);

    public static final ConfiguredFeature<?, ?> NETHER_IGNEOUS_ORE = Feature.ORE.configure(new OreFeatureConfig(
            ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.getDefaultState())), 5))
                .uniformRange(YOffset.getBottom(), YOffset.fixed(48))
                .spreadHorizontally()
                .repeat(10);

    public static final ConfiguredFeature<?, ?> DIAMOND_ORE = Feature.ORE.configure(new OreFeatureConfig(
            ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, Blocks.DIAMOND_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_DIAMOND_ORE.getDefaultState())), 8))
                .uniformRange(YOffset.getBottom(), YOffset.fixed(16))
                .spreadHorizontally()
                .repeat(4);

    public static final ConfiguredFeature<?, ?> ANCIENT_DEBRIS = Feature.SCATTERED_ORE.configure(new OreFeatureConfig(
            ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.BASE_STONE_NETHER, Blocks.ANCIENT_DEBRIS.getDefaultState())), 4))
                .uniformRange(YOffset.fixed(8), YOffset.fixed(24))
                .spreadHorizontally()
                .repeat(2);
}
