package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.UndergroundConfiguredFeatures;
import net.minecraft.world.gen.feature.UndergroundPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * Makes monster rooms spawn more commonly.
 */
@Mixin(UndergroundPlacedFeatures.class)
public class UndergroundPlacedFeaturesMixin {
    @Shadow
    private static final RegistryEntry<PlacedFeature> MONSTER_ROOM, MONSTER_ROOM_DEEP;

    static {
        if (options().main.structureSpawnRates == ModOptions.StructureSpawnRates.VERY_COMMON || options().main.structureSpawnRates == ModOptions.StructureSpawnRates.COMMON) {
            MONSTER_ROOM = PlacedFeatures.register("monster_room_" + SpeedrunnerMod.MOD_ID, UndergroundConfiguredFeatures.MONSTER_ROOM, new PlacementModifier[]{CountPlacementModifier.of(16), HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()), BiomePlacementModifier.of()});
            MONSTER_ROOM_DEEP = PlacedFeatures.register("monster_room_deep_" + SpeedrunnerMod.MOD_ID, UndergroundConfiguredFeatures.MONSTER_ROOM, new PlacementModifier[]{CountPlacementModifier.of(16), HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.fixed(-1)), BiomePlacementModifier.of()});
        } else {
            MONSTER_ROOM = PlacedFeatures.register("monster_room_" + SpeedrunnerMod.MOD_ID, UndergroundConfiguredFeatures.MONSTER_ROOM, new PlacementModifier[]{CountPlacementModifier.of(10), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()), BiomePlacementModifier.of()});
            MONSTER_ROOM_DEEP = PlacedFeatures.register("monster_room_deep_" + SpeedrunnerMod.MOD_ID, UndergroundConfiguredFeatures.MONSTER_ROOM, new PlacementModifier[]{CountPlacementModifier.of(4), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.fixed(-1)), BiomePlacementModifier.of()});
        }
    }
}