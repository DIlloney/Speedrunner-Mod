package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.world.ModWorldGen;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.structure.NetherFortressStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Changes monster spawning in nether fortresses, see {@link ModWorldGen}.
 */
@Mixin(NetherFortressStructure.class)
public class NetherFortressFeatureMixin {
    @Shadow
    private static final Pool<SpawnSettings.SpawnEntry> MONSTER_SPAWNS = ModWorldGen.NETHER_FORTRESS_MOB_SPAWNS;
}