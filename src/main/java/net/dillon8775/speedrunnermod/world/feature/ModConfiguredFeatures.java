package net.dillon8775.speedrunnermod.world.feature;

import com.google.common.collect.ImmutableList;
import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunk.*;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> DEFAULT_SPEEDRUNNER =
            ConfiguredFeatures.register("default_speedrunner",
                    Feature.TREE, defaultSpeedrunner(ModBlocks.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LEAVES).build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> DEAD_SPEEDRUNNER =
            ConfiguredFeatures.register("dead_speedrunner",
                    Feature.TREE, defaultSpeedrunner(ModBlocks.DEAD_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_LEAVES).build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FANCY_SPEEDRUNNER =
            ConfiguredFeatures.register("fancy_speedrunner", Feature.TREE, fancySpeedrunner(ModBlocks.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LEAVES).build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> DEAD_FANCY_SPEEDRUNNER =
            ConfiguredFeatures.register("dead_fancy_speedrunner", Feature.TREE, fancySpeedrunner(ModBlocks.DEAD_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_LEAVES).build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BIRCH_SPEEDRUNNER =
            ConfiguredFeatures.register("birch_speedrunner", Feature.TREE, birchSpeedrunner().build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SAVANNA_SPEEDRUNNER =
            ConfiguredFeatures.register("savanna_speedrunner", Feature.TREE, savannaSpeedrunner().build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TAIGA_SPEEDRUNNER =
            ConfiguredFeatures.register("taiga_speedrunner", Feature.TREE, taigaSpeedrunner().build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MEGA_JUNGLE_SPEEDRUNNER_PLACED =
            ConfiguredFeatures.register("mega_jungle_speedrunner", Feature.TREE, jungleSpeedrunner().decorators(ImmutableList.of(TrunkVineTreeDecorator.INSTANCE, new LeavesVineTreeDecorator(0.25f))).build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BIG_SPEEDRUNNER =
            ConfiguredFeatures.register("big_speedrunner", Feature.TREE,
                    new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LOG),
                            new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LEAVES),
                            new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                            new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))
                            .ignoreVines()
                            .build());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> DOOM_TREE =
            ConfiguredFeatures.register("doom_tree", Feature.TREE, doomTree().build());
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_DEAD_SPEEDRUNNER_BUSH =
            ConfiguredFeatures.register("patch_dead_speedrunner_bush", Feature.RANDOM_PATCH,
                    VegetationConfiguredFeatures.createRandomPatchFeatureConfig(BlockStateProvider.of(ModBlocks.DEAD_SPEEDRUNNER_BUSH), 4));
    protected static final List<OreFeatureConfig.Target> SPEEDRUNNER_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SPEEDRUNNER_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.getDefaultState()));
    protected static final List<OreFeatureConfig.Target> IGNEOUS_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.IGNEOUS_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_IGNEOUS_ORE.getDefaultState()));
    protected static final List<OreFeatureConfig.Target> EXPERIENCE_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.EXPERIENCE_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_EXPERIENCE_ORE.getDefaultState()));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SPEEDRUNNER = ConfiguredFeatures.register("ore_speedrunner",
            Feature.ORE, new OreFeatureConfig(SPEEDRUNNER_ORES, 9));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SPEEDRUNNER_SMALL = ConfiguredFeatures.register("ore_speedrunner_small",
            Feature.ORE, new OreFeatureConfig(SPEEDRUNNER_ORES, 4));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_NETHER_SPEEDRUNNER = ConfiguredFeatures.register("ore_nether_speedrunner",
            Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.getDefaultState(), 10));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_IGNEOUS = ConfiguredFeatures.register("ore_igneous",
            Feature.ORE, new OreFeatureConfig(IGNEOUS_ORES, 4, 0.2f));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_NETHER_IGNEOUS = ConfiguredFeatures.register("ore_nether_igneous",
            Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.getDefaultState(), 4));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_EXPERIENCE = ConfiguredFeatures.register("ore_experience",
            Feature.ORE, new OreFeatureConfig(EXPERIENCE_ORES, 3, 0.3f));
    protected static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_NETHER_EXPERIENCE = ConfiguredFeatures.register("ore_nether_experience",
            Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.NETHERRACK, ModBlocks.NETHER_EXPERIENCE_ORE.getDefaultState(), 3));

    private static TreeFeatureConfig.Builder defaultSpeedrunner(Block trunk, Block leave) {
        return TreeConfiguredFeatures.builder(
                trunk, leave, 4, 2, 0, 2)
                .ignoreVines();
    }

    private static TreeFeatureConfig.Builder fancySpeedrunner(Block trunk, Block leave) {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(trunk),
                new LargeOakTrunkPlacer(3, 11, 0), BlockStateProvider.of(leave),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                .ignoreVines();
    }

    private static TreeFeatureConfig.Builder birchSpeedrunner() {
        return TreeConfiguredFeatures.builder(
                ModBlocks.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LEAVES, 5, 2, 6, 2).ignoreVines();
    }

    private static TreeFeatureConfig.Builder savannaSpeedrunner() {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LOG),
                new ForkingTrunkPlacer(5, 2, 2),
                BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LEAVES),
                new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines();
    }

    private static TreeFeatureConfig.Builder taigaSpeedrunner() {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LOG),
                new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LEAVES),
                new SpruceFoliagePlacer(UniformIntProvider.create(2, 3), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 2)),
                new TwoLayersFeatureSize(2, 0, 2))
                .ignoreVines();
    }

    private static TreeFeatureConfig.Builder jungleSpeedrunner() {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LOG),
                new MegaJungleTrunkPlacer(10, 2, 19), BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LEAVES),
                new JungleFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 2),
                new TwoLayersFeatureSize(1, 1, 2));
    }

    private static TreeFeatureConfig.Builder doomTree() {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(
                ModBlocks.DOOM_LOG),
                new LargeOakTrunkPlacer(3, 11, 0), BlockStateProvider.of(ModBlocks.DOOM_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                .ignoreVines();
    }
}