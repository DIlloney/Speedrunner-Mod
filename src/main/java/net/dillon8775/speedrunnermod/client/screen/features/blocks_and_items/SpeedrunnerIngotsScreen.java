package net.dillon8775.speedrunnermod.client.screen.features.blocks_and_items;

import net.dillon8775.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class SpeedrunnerIngotsScreen extends AbstractFeatureScreen {

    public SpeedrunnerIngotsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_ingots").formatted(Formatting.AQUA), 1, true, false);
    }

    @Override
    protected @NotNull String linesKey() {
        return "speedrunner_ingots";
    }

    @Override
    protected @NotNull Identifier getImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/speedrunner_ingot.png");
    }

    @Override
    protected int getImageWidth() {
        return 34;
    }

    @Override
    protected int getImageHeight() {
        return 26;
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
        return 3;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.STARTER;
    }
}