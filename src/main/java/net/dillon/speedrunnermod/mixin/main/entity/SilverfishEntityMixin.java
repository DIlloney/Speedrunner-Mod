package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(SilverfishEntity.class)
public abstract class SilverfishEntityMixin extends HostileEntity {

    public SilverfishEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop(PlayerEntity player) {
        if (player != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 16;
        }
        return super.getXpToDrop(player);
    }

    /**
     * Modifies the silverfish's attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createSilverfishAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 8.0D : 4.0D;
        final double genericMovementSpeed = DOOM_MODE ? 0.25D : 0.15D;
        final double genericAttackDamage = DOOM_MODE ? 2.0D : 0.01D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
    }
}