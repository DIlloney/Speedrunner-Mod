package net.dillon8775.speedrunnermod.world;

import net.dillon8775.speedrunnermod.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.*;

public class ModWorldGen {

    public static void init() {
        if (options().advanced.generateSpeedrunnerWood) {
            BiomeModifications.addFeature(BiomeSelectors.categories(
                            Biome.Category.PLAINS,
                            Biome.Category.FOREST,
                            Biome.Category.SAVANNA,
                            Biome.Category.SWAMP,
                            Biome.Category.JUNGLE,
                            Biome.Category.EXTREME_HILLS,
                            Biome.Category.MOUNTAIN,
                            Biome.Category.TAIGA),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.DEFAULT_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.FOREST),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.DEFAULT_SPEEDRUNNER_PLACED_FOREST.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.FANCY_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.categories(
                            Biome.Category.DESERT,
                            Biome.Category.MESA),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.DEAD_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.BIRCH_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SAVANNA),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.SAVANNA_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.TAIGA),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.TAIGA_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.MEGA_JUNGLE_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.BIG_SPEEDRUNNER_PLACED.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SWAMP),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH_SWAMP.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.DESERT),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    BuiltinRegistries.PLACED_FEATURE.getKey(ModPlacedFeatures.PATCH_DEAD_SPEEDRUNNER_BUSH_DESERT.value()).get());

            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.MESA),
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