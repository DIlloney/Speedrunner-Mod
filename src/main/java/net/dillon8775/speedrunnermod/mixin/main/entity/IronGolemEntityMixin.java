package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(IronGolemEntity.class)
public class IronGolemEntityMixin extends GolemEntity {

    public IronGolemEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getXpToDrop(PlayerEntity player) {
        this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
        return super.getXpToDrop(player);
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createIronGolemAttributes() {
        final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 100.0D : 50.0D;
        final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.3D : 0.25D;
        final double genericKnockbackResistance = SpeedrunnerMod.options().main.doomMode ? 0.7D : 0.5D;
        final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 20.0D : 7.0D;
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, genericKnockbackResistance)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
    }
}