package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WardenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(WardenEntity.class)
public class WardenEntityMixin {

    /**
     * Makes the warden weaker.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder addAttributes() {
        double genericMaxHealth = DOOM_MODE ? 300.0 : 150.0;
        double genericMovementSpeed = DOOM_MODE ? 0.4 : 0.2;
        double genericKnockbackResistance = DOOM_MODE ? 1.0 : 0.4;
        double genericAttackKnockback = DOOM_MODE ? 2.0 : 1.0;
        double genericAttackDamage = DOOM_MODE ? 30.0 : 10.0;
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, genericKnockbackResistance)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
    }
}