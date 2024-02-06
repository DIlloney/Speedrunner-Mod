package net.dillon8775.speedrunnermod.world.biome;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.world.gen.feature.ModPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.UndergroundPlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

/**
 * <p>See {@link net.dillon8775.speedrunnermod.mixin.main.worldgen.BiomeGeneration} for more. </p>
 * <p></p>
 * See {@link net.dillon8775.speedrunnermod.mixin.main.worldgen.ModdedWorldGeneration} for structure generation.
 */
public class ModBiomes {
    public static final RegistryKey<Biome> SPEEDRUNNERS_WASTELAND_KEY = RegistryKey.of(
            Registry.BIOME_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunners_wasteland"));
    public static final Biome SPEEDRUNNERS_WASTELAND = createSpeedrunnersWasteland();

    public static void init() {
        Registry.register(BuiltinRegistries.BIOME, SPEEDRUNNERS_WASTELAND_KEY.getValue(), SPEEDRUNNERS_WASTELAND);
    }

    private static Biome createSpeedrunnersWasteland() {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();

        OverworldBiomeCreator.addBasicFeatures(generationBuilder);

        DefaultBiomeFeatures.addPlainsTallGrass(generationBuilder);
        generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        DefaultBiomeFeatures.addDefaultFlowers(generationBuilder);

        DefaultBiomeFeatures.addCaveMobs(spawnBuilder);
        addSpeedrunnersWastelandMonsters(spawnBuilder, 25, 25, 25);

        addSpeedrunnersWastelandOres(generationBuilder);
        DefaultBiomeFeatures.addDefaultDisks(generationBuilder);

        addSpeedrunnersWastelandFeatures(generationBuilder);

        DefaultBiomeFeatures.addDefaultVegetation(generationBuilder);

        return new Biome.Builder()
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.PLAINS)
                .temperature(0.3F)
                .downfall(0.7F)
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x85C1E9)
                        .waterFogColor(0x85C1E9)
                        .fogColor(0xEBF5FB)
                        .skyColor(OverworldBiomeCreator.getSkyColor(0.5F))
                        .foliageColor(0xAED6F1)
                        .grassColor(0xAED6F1)
                        .moodSound(BiomeMoodSound.CAVE).build())
                .spawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build()).build();
    }

    private static void addSpeedrunnersWastelandOres(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.SpeedrunnersWasteland.ORE_DIAMOND);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.SpeedrunnersWasteland.ORE_DIAMOND_BURIED);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.SpeedrunnersWasteland.ORE_SPEEDRUNNER_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.SpeedrunnersWasteland.ORE_SPEEDRUNNER_MIDDLE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.SpeedrunnersWasteland.ORE_SPEEDRUNNER_SMALL);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COAL_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COAL_LOWER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_IRON_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_IRON_MIDDLE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_IRON_SMALL);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GOLD);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GOLD_LOWER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_REDSTONE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_REDSTONE_LOWER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_LAPIS);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_LAPIS_BURIED);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, UndergroundPlacedFeatures.UNDERWATER_MAGMA);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_IGNEOUS);
    }

    private static void addSpeedrunnersWastelandFeatures(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SpeedrunnersWasteland.PATCH_RAW_SPEEDRUNNER_BLOCK);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SpeedrunnersWasteland.DEFAULT_SPEEDRUNNER_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SpeedrunnersWasteland.FANCY_SPEEDRUNNER_PLACED_SPEEDRUNNERS_WASTELAND);
    }

    private static void addSpeedrunnersWastelandMonsters(SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight) {
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 25, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, zombieWeight, 1, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, skeletonWeight, 1, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 25, 1, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 100, 1, 4));
    }
}