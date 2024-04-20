package net.dillon.speedrunnermod.client.screen.features.more;

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
public class BetterPiglinBarteringScreen extends AbstractFeatureScreen {

    public BetterPiglinBarteringScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.more.piglin_bartering"), false, false, true);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "piglin_bartering";
    }

    @Override
    public int getPageNumber() {
        return 7;
    }

    @Override
    protected Identifier getDownscaledImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/better_piglin_bartering.png");
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
        return ScreenCategory.MORE;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}