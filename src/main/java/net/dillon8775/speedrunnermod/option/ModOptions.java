package net.dillon8775.speedrunnermod.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.gui.screen.SafeBootScreen;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.*;

/**
 * The main options for the {@code Speedrunner Mod}.
 * <p>When adding new options...</p>
 * <p></p>
 * <p>Must add a check for restart required in {@link net.dillon8775.speedrunnermod.client.gui.screen.RestartRequiredScreen},</p>
 * <p>An {@code "isBroken"} check {@link SafeBootScreen} and in {@link ModOptions#safeCheck()}</p>
 * <p>A {@link net.dillon8775.speedrunnermod.client.gui.screen.options.ModListOptions},</p>
 * <p>A reset option in {@link net.dillon8775.speedrunnermod.client.gui.screen.ResetOptionsConfirmScreen}.</p>
 */
public class ModOptions {
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static final String CONFIG = "speedrunnermod-options.json";
    private static File file;
    public static ModOptions OPTIONS = getConfig();
    private static final String OLD_CONFIG = "speedrunnermod_options.json";
    private static File old_config_file;
    public final Main main = new Main();
    public final Advanced advanced = new Advanced();

    public static class Main {
        public StructureSpawnRate structureSpawnRate = StructureSpawnRate.COMMON;
        public boolean fasterBlockBreaking = true;
        public boolean betterBiomes = true;
        public boolean iCarusMode = false;
        public boolean infiniPearlMode = false;
        public boolean doomMode = false;
        public int dragonPerchTime = 30;
        public boolean killGhastOnFireball = false;
        public boolean fireproofItems = true;
        public int maxItemCount = 64;

        public enum StructureSpawnRate {
            VERY_COMMON(0, "speedrunnermod.options.structure_spawn_rate.very_common"),
            COMMON(1, "speedrunnermod.options.structure_spawn_rate.common"),
            NORMAL(2, "speedrunnermod.options.structure_spawn_rate.normal"),
            UNCOMMON(3, "speedrunnermod.options.structure_spawn_rate.uncommon"),
            RARE(3, "speedrunnermod.options.structure_spawn_rate.rare"),
            VERY_RARE(3, "speedrunnermod.options.structure_spawn_rate.very_rare");

            private static final StructureSpawnRate[] VALUES = (StructureSpawnRate[])Arrays.stream(values()).sorted(Comparator.comparingInt(StructureSpawnRate::getId)).toArray((i) -> {
                return new StructureSpawnRate[i];
            });
            private final int id;
            private final String translateKey;

            StructureSpawnRate(int id, String translationKey) {
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

    public static class Advanced {
        public boolean generateSpeedrunnerTrees = true;
        public boolean customBiomes = true;
        public boolean commonOres = true;
        public boolean allowBoatsInLava = true;
        public boolean allowWaterInNether = true;
        public boolean betterFoods = true;
        public int strongholdDistance = 4;
        public int strongholdSpread = 3;
        public int strongholdCount = 128;
        public MobSpawningRate mobSpawningRate = MobSpawningRate.HIGH;
        public int mobSpawnerMinimumSpawnDuration = 20;
        public int mobSpawnerMaximumSpawnDuration = 40;
        public int netherPortalCooldown = 2;
        public boolean globalNetherPortals = false;
        public boolean betterAnvil = true;
        public int anvilCostLimit = 40;
        public boolean higherEnchantmentLevels = true;
        public int playerBreathTime = 4;
        public int catchBreathTime = 4;

        public enum MobSpawningRate {
            LOW(0, "speedrunnermod.options.mob_spawning_rate.low"),
            NORMAL(1, "speedrunnermod.options.mob_spawning_rate.normal"),
            HIGH(2, "speedrunnermod.options.mob_spawning_rate.high");

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
        }
    }

    public static class Broken {
        public static boolean isStructureSpawnRateBroken = false;
        public static boolean isMaxItemCountBroken = false;
        public static boolean isMobSpawningRateBroken = false;
    }

    public static void loadConfig() {
        File configFile = getConfigFile();
        File oldConfigFile = getOldConfigFile();
        if (oldConfigFile.exists()) {
            oldConfigFile.delete();
            warn("Found an old configuration file, deleting.");
        }

        if (!configFile.exists()) {
            OPTIONS = new ModOptions();
            info("Creating speedrunner mod options file...");
        } else {
            info("Reading speedrunner mod options...");
            safeCheck();
            readConfig();
        }
        saveConfig();
    }

    private static void readConfig() {
        OPTIONS = getConfig();
    }

    public static void saveConfig() {
        File file = getConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(SpeedrunnerMod.options()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setConfig(ModOptions config) {
        OPTIONS = config;
        saveConfig();
    }

    private static ModOptions getConfig() {
        File file = getConfigFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, ModOptions.class);
        } catch (Exception e) {
            ModOptions newconfig = new ModOptions();
            setConfig(newconfig);
            return newconfig;
        }
    }

    private static File getConfigFile() {
        if (file == null) {
            file = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG);
        }
        return file;
    }

