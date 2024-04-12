package net.dillon.speedrunnermod.client.screen.features;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * <p>Used for features screens.</p>
 * <p>STARTER = The first screen (or page) on a set screen category.
 * <p>FINAL = The last screen (or page) on a set screen category.
 * <p>NORMAL = Any screen that is not the first or last screen in a set screen category.</p>
 */
@Environment(EnvType.CLIENT)
public enum ScreenType {
    STARTER,
    NORMAL,
    FINAL
}