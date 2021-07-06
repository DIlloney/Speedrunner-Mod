package com.dilloney.speedrunnermod.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.SERVER)
public class SpeedrunnerModServer implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        System.out.println("Client needs both the Speedrunner Mod and Fabric API to use the mod on the server.");
        System.out.println("Speedrunner Mod loaded successfully for server! version = 1.15 | mcversion = 1.17.1");
    }
}
