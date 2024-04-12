package net.dillon.speedrunnermod.client.screen.features.ores_and_worldgen;

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
public class StructuresScreen extends AbstractFeatureScreen {

    public StructuresScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.ores_and_worldgen.structures").formatted(Formatting.GREEN), 6, false, false);
    }

    @Override
    protected @NotNull String linesKey() {
        return "structures";
    }

    @Override
    protected void renderCustomImage(MatrixStack matrices) {
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/screens/structures.png"));
        drawTexture(matrices, this.width / 2, 170, 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
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
        return 240;
    }

    @Override
    protected int getImageHeight() {
        return 135;
    }

    @Override
    protected @NotNull Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.ORES_AND_WORLDGEN;
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