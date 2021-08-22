package com.dilloney.speedrunnermod.mixins.entity.mob;

import com.dilloney.speedrunnermod.entity.ai.goal.SpeedrunnerBowAttackGoal;
import com.dilloney.speedrunnermod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityMixin extends HostileEntity {

    @Deprecated
    protected AbstractSkeletonEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    private final SpeedrunnerBowAttackGoal<AbstractSkeletonEntity> speedrunnerBowAttackGoal = new SpeedrunnerBowAttackGoal(this, 1.0D, 20, 15.0F);
    @Shadow @Final BowAttackGoal<AbstractSkeletonEntity> bowAttackGoal;
    @Shadow @Final MeleeAttackGoal meleeAttackGoal;

    /**
     * Changes the Abstract Skeletons attributes.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createAbstractSkeletonAttributes() {
        return OPTIONS.doomMode ? HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D) : HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }

    /**
     * Allows Skeleton entities have a chance to spawn with a speedrunner bow.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public void initEquipment(LocalDifficulty difficulty) {
        super.initEquipment(difficulty);
        if (world.random.nextFloat() < 0.80F) {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        } else {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ModItems.SPEEDRUNNER_BOW));
        }
    }

    /**
     * Allows the Abstract Skeletons to be able to use speedrunner bows correctly.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public void updateAttackType() {
        if (this.world != null && !this.world.isClient) {
            this.goalSelector.remove(this.meleeAttackGoal);
            this.goalSelector.remove(this.bowAttackGoal);
            this.goalSelector.remove(this.speedrunnerBowAttackGoal);
            ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW));
            ItemStack itemStack1 = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, ModItems.SPEEDRUNNER_BOW));
            if (itemStack.isOf(Items.BOW)) {
                int i = 20;
                if (OPTIONS.doomMode) {
                    i = 10;
                } else if (this.world.getDifficulty() != Difficulty.HARD) {
                    i = 40;
                }

                this.bowAttackGoal.setAttackInterval(i);
                this.goalSelector.add(4, this.bowAttackGoal);
            } else if (itemStack1.isOf(ModItems.SPEEDRUNNER_BOW)) {
                int i = 15;
                if (OPTIONS.doomMode) {
                    i = 7;
                } else if (this.world.getDifficulty() != Difficulty.HARD) {
                    i = 30;
                }

                this.speedrunnerBowAttackGoal.setAttackInterval(i);
                this.goalSelector.add(4, this.speedrunnerBowAttackGoal);
            } else {
                this.goalSelector.add(4, this.meleeAttackGoal);
            }
        }
    }

    /**
     * Allows Abstract Skeletons to be able to use speedrunner bows.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
        return weapon == Items.BOW || weapon == ModItems.SPEEDRUNNER_BOW;
    }
}