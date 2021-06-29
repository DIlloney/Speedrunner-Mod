package com.dilloney.speedrunnermod.mixins.misc;

import net.minecraft.util.collection.WeightedPicker;
import net.minecraft.world.MobSpawnerEntry;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin {

    @Shadow int spawnDelay;

    @Shadow @Final List<MobSpawnerEntry> spawnPotentials;

    @Shadow MobSpawnerEntry spawnEntry;

    @Shadow abstract void sendStatus(int status);

    @Shadow abstract World getWorld();

    int minSpawnDelayMixin = 200;
    int maxSpawnDelayMixin = 400;

    @Overwrite
    private void updateSpawns() {
        if (this.maxSpawnDelayMixin <= this.minSpawnDelayMixin) {
            this.spawnDelay = this.minSpawnDelayMixin;
        } else {
            int var10003 = this.maxSpawnDelayMixin - this.minSpawnDelayMixin;
            this.spawnDelay = this.minSpawnDelayMixin + this.getWorld().random.nextInt(var10003);
        }

        if (!this.spawnPotentials.isEmpty()) {
            this.setSpawnEntry((MobSpawnerEntry) WeightedPicker.getRandom(this.getWorld().random, this.spawnPotentials));
        }

        this.sendStatus(1);
    }

    public void setSpawnEntry(MobSpawnerEntry spawnEntry) {
        this.spawnEntry = spawnEntry;
    }
}