package net.dillon8775.speedrunnermod.mixin.main.entity.living;

import net.dillon8775.speedrunnermod.enchantment.ModEnchantments;
import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.advancement.criterion.Criteria;
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
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.tag.Tag;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
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

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    public abstract ItemStack getStackInHand(Hand hand);
    @Shadow
    public abstract void setHealth(float health);
    @Shadow
    public abstract boolean clearStatusEffects();
    @Shadow @Final
    public abstract boolean addStatusEffect(StatusEffectInstance effect);
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();
    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot var1);
    @Shadow
    protected abstract boolean shouldSwimInFluids();
    @Shadow
    public abstract boolean canWalkOnFluid(Fluid fluid);
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
            boolean hasBuffedItem = this.getEquippedStack(EquipmentSlot.FEET).isIn(ModItemTags.BUFFED_ITEMS);
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, i, dashEnchantmentLevel, true, false, true));
            FluidState fluidState = this.world.getFluidState(this.getBlockPos());
            float lavaVelocity = dashEnchantmentLevel > 0 ? 0.035F : 0.025F;
            float waterVelocity = dashEnchantmentLevel > 0 ? 0.006F : 0.004F;
            boolean isBuffedItems = this.getEquippedStack(EquipmentSlot.FEET).isIn(ModItemTags.BUFFED_ITEMS) && this.getRandom().nextFloat() < 0.01F;
            if (this.isInLava() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid())) {
                this.updateVelocity(lavaVelocity, movementInput);
                if (!this.hasNoGravity()) {
                    this.setVelocity(this.getVelocity().add(0.0D, -0.02D, 0.0D));
                }

                if (isBuffedItems) {
                    this.getEquippedStack(EquipmentSlot.FEET).damage(1, (LivingEntity)(Object)this, (livingEntity) -> {
                        livingEntity.sendEquipmentBreakStatus(EquipmentSlot.FEET);
                    });
                }
            } else if (this.isTouchingWater() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid())) {
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
     * Allows entities to "swim upward" at a faster rate if they have on a buffed piece of armor.
     */
    @Overwrite
    public void swimUpward(Tag<Fluid> fluid) {
        if (this.isInLava() && this.getEquippedStack(EquipmentSlot.FEET).isIn(ModItemTags.BUFFED_ITEMS)) {
            double velocity = EnchantmentHelper.getEquipmentLevel(ModEnchantments.DASH, (LivingEntity)(Object)this) > 0 ? 0.09D : 0.06D;
            this.setVelocity(this.getVelocity().add(0.0D, velocity, 0.0D));
        } else if (this.isInLava() && EnchantmentHelper.getEquipmentLevel(ModEnchantments.DASH, (LivingEntity)(Object)this) > 0) {
            this.setVelocity(this.getVelocity().add(0.0D, 0.08D, 0.0D));
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
    @Overwrite
    private boolean tryUseTotem(DamageSource source) {
        if (source.isOutOfWorld()) {
            return false;
        } else {
            ItemStack itemStack = null;
            Hand[] var4 = Hand.values();
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Hand hand = var4[var6];
                ItemStack itemStack2 = this.getStackInHand(hand);
                if (itemStack2.isOf(Items.TOTEM_OF_UNDYING)) {
                    itemStack = itemStack2.copy();
                    itemStack2.decrement(1);
                    break;
                }
            }

            if (itemStack != null) {
                if ((LivingEntity)(Object)this instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)(Object)this;
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING));
                    Criteria.USED_TOTEM.trigger(serverPlayerEntity, itemStack);
                }

                this.setHealth(1.0F);
                this.clearStatusEffects();
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2400, 0));
                this.world.sendEntityStatus(this, (byte)35);
            }

            return itemStack != null;
        }
    }
}