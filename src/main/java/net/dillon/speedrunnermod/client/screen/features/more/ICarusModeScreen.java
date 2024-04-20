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
public class ICarusModeScreen extends AbstractFeatureScreen {

    public ICarusModeScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.more.icarus_mode"), false, false, true);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "icarus_mode";
    }

    @Override
    public int getPageNumber() {
        return 5;
    }

    @Override
    protected Identifier getDownscaledImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/icarus_mode.png");
    }

    @Override
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected int getImageWidth() {
        return 32;
    }

    @Override
    protected int getImageHeight() {
        return 27;
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