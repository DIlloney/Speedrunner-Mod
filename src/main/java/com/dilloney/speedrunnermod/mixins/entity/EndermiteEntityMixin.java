package com.dilloney.speedrunnermod.mixins.entity;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EndermiteEntity.class)
public class EndermiteEntityMixin {

    @Overwrite
    public static DefaultAttributeContainer.Builder createEndermiteAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.08D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }
}