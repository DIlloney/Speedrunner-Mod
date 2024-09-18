package net.dillon.speedrunnermod.option;

import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * All the different {@code Mob Spawning Rate} options.
 */
public enum MobSpawningRate implements TranslatableOption {
    LOW(0, "speedrunnermod.options.mob_spawning_rate.low"),
    NORMAL(1, "speedrunnermod.options.mob_spawning_rate.normal"),
    HIGH(2, "speedrunnermod.options.mob_spawning_rate.high");

    private static final MobSpawningRate[] VALUES = Arrays.stream(MobSpawningRate.values()).sorted(Comparator.comparingInt(MobSpawningRate::getId)).toArray(MobSpawningRate[]::new);
    private final int id;
    private final String translateKey;

    MobSpawningRate(int id, String translationKey) {
        this.id = id;
        this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
    }

    /**
     * Returns the {@code id value} of the {@code Mob Spawning Rate} option.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Returns the {@code translation key} of the {@code Mob Spawning Rate} option.
     */
    @Override
    public String getTranslationKey() {
        return this.translateKey;
    }

    /**
     * Not sure what this does to be honest, but it's used in {@link ModListOptions}.
     */
    public static MobSpawningRate byId(int id) {
        return VALUES[MathHelper.floorMod(id, VALUES.length)];
    }
}