package net.dillon8775.speedrunnermod;

import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.dillon8775.speedrunnermod.enchantment.ModEnchantments;
import net.dillon8775.speedrunnermod.item.ModBlockItems;
import net.dillon8775.speedrunnermod.item.ModFuels;
import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.loot.ModLootTables;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.dillon8775.speedrunnermod.recipe.ModRecipes;
import net.dillon8775.speedrunnermod.tag.ModBlockTags;
import net.dillon8775.speedrunnermod.tag.ModFluidTags;
import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.dillon8775.speedrunnermod.util.entity.ModVillagers;
import net.dillon8775.speedrunnermod.world.ModFeatures;
import net.dillon8775.speedrunnermod.world.ModWorldGen;
import net.dillon8775.speedrunnermod.world.StructureSpawnRates;
import net.dillon8775.speedrunnermod.world.biome.ModBiomes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.SpawnSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main class file for {@code The Speedrunner Mod}.
 */
public class SpeedrunnerMod implements ModInitializer {
    /**
     * <p>{@link StructureSpawnRates}
     * <p>{@link ModOptions.Main.StructureSpawnRate} Feature</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.worldgen.BiomeGeneration}
     * <p>Speedrunner's Wasteland Biome Spawning <i>and</i> Better Biomes configuration</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.worldgen.ModdedWorldGeneration}</p>
     * <p>Modified structure generation and "Custom Biomes" option.</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.entity.EntityMixin}, {@link net.dillon8775.speedrunnermod.mixin.main.boat.BoatEntityMixin} <i>and</i> {@link net.dillon8775.speedrunnermod.mixin.main.boat.BoatEntityTypeMixin} </p>
     * <p>Modded boats configuration and functions.</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.nether.AllowWaterInNether}</p>
     * <p>Allow water in the nether configuration.</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.block.MoreBlockXP} <i>and</i> {@link net.dillon8775.speedrunnermod.mixin.main.block.MoreBlockXPRedstone}</p>
     * <p>Particle effects on ores in the Speedrunner's Wasteland <i>and</i> fortune enchantment multiplier.</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.block.FasterBlockBreaking}
     * <p>Faster Block Breaking configurations</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.modes.ICarusAndInfiniPearlMode}
     * <p>iCarus Mode and InfiniPearl Mode</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.entity.GhastEntityShootFireballGoalMixin}
     * <p>Kill Ghast On Fireball feature</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.worldgen.StrongholdSettings}
     * <p>Stronghold Count <i>and</i> Stronghold Distance option configuration</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.entity.DragonPerchTime}
     * <p>Dragon Perch Time configuration</p>
     * <p></p>
     * <p>{@link ModFeatures#makeAnimalsMoreCommon(SpawnSettings.Builder)} <i>and</i> {@link ModFeatures#makeDolphinsMoreCommon(SpawnSettings.Builder, int, int)}
     * <p>{@link ModOptions.Advanced.MobSpawningRate} configuration</p>
     * <p></p>
     * <p><b>For client options, refer to</b> {@link SpeedrunnerModClient}.</p>
     */
    public static final String MOD_ID = "speedrunnermod";
    public static final String MOD_VERSION = "v1.8.3";
    public static final String VERSION = "Version: " + MOD_VERSION;
    public static final String THE_SPEEDRUNNER_MOD_STRING = "The Speedrunner Mod";
    public static final Identifier SPEEDRUNNER_MOD_ICON = new Identifier("speedrunnermod:icon.png");
    public static final Identifier DISCORD_ICON = new Identifier("dillon8775:textures/discord.png");
    public static final Identifier WEBPAGE_ICON = new Identifier("dillon8775:textures/webpage.png");
    public static final Identifier DILLON8775_ICON = new Identifier("dillon8775:textures/dillon8775.png");
    public static final Identifier MANNYQUESO_ICON = new Identifier("dillon8775:textures/mannyqueso.png");
    public static final Identifier NUZLAND_ICON = new Identifier("dillon8775:textures/nuzland.png");
    public static final String OPTIONS_ERROR_MESSAGE = "Found error with speedrunner mod settings, launching in safe mode.";
    public static final String OPTIONS_WARNING_MESSAGE = "Found an unusual value in the speedrunner mod settings.";
    public static boolean safeBoot;
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * <p>Initializes all the {@code Speedrunner Mod} features, items, blocks, etc.</p>
     * <p>This does not include any {@link org.spongepowered.asm.mixin.Mixins}.</p>
     */
    public void onInitialize() {
        ModBlocks.init();
        ModBlockItems.init();
        ModItems.init();
        ModBlockTags.init();
        ModItemTags.init();
        ModEnchantments.init();
        ModRecipes.init();
        ModFluidTags.init();
        ModFuels.init();
        ModLootTables.init();
        ModBiomes.init();
        ModVillagers.init();

        if (SpeedrunnerMod.options().advanced.generateSpeedrunnerTrees) {
            ModWorldGen.init();
        } else {
            info("Failed to initialize worldgen features, because option \"Generate Speedrunner Trees\" is OFF.");
        }

        StructureSpawnRates.setValues();
        StructureSpawnRates.init();

        ModOptions.isSafe(true);
        ModOptions.loadConfig();

        if (SpeedrunnerMod.options().main.doomMode) {
            info("You dare to attempt Doom Mode? Good luck...");
        }

        info("The Speedrunner Mod (" + MOD_VERSION + ")" + " has successfully initialized!");
    }

    /**
     * Sends an {@code info} message in console.
     */
    public static void info(String info) {
        LOGGER.info(info);
    }

    /**
     * Sends a {@code warning} message in console.
     */
    public static void warn(String warning) {
        LOGGER.warn(warning);
    }

    /**
     * Sends a {@code error} message in console.
     */
    public static void error(String error) {
        LOGGER.error(error);
    }

    /**
     * Sends a {@code debug} message in console.
     */
    public static void debug(String debug) {
        LOGGER.debug(debug);
    }

     /**
     * The {@code Speedrunner Mod} options method.
     */
    public static ModOptions options() {
        return ModOptions.OPTIONS;
    }
}