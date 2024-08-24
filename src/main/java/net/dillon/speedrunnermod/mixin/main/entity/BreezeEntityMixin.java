package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.BreezeEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(BreezeEntity.class)
public class BreezeEntityMixin {

    /**
     * Modifies the attributes for breezes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createBreezeAttributes() {
        final double genericMovementSpeed = DOOM_MODE ? 0.65D : 0.50D;
        final double genericMaxHealth = DOOM_MODE ? 35.0D : 25.0D;
        final double genericFollowRange = DOOM_MODE ? 32.0D : 16.0D;
        final double genericAttackDamage = DOOM_MODE ? 3.5D : 2.0D;
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
    }
}