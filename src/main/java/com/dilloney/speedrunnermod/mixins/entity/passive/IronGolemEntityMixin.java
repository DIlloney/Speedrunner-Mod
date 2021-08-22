package com.dilloney.speedrunnermod.mixins.entity.passive;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(IronGolemEntity.class)
public class IronGolemEntityMixin {

    /**
     * Changes the Iron Golems health.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createIronGolemAttributes() {
        if (OPTIONS.doomMode) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.7D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 20.0D);
        } else if (OPTIONS.getModDifficulty() == 1) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.5D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0D);
        } else if (OPTIONS.getModDifficulty() == 2) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 60.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.6D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 9.0D);
        } else if (OPTIONS.getModDifficulty() == 3) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.7D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 11.0D);
        } else {
            return OPTIONS.getModDifficulty() == 4 ? MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 150.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0D) : MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.5D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0D);
        }
    }
}