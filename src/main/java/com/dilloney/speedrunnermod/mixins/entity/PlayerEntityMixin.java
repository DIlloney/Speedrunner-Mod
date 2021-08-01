package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.dilloney.speedrunnermod.items.ModItems;
import com.dilloney.speedrunnermod.util.ModTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow abstract void disableShield(boolean sprinting);
    @Shadow abstract ItemCooldownManager getItemCooldownManager();

    @Inject(method = "eatFood", at = @At("HEAD"))
    public void eatFoodMod(World world, ItemStack stack, CallbackInfoReturnable callbackInfoReturnable) {
        if (stack.isIn(ModTags.GOLDEN_ITEMS)) {
            this.removeStatusEffect(StatusEffects.POISON);
        } else if (stack.getItem() == ModItems.SPEEDRUNNER_BULK) {
            this.heal(2.0F);
            if (SpeedrunnerMod.CONFIG.doomMode) {
                this.removeStatusEffect(StatusEffects.POISON);
            }
        }
    }

    @Inject(method = "travel", at = @At("TAIL"))
    private void travelMod(Vec3d movementInput, CallbackInfo callbackInfo) {
        if (this.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.SPEEDRUNNER_BOOTS || this.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.GOLDEN_SPEEDRUNNER_BOOTS) {
            FluidState fluidState = this.world.getFluidState(this.getBlockPos());
            if (this.isInLava() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid())) {
                this.updateVelocity(0.025F, movementInput);
                if (this.getRandom().nextFloat() < 0.01F) {
                    this.getEquippedStack(EquipmentSlot.FEET).damage(1, this, (livingEntity) -> {
                        livingEntity.sendEquipmentBreakStatus(EquipmentSlot.FEET);
                    });
                }
            } else if (this.isTouchingWater() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid())) {
                this.updateVelocity(0.004F, movementInput);
                if (this.getRandom().nextFloat() < 0.01F) {
                    this.getEquippedStack(EquipmentSlot.FEET).damage(1, this, (livingEntity) -> {
                        livingEntity.sendEquipmentBreakStatus(EquipmentSlot.FEET);
                    });
                }
            }
        }
    }

    @Inject(method = "updateTurtleHelmet", at = @At("TAIL"))
    private void updateTurtleHelmetMod(CallbackInfo callbackInfo) {
        if (this.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.SPEEDRUNNER_BOOTS || this.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.GOLDEN_SPEEDRUNNER_BOOTS) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 10, 0, true, false, true));
        }
        if (this.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.SPEEDRUNNER_LEGGINGS && this.isSubmergedIn(FluidTags.WATER) && this.isSwimming() && SpeedrunnerMod.CONFIG.difficulty == 1 || this.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS && this.isSubmergedIn(FluidTags.WATER) && this.isSwimming() && SpeedrunnerMod.CONFIG.difficulty == 1) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 10, 0, true, true, true));
            if (this.getRandom().nextFloat() < 0.04F) {
                this.getEquippedStack(EquipmentSlot.LEGS).damage(1, this, (livingEntity) -> {
                    livingEntity.sendEquipmentBreakStatus(EquipmentSlot.LEGS);
                });
            }
        }
    }

    @Overwrite
    public void takeShieldHit(LivingEntity attacker) {
        super.takeShieldHit(attacker);
        if (attacker.getMainHandStack().getItem() instanceof AxeItem) {
            this.disableShield(true);
        }

        if (SpeedrunnerMod.CONFIG.doomMode) {
            if (attacker instanceof GiantEntity || attacker instanceof RavagerEntity || attacker instanceof HoglinEntity) {
                this.disableShieldFromDoomModeEntities();
            }
        }
    }

    public void disableShieldFromDoomModeEntities() {
        this.getItemCooldownManager().set(Items.SHIELD, 200);
        this.clearActiveItem();
        this.world.sendEntityStatus(this, (byte)30);
    }
}