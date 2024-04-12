package net.dillon.speedrunnermod.client.screen.features.tools_and_armor;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.dillon.speedrunnermod.client.screen.features.blocks_and_items.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.client.screen.features.miscellaneous.ResetKeyScreen;
import net.dillon.speedrunnermod.client.screen.features.ores_and_worldgen.SpeedrunnersWastelandBiomeScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class WitherSwordScreen extends AbstractFeatureScreen {

    public WitherSwordScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor.wither_sword").formatted(Formatting.GRAY), 6, true, true, new SpeedrunnersWastelandBiomeScreen(parent, options), Text.translatable("speedrunnermod.menu.features.ores_and_worldgen"), new ResetKeyScreen(parent, options), Text.translatable("speedrunnermod.menu.features.miscellaneous"), new SpeedrunnerIngotsScreen(parent, options), Text.translatable("speedrunnermod.menu.features.blocks_and_items"), false, null, null);
    }

    @Override
    protected @NotNull String linesKey() {
        return "wither_sword";
    }

    @Override
    protected @NotNull Identifier getImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/wither_sword.png");
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
        return new Identifier("speedrunnermod:textures/gui/screens/wither_sword_crafting_recipe.png");
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.TOOLS_AND_ARMOR;
    }

    @Override
    protected int getScreenLines() {
        return 3;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FINAL;
    }
}