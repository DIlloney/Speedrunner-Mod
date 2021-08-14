package com.dilloney.speedrunnermod.mixins.misc;

import net.minecraft.world.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin {

    @Shadow int minSpawnDelay, maxSpawnDelay;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeSpawnDelays(CallbackInfo callbackInfo) {
        this.minSpawnDelay = 200;
        this.maxSpawnDelay = 400;
    }
}