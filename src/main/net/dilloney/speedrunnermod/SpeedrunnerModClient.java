package net.dilloney.speedrunnermod;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dilloney.speedrunnermod.client.timer.Timer;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.option.clModOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static final String CONFIG = "speedrunnermod-cloptions.json";
    private static File file;
    private static clModOptions CLOPTIONS = getClientConfig();

    public void onInitializeClient() {
        loadClientConfig();

        ModItems.clinit();
        Timer.init();
    }

    private static void loadClientConfig() {
        File file = getClientConfigFile();
        if (!file.exists()) {
            SpeedrunnerModClient.CLOPTIONS = new clModOptions();
        } else {
            sanitize();
            readClientConfig();
        }
        saveClientConfig();
    }

    private static void readClientConfig() {
        SpeedrunnerModClient.CLOPTIONS = getClientConfig();
    }

    public static void saveClientConfig() {
        File file = getClientConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(SpeedrunnerModClient.clOptions()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setClientConfig(clModOptions config) {
        SpeedrunnerModClient.CLOPTIONS = config;
        saveClientConfig();
    }

    private static clModOptions getClientConfig() {
        File file = getClientConfigFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, clModOptions.class);
        } catch (Exception e) {
            clModOptions newconfig = new clModOptions();
            setClientConfig(newconfig);
            return newconfig;
        }
    }

    private static File getClientConfigFile() {
        if (file == null) {
            file = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG);
        }
        return file;
    }

    private static void sanitize() {
        if (SpeedrunnerModClient.clOptions().worldDifficulty == null) {
            SpeedrunnerModClient.clOptions().worldDifficulty = clModOptions.WorldDifficulty.EASY;
        }

        if (SpeedrunnerModClient.clOptions().modButtonType == null) {
            SpeedrunnerModClient.clOptions().modButtonType = clModOptions.ModButtonType.LOGO;
        }
    }

    public static clModOptions clOptions() {
        return CLOPTIONS;
    }
}