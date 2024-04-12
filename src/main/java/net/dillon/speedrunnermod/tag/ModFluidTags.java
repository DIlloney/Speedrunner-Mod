package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * {@link SpeedrunnerMod} fluid tags.
 */
public class ModFluidTags {
    public static TagKey<Fluid> BOAT_SAFE_FLUIDS = TagKey.of(Registry.FLUID_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "boat_safe_fluids"));

    public static void init() {
        info("Registered fluid tags.");
    }
}