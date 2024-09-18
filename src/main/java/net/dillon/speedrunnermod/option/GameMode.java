package net.dillon.speedrunnermod.option;

import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * All the different {@code GameMode} options.
 */
public enum GameMode implements TranslatableOption {
    SURVIVAL(0, "speedrunnermod.options.gamemode.survival"),
    CREATIVE(1, "speedrunnermod.options.gamemode.creative"),
    HARDCORE(2, "speedrunnermod.options.gamemode.hardcore"),
    SPECTATOR(3, "speedrunnermod.options.gamemode.spectator");

    private static final GameMode[] VALUES = Arrays.stream(GameMode.values()).sorted(Comparator.comparingInt(GameMode::getId)).toArray(GameMode[]::new);
    private final int id;
    private final String translateKey;

    GameMode(int id, String translationKey) {
        this.id = id;
        this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
    }

    /**
     * Returns the {@code id value} of the fast world creation {@code GameMode} option.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Returns the {@code translation key} of the {@code GameMode} option.
     */
    @Override
    public String getTranslationKey() {
        return this.translateKey;
    }

    /**
     * Not sure what this does to be honest, but it's used in {@link ModListOptions}.
     */
    public static GameMode byId(int id) {
        return VALUES[MathHelper.floorMod(id, VALUES.length)];
    }

    /**
     * Returns true if the {@code GameMode} option is safe to run.
     */
    public boolean isSafe() {
        return options().client.gameMode.equals(SURVIVAL) ||
                options().client.gameMode.equals(CREATIVE) ||
                options().client.gameMode.equals(HARDCORE) ||
                options().client.gameMode.equals(SPECTATOR);
    }
}