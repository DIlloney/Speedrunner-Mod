package net.dilloney.speedrunnermod.option;

import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class ModOptions {

    public boolean makeStructuresMoreCommon = true;
    public boolean makeBiomesMoreCommon = true;
    public boolean iCarusMode = false;
    public boolean infiniPearlMode = false;
    public boolean fog = true;
    public boolean timer = false;
    public boolean doomMode = false;
    public boolean killGhastUponFireball = false;
    public int strongholdCount = 128;
    public int dragonPerchTime = 30;
    public boolean autoCreateWorld = true;
    public WorldDifficulty worldDifficulty = WorldDifficulty.EASY;

    protected boolean getMakeStructuresMoreCommon() {
        return this.makeStructuresMoreCommon;
    }

    protected boolean getMakeBiomesMoreCommon() {
        return this.makeBiomesMoreCommon;
    }

    protected boolean getiCarusMode() {
        return this.iCarusMode;
    }

    protected boolean getInfinityPearlMode() {
        return this.infiniPearlMode;
    }

    protected boolean getFog() {
        return this.fog;
    }

    protected boolean getTimer() {
        return this.timer;
    }

    protected boolean getDoomMode() {
        return this.doomMode;
    }

    protected boolean getKillGhastUponFireball() {
        return this.killGhastUponFireball;
    }

    public int getStrongholdCount() {
        return this.strongholdCount;
    }

    public int getDragonPerchTime() {
        return this.dragonPerchTime;
    }

    protected boolean getAutoCreateWorld() {
        return this.autoCreateWorld;
    }

    protected void setMakeStructuresMoreCommon(boolean makeStructuresMoreCommon) {
        this.makeStructuresMoreCommon = makeStructuresMoreCommon;
    }

    protected void setMakeBiomesMoreCommon(boolean makeBiomesMoreCommon) {
        this.makeBiomesMoreCommon = makeBiomesMoreCommon;
    }

    protected void setiCarusMode(boolean iCarusMode) {
        this.iCarusMode = iCarusMode;
    }

    protected void setInfiniPearlMode(boolean infiniPearlMode) {
        this.infiniPearlMode = infiniPearlMode;
    }

    protected void setFog(boolean fog) {
        this.fog = fog;
    }

    protected void setTimer(boolean timer) {
        this.timer = timer;
    }

    protected void setDoomMode(boolean doomMode) {
        this.doomMode = doomMode;
    }

    protected void setKillGhastUponFireball(boolean killGhastUponFireball) {
        this.killGhastUponFireball = killGhastUponFireball;
    }

    public void setStrongholdCount(int strongholdCount) {
        this.strongholdCount = strongholdCount;
    }

    protected void setDragonPerchTime(int dragonPerchTime) {
        this.dragonPerchTime = dragonPerchTime;
    }

    protected void setAutoCreateWorld(boolean autoCreateWorld) {
        this.autoCreateWorld = autoCreateWorld;
    }

    public enum WorldDifficulty {
        PEACEFUL(0, "options.difficulty.peaceful"),
        EASY(1, "options.difficulty.easy"),
        NORMAL(2, "options.difficulty.normal"),
        HARD(3, "options.difficulty.hard"),
        HARDCORE(4, "options.difficulty.hardcore");

        private static final WorldDifficulty[] VALUES = (WorldDifficulty[])Arrays.stream(values()).sorted(Comparator.comparingInt(WorldDifficulty::getId)).toArray((i) -> {
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

        public static WorldDifficulty byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }
}