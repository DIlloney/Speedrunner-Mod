package com.dilloney.speedrunnermod.mixins.world;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.biome.DefaultBiomeCreator;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DefaultBiomeCreator.class)
public class DefaultBiomeCreatorMixin {

    @Overwrite
    public static Biome createPlains(boolean sunflower) {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addPlainsMobs(builder);
        if (!sunflower) {
            builder.playerSpawnFriendly();
        }

        net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        builder2.structureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS).structureFeature(ConfiguredStructureFeatures.PILLAGER_OUTPOST);

        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder2);
        builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
        DefaultBiomeFeatures.addLandCarvers(builder2);
        DefaultBiomeFeatures.addDefaultLakes(builder2);
        DefaultBiomeFeatures.addAmethystGeodes(builder2);
        DefaultBiomeFeatures.addDungeons(builder2);
        DefaultBiomeFeatures.addPlainsTallGrass(builder2);
        if (sunflower) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_SUNFLOWER);
        }

        DefaultBiomeFeatures.addMineables(builder2);
        DefaultBiomeFeatures.addDefaultOres(builder2);
        DefaultBiomeFeatures.addDefaultDisks(builder2);
        DefaultBiomeFeatures.addPlainsFeatures(builder2);
        if (sunflower) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_SUGAR_CANE);
        }

        DefaultBiomeFeatures.addDefaultMushrooms(builder2);
        if (sunflower) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_PUMPKIN);
        } else {
            DefaultBiomeFeatures.addDefaultVegetation(builder2);
        }

        DefaultBiomeFeatures.addSprings(builder2);
        DefaultBiomeFeatures.addFrozenTopLayer(builder2);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(0.8F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    @Overwrite
    private static Biome createForest(float depth, float scale, boolean flower, SpawnSettings.Builder spawnSettings) {
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder);
        builder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL).structureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS);
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        if (flower) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.FOREST_FLOWER_VEGETATION_COMMON);
        } else {
            DefaultBiomeFeatures.addForestFlowers(builder);
        }

        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        if (flower) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.FOREST_FLOWER_TREES);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.FLOWER_FOREST);
            DefaultBiomeFeatures.addDefaultGrass(builder);
        } else {
            DefaultBiomeFeatures.addForestTrees(builder);
            DefaultBiomeFeatures.addDefaultFlowers(builder);
            DefaultBiomeFeatures.addForestGrass(builder);
        }

        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultVegetation(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST).depth(depth).scale(scale).temperature(0.7F).downfall(0.8F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(0.7F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(spawnSettings.build()).generationSettings(builder.build()).build();
    }

    @Overwrite
    public static Biome createMountains(float depth, float scale, ConfiguredSurfaceBuilder<TernarySurfaceConfig> surfaceBuilder, boolean extraTrees) {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(builder);
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.LLAMA, 5, 4, 6));
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.GOAT, 10, 4, 6));
        DefaultBiomeFeatures.addBatsAndMonsters(builder);
        net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder2);
        builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_MOUNTAIN).structureFeature(ConfiguredStructureFeatures.VILLAGE_TAIGA);
        DefaultBiomeFeatures.addLandCarvers(builder2);
        DefaultBiomeFeatures.addDefaultLakes(builder2);
        DefaultBiomeFeatures.addAmethystGeodes(builder2);
        DefaultBiomeFeatures.addDungeons(builder2);
        DefaultBiomeFeatures.addMineables(builder2);
        DefaultBiomeFeatures.addDefaultOres(builder2);
        DefaultBiomeFeatures.addDefaultDisks(builder2);
        if (extraTrees) {
            DefaultBiomeFeatures.addExtraMountainTrees(builder2);
        } else {
            DefaultBiomeFeatures.addMountainTrees(builder2);
        }

        DefaultBiomeFeatures.addDefaultFlowers(builder2);
        DefaultBiomeFeatures.addDefaultGrass(builder2);
        DefaultBiomeFeatures.addDefaultMushrooms(builder2);
        DefaultBiomeFeatures.addDefaultVegetation(builder2);
        DefaultBiomeFeatures.addSprings(builder2);
        DefaultBiomeFeatures.addEmeraldOre(builder2);
        DefaultBiomeFeatures.addInfestedStone(builder2);
        DefaultBiomeFeatures.addFrozenTopLayer(builder2);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.EXTREME_HILLS).depth(depth).scale(scale).temperature(0.2F).downfall(0.3F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(0.2F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    @Overwrite
    public static Biome createNetherWastes() {
        SpawnSettings spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 20, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 2, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 50, 2, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.NETHER).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3344392).skyColor(getSkyColor(2.0F)).loopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_NETHER_WASTES)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    @Overwrite
    public static Biome createSoulSandValley() {
        SpawnSettings spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 10, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 4, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.SKELETON, 0.7D, 0.15D).spawnCost(EntityType.GHAST, 0.7D, 0.15D).spawnCost(EntityType.ENDERMAN, 0.7D, 0.15D).spawnCost(EntityType.STRIDER, 0.7D, 0.15D).build();
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.SOUL_SAND_VALLEY).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.NETHER_FOSSIL).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA).feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ConfiguredFeatures.BASALT_PILLAR).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_CRIMSON_ROOTS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_SOUL_SAND);
        DefaultBiomeFeatures.addNetherMineables(builder);
        if (SpeedrunnerMod.CONFIG.netherBiomeParticles) {
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1787717).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.ASH, 0.00625F)).loopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_SOUL_SAND_VALLEY)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        } else {
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1787717).skyColor(getSkyColor(2.0F)).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_SOUL_SAND_VALLEY)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }
    }

    @Overwrite
    public static Biome createBasaltDeltas() {
        SpawnSettings spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 40, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.BASALT_DELTAS).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).structureFeature(ConfiguredStructureFeatures.FORTRESS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.DELTA).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA_DOUBLE).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.SMALL_BASALT_COLUMNS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.LARGE_BASALT_COLUMNS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BASALT_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BLACKSTONE_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_DELTA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED_DOUBLE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_GOLD_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_QUARTZ_DELTAS);
        DefaultBiomeFeatures.addAncientDebris(builder);
        if (SpeedrunnerMod.CONFIG.netherBiomeParticles) {
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(4341314).fogColor(6840176).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.118093334F)).loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_BASALT_DELTAS)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        } else {
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(4341314).fogColor(6840176).skyColor(getSkyColor(2.0F)).loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_BASALT_DELTAS)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }
    }

    @Overwrite
    public static Biome createCrimsonForest() {
        SpawnSettings spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 6, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 9, 2, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.CRIMSON_FOREST).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.WEEPING_VINES).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.CRIMSON_FUNGI).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.CRIMSON_FOREST_VEGETATION);
        DefaultBiomeFeatures.addNetherMineables(builder);
        if (SpeedrunnerMod.CONFIG.netherBiomeParticles) {
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3343107).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.025F)).loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        } else {
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3343107).skyColor(getSkyColor(2.0F)).loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }
    }

    @Overwrite
    public static Biome createWarpedForest() {
        SpawnSettings spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 5, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.ENDERMAN, 1.0D, 0.12D).build();
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.WARPED_FOREST).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.WARPED_FUNGI).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.WARPED_FOREST_VEGETATION).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.NETHER_SPROUTS).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.TWISTING_VINES);
        DefaultBiomeFeatures.addNetherMineables(builder);
        if (SpeedrunnerMod.CONFIG.netherBiomeParticles) {
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1705242).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WARPED_SPORE, 0.01428F)).loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        } else {
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1705242).skyColor(getSkyColor(2.0F)).loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }
    }

    private static int getSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }
}