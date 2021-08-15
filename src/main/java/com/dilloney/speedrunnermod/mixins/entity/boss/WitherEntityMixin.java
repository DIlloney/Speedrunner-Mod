package com.dilloney.speedrunnermod.mixins.entity.boss;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(WitherEntity.class)
public class WitherEntityMixin {

    @Overwrite
    public static DefaultAttributeContainer.Builder createWitherAttributes() {
        return OPTIONS.getModDifficulty() <= 2 ? HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6000000238418579D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0D).add(EntityAttributes.GENERIC_ARMOR, 4.0D) : HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6000000238418579D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0D).add(EntityAttributes.GENERIC_ARMOR, 4.0D);
    }
}