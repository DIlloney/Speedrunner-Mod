package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.mob.SilverfishEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SilverfishEntity.CallForHelpGoal.class)
public class SilverfishEntityCallForHelpGoalMixin {
    @Shadow
    int delay;

    @Redirect(method = "onHurt", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/SilverfishEntity$CallForHelpGoal;delay:I", ordinal = 0))
    private int onHurt(SilverfishEntity.CallForHelpGoal callForHelpGoal) {
        return this.delay = SpeedrunnerMod.options().main.doomMode ? 20 : 100;
    }
}