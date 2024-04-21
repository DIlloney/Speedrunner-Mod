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
public class ArrowsExplodeBedsScreen extends AbstractFeatureScreen {

    public ArrowsExplodeBedsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.more.arrows_explode_beds"), false, false, true);
    }

    @Override
    public @NotNull String linesKey() {
        return "arrows_explode_beds";
    }

    @Override
    public int getPageNumber() {
        return 26;
    }

    @Override
    protected Identifier getDownscaledImage() {
        return new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/features/screenshots/arrows_destroy_beds.png");
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