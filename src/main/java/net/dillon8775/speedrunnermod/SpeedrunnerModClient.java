package net.dillon8775.speedrunnermod;

import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.mixin.client.Keybindings;
import net.dillon8775.speedrunnermod.option.ClientModOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

/**
 * The main class file for the client-side of the {@code The Speedrunner Mod}.
 */
@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {
    public static final int DOUBLE_LINED_SCREEN_LINE_1 = 110;
    public static final int DOUBLE_LINED_SCREEN_LINE_2 = 130;
    public static final int DOUBLE_LINED_FINAL_SCREEN_LINE_1 = 90;
    public static final int DOUBLE_LINED_FINAL_SCREEN_LINE_2 = 110;
    public static final int TRIPLE_LINED_SCREEN_LINE_1 = 90;
    public static final int TRIPLE_LINED_SCREEN_LINE_2 = 110;
    public static final int TRIPLE_LINED_SCREEN_LINE_3 = 130;
    public static final int QUADRUPLE_LINED_SCREEN_LINE_1 = 80;
    public static final int QUADRUPLE_LINED_SCREEN_LINE_2 = 100;
    public static final int QUADRUPLE_LINED_SCREEN_LINE_3 = 120;
    public static final int QUADRUPLE_LINED_SCREEN_LINE_4 = 140;

    /**
     * Initializes all the client-side {@code speedrunner mod} renderers, configurations, etc.
     */
    @Override
    public void onInitializeClient() {
        ModItems.clinit();
        ModBlocks.clinit();

        ClientModOptions.loadClientConfig();

        info("Client-side options have successfully loaded!");
    }

    /**
     * The {@code Speedrunner Mod} client options method.
     */
    public static ClientModOptions clientOptions() {
        return ClientModOptions.CLIENT_OPTIONS;
    }

    /**
     * Sends error message, used in {@link Keybindings}.
     */
    public static void logException(Exception ex, String message) {
        System.err.printf("[Speedrunner Mod] %s (%s: %s)", message, ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }
}