package com.dilloney.speedrunnermod;

import com.dilloney.speedrunnermod.registry.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModRegistry.registerFabricModelPredicateProviderRegistries();
    }
}
