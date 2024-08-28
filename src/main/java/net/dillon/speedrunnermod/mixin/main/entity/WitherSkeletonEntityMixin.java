package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
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

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityMixin extends AbstractSkeletonEntity {

    public WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop() {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ItemUtil.enchantment((WitherSkeletonEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 36;
        }
        return super.getXpToDrop();
    }

    /**
     * Lowers attack damage from wither skeletons.
     */
    @ModifyArg(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeInstance;setBaseValue(D)V"))
    private double genericAttackDamage(double baseValue) {
        return DOOM_MODE ? 10.0D : 1.0D;
    }

    /**
     * Decreases the amplifier of the wither effect when wither skeleton's attack.
     */
    @ModifyArg(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/registry/entry/RegistryEntry;I)V"), index = 1)
    private int tryAttack(int x) {
        return SpeedrunnerMod.getWitherSkeletonWitherEffectDuration();
    }

    /**
     * Inflicts players with {@code slowness} if {@code doom mode} is enabled.
     */
    @Inject(method = "tryAttack", at = @At("RETURN"))
    private void tryAttack(Entity target, CallbackInfoReturnable<?> cir) {
        if (DOOM_MODE && target instanceof PlayerEntity) {
            ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TickCalculator.seconds(10), 0));
        }
    }
}