package com.dilloney.speedrunnermod.config;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ModConfigLoader {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILENAME = "speedrunnermod-config.json";
    private static File file;

    public static void load() {
        File file = getFile();
        if (!file.exists()) {
            SpeedrunnerMod.CONFIG = new DefaultModConfig();
        } else {
            read();
        }
        save();
    }

    private static void read() {
        SpeedrunnerMod.CONFIG = get();
    }

    public static void save() {
        File file = getFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(SpeedrunnerMod.CONFIG));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void set(DefaultModConfig config) {
        SpeedrunnerMod.CONFIG = config;
        save();
    }

    public static DefaultModConfig get() {
        File file = getFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, DefaultModConfig.class);
        } catch (Exception e) {
            DefaultModConfig newconfig = new DefaultModConfig();
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
