package com.dilloney.speedrunnermod.mixins.entity.mob.goal;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlazeEntity.ShootFireballGoal.class)
public abstract class BlazeEntityShootFireballGoalMixin extends Goal {

    @Shadow @Final BlazeEntity blaze;
    @Shadow int fireballsFired;
    @Shadow int fireballCooldown;
    @Shadow int targetNotVisibleTicks;
    @Shadow abstract double getFollowRange();

    @Overwrite
    public void tick() {
        --this.fireballCooldown;
        LivingEntity livingEntity = this.blaze.getTarget();
        if (livingEntity != null) {
            boolean bl = this.blaze.getVisibilityCache().canSee(livingEntity);
            if (bl) {
                this.targetNotVisibleTicks = 0;
            } else {
                ++this.targetNotVisibleTicks;
            }

            double d = this.blaze.squaredDistanceTo(livingEntity);
            if (d < 4.0D) {
                if (!bl) {
                    return;
                }

                if (this.fireballCooldown <= 0) {
                    this.fireballCooldown = 20;
                    this.blaze.tryAttack(livingEntity);
                }

                this.blaze.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0D);
            } else if (d < this.getFollowRange() * this.getFollowRange() && bl) {
                double e = livingEntity.getX() - this.blaze.getX();
                double f = livingEntity.getBodyY(0.5D) - this.blaze.getBodyY(0.5D);
                double g = livingEntity.getZ() - this.blaze.getZ();
                if (this.fireballCooldown <= 0) {
                    ++this.fireballsFired;
                    if (this.fireballsFired == 1) {
                        this.fireballCooldown = 60;
                        this.blaze.setFireActive(true);
                    } else if (this.fireballsFired <= 4) {
                        this.fireballCooldown = 6;
                    } else {
                        if (SpeedrunnerMod.CONFIG.doomMode) {
                            this.fireballCooldown = 60;
                        } else {
                            this.fireballCooldown = 100;
                        }
                        this.fireballsFired = 0;
                        this.blaze.setFireActive(false);
                    }

                    if (this.fireballsFired > 1) {
                        double h = Math.sqrt(Math.sqrt(d)) * 0.5D;
                        if (!this.blaze.isSilent()) {
                            this.blaze.world.syncWorldEvent((PlayerEntity)null, 1018, this.blaze.getBlockPos(), 0);
                        }

                        for(int i = 0; i < 1; ++i) {
                            SmallFireballEntity smallFireballEntity = new SmallFireballEntity(this.blaze.world, this.blaze, e + this.blaze.getRandom().nextGaussian() * h, f, g + this.blaze.getRandom().nextGaussian() * h);
                            smallFireballEntity.setPosition(smallFireballEntity.getX(), this.blaze.getBodyY(0.5D) + 0.5D, smallFireballEntity.getZ());
                            this.blaze.world.spawnEntity(smallFireballEntity);
                        }
                    }
                }

                this.blaze.getLookControl().lookAt(livingEntity, 10.0F, 10.0F);
            } else if (this.targetNotVisibleTicks < 5) {
                this.blaze.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0D);
            }

            super.tick();
        }
    }
}