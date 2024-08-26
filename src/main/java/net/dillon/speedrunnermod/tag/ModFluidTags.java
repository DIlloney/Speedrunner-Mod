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
    public static TagKey<Fluid> BOAT_SAFE_FLUIDS = TagKey.of(RegistryKeys.FLUID, Identifier.of(SpeedrunnerMod.MOD_ID, "boat_safe_fluids"));

    /**
     * Initializes all Speedrunner Mod {@code fluid tags.}
     */
    public static void init() {
        info("Registered fluid tags.");
    }
}