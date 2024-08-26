package net.dillon.speedrunnermod.util;

import java.util.Random;

/**
 * Some helper math functions.
 */
public class MathUtil {

    /**
     * Rounds the inputted number to the nearest one decimal place.
     */
    public static double roundToOneDecimalPlace(double number) {
        return Math.round(number * 10.0D) / 10.0D;
    }

    /**
     * Returns a random float, with a minimum and maximum value.
     */
    public static float randomFloat(float min, float max) {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    /**
     * Multiplies the inputted number by itself.
     */
    public static int multiplyBySelf(int value) {
        return value * value;
    }
}