package net.dilloney.speedrunnermod.option;

import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class ModOptions {
    public final Main main = new Main();
    public final Advanced advanced = new Advanced();

    public static class Main {
        public boolean makeStructuresMoreCommon = true;
        public boolean makeBiomesMoreCommon = true;
        public boolean iCarusMode = false;
        public boolean infiniPearlMode = false;
        public boolean doomMode = false;
        public boolean modifiedBlockHardness = true;
        public boolean killGhastUponFireball = false;
        public int strongholdCount = 128;
        public int dragonPerchTime = 30;

        public boolean getMakeStructuresMoreCommon() {
            return this.makeStructuresMoreCommon;
        }

        public boolean getMakeBiomesMoreCommon() {
            return this.makeBiomesMoreCommon;
        }

        public boolean getiCarusMode() {
            return this.iCarusMode;
        }

        public boolean getInfinityPearlMode() {
            return this.infiniPearlMode;
        }

        public boolean getDoomMode() {
            return this.doomMode;
        }

        public boolean getModifiedBlockHardness() {
            return this.modifiedBlockHardness;
        }

        public boolean getKillGhastUponFireball() {
            return this.killGhastUponFireball;
        }

        public int getStrongholdCount() {
            return this.strongholdCount;
        }

        public int getDragonPerchTime() {
            return this.dragonPerchTime;
        }

        public void setMakeStructuresMoreCommon(boolean makeStructuresMoreCommon) {
            this.makeStructuresMoreCommon = makeStructuresMoreCommon;
        }

        public void setMakeBiomesMoreCommon(boolean makeBiomesMoreCommon) {
            this.makeBiomesMoreCommon = makeBiomesMoreCommon;
        }

        public void setiCarusMode(boolean iCarusMode) {
            this.iCarusMode = iCarusMode;
        }

        public void setInfiniPearlMode(boolean infiniPearlMode) {
            this.infiniPearlMode = infiniPearlMode;
        }

        public void setDoomMode(boolean doomMode) {
            this.doomMode = doomMode;
        }

        public void setModifiedBlockHardness(boolean modifiedBlockHardness) {
            this.modifiedBlockHardness = modifiedBlockHardness;
        }

        public void setKillGhastUponFireball(boolean killGhastUponFireball) {
            this.killGhastUponFireball = killGhastUponFireball;
        }

        public void setStrongholdCount(int strongholdCount) {
            this.strongholdCount = strongholdCount;
        }

        public void setDragonPerchTime(int dragonPerchTime) {
            this.dragonPerchTime = dragonPerchTime;
        }
    }

    public static class Advanced {

        public MobSpawningRate mobSpawningRate = MobSpawningRate.HIGH;
        public boolean modifyBiomes = true;
        public boolean commonOres = true;
        public int strongholdDistance = 4;
        public boolean combineFortressesAndBastions = false;

        public boolean getModifyBiomes() {
            return this.modifyBiomes;
        }

        public boolean getCommonOres() {
            return this.commonOres;
        }

        public boolean getCombineFortressesAndBastions() {
            return combineFortressesAndBastions;
        }

        public int getStrongholdDistance() {
            return this.strongholdDistance;
        }

        public void setModifyBiomes(boolean modifyBiomes) {
            this.modifyBiomes = modifyBiomes;
        }

        public void setCommonOres(boolean commonOres) {
            this.commonOres = commonOres;
        }

        public void setStrongholdDistance(int strongholdDistance) {
            this.strongholdDistance = strongholdDistance;
        }

        public void setCombineFortressesAndBastions(boolean combineFortressesAndBastions) {
            this.combineFortressesAndBastions = combineFortressesAndBastions;
        }

        public enum MobSpawningRate {
            LOW(0, "options.mob_spawning_rate.low"),
            NORMAL(1, "options.mob_spawning_rate.normal"),
            HIGH(2, "options.mob_spawning_rate.high");

            private static final MobSpawningRate[] VALUES = (MobSpawningRate[])Arrays.stream(values()).sorted(Comparator.comparingInt(MobSpawningRate::getId)).toArray((i) -> {
                return new MobSpawningRate[i];
            });
            private final int id;
            private final String translateKey;

            MobSpawningRate(int id, String translationKey) {
                this.id = id;
                this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
            }

            public int getId() {
                return this.id;
            }

            public String getTranslationKey() {
                return this.translateKey;
            }

            public static MobSpawningRate byId(int id) {
                return VALUES[MathHelper.floorMod(id, VALUES.length)];
            }
        }
    }
}