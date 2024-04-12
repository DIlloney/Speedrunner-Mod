<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.world.ModFeatures;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Makes strongholds smaller, and easier to navigate.
 * <p>Applies the opposite effect if {@code Doom Mode} is on.</p>
 */
@Mixin(StrongholdGenerator.class)
public class StrongholdGeneratorMixin {
    @Shadow
    private static final StrongholdGenerator.PieceData[] ALL_PIECES = ModFeatures.STRONGHOLD_GENERATION;
=======
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.world.ModFeatures;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Makes strongholds smaller, and easier to navigate.
 * <p>Applies the opposite effect if {@code Doom Mode} is on.</p>
 */
@Mixin(StrongholdGenerator.class)
public class StrongholdGeneratorMixin {
    @Shadow
    private static final StrongholdGenerator.PieceData[] ALL_PIECES = ModFeatures.STRONGHOLD_GENERATION;
>>>>>>> Stashed changes
}