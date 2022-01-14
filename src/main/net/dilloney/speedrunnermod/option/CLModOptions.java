package net.dilloney.speedrunnermod.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class CLModOptions {
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static final String CONFIG = "speedrunnermod-cloptions.json";
    private static File file;
    public static CLModOptions CLOPTIONS = getClientConfig();
    public boolean fog = true;
    public boolean autoCreateWorld = true;
    public WorldDifficulty worldDifficulty = WorldDifficulty.EASY;
    public boolean allowCheats = false;
    public ModButtonType modButtonType = ModButtonType.LOGO;

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

    public enum ModButtonType {
        LOGO(0, "options.mod_button_type.logo"),
        BUTTON(1, "options.mod_button_type.button");

        private static final ModButtonType[] VALUES = (ModButtonType[]) Arrays.stream(values()).sorted(Comparator.comparingInt(ModButtonType::getId)).toArray((i) -> {
            return new ModButtonType[i];
        });
        private final int id;
        private final String translateKey;

        ModButtonType(int id, String translationKey) {
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

    public static void loadClientConfig() {
        File file = getClientConfigFile();
        if (!file.exists()) {
            CLOPTIONS = new CLModOptions();
        } else {
            sanitize();
            readClientConfig();
        }
        saveClientConfig();
    }

    private static void readClientConfig() {
        CLOPTIONS = getClientConfig();
    }

    public static void saveClientConfig() {
        File file = getClientConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(SpeedrunnerModClient.clOptions()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setClientConfig(CLModOptions config) {
        CLOPTIONS = config;
        saveClientConfig();
    }

    private static CLModOptions getClientConfig() {
        File file = getClientConfigFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, CLModOptions.class);
        } catch (Exception e) {
            CLModOptions newconfig = new CLModOptions();
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
            SpeedrunnerModClient.clOptions().worldDifficulty = CLModOptions.WorldDifficulty.EASY;
        }

        if (SpeedrunnerModClient.clOptions().modButtonType == null) {
            SpeedrunnerModClient.clOptions().modButtonType = CLModOptions.ModButtonType.LOGO;
        }
    }
}