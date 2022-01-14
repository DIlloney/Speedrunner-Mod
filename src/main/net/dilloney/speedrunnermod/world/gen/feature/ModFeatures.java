package net.dilloney.speedrunnermod.world.gen.feature;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.structure.NetherFortressGenerator;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;

import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.addBatsAndMonsters;

public class ModFeatures {
    public static final Pool<SpawnSettings.SpawnEntry> NETHER_FORTRESS_MOB_SPAWNS;
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_BRIDGE;
    public static final NetherFortressGenerator.PieceData[] NETHER_FORTRESS_GENERATION_CORRIDOR;
    public static final StrongholdGenerator.PieceData[] STRONGHOLD_GENERATION;

    public static void modifyMonsterSpawns(net.minecraft.world.biome.SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, boolean drowned) {
        if (SpeedrunnerMod.options().main.doomMode) {
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

    static {
        NETHER_FORTRESS_MOB_SPAWNS = SpeedrunnerMod.options().main.doomMode ? Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 50, 1, 4), new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 75, 4, 12), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 20, 1, 4)) : Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4), new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 2), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 2), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 1));
        NETHER_FORTRESS_GENERATION_BRIDGE = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 10, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 50, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 10, 1)};
        NETHER_FORTRESS_GENERATION_CORRIDOR = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 25, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 25, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 2, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 20, 2)};
        STRONGHOLD_GENERATION = SpeedrunnerMod.options().main.doomMode ? new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 75, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, 4) {
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
    }
}