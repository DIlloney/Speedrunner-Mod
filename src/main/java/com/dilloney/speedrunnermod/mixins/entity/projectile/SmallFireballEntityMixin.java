package com.dilloney.speedrunnermod.mixins.entity.projectile;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
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
                if (SpeedrunnerMod.CONFIG.doomMode) {
                    entity.setOnFireFor(6);
                    boolean bl = entity.damage(DamageSource.fireball(this, entity2), 5.0F);
                    if (!bl) {
                        entity.setFireTicks(i);
                    } else if (entity2 instanceof LivingEntity) {
                        this.applyDamageEffects((LivingEntity)entity2, entity);
                    }
                } else if (SpeedrunnerMod.CONFIG.difficulty == 1 || SpeedrunnerMod.CONFIG.difficulty == 2) {
                    entity.setOnFireFor(3);
                    boolean bl = entity.damage(DamageSource.fireball(this, entity2), 3.0F);
                    if (!bl) {
                        entity.setFireTicks(i);
                    } else if (entity2 instanceof LivingEntity) {
                        this.applyDamageEffects((LivingEntity)entity2, entity);
                    }
                } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
                    entity.setOnFireFor(5);
                    boolean bl = entity.damage(DamageSource.fireball(this, entity2), 4.0F);
                    if (!bl) {
                        entity.setFireTicks(i);
                    } else if (entity2 instanceof LivingEntity) {
                        this.applyDamageEffects((LivingEntity)entity2, entity);
                    }
                } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
                    entity.setOnFireFor(6);
                    boolean bl = entity.damage(DamageSource.fireball(this, entity2), 6.0F);
                    if (!bl) {
                        entity.setFireTicks(i);
                    } else if (entity2 instanceof LivingEntity) {
                        this.applyDamageEffects((LivingEntity)entity2, entity);
                    }
                } else {
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
}