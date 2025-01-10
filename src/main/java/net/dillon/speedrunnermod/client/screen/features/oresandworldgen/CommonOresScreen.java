package net.dillon.speedrunnermod.client.screen.features.oresandworldgen;

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

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

@Environment(EnvType.CLIENT)
public class CommonOresScreen extends AbstractFeatureScreen {

    public CommonOresScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.ores_and_worldgen.common_ores"), false, false, true);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "common_ores";
    }

    @Override
    public int getPageNumber() {
        return 5;
    }

    @Override
    protected Identifier getDownscaledImage() {
        return ofSpeedrunnerMod("textures/gui/features/screenshots/common_ores.png");
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
        return ScreenCategory.ORES_AND_WORLDGEN;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}