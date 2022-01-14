package net.dilloney.speedrunnermod.world.gen.feature;

import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;

import static net.minecraft.world.gen.feature.OrePlacedFeatures.modifiersWithCount;

public class ModOrePlacedFeatures {
    public static final PlacedFeature ORE_SPEEDRUNNER_UPPER;
    public static final PlacedFeature ORE_SPEEDRUNNER_MIDDLE;
    public static final PlacedFeature ORE_SPEEDRUNNER_SMALL;
    public static final PlacedFeature ORE_SPEEDRUNNER_DELTAS;
    public static final PlacedFeature ORE_SPEEDRUNNER_NETHER;
    public static final PlacedFeature ORE_IGNEOUS;
    public static final PlacedFeature ORE_IGNEOUS_DELTAS;
    public static final PlacedFeature ORE_IGNEOUS_NETHER;

    static {
        ORE_SPEEDRUNNER_UPPER = PlacedFeatures.register("ore_speedrunner_upper", ModOreConfiguredFeatures.ORE_SPEEDRUNNER.withPlacement(modifiersWithCount(90, HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384)))));
        ORE_SPEEDRUNNER_MIDDLE = PlacedFeatures.register("ore_speedrunner_middle", ModOreConfiguredFeatures.ORE_SPEEDRUNNER.withPlacement(modifiersWithCount(10, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56)))));
        ORE_SPEEDRUNNER_SMALL = PlacedFeatures.register("ore_speedrunner_small", ModOreConfiguredFeatures.ORE_SPEEDRUNNER_SMALL.withPlacement(modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(72)))));
        ORE_SPEEDRUNNER_DELTAS = PlacedFeatures.register("ore_speedrunner_deltas", ModOreConfiguredFeatures.ORE_NETHER_SPEEDRUNNER.withPlacement(modifiersWithCount(20, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE)));
        ORE_SPEEDRUNNER_NETHER = PlacedFeatures.register("ore_speedrunner_nether", ModOreConfiguredFeatures.ORE_NETHER_SPEEDRUNNER.withPlacement(modifiersWithCount(10, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE)));
        ORE_IGNEOUS = PlacedFeatures.register("ore_igneous", ModOreConfiguredFeatures.ORE_IGNEOUS.withPlacement(modifiersWithCount(10, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(24)))));
        ORE_IGNEOUS_DELTAS = PlacedFeatures.register("ore_igneous_deltas", ModOreConfiguredFeatures.ORE_NETHER_IGNEOUS.withPlacement(modifiersWithCount(20, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE)));
        ORE_IGNEOUS_NETHER = PlacedFeatures.register("ore_igneous_nether", ModOreConfiguredFeatures.ORE_NETHER_IGNEOUS.withPlacement(modifiersWithCount(10, PlacedFeatures.TEN_ABOVE_AND_BELOW_RANGE)));
    }
}