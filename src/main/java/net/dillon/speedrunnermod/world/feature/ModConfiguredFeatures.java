package net.dillon.speedrunnermod.world.feature;

import com.google.common.collect.ImmutableList;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
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
    protected static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
    protected static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    protected static final RuleTest NETHERRACK = new BlockMatchRuleTest(Blocks.NETHERRACK);
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEFAULT_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:default_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEAD_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:dead_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:fancy_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEAD_FANCY_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:dead_fancy_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BIRCH_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:birch_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SAVANNA_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:savanna_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TAIGA_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:taiga_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_JUNGLE_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:mega_jungle_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BIG_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:big_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DOOM_TREE = ConfiguredFeatures.of("speedrunnermod:doom_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_DEAD_SPEEDRUNNER_BUSH = ConfiguredFeatures.of("speedrunnermod:patch_dead_speedrunner_bush");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:ore_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER_SMALL = ConfiguredFeatures.of("speedrunnermod:ore_speedrunner_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_NETHER_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:ore_nether_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_IGNEOUS = ConfiguredFeatures.of("speedrunnermod:ore_igneous");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_NETHER_IGNEOUS = ConfiguredFeatures.of("speedrunnermod:ore_nether_igneous");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_EXPERIENCE = ConfiguredFeatures.of("speedrunnermod:ore_experience");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_NETHER_EXPERIENCE = ConfiguredFeatures.of("speedrunnermod:ore_nether_experience");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        List<OreFeatureConfig.Target> speedrunnerOres = List.of(
                OreFeatureConfig.createTarget(STONE_ORE_REPLACEABLES, ModBlocks.SPEEDRUNNER_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherSpeedrunnerOres = List.of(
                OreFeatureConfig.createTarget(NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> igneousOres = List.of(
                OreFeatureConfig.createTarget(STONE_ORE_REPLACEABLES, ModBlocks.IGNEOUS_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_IGNEOUS_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherIgneousOres = List.of(
                OreFeatureConfig.createTarget(NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> experienceOres = List.of(
                OreFeatureConfig.createTarget(STONE_ORE_REPLACEABLES, ModBlocks.EXPERIENCE_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_EXPERIENCE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherExperienceOres = List.of(
                OreFeatureConfig.createTarget(NETHERRACK, ModBlocks.NETHER_EXPERIENCE_ORE.getDefaultState()));

        ConfiguredFeatures.register(context, DEFAULT_SPEEDRUNNER, Feature.TREE, defaultSpeedrunner(ModBlocks.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LEAVES).build());
        ConfiguredFeatures.register(context, DEAD_SPEEDRUNNER, Feature.TREE, defaultSpeedrunner(ModBlocks.DEAD_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_LEAVES).build());
        ConfiguredFeatures.register(context, FANCY_SPEEDRUNNER, Feature.TREE, fancySpeedrunner(ModBlocks.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LEAVES).build());
        ConfiguredFeatures.register(context, DEAD_FANCY_SPEEDRUNNER, Feature.TREE, fancySpeedrunner(ModBlocks.DEAD_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_LEAVES).build());
        ConfiguredFeatures.register(context, BIRCH_SPEEDRUNNER,  Feature.TREE, birchSpeedrunner().build());
        ConfiguredFeatures.register(context, SAVANNA_SPEEDRUNNER, Feature.TREE, savannaSpeedrunner().build());
        ConfiguredFeatures.register(context, TAIGA_SPEEDRUNNER, Feature.TREE, taigaSpeedrunner().build());
        ConfiguredFeatures.register(context, MEGA_JUNGLE_SPEEDRUNNER,  Feature.TREE, jungleSpeedrunner().decorators(ImmutableList.of(TrunkVineTreeDecorator.INSTANCE, new LeavesVineTreeDecorator(0.25f))).build());
        ConfiguredFeatures.register(context, BIG_SPEEDRUNNER, Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LOG),
                        new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(ModBlocks.SPEEDRUNNER_LEAVES),
                        new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))
                        .ignoreVines()
                        .build());
        ConfiguredFeatures.register(context, DOOM_TREE, Feature.TREE, doomTree().build());
        ConfiguredFeatures.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH, Feature.RANDOM_PATCH,
                VegetationConfiguredFeatures.createRandomPatchFeatureConfig(BlockStateProvider.of(ModBlocks.DEAD_SPEEDRUNNER_BUSH), 4));
        ConfiguredFeatures.register(context, ORE_SPEEDRUNNER, Feature.ORE, new OreFeatureConfig(speedrunnerOres, 9));
        ConfiguredFeatures.register(context, ORE_SPEEDRUNNER_SMALL,  Feature.ORE, new OreFeatureConfig(speedrunnerOres, 4));
        ConfiguredFeatures.register(context, ORE_NETHER_SPEEDRUNNER, Feature.ORE, new OreFeatureConfig(netherSpeedrunnerOres, 10));
        ConfiguredFeatures.register(context, ORE_IGNEOUS, Feature.ORE, new OreFeatureConfig(igneousOres, 4, 0.2F));
        ConfiguredFeatures.register(context, ORE_NETHER_IGNEOUS, Feature.ORE, new OreFeatureConfig(netherIgneousOres, 4));
        ConfiguredFeatures.register(context, ORE_EXPERIENCE, Feature.ORE, new OreFeatureConfig(experienceOres, 3, 0.3F));
        ConfiguredFeatures.register(context, ORE_NETHER_EXPERIENCE, Feature.ORE, new OreFeatureConfig(netherExperienceOres, 3));
    }

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