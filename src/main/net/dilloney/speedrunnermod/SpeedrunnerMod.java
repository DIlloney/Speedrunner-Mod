package net.dilloney.speedrunnermod;

import com.google.common.collect.ImmutableMap;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dilloney.speedrunnermod.block.ModBlocks;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.option.ModOptions;
import net.dilloney.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe;
import net.dilloney.speedrunnermod.tag.ModBlockTags;
import net.dilloney.speedrunnermod.tag.ModItemTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.mixin.structure.StructuresConfigAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class SpeedrunnerMod implements ModInitializer {
    public static final Text SPEEDRUNNER_MOD_TITLE = new TranslatableText("speedrunnermod.title");
    public static final Identifier SPEEDRUNNER_BOOTS = new Identifier(SpeedrunnerMod.MOD_ID, "textures/item/speedrunner_boots.png");
    public static final Identifier SPEEDRUNNER_INGOT = new Identifier(SpeedrunnerMod.MOD_ID, "textures/item/speedrunner_ingot.png");
    public static final String MOD_ID = "speedrunnermod";
    public static final String MOD_VERSION = "v1.5.2";
    public static final String MINECRAFT_VERSION = "1.18.1";
    public static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static final String CONFIG = "speedrunnermod-options.json";
    private static File file;
    private static ModOptions OPTIONS = getConfig();
    private static final String OLD_CONFIG = "speedrunnermod_options.json";
    private static File old_config_file;

    public void onInitialize() {
        SpeedrunnerMod.loadConfig();

        ModItems.init();
        ModBlocks.init();
        ModItemTags.init();
        ModBlockTags.init();
        SpeedrunnerShieldDecorationRecipe.register();

        if (SpeedrunnerMod.options().main.makeStructuresMoreCommon) {
            SpeedrunnerMod.makeStructuresMoreCommon();
        }

        LOGGER.info("The Speedrunner Mod (" + MOD_VERSION + ")" + " has been loaded.");
    }

    private static void makeStructuresMoreCommon() {
        ServerWorldEvents.LOAD.register((server, world) -> {
            Map<StructureFeature<?>, StructureConfig> map = new HashMap<>(world.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());

            map.computeIfPresent(StructureFeature.VILLAGE, (structureFeature, structureConfig) -> {
                return new StructureConfig(16, 9, 10387312);
            });
            map.computeIfPresent(StructureFeature.DESERT_PYRAMID, (structureFeature, structureConfig) -> {
                return new StructureConfig(10, 8, 14357617);
            });
            map.computeIfPresent(StructureFeature.END_CITY, (structureFeature, structureConfig) -> {
                return new StructureConfig(7, 6, 10387313);
            });
            map.computeIfPresent(StructureFeature.RUINED_PORTAL, (structureFeature, structureConfig) -> {
                return new StructureConfig(9, 8, 34222645);
            });
            map.computeIfPresent(StructureFeature.SHIPWRECK, (structureFeature, structureConfig) -> {
                return new StructureConfig(10, 8, 165745295);
            });
            map.computeIfPresent(StructureFeature.BASTION_REMNANT, (structureFeature, structureConfig) -> {
                return new StructureConfig(9, 8, 30084232);
            });
            map.computeIfPresent(StructureFeature.FORTRESS, (structureFeature, structureConfig) -> {
                return new StructureConfig(8, 7, 30084232);
            });

            ImmutableMap<StructureFeature<?>, StructureConfig> immutableMap = ImmutableMap.copyOf(map);

            ((StructuresConfigAccessor)world.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(immutableMap);
        });
    }

    private static void loadConfig() {
        File configFile = getConfigFile();
        File oldConfigFile = getOldConfigFile();
        if (oldConfigFile.exists()) {
            oldConfigFile.delete();
            LOGGER.warn("Found an old configuration file, deleting.");
        }

        if (!configFile.exists()) {
            SpeedrunnerMod.OPTIONS = new ModOptions();
        } else {
            sanitize();
            readConfig();
        }
        saveConfig();
    }

    private static void readConfig() {
        SpeedrunnerMod.OPTIONS = getConfig();
    }

    public static void saveConfig() {
        File file = getConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(SpeedrunnerMod.options()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setConfig(ModOptions config) {
        SpeedrunnerMod.OPTIONS = config;
        saveConfig();
    }

    private static ModOptions getConfig() {
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

    private static void sanitize() {
        if (SpeedrunnerMod.options().advanced.mobSpawningRate == null) {
            SpeedrunnerMod.options().advanced.mobSpawningRate = ModOptions.Advanced.MobSpawningRate.HIGH;
        }
    }

    public static ModOptions options() {
        return OPTIONS;
    }

    private static File getOldConfigFile() {
        if (old_config_file == null) {
            old_config_file = new File(FabricLoader.getInstance().getConfigDir().toFile(), OLD_CONFIG);
        }
        return old_config_file;
    }
}