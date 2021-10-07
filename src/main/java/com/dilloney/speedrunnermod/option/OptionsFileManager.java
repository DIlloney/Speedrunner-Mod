package com.dilloney.speedrunnermod.option;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.*;

public class OptionsFileManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String MAIN_CONFIG = "speedrunnermod-config.json";
    private static final String WORLD_CONFIG = "speedrunnermod-config_world.json";
    private static final String MISC_CONFIG = "speedrunnermod-config_misc.json";
    private static File main_file;
    private static File world_file;
    private static File misc_file;

    public static void loadMain() {
        File file = getMainFile();
        if (!file.exists()) {
            OPTIONS = new ModOptions();
        } else {
            readMain();
        }
        saveMain();
    }

    public static void loadWorld() {
        File file = getWorldFile();
        if (!file.exists()) {
            WORLD_OPTIONS = new ModOptions.WorldOptions();
        } else {
            readWorld();
        }
        saveWorld();
    }

    public static void loadMisc() {
        File file = getMiscFile();
        if (!file.exists()) {
            MISC_OPTIONS = new ModOptions.MiscOptions();
        } else {
            readMisc();
        }
        saveMisc();
    }

    public static void loadAll() {
        if (MISC_OPTIONS.getStrongholdCount() < 64) {
            MISC_OPTIONS.setStrongholdCount(128);
            LOGGER.info("You cannot set the Stronghold Count below 64! Setting it to 128.");
            OptionsFileManager.saveMisc();
        }

        if (MISC_OPTIONS.getStrongholdCount() > 256) {
            MISC_OPTIONS.setStrongholdCount(128);
            LOGGER.info("You cannot set the Stronghold Count above 256! Setting it to 128.");
            OptionsFileManager.saveMisc();
        }
        loadMain();
        loadWorld();
        loadMisc();
    }

    private static void readMain() {
        OPTIONS = getMain();
    }

    private static void readWorld() {
        WORLD_OPTIONS = getWorld();
    }

    private static void readMisc() {
        MISC_OPTIONS = getMisc();
    }

    public static void saveMain() {
        File file = getMainFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(OPTIONS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveWorld() {
        File file = getWorldFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(WORLD_OPTIONS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveMisc() {
        File file = getMiscFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(MISC_OPTIONS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setMain(ModOptions config) {
        OPTIONS = config;
        saveMain();
    }

    public static void setWorld(ModOptions.WorldOptions config) {
        WORLD_OPTIONS = config;
        saveWorld();
    }

    public static void setMisc(ModOptions.MiscOptions config) {
        MISC_OPTIONS = config;
        saveMisc();
    }

    public static ModOptions getMain() {
        File file = getMainFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, ModOptions.class);
        } catch (Exception e) {
            ModOptions newconfig = new ModOptions();
            setMain(newconfig);
            return newconfig;
        }
    }

    public static ModOptions.WorldOptions getWorld() {
        File file = getWorldFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, ModOptions.WorldOptions.class);
        } catch (Exception e) {
            ModOptions.WorldOptions newconfig = new ModOptions.WorldOptions();
            setWorld(newconfig);
            return newconfig;
        }
    }

    public static ModOptions.MiscOptions getMisc() {
        File file = getMiscFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, ModOptions.MiscOptions.class);
        } catch (Exception e) {
            ModOptions.MiscOptions newconfig = new ModOptions.MiscOptions();
            setMisc(newconfig);
            return newconfig;
        }
    }

    private static File getMainFile() {
        if (main_file == null) {
            main_file = new File(FabricLoader.getInstance().getConfigDir().toFile(), MAIN_CONFIG);
        }
        return main_file;
    }

    private static File getWorldFile() {
        if (world_file == null) {
            world_file = new File(FabricLoader.getInstance().getConfigDir().toFile(), WORLD_CONFIG);
        }
        return world_file;
    }

    private static File getMiscFile() {
        if (misc_file == null) {
            misc_file = new File(FabricLoader.getInstance().getConfigDir().toFile(), MISC_CONFIG);
        }
        return misc_file;
    }
}
