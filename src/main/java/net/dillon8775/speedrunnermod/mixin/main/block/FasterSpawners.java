package net.dillon8775.speedrunnermod.mixin.main.block;

import net.minecraft.world.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * Changes the spawn delay for mobs to spawn.
 */
@Mixin(value = MobSpawnerLogic.class, priority = 999)
public class FasterSpawners {
    @Shadow
    private int maxSpawnDelay = options().main.fasterSpawners ? 400 : 800;
}