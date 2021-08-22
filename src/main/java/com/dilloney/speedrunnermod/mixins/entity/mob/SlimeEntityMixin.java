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

    @Deprecated
    protected SlimeEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Changes how much a Slime can jump based on Mod Difficulty.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public int getTicksUntilNextJump() {
        if (OPTIONS.doomMode) {
            return 20;
        }  else if (OPTIONS.getModDifficulty() == 1) {
            return 100;
        } else if (OPTIONS.getModDifficulty() == 2) {
            return 80;
        } else if (OPTIONS.getModDifficulty() == 3) {
            return 60;
        } else {
            return OPTIONS.getModDifficulty() == 4 ? 40 : 100;
        }
    }
}