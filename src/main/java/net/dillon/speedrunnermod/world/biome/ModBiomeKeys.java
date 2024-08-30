package net.dillon.speedrunnermod.world.biome;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * All Speedrunner Mod {@code biome keys.}
 * <p>Note: These do not contain the biome's features, it just registers the biome itself. See {@link ModBiomes} for biome features.</p>
 */
public class ModBiomeKeys {
    public static final RegistryKey<Biome> SPEEDRUNNERS_WASTELAND_KEY = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(SpeedrunnerMod.MOD_ID, "speedrunners_wasteland"));

    /**
     * Initializes this class, registering the {@code Speedrunner's Wasteland} biome.
     */
    public static void init() {
        info("Initialized the Speedrunner's Wasteland biome.");
    }
}