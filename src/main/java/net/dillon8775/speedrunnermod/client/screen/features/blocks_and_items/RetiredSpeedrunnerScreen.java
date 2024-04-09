package net.dillon8775.speedrunnermod.client.screen.features.blocks_and_items;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class RetiredSpeedrunnerScreen extends AbstractFeatureScreen {

    public RetiredSpeedrunnerScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items.retired_speedrunner").formatted(Formatting.AQUA), 17, false, false);
    }

    @Override
    protected @NotNull String linesKey() {
        return "retired_speedrunner";
    }

    @Override
    protected void renderCustomImage(MatrixStack matrices) {
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/screens/retired_speedrunner.png"));
        drawTexture(matrices, this.width / 2 + 60, 180, 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
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
        return 60;
    }

    @Override
    protected int getImageHeight() {
        return 112;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.BLOCKS_AND_ITEMS;
    }

    @Override
    protected int getScreenLines() {
        return 3;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}