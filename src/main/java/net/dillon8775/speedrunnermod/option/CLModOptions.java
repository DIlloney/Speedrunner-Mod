package net.dillon8775.speedrunnermod.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

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
    private static final String MOD_KEYBINDS = "speedrunnermod.keybinds";
    private static File file;
    public static CLModOptions CLOPTIONS = getClientConfig();
    public boolean fog = true;
    public boolean itemTooltips = true;
    public boolean fastWorldCreation = true;
    public GameMode gameMode = GameMode.SURVIVAL;
    public Difficulty difficulty = Difficulty.EASY;
    public boolean allowCheats = false;
    public boolean blockParticles = true;
    public ItemMessages itemMessages = ItemMessages.CHAT;
    public ModButtonType modButtonType = ModButtonType.LOGO;
    public boolean socialButtons = true;
    public static KeyBinding resetKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.reset", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, MOD_KEYBINDS));
    public static KeyBinding fogKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.toggle_fog", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, MOD_KEYBINDS));

    public enum GameMode {
        SURVIVAL(0, "speedrunnermod.options.gamemode.survival"),
        CREATIVE(1, "speedrunnermod.options.gamemode.creative"),
        HARDCORE(2, "speedrunnermod.options.gamemode.hardcore"),
        SPECTATOR(3, "speedrunnermod.options.gamemode.spectator");

        private static final GameMode[] VALUES = (GameMode[]) Arrays.stream(values()).sorted(Comparator.comparingInt(GameMode::getId)).toArray((i) -> {
            return new GameMode[i];
        });
        private final int id;
        private final String translateKey;

        GameMode(int id, String translationKey) {
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

    public enum Difficulty {
        PEACEFUL(0, "speedrunnermod.options.difficulty.peaceful"),
        EASY(1, "speedrunnermod.options.difficulty.easy"),
        NORMAL(2, "speedrunnermod.options.difficulty.normal"),
        HARD(3, "speedrunnermod.options.difficulty.hard");

        private static final Difficulty[] VALUES = (Difficulty[]) Arrays.stream(values()).sorted(Comparator.comparingInt(Difficulty::getId)).toArray((i) -> {
            return new Difficulty[i];
        });
        private final int id;
        private final String translateKey;

        Difficulty(int id, String translationKey) {
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
        LOGO(0, "speedrunnermod.options.mod_button_type.logo"),
        BUTTON(1, "speedrunnermod.options.mod_button_type.button");

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

    public enum ItemMessages {
        CHAT(0, "speedrunnermod.options.item_messages.chat"),
        ACTIONBAR(1, "speedrunnermod.options.item_messages.actionbar");

        private static final ItemMessages[] VALUES = (ItemMessages[]) Arrays.stream(values()).sorted(Comparator.comparingInt(ItemMessages::getId)).toArray((i) -> {
            return new ItemMessages[i];
        });
        private final int id;
        private final String translateKey;

        ItemMessages(int id, String translationKey) {
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

    public static boolean isActionbar() {
        return SpeedrunnerModClient.clOptions().itemMessages == ItemMessages.ACTIONBAR;
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
        if (SpeedrunnerModClient.clOptions().difficulty == null) {
            SpeedrunnerModClient.clOptions().difficulty = Difficulty.EASY;
        }

        if (SpeedrunnerModClient.clOptions().modButtonType == null) {
            SpeedrunnerModClient.clOptions().modButtonType = CLModOptions.ModButtonType.LOGO;
        }
    }
}