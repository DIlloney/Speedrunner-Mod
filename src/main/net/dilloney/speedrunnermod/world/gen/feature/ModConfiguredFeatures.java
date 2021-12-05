package net.dilloney.speedrunnermod.world.gen.feature;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DepthAverageDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> ORE_SPEEDRUNNER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.SPEEDRUNNER_ORE.getDefaultState(), 9))
                .rangeOf(64)
                .spreadHorizontally()
                .repeat(22);

    public static final ConfiguredFeature<?, ?> ORE_SPEEDRUNNER_NETHER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.getDefaultState(), 9))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(10, 20, 124)))
                .spreadHorizontally()
                .repeat(13);

    public static final ConfiguredFeature<?, ?> ORE_SPEEDRUNNER_DELTAS = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.getDefaultState(), 9))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(10, 20, 124)))
                .spreadHorizontally()
                .repeat(13);

    public static final ConfiguredFeature<?, ?> ORE_IGNEOUS = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.IGNEOUS_ORE.getDefaultState(), 4))
                .rangeOf(24)
                .spreadHorizontally()
                .repeat(10);

    public static final ConfiguredFeature<?, ?> ORE_IGNEOUS_NETHER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.getDefaultState(), 4))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(10, 20, 48)))
                .spreadHorizontally()
                .repeat(10);

    public static final ConfiguredFeature<?, ?> ORE_IGNEOUS_DELTAS = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.getDefaultState(), 4))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(10, 20, 48)))
                .spreadHorizontally()
                .repeat(10);

    public static final ConfiguredFeature<?, ?> ORE_DIAMOND = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, Blocks.DIAMOND_ORE.getDefaultState(), 8))
                .rangeOf(16)
                .spreadHorizontally()
                .repeat(4);

    public static final ConfiguredFeature<?, ?> ORE_DEBRIS_LARGE = Feature.NO_SURFACE_ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_NETHER, Blocks.ANCIENT_DEBRIS.getDefaultState(), 3))
                .decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(16, 8)))
                .spreadHorizontally()
                .repeat(2);

    public static final ConfiguredFeature<?, ?> ORE_DEBRIS_SMALL = Feature.NO_SURFACE_ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_NETHER, Blocks.ANCIENT_DEBRIS.getDefaultState(), 2))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(8, 16, 128)))
                .spreadHorizontally()
                .repeat(3);

    public static void init() {
        RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_speedrunner"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_SPEEDRUNNER.getValue(), ModConfiguredFeatures.ORE_SPEEDRUNNER);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER_NETHER = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_speedrunner_nether"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_SPEEDRUNNER_NETHER.getValue(), ModConfiguredFeatures.ORE_SPEEDRUNNER_NETHER);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER_DELTAS = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_speedrunner_deltas"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_SPEEDRUNNER_DELTAS.getValue(), ModConfiguredFeatures.ORE_SPEEDRUNNER_DELTAS);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_IGNEOUS = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_igneous"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_IGNEOUS.getValue(), ModConfiguredFeatures.ORE_IGNEOUS);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_IGNEOUS_NETHER = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(SpeedrunnerMod.MOD_ID, "nether_igneous_ore"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_IGNEOUS_NETHER.getValue(), ModConfiguredFeatures.ORE_IGNEOUS_NETHER);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_IGNEOUS_DELTAS = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_igneous_deltas"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_IGNEOUS_DELTAS.getValue(), ModConfiguredFeatures.ORE_IGNEOUS_DELTAS);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_DIAMOND = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_diamond_" + SpeedrunnerMod.MOD_ID));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_DIAMOND.getValue(), ModConfiguredFeatures.ORE_DIAMOND);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_DEBRIS_LARGE = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_debris_large_" + SpeedrunnerMod.MOD_ID));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_DEBRIS_LARGE.getValue(), ModConfiguredFeatures.ORE_DEBRIS_LARGE);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_DEBRIS_SMALL = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_debris_small_" + SpeedrunnerMod.MOD_ID));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_DEBRIS_SMALL.getValue(), ModConfiguredFeatures.ORE_DEBRIS_SMALL);
    }
}