package com.dilloney.speedrunnermod.mixins.entity.mob;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(SilverfishEntity.class)
public class SilverfishEntityMixin {

    /**
     * Changes the Silverfishes health.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createSilverfishAttributes() {
        if (OPTIONS.doomMode) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.5D);
        } else {
            return OPTIONS.getModDifficulty() <= 2 ? HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.01D) : HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D);
        }
    }

    @Mixin(SilverfishEntity.CallForHelpGoal.class)
    public static class CallForHelpGoalMixin {

        @Shadow int delay;

        /**
         * Changes how fast a silverfish can spawn in reinforcements.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void onHurt() {
            if (this.delay == 0) {
                if (OPTIONS.getModDifficulty() <= 2) {
                    this.delay = 100;
                } else {
                    this.delay = 20;
                }
            }
        }
    }
}