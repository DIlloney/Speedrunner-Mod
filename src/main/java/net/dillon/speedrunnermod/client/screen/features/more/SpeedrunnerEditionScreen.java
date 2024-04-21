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
public class SpeedrunnerEditionScreen extends AbstractFeatureScreen {

    public SpeedrunnerEditionScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.more.speedrunner_edition"), false, false, true);
    }

    @Override
    public @NotNull String linesKey() {
        return "speedrunner_edition";
    }

    @Override
    public int getPageNumber() {
        return 27;
    }

    @Override
    protected void renderCustomImage(DrawContext context) {
        context.drawTexture(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/features/gui/speedrunner_edition_text.png"), this.width / 2 + 10, 220, 0, 0, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
    }

    @Override
    protected int getImageWidth() {
        return 238;
    }

    @Override
    protected int getImageHeight() {
        return 47;
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