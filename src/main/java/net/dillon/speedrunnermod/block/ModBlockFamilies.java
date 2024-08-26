package net.dillon.speedrunnermod.block;

import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * The {@code speedrunner mod} block family.
 * <p>See data generators for more.</p>
 */
public class ModBlockFamilies {
    public static final BlockFamily SPEEDRUNNER_FAMILY = BlockFamilies.register(ModBlocks.SPEEDRUNNER_PLANKS)
            .sign(ModBlocks.SPEEDRUNNER_SIGN, ModBlocks.SPEEDRUNNER_WALL_SIGN)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static final BlockFamily DEAD_SPEEDRUNNER_FAMILY = BlockFamilies.register(ModBlocks.DEAD_SPEEDRUNNER_PLANKS)
            .sign(ModBlocks.DEAD_SPEEDRUNNER_SIGN, ModBlocks.DEAD_SPEEDRUNNER_WALL_SIGN)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static void init() {
        info("Initialized the speedrunner wood block family.");
    }
}