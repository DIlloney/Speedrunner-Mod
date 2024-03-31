package net.dillon8775.speedrunnermod.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.screen.SafeBootScreen;
import net.dillon8775.speedrunnermod.util.TimeCalculator;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.*;

/**
 * The main options for the {@code Speedrunner Mod}.
 * <p>When adding new options...</p>
 * <p>- Must add a check for restart required in {@link net.dillon8775.speedrunnermod.client.screen.RestartRequiredScreen}, only if necessary,</p>
 * <p>- Determine if it is leaderboard-eligible, and then implement that into {@link Leaderboards}.</p>
 * <p>- An {@code "isBroken"} check {@link SafeBootScreen} and in {@link ModOptions#safeCheck()}</p>
 * <p>- A {@link ModListOptions},</p>
 * <p>- A reset option in {@link SpeedrunnerMod#resetOptions()}.</p>
 */
public class ModOptions {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    public static final String CONFIG = "speedrunnermod-options.json";
    private static File file;
    public static ModOptions OPTIONS = getConfig();
    public final Main main = new Main();
    public final Client client = new Client();
    public final Advanced advanced = new Advanced();

    /**
     * {@code Main Speedrunner Mod options.}
     */
    public static class Main {
        @RequiresRestart
        public StructureSpawnRates structureSpawnRates = StructureSpawnRates.COMMON;
        public boolean fasterBlockBreaking = true;
        public int blockBreakingMultiplier = 1;
        @RequiresRestart
        public boolean betterBiomes = true;
        public boolean iCarusMode = false;
        public boolean infiniPearlMode = false;
        @RequiresRestart
        public boolean doomMode = false;
        public int dragonPerchTime = 8;
        @RequiresRestart
        public boolean leaderboardsMode = false;
        public boolean killGhastOnFireball = false;
        @RequiresRestart
        public boolean betterVillagerTrades = true;
        public boolean fireproofItems = true;
        @RequiresRestart
        public boolean customBiomes = true;
        @RequiresRestart
        public boolean commonOres = true;
        public boolean lavaBoats = true;
        public boolean netherWater = false;
        public boolean betterFoods = true;
        public boolean fallDamage = true;
        public boolean kineticDamage = true;
        @RequiresRestart
        public int strongholdDistance = 4;
        @RequiresRestart
        public int strongholdSpread = 3;
        @RequiresRestart
        public int strongholdCount = 128;
        @RequiresRestart
        public int strongholdPortalRoomCount = 3;
        @RequiresRestart
        public int strongholdLibraryCount = 2;
        public MobSpawningRate mobSpawningRate = MobSpawningRate.HIGH;
        public int netherPortalCooldown = 2;
        public boolean throwableFireballs = true;
        public boolean arrowsDestroyBeds = false;
        public boolean globalNetherPortals = true;
        public boolean betterAnvil = true;
        public int anvilCostLimit = 10;
        public boolean higherEnchantmentLevels = true;
        public boolean stackUnstackables = false;
        public boolean fasterSpawners = true;
    }

    /**
     * {@code Client-side Speedrunner Mod options.}
     * <p>Not labeled as {@code Environment Type Client} due to certain things breaking on the server-side.</p>
     */
    public static class Client {
        public boolean fog = true;
        public boolean itemTooltips = true;
        @RequiresRestart
        public Panorama panorama = Panorama.SPEEDRUNNER_MOD;
        public ItemMessages itemMessages = ItemMessages.ACTIONBAR;
        @RequiresRestart
        public boolean confirmMessages = false;
        public ModButtonType modButtonType = ModButtonType.LOGO;
        public boolean socialButtons = true;
        public boolean fastWorldCreation = true;
        public GameMode gameMode = GameMode.SURVIVAL;
        public Difficulty difficulty = Difficulty.EASY;
        public boolean allowCheats = false;
        public boolean showDeathCords = true;
    }

