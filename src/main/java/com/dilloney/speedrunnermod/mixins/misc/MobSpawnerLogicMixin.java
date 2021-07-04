package com.dilloney.speedrunnermod.mixins.misc;

import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerEntry;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin {

    @Shadow int spawnDelay;

    @Shadow Pool<MobSpawnerEntry> spawnPotentials;

    @Shadow MobSpawnerEntry spawnEntry;

    @Shadow @Final Random random;

    @Shadow abstract void sendStatus(World world, BlockPos pos, int i);

    @Shadow abstract boolean isPlayerInRange(World world, BlockPos pos);

    int minSpawnDelayMixin = 200;
    int maxSpawnDelayMixin = 400;

    @Overwrite
    private void updateSpawns(World world, BlockPos pos) {
        if (this.maxSpawnDelayMixin <= this.minSpawnDelayMixin) {
            this.spawnDelay = this.minSpawnDelayMixin;
        } else {
            this.spawnDelay = this.minSpawnDelayMixin + this.random.nextInt(this.maxSpawnDelayMixin - this.minSpawnDelayMixin);
        }

        this.spawnPotentials.getOrEmpty(this.random).ifPresent((mobSpawnerEntry) -> {
            this.setSpawnEntry(world, pos, mobSpawnerEntry);
        });
        this.sendStatus(world, pos, 1);
    }

    public void setSpawnEntry(@Nullable World world, BlockPos pos, MobSpawnerEntry spawnEntry) {
        this.spawnEntry = spawnEntry;
    }
}