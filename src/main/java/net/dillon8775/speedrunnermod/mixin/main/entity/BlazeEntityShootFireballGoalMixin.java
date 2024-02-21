package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.BlazeEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(BlazeEntity.ShootFireballGoal.class)
public abstract class BlazeEntityShootFireballGoalMixin extends Goal {

    /**
     * Increases the blaze's fireball cooldown, unless it's on {@code doom mode}.
     */
    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/BlazeEntity$ShootFireballGoal;fireballCooldown:I", ordinal = 4, opcode = Opcodes.PUTFIELD))
    private void changeFireballCooldown(BlazeEntity.ShootFireballGoal blaze, int value) {
        blaze.fireballCooldown = DOOM_MODE ? 60 : 120;
    }

    /**
     * Prevents blazes from melee attacking.
     */
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/BlazeEntity;tryAttack(Lnet/minecraft/entity/Entity;)Z"))
    private boolean disableMeleeAttack(BlazeEntity blaze, Entity entity) {
        return false;
    }
}