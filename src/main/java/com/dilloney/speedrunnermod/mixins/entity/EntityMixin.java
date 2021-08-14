package com.dilloney.speedrunnermod.mixins.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(Entity.class)
public class EntityMixin {

    @Overwrite
    public void setOnFireFromLava() {
        Entity entity = (Entity)(Object)this;
        if (!entity.isFireImmune()) {
            if (OPTIONS.doomMode) {
                entity.setOnFireFor(15);
                if (entity.damage(DamageSource.LAVA, 4.0F)) {
                    entity.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + entity.world.random.nextFloat() * 0.4F);
                }
            } else if (OPTIONS.getModDifficulty() == 1) {
                entity.setOnFireFor(7);
                if (entity.damage(DamageSource.LAVA, 2.0F)) {
                    entity.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + entity.world.random.nextFloat() * 0.4F);
                }
            } else if (OPTIONS.getModDifficulty() == 2) {
                entity.setOnFireFor(10);
                if (entity.damage(DamageSource.LAVA, 3.0F)) {
                    entity.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + entity.world.random.nextFloat() * 0.4F);
                }
            } else if (OPTIONS.getModDifficulty() >= 3) {
                entity.setOnFireFor(10);
                if (entity.damage(DamageSource.LAVA, 4.0F)) {
                    entity.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + entity.world.random.nextFloat() * 0.4F);
                }
            } else {
                entity.setOnFireFor(8);
                if (entity.damage(DamageSource.LAVA, 2.0F)) {
                    entity.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + entity.world.random.nextFloat() * 0.4F);
                }
            }
        }
    }
}