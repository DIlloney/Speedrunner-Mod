package com.dilloney.speedrunnermod;

import com.dilloney.speedrunnermod.util.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {

    public static MinecraftClient client;
    public static double minBrightness = 1.0;
    public static double maxBrightness = 5.0;

    @Override
    public void onInitializeClient() {
        ModRegistry.registerModels();
    }
}
