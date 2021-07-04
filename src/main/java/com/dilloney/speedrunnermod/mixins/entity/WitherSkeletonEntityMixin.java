package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityMixin extends AbstractSkeletonEntity {

    public WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
    }

    @Overwrite
    public void initGoals() {
        super.initGoals();
    }

    @Overwrite @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityTag) {
        EntityData entityData2 = super.initialize(world, difficulty, spawnReason, entityData, entityTag);
        if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(1.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(2.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(3.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(5.0D);
        }
        this.updateAttackType();
        return entityData2;
    }

    @Overwrite
    public boolean tryAttack(Entity target) {
        if (!super.tryAttack(target)) {
            return false;
        } else {
            if (target instanceof LivingEntity) {
                if (SpeedrunnerMod.CONFIG.difficulty == 1) {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60));
                } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100));
                } else if (SpeedrunnerMod.CONFIG.difficulty == 3 || SpeedrunnerMod.CONFIG.difficulty == 4) {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 120));
                } else {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60));
                }
            }

            return true;
        }
    }
}
