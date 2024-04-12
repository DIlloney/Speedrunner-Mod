package net.dillon.speedrunnermod.client.screen.features.doom_mode;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class GiantScreen extends AbstractFeatureScreen {

    public GiantScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.features.doom_mode.giant").formatted(Formatting.GREEN), 3, false, false);
    }

    @Override
    protected @NotNull String linesKey() {
        return "giant";
    }

    @Override
    protected void renderCustomImage(MatrixStack matrices) {
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/screens/giant.png"));
        drawTexture(matrices, this.width / 2 + 65, 180, 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
    }

    @Override
    protected int getButtonsWidth() {
        return this.width / 2 - 150;
    }

    @Override
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected int getImageWidth() {
        return 79;
    }

    @Override
    protected int getImageHeight() {
        return 128;
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
        return 5;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}