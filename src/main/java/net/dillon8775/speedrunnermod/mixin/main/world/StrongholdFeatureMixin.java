package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.world.gen.feature.StrongholdFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

/**
 * Changes how stronghold's initially generate in a world.
 */
@Mixin(StrongholdFeature.class)
public abstract class StrongholdFeatureMixin {

    /**
     * Changes the minimum and maximum Y value a stronghold can generate at.
     */
    @ModifyArgs(method = "addPieces", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/StructurePiecesCollector;shiftInto(IILjava/util/Random;I)V"))
    private static void changeMinAndMaxYValue(Args args) {
        args.set(1, SpeedrunnerMod.getStrongholdMinY());
        args.set(0, SpeedrunnerMod.getStrongholdMaxY());
    }
}