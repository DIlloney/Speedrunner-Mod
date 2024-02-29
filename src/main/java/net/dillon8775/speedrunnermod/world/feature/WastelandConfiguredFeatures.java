package net.dillon8775.speedrunnermod.world.feature;

import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class WastelandConfiguredFeatures {
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> DEFAULT_SPEEDRUNNER =
            ConfiguredFeatures.register("default_speedrunner_speedrunners_wasteland",
                    Feature.TREE, speedrunnersWasteland().build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_SPEEDRUNNER =
            ConfiguredFeatures.register("fancy_speedrunner_speedrunners_wasteland",
                    Feature.TREE, fancySpeedrunnersWasteland().build());
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_RAW_SPEEDRUNNER_BLOCK =
            ConfiguredFeatures.register("patch_raw_speedrunner_block", Feature.RANDOM_PATCH,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(
                            Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.RAW_SPEEDRUNNER_BLOCK)), List.of(Blocks.GRASS_BLOCK)));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SPEEDRUNNER = ConfiguredFeatures.register("ore_speedrunner_speedrunners_wasteland", Feature.ORE,
            new OreFeatureConfig(ModConfiguredFeatures.SPEEDRUNNER_ORES, 12));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SPEEDRUNNER_SMALL = ConfiguredFeatures.register("ore_speedrunner_small_speedrunners_wasteland", Feature.ORE,
            new OreFeatureConfig(ModConfiguredFeatures.SPEEDRUNNER_ORES, 5));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_EXPERIENCE = ConfiguredFeatures.register("ore_experience_speedrunners_wasteland",
            Feature.ORE, new OreFeatureConfig(ModConfiguredFeatures.EXPERIENCE_ORES, 4, 0.2f));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_DIAMOND = ConfiguredFeatures.register("ore_diamond_speedrunners_wasteland",
            Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DIAMOND_ORES, 8, 0.3f));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_DIAMOND_BURIED = ConfiguredFeatures.register("ore_diamond_buried_speedrunners_wasteland",
            Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DIAMOND_ORES, 12, 1.0f));

    private static TreeFeatureConfig.Builder speedrunnersWasteland() {
        return TreeConfiguredFeatures.builder(
                        ModBlocks.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LEAVES, 5, 3, 1, 3)
                .ignoreVines();
    }

    private static TreeFeatureConfig.Builder fancySpeedrunnersWasteland() {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(
                ModBlocks.SPEEDRUNNER_LOG),
                new LargeOakTrunkPlacer(3, 13, 0), BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(0, 1, 0, OptionalInt.of(4)))
                .ignoreVines();
    }
}