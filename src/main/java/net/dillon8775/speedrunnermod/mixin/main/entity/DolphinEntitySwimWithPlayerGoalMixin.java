<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.passive.DolphinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(DolphinEntity.SwimWithPlayerGoal.class)
public class DolphinEntitySwimWithPlayerGoalMixin {

    /**
     * Increases the range that a dolphin can detect a player and give them the {@code dolphins grade} status effect.
     */
    @ModifyArg(method = "start", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;I)V"), index = 1)
    private int start(int x) {
        return SpeedrunnerMod.getDolphinRange();
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.passive.DolphinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(DolphinEntity.SwimWithPlayerGoal.class)
public class DolphinEntitySwimWithPlayerGoalMixin {

    /**
     * Increases the range that a dolphin can detect a player and give them the {@code dolphins grade} status effect.
     */
    @ModifyArg(method = "start", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;I)V"), index = 1)
    private int start(int x) {
        return SpeedrunnerMod.getDolphinRange();
    }
>>>>>>> Stashed changes
}