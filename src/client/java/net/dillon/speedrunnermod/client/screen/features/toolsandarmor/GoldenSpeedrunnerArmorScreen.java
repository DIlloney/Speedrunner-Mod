package net.dillon.speedrunnermod.client.screen.features.toolsandarmor;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

@Environment(EnvType.CLIENT)
public class GoldenSpeedrunnerArmorScreen extends AbstractFeatureScreen {

    public GoldenSpeedrunnerArmorScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor.golden_speedrunner_armor"), true, false, true);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "golden_speedrunner_armor";
    }

    @Override
    public int getPageNumber() {
        return 2;
    }

    @Override
    protected void renderCustomImage(DrawContext context) {
        context.drawTexture(RenderLayer::getGuiTextured, ofSpeedrunnerMod("textures/gui/features/other/golden_speedrunner_armor.png"), this.width / 2 + 65, 160, 0.0F, 0.0F, 83, 151, 83, 151);
    }

    @Override
    protected @NotNull Identifier getImage() {
        return ofSpeedrunnerMod("textures/gui/features/items/golden_speedrunner_chestplate.png");
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
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}