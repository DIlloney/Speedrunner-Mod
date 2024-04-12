package net.dillon.speedrunnermod.client.screen.features;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * Determines the category of a {@code feature screen,} which also determines the maximum page number.
 */
@Environment(EnvType.CLIENT)
public enum ScreenCategories {
    BLOCKS_AND_ITEMS,
    TOOLS_AND_ARMOR,
    ORES_AND_WORLDGEN,
    MISCELLANEOUS,
    DOOM_MODE
}