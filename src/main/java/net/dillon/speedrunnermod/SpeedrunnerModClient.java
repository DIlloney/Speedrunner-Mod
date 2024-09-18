package net.dillon.speedrunnermod;

import net.dillon.speedrunnermod.client.keybind.ModKeybindings;
import net.dillon.speedrunnermod.client.render.ModRenderers;
import net.dillon.speedrunnermod.option.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import static net.dillon.speedrunnermod.SpeedrunnerMod.*;

/**
 * The home initializer for the client-side of the Speedrunner Mod.
 */
@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {
    public static boolean speedrunIGTMissing = false;

    /**
     * Initializes all the client-side {@code speedrunner mod} renderers, configurations, etc.
     */
    @Override
    public void onInitializeClient() {
        ModRenderers.init();

        if (options().main.leaderboardsMode.getCurrentValue() && !isSpeedrunIGTLoaded()) {
            speedrunIGTMissing = true;
            warn("Detected that SpeedrunIGT is not loaded, you should probably download this mod if you would like to submit speedruns to the leaderboards.");
        }

        ModKeybindings.clinit();

        info("Client-side speedrunner mod has successfully loaded!");
    }

    /**
     * Returns true if the {@code SpeedrunIGT} mod is loaded.
     */
    public static boolean isSpeedrunIGTLoaded() {
        return FabricLoader.getInstance().isModLoaded("speedrunigt");
    }

    /**
     * Returns true if the {@code Simple Keybinds} mod is loaded.
     */
    public static boolean isSimpleKeybindsLoaded() {
        return FabricLoader.getInstance().isModLoaded("simplekeybinds");
    }

    /**
     * Fixes broken speedrunner mod options.
     */
    public static void fixOptions() {
        if (BrokenModOptions.structureSpawnRates) {
            options().main.structureSpawnRates = StructureSpawnRate.COMMON;
        }

        if (BrokenModOptions.blockBreakingMultiplier) {
            options().main.blockBreakingMultiplier.reset();
        }

        if (BrokenModOptions.strongholdPortalRoomCount) {
            options().main.strongholdPortalRoomCount.reset();
        }

        if (BrokenModOptions.strongholdLibraryCount) {
            options().main.strongholdLibraryCount.reset();
        }

        if (BrokenModOptions.netherPortalCooldown) {
            options().main.netherPortalDelay.reset();
        }

        if (BrokenModOptions.mobSpawningRate) {
            options().main.mobSpawningRate = MobSpawningRate.HIGH;
        }

        if (BrokenModOptions.leaderboards) {
            options().main.leaderboardsMode.reset();
        }

        if (BrokenModOptions.speedrunnersWastelandBiomeWeight) {
            options().advanced.speedrunnersWastelandBiomeWeight.reset();
        }

        if (BrokenModOptions.iCarusFireworksInventorySlot) {
            options().advanced.iCarusFireworksInventorySlot.reset();
        }

        if (BrokenModOptions.infiniPearlInventorySlot) {
            options().advanced.infiniPearlInventorySlot.reset();
        }

        if (BrokenModOptions.itemMessages) {
            options().client.itemMessages = ItemMessages.CHAT;
        }

        if (BrokenModOptions.gameMode) {
            options().client.gameMode = GameMode.SURVIVAL;
        }

        if (BrokenModOptions.difficulty) {
            options().client.difficulty = Difficulty.EASY;
        }

        ModOptions.saveConfig();
    }

    /**
     * Returns the {@code minimum brightness} value for the speedrunner mod.
     */
    public static double getMinBrightness() {
        return options().advanced.minimumBrightness.getCurrentValue();
    }

    /**
     * Returns the {@code maximum brightness} value for the speedrunner mod.
     */
    public static double getMaxBrightness() {
        return options().advanced.maximumBrightness.getCurrentValue();
    }
}