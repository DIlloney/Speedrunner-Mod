package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.passive.DolphinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(DolphinEntity.SwimWithPlayerGoal.class)
public class DolphinEntitySwimWithPlayerGoalMixin {

    @ModifyArg(method = "start", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;I)V"), index = 1)
    private int start(int x) {
        return 200;
    }
}