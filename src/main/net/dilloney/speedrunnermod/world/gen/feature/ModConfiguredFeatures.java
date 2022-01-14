package net.dilloney.speedrunnermod.world.gen.feature;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.block.ModBlocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> ORE_SPEEDRUNNER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.SPEEDRUNNER_ORE.getDefaultState(), 9))
                .rangeOf(64)
                .spreadHorizontally()
                .repeat(20);

    public static final ConfiguredFeature<?, ?> ORE_SPEEDRUNNER_NETHER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.getDefaultState(), 9))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(10, 20, 124)))
                .spreadHorizontally()
                .repeat(10);

    public static final ConfiguredFeature<?, ?> ORE_SPEEDRUNNER_DELTAS = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.getDefaultState(), 9))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(10, 20, 124)))
                .spreadHorizontally()
                .repeat(20);

    public static final ConfiguredFeature<?, ?> ORE_IGNEOUS = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.IGNEOUS_ORE.getDefaultState(), 4))
                .rangeOf(24)
                .spreadHorizontally()
                .repeat(10);

    public static final ConfiguredFeature<?, ?> ORE_IGNEOUS_NETHER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.getDefaultState(), 4))
                .rangeOf(24)
                .spreadHorizontally()
                .repeat(10);

    public static final ConfiguredFeature<?, ?> ORE_IGNEOUS_DELTAS = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.getDefaultState(), 4))
                .rangeOf(24)
                .spreadHorizontally()
                .repeat(20);

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
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_igneous_nether"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_IGNEOUS_NETHER.getValue(), ModConfiguredFeatures.ORE_IGNEOUS_NETHER);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_IGNEOUS_DELTAS = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_igneous_deltas"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_IGNEOUS_DELTAS.getValue(), ModConfiguredFeatures.ORE_IGNEOUS_DELTAS);
    }
}