package com.dilloney.speedrunnermod.mixins.entity.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin extends MobEntity {

    protected SlimeEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Overwrite
    public int getTicksUntilNextJump() {
        if (OPTIONS.getModDifficulty() == 1) {
            return this.random.nextInt(60) + 120;
        } else if (OPTIONS.getModDifficulty() == 2) {
            return this.random.nextInt(40) + 60;
        } else if (OPTIONS.getModDifficulty() == 3) {
            return this.random.nextInt(30) + 30;
        } else {
            return OPTIONS.getModDifficulty() == 4 ? this.random.nextInt(20) + 20 : this.random.nextInt(60) + 120;
        }
    }
}