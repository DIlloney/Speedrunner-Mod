<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.world;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.structure.NetherFortressGenerator;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.*;
import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.addBatsAndMonsters;

public class ModFeatures {
    public static final Pool<SpawnSettings.SpawnEntry> NETHER_FORTRESS_MOB_SPAWNS;
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_BRIDGE;
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_CORRIDOR;
    public static final StrongholdGenerator.PieceData[] STRONGHOLD_GENERATION;

    public static void modifyMonsterSpawns(SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, boolean drowned) {
        if (DOOM_MODE) {
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(drowned ? EntityType.DROWNED : EntityType.ZOMBIE, zombieWeight, 1, 4));
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
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(drowned ? EntityType.DROWNED : EntityType.ZOMBIE, zombieWeight, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, skeletonWeight, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 2, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 50, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
        }
    }

    public static void makeAnimalsMoreCommon(SpawnSettings.Builder builder) {
        switch (options().main.mobSpawningRate) {
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

    public static void makeDolphinsMoreCommon(SpawnSettings.Builder builder, int squidWeight, int squidMinGroupSize) {
        switch (options().main.mobSpawningRate) {
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

    public static void modifyEndMonsterSpawning(SpawnSettings.Builder builder) {
        if (DOOM_MODE) {
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

    static {
        if (options().advanced.modifiedStrongholdGeneration) {
            STRONGHOLD_GENERATION = DOOM_MODE ? new StrongholdGenerator.PieceData[]{
                    new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 50, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 25, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 25, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 75, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 50, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 50, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 50, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, options().main.strongholdLibraryCount * 2) {

                        @Override
                        public boolean canGenerate(int chainLength) {
                            return super.canGenerate(chainLength) && chainLength > 3;
                        }
                    }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 50, 1) {

                        @Override
                        public boolean canGenerate(int chainLength) {
                            return super.canGenerate(chainLength) && chainLength > 5;
                        }
            }} /* -> NOT Doom Mode, or Normal Generation */ :
                    new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 20, 2),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 1),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 10, 2),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 10, 2),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 10, 1),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 10, 1),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 10, 2),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 2),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 200, options().main.strongholdPortalRoomCount) {

                                @Override
                                public boolean canGenerate(int chainLength) {
                                    return super.canGenerate(chainLength);
                                }
                            }, new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 200, options().main.strongholdLibraryCount) {

                        @Override
                        public boolean canGenerate(int chainLength) {
                            return super.canGenerate(chainLength);
                        }
                    }};
        } else {
            STRONGHOLD_GENERATION = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 40, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 20, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 20, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 10, 6), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 5, 4), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 5, 4), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 10, 2){

                @Override
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 4;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 20, 1) {

                @Override
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 5;
                }
            }};
        }

        if (options().advanced.modifiedNetherFortressGeneration) {
            NETHER_FORTRESS_GENERATION_BRIDGE = new NetherFortressGenerator.PieceData[]{
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 10, 1),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 1),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 50, 3),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 10, 1)};

            NETHER_FORTRESS_GENERATION_CORRIDOR = new NetherFortressGenerator.PieceData[]{
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 25, 3),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 25, 3),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 2, true),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 20, 2)};
        } else {
            NETHER_FORTRESS_GENERATION_BRIDGE = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 30, 0, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 4), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 4), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 5, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 5, 1)};
            NETHER_FORTRESS_GENERATION_CORRIDOR = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 25, 0, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 15, 5), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 5, 10), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 5, 10), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 3, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 5, 2)};
        }

        NETHER_FORTRESS_MOB_SPAWNS = DOOM_MODE ?
                Pool.of(
                        new SpawnSettings.SpawnEntry(EntityType.BLAZE, 50, 1, 4),
                        new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 2, 4),
                        new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1),
                        new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 75, 4, 12),
                        new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5),
                        new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 20, 1, 4)) :
                Pool.of(
                        new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4),
                        new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4),
                        new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2),
                        new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 2),
                        new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 2),
                        new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 1));
    }

    public static void init() {
        if (DOOM_MODE) {
            if (options().main.strongholdLibraryCount > 5) {
                options().main.strongholdLibraryCount = 5;
                warn("Doom mode is on, and detected too high stronghold library count. Setting to 5. May require a restart to take full effect.");
            }
        }
        info("Initialized custom mod features.");
    }
