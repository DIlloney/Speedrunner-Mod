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
    /**
     * <p>{@link net.dillon8775.speedrunnermod.mixin.client.Fog}</p>
     * <p>Fog configuration</p>
     * <p></p>
     * <p>{@link net.dillon8775.speedrunnermod.client.gui.widget}</p>
     * <p>Instant World Creation settings and function, <i>along with</i> the {@link ClientModOptions.ModButtonType} configuration <i>and</i> the social buttons option</p>
     * <p></p>
     * <p>{@link Keybindings}</p>
     * <p>Reset and toggle fog key functions</p>
     */
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