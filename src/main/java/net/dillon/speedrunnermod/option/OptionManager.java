package net.dillon.speedrunnermod.option;

import net.dillon.speedrunnermod.SpeedrunnerMod;

import java.util.ArrayList;
import java.util.List;

public class OptionManager {
    private static final List<BooleanOption> booleanOptions = new ArrayList<>();
    private static final List<IntegerOption> integerOptions = new ArrayList<>();
    private static final List<IntegerListOption> integerListOptions = new ArrayList<>();
    private static final List<DoubleOption> doubleOptions = new ArrayList<>();
    private static final List<DoubleListOption> doubleListOptions = new ArrayList<>();

    public static void addBooleanOption(BooleanOption booleanOption) {
        booleanOptions.add(booleanOption);
    }

    public static void addIntegerOption(IntegerOption integerOption) {
        if (integerOptions.size() <= 14) {
            integerOptions.add(integerOption);
            System.out.println("ADDED INTEGER OPTION" + integerOption.getDefaultValue());
        } else {
            System.out.println("NOPE!");
        }
    }

    public static void addIntegerListOption(IntegerListOption integerListOption) {
        integerListOptions.add(integerListOption);
    }

    public static void addDoubleOption(DoubleOption doubleOption) {
        doubleOptions.add(doubleOption);
    }

    public static void addDoubleListOption(DoubleListOption doubleListOption) {
        doubleListOptions.add(doubleListOption);
    }

    public static List<BooleanOption> getBooleanOptions() {
        return booleanOptions;
    }

    public static List<IntegerOption> getIntegerOptions() {
        return integerOptions;
    }

    public static List<IntegerListOption> getIntegerListOptions() {
        return integerListOptions;
    }

    public static List<DoubleOption> getDoubleOptions() {
        return doubleOptions;
    }

    public static List<DoubleListOption> getDoubleListOptions() {
        return doubleListOptions;
    }
}