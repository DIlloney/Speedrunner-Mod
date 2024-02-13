package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(VindicatorEntity.class)
public abstract class VindicatorEntityMixin extends IllagerEntity {

    public VindicatorEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getXpToDrop(PlayerEntity player) {
        this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
        return super.getXpToDrop(player);
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createVindicatorAttributes() {
        final double genericMovementSpeed = 0.3499999940395355D;
        final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 48.0D : 12.0D;
        final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 20.0D : 24.0D;
        final double genericAttackDamage = 5.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (!super.tryAttack(target)) {
            return false;
        } else {
            if (SpeedrunnerMod.options().main.doomMode && target instanceof PlayerEntity) {
                ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
            }

            return true;
        }
    }
}