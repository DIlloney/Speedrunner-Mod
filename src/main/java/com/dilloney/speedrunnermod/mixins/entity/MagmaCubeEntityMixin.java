package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MagmaCubeEntity.class)
public class MagmaCubeEntityMixin extends SlimeEntity {

    public MagmaCubeEntityMixin(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
    }

    @Overwrite
    public int getTicksUntilNextJump() {
        if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            return this.random.nextInt(60) + 120;
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
            return this.random.nextInt(30) + 40;
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
            return this.random.nextInt(20) + 10;
        } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
            return this.random.nextInt(10) + 5;
        } else {
            return this.random.nextInt(60) + 120;
        }
    }

    @Overwrite
    public float getDamageAmount() {
        if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 2.0F;
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
            return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 3.0F;
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
            return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 4.0F;
        } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
            return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 6.0F;
        } else {
            return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 2.0F;
        }
    }
}