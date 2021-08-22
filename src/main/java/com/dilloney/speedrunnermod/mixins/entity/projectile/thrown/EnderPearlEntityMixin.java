package com.dilloney.speedrunnermod.mixins.entity.projectile.thrown;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(EnderPearlEntity.class)
public abstract class EnderPearlEntityMixin extends ThrownItemEntity {

    protected EnderPearlEntityMixin(EntityType<? extends EnderPearlEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Makes the Ender Pearl entity work correctly when Infinity Pearl Mode is enabled.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        boolean ifb = EnchantmentHelper.getLevel(Enchantments.INFINITY, super.getItem()) > 0;

        for(int i = 0; i < 32; ++i) {
            this.world.addParticle(ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * 2.0D, this.getZ(), this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
        }

        if (!this.world.isClient && !this.isRemoved()) {
            Entity entity = this.getOwner();
            if (entity instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
                if (serverPlayerEntity.networkHandler.getConnection().isOpen() && serverPlayerEntity.world == this.world && !serverPlayerEntity.isSleeping()) {
                    if (!ifb && this.random.nextFloat() < 0.05F && this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
                        EndermiteEntity endermiteEntity = (EndermiteEntity)EntityType.ENDERMITE.create(this.world);
                        endermiteEntity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch());
                        this.world.spawnEntity(endermiteEntity);
                    }

                    if (entity.hasVehicle()) {
                        serverPlayerEntity.requestTeleportAndDismount(this.getX(), this.getY(), this.getZ());
                    } else {
                        entity.requestTeleport(this.getX(), this.getY(), this.getZ());
                    }

                    entity.fallDistance = 0.0F;
                    if (!ifb) {
                        if (OPTIONS.doomMode) {
                            if (!serverPlayerEntity.isCreative() || !serverPlayerEntity.isSpectator()) {
                                ((ServerPlayerEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
                            }
                            entity.damage(DamageSource.FALL, 5.0F);
                        } else if (OPTIONS.getModDifficulty() == 1) {
                            entity.damage(DamageSource.FALL, 2.0F);
                        } else if (OPTIONS.getModDifficulty() == 2) {
                            entity.damage(DamageSource.FALL, 3.0F);
                        } else if (OPTIONS.getModDifficulty() >= 3) {
                            entity.damage(DamageSource.FALL, 5.0F);
                        }
                    }
                }
            } else if (entity != null) {
                entity.requestTeleport(this.getX(), this.getY(), this.getZ());
                entity.fallDistance = 0.0F;
            }

            this.discard();
        }
    }
}