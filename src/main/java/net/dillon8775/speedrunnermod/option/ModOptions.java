package net.dillon8775.speedrunnermod.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.screen.SafeBootScreen;
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
 * <p>Must add a check for restart required in {@link net.dillon8775.speedrunnermod.client.screen.ResetOptionsConfirmScreen},</p>
 * <p>An {@code "isBroken"} check {@link SafeBootScreen} and in {@link ModOptions#safeCheck()}</p>
 * <p>A {@link ModListOptions},</p>
 * <p>A reset option in {@link net.dillon8775.speedrunnermod.client.screen.ResetOptionsConfirmScreen}.</p>
 */
public class ModOptions {
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static final String CONFIG = "speedrunnermod-options.json";
    private static File file;
    public static ModOptions OPTIONS = getConfig();
    private static final String OLD_CONFIG = "speedrunnermod_options.json";
    private static File old_config_file;
    // OPTIONS:
    @RequiresRestart(true)
    public StructureSpawnRates structureSpawnRates = StructureSpawnRates.COMMON;
    public boolean fasterBlockBreaking = true;
    @RequiresRestart(true)
    public boolean betterBiomes = true;
    public boolean iCarusMode = false;
    public boolean infiniPearlMode = false;
    @RequiresRestart(true)
    public boolean doomMode = false;
    public int dragonPerchTime = 30;
    public boolean killGhastOnFireball = false;
    @RequiresRestart(true)
    public boolean betterVillagerTrades = true;
    public boolean fireproofItems = true;
    @RequiresRestart(true)
    public boolean generateSpeedrunnerTrees = true;
    @RequiresRestart(true)
    public boolean customBiomes = true;
    @RequiresRestart(true)
    public boolean commonOres = true;
    public boolean lavaBoats = true;
    public boolean netherWater = true;
    public boolean betterFoods = true;
    public boolean fallDamage = true;
    @RequiresRestart(true)
    public int strongholdDistance = 4;
    @RequiresRestart(true)
    public int strongholdSpread = 3;
    @RequiresRestart(true)
    public int strongholdCount = 128;
    @RequiresRestart(true)
    public int strongholdPortalRoomCount = 3;
    @RequiresRestart(true)
    public int strongholdLibraryCount = 2;
    public MobSpawningRate mobSpawningRate = MobSpawningRate.HIGH;
    public int mobSpawnerMinimumSpawnDuration = 20;
    public int mobSpawnerMaximumSpawnDuration = 40;
    public int netherPortalCooldown = 2;
    public boolean globalNetherPortals = false;
    public boolean betterAnvil = true;
    public int anvilCostLimit = 40;
    public boolean higherEnchantmentLevels = true;
    public boolean higherBreathTime = true;

    public enum StructureSpawnRates {
        EVERYWHERE(0, "speedrunnermod.options.structure_spawn_rates.everywhere"),
        VERY_COMMON(1, "speedrunnermod.options.structure_spawn_rates.very_common"),
        COMMON(2, "speedrunnermod.options.structure_spawn_rates.common"),
        NORMAL(3, "speedrunnermod.options.structure_spawn_rates.normal"),
        DEFAULT(4, "speedrunnermod.options.structure_spawn_rates.default"),
        RARE(5, "speedrunnermod.options.structure_spawn_rates.rare"),
        VERY_RARE(6, "speedrunnermod.options.structure_spawn_rates.very_rare"),
        OFF(7, "speedrunnermod.options.structure_spawn_rates.off");

        private static final StructureSpawnRates[] VALUES = (StructureSpawnRates[])Arrays.stream(values()).sorted(Comparator.comparingInt(StructureSpawnRates::getId)).toArray((i) -> {
            return new StructureSpawnRates[i];
        });
        private final int id;
        private final String translateKey;

        StructureSpawnRates(int id, String translationKey) {
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

    public boolean isDragonPerchTimeOn() {
        return options().dragonPerchTime >= 10;
    }

    public int getDragonPerchTime() {
        return options().dragonPerchTime * 1000;
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
        if (options().structureSpawnRates == StructureSpawnRates.EVERYWHERE ||
                options().structureSpawnRates == StructureSpawnRates.VERY_COMMON ||
                options().structureSpawnRates == StructureSpawnRates.COMMON ||
                options().structureSpawnRates == StructureSpawnRates.NORMAL ||
                options().structureSpawnRates == StructureSpawnRates.DEFAULT ||
                options().structureSpawnRates == StructureSpawnRates.RARE ||
                options().structureSpawnRates == StructureSpawnRates.VERY_RARE ||
                options().structureSpawnRates == StructureSpawnRates.OFF) {
            doNothing();
        } else {
            error(SpeedrunnerMod.OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.structureSpawnRate");
            isSafe(false);
            BrokenModOptions.structureSpawnRates = true;
        }

        if (options().dragonPerchTime >= 10 &&
                options().dragonPerchTime <= 90) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.dragonPerchTime");
        }

        if (options().strongholdDistance >= 2 &&
                options().strongholdDistance <= 32) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdDistance");
        }

        if (options().strongholdSpread >= 2 &&
                options().strongholdSpread <= 32) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdSpread");
        }

        if (options().strongholdCount >= 64 &&
                options().strongholdCount <= 128) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdCount");
        }

        if (options().strongholdPortalRoomCount >= 1 &&
                options().strongholdPortalRoomCount <= 10) {
            doNothing();
        } else {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.strongholdPortalRoomCount");
            isSafe(false);
            BrokenModOptions.strongholdPortalRoomCount = true;
        }

        if (options().strongholdLibraryCount >= 1 &&
                options().strongholdLibraryCount <= 10) {
            doNothing();
        } else {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.strongholdLibraryCount");
            isSafe(false);
            BrokenModOptions.strongholdLibraryCount = true;
        }

        if (options().mobSpawningRate == MobSpawningRate.LOW ||
                options().mobSpawningRate == MobSpawningRate.NORMAL ||
                options().mobSpawningRate == MobSpawningRate.HIGH) {
            doNothing();
        } else {
            error(SpeedrunnerMod.OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.mobSpawningRate");
            isSafe(false);
            BrokenModOptions.mobSpawningRate = true;
        }

        if (options().mobSpawnerMinimumSpawnDuration >= 10 &&
                options().mobSpawnerMinimumSpawnDuration <= 40) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.mobSpawnerMinimumSpawnDuration");
        }

        if (options().mobSpawnerMaximumSpawnDuration >= 40 &&
                options().mobSpawnerMaximumSpawnDuration <= 80) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.mobSpawnerMaximumSpawnDuration");
        }

        if (options().anvilCostLimit >= 1 &&
                options().anvilCostLimit <= 50 ||
                options().anvilCostLimit > 50) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.anvilCostLimit");
        }

        if (options().netherPortalCooldown >= 0) {
            doNothing();
        } else {
            warn(SpeedrunnerMod.OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.netherPortalCooldown");
        }
    }

    private static File getOldConfigFile() {
        if (old_config_file == null) {
            old_config_file = new File(FabricLoader.getInstance().getConfigDir().toFile(), OLD_CONFIG);
        }
        return old_config_file;
    }
}