package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin extends MobEntity {

    protected SlimeEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
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
}