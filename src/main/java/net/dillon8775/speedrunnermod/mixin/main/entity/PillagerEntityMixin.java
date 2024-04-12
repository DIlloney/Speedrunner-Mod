<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(PillagerEntity.class)
public abstract class PillagerEntityMixin extends IllagerEntity {

    public PillagerEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop() {
        if (attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(attackingPlayer) * 36;
        }
        return super.getXpToDrop();
    }

    /**
     * Modifies the pillager's attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createPillagerAttributes() {
        final double genericMovementSpeed = 0.3499999940395355D;
        final double genericMaxHealth = DOOM_MODE ? 32.0D : 12.0D;
        final double genericAttackDamage = DOOM_MODE ? 8.0D : 4.0;
        final double genericFollowRange = DOOM_MODE ? 32.0D : 16.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(PillagerEntity.class)
public abstract class PillagerEntityMixin extends IllagerEntity {

    public PillagerEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop() {
        if (attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(attackingPlayer) * 36;
        }
        return super.getXpToDrop();
    }

    /**
     * Modifies the pillager's attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createPillagerAttributes() {
        final double genericMovementSpeed = 0.3499999940395355D;
        final double genericMaxHealth = DOOM_MODE ? 32.0D : 12.0D;
        final double genericAttackDamage = DOOM_MODE ? 8.0D : 4.0;
        final double genericFollowRange = DOOM_MODE ? 32.0D : 16.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
    }
>>>>>>> Stashed changes
}