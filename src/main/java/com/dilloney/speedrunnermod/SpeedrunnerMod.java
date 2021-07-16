package com.dilloney.speedrunnermod;

import com.dilloney.speedrunnermod.config.DefaultModConfig;
import com.dilloney.speedrunnermod.config.ModConfigManager;
import com.dilloney.speedrunnermod.registry.ModRegistry;
import net.fabricmc.api.ModInitializer;

public class SpeedrunnerMod implements ModInitializer {

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

        System.out.println("Difficulty is set to 1 by default. 1 = easy, 2 = medium, 3 = hard, and 4 = extreme.");
        System.out.println("Anything set higher or lower than any of these numbers will act as if it is set to easy difficulty.");
        System.out.println("A restart is required after modifying the config in order for changes to take effect.");
        System.out.println("If your config somehow breaks, just delete the file and restart your game.");
    }

    public static DefaultModConfig CONFIG = ModConfigManager.get();
}
