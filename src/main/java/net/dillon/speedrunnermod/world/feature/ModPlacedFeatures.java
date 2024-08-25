package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.data.generator.ModWorldGenerator;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import static net.minecraft.world.gen.feature.OrePlacedFeatures.modifiersWithCount;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> DEFAULT_SPEEDRUNNER_PLACED = of("speedrunnermod:default_speedrunner_placed");
    public static final RegistryKey<PlacedFeature> DEFAULT_SPEEDRUNNER_PLACED_FOREST = of("speedrunnermod:default_speedrunner_placed_forest");
    public static final RegistryKey<PlacedFeature> DEAD_SPEEDRUNNER_PLACED = of("speedrunnermod:dead_speedrunner_placed");
    public static final RegistryKey<PlacedFeature> FANCY_SPEEDRUNNER_PLACED = of("speedrunnermod:fancy_speedrunner_placed");
    public static final RegistryKey<PlacedFeature> DEAD_FANCY_SPEEDRUNNER_PLACED = of("speedrunnermod:dead_fancy_speedrunner_placed");
    public static final RegistryKey<PlacedFeature> BIRCH_SPEEDRUNNER_PLACED = of("speedrunnermod:birch_speedrunner_placed");
    public static final RegistryKey<PlacedFeature> SAVANNA_SPEEDRUNNER_PLACED = of("speedrunnermod:savanna_speedrunner_placed");
    public static final RegistryKey<PlacedFeature> TAIGA_SPEEDRUNNER_PLACED = of("speedrunnermod:taiga_speedrunner_placed");
    public static final RegistryKey<PlacedFeature> MEGA_JUNGLE_SPEEDRUNNER_PLACED = of("speedrunnermod:mega_jungle_speedrunner_placed");
    public static final RegistryKey<PlacedFeature> BIG_SPEEDRUNNER_PLACED = of("speedrunnermod:big_speedrunner_placed");
    public static final RegistryKey<PlacedFeature> DOOM_TREE_PLACED = of("speedrunnermod:doom_tree_placed");
    public static final RegistryKey<PlacedFeature> ORE_SPEEDRUNNER_UPPER = of("speedrunnermod:ore_speedrunner_upper");
    public static final RegistryKey<PlacedFeature> ORE_SPEEDRUNNER_MIDDLE = of("speedrunnermod:ore_speedrunner_middle");
    public static final RegistryKey<PlacedFeature> ORE_SPEEDRUNNER_SMALL = of("speedrunnermod:ore_speedrunner_small");
    public static final RegistryKey<PlacedFeature> ORE_SPEEDRUNNER_DELTAS = of("speedrunnermod:ore_speedrunner_deltas");
    public static final RegistryKey<PlacedFeature> ORE_SPEEDRUNNER_NETHER = of("speedrunnermod:ore_speedrunner_nether");
    public static final RegistryKey<PlacedFeature> ORE_IGNEOUS = of("speedrunnermod:ore_igneous");
    public static final RegistryKey<PlacedFeature> ORE_IGNEOUS_DELTAS = of("speedrunnermod:ore_igneous_deltas");
    public static final RegistryKey<PlacedFeature> ORE_IGNEOUS_NETHER = of("speedrunnermod:ore_igneous_nether");
    public static final RegistryKey<PlacedFeature> ORE_EXPERIENCE = of("speedrunnermod:ore_experience");
    public static final RegistryKey<PlacedFeature> ORE_EXPERIENCE_DELTAS = of("speedrunnermod:ore_experience_deltas");
    public static final RegistryKey<PlacedFeature> ORE_EXPERIENCE_NETHER = of("speedrunnermod:ore_experience_nether");
    public static final RegistryKey<PlacedFeature> PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP = of("speedrunnermod:patch_dead_speedrunner_bush_swamp");
    public static final RegistryKey<PlacedFeature> PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT = of("speedrunnermod:patch_dead_speedrunner_bush_desert");
    public static final RegistryKey<PlacedFeature> PATCH_DEAD_SPEEDRUNNER_BUSH_BADLANDS = of("speedrunnermod:patch_dead_speedrunner_bush_badlands");

    /**
     * See {@link ModWorldGenerator} for more.
     */
    public static void bootstrap(Registerable<PlacedFeature> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        PlacedFeatures.register(context, DEFAULT_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.DEFAULT_SPEEDRUNNER),
                PlacedFeatures.createCountExtraModifier(0, 0.10F, 1),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
        PlacedFeatures.register(context, DEFAULT_SPEEDRUNNER_PLACED_FOREST, registryEntryLookup.getOrThrow(ModConfiguredFeatures.FANCY_SPEEDRUNNER),
                PlacedFeatures.createCountExtraModifier(1, 0.20F, 1),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
        PlacedFeatures.register(context, DEAD_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.DEAD_SPEEDRUNNER),
                PlacedFeatures.createCountExtraModifier(0, 0.10F, 1),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
        PlacedFeatures.register(context, FANCY_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.FANCY_SPEEDRUNNER),
                PlacedFeatures.createCountExtraModifier(0, 0.05F, 1),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
        PlacedFeatures.register(context, DEAD_FANCY_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.DEAD_FANCY_SPEEDRUNNER),
                CountMultilayerPlacementModifier.of(1),
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)),
                BiomePlacementModifier.of());
        PlacedFeatures.register(context, BIRCH_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.BIRCH_SPEEDRUNNER),
                PlacedFeatures.createCountExtraModifier(1, 0.20F, 1),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
        PlacedFeatures.register(context, SAVANNA_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.SAVANNA_SPEEDRUNNER),
                PlacedFeatures.createCountExtraModifier(0, 0.50F, 1),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
        PlacedFeatures.register(context, TAIGA_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.TAIGA_SPEEDRUNNER),
                PlacedFeatures.createCountExtraModifier(1, 0.50F, 1),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
        PlacedFeatures.register(context, MEGA_JUNGLE_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.MEGA_JUNGLE_SPEEDRUNNER),
                PlacedFeatures.createCountExtraModifier(1, 0.50F, 1),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
        PlacedFeatures.register(context, BIG_SPEEDRUNNER_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.BIG_SPEEDRUNNER),
                PlacedFeatures.createCountExtraModifier(0, 0.20F, 1),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of());
        PlacedFeatures.register(context, DOOM_TREE_PLACED, registryEntryLookup.getOrThrow(ModConfiguredFeatures.DOOM_TREE), VegetationPlacedFeatures.modifiers(1));
        PlacedFeatures.register(context, ORE_SPEEDRUNNER_UPPER, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_SPEEDRUNNER),
                modifiersWithCount(72, HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384))));
        PlacedFeatures.register(context, ORE_SPEEDRUNNER_MIDDLE, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_SPEEDRUNNER),
                modifiersWithCount(10, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56))));
        PlacedFeatures.register(context, ORE_SPEEDRUNNER_SMALL, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_SPEEDRUNNER_SMALL),
                modifiersWithCount(9, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(72))));
        PlacedFeatures.register(context, ORE_SPEEDRUNNER_DELTAS, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_SPEEDRUNNER),
                modifiersWithCount(20, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE));
        PlacedFeatures.register(context, ORE_SPEEDRUNNER_NETHER, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_SPEEDRUNNER),
                modifiersWithCount(10, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE));
        PlacedFeatures.register(context, ORE_IGNEOUS, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_IGNEOUS),
                modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(24))));
        PlacedFeatures.register(context, ORE_IGNEOUS_DELTAS, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_IGNEOUS),
                modifiersWithCount(20, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE));
        PlacedFeatures.register(context, ORE_IGNEOUS_NETHER, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_IGNEOUS),
                modifiersWithCount(10, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE));
        PlacedFeatures.register(context, ORE_EXPERIENCE, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_EXPERIENCE),
                modifiersWithCount(28, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(49))));
        PlacedFeatures.register(context, ORE_EXPERIENCE_DELTAS, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_EXPERIENCE),
                modifiersWithCount(108, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE));
        PlacedFeatures.register(context, ORE_EXPERIENCE_NETHER, registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_NETHER_EXPERIENCE),
                modifiersWithCount(92, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE));
        PlacedFeatures.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP, registryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH),
                SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT, registryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH),
                VegetationPlacedFeatures.modifiers(2));
        PlacedFeatures.register(context, PATCH_DEAD_SPEEDRUNNER_BUSH_BADLANDS, registryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH),
                VegetationPlacedFeatures.modifiers(3));
    }

    protected static RegistryKey<PlacedFeature> of(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(id));
    }
}