package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PiglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PiglinEntity.class)
public class PiglinEntityMixin {

    @Overwrite
    public static DefaultAttributeContainer.Builder createPiglinAttributes() {
        if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 28.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D);
        } else {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
        }
    }
}
