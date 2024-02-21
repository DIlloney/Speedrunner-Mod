package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.mob.SilverfishEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(SilverfishEntity.CallForHelpGoal.class)
public class SilverfishEntityCallForHelpGoalMixin {
    @Shadow
    int delay;

    @Redirect(method = "onHurt", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/SilverfishEntity$CallForHelpGoal;delay:I", ordinal = 0))
    private int onHurt(SilverfishEntity.CallForHelpGoal callForHelpGoal) {
        return this.delay = DOOM_MODE ? 20 : 100;
    }
}