package net.dillon8775.speedrunnermod.mixin.main.worldgen;

import net.minecraft.structure.RuinedPortalStructurePiece;
import net.minecraft.world.gen.feature.RuinedPortalFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(RuinedPortalFeature.class)
public class AllowRuinedPortalsAnywhere {

    /**
     * Allows ruined portals to generate correctly, due to the speedrunner mod's world generation modifications.
     */
    @ModifyVariable(method = "addPieces", at = @At(value = "STORE", ordinal = 0), index = 1)
    private static RuinedPortalStructurePiece.VerticalPlacement addPieces(RuinedPortalStructurePiece.VerticalPlacement verticalPlacement) {
        return RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
    }
}