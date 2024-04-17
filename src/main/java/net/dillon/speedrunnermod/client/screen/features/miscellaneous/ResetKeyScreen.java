package net.dillon.speedrunnermod.client.screen.features.miscellaneous;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class ResetKeyScreen extends AbstractFeatureScreen {

    public ResetKeyScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.miscellaneous.reset_key").formatted(Formatting.AQUA), 1, false, false);
    }

    @Override
    protected @NotNull String linesKey() {
        return "reset_key";
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
        return ScreenCategories.MISCELLANEOUS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.STARTER;
    }
}