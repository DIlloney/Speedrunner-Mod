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
public class ResetKeyScreen extends AbstractFeatureScreen {

    public ResetKeyScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.more.reset_key"), true, false);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "reset_key";
    }

    @Override
    public int getPageNumber() {
        return 1;
    }

    @Override
    protected Identifier getImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/fast_world_creation_keybind.png");
    }

    @Override
    protected int getImageX() {
        return this.width / 2 - 125;
    }

    @Override
    protected int getImageY() {
        return 170;
    }

    @Override
    protected int getImageWidth() {
        return 256;
    }

    @Override
    protected int getImageHeight() {
        return 21;
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
        return ScreenType.STARTER;
    }
}