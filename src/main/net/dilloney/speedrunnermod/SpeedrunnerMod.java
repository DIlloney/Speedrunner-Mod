package net.dilloney.speedrunnermod;

import net.dilloney.speedrunnermod.option.ModOptions;
import net.dilloney.speedrunnermod.option.OptionsFileManager;
import net.dilloney.speedrunnermod.util.ModRegistry;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpeedrunnerMod implements ModInitializer {

    public static final String MOD_VERSION = "v1.3.3";
    public static final String MINECRAFT_VERSION = "1.16.5";
    public static final Logger LOGGER = LogManager.getLogger();
    public static ModOptions OPTIONS = OptionsFileManager.getMain();
    public static ModOptions.WorldOptions WORLD_OPTIONS = OptionsFileManager.getWorld();
    public static ModOptions.MiscOptions MISC_OPTIONS = OptionsFileManager.getMisc();
    public static ModOptions.TimerOptions TIMER_OPTIONS = OptionsFileManager.TimerFileManager.getTimer();

    public void onInitialize() {
        ModRegistry.loadOptions();
        ModRegistry.loadItems();
        ModRegistry.loadBlocks();
        ModRegistry.loadConfiguredFeatures();
        ModRegistry.loadItemTags();
        ModRegistry.loadBlockTags();
        ModRegistry.loadMiscellaneous();
        LOGGER.info("The Speedrunner Mod has been loaded successfully! modVersion = " + MOD_VERSION + " on " + MINECRAFT_VERSION);
        LOGGER.info("Thank you for using this mod! Enjoy Speedrunning! :)");
    }
}
