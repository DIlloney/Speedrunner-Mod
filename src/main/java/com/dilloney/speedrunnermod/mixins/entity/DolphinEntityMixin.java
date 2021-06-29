package com.dilloney.speedrunnermod.mixins.entity;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.DolphinEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DolphinEntity.class)
public class DolphinEntityMixin {

    @Shadow @Final static TargetPredicate CLOSE_PLAYER_PREDICATE;

    static {
        CLOSE_PLAYER_PREDICATE = (new TargetPredicate()).setBaseMaxDistance(20.0D).includeTeammates().includeInvulnerable().includeHidden();
    }
}