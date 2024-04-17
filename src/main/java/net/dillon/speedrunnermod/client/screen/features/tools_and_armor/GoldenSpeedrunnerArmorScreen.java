package net.dillon.speedrunnermod.client.screen.features.tools_and_armor;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class GoldenSpeedrunnerArmorScreen extends AbstractFeatureScreen {

    public GoldenSpeedrunnerArmorScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor.golden_speedrunner_armor").formatted(Formatting.GOLD), 2, true, false);
    }

    @Override
    protected @NotNull String linesKey() {
        return "golden_speedrunner_armor";
    }

    @Override
    protected void renderCustomImage(DrawContext context) {
        context.drawTexture(new Identifier("speedrunnermod:textures/gui/screens/golden_speedrunner_armor.png"), this.width / 2 + 65, 160, 0.0F, 0.0F, 83, 151, 83, 151);
    }

    @Override
    protected int getButtonsWidth() {
        return this.width / 2 - 175;
    }

    @Override
    protected @NotNull Identifier getImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/golden_speedrunner_chestplate.png");
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
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}