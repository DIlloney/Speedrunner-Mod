package com.dilloney.speedrunnermod.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class BrightnessFeature {

    public static MinecraftClient client;
    public static double minBrightness = 1.0;
    public static double maxBrightness = 5.0;
}
