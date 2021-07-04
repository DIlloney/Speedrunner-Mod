package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;

@Mixin(EnderDragonEntity.class)
public class EnderDragonEntityMixin extends MobEntity implements Monster {

    @Shadow EndCrystalEntity connectedCrystal;

    public EnderDragonEntityMixin(EntityType<? extends EnderDragonEntity> entityType, World world) {
        super(EntityType.ENDER_DRAGON, world);
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createEnderDragonAttributes() {
        if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 150.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 225.0D);
        } else {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D);
        }
    }

    @Overwrite
    private void tickWithEndCrystals() {
        if (this.connectedCrystal != null) {
            if (this.connectedCrystal.isRemoved()) {
                this.connectedCrystal = null;
            } else if (this.age % 10 == 0 && this.getHealth() < this.getMaxHealth()) {
                if (SpeedrunnerMod.CONFIG.difficulty == 1) {
                    this.setHealth(this.getHealth() + 0.1F);
                } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
                    this.setHealth(this.getHealth() + 0.4F);
                } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
                    this.setHealth(this.getHealth() + 0.7F);
                } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
                    this.setHealth(this.getHealth() + 1.1F);
                } else {
                    this.setHealth(this.getHealth() + 0.1F);
                }
            }
        }

        if (this.random.nextInt(10) == 0) {
            List<EndCrystalEntity> list = this.world.getNonSpectatingEntities(EndCrystalEntity.class, this.getBoundingBox().expand(32.0D));
            EndCrystalEntity endCrystalEntity = null;
            double d = 1.7976931348623157E308D;
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                EndCrystalEntity endCrystalEntity2 = (EndCrystalEntity)var5.next();
                double e = endCrystalEntity2.squaredDistanceTo(this);
                if (e < d) {
                    d = e;
                    endCrystalEntity = endCrystalEntity2;
                }
            }

            this.connectedCrystal = endCrystalEntity;
        }

    }

    @Overwrite
    private void damageLivingEntities(List<Entity> entities) {
        Iterator var2 = entities.iterator();

        while(var2.hasNext()) {
            Entity entity = (Entity)var2.next();
            if (entity instanceof LivingEntity) {
                if (SpeedrunnerMod.CONFIG.difficulty == 1) {
                    entity.damage(DamageSource.mob(this), 3.0F);
                } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
                    entity.damage(DamageSource.mob(this), 5.0F);
                } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
                    entity.damage(DamageSource.mob(this), 7.0F);
                } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
                    entity.damage(DamageSource.mob(this), 10.0F);
                } else {
                    entity.damage(DamageSource.mob(this), 3.0F);
                }
                this.applyDamageEffects(this, entity);
            }
        }
    }
}