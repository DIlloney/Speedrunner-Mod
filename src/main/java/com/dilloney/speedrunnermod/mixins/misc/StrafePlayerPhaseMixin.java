package com.dilloney.speedrunnermod.mixins.misc;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.AbstractPhase;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.boss.dragon.phase.StrafePlayerPhase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StrafePlayerPhase.class)
public abstract class StrafePlayerPhaseMixin extends AbstractPhase {

    public StrafePlayerPhaseMixin(EnderDragonEntity dragon) {
        super(dragon);
    }

    @Shadow @Final static Logger LOGGER;

    @Shadow int seenTargetTimes;

    @Shadow Path path;

    @Shadow Vec3d pathTarget;

    @Shadow LivingEntity target;

    @Shadow abstract void updatePath();

    @Overwrite
    public void serverTick() {
        if (this.target == null) {
            LOGGER.warn("Skipping player strafe phase because no player was found");
            this.dragon.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
        } else {
            double j;
            double k;
            double n;
            if (this.path != null && this.path.isFinished()) {
                j = this.target.getX();
                k = this.target.getZ();
                double f = j - this.dragon.getX();
                double g = k - this.dragon.getZ();
                n = Math.sqrt(f * f + g * g);
                double i = Math.min(0.4000000059604645D + n / 80.0D - 1.0D, 10.0D);
                this.pathTarget = new Vec3d(j, this.target.getY() + i, k);
            }

            j = this.pathTarget == null ? 0.0D : this.pathTarget.squaredDistanceTo(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
            if (j < 100.0D || j > 22500.0D) {
                this.updatePath();
            }

            if (SpeedrunnerMod.CONFIG.difficulty == 1 || SpeedrunnerMod.CONFIG.difficulty == 2) {
                if (this.target.squaredDistanceTo(this.dragon) < 3072.0D) {
                    if (this.dragon.canSee(this.target)) {
                        ++this.seenTargetTimes;
                        Vec3d vec3d = (new Vec3d(this.target.getX() - this.dragon.getX(), 0.0D, this.target.getZ() - this.dragon.getZ())).normalize();
                        Vec3d vec3d2 = (new Vec3d((double) MathHelper.sin(this.dragon.getYaw() * 0.017453292F), 0.0D, (double)(-MathHelper.cos(this.dragon.getYaw() * 0.017453292F)))).normalize();
                        float l = (float)vec3d2.dotProduct(vec3d);
                        float m = (float)(Math.acos((double)l) * 57.2957763671875D);
                        m += 0.5F;
                        if (this.seenTargetTimes >= 5 && m >= 0.0F && m < 10.0F) {
                            n = 1.0D;
                            Vec3d vec3d3 = this.dragon.getRotationVec(1.0F);
                            double o = this.dragon.head.getX() - vec3d3.x * 1.0D;
                            double p = this.dragon.head.getBodyY(0.5D) + 0.5D;
                            double q = this.dragon.head.getZ() - vec3d3.z * 1.0D;
                            double r = this.target.getX() - o;
                            double s = this.target.getBodyY(0.5D) - p;
                            double t = this.target.getZ() - q;
                            if (!this.dragon.isSilent()) {
                                this.dragon.world.syncWorldEvent((PlayerEntity)null, 1017, this.dragon.getBlockPos(), 0);
                            }

                            DragonFireballEntity dragonFireballEntity = new DragonFireballEntity(this.dragon.world, this.dragon, r, s, t);
                            dragonFireballEntity.refreshPositionAndAngles(o, p, q, 0.0F, 0.0F);
                            this.dragon.world.spawnEntity(dragonFireballEntity);
                            this.seenTargetTimes = 0;
                            if (this.path != null) {
                                while(!this.path.isFinished()) {
                                    this.path.next();
                                }
                            }

                            this.dragon.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
                        }
                    } else if (this.seenTargetTimes > 0) {
                        --this.seenTargetTimes;
                    }
                }
            } else if (SpeedrunnerMod.CONFIG.difficulty == 3 || SpeedrunnerMod.CONFIG.difficulty == 4) {
                if (this.target.squaredDistanceTo(this.dragon) < 4096.0D) {
                    if (this.dragon.canSee(this.target)) {
                        ++this.seenTargetTimes;
                        Vec3d vec3d = (new Vec3d(this.target.getX() - this.dragon.getX(), 0.0D, this.target.getZ() - this.dragon.getZ())).normalize();
                        Vec3d vec3d2 = (new Vec3d((double) MathHelper.sin(this.dragon.getYaw() * 0.017453292F), 0.0D, (double)(-MathHelper.cos(this.dragon.getYaw() * 0.017453292F)))).normalize();
                        float l = (float)vec3d2.dotProduct(vec3d);
                        float m = (float)(Math.acos((double)l) * 57.2957763671875D);
                        m += 0.5F;
                        if (this.seenTargetTimes >= 5 && m >= 0.0F && m < 10.0F) {
                            n = 1.0D;
                            Vec3d vec3d3 = this.dragon.getRotationVec(1.0F);
                            double o = this.dragon.head.getX() - vec3d3.x * 1.0D;
                            double p = this.dragon.head.getBodyY(0.5D) + 0.5D;
                            double q = this.dragon.head.getZ() - vec3d3.z * 1.0D;
                            double r = this.target.getX() - o;
                            double s = this.target.getBodyY(0.5D) - p;
                            double t = this.target.getZ() - q;
                            if (!this.dragon.isSilent()) {
                                this.dragon.world.syncWorldEvent((PlayerEntity)null, 1017, this.dragon.getBlockPos(), 0);
                            }

                            DragonFireballEntity dragonFireballEntity = new DragonFireballEntity(this.dragon.world, this.dragon, r, s, t);
                            dragonFireballEntity.refreshPositionAndAngles(o, p, q, 0.0F, 0.0F);
                            this.dragon.world.spawnEntity(dragonFireballEntity);
                            this.seenTargetTimes = 0;
                            if (this.path != null) {
                                while(!this.path.isFinished()) {
                                    this.path.next();
                                }
                            }

                            this.dragon.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
                        }
                    } else if (this.seenTargetTimes > 0) {
                        --this.seenTargetTimes;
                    }
                }
            } else if (this.seenTargetTimes > 0) {
                --this.seenTargetTimes;
            }

        }
    }
}
