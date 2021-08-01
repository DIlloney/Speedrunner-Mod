package com.dilloney.speedrunnermod.mixins.entity.mob;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GuardianEntity.class)
public class GuardianEntityMixin {

    @Overwrite
    public static DefaultAttributeContainer.Builder createGuardianAttributes() {
        if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0D);
        } else {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0D);
        }
    }
}