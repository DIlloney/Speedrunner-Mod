package net.dillon8775.speedrunnermod.tag;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ModFluidTags {
    public static Tag.Identified<Fluid> BOAT_SAFE_FLUIDS = TagFactory.FLUID.create(new Identifier(SpeedrunnerMod.MOD_ID, "boat_safe_fluids"));

    public static void init() {
    }
}