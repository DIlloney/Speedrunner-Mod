package net.dillon.speedrunnermod.util;

import net.minecraft.util.SignType;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * {@link net.dillon.speedrunnermod.SpeedrunnerMod} sign types.
 */
public class ModSignTypes {
    public static final SignType SPEEDRUNNER = SignType.register(new SignType("speedrunner"));

    public static void init() {
        info("Initialized speedrunner sign type.");
    }
}