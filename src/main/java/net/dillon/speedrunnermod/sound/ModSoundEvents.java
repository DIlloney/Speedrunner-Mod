package net.dillon.speedrunnermod.sound;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * The {@link SpeedrunnerMod} sound effects.
 */
public class ModSoundEvents {
    public static final SoundEvent ENTITY_BOAT_PADDLE_LAVA = Registry.register(Registry.SOUND_EVENT, "speedrunnermod:entity.boat.paddle_lava", new SoundEvent(new Identifier(SpeedrunnerMod.MOD_ID, "entity.boat.paddle_lava")));

    public static void init() {
        info("Initialized lava boat paddling sound.");
    }
}