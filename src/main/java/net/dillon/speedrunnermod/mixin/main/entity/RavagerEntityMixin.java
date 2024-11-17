package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(RavagerEntity.class)
public abstract class RavagerEntityMixin extends RaiderEntity {

    public RavagerEntityMixin(EntityType<? extends RaiderEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop(ServerWorld world) {
        if (attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ItemUtil.enchantment((RavagerEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 72;
        }
        return super.getXpToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code ravager} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createRavagerAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 100.0D : 50.0D;
        final double genericMovementSpeed = 0.3D;
        final double genericKnockbackResistance = 0.75D;
        final double genericAttackDamage = DOOM_MODE ? 16.0D : 10.0D;
        final double genericAttackKnockback = DOOM_MODE ? 1.6D : 1.1D;
        final double genericFollowRange = DOOM_MODE ? 48.0D : 32.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, genericKnockbackResistance)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.ATTACK_KNOCKBACK, genericAttackKnockback)
                .add(EntityAttributes.FOLLOW_RANGE, genericFollowRange);
    }

    /**
     * Inflicts players with {@code slowness} when attacking.
     */
    @Inject(method = "tryAttack", at = @At("RETURN"))
    private void tryAttack(Entity target, CallbackInfoReturnable cir) {
        if (DOOM_MODE && target instanceof PlayerEntity) {
            ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TickCalculator.seconds(10), 0));
        }
    }
}