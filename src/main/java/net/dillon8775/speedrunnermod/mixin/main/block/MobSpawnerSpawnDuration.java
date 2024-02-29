package net.dillon8775.speedrunnermod.mixin.main.block;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.world.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Changes the max spawn delay for mobs to spawn according to the {@code Mob Spawner Max/Min Spawn Duration} option.
 */
@Mixin(value = MobSpawnerLogic.class, priority = 999)
public abstract class MobSpawnerSpawnDuration {
    @Shadow
    private int minSpawnDelay = SpeedrunnerMod.options().mobSpawnerMinimumSpawnDuration * 10;
    @Shadow
    private int maxSpawnDelay = SpeedrunnerMod.options().mobSpawnerMaximumSpawnDuration * 10;
}