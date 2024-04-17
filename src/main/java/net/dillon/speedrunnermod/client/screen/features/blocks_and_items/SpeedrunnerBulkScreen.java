package net.dillon.speedrunnermod.client.screen.features.blocks_and_items;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
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
public class SpeedrunnerBulkScreen extends AbstractFeatureScreen {

    public SpeedrunnerBulkScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunner_bulk").formatted(Formatting.AQUA), true, false);
    }

    @Override
    protected @NotNull String linesKey() {
        return "speedrunner_bulk";
    }

    @Override
    protected int getPageNumber() {
        return 15;
    }

    @Override
    protected @NotNull Identifier getImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/speedrunner_bulk.png");
    }

    @Override
    protected int getImageWidth() {
        return 32;
    }

    @Override
    protected int getImageHeight() {
        return 30;
    }

    @Override
    protected @NotNull Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    protected @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.BLOCKS_AND_ITEMS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}