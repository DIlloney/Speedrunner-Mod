package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * All Speedrunner Mod {@code fluid tags.}
 */
public class ModFluidTags {
    public static TagKey<Fluid> BOAT_SAFE_FLUIDS = of("boat_safe_fluids");

    /**
     * Registers a {@code fluid tag.}
     */
    private static TagKey<Fluid> of(String path) {
        return TagKey.of(RegistryKeys.FLUID, Identifier.of(SpeedrunnerMod.MOD_ID, path));
    }

    /**
     * Initializes all Speedrunner Mod {@code fluid tags.}
     */
    public static void initializeFluidTags() {
        info("Initialized fluid tags.");
    }
}