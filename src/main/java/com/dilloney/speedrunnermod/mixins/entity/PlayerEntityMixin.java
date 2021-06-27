package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "eatFood", at = @At("HEAD"))
    public void eatFood(World world, ItemStack stack, CallbackInfoReturnable callbackInfoReturnable) {
        PlayerEntity playerEntity = (PlayerEntity)(Object)this;
        if (stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
            playerEntity.setHealth(getHealth() + 6);
        }
        if (stack.getItem() == Items.GOLDEN_APPLE) {
            playerEntity.setHealth(getHealth() + 2);
        }
    }

    @Inject(method = "updateTurtleHelmet", at = @At("TAIL"))
    private void speedrunnerModEffects(CallbackInfo callbackInfo) {
        PlayerEntity playerEntity = (PlayerEntity)(Object)this;
        ItemStack leggingStack = this.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack bootStack = this.getEquippedStack(EquipmentSlot.FEET);
        if (bootStack.getItem() == ModItems.SPEEDRUNNER_BOOTS) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 0, true, false, true));
        }
        if (leggingStack.getItem() == ModItems.SPEEDRUNNER_LEGGINGS && this.isSubmergedIn(FluidTags.WATER)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 60, 0, true, true, true));
        }
        if (playerEntity.getHealth() < 2 && !isSpectator() && !playerEntity.abilities.creativeMode && !playerEntity.isDead() || playerEntity.getHealth() == 2 && !isSpectator() && !playerEntity.abilities.creativeMode && !playerEntity.isDead()) {
            this.world.addParticle(ParticleTypes.DAMAGE_INDICATOR, this.getParticleX(0.5D), this.getRandomBodyY() - 0.25D, this.getParticleZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
        }
    }
}
