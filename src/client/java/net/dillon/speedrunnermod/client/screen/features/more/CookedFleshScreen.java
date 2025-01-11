package net.dillon.speedrunnermod.client.screen.features.more;

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
public class CookedFleshScreen extends AbstractFeatureScreen {

    public CookedFleshScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.more.cooked_flesh"), false, false, true);
    }

    @Override
    public @NotNull String linesKey() {
        return "cooked_flesh";
    }

    @Override
    public int getPageNumber() {
        return 12;
    }

    @Override
    protected void renderCustomImage(DrawContext context) {
        context.drawTexture(RenderLayer::getGuiTextured, ofSpeedrunnerMod("textures/gui/features/recipes/cooked_flesh_recipe.png"), this.width / 2, 190, 0, 0, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
    }

    @Override
    protected int getImageWidth() {
        return 238;
    }

    @Override
    protected int getImageHeight() {
        return 94;
    }

    @Override
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.MORE;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}