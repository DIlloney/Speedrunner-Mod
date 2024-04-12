<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;
import static net.minecraft.world.gen.feature.OrePlacedFeatures.modifiersWithCount;
import static net.minecraft.world.gen.feature.OrePlacedFeatures.modifiersWithRarity;

/**
 * Makes certain ores generate more commonly, according to the {@code Common Ores} option.
 */
@Mixin(OrePlacedFeatures.class)
public class OrePlacedFeaturesMixin {
    @Shadow
    private static final RegistryEntry<PlacedFeature> ORE_DIAMOND, ORE_DIAMOND_LARGE, ORE_DIAMOND_BURIED, ORE_LAPIS, ORE_LAPIS_BURIED, ORE_ANCIENT_DEBRIS_LARGE, ORE_DEBRIS_SMALL;

    static {
        if (options().main.commonOres) {
            ORE_DIAMOND = PlacedFeatures.register("ore_diamond_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_SMALL, CountPlacementModifier.of(6), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)));
            ORE_DIAMOND_LARGE = PlacedFeatures.register("ore_diamond_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_LARGE, CountPlacementModifier.of(4), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)));
            ORE_DIAMOND_BURIED = PlacedFeatures.register("ore_diamond_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_BURIED, CountPlacementModifier.of(6), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)));
            ORE_LAPIS = PlacedFeatures.register("ore_lapis_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS, CountPlacementModifier.of(2), HeightRangePlacementModifier.trapezoid(YOffset.fixed(-32), YOffset.fixed(32)));
            ORE_LAPIS_BURIED = PlacedFeatures.register("ore_lapis_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS_BURIED, CountPlacementModifier.of(2), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64)));
            ORE_ANCIENT_DEBRIS_LARGE = PlacedFeatures.register("ore_ancient_debris_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_LARGE, new PlacementModifier[]{CountPlacementModifier.of(2), HeightRangePlacementModifier.trapezoid(YOffset.fixed(8), YOffset.fixed(24)), BiomePlacementModifier.of()});
            ORE_DEBRIS_SMALL = PlacedFeatures.register("ore_debris_small_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_SMALL, new PlacementModifier[]{CountPlacementModifier.of(3), PlacedFeatures.EIGHT_ABOVE_AND_BELOW_RANGE, BiomePlacementModifier.of()});
        } else {
            ORE_DIAMOND = PlacedFeatures.register("ore_diamond_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_SMALL, modifiersWithCount(7, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
            ORE_DIAMOND_LARGE = PlacedFeatures.register("ore_diamond_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_LARGE, modifiersWithRarity(9, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
            ORE_DIAMOND_BURIED = PlacedFeatures.register("ore_diamond_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_BURIED, modifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
            ORE_LAPIS = PlacedFeatures.register("ore_lapis_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS, modifiersWithCount(2, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-32), YOffset.fixed(32))));
            ORE_LAPIS_BURIED = PlacedFeatures.register("ore_lapis_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS_BURIED, modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
            ORE_ANCIENT_DEBRIS_LARGE = PlacedFeatures.register("ore_ancient_debris_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_LARGE, new PlacementModifier[]{SquarePlacementModifier.of(), HeightRangePlacementModifier.trapezoid(YOffset.fixed(8), YOffset.fixed(24)), BiomePlacementModifier.of()});
            ORE_DEBRIS_SMALL = PlacedFeatures.register("ore_debris_small_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_SMALL, new PlacementModifier[]{SquarePlacementModifier.of(), PlacedFeatures.EIGHT_ABOVE_AND_BELOW_RANGE, BiomePlacementModifier.of()});
        }
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;
import static net.minecraft.world.gen.feature.OrePlacedFeatures.modifiersWithCount;
import static net.minecraft.world.gen.feature.OrePlacedFeatures.modifiersWithRarity;

/**
 * Makes certain ores generate more commonly, according to the {@code Common Ores} option.
 */
@Mixin(OrePlacedFeatures.class)
public class OrePlacedFeaturesMixin {
    @Shadow
    private static final RegistryEntry<PlacedFeature> ORE_DIAMOND, ORE_DIAMOND_LARGE, ORE_DIAMOND_BURIED, ORE_LAPIS, ORE_LAPIS_BURIED, ORE_ANCIENT_DEBRIS_LARGE, ORE_DEBRIS_SMALL;

    static {
        if (options().main.commonOres) {
            ORE_DIAMOND = PlacedFeatures.register("ore_diamond_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_SMALL, CountPlacementModifier.of(6), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)));
            ORE_DIAMOND_LARGE = PlacedFeatures.register("ore_diamond_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_LARGE, CountPlacementModifier.of(4), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)));
            ORE_DIAMOND_BURIED = PlacedFeatures.register("ore_diamond_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_BURIED, CountPlacementModifier.of(6), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)));
            ORE_LAPIS = PlacedFeatures.register("ore_lapis_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS, CountPlacementModifier.of(2), HeightRangePlacementModifier.trapezoid(YOffset.fixed(-32), YOffset.fixed(32)));
            ORE_LAPIS_BURIED = PlacedFeatures.register("ore_lapis_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS_BURIED, CountPlacementModifier.of(2), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64)));
            ORE_ANCIENT_DEBRIS_LARGE = PlacedFeatures.register("ore_ancient_debris_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_LARGE, new PlacementModifier[]{CountPlacementModifier.of(2), HeightRangePlacementModifier.trapezoid(YOffset.fixed(8), YOffset.fixed(24)), BiomePlacementModifier.of()});
            ORE_DEBRIS_SMALL = PlacedFeatures.register("ore_debris_small_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_SMALL, new PlacementModifier[]{CountPlacementModifier.of(3), PlacedFeatures.EIGHT_ABOVE_AND_BELOW_RANGE, BiomePlacementModifier.of()});
        } else {
            ORE_DIAMOND = PlacedFeatures.register("ore_diamond_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_SMALL, modifiersWithCount(7, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
            ORE_DIAMOND_LARGE = PlacedFeatures.register("ore_diamond_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_LARGE, modifiersWithRarity(9, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
            ORE_DIAMOND_BURIED = PlacedFeatures.register("ore_diamond_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_BURIED, modifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
            ORE_LAPIS = PlacedFeatures.register("ore_lapis_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS, modifiersWithCount(2, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-32), YOffset.fixed(32))));
            ORE_LAPIS_BURIED = PlacedFeatures.register("ore_lapis_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS_BURIED, modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
            ORE_ANCIENT_DEBRIS_LARGE = PlacedFeatures.register("ore_ancient_debris_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_LARGE, new PlacementModifier[]{SquarePlacementModifier.of(), HeightRangePlacementModifier.trapezoid(YOffset.fixed(8), YOffset.fixed(24)), BiomePlacementModifier.of()});
            ORE_DEBRIS_SMALL = PlacedFeatures.register("ore_debris_small_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_SMALL, new PlacementModifier[]{SquarePlacementModifier.of(), PlacedFeatures.EIGHT_ABOVE_AND_BELOW_RANGE, BiomePlacementModifier.of()});
        }
    }
>>>>>>> Stashed changes
}