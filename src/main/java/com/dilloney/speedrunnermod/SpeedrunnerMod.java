package com.dilloney.speedrunnermod;

import com.dilloney.speedrunnermod.option.OptionsFileHelper;
import com.dilloney.speedrunnermod.option.ModOptions;
import com.dilloney.speedrunnermod.option.OptionsFileManager;
import com.dilloney.speedrunnermod.util.ModRegistry;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpeedrunnerMod implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static ModOptions OPTIONS = OptionsFileManager.get();

    @Override
    public void onInitialize() {
        OptionsFileManager.load();
        OptionsFileHelper.fix();
        if (OPTIONS.officialSpeedrunMode) {
            OptionsFileHelper.fixForOfficialSpeedruns();
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
        LOGGER.info("Speedrunner Mod loaded successfully! modVersion = v1.3.1 | minecraftVersion = 1.17x");
        LOGGER.info("See wiki for more information: https://sites.google.com/view/speedrunnermod");
    }
}
