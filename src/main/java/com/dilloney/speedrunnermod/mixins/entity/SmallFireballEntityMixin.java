package com.dilloney.speedrunnermod.mixins.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SmallFireballEntity.class)
public class SmallFireballEntityMixin extends AbstractFireballEntity {

    public SmallFireballEntityMixin(EntityType<? extends AbstractFireballEntity> entityType, World world) {
        super(entityType, world);
    }

    @Overwrite
    public void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.world.isClient) {
            Entity entity = entityHitResult.getEntity();
            if (!entity.isFireImmune()) {
                Entity entity2 = this.getOwner();
                int i = entity.getFireTicks();
                entity.setOnFireFor(3);
                boolean bl = entity.damage(DamageSource.fireball(this, entity2), 3.0F);
                if (!bl) {
                    entity.setFireTicks(i);
                } else if (entity2 instanceof LivingEntity) {
                    this.applyDamageEffects((LivingEntity)entity2, entity);
                }
            }
        }
    }
}
