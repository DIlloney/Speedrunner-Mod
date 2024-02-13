package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityMixin extends AbstractSkeletonEntity {

    public WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getXpToDrop(PlayerEntity player) {
        this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
        return super.getXpToDrop(player);
    }

    @ModifyArg(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeInstance;setBaseValue(D)V"))
    private double genericAttackDamage(double baseValue) {
        return SpeedrunnerMod.options().main.doomMode ? 10.0D : 1.0D;
    }

    @ModifyArg(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;I)V"), index = 1)
    private int tryAttack(int x) {
        return SpeedrunnerMod.options().main.doomMode ? 200 : 60;
    }

    @Inject(method = "tryAttack", at = @At("RETURN"))
    private void tryAttack(Entity target, CallbackInfoReturnable cir) {
        if (SpeedrunnerMod.options().main.doomMode && target instanceof PlayerEntity) {
            ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
        }
    }
}