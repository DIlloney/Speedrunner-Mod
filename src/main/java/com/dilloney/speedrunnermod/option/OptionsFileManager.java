package com.dilloney.speedrunnermod.option;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

public class OptionsFileManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILENAME = "speedrunnermod-config.json";
    private static File file;

    public static void load() {
        File file = getFile();
        if (!file.exists()) {
            OPTIONS = new ModOptions();
        } else {
            read();
        }
        save();
    }

    private static void read() {
        OPTIONS = get();
    }

    public static void save() {
        File file = getFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(OPTIONS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void set(ModOptions config) {
        OPTIONS = config;
        save();
    }

    public static ModOptions get() {
        File file = getFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, ModOptions.class);
        } catch (Exception e) {
            ModOptions newconfig = new ModOptions();
            set(newconfig);
            return newconfig;
        }
    }

    private static File getFile() {
        if (file == null) {
            file = new File(FabricLoader.getInstance().getConfigDir().toFile(), FILENAME);
        }
        return file;
    }
}
