package net.dilloney.speedrunnermod.world.gen.feature;

import com.google.common.collect.ImmutableList;
import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.block.ModBlocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> ORE_SPEEDRUNNER = Feature.ORE.configure(new OreFeatureConfig(
            ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, ModBlocks.SPEEDRUNNER_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE.getDefaultState())), 9))
                .uniformRange(YOffset.getBottom(), YOffset.fixed(128))
                .spreadHorizontally()
                .repeat(20);

    public static final ConfiguredFeature<?, ?> ORE_SPEEDRUNNER_NETHER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.getDefaultState(), 9))
                .uniformRange(YOffset.getBottom(), YOffset.fixed(63))
                .spreadHorizontally()
                .repeat(10);

    public static final ConfiguredFeature<?, ?> ORE_SPEEDRUNNER_DELTAS = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_SPEEDRUNNER_ORE.getDefaultState(), 9))
                .uniformRange(YOffset.getBottom(), YOffset.fixed(124))
                .spreadHorizontally()
                .repeat(20);

    public static final ConfiguredFeature<?, ?> ORE_IGNEOUS = Feature.ORE.configure(new OreFeatureConfig(
                    ImmutableList.of(OreFeatureConfig.createTarget(OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES, ModBlocks.IGNEOUS_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreFeatureConfig.Rules.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_IGNEOUS_ORE.getDefaultState())), 4))
                .uniformRange(YOffset.getBottom(), YOffset.fixed(24))
                .spreadHorizontally()
                .repeat(10);

    public static final ConfiguredFeature<?, ?> ORE_IGNEOUS_NETHER = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.getDefaultState(), 4))
                .uniformRange(YOffset.getBottom(), YOffset.fixed(24))
                .spreadHorizontally()
                .repeat(10);

    public static final ConfiguredFeature<?, ?> ORE_IGNEOUS_DELTAS = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.NETHERRACK, ModBlocks.NETHER_IGNEOUS_ORE.getDefaultState(), 4))
                .uniformRange(YOffset.getBottom(), YOffset.fixed(24))
                .spreadHorizontally()
                .repeat(20);

    public static void init() {
        RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_speedrunner"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_SPEEDRUNNER.getValue(), ModConfiguredFeatures.ORE_SPEEDRUNNER);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER_NETHER = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_speedrunner_nether"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_SPEEDRUNNER_NETHER.getValue(), ModConfiguredFeatures.ORE_SPEEDRUNNER_NETHER);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_SPEEDRUNNER_DELTAS = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_speedrunner_deltas"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_SPEEDRUNNER_DELTAS.getValue(), ModConfiguredFeatures.ORE_SPEEDRUNNER_DELTAS);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_IGNEOUS = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_igneous"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_IGNEOUS.getValue(), ModConfiguredFeatures.ORE_IGNEOUS);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_IGNEOUS_NETHER = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_igneous_nether"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_IGNEOUS_NETHER.getValue(), ModConfiguredFeatures.ORE_IGNEOUS_NETHER);

        RegistryKey<ConfiguredFeature<?, ?>> ORE_IGNEOUS_DELTAS = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(SpeedrunnerMod.MOD_ID, "ore_igneous_deltas"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_IGNEOUS_DELTAS.getValue(), ModConfiguredFeatures.ORE_IGNEOUS_DELTAS);
    }
}