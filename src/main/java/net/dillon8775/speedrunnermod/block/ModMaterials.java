package net.dillon8775.speedrunnermod.block;

import net.minecraft.block.MapColor;
import net.minecraft.block.Material;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

/**
 * <p>Materials for the {@link net.dillon8775.speedrunnermod.SpeedrunnerMod}.</p>
 * <p>Fixes speedrunner wood burning.</p>
 */
public class ModMaterials {

    public static final Material SPEEDRUNNER_WOOD = new Material.Builder(MapColor.OAK_TAN).build();

    public static void init() {
        info("Initialized mod block materials.");
    }
}