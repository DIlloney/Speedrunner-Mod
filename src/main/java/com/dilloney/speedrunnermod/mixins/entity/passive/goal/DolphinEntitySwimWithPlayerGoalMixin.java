package com.dilloney.speedrunnermod.mixins.entity.passive.goal;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(DolphinEntity.SwimWithPlayerGoal.class)
public class DolphinEntitySwimWithPlayerGoalMixin {

    @Shadow @Final DolphinEntity dolphin;
    @Shadow PlayerEntity closestPlayer;

    @Overwrite
    public void start() {
        if (OPTIONS.getModDifficulty() == 1) {
            this.closestPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200), this.dolphin);
        } else if (OPTIONS.getModDifficulty() >= 2) {
            this.closestPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 100), this.dolphin);
        } else {
            this.closestPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200), this.dolphin);
        }
    }
}