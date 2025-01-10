package net.dillon.speedrunnermod.util;

/**
 * Helper methods for converting normal time into Minecraft ticks.
 */
public class TickCalculator {

    /**
     * Converts seconds to ticks.
     */
    public static int seconds(int seconds) {
        return seconds * 20;
    }

    /**
     * Converts minutes to ticks.
     */
    public static int minutes(int minutes) {
        return (minutes * 60) * 20;
    }
}