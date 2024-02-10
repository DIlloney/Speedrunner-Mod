package net.dillon8775.speedrunnermod.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
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

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.*;
import static net.dillon8775.speedrunnermod.option.ModOptions.doNothing;
import static net.dillon8775.speedrunnermod.option.ModOptions.isSafe;

/**
 * <p>All the client-side options for the {@code Speedrunner Mod}.</p>
 * <p>See {@link ModOptions} for more.</p>
 */
@Environment(EnvType.CLIENT)
public class ClientModOptions {
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static final String CONFIG = "speedrunnermod-client-options.json";
    private static final String MOD_KEYBINDS = "speedrunnermod.keybinds";
    private static File file;
    public static ClientModOptions CLIENT_OPTIONS = getClientConfig();
    public boolean fog = true;
    public boolean itemTooltips = true;
    public boolean blockParticles = false;
    public Panorama panorama = Panorama.SPEEDRUNNER_MOD;
    public ItemMessages itemMessages = ItemMessages.CHAT;
    public ModButtonType modButtonType = ModButtonType.LOGO;
    public boolean socialButtons = true;
    private static File old_client_config_file;
    private static final String OLD_CLIENT_CONFIG = "speedrunnermod-cloptions.json";
    public final WorldSettings worldSettings = new WorldSettings();
    public static KeyBinding resetKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.reset", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, MOD_KEYBINDS));
    public static KeyBinding fogKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.toggle_fog", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, MOD_KEYBINDS));

    public static class WorldSettings {
        public boolean fastWorldCreation = true;
        public GameMode gameMode = GameMode.SURVIVAL;
        public Difficulty difficulty = Difficulty.EASY;
        public boolean allowCheats = false;
    }

    public enum Panorama {
        SPEEDRUNNER_MOD(0, "speedrunnermod.options.panorama.speedrunner_mod"),
        NIGHT(1, "speedrunnermod.options.panorama.night"),
        CAVE(2, "speedrunnermod.options.panorama.cave"),
        CLASSIC(3, "speedrunnermod.options.panorama.classic"),
        EMPTY(4, "speedrunnermod.options.panorama.empty"),
        DEFAULT(5, "speedrunnermod.options.panorama.default");

        private static final Panorama[] VALUES = (Panorama[])Arrays.stream(values()).sorted(Comparator.comparingInt(Panorama::getId)).toArray((i) -> {
            return new Panorama[i];
        });
        private final int id;
        private final String translateKey;

        Panorama(int id, String translationKey) {
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

    public static class Broken {
        public static boolean isPanoramaBroken = false;
        public static boolean isItemMessagesBroken = false;
        public static boolean isModButtonTypeBroken = false;
        public static boolean isGameModeBroken = false;
        public static boolean isDifficultyBroken = false;
    }

    public static boolean isActionbar() {
        return SpeedrunnerModClient.clientOptions().itemMessages == ItemMessages.ACTIONBAR;
    }

    public static void loadClientConfig() {
        File file = getClientConfigFile();
        File oldClientConfigFile = getOldClientConfigFile();
        if (oldClientConfigFile.exists()) {
            oldClientConfigFile.delete();
            warn("Found an old configuration file, deleting.");
        }

        if (!file.exists()) {
            CLIENT_OPTIONS = new ClientModOptions();
            info("Creating speedrunner mod client options file...");
        } else {
            info("Reading speedrunner mod client options...");
            safeCheck();
            readClientConfig();
        }
        saveClientConfig();
    }

    private static void readClientConfig() {
        CLIENT_OPTIONS = getClientConfig();
    }

    public static void saveClientConfig() {
        File file = getClientConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(SpeedrunnerModClient.clientOptions()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setClientConfig(ClientModOptions config) {
        CLIENT_OPTIONS = config;
        saveClientConfig();
    }

    private static ClientModOptions getClientConfig() {
        File file = getClientConfigFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, ClientModOptions.class);
        } catch (Exception e) {
            ClientModOptions newconfig = new ClientModOptions();
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

    /**
     * Runs a safe check on client options.
     */
    @Deprecated
    private static void safeCheck() {
        final String space = " ";
        final String pertaining = "Pertaining to: ";
        final String related = space + pertaining;
        if (SpeedrunnerModClient.clientOptions().panorama == Panorama.SPEEDRUNNER_MOD ||
                SpeedrunnerModClient.clientOptions().panorama == Panorama.NIGHT ||
                SpeedrunnerModClient.clientOptions().panorama == Panorama.CAVE ||
                SpeedrunnerModClient.clientOptions().panorama == Panorama.CLASSIC ||
                SpeedrunnerModClient.clientOptions().panorama == Panorama.EMPTY ||
                SpeedrunnerModClient.clientOptions().panorama == Panorama.DEFAULT) {
            doNothing();
        } else {
            error(SpeedrunnerMod.OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.panorama");
            isSafe(false);
            Broken.isPanoramaBroken = true;
        }

        if (SpeedrunnerModClient.clientOptions().itemMessages == ItemMessages.ACTIONBAR ||
                SpeedrunnerModClient.clientOptions().itemMessages == ItemMessages.CHAT) {
            doNothing();
        } else {
            error(SpeedrunnerMod.OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.itemMessages");
            isSafe(false);
            Broken.isItemMessagesBroken = true;
        }

        if (SpeedrunnerModClient.clientOptions().modButtonType == ModButtonType.LOGO ||
                SpeedrunnerModClient.clientOptions().modButtonType == ModButtonType.BUTTON) {
            doNothing();
        } else {
            error(SpeedrunnerMod.OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.modButtonType");
            isSafe(false);
            Broken.isModButtonTypeBroken = true;
        }

        if (SpeedrunnerModClient.clientOptions().worldSettings.gameMode == GameMode.SURVIVAL ||
                SpeedrunnerModClient.clientOptions().worldSettings.gameMode == GameMode.CREATIVE ||
                SpeedrunnerModClient.clientOptions().worldSettings.gameMode == GameMode.SPECTATOR ||
                SpeedrunnerModClient.clientOptions().worldSettings.gameMode == GameMode.HARDCORE) {
            doNothing();
        } else {
            error(SpeedrunnerMod.OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.gamemode");
            isSafe(false);
            Broken.isGameModeBroken = true;
        }

        if (SpeedrunnerModClient.clientOptions().worldSettings.difficulty == Difficulty.PEACEFUL ||
                SpeedrunnerModClient.clientOptions().worldSettings.difficulty == Difficulty.EASY ||
                SpeedrunnerModClient.clientOptions().worldSettings.difficulty == Difficulty.NORMAL ||
                SpeedrunnerModClient.clientOptions().worldSettings.difficulty == Difficulty.HARD) {
            doNothing();
        } else {
            error(SpeedrunnerMod.OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.difficulty");
            isSafe(false);
            Broken.isDifficultyBroken = true;
        }
    }

    private static File getOldClientConfigFile() {
        if (old_client_config_file == null) {
            old_client_config_file = new File(FabricLoader.getInstance().getConfigDir().toFile(), OLD_CLIENT_CONFIG);
        }
        return old_client_config_file;
    }
}