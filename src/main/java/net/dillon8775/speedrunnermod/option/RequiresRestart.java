package net.dillon8775.speedrunnermod.option;

/**
 * Specifies if a {@code speedrunner mod option} requires a restart for the option to take full effect.
 */
public @interface RequiresRestart {
    boolean value();
}