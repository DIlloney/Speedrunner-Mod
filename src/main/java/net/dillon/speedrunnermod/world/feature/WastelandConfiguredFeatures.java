package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class WastelandConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEFAULT_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:wasteland_default_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:wasteland_fancy_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_RAW_SPEEDRUNNER_BLOCK = ConfiguredFeatures.of("speedrunnermod:wasteland_patch_raw_speedrunner_block");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER = ConfiguredFeatures.of("speedrunnermod:wasteland_ore_speedrunner");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER_SMALL = ConfiguredFeatures.of("speedrunnermod:wasteland_ore_speedrunner_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_EXPERIENCE = ConfiguredFeatures.of("speedrunnermod:wasteland_ore_experience");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_DIAMOND = ConfiguredFeatures.of("speedrunnermod:wasteland_ore_diamond");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_DIAMOND_BURIED = ConfiguredFeatures.of("speedrunnermod:wasteland_ore_diamond_buried");

    /**
     * See {@link net.dillon.speedrunnermod.datagen.ModWorldGenerator} for more.
     */
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        List<OreFeatureConfig.Target> speedrunnerOres = List.of(
                OreFeatureConfig.createTarget(ModConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SPEEDRUNNER_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(ModConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> experienceOres = List.of(
                OreFeatureConfig.createTarget(ModConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.EXPERIENCE_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(ModConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_EXPERIENCE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> diamondOres = List.of(
                OreFeatureConfig.createTarget(ModConfiguredFeatures.STONE_ORE_REPLACEABLES, Blocks.DIAMOND_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(ModConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_DIAMOND_ORE.getDefaultState()));

        ConfiguredFeatures.register(context, DEFAULT_SPEEDRUNNER, Feature.TREE, speedrunnersWasteland().build());
        ConfiguredFeatures.register(context, FANCY_SPEEDRUNNER, Feature.TREE, fancySpeedrunnersWasteland().build());
        ConfiguredFeatures.register(context, PATCH_RAW_SPEEDRUNNER_BLOCK, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.RAW_SPEEDRUNNER_BLOCK)), List.of(Blocks.GRASS_BLOCK)));
        ConfiguredFeatures.register(context, ORE_SPEEDRUNNER, Feature.ORE, new OreFeatureConfig(speedrunnerOres, 12));
        ConfiguredFeatures.register(context, ORE_SPEEDRUNNER_SMALL, Feature.ORE, new OreFeatureConfig(speedrunnerOres, 5));
        ConfiguredFeatures.register(context, ORE_EXPERIENCE, Feature.ORE, new OreFeatureConfig(experienceOres, 4, 0.2F));
        ConfiguredFeatures.register(context, ORE_DIAMOND, Feature.ORE, new OreFeatureConfig(diamondOres, 8, 0.3F));
        ConfiguredFeatures.register(context, ORE_DIAMOND_BURIED, Feature.ORE, new OreFeatureConfig(diamondOres, 12, 1.0F));
    }

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