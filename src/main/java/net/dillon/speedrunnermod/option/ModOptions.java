package net.dillon.speedrunnermod.option;

import com.google.gson.*;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.screen.SafeBootScreen;
import net.dillon.speedrunnermod.util.TimeCalculator;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static net.dillon.speedrunnermod.SpeedrunnerMod.*;

/**
 * All Speedrunner Mod {@code options.}
 * <p>When adding new options...</p>
 * <p>- Must add a check for restart required in {@link net.dillon.speedrunnermod.client.screen.RestartRequiredScreen}, only if necessary,</p>
 * <p>- Determine if it is leaderboard-eligible, and then implement into {@link Leaderboards}.</p>
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
    public final StateOfTheArtItems stateOfTheArtItems = new StateOfTheArtItems();
    public final Advanced advanced = new Advanced();
    public final StructureSpawnRates structureSpawnRates = new StructureSpawnRates();
    public final Mixins mixins = new Mixins();

    /**
     * All {@code Main Speedrunner Mod options.}
     * <p>See additional comments inside of static class for option documentation.</p>
     */
    public static class Main {

        /**
         * Determines how frequently Minecraft structures generate throughout the world.
         */
        public StructureSpawnRate structureSpawnRates = StructureSpawnRate.COMMON;

        /**
         * Allows certain blocks to be broken faster.
         */
        public BooleanOption fasterBlockBreaking = new BooleanOption(true);

        /**
         * The multiplier for how faster blocks can be broken.
         */
        public IntegerOption blockBreakingMultiplier = new IntegerOption(1);

        /**
         * Allows certain biomes, such as plains, deserts, savannas, etc., to generate more commonly.
         */
        @RequiresRestart
        public BooleanOption betterBiomes = new BooleanOption(true);

        /**
         * Grants the player with a pre-equipped unbreakable elytra and a stack of flight duration 3 firework rockets.
         */
        public BooleanOption iCarusMode = new BooleanOption(false);

        /**
         * Grants the player with an ender pearl that does not do damage nor get consumed upon use.
         */
        public BooleanOption infiniPearlMode = new BooleanOption(false);

        /**
         * Flips the mod's description upside down, and makes the game harder to speedrun.
         */
        @RequiresRestart
        public BooleanOption doomMode = new BooleanOption(false);

        /**
         *  Determines the amount of time (in seconds) that it takes for the ender dragon to automatically perch upon entering the end.
         *  <p>Note: {@code 8 = OFF, 9 = Instant.}</p>
         */
        public IntegerOption dragonPerchTime = new IntegerOption(8);

        /**
         * Instantly kills a ghast when they shoot a fireball.
         */
        public BooleanOption killGhastOnFireball = new BooleanOption(false);

        /**
         * Improves villager trades by making them less expensive and sell better stuff.
         */
        @RequiresRestart
        public BooleanOption betterVillagerTrades = new BooleanOption(true);

        /**
         * Allows certain items to be fireproof.
         */
        public BooleanOption fireproofItems = new BooleanOption(true);

        /**
         * Allows the Speedrunner's Wasteland biome to generate, and allows some additional worldgen features to be added to different biomes.
         */
        @RequiresRestart
        public BooleanOption customBiomesAndCustomBiomeFeatures = new BooleanOption(true);

        /**
         * Allows certain ores to generate more commonly.
         */
        public BooleanOption commonOres = new BooleanOption(true);

        /**
         * Allows certain boats to be fireproof.
         */
        public BooleanOption lavaBoats = new BooleanOption(true);

        /**
         * Allows water to be placed in the nether.
         */
        public BooleanOption netherWater = new BooleanOption(true);

        /**
         * Improves most vanilla food items to restore more hunger bars and give more saturation.
         */
        public BooleanOption betterFoods = new BooleanOption(true);

        /**
         * Enables/disables fall damage.
         */
        public BooleanOption fallDamage = new BooleanOption(true);

        /**
         * Enables/disables "kinetic" damage (damage taken when flying into walls with an elytra).
         */
        public BooleanOption kineticDamage = new BooleanOption(true);

        /**
         * Determines how far from spawn strongholds can generate.
         */
        @RequiresRestart
        public IntegerOption strongholdDistance = new IntegerOption(4);

        /**
         * Determines how far apart strongholds can generate from each other.
         */
        @RequiresRestart
        public IntegerOption strongholdSpread = new IntegerOption(3);

        /**
         * Determines the total amount of strongholds that can generate in a singular Minecraft world.
         */
        @RequiresRestart
        public IntegerOption strongholdCount = new IntegerOption(128);

        /**
         * Determines how many stronghold portal rooms can generate per stronghold.
         */
        @RequiresRestart
        public IntegerOption strongholdPortalRoomCount = new IntegerOption(3);

        /**
         * Determines how many libraries can generate per stronghold.
         */
        @RequiresRestart
        public IntegerOption strongholdLibraryCount = new IntegerOption(2);

        /**
         * Determines how big of packs mobs can spawn in.
         */
        public MobSpawningRate mobSpawningRate = MobSpawningRate.HIGH;

        /**
         * Sets the delay when entering/exiting the nether via a nether portal block.
         */
        public IntegerOption netherPortalDelay = new IntegerOption(2);

        /**
         * Allows fireballs to be thrown.
         */
        public BooleanOption throwableFireballs = new BooleanOption(true);

        /**
         * Allows arrows to blow up beds.
         */
        public BooleanOption arrowsDestroyBeds = new BooleanOption(true);

        /**
         * Allows nether portals to be built in the end.
         */
        public BooleanOption globalNetherPortals = new BooleanOption(true);

        /**
         * Removes the "too expensive" feature from anvils, and also lowers the maximum cost for block use.
         */
        public BooleanOption betterAnvil = new BooleanOption(true);

        /**
         * Sets the maximum cost that is allowed when using an anvil.
         * <p>If the cost goes above this value, the cost will instead be this value.</p>
         */
        public IntegerOption anvilCostLimit = new IntegerOption(10);

        /**
         * Allows the combination of two maximum level enchanted items to go above the enchantment level cap.
         */
        public BooleanOption higherEnchantmentLevels = new BooleanOption(true);

        /**
         * Allows the player to right-click on an ore block and remove the silk touch enchantment from their handheld item.
         */
        public BooleanOption rightClickToRemoveSilkTouch = new BooleanOption(true);

        /**
         * Increases the rate at which mobs spawn from spawner blocks.
         */
        public BooleanOption fasterSpawners = new BooleanOption(true);

        /**
         * This allows all world modifications to be applied, which includes making structures more common, modifying mob/creature spawn rates, doom mode features, and more.
         */
        public BooleanOption customDataGeneration = new BooleanOption(true);

        /**
         * Enables leaderboard mode, and applies the leaderboard checks and settings to the mod.
         */
        @RequiresRestart @Deprecated
        public BooleanOption leaderboardsMode = new BooleanOption(false);
    }

    /**
     * All {@code Client-side Speedrunner Mod options.}
     * <p>Not labeled as {@code environment-type client} due to certain things breaking on the server-side.</p>
     */
    public static class Client {

        /**
         * Enable/disable Minecraft's default fog.
         */
        public BooleanOption fog = new BooleanOption(true);

        /**
         * Enables/disables fullbright.
         */
        public BooleanOption fullBright = new BooleanOption(false);

        /**
         * Applies certain tooltips to certain items.
         */
        public BooleanOption itemTooltips = new BooleanOption(true);

        /**
         * Puts the creator name of a certain texture on the specified item.
         */
        public BooleanOption textureTooltips = new BooleanOption(false);

        /**
         * Enable/disable the Speedrunner Mod's custom panorama.
         */
        @RequiresRestart
        public BooleanOption customPanorama = new BooleanOption(true);

        /**
         * Determines whether certain player messages should be sent to the player's chat or actionbar (the area above the hotbar).
         */
        public ItemMessages itemMessages = ItemMessages.ACTIONBAR;

        /**
         * Enable/disable the confirmation messages when using certain items (ex. eye of annul, ender thruster, piglin awakener, etc.).
         */
        @RequiresRestart
        public BooleanOption confirmMessages = new BooleanOption(true);

        /**
         * Display the external link buttons across different screens.
         */
        public BooleanOption socialButtons = new BooleanOption(false);

        /**
         * Create a new world with just one click.
         */
        public BooleanOption fastWorldCreation = new BooleanOption(true);

        /**
         * Determines the gamemode they every new world should generate with.
         */
        public GameMode gameMode = GameMode.SURVIVAL;

        /**
         * Determines the difficulty that every new world should generate with.
         */
        public Difficulty difficulty = Difficulty.EASY;

        /**
         * Allows cheats when a new world is created.
         */
        public BooleanOption allowCheats = new BooleanOption(false);

        /**
         * Sends the players coordinates to chat upon death, and displays them on the death screen.
         */
        public BooleanOption showDeathCords = new BooleanOption(true);
    }

    /**
     * {@code State-Of-The-Art items} config.
     * <p>Allows for toggling of individual item functions.</p>
     */
    public static class StateOfTheArtItems {

        /**
         * Allows the user to enable/disable the functions of "State of the Art" items, which are items that may be considered "broken" (as in crazy, not actually broken), OP, or cracked, such as the Dragon's Pearl, Piglin Awakener, Raid Eradicator, etc.
         */
        public BooleanOption stateOfTheArtItems = new BooleanOption(true);

        /**
         * Determines if the annul eye stronghold portal room teleporter should be enabled.
         */
        public BooleanOption annulEye = new BooleanOption(true);

        /**
         * Determines if the blaze spotter item function should be enabled.
         */
        public BooleanOption blazeSpotter = new BooleanOption(true);

        /**
         * Determines if the dragons pearl item function should be enabled.
         */
        public BooleanOption dragonsPearl = new BooleanOption(true);

        /**
         * Determines if the dragons sword item should be enabled.
         */
        public BooleanOption dragonsSword = new BooleanOption(true);

        /**
         * Determines if the ender thruster item function should be enabled.
         */
        public BooleanOption enderThruster = new BooleanOption(true);

        /**
         * Determines if the piglin awakener item function should be enabled.
         */
        public BooleanOption piglinAwakener = new BooleanOption(true);

        /**
         * Determines if the raid eradicator item function should be enabled.
         */
        public BooleanOption raidEradicator = new BooleanOption(true);

        /**
         * Returns true if {@code State-Of-The-Art items} option is enabled and the annul eye toggle switch is enabled.
         */
        public boolean isAnnulEyeTeleporterEnabled() {
            return isStateOfTheArtItemsEnabled() && options().stateOfTheArtItems.annulEye.getCurrentValue();
        }

        /**
         * Returns true if {@code State-Of-The-Art items} option is enabled and the blaze spotter toggle switch is enabled.
         */
        public boolean isBlazeSpotterEnabled() {
            return isStateOfTheArtItemsEnabled() && options().stateOfTheArtItems.blazeSpotter.getCurrentValue();
        }

        /**
         * Returns true if {@code State-Of-The-Art items} option is enabled and the dragons pearl toggle switch is enabled.
         */
        public boolean isDragonsPearlEnabled() {
            return isStateOfTheArtItemsEnabled() && options().stateOfTheArtItems.dragonsPearl.getCurrentValue();
        }

        /**
         * Returns true if {@code State-Of-The-Art items} option is enabled and the dragons sword toggle switch is enabled.
         */
        public boolean isDragonsSwordEnabled() {
            return isStateOfTheArtItemsEnabled() && options().stateOfTheArtItems.dragonsSword.getCurrentValue();
        }

        /**
         * Returns true if {@code State-Of-The-Art items} option is enabled and the ender thruster toggle switch is enabled.
         */
        public boolean isEnderThrusterEnabled() {
            return isStateOfTheArtItemsEnabled() && options().stateOfTheArtItems.enderThruster.getCurrentValue();
        }

        /**
         * Returns true if {@code State-Of-The-Art items} option is enabled and the piglin awakener toggle switch is enabled.
         */
        public boolean isPiglinAwakenerEnabled() {
            return isStateOfTheArtItemsEnabled() && options().stateOfTheArtItems.piglinAwakener.getCurrentValue();
        }

        /**
         * Returns true if {@code State-Of-The-Art items} option is enabled and the raid eradicator toggle switch is enabled.
         */
        public boolean isRaidEradicatorEnabled() {
            return isStateOfTheArtItemsEnabled() && options().stateOfTheArtItems.raidEradicator.getCurrentValue();
        }

        /**
         * Returns true if {@code State-Of-The-Art items} option is enabled.
         */
        private boolean isStateOfTheArtItemsEnabled() {
            return options().stateOfTheArtItems.stateOfTheArtItems.getCurrentValue();
        }
    }

    /**
     * All {@code Advanced Speedrunner Mod options.}
     */
    public static class Advanced {

        /**
         * Allows strongholds to generate differently, or smaller.
         */
        @RequiresRestart
        public BooleanOption modifiedStrongholdGeneration = new BooleanOption(true);

        /**
         * Allows strongholds to generate at higher/lower Y-levels, depending on if doom mode is enabled or not.
         */
        @RequiresRestart
        public BooleanOption modifiedStrongholdYGeneration = new BooleanOption(true);

        /**
         * Allows nether fortresses to generate differently, or smaller, along with more than two blaze spawners per fortress.
         */
        @RequiresRestart
        public BooleanOption modifiedNetherFortressGeneration = new BooleanOption(true);

        /**
         * Display the reset button on the title screen, game menu screen and pause screen.
         */
        public BooleanOption showResetButton = new BooleanOption(true);

        /**
         * Allows the player to breath for a longer period of time while underwater, and also allows the player to regain oxygen when coming out of water blocks.
         */
        public BooleanOption higherBreathTime = new BooleanOption(true);

        /**
         * Allow all types/variants of speedrunner wood to generate across the world. This includes the different variants of speedrunner trees, dead speedrunner trees, and dead speedrunner bushes.
         */
        public BooleanOption generateSpeedrunnerWood = new BooleanOption(true);

        /**
         * The weight for the Speedrunner's Wasteland biome (how commonly it can generate).
         */
        @RequiresRestart
        public IntegerOption speedrunnersWastelandBiomeWeight = new IntegerOption(9);

        /**
         * In vanilla Minecraft, the ender dragon will fly away after perching when it takes so much damage. However, this option extends that damage amount, to allow the dragon to stay perched for a longer period of time, even after taking a large amount of damage.
         */
        public BooleanOption longerDragonPerchStayTime = new BooleanOption(true);

        /**
         * Piglins get scared of zombified piglin if they are within 6 blocks of the zombified piglin. This option decreases that distance to 2 blocks.
         */
        public BooleanOption decreasedZombifiedPiglinScareDistance = new BooleanOption(true);

        /**
         * Determines how long it takes (in ticks) for an eye of ender to break after throwing it.
         */
        public IntegerOption enderEyeBreakingCooldown = new IntegerOption(60);

        /**
         * Determines the total amount of piglin that can teleport to the player per time using the piglin awakener item (Default = 10).
         */
        public IntegerOption piglinAwakenerPiglinCount = new IntegerOption(10);

        /**
         * Sets the inventory slot that the flight duration 3 firework rockets should be given to when iCarus Mode is enabled.
         */
        public IntegerOption iCarusFireworksInventorySlot = new IntegerOption(1);

        /**
         * Sets the inventory slot that the InfiniPearl item should be given to when InfiniPearl mode is enabled.
         * <p>This value is incremented by one if iCarus Mode is already enabled.</p>
         */
        public IntegerOption infiniPearlInventorySlot = new IntegerOption(1);

        /**
         * Determines the explosion power for fireballs when thrown with a fire charge.
         */
        public IntegerOption fireballExplosionPower = new IntegerOption(1);

        /**
         * The minimum brightness amount for the Speedrunner Mod.
         */
        public DoubleOption minimumBrightness = new DoubleOption(0.0D);

        /**
         * The maximum brightness amount for the Speedrunner Mod.
         */
        public DoubleOption maximumBrightness = new DoubleOption(12.0D);

        /**
         * Makes the ender dragon kill all nearby hostile entities upon dying, excluding enderman.
         */
        public BooleanOption dragonKillsNearbyHostileEntities = new BooleanOption(true);

        /**
         * On doom mode, the ender dragon cannot be killed by any means if the Giant and Wither are still alive in the end.
         */
        public BooleanOption dragonImmunityFromGiantAndWither = new BooleanOption(true);

        /**
         * When using the eye of annul stronghold portal room teleporter feature, it iterates through [-X, -Y, -Z, X, Y, Z] all blocks in this location to locate the portal room block. Negative values go below the player, positive values go above.
         */
        public IntegerListOption annulEyePortalRoomDistanceXYZ = new IntegerListOption(createListOption(-128, -128, -128, 128, 128, 128));

        /**
         * When using the piglin awakener, the game will search around the player [X_Y_Z] blocks to find nearby piglin. The higher these numbers, the farther out the game looks. Increasing these numbers however is not recommended, as it could create extreme amounts of lag.
         */
        public DoubleListOption piglinAwakenerPiglinDistanceXYZ = new DoubleListOption(createListOption(100.0D, 100.0D, 100.0D));

        /**
         * Determines the distance that the blaze spotter will use to determine the nearest blaze spawner.
         */
        public IntegerListOption blazeSpotterDistanceXYZ = new IntegerListOption(createListOption(-156, -72, -156, 156, 72, 156));

        /**
         * When using the raid eradicator, the item will search a distance to search for the nearest raider entities.
         */
        public DoubleListOption raidEradicatorDistanceXYZ = new DoubleListOption(createListOption(300.0D, 300.0D, 300.0D));

        /**
         * The dragon's pearl item will look in the radius of [X_Y_Z] for the nearest ender dragon, and choose that dragon to control perching.
         */
        public DoubleListOption dragonsPearlDragonDistanceXYZ = new DoubleListOption(createListOption(150.0D, 150.0D, 150.0D));

        /**
         * Determines the entities in range that will be killed upon the ender dragon's death.
         * <p>This option is redundant if the option Dragon Kills Nearby Hostile Entities is OFF.</p>
         */
        public DoubleListOption dragonKillsHostileEntitiesDistance = new DoubleListOption(createListOption(200.0D, 200.0D, 200.0D));

        /**
         * When on doom mode, the dragon cannot die if there is a nearby giant. This option specifies the range that the giant has to be in from the dragon in order for it to be immune.
         */
        public DoubleListOption dragonImmunityDetectionDistanceForGiant = new DoubleListOption(createListOption(200.0D, 200.0D, 200.0D));

        /**
         * When on doom mode, the dragon cannot die if there is a nearby wither. This option specifies the range that the wither has to be in from the dragon in order for it to be immune.
         */
        public DoubleListOption dragonImmunityDetectionDistanceForWither = new DoubleListOption(createListOption(300.0D, 300.0D, 300.0D));
    }

    /**
     * All {@code Mixin} control options.
     */
    public static class Mixins {

        /**
         * Applies the terrablender surface rule data mixin into the game.
         * <p>Disable this if you do not want doom stone to generate throughout the end when doom mode is enabled, or if another mod is trying to generate other blocks.</p>
         */
        @RequiresRestart
        public BooleanOption terraBlenderSurfaceRuleDataMixin = new BooleanOption(true);

        /**
         * Applies the fog option into the game.
         * <p>Disable this if you are experiencing compatibility issues with other mods that may also mess with fog settings.</p>
         */
        @RequiresRestart
        public BooleanOption backgroundRendererMixin = new BooleanOption(true);

        /**
         * Applies the simple option mixin into the game, which controls the brightness option slider.
         * <p>Disable this if you are experiencing compatibility issues with other mods, or if you don't want the new brightness slider.</p>
         */
        @RequiresRestart
        public BooleanOption simpleOptionMixin = new BooleanOption(true);

        /**
         * Applies the logo drawer mixin into the game, which adds the custom speedrunner edition logo to the title screen.
         * <p>Disable this if you do not want the custom logo, or are making a custom texture pack that uses a different logo, or are experiencing compatibility issues with other mods.</p>
         */
        @RequiresRestart
        public BooleanOption logoDrawerMixin = new BooleanOption(true);

        /**
         * Applies the render layers mixin into the game, which registers a render layer for lava boats.
         * <p>I would only disable this if you absolutely have to, or if you are experiencing noticeable issues with lava boats, or aren't using them.</p>
         */
        @RequiresRestart
        public BooleanOption renderLayersMixin = new BooleanOption(true);
    }

    /**
     * {@code Structure Spawn Rates} config.
     * <p>These values are only applied if the {@code Structure Spawn Rates} option is set to {@code CUSTOM.}
     * <p>The {@code first integer} in the option list is the {@code spacing value.}
     * <p>The {@code second integer} in the option list is the {@code separation value.}
     * <p>The {@code separation value} should NEVER be greater than or equal to the spacing value. The game will crash if this happens.
     */
    public static class StructureSpawnRates {
        public int[] ancientCities = createStructureSpawnRateOption(16, 8);
        public int[] villages = createStructureSpawnRateOption(16, 8);
        public int[] desertPyramids = createStructureSpawnRateOption(10, 5);
        public int[] junglePyramids = createStructureSpawnRateOption(10, 5);
        public int[] pillagerOutposts = createStructureSpawnRateOption(10, 5);
        public int[] endCities = createStructureSpawnRateOption(7, 3);
        public int[] woodlandMansions = createStructureSpawnRateOption(25, 12);
        public int[] ruinedPortals = createStructureSpawnRateOption(9, 4);
        public int[] shipwrecks = createStructureSpawnRateOption(10, 5);
        public int[] trialChambers = createStructureSpawnRateOption(12, 6);
        public int[] netherComplexes = createStructureSpawnRateOption(8, 4);
    }

    /**
     * Creates an {@code integer list option,} with {@code positive} coordinate values.
     */
    public static double[] createListOption(double posX, double posY, double posZ) {
        return new double[]{posX, posY, posZ};
    }

    /**
     * Creates an {@code integer list option,} with {@code negative} and {@code positive} coordinate values.
     */
    public static int[] createListOption(int negX, int negY, int negZ, int posX, int posY, int posZ) {
        return new int[]{negX, negY, negZ, posX, posY, posZ};
    }

    /**
     * Creates a new {@code structure spawn rate option.}
     */
    public static int[] createStructureSpawnRateOption(int spacing, int separation) {
        return new int[]{spacing, separation};
    }

    /**
     * Returns true if the {@code Mob Spawning Rate} option is safe to run.
     */
    public boolean isMobSpawningRateSafe() {
        return options().main.mobSpawningRate.equals(MobSpawningRate.LOW) ||
                options().main.mobSpawningRate.equals(MobSpawningRate.NORMAL) ||
                options().main.mobSpawningRate.equals(MobSpawningRate.HIGH);
    }

    /**
     * Returns true if the {@code Dragon Perch Time} option is valid.
     */
    public boolean isDragonPerchTimeValid() {
        return this.inBounds(main.dragonPerchTime.getCurrentValue(), 8, 90);
    }

    /**
     * Returns true if the {@code Dragon Perch Time} option is {@code on.}
     */
    public boolean isDragonPerchTimeOn() {
        return this.inBounds(main.dragonPerchTime.getCurrentValue(), 10);
    }

    /**
     * Returns true if the {@code Dragon Perch Time} option is {@code "instant".}
     */
    public boolean isInstantDragonPerchTime() {
        return options().main.dragonPerchTime.getCurrentValue() == 9;
    }

    /**
     * Returns the current {@code Dragon Perch Time} option in milliseconds.
     */
    public int getDragonPerchTime() {
        return TimeCalculator.secondsToMilliseconds(options().main.dragonPerchTime.getCurrentValue());
    }

    /**
     * Returns true if the {@code Block Breaking Multiplier} option is valid.
     */
    public boolean isBlockBreakingMultiplierValid() {
        return this.inBounds(main.blockBreakingMultiplier.getCurrentValue(), 1, 3);
    }

    /**
     * Returns true if the {@code Stronghold Distance} option is valid.
     */
    public boolean isStrongholdDistanceValid() {
        return this.inBounds(main.strongholdDistance.getCurrentValue(), 3, 64);
    }

    /**
     * Returns true if the {@code Stronghold Spread} option is valid.
     */
    public boolean isStrongholdSpreadValid() {
        return this.inBounds(main.strongholdSpread.getCurrentValue(), 2, 32);
    }

    /**
     * Returns true if the {@code Stronghold Count} option is valid.
     */
    public boolean isStrongholdCountValid() {
        return this.inBounds(main.strongholdCount.getCurrentValue(), 4, 156);
    }

    /**
     * Returns true if the {@code Stronghold Portal Room Count} option is valid.
     */
    public boolean isStrongholdPortalRoomCountValid() {
        return this.inBounds(main.strongholdPortalRoomCount.getCurrentValue(), 0, 3);
    }

    /**
     * Returns true if the {@code Stronghold Library Count} option is valid.
     */
    public boolean isStrongholdLibraryCountValid() {
        return this.inBounds(main.strongholdLibraryCount.getCurrentValue(), 1, 10);
    }

    /**
     * Returns true if the {@code Anvil Cost Limit} option is valid.
     */
    public boolean isAnvilCostLimitValid() {
        return this.inBounds(main.anvilCostLimit.getCurrentValue(), 1, 50);
    }

    /**
     * Returns true if the {@code Nether Portal Cooldown} option is valid.
     */
    public boolean isNetherPortalCooldownValid() {
        return this.inBounds(main.netherPortalDelay.getCurrentValue(), 0, 20);
    }

    /**
     * Returns true if the {@code Speedrunner's Wasteland Biome Weight} option is valid.
     */
    public boolean isSpeedrunnersWastelandBiomeWeightValid() {
        return this.inBounds(advanced.speedrunnersWastelandBiomeWeight.getCurrentValue(), 1, 32);
    }

    /**
     * Returns true if the {@code Eye of Ender Breaking Cooldown} advanced option is valid.
     */
    public boolean isEyeOfEnderBreakingCooldownValid() {
        return this.inBounds(advanced.enderEyeBreakingCooldown.getCurrentValue(), 20, 200);
    }

    /**
     * Returns true if the {@code Icarus Fireworks Inventory Slot} advanced option is valid.
     */
    public boolean isIcarusFireworksInventorySlotValid() {
        return this.inBounds(advanced.iCarusFireworksInventorySlot.getCurrentValue(), 1, 36);
    }

    /**
     * Returns true if the {@code InfiniPearl Inventory Slot} advanced option is valid.
     */
    public boolean isInfiniPearlInventorySlotValid() {
        return this.inBounds(advanced.infiniPearlInventorySlot.getCurrentValue(), 1, 36);
    }

    /**
     * Determines if a certain option is {@code greater than} or {@code equal to} inputted parameters.
     */
    public boolean inBounds(int option, int min) {
        return option >= min;
    }

    /**
     * Determines if a certain option is {@code less than} and {@code greater than} equal to said parameters.
     */
    public boolean inBounds(int option, int min, int max) {
        return option >= min && option <= max;
    }

    /**
     * Loads the configuration file.
     */
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

    /**
     * Reads the configuration file.
     */
    public static void readConfig() {
        OPTIONS = getConfig();
    }

    /**
     * Saves the configuration file.
     */
    public static void saveConfig() {
        File file = getConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(SpeedrunnerMod.options()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the {@code OPTIONS} variable to the config.
     */
    public static void setConfig(ModOptions config) {
        OPTIONS = config;
        saveConfig();
    }

    /**
     * Gets all the Speedrunner Mod configuration options and returns them.
     */
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

    /**
     * Returns the Speedrunner Mod configuration file.
     */
    private static File getConfigFile() {
        if (file == null) {
            file = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG);
        }
        return file;
    }

    public static void loadValues() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CONFIG)) {
            ModOptions options = gson.fromJson(reader, ModOptions.class);

            for (IntegerOption option : OptionManager.getIntegerOptions()) {

            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The game is safe to run if the {@code safe} parameter returns true.
     */
    protected static void isSafe(boolean safe) {
        SpeedrunnerMod.safeBoot = !safe;
    }

    /**
     * Preforms a {@code "safe check"} on all the Speedrunner Mod options, and makes sure that they are valid and safe to run in-game.
     * <p>If an option is broken or invalid, and it is not recommended to run, the user will automatically boot into the {@link SafeBootScreen}.</p>
     */
    private static void safeCheck() {
        final String space = " ";
        final String pertaining = "Pertaining to: ";
        final String related = space + pertaining;

        if (options().main.leaderboardsMode.getCurrentValue()) {
            error("Leaderboards mode is ON, please disable, as the leaderboards have been deleted.");
            isSafe(false);
            BrokenModOptions.leaderboards = true;
        }

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

        if (!options().client.itemMessages.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.itemMessages");
            isSafe(false);
            BrokenModOptions.itemMessages = true;
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

        if (options().main.netherPortalDelay.getCurrentValue() < 0) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.netherPortalCooldown");
            isSafe(false);
            BrokenModOptions.netherPortalCooldown = true;
        } else if (!options().isNetherPortalCooldownValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.netherPortalCooldown");
        }

        if (options().main.strongholdPortalRoomCount.getCurrentValue() < 1) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.strongholdPortalRoomCount");
            isSafe(false);
            BrokenModOptions.strongholdPortalRoomCount = true;
        } else if (!options().isStrongholdPortalRoomCountValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdPortalRoomCount");
        }

        if (options().main.blockBreakingMultiplier.getCurrentValue() < 0) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.blockBreakingMultiplier");
            isSafe(false);
            BrokenModOptions.blockBreakingMultiplier = true;
            warn("Cannot divide by zero! o_0");
        } else if (!options().isBlockBreakingMultiplierValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.blockBreakingMultiplier");
        }

        if (options().advanced.speedrunnersWastelandBiomeWeight.getCurrentValue() < 1) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.speedrunnersWastelandBiomeWeight");
            isSafe(false);
            BrokenModOptions.speedrunnersWastelandBiomeWeight = true;
            warn("Speedrunner's Wasteland Biome Weight is below 1. Instead, turn \"Custom Biomes and Custom Biome Features\" OFF.");
        } else if (!options().isSpeedrunnersWastelandBiomeWeightValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.speedrunnersWastelandBiomeWeight");
            warn("The weight for the Speedrunner's Wasteland biome is either too high or too low. Proceed with caution.");
        }

        if (!options().isIcarusFireworksInventorySlotValid()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.icarusFireworksInventorySlot");
            isSafe(false);
            BrokenModOptions.iCarusFireworksInventorySlot = true;
        }

        if (!options().isInfiniPearlInventorySlotValid()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.infiniPearlInventorySlot");
            isSafe(false);
            BrokenModOptions.infiniPearlInventorySlot = true;
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