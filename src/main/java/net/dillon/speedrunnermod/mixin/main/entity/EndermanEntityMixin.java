package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin extends HostileEntity {

    public EndermanEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop() {
        if (attackingPlayer != null) {
            this.experiencePoints = 10 + EnchantmentHelper.getLooting(attackingPlayer) * 48;
        }
        return super.getXpToDrop();
    }

    /**
     * Modifies the enderman's attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createEndermanAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 60.0D : 25.0D;
        final double genericMovementSpeed = 0.30000001192092896D;
        final double genericAttackDamage = DOOM_MODE ? 8.0D : 4.0D;
        final double genericFollowRange = DOOM_MODE ? 64.0D : 12.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
    }
}