package net.dillon8775.speedrunnermod.world;

import net.dillon8775.speedrunnermod.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.*;

public class ModWorldGen {

    public static void init() {
        if (options().advanced.generateSpeedrunnerWood) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                            BiomeKeys.PLAINS,
                            BiomeKeys.FOREST,
                            BiomeKeys.SAVANNA,
                            BiomeKeys.SWAMP,
                            BiomeKeys.JUNGLE,
                            BiomeKeys.WINDSWEPT_HILLS,
                            BiomeKeys.TAIGA),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.DEFAULT_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_FOREST),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.DEFAULT_SPEEDRUNNER_PLACED_FOREST.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.FANCY_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                            BiomeKeys.DESERT,
                            BiomeKeys.BADLANDS),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.DEAD_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.BIRCH_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_SAVANNA),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.SAVANNA_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_TAIGA),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.TAIGA_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.MEGA_JUNGLE_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.BIG_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.SWAMP_HUT_HAS_STRUCTURE),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.DESERT_PYRAMID_HAS_STRUCTURE),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_BADLANDS),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH_BADLANDS.value()).get());
        }

        if (DOOM_MODE) {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.DOOM_TREE_PLACED.value()).get());
        }

        info("Initialized worldgen features.");
    }
}