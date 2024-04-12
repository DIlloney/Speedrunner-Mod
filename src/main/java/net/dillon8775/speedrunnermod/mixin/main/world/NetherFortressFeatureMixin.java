package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.world.ModFeatures;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.structure.NetherFortressStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Changes monster spawning in nether fortresses, see {@link ModFeatures}.
 */
@Mixin(NetherFortressStructure.class)
public class NetherFortressFeatureMixin {
    @Shadow
    private static final Pool<SpawnSettings.SpawnEntry> MONSTER_SPAWNS = ModFeatures.NETHER_FORTRESS_MOB_SPAWNS;
}