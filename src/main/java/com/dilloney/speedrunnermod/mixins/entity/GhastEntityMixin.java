package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GhastEntity.class)
public class GhastEntityMixin {

    @Overwrite
    public static DefaultAttributeContainer.Builder createGhastAttributes() {
        if (SpeedrunnerMod.CONFIG.doomMode) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0D);
        } if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 15.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 7.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 25.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0D);
        } else {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0D);
        }
    }
}