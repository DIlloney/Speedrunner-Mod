package net.dillon.speedrunnermod.client.screen.features.more;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class BetterPiglinBarteringScreen extends AbstractFeatureScreen {

    public BetterPiglinBarteringScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.more.piglin_bartering"), false, false, true);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "piglin_bartering";
    }

    @Override
    public int getPageNumber() {
        return 7;
    }

    @Override
    protected void renderCustomImage(DrawContext context) {
        context.drawTexture(Identifier.of(SpeedrunnerMod.MOD_ID, "textures/gui/features/gui/better_piglin_bartering.png"), this.width / 2, 190, 0, 0, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
    }

    @Override
    protected int getImageWidth() {
        return 238;
    }

    @Override
    protected int getImageHeight() {
        return 114;
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
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.MORE;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}