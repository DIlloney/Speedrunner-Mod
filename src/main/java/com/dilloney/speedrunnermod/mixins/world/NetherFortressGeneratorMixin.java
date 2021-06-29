package com.dilloney.speedrunnermod.mixins.world;

import net.minecraft.structure.NetherFortressGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetherFortressGenerator.class)
public class NetherFortressGeneratorMixin {

    @Shadow @Final static NetherFortressGenerator.PieceData[] ALL_BRIDGE_PIECES;
    @Shadow @Final static NetherFortressGenerator.PieceData[] ALL_CORRIDOR_PIECES;

    static {
        ALL_BRIDGE_PIECES = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 10, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 50, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 5, 1)};
        ALL_CORRIDOR_PIECES = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 10, 2, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 10, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 3, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 3, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 2, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 3, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 5, 2)};
        System.out.print("[Speedrunner Mod] [main/INFO]: Ignore these error too, everything will work properly.");
    }
}