package com.dilloney.speedrunnermod.mixins.entity.mob.goal;

import net.minecraft.entity.mob.SilverfishEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SilverfishEntity.CallForHelpGoal.class)
public class SilverfishEntityCallForHelpGoalMixin {

    @Shadow int delay;

    @Overwrite
    public void onHurt() {
        if (this.delay == 0) {
            this.delay = 100;
        }
    }
}