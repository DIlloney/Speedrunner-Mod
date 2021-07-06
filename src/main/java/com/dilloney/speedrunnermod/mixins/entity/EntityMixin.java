package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Entity.class)
public class EntityMixin {

    @Overwrite
    public void setOnFireFromLava() {
        Entity entity = (Entity)(Object)this;
        if (!entity.isFireImmune()) {
            if (SpeedrunnerMod.CONFIG.difficulty == 1) {
                entity.setOnFireFor(8);
                if (entity.damage(DamageSource.LAVA, 2.0F)) {
                    entity.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + entity.world.random.nextFloat() * 0.4F);
                }
            } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
                entity.setOnFireFor(10);
                if (entity.damage(DamageSource.LAVA, 3.0F)) {
                    entity.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + entity.world.random.nextFloat() * 0.4F);
                }
            } else if (SpeedrunnerMod.CONFIG.difficulty == 3 || SpeedrunnerMod.CONFIG.difficulty == 4) {
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