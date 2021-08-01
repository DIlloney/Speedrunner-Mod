package com.dilloney.speedrunnermod.mixins.world.structure;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StrongholdGenerator.class)
public class StrongholdGeneratorMixin {

    @Shadow final static StrongholdGenerator.PieceData[] ALL_PIECES;

    static {
        if (SpeedrunnerMod.CONFIG.doomMode) {
            ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 20, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 20, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 20, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 20, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 20, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 20, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 20, 3), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, 6) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 4;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 20, 1) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 5;
                }
            }};
        } else if (SpeedrunnerMod.CONFIG.difficulty == 1 && SpeedrunnerMod.CONFIG.modifiedWorldGeneration || SpeedrunnerMod.CONFIG.difficulty == 2 && SpeedrunnerMod.CONFIG.modifiedWorldGeneration) {
            ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 5, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 5, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 4;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 200, 3) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 2;
                }
            }};
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3 && SpeedrunnerMod.CONFIG.modifiedWorldGeneration || SpeedrunnerMod.CONFIG.difficulty == 4 && SpeedrunnerMod.CONFIG.modifiedWorldGeneration) {
            ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 25, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 5, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 5, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 5, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 5, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 50, 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 4;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 50, 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 3;
                }
            }};
        } else {
            ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 40, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 20, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 20, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 10, 6), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 5, 4), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 5, 4), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 10, 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 4;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 20, 1) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 5;
                }
            }};
        }
    }
}