package net.dillon.speedrunnermod.client.screen.features.more;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class CustomPanoramaScreen extends AbstractFeatureScreen {

    public CustomPanoramaScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.more.custom_panorama"), false, false, true);
    }

    @Override
    public @NotNull String linesKey() {
        return "custom_panorama";
    }

    @Override
    public int getPageNumber() {
        return 29;
    }

    @Override
    protected Identifier getDownscaledImage() {
        return Identifier.of(SpeedrunnerMod.MOD_ID, "textures/gui/features/gui/custom_panorama.png");
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