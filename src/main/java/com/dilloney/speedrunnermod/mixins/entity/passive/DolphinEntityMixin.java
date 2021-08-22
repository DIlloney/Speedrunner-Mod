package com.dilloney.speedrunnermod.mixins.entity.passive;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(DolphinEntity.class)
public class DolphinEntityMixin {

    @Shadow static final TargetPredicate CLOSE_PLAYER_PREDICATE;

    static {
        if (OPTIONS.getModDifficulty() == 1) {
            CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(20.0D).ignoreVisibility();
        } else if (OPTIONS.getModDifficulty() == 2) {
            CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(15.0D).ignoreVisibility();
        } else if (OPTIONS.getModDifficulty() == 3) {
            CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0D).ignoreVisibility();
        } else if (OPTIONS.getModDifficulty() == 4) {
            CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(5.0D).ignoreVisibility();
        } else {
            CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0D).ignoreVisibility();
        }
    }

    @Mixin(DolphinEntity.SwimWithPlayerGoal.class)
    public static class SwimWithPlayerGoalMixin {

        @Shadow @Final DolphinEntity dolphin;
        @Shadow PlayerEntity closestPlayer;

        /**
         * Makes the Dolphin Entity give dolphins grace for a longer period of time based on Mod Difficulty.
         * @author Dilloney
         * @reason
         */
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
}