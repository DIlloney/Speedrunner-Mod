package net.dillon8775.speedrunnermod.mixin.main.block;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.util.collection.DataPool;
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

@Mixin(value = MobSpawnerLogic.class, priority = 999)
public abstract class MobSpawnerMaxSpawnDuration {
    @Shadow
    private int spawnDelay;
    @Shadow
    private DataPool<MobSpawnerEntry> spawnPotentials;
    @Shadow @Final
    private Random random;
    @Shadow
    abstract void setSpawnEntry(@Nullable World world, BlockPos pos, MobSpawnerEntry spawnEntry);
    @Shadow
    abstract void sendStatus(World world, BlockPos pos, int i);
    int minSpawnDelayMixin = 200;
    int maxSpawnDelayMixin = SpeedrunnerMod.options().advanced.mobSpawnerMaxSpawnDuration * 10;

    /**
     * Changes the max spawn delay for mobs to spawn according to the {@code Mob Spawner Max Spawn Duration} option.
     */
    @Overwrite
    private void updateSpawns(World world, BlockPos pos) {
        if (this.maxSpawnDelayMixin <= this.minSpawnDelayMixin) {
            this.spawnDelay = this.minSpawnDelayMixin;
        } else {
            this.spawnDelay = this.minSpawnDelayMixin + this.random.nextInt(this.maxSpawnDelayMixin - this.minSpawnDelayMixin);
        }

        this.spawnPotentials.getOrEmpty(this.random).ifPresent((present) -> {
            this.setSpawnEntry(world, pos, (MobSpawnerEntry)present.getData());
        });
        this.sendStatus(world, pos, 1);
    }
}