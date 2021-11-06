package net.dilloney.speedrunnermod.util.timer.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Environment(EnvType.CLIENT)
public class DataStorage {

    static final class Data {
        public Runs runs = new Runs();
        public PersonalBest personalBest = new PersonalBest();
        public BestSplits bestSplits = new BestSplits();
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final Data data;
    private final File file;

    private DataStorage(File file, Data data) {
        this.file = file;
        this.data = data;
    }

    public static DataStorage of(File file) {
        Data initData = new Data();
        if (file.isFile()) {
            if (file.length() == 0) {
                file.delete();
                SpeedrunnerMod.LOGGER.warn("Deleted timer storage file, as it was blank. Restart your game to regenerate the file and make sure you load into a world.");
            }
            try (FileReader reader = new FileReader(file)) {
                initData = GSON.fromJson(reader, Data.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new DataStorage(file, initData);
    }

    public void persist() {
        if (data == null) return;
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Runs getRuns() {
        return this.data.runs;
    }

    public BestSplits getBestSplits() {
        return this.data.bestSplits;
    }

    public PersonalBest getPersonalBest() {
        return this.data.personalBest;
    }

    public void refreshBests(String ignoreKey) {
        getRuns().forEach((k, v) -> {
            if (k.equals(ignoreKey)) return;
            data.bestSplits.tryRun(v);
            data.personalBest.tryRun(v);
        });
    }
}