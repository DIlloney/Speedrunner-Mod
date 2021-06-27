package com.dilloney.speedrunnermod.mixins.entity;

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
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }
}
