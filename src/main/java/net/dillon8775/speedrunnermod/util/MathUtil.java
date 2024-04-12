<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.util;

import java.util.Random;

/**
 * Some math functions.
 */
public class MathUtil {

    public static double roundToOneDecimalPlace(double number) {
        return Math.round(number * 10.0D) / 10.0D;
    }

    public static float randomFloat(float min, float max) {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    public static int multiplyBySelf(int value) {
        return value * value;
    }
=======
package net.dillon8775.speedrunnermod.util;

import java.util.Random;

/**
 * Some math functions.
 */
public class MathUtil {

    public static double roundToOneDecimalPlace(double number) {
        return Math.round(number * 10.0D) / 10.0D;
    }

    public static float randomFloat(float min, float max) {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    public static int multiplyBySelf(int value) {
        return value * value;
    }
>>>>>>> Stashed changes
}