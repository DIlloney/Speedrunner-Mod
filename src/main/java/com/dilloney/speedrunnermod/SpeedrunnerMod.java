package com.dilloney.speedrunnermod;

import com.dilloney.speedrunnermod.config.ConfigurationOptions;
import com.dilloney.speedrunnermod.config.ConfigFileManager;
import com.dilloney.speedrunnermod.registry.ModRegistry;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpeedrunnerMod implements ModInitializer {

    private static final Logger LOGGER = LogManager.getLogger();
    public static ConfigurationOptions CONFIG = ConfigFileManager.get();

    @Override
    public void onInitialize() {
        ModRegistry.loadConfig();
        ModRegistry.registerItems();
        ModRegistry.registerBlocks();
        ModRegistry.registerBlockItems();
        ModRegistry.registerModifiedStructureGeneration();
        ModRegistry.registerModDifficulty();
        ModRegistry.registerConfiguredFeatures();
        ModRegistry.registerMisc();
        LOGGER.info("Speedrunner Mod loaded successfully! modVersion = 1.16.4 | minecraftVersion = 1.17x");
    }
}
