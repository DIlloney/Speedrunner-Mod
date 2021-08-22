package com.dilloney.speedrunnermod.entity.ai.goal;

import com.dilloney.speedrunnermod.entity.SpeedrunnerCrossbowUser;
import com.dilloney.speedrunnermod.item.ModItems;
import com.dilloney.speedrunnermod.item.SpeedrunnerCrossbowItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.EnumSet;

public class SpeedrunnerCrossbowAttackGoal<T extends HostileEntity & RangedAttackMob & SpeedrunnerCrossbowUser> extends Goal {
    public static final UniformIntProvider COOLDOWN_RANGE = TimeHelper.betweenSeconds(1, 2);
    private final T actor;
    private SpeedrunnerCrossbowAttackGoal.Stage stage;
    private final double speed;
    private final float squaredRange;
    private int seeingTargetTicker;
    private int chargedTicksLeft;
    private int cooldown;

    public SpeedrunnerCrossbowAttackGoal(T actor, double speed, float range) {
        this.stage = SpeedrunnerCrossbowAttackGoal.Stage.UNCHARGED;
        this.actor = actor;
        this.speed = speed;
        this.squaredRange = range * range;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        return this.hasAliveTarget() && this.isEntityHoldingCrossbow();
    }

    private boolean isEntityHoldingCrossbow() {
        return this.actor.isHolding(ModItems.SPEEDRUNNER_CROSSBOW);
    }

    @Override
    public boolean shouldContinue() {
        return this.hasAliveTarget() && (this.canStart() || !this.actor.getNavigation().isIdle()) && this.isEntityHoldingCrossbow();
    }

    private boolean hasAliveTarget() {
        return this.actor.getTarget() != null && this.actor.getTarget().isAlive();
    }

    @Override
    public void stop() {
        super.stop();
        this.actor.setAttacking(false);
        this.actor.setTarget((LivingEntity)null);
        this.seeingTargetTicker = 0;
        if (this.actor.isUsingItem()) {
            this.actor.clearActiveItem();
            ((SpeedrunnerCrossbowUser)this.actor).setCharging0(false);
            CrossbowItem.setCharged(this.actor.getActiveItem(), false);
        }
    }

    @Override
    public void tick() {
        LivingEntity livingEntity = this.actor.getTarget();
        if (livingEntity != null) {
            boolean bl = this.actor.getVisibilityCache().canSee(livingEntity);
            boolean bl2 = this.seeingTargetTicker > 0;
            if (bl != bl2) {
                this.seeingTargetTicker = 0;
            }

            if (bl) {
                ++this.seeingTargetTicker;
            } else {
                --this.seeingTargetTicker;
            }

            double d = this.actor.squaredDistanceTo(livingEntity);
            boolean bl3 = (d > (double)this.squaredRange || this.seeingTargetTicker < 5) && this.chargedTicksLeft == 0;
            if (bl3) {
                --this.cooldown;
                if (this.cooldown <= 0) {
                    this.actor.getNavigation().startMovingTo(livingEntity, this.isUncharged() ? this.speed : this.speed * 0.5D);
                    this.cooldown = COOLDOWN_RANGE.get(this.actor.getRandom());
                }
            } else {
                this.cooldown = 0;
                this.actor.getNavigation().stop();
            }

            this.actor.getLookControl().lookAt(livingEntity, 30.0F, 30.0F);
            if (this.stage == SpeedrunnerCrossbowAttackGoal.Stage.UNCHARGED) {
                if (!bl3) {
                    this.actor.setCurrentHand(ProjectileUtil.getHandPossiblyHolding(this.actor, ModItems.SPEEDRUNNER_CROSSBOW));
                    this.stage = SpeedrunnerCrossbowAttackGoal.Stage.CHARGING;
                    ((SpeedrunnerCrossbowUser)this.actor).setCharging0(true);
                }
            } else if (this.stage == SpeedrunnerCrossbowAttackGoal.Stage.CHARGING) {
                if (!this.actor.isUsingItem()) {
                    this.stage = SpeedrunnerCrossbowAttackGoal.Stage.UNCHARGED;
                }

                int i = this.actor.getItemUseTime();
                ItemStack itemStack = this.actor.getActiveItem();
                if (i >= SpeedrunnerCrossbowItem.getPullTime(itemStack)) { // FIX THIS DUMBASS THING
                    this.actor.stopUsingItem();
                    this.stage = SpeedrunnerCrossbowAttackGoal.Stage.CHARGED;
                    this.chargedTicksLeft = 15 + this.actor.getRandom().nextInt(15);
                    ((SpeedrunnerCrossbowUser)this.actor).setCharging0(false);
                }
            } else if (this.stage == SpeedrunnerCrossbowAttackGoal.Stage.CHARGED) {
                --this.chargedTicksLeft;
                if (this.chargedTicksLeft == 0) {
                    this.stage = SpeedrunnerCrossbowAttackGoal.Stage.READY_TO_ATTACK;
                }
            } else if (this.stage == SpeedrunnerCrossbowAttackGoal.Stage.READY_TO_ATTACK && bl) {
                ((RangedAttackMob)this.actor).attack(livingEntity, 1.0F);
                ItemStack itemStack2 = this.actor.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this.actor, ModItems.SPEEDRUNNER_CROSSBOW));
                CrossbowItem.setCharged(itemStack2, false);
                this.stage = SpeedrunnerCrossbowAttackGoal.Stage.UNCHARGED;
            }
        }
    }

    private boolean isUncharged() {
        return this.stage == SpeedrunnerCrossbowAttackGoal.Stage.UNCHARGED;
    }

    public enum Stage {
        UNCHARGED,
        CHARGING,
        CHARGED,
        READY_TO_ATTACK;
    }
}