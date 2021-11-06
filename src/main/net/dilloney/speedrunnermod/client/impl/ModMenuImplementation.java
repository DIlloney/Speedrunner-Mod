package net.dilloney.speedrunnermod.client.impl;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.dilloney.speedrunnermod.client.ModMenuScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ModMenuImplementation implements ModMenuApi {

    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (parent) -> new ModMenuScreen(parent, MinecraftClient.getInstance().options);
    }
}