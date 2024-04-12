package net.dillon.speedrunnermod.client.screen.features.doom_mode;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class BasicsScreen extends AbstractFeatureScreen {

    public BasicsScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.features.doom_mode.basics").formatted(Formatting.RED), 1, false, false);
    }

    @Override
    protected @NotNull String linesKey() {
        return "basics";
    }

    @Override
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected int getImageWidth() {
        return 0;
    }

    @Override
    protected int getImageHeight() {
        return 0;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.DOOM_MODE;
    }

    @Override
    protected int getScreenLines() {
        return 3;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.STARTER;
    }
}