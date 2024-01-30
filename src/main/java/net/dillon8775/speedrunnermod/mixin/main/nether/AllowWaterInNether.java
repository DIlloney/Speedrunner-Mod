package net.dillon8775.speedrunnermod.mixin.main.nether;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionType.class)
public class AllowWaterInNether {
    @Shadow @Final
    private boolean ultrawarm;

    /**
     * Allows water to be placed in the nether if the {@code "Allow Water In Nether"} option is on.
     */
    @Inject(method = "isUltrawarm", at = @At("RETURN"), cancellable = true)
    private void allowWaterInNether(CallbackInfoReturnable<Boolean> cir) {
        if (SpeedrunnerMod.options().advanced.allowWaterInNether && this.ultrawarm) {
            cir.setReturnValue(false);
        }
    }
}