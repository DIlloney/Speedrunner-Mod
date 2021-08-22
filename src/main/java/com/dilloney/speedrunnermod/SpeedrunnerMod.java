package com.dilloney.speedrunnermod;

import com.dilloney.speedrunnermod.option.ModOptions;
import com.dilloney.speedrunnermod.option.OptionsFileHelper;
import com.dilloney.speedrunnermod.option.OptionsFileManager;
import com.dilloney.speedrunnermod.util.ModRegistry;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpeedrunnerMod implements ModInitializer {

    private static final String MINECRAFT_VERSION = "1.17x";
    public static final Logger LOGGER = LogManager.getLogger();
    public static ModOptions OPTIONS = OptionsFileManager.get();

    @Override
    public void onInitialize() {
        OptionsFileManager.load();
        OptionsFileHelper.fix();
        if (OPTIONS.normalMode) {
            OptionsFileHelper.fixForNormalMode();
        }
        ModRegistry.registerItems();
        ModRegistry.registerBlocks();
        ModRegistry.registerBlockItems();
        ModRegistry.registerSoundEvents();
        ModRegistry.registerConfiguredFeatures();
        ModRegistry.registerTags();
        ModRegistry.registerMiscellaneous();
        if (OPTIONS.manhuntMode) {
            ModRegistry.registerManhuntModeCommands();
        }
        LOGGER.info("Speedrunner Mod loaded successfully! modVersion = v1.3.1 on " + MINECRAFT_VERSION);
        LOGGER.info("See wiki for more information: https://sites.google.com/view/speedrunnermod");
    }
}
