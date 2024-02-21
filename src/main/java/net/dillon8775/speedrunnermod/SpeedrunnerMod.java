package net.dillon8775.speedrunnermod;

import com.google.common.collect.ImmutableMap;
import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.dillon8775.speedrunnermod.enchantment.ModEnchantments;
import net.dillon8775.speedrunnermod.item.ModBlockItems;
import net.dillon8775.speedrunnermod.item.ModFuels;
import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.mixin.main.world.ConfiguredStructureFeaturesMixin;
import net.dillon8775.speedrunnermod.mixin.main.world.StrongholdConfigMixin;
import net.dillon8775.speedrunnermod.mixin.main.world.VanillaBiomeParametersMixin;
import net.dillon8775.speedrunnermod.option.ClientModOptions;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.dillon8775.speedrunnermod.recipe.ModRecipes;
import net.dillon8775.speedrunnermod.tag.ModBlockTags;
import net.dillon8775.speedrunnermod.tag.ModFluidTags;
import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.dillon8775.speedrunnermod.village.ModTradeOffers;
import net.dillon8775.speedrunnermod.village.ModVillagerProfessions;
import net.dillon8775.speedrunnermod.world.ModFeatures;
import net.dillon8775.speedrunnermod.world.ModWorldGen;
import net.dillon8775.speedrunnermod.world.biome.ModBiomes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.mixin.structure.StructuresConfigAccessor;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static net.dillon8775.speedrunnermod.SpeedrunnerModClient.clientOptions;
import static net.dillon8775.speedrunnermod.option.ModOptions.isSafe;

/**
 * The main class file for {@code The Speedrunner Mod}.
 */
public class SpeedrunnerMod implements ModInitializer {
    /**
     * <p>{@link SpeedrunnerMod#initializeModStructureConfigs()}
     * <p>{@link net.dillon8775.speedrunnermod.option.ModOptions.StructureSpawnRates} Feature</p>
     * <p></p>
     * <p>{@link VanillaBiomeParametersMixin}
     * <p>Speedrunner's Wasteland Biome Spawning <i>and</i> Better Biomes configuration</p>
     * <p></p>
     * <p>{@link ConfiguredStructureFeaturesMixin}</p>
     * <p>Modified structure generation and "Custom Biomes" option.</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.entity.basic.EntityMixin}, {@link net.dillon8775.speedrunnermod.mixin.main.boat.BoatEntityMixin} <i>and</i> {@link net.dillon8775.speedrunnermod.mixin.main.boat.BoatEntityTypeMixin} </p>
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
     * <p>{@link StrongholdConfigMixin}
     * <p>Stronghold Count <i>and</i> Stronghold Distance option configuration</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.mixin.main.entity.DragonPerchTime}
     * <p>Dragon Perch Time configuration</p>
     * <p></p>
     * <p>{@link ModFeatures#makeAnimalsMoreCommon(SpawnSettings.Builder)} <i>and</i> {@link ModFeatures#makeDolphinsMoreCommon(SpawnSettings.Builder, int, int)}
     * <p>{@link net.dillon8775.speedrunnermod.option.ModOptions.MobSpawningRate} configuration</p>
     * <p></p>
     * <p><b>For client options, refer to</b> {@link SpeedrunnerModClient}.</p>
     */
    public static final String MOD_ID = "speedrunnermod";
    public static final String MOD_VERSION = "v1.8.4";
    public static final String VERSION = "Version: " + MOD_VERSION;
    public static final String THE_SPEEDRUNNER_MOD_STRING = "The Speedrunner Mod";
    public static final String WIKI_LINK = "https://sites.google.com/view/dillon8775/the-speedrunner-mod";
    public static boolean DOOM_MODE = SpeedrunnerMod.options().doomMode;
    public static final Identifier SPEEDRUNNER_MOD_ICON = new Identifier("speedrunnermod:icon.png");
    public static final Identifier DISCORD_ICON = new Identifier("dillon8775:textures/discord.png");
    public static final Identifier WEBPAGE_ICON = new Identifier("dillon8775:textures/webpage.png");
    public static final Identifier DILLON8775_ICON = new Identifier("dillon8775:textures/dillon8775.png");
    public static final Identifier MANNYQUESO_ICON = new Identifier("dillon8775:textures/mannyqueso.png");
    public static final Identifier NUZLAND_ICON = new Identifier("dillon8775:textures/nuzland.png");
    public static final String OPTIONS_ERROR_MESSAGE = "Found error with speedrunner mod settings, launching in safe mode.";
    public static final String OPTIONS_WARNING_MESSAGE = "Found an unusual value in the speedrunner mod settings.";
    public static boolean safeBoot;
    private static final Logger LOGGER = LogManager.getLogger("Speedrunner Mod");