    @Deprecated
    public static void isSafe(boolean safe) {
        SpeedrunnerMod.safeBoot = !safe;
    }

    @Deprecated
    protected static void doNothing() {
    }

    /**
     * Runs a "safe check" on all the speedrunner mod's main and advanced options, to make sure that there are no errors. If there are, Minecraft will boot into a "safe mode", to prevent any further errors or crashes.
     */
    @Deprecated
    private static void safeCheck() {
        final String space = " ";
        final String pertaining = "Pertaining to: ";
        final String related = space + pertaining;
        if (SpeedrunnerMod.options().main.structureSpawnRate == Main.StructureSpawnRate.VERY_COMMON ||
                SpeedrunnerMod.options().main.structureSpawnRate == Main.StructureSpawnRate.COMMON ||
                SpeedrunnerMod.options().main.structureSpawnRate == Main.StructureSpawnRate.NORMAL ||
                SpeedrunnerMod.options().main.structureSpawnRate == Main.StructureSpawnRate.UNCOMMON ||
                SpeedrunnerMod.options().main.structureSpawnRate == Main.StructureSpawnRate.RARE ||
                SpeedrunnerMod.options().main.structureSpawnRate == Main.StructureSpawnRate.VERY_RARE) {
            doNothing();
        } else {
            error(SpeedrunnerMod.OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.structureSpawnRate");
            isSafe(false);
            Broken.isStructureSpawnRateBroken = true;
        }

        if (SpeedrunnerMod.options().main.dragonPerchTime >= 21 &&
                SpeedrunnerMod.options().main.dragonPerchTime <= 90) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.dragonPerchTime");
        }

        if (SpeedrunnerMod.options().main.maxItemCount >= 4 &&
                SpeedrunnerMod.options().main.maxItemCount <= 1000) {
            doNothing();
        } else if (SpeedrunnerMod.options().main.maxItemCount < 1) {
            error(SpeedrunnerMod.OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.maxItemCount");
            isSafe(false);
            Broken.isMaxItemCountBroken = true;
        } else {
            doNothing();
        }

        if (SpeedrunnerMod.options().advanced.strongholdDistance >= 2 &&
                SpeedrunnerMod.options().advanced.strongholdDistance <= 32) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdDistance");
        }

        if (SpeedrunnerMod.options().advanced.strongholdSpread >= 2 &&
                SpeedrunnerMod.options().advanced.strongholdSpread <= 32) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdSpread");
        }

        if (SpeedrunnerMod.options().advanced.strongholdCount >= 64 &&
                SpeedrunnerMod.options().advanced.strongholdCount <= 128) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdCount");
        }

        if (SpeedrunnerMod.options().advanced.mobSpawningRate == Advanced.MobSpawningRate.LOW ||
                SpeedrunnerMod.options().advanced.mobSpawningRate == Advanced.MobSpawningRate.NORMAL ||
                SpeedrunnerMod.options().advanced.mobSpawningRate == Advanced.MobSpawningRate.HIGH) {
            doNothing();
        } else {
            error(SpeedrunnerMod.OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.mobSpawningRate");
            isSafe(false);
            Broken.isMobSpawningRateBroken = true;
        }

        if (SpeedrunnerMod.options().advanced.mobSpawnerMinimumSpawnDuration >= 10 &&
                SpeedrunnerMod.options().advanced.mobSpawnerMinimumSpawnDuration <= 40) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.mobSpawnerMinimumSpawnDuration");
        }

        if (SpeedrunnerMod.options().advanced.mobSpawnerMaximumSpawnDuration >= 40 &&
                SpeedrunnerMod.options().advanced.mobSpawnerMaximumSpawnDuration <= 80) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.mobSpawnerMaximumSpawnDuration");
        }

        if (SpeedrunnerMod.options().advanced.anvilCostLimit >= 1 &&
                SpeedrunnerMod.options().advanced.anvilCostLimit <= 50 ||
                SpeedrunnerMod.options().advanced.anvilCostLimit > 50) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.anvilCostLimit");
        }

        if (SpeedrunnerMod.options().advanced.netherPortalCooldown >= 0) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.netherPortalCooldown");
        }

        if (SpeedrunnerMod.options().advanced.playerBreathTime >= 4 &&
                SpeedrunnerMod.options().advanced.playerBreathTime <= 20) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.playerBreathTime");
        }
    }

    private static File getOldConfigFile() {
        if (old_config_file == null) {
            old_config_file = new File(FabricLoader.getInstance().getConfigDir().toFile(), OLD_CONFIG);
        }
        return old_config_file;
    }
}