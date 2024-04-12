package net.dillon.speedrunnermod.client.screen.features.blocks_and_items;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
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
public class MoreBoatsScreen extends AbstractFeatureScreen {

    public MoreBoatsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items.more_boats").formatted(Formatting.AQUA), 5, false, false);
    }

    @Override
    protected @NotNull String linesKey() {
        return "more_boats";
    }

    @Override
    protected void renderCustomImage(MatrixStack matrices) {
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/screens/more_boats.png"));
        drawTexture(matrices, this.width / 2, 200, 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
    }

    @Override
    protected int getButtonsWidth() {
        return this.width / 2 - 175;
    }

    @Override
    protected @NotNull Identifier getImage() {
        return null;
    }

    @Override
    protected int getImageWidth() {
        return 233;
    }

    @Override
    protected int getImageHeight() {
        return 84;
    }

    @Override
    protected @NotNull Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.BLOCKS_AND_ITEMS;
    }

    @Override
    protected int getScreenLines() {
        return 2;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}