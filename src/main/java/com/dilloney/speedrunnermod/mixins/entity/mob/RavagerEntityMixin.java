package com.dilloney.speedrunnermod.mixins.entity.mob;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.RavagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RavagerEntity.class)
public class RavagerEntityMixin {

    @Overwrite
    public static DefaultAttributeContainer.Builder createRavagerAttributes() {
        if (SpeedrunnerMod.CONFIG.doomMode) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.75D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0D).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2.1D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D);
        } else {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.75D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0D).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.5D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0D);
        }
    }
}