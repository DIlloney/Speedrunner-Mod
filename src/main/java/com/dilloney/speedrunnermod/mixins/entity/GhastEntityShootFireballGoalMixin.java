package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GhastEntity.ShootFireballGoal.class)
public class GhastEntityShootFireballGoalMixin {

    @Shadow @Final GhastEntity ghast;
    @Shadow int cooldown;

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
                if (SpeedrunnerMod.CONFIG.killGhastUponFireball) {
                    this.ghast.kill();
                }
                if (SpeedrunnerMod.CONFIG.enableChallengeMode) {
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