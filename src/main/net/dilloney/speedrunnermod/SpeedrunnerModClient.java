package net.dillon.speedrunnermod;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.option.CLModOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {

    public void onInitializeClient() {
        CLModOptions.loadClientConfig();

        ModItems.clinit();
    }

    public static CLModOptions clOptions() {
        return CLModOptions.CLOPTIONS;
    }

    public static void logException(Exception ex, String message) {
        System.err.printf("[Speedrunner Mod] %s (%s: %s)", message, ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }
}