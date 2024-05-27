package net.dillon.speedrunnermod;

import net.dillon.speedrunnermod.client.keybind.ModKeybindings;
import net.dillon.speedrunnermod.client.render.ModRenderers;
import net.dillon.speedrunnermod.option.BrokenModOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import static net.dillon.speedrunnermod.SpeedrunnerMod.*;

/**
 * The main class file for the client-side of the {@code The Speedrunner Mod}.
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

        if (options().main.leaderboardsMode && !isSpeedrunIGTLoaded()) {
            speedrunIGTMissing = true;
            warn("Detected that SpeedrunIGT is not loaded, you should probably download this mod if you would like to submit speedruns to the leaderboards.");
        }

        ModKeybindings.clinit();

        info("Client-side speedrunner mod has successfully loaded!");
    }

    /**
     * Checks if {@code SpeedrunIGT} mod is loaded.
     */
    private static boolean isSpeedrunIGTLoaded() {
        return FabricLoader.getInstance().isModLoaded("speedrunigt");
    }

    /**
     * Checks if the {@code Simple Keybinds} mod is loaded.
     */
    public static boolean isSimpleKeybindsLoaded() {
        return FabricLoader.getInstance().isModLoaded("simplekeybinds");
    }

    /**
     * Fixes broken speedrunner mod options.
     * <p>See {@link net.dillon.speedrunnermod.client.screen.SafeBootScreen} for more.</p>
     */
    public static void fixOptions() {
        if (BrokenModOptions.structureSpawnRates) {
            options().main.structureSpawnRates = ModOptions.StructureSpawnRates.COMMON;
        }

        if (BrokenModOptions.blockBreakingMultiplier) {
            options().main.blockBreakingMultiplier = 1;
        }

        if (BrokenModOptions.strongholdPortalRoomCount) {
            options().main.strongholdPortalRoomCount = 3;
        }

        if (BrokenModOptions.strongholdLibraryCount) {
            options().main.strongholdLibraryCount = 2;
        }

        if (BrokenModOptions.netherPortalCooldown) {
            options().main.netherPortalCooldown = 2;
        }

        if (BrokenModOptions.mobSpawningRate) {
            options().main.mobSpawningRate = ModOptions.MobSpawningRate.HIGH;
        }

        if (BrokenModOptions.leaderboards) {
            options().main.leaderboardsMode = false;
        }

        if (BrokenModOptions.panorama) {
            options().client.panorama = ModOptions.Panorama.SPEEDRUNNER_MOD;
        }

        if (BrokenModOptions.itemMessages) {
            options().client.itemMessages = ModOptions.ItemMessages.CHAT;
        }

        if (BrokenModOptions.modButtonType) {
            options().client.modButtonType = ModOptions.ModButtonType.LOGO;
        }

        if (BrokenModOptions.gameMode) {
            options().client.gameMode = ModOptions.GameMode.SURVIVAL;
        }

        if (BrokenModOptions.difficulty) {
            options().client.difficulty = ModOptions.Difficulty.EASY;
        }

        ModOptions.saveConfig();
    }

    /**
     * Gets the minimum brightness amount for the speedrunner mod.
     */
    public static double getMinBrightness() {
        return 0.5D;
    }

    /**
     * Gets the maximum brightness amount for the speedrunner mod.
     */
    public static double getMaxBrightness() {
        return 12.0D;
    }
}