package net.dilloney.speedrunnermod.client.impl;

import net.dilloney.speedrunnermod.client.ModMenuScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuImplementation implements ModMenuApi {

    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ModMenuScreen::new;
    }
}
