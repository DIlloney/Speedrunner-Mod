package net.dillon8775.speedrunnermod.world.feature;

import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.BlockFilterPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import static net.minecraft.world.gen.feature.OrePlacedFeatures.modifiersWithCount;

public class ModPlacedFeatures {
    public static final PlacedFeature DEFAULT_SPEEDRUNNER_PLACED = PlacedFeatures.register("speedrunner_trees_plains",
            ModConfiguredFeatures.DEFAULT_SPEEDRUNNER.withPlacement(
                    PlacedFeatures.createCountExtraModifier(0, 0.10F, 1),
                    SquarePlacementModifier.of(),
                    VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature DEFAULT_SPEEDRUNNER_PLACED_FOREST = PlacedFeatures.register("speedrunner_trees_forest",
            ModConfiguredFeatures.DEFAULT_SPEEDRUNNER.withPlacement(
                    PlacedFeatures.createCountExtraModifier(1, 0.20F, 1),
                    SquarePlacementModifier.of(),
                    VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature FANCY_SPEEDRUNNER_PLACED = PlacedFeatures.register("fancy_speedrunner_placed",
            ModConfiguredFeatures.FANCY_SPEEDRUNNER.withPlacement(
                    PlacedFeatures.createCountExtraModifier(0, 0.05F, 1),
                    SquarePlacementModifier.of(),
                    VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature BIRCH_SPEEDRUNNER_PLACED = PlacedFeatures.register("birch_speedrunner_placed",
            ModConfiguredFeatures.BIRCH_SPEEDRUNNER.withPlacement(
                    PlacedFeatures.createCountExtraModifier(1, 0.20F, 1),
                    SquarePlacementModifier.of(),
                    VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature SAVANNA_SPEEDRUNNER_PLACED = PlacedFeatures.register("savanna_speedrunner_placed",
            ModConfiguredFeatures.SAVANNA_SPEEDRUNNER.withPlacement(
                    PlacedFeatures.createCountExtraModifier(0, 0.50F, 1),
                    SquarePlacementModifier.of(),
                    VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature TAIGA_SPEEDRUNNER_PLACED = PlacedFeatures.register("taiga_speedrunner_placed",
            ModConfiguredFeatures.TAIGA_SPEEDRUNNER.withPlacement(
                    PlacedFeatures.createCountExtraModifier(1, 0.50F, 1),
                    SquarePlacementModifier.of(),
                    VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature MEGA_JUNGLE_SPEEDRUNNER_PLACED = PlacedFeatures.register("mega_jungle_speedrunner_placed",
            ModConfiguredFeatures.MEGA_JUNGLE_SPEEDRUNNER_PLACED.withPlacement(
                    PlacedFeatures.createCountExtraModifier(1, 0.50F, 1),
                    SquarePlacementModifier.of(),
                    VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature BIG_SPEEDRUNNER_PLACED = PlacedFeatures.register("big_speedrunner_placed",
            ModConfiguredFeatures.BIG_SPEEDRUNNER.withPlacement(
                    PlacedFeatures.createCountExtraModifier(0, 0.20F, 1),
                    SquarePlacementModifier.of(),
                    VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature ORE_SPEEDRUNNER_UPPER = PlacedFeatures.register("ore_speedrunner_upper",
            ModConfiguredFeatures.ORE_SPEEDRUNNER.withPlacement(
                    modifiersWithCount(90, HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384)))));
    public static final PlacedFeature ORE_SPEEDRUNNER_MIDDLE = PlacedFeatures.register("ore_speedrunner_middle",
            ModConfiguredFeatures.ORE_SPEEDRUNNER.withPlacement(
                    modifiersWithCount(10, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56)))));
    public static final PlacedFeature ORE_SPEEDRUNNER_SMALL = PlacedFeatures.register("ore_speedrunner_small",
            ModConfiguredFeatures.ORE_SPEEDRUNNER_SMALL.withPlacement(
                    modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(72)))));
    public static final PlacedFeature ORE_SPEEDRUNNER_DELTAS = PlacedFeatures.register("ore_speedrunner_deltas",
            ModConfiguredFeatures.ORE_NETHER_SPEEDRUNNER.withPlacement(
                    modifiersWithCount(20, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE)));
    public static final PlacedFeature ORE_SPEEDRUNNER_NETHER = PlacedFeatures.register("ore_speedrunner_nether",
            ModConfiguredFeatures.ORE_NETHER_SPEEDRUNNER.withPlacement(
                    modifiersWithCount(10, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE)));
    public static final PlacedFeature ORE_IGNEOUS = PlacedFeatures.register("ore_igneous",
            ModConfiguredFeatures.ORE_IGNEOUS.withPlacement(
                    modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(24)))));
    public static final PlacedFeature ORE_IGNEOUS_DELTAS = PlacedFeatures.register("ore_igneous_deltas",
            ModConfiguredFeatures.ORE_NETHER_IGNEOUS.withPlacement(
                    modifiersWithCount(20, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE)));
    public static final PlacedFeature ORE_IGNEOUS_NETHER = PlacedFeatures.register("ore_igneous_nether",
            ModConfiguredFeatures.ORE_NETHER_IGNEOUS.withPlacement(
                    modifiersWithCount(10, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE)));
    public static final PlacedFeature ORE_EXPERIENCE = PlacedFeatures.register("ore_experience",
            ModConfiguredFeatures.ORE_EXPERIENCE.withPlacement(
                    modifiersWithCount(92, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(49)))));
    public static final PlacedFeature ORE_EXPERIENCE_DELTAS = PlacedFeatures.register("ore_experience_deltas",
            ModConfiguredFeatures.ORE_NETHER_EXPERIENCE.withPlacement(
                    modifiersWithCount(108, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE)));
    public static final PlacedFeature ORE_EXPERIENCE_NETHER = PlacedFeatures.register("ore_experience_nether",
            ModConfiguredFeatures.ORE_NETHER_EXPERIENCE.withPlacement(
                    modifiersWithCount(92, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE)));
    public static final PlacedFeature PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP = PlacedFeatures.register("patch_dead_speedrunner_bush_swamp",
            ModConfiguredFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH.withPlacement(
                    SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
    public static final PlacedFeature PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT = PlacedFeatures.register("patch_dead_speedrunner_bush_desert",
            ModConfiguredFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH.withPlacement(
                    VegetationPlacedFeatures.modifiers(2)));
    public static final PlacedFeature PATCH_DEAD_SPEEDRUNNER_BUSH_BADLANDS = PlacedFeatures.register("patch_dead_speedrunner_bush_badlands",
            ModConfiguredFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH.withPlacement(
                    VegetationPlacedFeatures.modifiers(3)));
}