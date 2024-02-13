package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GhastEntity.class)
public class GhastEntityMixin extends FlyingEntity {

    public GhastEntityMixin(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getXpToDrop(PlayerEntity player) {
        this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
        return super.getXpToDrop(player);
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createGhastAttributes() {
        final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 20.0D : 5.0D;
        final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 100.0D : 50.0D;
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
    }
}