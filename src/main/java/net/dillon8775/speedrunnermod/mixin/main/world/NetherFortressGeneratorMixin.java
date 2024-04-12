package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.world.ModFeatures;
import net.minecraft.structure.NetherFortressGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Makes nether fortress smaller and easier to navigate, see {@link ModFeatures}.
 */
@Mixin(NetherFortressGenerator.class)
public class NetherFortressGeneratorMixin {
    @Shadow
    private static final NetherFortressGenerator.PieceData[] ALL_BRIDGE_PIECES = ModFeatures.NETHER_FORTRESS_GENERATION_BRIDGE;
    @Shadow
    private static final NetherFortressGenerator.PieceData[] ALL_CORRIDOR_PIECES = ModFeatures.NETHER_FORTRESS_GENERATION_CORRIDOR;
}