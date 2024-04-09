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
public class PiglinAwakenerScreen extends AbstractFeatureScreen {

    public PiglinAwakenerScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items.piglin_awakener").formatted(Formatting.GOLD), 11, true, true);
    }

    @Override
    protected @NotNull String linesKey() {
        return "piglin_awakener";
    }

    @Override
    protected @NotNull Identifier getImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/piglin_awakener.png");
    }

    @Override
    protected int getImageWidth() {
        return 32;
    }

    @Override
    protected int getImageHeight() {
        return 32;
    }

    @Override
    protected @NotNull Identifier getCraftingRecipeImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/piglin_awakener_crafting_recipe.png");
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.BLOCKS_AND_ITEMS;
    }

    @Override
    protected int getScreenLines() {
        return 4;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}