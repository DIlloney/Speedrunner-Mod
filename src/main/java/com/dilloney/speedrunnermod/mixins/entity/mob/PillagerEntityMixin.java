package com.dilloney.speedrunnermod.mixins.entity.mob;

import com.dilloney.speedrunnermod.entity.SpeedrunnerCrossbowUser;
import com.dilloney.speedrunnermod.entity.ai.goal.SpeedrunnerCrossbowAttackGoal;
import com.dilloney.speedrunnermod.item.ModItems;
import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(PillagerEntity.class)
public abstract class PillagerEntityMixin extends IllagerEntity implements CrossbowUser, InventoryOwner, SpeedrunnerCrossbowUser {

    @Deprecated
    protected PillagerEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow @Final static TrackedData<Boolean> CHARGING;
    @Shadow abstract boolean isCharging();

    @Inject(method = "initGoals", at = @At("TAIL"))
    public void allowPillagersToUseSpeedrunnerCrossbows(CallbackInfo callbackInfo) {
        this.goalSelector.add(3, new SpeedrunnerCrossbowAttackGoal(this, 1.0D, 8.0F));
    }

    /**
     * Changes the pillagers attributes.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createPillagerAttributes() {
        return OPTIONS.getModDifficulty() <= 2 ? HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D) : HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0D);
    }

    /**
     * Allows Pillagers to use speedrunner crossbows as a weapon.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public State getState() {
        if (this.isCharging()) {
            return State.CROSSBOW_CHARGE;
        } else if (this.isHolding((stack) -> {
            return stack.isIn(ModItemTags.CROSSBOWS);
        })) {
            return State.CROSSBOW_HOLD;
        } else {
            return this.isAttacking() ? State.ATTACKING : State.NEUTRAL;
        }
    }

    /**
     * Allows pillagers to spawn with speedrunner crossbows.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public void initEquipment(LocalDifficulty difficulty) {
        if (world.random.nextFloat() < 0.80F) {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
        } else {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ModItems.SPEEDRUNNER_CROSSBOW));
        }
    }

    @Redirect(method = "enchantMainHandItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    public boolean allowSpeedrunnerCrossbowToBeEnchanted(ItemStack itemStack, Item item) {
        return itemStack.isIn(ModItemTags.CROSSBOWS);
    }

    @Inject(method = "attack", at = @At("TAIL"))
    public void allowPillagersToAttackWithSpeedrunnerCrossbows(LivingEntity target, float pullProgress, CallbackInfo callbackInfo) {
        this.shoot1(this, 1.8F);
    }

    @Inject(method = "shoot", at = @At("TAIL"))
    public void allowPillagersToShootSpeedrunnerCrossbows(LivingEntity target, ItemStack crossbow, ProjectileEntity projectile, float multiShotSpray, CallbackInfo callbackInfo) {
        this.shoot2(this, target, projectile, multiShotSpray, 1.8F);
    }

    /**
     * Allows Pillagers to use speedrunner crossbows as a weapon.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
        return weapon == Items.CROSSBOW || weapon == ModItems.SPEEDRUNNER_CROSSBOW;
    }

    @Override
    public void setCharging0(boolean charging) {
        this.dataTracker.set(CHARGING, charging);
    }

    @Override
    public void postShoot0() {
        this.despawnCounter = 0;
    }
}