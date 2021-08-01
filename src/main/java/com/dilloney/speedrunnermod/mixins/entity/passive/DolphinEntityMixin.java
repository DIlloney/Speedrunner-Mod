package com.dilloney.speedrunnermod.mixins.entity.passive;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.DolphinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DolphinEntity.class)
public class DolphinEntityMixin {

    @Shadow final static TargetPredicate CLOSE_PLAYER_PREDICATE;

    static {
        if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(20.0D).ignoreVisibility();
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
            CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(15.0D).ignoreVisibility();
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
            CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0D).ignoreVisibility();
        } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
            CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(5.0D).ignoreVisibility();
        } else {
            CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0D).ignoreVisibility();
        }
    }
}