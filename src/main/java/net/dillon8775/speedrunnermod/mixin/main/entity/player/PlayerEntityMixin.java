package net.dillon8775.speedrunnermod.mixin.main.entity.player;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    abstract ItemCooldownManager getItemCooldownManager();

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getItemCooldownManager()Lnet/minecraft/entity/player/ItemCooldownManager;"))
    private void disableShield(CallbackInfo ci) {
        this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 80);
    }

    @Redirect(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean damageShield(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.SHIELDS);
    }

    /**
     * Adds some features to the players movement and abilities.
     */
    @Inject(method = "travel", at = @At("TAIL"))
    private void travel(Vec3d movementInput, CallbackInfo ci) {
        if (SpeedrunnerMod.options().advanced.itemBuffs) {
            if (this.getEquippedStack(EquipmentSlot.FEET).isIn(ModItemTags.BUFFED_ITEMS)) {
                int i = this.world.getDifficulty() != Difficulty.HARD ? 60 : 20;
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, i, 0, true, false, true));
                FluidState fluidState = this.world.getFluidState(this.getBlockPos());
                if (this.isInLava() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid())) {
                    this.updateVelocity(0.025F, movementInput);
                    if (!this.hasNoGravity()) {
                        this.setVelocity(this.getVelocity().add(0.0D, -0.02D, 0.0D));
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
    }

    /**
     * Allows players to "swim upward" at a faster rate if they have on a buffed piece of armor.
     */
    protected void swimUpward(Tag<Fluid> fluid) {
        if (SpeedrunnerMod.options().advanced.itemBuffs && this.isInLava() && this.getEquippedStack(EquipmentSlot.FEET).isIn(ModItemTags.BUFFED_ITEMS)) {
            this.setVelocity(this.getVelocity().add(0.0D, 0.07999999910593034D, 0.0D));
        } else {
            this.setVelocity(this.getVelocity().add(0.0D, 0.03999999910593033D, 0.0D));
        }
    }

    /**
     * Allows speedrunner shields to get disabled, as a shield should, when hit by an axe.
     */
    @Inject(method = "takeShieldHit", at = @At("TAIL"))
    private void takeShieldHit(LivingEntity attacker, CallbackInfo ci) {
        if (SpeedrunnerMod.options().main.doomMode) {
            if (attacker instanceof GiantEntity) {
                this.getItemCooldownManager().set(Items.SHIELD, 200);
                this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 180);
                this.clearActiveItem();
                this.world.sendEntityStatus(this, (byte)30);
            }
        }
    }
}