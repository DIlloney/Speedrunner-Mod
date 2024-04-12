package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(HoglinEntity.class)
public abstract class HoglinEntityMixin extends AnimalEntity {

    public HoglinEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop() {
        int looting = attackingPlayer != null ? EnchantmentHelper.getLooting(attackingPlayer) * 36 : 0;
        return this.experiencePoints + looting;
    }

    /**
     * Modifies the hoglin's attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createHoglinAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 60.0D : 25.0D;
        final double genericMovementSpeed = 0.30000001192092896D;
        final double genericKnockbackResistance = DOOM_MODE ? 0.7000000238518589D : 0.6000000238418579D;
        final double genericAttackKnockback = DOOM_MODE ? 1.2D : 0.5D;
        final double genericAttackDamage = DOOM_MODE ? 8.0D : 4.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, genericKnockbackResistance)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
    }
}