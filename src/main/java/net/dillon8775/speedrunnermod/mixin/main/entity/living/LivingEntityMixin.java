package net.dillon8775.speedrunnermod.mixin.main.entity.living;

import net.dillon8775.speedrunnermod.enchantment.ModEnchantments;
import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.dillon8775.speedrunnermod.util.TickCalculator;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow @Final
    public abstract boolean addStatusEffect(StatusEffectInstance effect);
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();
    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot var1);
    @Shadow
    protected abstract boolean shouldSwimInFluids();
    @Shadow
    public abstract boolean canWalkOnFluid(FluidState fluidState);
    @Shadow
    public abstract Random getRandom();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /**
     * Adds some features to the players movement and abilities.
     */
    @Inject(method = "travel", at = @At("TAIL"))
    private void travel(Vec3d movementInput, CallbackInfo ci) {
        if (this.getEquippedStack(EquipmentSlot.FEET).isIn(ModItemTags.BUFFED_ITEMS) || EnchantmentHelper.getEquipmentLevel(ModEnchantments.DASH, (LivingEntity)(Object)this) > 0) {
            int i = this.world.getDifficulty() != Difficulty.HARD ? 60 : 20;
            int dashEnchantmentLevel = EnchantmentHelper.getEquipmentLevel(ModEnchantments.DASH, (LivingEntity)(Object)this);
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, i, dashEnchantmentLevel, true, false, true));
            FluidState fluidState = this.world.getFluidState(this.getBlockPos());
            float lavaVelocity = dashEnchantmentLevel > 8 ? (0.1F * dashEnchantmentLevel) / 6.0F : dashEnchantmentLevel == 8 ? 0.1F : dashEnchantmentLevel == 7 ? 0.090F : dashEnchantmentLevel == 6 ? 0.080F : dashEnchantmentLevel == 5 ? 0.070F : dashEnchantmentLevel == 4 ? 0.060F : dashEnchantmentLevel == 3 ? 0.045F : dashEnchantmentLevel == 2 ? 0.040F : dashEnchantmentLevel == 1 ? 0.035F : 0.025F;
            float waterVelocity = dashEnchantmentLevel > 8 ? (0.020F * dashEnchantmentLevel) / 6.0F : dashEnchantmentLevel == 8 ? 0.020F : dashEnchantmentLevel == 7 ? 0.018F : dashEnchantmentLevel == 6 ? 0.016F : dashEnchantmentLevel == 5 ? 0.014F : dashEnchantmentLevel == 4 ? 0.012F : dashEnchantmentLevel == 3 ? 0.010F : dashEnchantmentLevel == 2 ? 0.008F : dashEnchantmentLevel == 1 ? 0.006F : 0.004F;
            boolean isBuffedItems = this.getEquippedStack(EquipmentSlot.FEET).isIn(ModItemTags.BUFFED_ITEMS) && this.getRandom().nextFloat() < 0.01F;
            if (this.isInLava() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState)) {
                this.updateVelocity(lavaVelocity, movementInput);
                if (!this.hasNoGravity()) {
                    this.setVelocity(this.getVelocity().add(0.0D, -0.02D, 0.0D));
                }

                if (isBuffedItems) {
                    this.getEquippedStack(EquipmentSlot.FEET).damage(1, (LivingEntity)(Object)this, (livingEntity) -> {
                        livingEntity.sendEquipmentBreakStatus(EquipmentSlot.FEET);
                    });
                }
            } else if (this.isTouchingWater() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState)) {
                this.updateVelocity(waterVelocity, movementInput);
                if (isBuffedItems) {
                    this.getEquippedStack(EquipmentSlot.FEET).damage(1, (LivingEntity)(Object)this, (livingEntity) -> {
                        livingEntity.sendEquipmentBreakStatus(EquipmentSlot.FEET);
                    });
                }
            }
        }
    }

    /**
     * Makes the player immune to kinetic damage, if disabled.
     */
    @Inject(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), cancellable = true)
    private void cancelElytraDamage(Vec3d movementInput, CallbackInfo ci) {
        if (!options().main.kineticDamage) {
            ci.cancel();
        }
    }

    /**
     * Disables the sound from playing to kinetic damage, if disabled.
     */
    @Inject(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"), cancellable = true)
    private void cancelElytraDamageSound(Vec3d movementInput, CallbackInfo ci) {
        if (!options().main.kineticDamage) {
            ci.cancel();
        }
    }

    /**
     * Allows entities to "swim upward" at a faster rate if they have on a buffed piece of armor.
     */
    @Overwrite
    public void swimUpward(TagKey<Fluid> fluid) {
        double dashEnchantment = EnchantmentHelper.getEquipmentLevel(ModEnchantments.DASH, (LivingEntity)(Object)this);
        if (this.isInLava() && this.getEquippedStack(EquipmentSlot.FEET).isIn(ModItemTags.BUFFED_ITEMS)) {
            double velocity = dashEnchantment > 8 ? (0.21D * dashEnchantment) / 6.0D : dashEnchantment == 8 ? 0.21D : dashEnchantment == 7 ? 0.19D : dashEnchantment == 6 ? 0.17D : dashEnchantment == 5 ? 0.15D : dashEnchantment == 4 ? 0.13D : dashEnchantment == 3 ? 0.11D : dashEnchantment == 2 ? 0.09D : dashEnchantment == 1 ? 0.07D : 0.06D;
            this.setVelocity(this.getVelocity().add(0.0D, velocity, 0.0D));
        } else if (this.isInLava() && EnchantmentHelper.getEquipmentLevel(ModEnchantments.DASH, (LivingEntity)(Object)this) > 0) {
            double velocity = dashEnchantment > 8 ? (0.20D * dashEnchantment) / 6.0D : dashEnchantment == 8 ? 0.20D : dashEnchantment == 7 ? 0.18D : dashEnchantment == 6 ? 0.16D : dashEnchantment == 5 ? 0.14D : dashEnchantment == 4 ? 0.12D : dashEnchantment == 3 ? 0.10D : dashEnchantment == 2 ? 0.08D : dashEnchantment == 1 ? 0.06D : 0.04D;
            this.setVelocity(this.getVelocity().add(0.0D, velocity, 0.0D));
        } else {
            this.setVelocity(this.getVelocity().add(0.0D, 0.04D, 0.0D));
        }
    }

    /**
     * Redirects the preferred equipment slot to be either a shield or a speedrunner shield.
     */
    @Redirect(method = "getPreferredEquipmentSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 2))
    private static boolean getPreferredEquipmentSlot(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.SHIELDS);
    }

    /**
     * Applies fire resistance for 2 minutes when using a totem.
     */
    @Inject(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z"))
    private void applyFireResistance(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, TickCalculator.minutes(2), 0));
    }
}