package com.dilloney.speedrunnermod;

import com.dilloney.speedrunnermod.registry.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModRegistry.ClientRegistry.registerFabricModelPredicateProviderRegistries();
        ModRegistry.ClientRegistry.registerBrightnessFeatureControls();

        System.out.println("Speedrunner Mod loaded successfully! modVersion = 1.16 | minecraftVersion = 1.17x");
    }
}
