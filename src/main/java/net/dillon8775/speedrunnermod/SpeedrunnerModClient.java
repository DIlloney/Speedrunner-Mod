package net.dillon8775.speedrunnermod;

import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.mixin.client.Keybindings;
import net.dillon8775.speedrunnermod.option.CLModOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

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
     * <p>Instant World Creation settings and function, <i>along with</i> the {@link CLModOptions.ModButtonType} configuration <i>and</i> the social buttons option</p>
     * <p></p>
     * <p>{@link Keybindings}</p>
     * <p>Reset and toggle fog key functions</p>
     */
    public void onInitializeClient() {
        CLModOptions.loadClientConfig();

        ModItems.clinit();
        ModBlocks.clinit();
    }

    public static CLModOptions clOptions() {
        return CLModOptions.CLOPTIONS;
    }

    public static void logException(Exception ex, String message) {
        System.err.printf("[Speedrunner Mod] %s (%s: %s)", message, ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }
}