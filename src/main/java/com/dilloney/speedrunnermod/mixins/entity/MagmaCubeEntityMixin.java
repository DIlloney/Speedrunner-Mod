package com.dilloney.speedrunnermod.mixins.entity;

import net.minecraft.entity.EntityType;
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
        return this.random.nextInt(60) + 120;
    }

    @Overwrite
    public float getDamageAmount() {
        return 2.0F;
    }
}