package net.dilloney.speedrunnermod.option;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class clModOptions {

    public boolean fog = true;
    public boolean timer = false;
    public boolean autoCreateWorld = true;
    public WorldDifficulty worldDifficulty = WorldDifficulty.EASY;

    public boolean getFog() {
        return this.fog;
    }

    public boolean getTimer() {
        return this.timer;
    }

    public boolean getAutoCreateWorld() {
        return this.autoCreateWorld;
    }

    public void setFog(boolean fog) {
        this.fog = fog;
    }

    public void setTimer(boolean timer) {
        this.timer = timer;
    }

    public void setAutoCreateWorld(boolean autoCreateWorld) {
        this.autoCreateWorld = autoCreateWorld;
    }

    public enum WorldDifficulty {
        PEACEFUL(0, "options.difficulty.peaceful"),
        EASY(1, "options.difficulty.easy"),
        NORMAL(2, "options.difficulty.normal"),
        HARD(3, "options.difficulty.hard"),
        HARDCORE(4, "options.difficulty.hardcore");

        private static final WorldDifficulty[] VALUES = (WorldDifficulty[]) Arrays.stream(values()).sorted(Comparator.comparingInt(WorldDifficulty::getId)).toArray((i) -> {
            return new WorldDifficulty[i];
        });
        private final int id;
        private final String translateKey;

        WorldDifficulty(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public int getId() {
            return this.id;
        }

        public String getTranslationKey() {
            return this.translateKey;
        }
    }
}