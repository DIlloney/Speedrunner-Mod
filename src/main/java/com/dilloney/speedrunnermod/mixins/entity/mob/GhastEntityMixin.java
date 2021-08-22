package com.dilloney.speedrunnermod.mixins.entity.mob;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(GhastEntity.class)
public class GhastEntityMixin {

    /**
     * Changes the Ghasts health.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createGhastAttributes() {
        if (OPTIONS.doomMode) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0D);
        } else if (OPTIONS.getModDifficulty() == 1) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 15.0D);
        } else if (OPTIONS.getModDifficulty() == 2) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 7.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 25.0D);
        } else if (OPTIONS.getModDifficulty() == 3) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0D);
        } else {
            return OPTIONS.getModDifficulty() == 4 ? MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0D) : MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0D);
        }
    }

    @Mixin(GhastEntity.ShootFireballGoal.class)
    public static class ShootFireballGoalMixin {

        @Shadow @Final GhastEntity ghast;
        @Shadow int cooldown;

        /**
         * Changes the Ghasts fireball speed.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void tick() {
            LivingEntity livingEntity = this.ghast.getTarget();
            double d = 64.0D;
            if (livingEntity.squaredDistanceTo(this.ghast) < 4096.0D && this.ghast.canSee(livingEntity)) {
                World world = this.ghast.world;
                ++this.cooldown;
                if (this.cooldown == 10 && !this.ghast.isSilent()) {
                    world.syncWorldEvent((PlayerEntity)null, 1015, this.ghast.getBlockPos(), 0);
                }

                if (this.cooldown == 20) {
                    double e = 4.0D;
                    Vec3d vec3d = this.ghast.getRotationVec(1.0F);
                    double f = livingEntity.getX() - (this.ghast.getX() + vec3d.x * 4.0D);
                    double g = livingEntity.getBodyY(0.5D) - (0.5D + this.ghast.getBodyY(0.5D));
                    double h = livingEntity.getZ() - (this.ghast.getZ() + vec3d.z * 4.0D);
                    if (!this.ghast.isSilent()) {
                        world.syncWorldEvent((PlayerEntity)null, 1016, this.ghast.getBlockPos(), 0);
                    }

                    FireballEntity fireballEntity = new FireballEntity(world, this.ghast, f, g, h, this.ghast.getFireballStrength());
                    fireballEntity.setPosition(this.ghast.getX() + vec3d.x * 4.0D, this.ghast.getBodyY(0.5D) + 0.5D, fireballEntity.getZ() + vec3d.z * 4.0D);
                    world.spawnEntity(fireballEntity);
                    if (OPTIONS.killGhastUponFireball) {
                        this.ghast.kill();
                    }
                    if (OPTIONS.doomMode) {
                        this.cooldown = -5;
                    } else {
                        this.cooldown = -40;
                    }
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }

            this.ghast.setShooting(this.cooldown > 10);
        }
    }
}