    /**
     * {@code Advanced Speedrunner Mod options.}
     * <p>Only accessible via the mod's configuration file.</p>
     */
    public static class Advanced {
        public boolean disableDragonsPearl = false;
        public boolean disableDragonsSword = false;
        public boolean disableEnderThruster = false;
        public boolean disablePiglinAwakener = false;
        public boolean disableBlazeSpotter = false;
        public boolean disableRaidEradicator = false;
        public boolean modifiedStrongholdGeneration = true;
        public boolean modifiedStrongholdYGeneration = true;
        public boolean modifiedNetherFortressGeneration = true;
        public boolean disableEyeOfAnnulPortalRoomTeleporter = false;
        public boolean enableEyeOfAnnulPortalRoomTeleporterOnDoomMode = true;
        public boolean removeSilkTouchWhenRightClick = true;
        public boolean fixSpeedrunnerEditionTextOffset = true;
        public boolean showResetButton = true;
        public boolean higherBreathTime = true;
        public boolean generateSpeedrunnerWood = true;
        public boolean longerDragonPerchStayTime = true;
        public boolean decreasedZombifiedPiglinScareDistance = true;
        public int eyeOfEnderBreakingCooldown = 60;
        public int piglinAwakenerPiglinCount = 10;
        public boolean teleportPiglinDirectlyTowardsPlayer = false;
        public int throwableFireballsExplosionPower = 1;
        public boolean dragonKillsNearbyHostileEntities = true;
        public boolean dragonImmunityFromGiantAndWither = true;
        public int[] annulEyePortalRoomDistanceXYZ = createListOption(-128, -128, -128, 128, 128, 128);
        public double[] piglinAwakenerPiglinDistanceXYZ = createListOption(100.0D, 100.0D, 100.0D);
        public int[] blazeSpotterDistanceXYZ = createListOption(-156, -72, -156, 156, 72, 156);
        public double[] raidEradicatorDistanceXYZ = createListOption(300.0D, 300.0D, 300.0D);
        public double[] dragonsPearlDragonDistanceXYZ = createListOption(150.0D, 150.0D, 150.0D);
        public double[] dragonKillsHostileEntitiesDistance = createListOption(200.0D, 200.0D, 200.0D);
        public double[] dragonImmunityDetectionDistanceForGiant = createListOption(200.0D, 200.0D, 200.0D);
        public double[] dragonImmunityDetectionDistanceForWither = createListOption(300.0D, 300.0D, 300.0D);
    }

    public static int[] createListOption(int negX, int negY, int negZ, int posX, int posY, int posZ) {
        return new int[]{negX, negY, negZ, posX, posY, posZ};
    }

    public static double[] createListOption(double posX, double posY, double posZ) {
        return new double[]{posX, posY, posZ};
    }

    public boolean isMobSpawningRateSafe() {
        return options().main.mobSpawningRate.equals(MobSpawningRate.LOW) ||
                options().main.mobSpawningRate.equals(MobSpawningRate.NORMAL) ||
                options().main.mobSpawningRate.equals(MobSpawningRate.HIGH);
    }

    public boolean isDragonPerchTimeValid() {
        return this.inBounds(main.dragonPerchTime, 8, 90);
    }

    public boolean isDragonPerchTimeOn() {
        return this.inBounds(main.dragonPerchTime, 10);
    }

    public boolean isInstantDragonPerchTime() {
        return options().main.dragonPerchTime == 9;
    }

    public int getDragonPerchTime() {
        return TimeCalculator.secondsToMilliseconds(options().main.dragonPerchTime);
    }

    public boolean isBlockBreakingMultiplierValid() {
        return this.inBounds(main.blockBreakingMultiplier, 1, 3);
    }

    public boolean isStrongholdDistanceValid() {
        return this.inBounds(main.strongholdDistance, 3, 64);
    }

    public boolean isStrongholdSpreadValid() {
        return this.inBounds(main.strongholdSpread, 2, 32);
    }

    public boolean isStrongholdCountValid() {
        return this.inBounds(main.strongholdCount, 4, 156);
    }

    public boolean isStrongholdPortalRoomCountValid() {
        return this.inBounds(main.strongholdPortalRoomCount, 0, 3);
    }

    public boolean isStrongholdLibraryCountValid() {
        return this.inBounds(main.strongholdLibraryCount, 1, 10);
    }

    public boolean isAnvilCostLimitValid() {
        return this.inBounds(main.anvilCostLimit, 1, 50);
    }

    public boolean isNetherPortalCooldownValid() {
        return this.inBounds(main.netherPortalCooldown, 0, 20);
    }

    public boolean isEyeOfEnderBreakingCooldownValid() {
        return this.inBounds(advanced.eyeOfEnderBreakingCooldown, 20, 200);
    }

    /**
     * Determines if a certain option is greater than or equal to said parameters.
     */
    public boolean inBounds(int option, int min) {
        return option >= min;
    }

    /**
     * Determines if a certain option is less than and greater than equal to said parameters.
     */
    public boolean inBounds(int option, int min, int max) {
        return option >= min && option <= max;
    }