    /**
     * <p>Initializes all the {@code Speedrunner Mod} features, items, blocks, etc.</p>
     * <p>This does not include any {@link org.spongepowered.asm.mixin.Mixins}.</p>
     */
    @Override
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
        ModBiomes.init();
        ModVillagerProfessions.init();
        ModTradeOffers.init();

        if (SpeedrunnerMod.options().generateSpeedrunnerTrees) {
            ModWorldGen.init();
        } else {
            info("Failed to initialize worldgen features, because option \"Generate Speedrunner Trees\" is OFF.");
        }

        initializeModStructureConfigs();

        isSafe(true);
        ModOptions.loadConfig();

        if (SpeedrunnerMod.options().doomMode) {
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

    /**
     * Resets all of the {@code speedrunner mod options} back to the default settings.
     */
    @Environment(EnvType.CLIENT)
    public static void resetOptions() {
        options().structureSpawnRates = ModOptions.StructureSpawnRates.COMMON;
        options().fasterBlockBreaking = true;
        options().betterBiomes = true;
        options().iCarusMode = false;
        options().infiniPearlMode = false;
        options().doomMode = false;
        options().dragonPerchTime = 30;
        options().killGhastOnFireball = false;
        options().betterVillagerTrades = true;
        options().fireproofItems = true;
        options().generateSpeedrunnerTrees = true;
        options().customBiomes = true;
        options().commonOres = true;
        options().lavaBoats = true;
        options().netherWater = true;
        options().betterFoods = true;
        options().fallDamage = true;
        options().strongholdDistance = 4;
        options().strongholdSpread = 3;
        options().strongholdCount = 128;
        options().strongholdPortalRoomCount = 3;
        options().strongholdLibraryCount = 2;
        options().mobSpawningRate = ModOptions.MobSpawningRate.HIGH;
        options().mobSpawnerMinimumSpawnDuration = 20;
        options().mobSpawnerMaximumSpawnDuration = 40;
        options().netherPortalCooldown = 2;
        options().globalNetherPortals = false;
        options().betterAnvil = true;
        options().anvilCostLimit = 40;
        options().higherEnchantmentLevels = true;
        options().higherBreathTime = true;
        clientOptions().fog = true;
        clientOptions().itemTooltips = true;
        clientOptions().blockParticles = false;
        clientOptions().panorama = ClientModOptions.Panorama.SPEEDRUNNER_MOD;
        clientOptions().itemMessages = ClientModOptions.ItemMessages.CHAT;
        clientOptions().modButtonType = ClientModOptions.ModButtonType.LOGO;
        clientOptions().socialButtons = true;
    }

    /**
     * Structures don't generate commonly enough for speedrunning. With this, we make them more common, based on the user's desire.
     * <p>See {@link ModOptions} for more.</p>
     * <p>{@link net.minecraft.world.gen.chunk.StructuresConfig}</p>
     */
    public static void initializeModStructureConfigs() {
        if (options().structureSpawnRates == ModOptions.StructureSpawnRates.EVERYWHERE) {
            setVillageConfig(4, 2);
            setDesertPyramidConfig(3, 2);
            setJunglePyramidConfig(3, 2);
            setPillagerOutpostConfig(3, 2);
            setEndCityConfig(4, 2);
            setWoodlandMansionConfig(6, 4);
            setRuinedPortalConfig(4, 2);
            setShipwreckConfig(3, 2);
            setBastionRemnantConfig(4, 2);
            setNetherFortressConfig(4, 2);
        } else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.VERY_COMMON) {
            setVillageConfig(10, 8);
            setDesertPyramidConfig(8, 7);
            setJunglePyramidConfig(8, 7);
            setPillagerOutpostConfig(8, 7);
            setEndCityConfig(5, 4);
            setWoodlandMansionConfig(16, 8);
            setRuinedPortalConfig(7, 6);
            setShipwreckConfig(8, 7);
            setBastionRemnantConfig(7, 6);
            setNetherFortressConfig(6, 5);
        } else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.COMMON) {
            setVillageConfig(16, 9);
            setDesertPyramidConfig(10, 8);
            setJunglePyramidConfig(10, 8);
            setPillagerOutpostConfig(10, 8);
            setEndCityConfig(7, 6);
            setWoodlandMansionConfig(25, 20);
            setRuinedPortalConfig(9, 8);
            setShipwreckConfig(10, 8);
            setBastionRemnantConfig(9, 8);
            setNetherFortressConfig(8, 7);
        } else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.NORMAL) {
            setVillageConfig(20, 8);
            setDesertPyramidConfig(20, 8);
            setJunglePyramidConfig(20, 8);
            setPillagerOutpostConfig(20, 8);
            setEndCityConfig(15, 10);
            setWoodlandMansionConfig(40, 20);
            setRuinedPortalConfig(16, 9);
            setShipwreckConfig(20, 8);
            setBastionRemnantConfig(16, 9);
            setNetherFortressConfig(15, 8);
        } else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.DEFAULT) {
            setVillageConfig(32, 8);
            setDesertPyramidConfig(32, 8);
            setJunglePyramidConfig(32, 8);
            setPillagerOutpostConfig(32, 8);
            setEndCityConfig(20, 11);
            setWoodlandMansionConfig(80, 20);
            setRuinedPortalConfig(40, 15);
            setShipwreckConfig(24, 4);
            setBastionRemnantConfig(27, 4);
            setNetherFortressConfig(27, 4);
        } else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.RARE) {
            setVillageConfig(42, 10);
            setDesertPyramidConfig(40, 10);
            setJunglePyramidConfig(40, 10);
            setPillagerOutpostConfig(40, 10);
            setEndCityConfig(25, 16);
            setWoodlandMansionConfig(100, 20);
            setRuinedPortalConfig(50, 16);
            setShipwreckConfig(30, 9);
            setBastionRemnantConfig(35, 9);
            setNetherFortressConfig(34, 8);
        } else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.VERY_RARE) {
            setVillageConfig(52, 16);
            setDesertPyramidConfig(50, 16);
            setJunglePyramidConfig(50, 12);
            setPillagerOutpostConfig(50, 12);
            setEndCityConfig(30, 18);
            setWoodlandMansionConfig(120, 25);
            setRuinedPortalConfig(60, 20);
            setShipwreckConfig(40, 10);
            setBastionRemnantConfig(40, 10);
            setNetherFortressConfig(40, 10);
        } else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.OFF) {
            info("Structure spawn rate is off, ignoring custom structure generation.");
        } else {
            isSafe(false);
            error("Found error with structure spawn rates, booting into safe mode.");
        }

        if (options().structureSpawnRates != ModOptions.StructureSpawnRates.OFF) {
            info("Set structure spawn rate values.");

            ServerWorldEvents.LOAD.register((server, world) -> {
                Map<StructureFeature<?>, StructureConfig> map = new HashMap<>(world.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());

                map.computeIfPresent(StructureFeature.VILLAGE, (structureFeature, structureConfig) -> {
                    return new StructureConfig(villageSpacing, villageSeparation, 10387312);
                });
                map.computeIfPresent(StructureFeature.DESERT_PYRAMID, (structureFeature, structureConfig) -> {
                    return new StructureConfig(desertPyramidSpacing, desertPyramidSeparation, 14357617);
                });
                map.computeIfPresent(StructureFeature.JUNGLE_PYRAMID, (structureFeature, structureConfig) -> {
                    return new StructureConfig(junglePyramidSpacing, junglePyramidSeparation, 14357619);
                });
                map.computeIfPresent(StructureFeature.PILLAGER_OUTPOST, (structureFeature, structureConfig) -> {
                    return new StructureConfig(pillagerOutpostSpacing, pillagerOutpostSeparation, 165745296);
                });
                map.computeIfPresent(StructureFeature.END_CITY, (structureFeature, structureConfig) -> {
                    return new StructureConfig(endCitySpacing, endCitySeparation, 10387313);
                });
                map.computeIfPresent(StructureFeature.MANSION, (structureFeature, structureConfig) -> {
                    return new StructureConfig(mansionSpacing, mansionSeparation, 10387319);
                });
                map.computeIfPresent(StructureFeature.RUINED_PORTAL, (structureFeature, structureConfig) -> {
                    return new StructureConfig(ruinedPortalSpacing, ruinedPortalSeparation, 34222645);
                });
                map.computeIfPresent(StructureFeature.SHIPWRECK, (structureFeature, structureConfig) -> {
                    return new StructureConfig(shipwreckSpacing, shipwreckSeparation, 165745295);
                });
                map.computeIfPresent(StructureFeature.BASTION_REMNANT, (structureFeature, structureConfig) -> {
                    return new StructureConfig(bastionSpacing, bastionSeparation, 30084232);
                });
                map.computeIfPresent(StructureFeature.FORTRESS, (structureFeature, structureConfig) -> {
                    return new StructureConfig(fortressSpacing, fortressSeparation, 30084232);
                });

                ImmutableMap<StructureFeature<?>, StructureConfig> immutableMap = ImmutableMap.copyOf(map);

                ((StructuresConfigAccessor)world.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(immutableMap);
            });

            info("Initialized structure spawn rates, structures are now more common.");
        }
    }

    private static int villageSpacing;
    private static int villageSeparation;
    private static int desertPyramidSpacing;
    private static int desertPyramidSeparation;
    private static int junglePyramidSpacing;
    private static int junglePyramidSeparation;
    private static int pillagerOutpostSpacing;
    private static int pillagerOutpostSeparation;
    private static int endCitySpacing;
    private static int endCitySeparation;
    private static int mansionSpacing;
    private static int mansionSeparation;
    private static int ruinedPortalSpacing;
    private static int ruinedPortalSeparation;
    private static int shipwreckSpacing;
    private static int shipwreckSeparation;
    private static int bastionSpacing;
    private static int bastionSeparation;
    private static int fortressSpacing;
    private static int fortressSeparation;

    private static void setVillageConfig(int spacing, int separation) {
        villageSpacing = spacing;
        villageSeparation = separation;
    }

    private static void setDesertPyramidConfig(int spacing, int separation) {
        desertPyramidSpacing = spacing;
        desertPyramidSeparation = separation;
    }

    private static void setJunglePyramidConfig(int spacing, int separation) {
        junglePyramidSpacing = spacing;
        junglePyramidSeparation = separation;
    }

    private static void setPillagerOutpostConfig(int spacing, int separation) {
        pillagerOutpostSpacing = spacing;
        pillagerOutpostSeparation = separation;
    }

    private static void setEndCityConfig(int spacing, int separation) {
        endCitySpacing = spacing;
        endCitySeparation = separation;
    }

    private static void setWoodlandMansionConfig(int spacing, int separation) {
        mansionSpacing = spacing;
        mansionSeparation = separation;
    }

    private static void setRuinedPortalConfig(int spacing, int separation) {
        ruinedPortalSpacing = spacing;
        ruinedPortalSeparation = separation;
    }

    private static void setShipwreckConfig(int spacing, int separation) {
        shipwreckSpacing = spacing;
        shipwreckSeparation = separation;
    }

    private static void setBastionRemnantConfig(int spacing, int separation) {
        bastionSpacing = spacing;
        bastionSeparation = separation;
    }

    private static void setNetherFortressConfig(int spacing, int separation) {
        fortressSpacing = spacing;
        fortressSeparation = separation;
    }
}