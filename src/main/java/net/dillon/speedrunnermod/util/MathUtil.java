package net.dillon.speedrunnermod.util;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;

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
     * Returns the passed value divided by 2.
     */
    public static int divideByTwo(int value) {
        return value / 2;
    }

    /**
     * Returns a random float, with a minimum and maximum value.
     */
    public static float randomFloat(float min, float max) {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    /**
     * Multiplies the cost based on the enchantment levels.
     */
    public static int multiplyEnchantments(ItemEnchantmentsComponent.Builder enchantmentLevel, Object2IntMap.Entry<RegistryEntry<Enchantment>> entry, int totalTransferred) {
        return enchantmentLevel.getLevel(entry.getKey()) == 1 ? totalTransferred * totalTransferred : (totalTransferred * totalTransferred) + enchantmentLevel.getLevel(entry.getKey());
    }
}