    public enum StructureSpawnRates {
        EVERYWHERE("speedrunnermod.options.structure_spawn_rates.everywhere"),
        VERY_COMMON("speedrunnermod.options.structure_spawn_rates.very_common"),
        COMMON("speedrunnermod.options.structure_spawn_rates.common"),
        NORMAL("speedrunnermod.options.structure_spawn_rates.normal"),
        DEFAULT("speedrunnermod.options.structure_spawn_rates.default"),
        RARE("speedrunnermod.options.structure_spawn_rates.rare"),
        VERY_RARE("speedrunnermod.options.structure_spawn_rates.very_rare"),
        DISABLED("speedrunnermod.options.structure_spawn_rates.disabled");

        private final String translateKey;

        StructureSpawnRates(String translationKey) {
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public String getTranslationKey() {
            return this.translateKey;
        }

        public boolean isSafe() {
            return options().main.structureSpawnRates.equals(EVERYWHERE) ||
                    options().main.structureSpawnRates.equals(VERY_COMMON) ||
                    options().main.structureSpawnRates.equals(COMMON) ||
                    options().main.structureSpawnRates.equals(NORMAL) ||
                    options().main.structureSpawnRates.equals(DEFAULT) ||
                    options().main.structureSpawnRates.equals(RARE) ||
                    options().main.structureSpawnRates.equals(VERY_RARE) ||
                    options().main.structureSpawnRates.equals(DISABLED);
        }

        public boolean everywhere() {
            return options().main.structureSpawnRates.equals(StructureSpawnRates.EVERYWHERE);
        }

        public boolean veryCommon() {
            return options().main.structureSpawnRates.equals(StructureSpawnRates.VERY_COMMON);
        }

        public boolean common() {
            return options().main.structureSpawnRates.equals(StructureSpawnRates.COMMON);
        }

        public boolean normal() {
            return options().main.structureSpawnRates.equals(StructureSpawnRates.NORMAL);
        }

        public boolean ddefault() {
            return options().main.structureSpawnRates.equals(StructureSpawnRates.DEFAULT);
        }

        public boolean commonNormalOrDefault() {
            return options().main.structureSpawnRates.equals(StructureSpawnRates.COMMON) || options().main.structureSpawnRates.equals(StructureSpawnRates.NORMAL) || options().main.structureSpawnRates.equals(StructureSpawnRates.DEFAULT);
        }

        public boolean rare() {
            return options().main.structureSpawnRates.equals(StructureSpawnRates.RARE);
        }

        public boolean veryRare() {
            return options().main.structureSpawnRates.equals(StructureSpawnRates.VERY_RARE);
        }
    }

    public enum MobSpawningRate {
        LOW("speedrunnermod.options.mob_spawning_rate.low"),
        NORMAL("speedrunnermod.options.mob_spawning_rate.normal"),
        HIGH("speedrunnermod.options.mob_spawning_rate.high");

        private final String translateKey;

        MobSpawningRate(String translationKey) {
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public String getTranslationKey() {
            return this.translateKey;
        }
    }

    public enum Panorama {
        SPEEDRUNNER_MOD("speedrunnermod.options.panorama.speedrunner_mod"),
        EASIER_SPEEDRUNNING("speedrunnermod.options.panorama.easier_speedrunning"),
        NIGHT("speedrunnermod.options.panorama.night"),
        CAVE("speedrunnermod.options.panorama.cave"),
        CLASSIC("speedrunnermod.options.panorama.classic"),
        EMPTY("speedrunnermod.options.panorama.empty"),
        DEFAULT("speedrunnermod.options.panorama.default");

        private final String translateKey;

        Panorama(String translationKey) {
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public String getTranslationKey() {
            return this.translateKey;
        }

        public boolean isSafe() {
            return options().client.panorama.equals(SPEEDRUNNER_MOD) ||
                    options().client.panorama.equals(EASIER_SPEEDRUNNING) ||
                    options().client.panorama.equals(NIGHT) ||
                    options().client.panorama.equals(CAVE) ||
                    options().client.panorama.equals(CLASSIC) ||
                    options().client.panorama.equals(EMPTY) ||
                    options().client.panorama.equals(DEFAULT);
        }
    }

    public enum GameMode {
        SURVIVAL("speedrunnermod.options.gamemode.survival"),
        CREATIVE("speedrunnermod.options.gamemode.creative"),
        HARDCORE("speedrunnermod.options.gamemode.hardcore"),
        SPECTATOR("speedrunnermod.options.gamemode.spectator");

        private final String translateKey;

        GameMode(String translationKey) {
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public String getTranslationKey() {
            return this.translateKey;
        }

        public boolean isSafe() {
            return options().client.gameMode.equals(SURVIVAL) ||
                    options().client.gameMode.equals(CREATIVE) ||
                    options().client.gameMode.equals(HARDCORE) ||
                    options().client.gameMode.equals(SPECTATOR);
        }
    }

