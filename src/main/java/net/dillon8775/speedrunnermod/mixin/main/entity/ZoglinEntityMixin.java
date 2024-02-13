package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZoglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ZoglinEntity.class)
public class ZoglinEntityMixin extends HostileEntity {

    public ZoglinEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getXpToDrop(PlayerEntity player) {
        this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
        return super.getXpToDrop(player);
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createZoglinAttributes() {
        final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 60.0D : 25.0D;
        final double genericMovementSpeed = 0.30000001192092896D;
        final double genericKnockbackResistance = SpeedrunnerMod.options().main.doomMode ? 0.7000000238518589D : 0.6000000238418579D;
        final double genericAttackKnockback = SpeedrunnerMod.options().main.doomMode ? 1.2D : 0.5D;
        final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, genericKnockbackResistance)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
    }
}