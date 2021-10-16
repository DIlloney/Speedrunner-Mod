package net.dilloney.speedrunnermod;

import net.dilloney.speedrunnermod.util.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {

    public static double minBrightness = 1.0;
    public static double maxBrightness = 5.0;

    public void onInitializeClient() {
        ModRegistry.loadTimer();
        ModRegistry.loadModels();
    }
}
