package net.dillon.speedrunnermod.util;

/**
 * Saves trouble of having to calculate the seconds to milliseconds, etc., and instead just put in the amount of seconds using these methods.
 */
public class TimeCalculator {

    public static int secondsToMilliseconds(int seconds) {
        return seconds * 1000;
    }
}