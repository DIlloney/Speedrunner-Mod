package net.dilloney.speedrunnermod.util;

import com.google.common.collect.ImmutableList;
import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.tag.ModBlockTags;
import net.dilloney.speedrunnermod.world.gen.feature.ModConfiguredFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.structure.NetherFortressGenerator;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.addBatsAndMonsters;

public class ModFeatures {
    public static final int[] DRY_BIOME_IDS = SpeedrunnerMod.options().main.makeBiomesMoreCommon ? new int[]{2, 2, 2, 35, 35, 35, 36, 1, 1, 2, 35, 35, 1, 1, 2, 35, 2, 1, 35, 35, 2} : new int[]{2, 2, 2, 35, 35, 1};
    public static final int[] TEMPERATE_BIOME_IDS = SpeedrunnerMod.options().main.makeBiomesMoreCommon ? new int[]{4, 29, 2, 2, 2, 1, 1, 35, 35, 2, 35, 1} : new int[]{4, 29, 3, 1, 27, 6};
    public static final int[] COOL_BIOME_IDS = SpeedrunnerMod.options().main.makeBiomesMoreCommon ? new int[]{4, 5, 6, 1, 2, 2, 35} : new int[]{4, 3, 5, 1};
    public static final int[] SNOWY_BIOME_IDS = SpeedrunnerMod.options().main.makeBiomesMoreCommon ? new int[]{12, 30, 3, 2, 1, 35, 2} : new int[]{12, 12, 12, 30};
    public static final List<SpawnSettings.SpawnEntry> NETHER_FORTRESS_MOB_SPAWNS = SpeedrunnerMod.options().main.doomMode ? ImmutableList.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 50, 1, 4), new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 75, 4, 12), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 20, 1, 4)) : ImmutableList.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4), new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 2), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 2), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 1));
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_BRIDGE = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 10, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 50, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 10, 1)};
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_CORRIDOR = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 25, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 25, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 2, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 20, 2)};
    public static final StrongholdGenerator.PieceData[] STRONGHOLD_GENERATION = SpeedrunnerMod.options().main.doomMode ? new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 75, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, 4) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength) && chainLength > 3;
        }
    }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 20, 1) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength) && chainLength > 5;
        }
    }} : new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 20, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 10, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 10, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 2) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength) && chainLength > 4;
        }
    }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 200, 3) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength) && chainLength > 2;
        }
    }, new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 200, 2) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength);
        }
    }};

    public static void applyBlockHardnessValues(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (SpeedrunnerMod.options().main.modifiedBlockHardness) {
            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_HARDNESS)) {
                cir.setReturnValue(0.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_ONE_HARDNESS)) {
                cir.setReturnValue(0.1F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_TWO_HARDNESS)) {
                cir.setReturnValue(0.2F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_THREESEVEN_HARDNESS)) {
                cir.setReturnValue(0.37F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_FOUR_HARDNESS)) {
                cir.setReturnValue(0.4F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_FIVE_HARDNESS)) {
                cir.setReturnValue(0.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SIX_HARDNESS)) {
                cir.setReturnValue(0.6F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SIXFIVE_HARDNESS)) {
                cir.setReturnValue(0.65F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SEVEN_HARDNESS)) {
                cir.setReturnValue(0.7F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_EIGHT_HARDNESS)) {
                cir.setReturnValue(0.8F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_ZERO_HARDNESS)) {
                cir.setReturnValue(1.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_THREE_HARDNESS)) {
                cir.setReturnValue(1.3F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_FOUR_HARDNESS)) {
                cir.setReturnValue(1.4F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_FIVE_HARDNESS)) {
                cir.setReturnValue(1.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_SIX_HARDNESS)) {
                cir.setReturnValue(1.6F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TWO_ZERO_HARDNESS)) {
                cir.setReturnValue(2.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TWO_FIVE_HARDNESS)) {
                cir.setReturnValue(2.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.THREE_ZERO_HARDNESS)) {
                cir.setReturnValue(3.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TEN_HARDNESS)) {
                cir.setReturnValue(10.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TWENTY_FIVE_HARDNESS)) {
                cir.setReturnValue(25.0F);
            }
        }
    }

    public static Biome modifyPlains(boolean bl) {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addPlainsMobs(builder);
        if (!bl) {
            builder.playerSpawnFriendly();
        }

        net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        builder2.structureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS).structureFeature(ConfiguredStructureFeatures.PILLAGER_OUTPOST);

        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder2);
        builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
        DefaultBiomeFeatures.addLandCarvers(builder2);
        DefaultBiomeFeatures.addDefaultLakes(builder2);
        DefaultBiomeFeatures.addDungeons(builder2);
        DefaultBiomeFeatures.addPlainsTallGrass(builder2);
        if (bl) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_SUNFLOWER);
        }

        DefaultBiomeFeatures.addMineables(builder2);
        DefaultBiomeFeatures.addDefaultOres(builder2);
        DefaultBiomeFeatures.addDefaultDisks(builder2);
        DefaultBiomeFeatures.addPlainsFeatures(builder2);
        if (bl) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_SUGAR_CANE);
        }

        DefaultBiomeFeatures.addDefaultMushrooms(builder2);
        if (bl) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_PUMPKIN);
        } else {
            DefaultBiomeFeatures.addDefaultVegetation(builder2);
        }

        DefaultBiomeFeatures.addSprings(builder2);
        DefaultBiomeFeatures.addFrozenTopLayer(builder2);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(DefaultBiomeCreator.getSkyColor(0.8F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    public static Biome modifyForest(float depth, float scale, boolean flower, SpawnSettings.Builder spawnSettings) {
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder);
        builder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL).structureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS);
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
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
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.MUSHROOM).depth(depth).scale(scale).temperature(0.7F).downfall(0.8F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(DefaultBiomeCreator.getSkyColor(0.7F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(spawnSettings.build()).generationSettings(builder.build()).build();
    }

    public static Biome modifyMountains(float depth, float scale, ConfiguredSurfaceBuilder<TernarySurfaceConfig> surfaceBuilder, boolean extraTrees) {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(builder);
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.LLAMA, 5, 4, 6));
        addBatsAndMonsters(builder);
        net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder2);
        builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_MOUNTAIN).structureFeature(ConfiguredStructureFeatures.VILLAGE_TAIGA);
        DefaultBiomeFeatures.addLandCarvers(builder2);
        DefaultBiomeFeatures.addDefaultLakes(builder2);
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
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.EXTREME_HILLS).depth(depth).scale(scale).temperature(0.2F).downfall(0.3F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(DefaultBiomeCreator.getSkyColor(0.2F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    public static Biome modifyNetherWastes() {
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.options().main.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 20, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 50, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 100, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 20, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 100, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 20, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 2, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 50, 2, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.NETHER).structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_NETHER).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return (new Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3344392).skyColor(DefaultBiomeCreator.getSkyColor(2.0F)).loopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_NETHER_WASTES)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome modifySoulSandValley() {
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.options().main.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 4, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.SKELETON, 0.7D, 0.15D).spawnCost(EntityType.GHAST, 0.7D, 0.15D).spawnCost(EntityType.ENDERMAN, 0.7D, 0.15D).spawnCost(EntityType.STRIDER, 0.7D, 0.15D).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 10, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 4, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.SKELETON, 0.7D, 0.15D).spawnCost(EntityType.GHAST, 0.7D, 0.15D).spawnCost(EntityType.ENDERMAN, 0.7D, 0.15D).spawnCost(EntityType.STRIDER, 0.7D, 0.15D).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.SOUL_SAND_VALLEY).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.NETHER_FOSSIL).structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_NETHER).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA).feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ConfiguredFeatures.BASALT_PILLAR).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_CRIMSON_ROOTS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_SOUL_SAND);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1787717).skyColor(DefaultBiomeCreator.getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.ASH, 0.00625F)).loopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_SOUL_SAND_VALLEY)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome modifyBasaltDeltas() {
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.options().main.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 40, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 25, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 25, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.BASALT_DELTAS).structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_NETHER).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).structureFeature(ConfiguredStructureFeatures.FORTRESS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.DELTA).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA_DOUBLE).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.SMALL_BASALT_COLUMNS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.LARGE_BASALT_COLUMNS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BASALT_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BLACKSTONE_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_DELTA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED_DOUBLE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_GOLD_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_QUARTZ_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModConfiguredFeatures.ORE_SPEEDRUNNER_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModConfiguredFeatures.ORE_IGNEOUS_DELTAS);
        DefaultBiomeFeatures.addAncientDebris(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(4341314).fogColor(6840176).skyColor(DefaultBiomeCreator.getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.118093334F)).loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_BASALT_DELTAS)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome createBasaltDeltasWithModdedOres() {
        SpawnSettings spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 40, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 100, 2, 5)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.BASALT_DELTAS).structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_NETHER).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).structureFeature(ConfiguredStructureFeatures.FORTRESS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.DELTA).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA_DOUBLE).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.SMALL_BASALT_COLUMNS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.LARGE_BASALT_COLUMNS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BASALT_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BLACKSTONE_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_DELTA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED_DOUBLE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_GOLD_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_QUARTZ_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.ORE_SPEEDRUNNER_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.ORE_IGNEOUS_DELTAS);
        DefaultBiomeFeatures.addAncientDebris(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(4341314).fogColor(6840176).skyColor(DefaultBiomeCreator.getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.118093334F)).loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_BASALT_DELTAS)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome modifyCrimsonForest() {
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.options().main.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 50, 4, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 2, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 6, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 9, 2, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.CRIMSON_FOREST).structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_NETHER).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.WEEPING_VINES).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.CRIMSON_FUNGI).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.CRIMSON_FOREST_VEGETATION);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3343107).skyColor(DefaultBiomeCreator.getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.025F)).loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome modifyWarpedForest() {
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.options().main.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 4, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.ENDERMAN, 1.0D, 0.12D).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 5, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.ENDERMAN, 1.0D, 0.12D).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.WARPED_FOREST).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_NETHER).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.WARPED_FUNGI).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.WARPED_FOREST_VEGETATION).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.NETHER_SPROUTS).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.TWISTING_VINES);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1705242).skyColor(DefaultBiomeCreator.getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WARPED_SPORE, 0.01428F)).loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static void modifyMonsterSpawns(net.minecraft.world.biome.SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight) {
        if (SpeedrunnerMod.options().main.doomMode) {
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, zombieWeight, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, skeletonWeight, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 100, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 75, 1, 5));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 50, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 50, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 25, 1, 4));
        } else {
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, zombieWeight, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, skeletonWeight, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 2, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 50, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
        }
    }

    public static void makeAnimalsMoreCommon(net.minecraft.world.biome.SpawnSettings.Builder builder) {
        switch (SpeedrunnerMod.options().advanced.mobSpawningRate) {
            case LOW:
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 16, 1, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 1, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 1, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 8, 1, 4));
            case NORMAL:
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 16, 4, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 4, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 4, 4));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 8, 4, 4));
            case HIGH:
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 16, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 8, 4, 8));
            default:
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 16, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 4, 8));
                builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 8, 4, 8));
        }
    }

    public static void makeDolphinsMoreCommon(net.minecraft.world.biome.SpawnSettings.Builder builder, int squidWeight, int squidMinGroupSize) {
        switch (SpeedrunnerMod.options().advanced.mobSpawningRate) {
            case LOW:
                builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 10, 1, 4));
                builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 15, 1, 1));
            case NORMAL:
                builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 10, 4, 4));
                builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 15, 1, 1));
            case HIGH:
                builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 10, 4, 8));
                builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 15, 1, 2));
            default:
                builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 10, 4, 8));
                builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 15, 1, 2));
        }
        builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, squidWeight, squidMinGroupSize, 4));
        addBatsAndMonsters(builder);
    }

    public static void modifyEndMonsterSpawning(net.minecraft.world.biome.SpawnSettings.Builder builder) {
        if (SpeedrunnerMod.options().main.doomMode) {
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 85, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 75, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 75, 1, 2));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.RAVAGER, 50, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.EVOKER, 50, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 25, 1, 1));
        } else {
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 4, 4));
        }
    }

    public static boolean canPiglinBruteSpawn(EntityType<PiglinBruteEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return !world.getBlockState(pos.down()).isOf(Blocks.NETHER_WART_BLOCK);
    }
}