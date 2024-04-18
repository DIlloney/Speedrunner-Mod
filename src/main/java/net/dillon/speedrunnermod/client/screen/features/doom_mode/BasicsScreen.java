package net.dillon.speedrunnermod.client.screen.features.doom_mode;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class BasicsScreen extends AbstractFeatureScreen {

    public BasicsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.doom_mode.basics"), false, false);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "basics";
    }

    @Override
    public int getPageNumber() {
        return 1;
    }

    @Override
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.DOOM_MODE;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.STARTER;
    }
}