package net.dillon.speedrunnermod.client.screen.features;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;

public interface FeatureScreen {
    AbstractFeatureScreen screen(int pageNumber, Screen parent, GameOptions options);
}