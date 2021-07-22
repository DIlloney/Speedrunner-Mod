package com.dilloney.speedrunnermod.mixins.entity;

import com.google.common.base.MoreObjects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ShulkerBulletEntity.class)
public abstract class ShulkerBulletEntityMixin extends ProjectileEntity {

    public ShulkerBulletEntityMixin(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Overwrite
    public void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        Entity entity2 = this.getOwner();
        LivingEntity livingEntity = entity2 instanceof LivingEntity ? (LivingEntity)entity2 : null;
        boolean bl = entity.damage(DamageSource.mobProjectile(this, livingEntity).setProjectile(), 4.0F);
        if (bl) {
            this.applyDamageEffects(livingEntity, entity);
            if (entity instanceof LivingEntity) {
                ((LivingEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200), (Entity) MoreObjects.firstNonNull(entity2, this));
                ((LivingEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20), (Entity) MoreObjects.firstNonNull(entity2, this));
            }
        }
    }
}