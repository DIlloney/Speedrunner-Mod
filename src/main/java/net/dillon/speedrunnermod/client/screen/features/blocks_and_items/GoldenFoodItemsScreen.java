package net.dillon.speedrunnermod.client.screen.features.blocks_and_items;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.dillon.speedrunnermod.client.screen.features.miscellaneous.ResetKeyScreen;
import net.dillon.speedrunnermod.client.screen.features.ores_and_worldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon.speedrunnermod.client.screen.features.tools_and_armor.SpeedrunnerArmorScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class GoldenFoodItemsScreen extends AbstractFeatureScreen {

    public GoldenFoodItemsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items.golden_food_items").formatted(Formatting.GOLD), true, true, new SpeedrunnerArmorScreen(parent, options), Text.translatable("speedrunnermod.menu.features.tools_and_armor"), new SpeedrunnersWastelandBiomeScreen(parent, options), Text.translatable("speedrunnermod.menu.features.ores_and_worldgen"), new ResetKeyScreen(parent, options), Text.translatable("speedrunnermod.menu.features.miscellaneous"), false, null, null);
    }

    @Override
    protected @NotNull String linesKey() {
        return "golden_food_items";
    }

    @Override
    protected int getPageNumber() {
        return 18;
    }

    @Override
    protected @NotNull Identifier getImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/golden_food_item.png");
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
        return new Identifier("speedrunnermod:textures/gui/screens/golden_food_item_crafting_recipe.png");
    }

    @Override
    protected @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.BLOCKS_AND_ITEMS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FINAL;
    }
}