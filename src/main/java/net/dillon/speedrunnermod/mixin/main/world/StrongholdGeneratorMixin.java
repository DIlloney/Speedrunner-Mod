package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.world.ModWorldGen;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Makes strongholds smaller, and easier to navigate.
 * <p>Applies the opposite effect if {@code doom mode} is enabled.</p>
 */
@Mixin(StrongholdGenerator.class)
public class StrongholdGeneratorMixin {
    @Shadow
    private static final StrongholdGenerator.PieceData[] ALL_PIECES = ModWorldGen.STRONGHOLD_GENERATION;
}