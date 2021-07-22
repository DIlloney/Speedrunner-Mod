package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.dilloney.speedrunnermod.items.ModItems;
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

@Mixin(PiglinEntity.class)
public abstract class PiglinEntityMixin extends AbstractPiglinEntity {

    public PiglinEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
        super(entityType, world);
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createPiglinAttributes() {
        if (SpeedrunnerMod.CONFIG.doomMode) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 32.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999951496356D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 28.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D);
        } else {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
        }
    }

    @Inject(method = "initEquipment", at = @At("HEAD"))
    private void initEquipmentMod(LocalDifficulty difficulty, CallbackInfo callbackInfo) {
        if (SpeedrunnerMod.CONFIG.doomMode) {
            if (this.isAdult()) {
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_HELMET));
                this.equipStack(EquipmentSlot.CHEST, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE));
                this.equipStack(EquipmentSlot.LEGS, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS));
                this.equipStack(EquipmentSlot.FEET, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_BOOTS));
            }
        }
    }

    @Overwrite
    private void equipAtChance(EquipmentSlot slot, ItemStack stack) {
        if (SpeedrunnerMod.CONFIG.difficulty == 1 || SpeedrunnerMod.CONFIG.difficulty == 2) {
            if (this.world.random.nextFloat() < 0.1F) {
                this.equipStack(slot, stack);
            }
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3 || SpeedrunnerMod.CONFIG.difficulty == 4) {
            if (this.world.random.nextFloat() < 0.3F) {
                this.equipStack(slot, stack);
            }
        }
    }
}