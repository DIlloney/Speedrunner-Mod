package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.DolphinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DolphinEntity.class)
public class DolphinEntityMixin {
    @Shadow
    private static final TargetPredicate CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(20.0D).ignoreVisibility();
}