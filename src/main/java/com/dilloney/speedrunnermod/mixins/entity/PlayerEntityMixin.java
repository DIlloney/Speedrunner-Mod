package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.dilloney.speedrunnermod.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.Vec3d;
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
        if (stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
            this.setHealth(getHealth() + 6);
        }
        if (stack.getItem() == Items.GOLDEN_APPLE) {
            this.setHealth(getHealth() + 4);
        }
        if (stack.getItem() == ModItems.SPEEDRUNNER_BULK) {
            this.setHealth(getHealth() + 4);
        }
    }

    @Inject(method = "travel", at = @At("TAIL"))
    private void speedrunnerModEffectsTravel(Vec3d movementInput, CallbackInfo callbackInfo) {
        FluidState fluidState = this.world.getFluidState(this.getBlockPos());
        if (this.isInLava() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid()) && this.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.SPEEDRUNNER_BOOTS) {
            this.updateVelocity(0.025F, movementInput);
            if (this.getRandom().nextFloat() < 0.03F) {
                this.getEquippedStack(EquipmentSlot.FEET).damage(1, this, (livingEntity) -> {
                    livingEntity.sendEquipmentBreakStatus(EquipmentSlot.FEET);
                });
            }
        } else if (this.isTouchingWater() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid()) && this.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.SPEEDRUNNER_BOOTS) {
            this.updateVelocity(0.005F, movementInput);
            if (this.getRandom().nextFloat() < 0.02F) {
                this.getEquippedStack(EquipmentSlot.FEET).damage(1, this, (livingEntity) -> {
                    livingEntity.sendEquipmentBreakStatus(EquipmentSlot.FEET);
                });
            }
        }
    }

    @Inject(method = "updateTurtleHelmet", at = @At("TAIL"))
    private void speedrunnerModEffects(CallbackInfo callbackInfo) {
        if (this.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.SPEEDRUNNER_BOOTS) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 10, 0, true, false, true));
        }
        if (this.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.SPEEDRUNNER_LEGGINGS && this.isSubmergedIn(FluidTags.WATER) && this.isSwimming() && SpeedrunnerMod.CONFIG.difficulty == 1) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 10, 0, true, true, true));
            if (this.getRandom().nextFloat() < 0.04F) {
                this.getEquippedStack(EquipmentSlot.LEGS).damage(1, this, (livingEntity) -> {
                    livingEntity.sendEquipmentBreakStatus(EquipmentSlot.LEGS);
                });
            }
        }
    }
}