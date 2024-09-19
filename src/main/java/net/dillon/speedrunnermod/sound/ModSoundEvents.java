package net.dillon.speedrunnermod.sound;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

/**
 * All Speedrunner Mod {@code custom sounds.}
 */
public class ModSoundEvents {
    public static final SoundEvent ENTITY_BOAT_PADDLE_LAVA = of("entity.boat.paddle_lava");

    /**
     * Initializes all speedrunner mod {@code custom sounds.}
     */
    public static void initializeSoundEvents() {}

    /**
     * Registers a {@code sound event.}
     */
    private static SoundEvent of(String path) {
        return Registry.register(Registries.SOUND_EVENT, "speedrunnermod:" + path, SoundEvent.of(Identifier.of(SpeedrunnerMod.MOD_ID, path)));
    }
}