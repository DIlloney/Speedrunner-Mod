package com.dilloney.speedrunnermod.mixins.world;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StrongholdGenerator.class)
public class StrongholdGeneratorMixin {

    @Shadow @Final static StrongholdGenerator.PieceData[] ALL_PIECES;

    static {
        ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 10, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 5, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 5, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 50, 2) {
            public boolean canGenerate(int chainLength) {
                return super.canGenerate(chainLength) && chainLength > 4;
            }
        }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 250, 3) {
            public boolean canGenerate(int chainLength) {
                return super.canGenerate(chainLength) && chainLength > 2;
            }
        }};
        System.out.print("[Speedrunner Mod] [main/INFO]: Ignore these error too, everything will work properly.");
    }
}