=======
package net.dillon8775.speedrunnermod.world;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.structure.NetherFortressGenerator;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.*;
import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.addBatsAndMonsters;

public class ModFeatures {
    public static final Pool<SpawnSettings.SpawnEntry> NETHER_FORTRESS_MOB_SPAWNS;
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_BRIDGE;
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_CORRIDOR;
    public static final StrongholdGenerator.PieceData[] STRONGHOLD_GENERATION;

    public static void modifyMonsterSpawns(SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, boolean drowned) {
        if (DOOM_MODE) {
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(drowned ? EntityType.DROWNED : EntityType.ZOMBIE, zombieWeight, 1, 4));
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
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(drowned ? EntityType.DROWNED : EntityType.ZOMBIE, zombieWeight, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, skeletonWeight, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 2, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 1, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 50, 4, 4));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
        }
    }

    public static void makeAnimalsMoreCommon(SpawnSettings.Builder builder) {
        switch (options().main.mobSpawningRate) {
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

    public static void makeDolphinsMoreCommon(SpawnSettings.Builder builder, int squidWeight, int squidMinGroupSize) {
        switch (options().main.mobSpawningRate) {
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

    public static void modifyEndMonsterSpawning(SpawnSettings.Builder builder) {
        if (DOOM_MODE) {
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

    static {
        if (options().advanced.modifiedStrongholdGeneration) {
            STRONGHOLD_GENERATION = DOOM_MODE ? new StrongholdGenerator.PieceData[]{
                    new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 50, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 25, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 25, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 75, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 50, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 50, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 50, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 5),
                    new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, options().main.strongholdLibraryCount * 2) {

                        @Override
                        public boolean canGenerate(int chainLength) {
                            return super.canGenerate(chainLength) && chainLength > 3;
                        }
                    }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 50, 1) {

                        @Override
                        public boolean canGenerate(int chainLength) {
                            return super.canGenerate(chainLength) && chainLength > 5;
                        }
            }} /* -> NOT Doom Mode, or Normal Generation */ :
                    new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 20, 2),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 1),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 10, 2),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 10, 2),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 10, 1),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 10, 1),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 10, 2),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 2),
                            new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 200, options().main.strongholdPortalRoomCount) {

                                @Override
                                public boolean canGenerate(int chainLength) {
                                    return super.canGenerate(chainLength);
                                }
                            }, new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 200, options().main.strongholdLibraryCount) {

                        @Override
                        public boolean canGenerate(int chainLength) {
                            return super.canGenerate(chainLength);
                        }
                    }};
        } else {
            STRONGHOLD_GENERATION = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 40, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 20, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 20, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 10, 6), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 5, 4), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 5, 4), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 10, 2){

                @Override
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 4;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 20, 1) {

                @Override
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 5;
                }
            }};
        }

        if (options().advanced.modifiedNetherFortressGeneration) {
            NETHER_FORTRESS_GENERATION_BRIDGE = new NetherFortressGenerator.PieceData[]{
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 10, 1),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 1),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 50, 3),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 10, 1)};

            NETHER_FORTRESS_GENERATION_CORRIDOR = new NetherFortressGenerator.PieceData[]{
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 25, 3),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 25, 3),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 2, true),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 20, 2)};
        } else {
            NETHER_FORTRESS_GENERATION_BRIDGE = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 30, 0, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 4), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 4), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 5, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 5, 1)};
            NETHER_FORTRESS_GENERATION_CORRIDOR = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 25, 0, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 15, 5), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 5, 10), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 5, 10), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 3, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 5, 2)};
        }

        NETHER_FORTRESS_MOB_SPAWNS = DOOM_MODE ?
                Pool.of(
                        new SpawnSettings.SpawnEntry(EntityType.BLAZE, 50, 1, 4),
                        new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 2, 4),
                        new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1),
                        new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 75, 4, 12),
                        new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5),
                        new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 20, 1, 4)) :
                Pool.of(
                        new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4),
                        new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4),
                        new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2),
                        new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 2),
                        new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 2),
                        new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 1));
    }

    public static void init() {
        if (DOOM_MODE) {
            if (options().main.strongholdLibraryCount > 5) {
                options().main.strongholdLibraryCount = 5;
                warn("Doom mode is on, and detected too high stronghold library count. Setting to 5. May require a restart to take full effect.");
            }
        }
        info("Initialized custom mod features.");
    }
>>>>>>> Stashed changes
}