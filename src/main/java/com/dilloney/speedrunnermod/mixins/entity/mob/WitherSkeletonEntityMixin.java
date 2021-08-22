package com.dilloney.speedrunnermod.mixins.entity.mob;

import net.minecraft.entity.*;
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

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityMixin extends AbstractSkeletonEntity {

    @Deprecated
    protected WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Changes what goals a Wither Skeleton has.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public void initGoals() {
        super.initGoals();
    }

    /**
     * Changes how much damage a wither skeleton can do.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityTag) {
        EntityData entityData2 = super.initialize(world, difficulty, spawnReason, entityData, entityTag);
        if (OPTIONS.doomMode) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0D);
        } else if (OPTIONS.getModDifficulty() == 1) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(1.0D);
        } else if (OPTIONS.getModDifficulty() == 2) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(2.0D);
        } else if (OPTIONS.getModDifficulty() == 3) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(3.0D);
        } else if (OPTIONS.getModDifficulty() == 4) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(5.0D);
        }
        this.updateAttackType();
        return entityData2;
    }

    /**
     * Changes how long the wither can give the wither effect for based on mod difficulty.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public boolean tryAttack(Entity target) {
        if (!super.tryAttack(target)) {
            return false;
        } else {
            if (target instanceof LivingEntity) {
                if (OPTIONS.doomMode) {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200, 0));
                } else if (OPTIONS.getModDifficulty() == 1) {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 0));
                } else if (OPTIONS.getModDifficulty() == 2) {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 0));
                } else if (OPTIONS.getModDifficulty() >= 3) {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 120, 0));
                } else {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 0));
                }
            }

            return true;
        }
    }
}