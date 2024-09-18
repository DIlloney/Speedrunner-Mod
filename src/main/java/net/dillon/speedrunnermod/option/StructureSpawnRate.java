package net.dillon.speedrunnermod.option;

import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * All the different {@code Structure Spawn Rate} options, from extremely common to extremely rare.
 */
public enum StructureSpawnRate implements TranslatableOption {
    EVERYWHERE(0, "speedrunnermod.options.structure_spawn_rates.everywhere"),
    VERY_COMMON(1, "speedrunnermod.options.structure_spawn_rates.very_common"),
    COMMON(2, "speedrunnermod.options.structure_spawn_rates.common"),
    NORMAL(3, "speedrunnermod.options.structure_spawn_rates.normal"),
    DEFAULT(4, "speedrunnermod.options.structure_spawn_rates.default"),
    RARE(5, "speedrunnermod.options.structure_spawn_rates.rare"),
    VERY_RARE(6, "speedrunnermod.options.structure_spawn_rates.very_rare"),
    CUSTOM(7, "speedrunnermod.options.structure_spawn_rates.custom"),
    DISABLED(8, "speedrunnermod.options.structure_spawn_rates.disabled");

    private static final StructureSpawnRate[] VALUES = Arrays.stream(StructureSpawnRate.values()).sorted(Comparator.comparingInt(StructureSpawnRate::getId)).toArray(StructureSpawnRate[]::new);
    private final int id;
    private final String translateKey;

    StructureSpawnRate(int id, String translationKey) {
        this.id = id;
        this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
    }

    /**
     * Returns the {@code id value} of the {@code Structure Spawn Rate} option.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Returns the {@code translation key} of the {@code Structure Spawn Rate} option.
     */
    @Override
    public String getTranslationKey() {
        return this.translateKey;
    }

    /**
     * Not sure what this does to be honest, but it's used in {@link ModListOptions}.
     */
    public static StructureSpawnRate byId(int id) {
        return VALUES[MathHelper.floorMod(id, VALUES.length)];
    }

    /**
     * Returns true if the {@code Structure Spawn Rate} option is safe.
     * <p>Going into the configuration file and setting the option to an invalid string, will crash the game.</p>
     */
    public boolean isSafe() {
        return options().main.structureSpawnRates.equals(EVERYWHERE) ||
                options().main.structureSpawnRates.equals(VERY_COMMON) ||
                options().main.structureSpawnRates.equals(COMMON) ||
                options().main.structureSpawnRates.equals(NORMAL) ||
                options().main.structureSpawnRates.equals(DEFAULT) ||
                options().main.structureSpawnRates.equals(RARE) ||
                options().main.structureSpawnRates.equals(VERY_RARE) ||
                options().main.structureSpawnRates.equals(CUSTOM) ||
                options().main.structureSpawnRates.equals(DISABLED);
    }

    /**
     * Returns the {@code Everywhere} structure spawn rate option.
     */
    public boolean everywhere() {
        return options().main.structureSpawnRates.equals(StructureSpawnRate.EVERYWHERE);
    }

    /**
     * Returns the {@code Very Common} structure spawn rate option.
     */
    public boolean veryCommon() {
        return options().main.structureSpawnRates.equals(StructureSpawnRate.VERY_COMMON);
    }

    /**
     * Returns the {@code Very Common} or {@code Common} structure spawn rate option.
     */
    public boolean veryCommonOrCommon() {
        return options().main.structureSpawnRates.equals(StructureSpawnRate.VERY_COMMON) || options().main.structureSpawnRates.equals(COMMON);
    }

    /**
     * Returns the {@code Common} structure spawn rate option.
     */
    public boolean common() {
        return options().main.structureSpawnRates.equals(StructureSpawnRate.COMMON);
    }

    /**
     * Returns the {@code Normal} structure spawn rate option.
     */
    public boolean normal() {
        return options().main.structureSpawnRates.equals(StructureSpawnRate.NORMAL);
    }

    /**
     * Returns the {@code Default} structure spawn rate option,
     */
    public boolean ddefault() {
        return options().main.structureSpawnRates.equals(StructureSpawnRate.DEFAULT);
    }

    /**
     * Returns the {@code Normal} or {@code Default} structure spawn rate option.
     */
    public boolean commonNormalOrDefault() {
        return options().main.structureSpawnRates.equals(StructureSpawnRate.COMMON) || options().main.structureSpawnRates.equals(StructureSpawnRate.NORMAL) || options().main.structureSpawnRates.equals(StructureSpawnRate.DEFAULT);
    }

    /**
     * Returns the {@code Rare} structure spawn rate option.
     */
    public boolean rare() {
        return options().main.structureSpawnRates.equals(StructureSpawnRate.RARE);
    }

    /**
     * Returns the {@code Very Rare} structure spawn rate option.
     */
    public boolean veryRare() {
        return options().main.structureSpawnRates.equals(StructureSpawnRate.VERY_RARE);
    }

    /**
     * Returns the {@code Custom} structure spawn rate option.
     */
    public boolean custom() {
        return options().main.structureSpawnRates.equals(StructureSpawnRate.CUSTOM);
    }
}