    public enum Difficulty {
        PEACEFUL("speedrunnermod.options.difficulty.peaceful"),
        EASY("speedrunnermod.options.difficulty.easy"),
        NORMAL("speedrunnermod.options.difficulty.normal"),
        HARD("speedrunnermod.options.difficulty.hard");

        private final String translateKey;

        Difficulty(String translationKey) {
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public String getTranslationKey() {
            return this.translateKey;
        }

        public boolean isSafe() {
            return options().client.difficulty.equals(PEACEFUL) ||
                    options().client.difficulty.equals(EASY) ||
                    options().client.difficulty.equals(NORMAL) ||
                    options().client.difficulty.equals(HARD);
        }
    }

    public enum ModButtonType {
        LOGO("speedrunnermod.options.mod_button_type.logo"),
        BUTTON("speedrunnermod.options.mod_button_type.button");

        private final String translateKey;

        ModButtonType(String translationKey) {
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public String getTranslationKey() {
            return this.translateKey;
        }

        public boolean isSafe() {
            return options().client.modButtonType.equals(LOGO) ||
                    options().client.modButtonType.equals(BUTTON);
        }
    }

    public enum ItemMessages {
        CHAT("speedrunnermod.options.item_messages.chat"),
        ACTIONBAR("speedrunnermod.options.item_messages.actionbar");

        private final String translateKey;

        ItemMessages(String translationKey) {
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        public String getTranslationKey() {
            return this.translateKey;
        }

        public boolean isSafe() {
            return options().client.itemMessages.equals(ACTIONBAR) ||
                    options().client.itemMessages.equals(CHAT);
        }

        public static boolean isActionbar() {
            return options().client.itemMessages.equals(ACTIONBAR);
        }
    }

    public static void loadConfig() {
        File configFile = getConfigFile();

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

    public static void readConfig() {
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

    public static void setConfig(ModOptions config) {
        OPTIONS = config;
        saveConfig();
    }

    public static ModOptions getConfig() {
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

    protected static void isSafe(boolean safe) {
        SpeedrunnerMod.safeBoot = !safe;
    }

    private static void safeCheck() {
        final String space = " ";
        final String pertaining = "Pertaining to: ";
        final String related = space + pertaining;

        if (!options().main.structureSpawnRates.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.structureSpawnRates");
            isSafe(false);
            BrokenModOptions.structureSpawnRates = true;
        }

        if (!options().isMobSpawningRateSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.mobSpawningRate");
            isSafe(false);
            BrokenModOptions.mobSpawningRate = true;
        }

        if (!options().client.panorama.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.panorama");
            isSafe(false);
            BrokenModOptions.panorama = true;
        }

        if (!options().client.itemMessages.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.itemMessages");
            isSafe(false);
            BrokenModOptions.itemMessages = true;
        }

        if (!options().client.modButtonType.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.modButtonType");
            isSafe(false);
            BrokenModOptions.modButtonType = true;
        }

        if (!options().client.gameMode.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.gameMode");
            isSafe(false);
            BrokenModOptions.gameMode = true;
        }

        if (!options().client.difficulty.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.difficulty");
            isSafe(false);
            BrokenModOptions.difficulty = true;
        }

        if (options().main.netherPortalCooldown < 0) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.netherPortalCooldown");
            isSafe(false);
            BrokenModOptions.netherPortalCooldown = true;
        } else if (!options().isNetherPortalCooldownValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.netherPortalCooldown");
        }

        if (options().main.strongholdPortalRoomCount < 1) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.strongholdPortalRoomCount");
            isSafe(false);
            BrokenModOptions.strongholdPortalRoomCount = true;
        } else if (!options().isStrongholdPortalRoomCountValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdPortalRoomCount");
        }

        if (options().main.blockBreakingMultiplier < 0) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.blockBreakingMultiplier");
            isSafe(false);
            BrokenModOptions.blockBreakingMultiplier = true;
            warn("Cannot divide by zero! o_0");
        } else if (!options().isBlockBreakingMultiplierValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.blockBreakingMultiplier");
        }

        if (!options().isStrongholdLibraryCountValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdLibraryCount");
        }

        if (!options().isStrongholdDistanceValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdDistance");
        }

        if (!options().isStrongholdSpreadValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdSpread");
        }

        if (!options().isStrongholdCountValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdCount");
        }

        if (!options().isDragonPerchTimeValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.dragonPerchTime");
        }

        if (!options().isAnvilCostLimitValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.anvilCostLimit");
        }

        if (!options().isEyeOfEnderBreakingCooldownValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.eyeOfEnderBreakingCooldown");
        }
    }
}