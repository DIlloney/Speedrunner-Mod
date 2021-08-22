package com.dilloney.speedrunnermod.mixins.entity.player;

import com.dilloney.speedrunnermod.item.ModItems;
import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Deprecated
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow abstract void disableShield(boolean sprinting);
    @Shadow abstract ItemCooldownManager getItemCooldownManager();

    @Inject(method = "eatFood", at = @At("HEAD"))
    private void makeGoldenFoodItemsClearPoison(World world, ItemStack stack, CallbackInfoReturnable callbackInfoReturnable) {
        if (stack.isIn(ModItemTags.GOLDEN_ITEMS)) {
            this.removeStatusEffect(StatusEffects.POISON);
        } else if (stack.getItem() == ModItems.SPEEDRUNNER_BULK) {
            this.heal(2.0F);
            this.removeStatusEffect(StatusEffects.POISON);
        }
    }

    @Inject(method = "travel", at = @At("TAIL"))
    private void makeSpeedrunnerBootsMakePlayerFasterInLavaOrWater(Vec3d movementInput, CallbackInfo callbackInfo) {
        if (this.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.SPEEDRUNNER_BOOTS) || this.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.GOLDEN_SPEEDRUNNER_BOOTS)) {
            FluidState fluidState = this.world.getFluidState(this.getBlockPos());
            if (this.isInLava() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid())) {
                this.updateVelocity(0.025F, movementInput);
                if (!this.hasNoGravity()) {
                    this.setVelocity(this.getVelocity().add(0.0D, -0.08D / 4.0D, 0.0D));
                }
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
    private void makeSpeedrunnerBootsAndSpeedrunnerLeggingsGivePotionEffects(CallbackInfo callbackInfo) {
        if (this.getEquippedStack(EquipmentSlot.FEET).isIn(ModItemTags.SPEED_EFFECT_ITEMS)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 10, 0, true, false, true));
        }
        if (this.getEquippedStack(EquipmentSlot.LEGS).isIn(ModItemTags.DOLPHINS_GRACE_EFFECT_ITEMS) && this.isTouchingWater() && this.isSwimming() && OPTIONS.getModDifficulty() == 1) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 10, 0, true, true, true));
            if (this.getRandom().nextFloat() < 0.04F) {
                this.getEquippedStack(EquipmentSlot.LEGS).damage(1, this, (livingEntity) -> {
                    livingEntity.sendEquipmentBreakStatus(EquipmentSlot.LEGS);
                });
            }
        }
    }

    @Redirect(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean allowSpeedrunnerShieldToWorkCorrectly(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.SHIELDS);
    }

    /**
     * Changes how the shield gets disabled.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public void takeShieldHit(LivingEntity attacker) {
        super.takeShieldHit(attacker);
        if (attacker.getMainHandStack().getItem() instanceof AxeItem) {
            this.disableShield(true);
            this.disableSpeedrunnerShield(true);
        }

        if (OPTIONS.doomMode) {
            if (attacker instanceof GiantEntity || attacker instanceof RavagerEntity || attacker instanceof HoglinEntity) {
                this.disableShieldFromDoomModeEntities();
            }
        }
    }

    @Override
    protected void swimUpward(Tag<Fluid> fluid) {
        if (this.getEquippedStack(EquipmentSlot.FEET).isIn(ModItemTags.SPEED_EFFECT_ITEMS) && this.isInLava()) {
            this.setVelocity(this.getVelocity().add(0.0D, 0.07999999910593033D, 0.0D));
        } else {
            this.setVelocity(this.getVelocity().add(0.0D, 0.03999999910593033D, 0.0D));
        }
    }

    public void disableSpeedrunnerShield(boolean sprinting) {
        float f = 0.25F + (float) EnchantmentHelper.getEfficiency(this) * 0.05F;
        if (sprinting) {
            f += 0.75F;
        }

        if (this.random.nextFloat() < f) {
            this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 80);
            this.clearActiveItem();
            this.world.sendEntityStatus(this, (byte)30);
        }
    }

    public void disableShieldFromDoomModeEntities() {
        this.getItemCooldownManager().set(Items.SHIELD, 200);
        this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 180);
        this.clearActiveItem();
        this.world.sendEntityStatus(this, (byte)30);
    }
}