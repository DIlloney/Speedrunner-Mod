package net.dillon8775.speedrunnermod.sound;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * The {@link SpeedrunnerMod} sound effects.
 */
public class ModSoundEvents {
    public static final SoundEvent ENTITY_BOAT_PADDLE_LAVA = Registry.register(Registry.SOUND_EVENT, "entity.boat.paddle_lava", new SoundEvent(new Identifier(SpeedrunnerMod.MOD_ID, "entity.boat.paddle_lava")));
}