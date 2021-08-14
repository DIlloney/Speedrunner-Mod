package com.dilloney.speedrunnermod.mixins.entity.passive;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.DolphinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(DolphinEntity.class)
public class DolphinEntityMixin {

    @Shadow final static TargetPredicate CLOSE_PLAYER_PREDICATE;

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
}