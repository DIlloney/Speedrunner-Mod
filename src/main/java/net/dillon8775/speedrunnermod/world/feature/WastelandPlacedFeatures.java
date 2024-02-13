package net.dillon8775.speedrunnermod.world.feature;

import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import static net.minecraft.world.gen.feature.OrePlacedFeatures.modifiersWithCount;

public class WastelandPlacedFeatures {
    public static final PlacedFeature DEFAULT_SPEEDRUNNER_PLACED = PlacedFeatures.register("default_speedrunner_placed_speedrunners_wasteland",
            WastelandConfiguredFeatures.DEFAULT_SPEEDRUNNER.withPlacement(
                    PlacedFeatures.createCountExtraModifier(1, 0.05F, 1),
                    SquarePlacementModifier.of(),
                    VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature FANCY_SPEEDRUNNER_PLACED_SPEEDRUNNERS_WASTELAND = PlacedFeatures.register("fancy_speedrunner_placed_speedrunners_wasteland",
            WastelandConfiguredFeatures.FANCY_SPEEDRUNNER.withPlacement(
                    PlacedFeatures.createCountExtraModifier(0, 0.20F, 1),
                    SquarePlacementModifier.of(),
                    VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(ModBlocks.SPEEDRUNNER_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()));
    public static final PlacedFeature PATCH_RAW_SPEEDRUNNER_BLOCK = PlacedFeatures.register("patch_raw_speedrunner_block",
            WastelandConfiguredFeatures.PATCH_RAW_SPEEDRUNNER_BLOCK.withPlacement(
                    RarityFilterPlacementModifier.of(200), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
    public static final PlacedFeature ORE_SPEEDRUNNER_UPPER = PlacedFeatures.register("ore_speedrunner_upper_speedrunners_wasteland",
            ModConfiguredFeatures.ORE_SPEEDRUNNER.withPlacement(
                    modifiersWithCount(90, HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384)))));
    public static final PlacedFeature ORE_SPEEDRUNNER_MIDDLE = PlacedFeatures.register("ore_speedrunner_middle_speedrunners_wasteland",
            WastelandConfiguredFeatures.ORE_SPEEDRUNNER.withPlacement(
                    modifiersWithCount(10, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56)))));
    public static final PlacedFeature ORE_SPEEDRUNNER_SMALL = PlacedFeatures.register("ore_speedrunner_small_speedrunners_wasteland",
            WastelandConfiguredFeatures.ORE_SPEEDRUNNER_SMALL.withPlacement(
                    modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(72)))));
    public static final PlacedFeature ORE_EXPERIENCE = PlacedFeatures.register("ore_experience_speedrunners_wasteland",
            WastelandConfiguredFeatures.ORE_EXPERIENCE.withPlacement(
                    modifiersWithCount(148, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(62)))));
    public static final PlacedFeature ORE_DIAMOND = PlacedFeatures.register("ore_diamond_speedrunners_wasteland",
            WastelandConfiguredFeatures.ORE_DIAMOND.withPlacement(
                    CountPlacementModifier.of(11), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
    public static final PlacedFeature ORE_DIAMOND_BURIED = PlacedFeatures.register("ore_diamond_buried_speedrunners_wasteland",
            WastelandConfiguredFeatures.ORE_DIAMOND_BURIED.withPlacement(
                    CountPlacementModifier.of(7), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
}