<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.minecraft.structure.RuinedPortalStructurePiece;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.structure.RuinedPortalStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RuinedPortalStructure.class)
public class RuinedPortalStructureMixin {

    /**
     * Allows ruined portals to generate correctly, due to the speedrunner mod's world generation modifications.
     */
    @Redirect(method = "getFloorHeight", at = @At(value = "FIELD", target = "Lnet/minecraft/structure/RuinedPortalStructurePiece$VerticalPlacement;PARTLY_BURIED:Lnet/minecraft/structure/RuinedPortalStructurePiece$VerticalPlacement;"))
    private static RuinedPortalStructurePiece.VerticalPlacement makeDesertPortalsGenerateNormally(Random value) {
        return RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.minecraft.structure.RuinedPortalStructurePiece;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.structure.RuinedPortalStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RuinedPortalStructure.class)
public class RuinedPortalStructureMixin {

    /**
     * Allows ruined portals to generate correctly, due to the speedrunner mod's world generation modifications.
     */
    @Redirect(method = "getFloorHeight", at = @At(value = "FIELD", target = "Lnet/minecraft/structure/RuinedPortalStructurePiece$VerticalPlacement;PARTLY_BURIED:Lnet/minecraft/structure/RuinedPortalStructurePiece$VerticalPlacement;"))
    private static RuinedPortalStructurePiece.VerticalPlacement makeDesertPortalsGenerateNormally(Random value) {
        return RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
    }
>>>>>>> Stashed changes
}