package com.dilloney.speedrunnermod.mixins.entity.mob;

import com.dilloney.speedrunnermod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(PiglinEntity.class)
public abstract class PiglinEntityMixin extends AbstractPiglinEntity {

    protected PiglinEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
        super(entityType, world);
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createPiglinAttributes() {
        if (OPTIONS.doomMode) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 32.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999951496356D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D);
        } else if (OPTIONS.getModDifficulty() == 1) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
        } else if (OPTIONS.getModDifficulty() == 2) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
        } else if (OPTIONS.getModDifficulty() == 3) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D);
        } else {
            return OPTIONS.getModDifficulty() == 4 ? HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 28.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D) : HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
        }
    }

    @Inject(method = "initEquipment", at = @At("HEAD"))
    private void givePiglinGoldenSpeedrunnerArmorOnSpawn(LocalDifficulty difficulty, CallbackInfo callbackInfo) {
        if (OPTIONS.doomMode && this.isAdult()) {
            this.equipStack(EquipmentSlot.HEAD, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_HELMET));
            this.equipStack(EquipmentSlot.CHEST, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE));
            this.equipStack(EquipmentSlot.LEGS, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS));
            this.equipStack(EquipmentSlot.FEET, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_BOOTS));
        }
    }

    @Overwrite
    private void equipAtChance(EquipmentSlot slot, ItemStack stack) {
        if (OPTIONS.getModDifficulty() == 1 || OPTIONS.getModDifficulty() == 2) {
            if (this.world.random.nextFloat() < 0.1F) {
                this.equipStack(slot, stack);
            }
        } else if (OPTIONS.getModDifficulty() >= 3) {
            if (this.world.random.nextFloat() < 0.3F) {
                this.equipStack(slot, stack);
            }
        }
    }
}