package com.dilloney.speedrunnermod.mixins.entity.passive.goal;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DolphinEntity.SwimWithPlayerGoal.class)
public class DolphinEntitySwimWithPlayerGoalMixin {

    @Shadow @Final DolphinEntity dolphin;
    @Shadow PlayerEntity closestPlayer;

    @Overwrite
    public void start() {
        if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            this.closestPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200), this.dolphin);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2 || SpeedrunnerMod.CONFIG.difficulty == 3 || SpeedrunnerMod.CONFIG.difficulty == 4) {
            this.closestPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 100), this.dolphin);
        } else {
            this.closestPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200), this.dolphin);
        }
    }
}