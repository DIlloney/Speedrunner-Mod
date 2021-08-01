package com.dilloney.speedrunnermod;

import com.dilloney.speedrunnermod.config.ConfigurationOptions;
import com.dilloney.speedrunnermod.config.ConfigurationFileManager;
import com.dilloney.speedrunnermod.registry.ModRegistry;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpeedrunnerMod implements ModInitializer {

    private static final Logger LOGGER = LogManager.getLogger();
    public static ConfigurationOptions CONFIG = ConfigurationFileManager.get();

    @Override
    public void onInitialize() {
        ModRegistry.loadConfig();
        ModRegistry.registerItems();
        ModRegistry.registerBlocks();
        ModRegistry.registerBlockItems();
        ModRegistry.registerSoundEvents();
        ModRegistry.registerConfiguredFeatures();
        ModRegistry.registerTags();
        ModRegistry.registerMiscellaneous();
        LOGGER.info("Speedrunner Mod loaded successfully! modVersion = 1.17 | minecraftVersion = 1.17x");
        LOGGER.info("See wiki for more information: https://sites.google.com/view/speedrunnermod");
        if (CONFIG.manhuntMode) {
            ModRegistry.registerManhuntCommands();
        }
    }
}
