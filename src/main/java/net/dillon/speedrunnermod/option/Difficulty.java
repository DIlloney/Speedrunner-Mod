package net.dillon.speedrunnermod.option;

import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * All the different {@code Difficulty} options.
 */
public enum Difficulty implements TranslatableOption {
    PEACEFUL(0, "speedrunnermod.options.difficulty.peaceful"),
    EASY(1, "speedrunnermod.options.difficulty.easy"),
    NORMAL(2, "speedrunnermod.options.difficulty.normal"),
    HARD(3, "speedrunnermod.options.difficulty.hard");

    private static final Difficulty[] VALUES = Arrays.stream(Difficulty.values()).sorted(Comparator.comparingInt(Difficulty::getId)).toArray(Difficulty[]::new);
    private final int id;
    private final String translateKey;

    Difficulty(int id, String translationKey) {
        this.id = id;
        this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
    }

    /**
     * Returns the {@code id value} of the fast world creation {@code Difficulty} option.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Returns the {@code translation key} of the {@code Difficulty} option.
     */
    @Override
    public String getTranslationKey() {
        return this.translateKey;
    }

    /**
     * Not sure what this does to be honest, but it's used in {@link ModListOptions}.
     */
    public static Difficulty byId(int id) {
        return VALUES[MathHelper.floorMod(id, VALUES.length)];
    }

    /**
     * Returns true if the {@code Difficulty} option is safe to run.
     */
    public boolean isSafe() {
        return options().client.difficulty.equals(PEACEFUL) ||
                options().client.difficulty.equals(EASY) ||
                options().client.difficulty.equals(NORMAL) ||
                options().client.difficulty.equals(HARD);
    }
}