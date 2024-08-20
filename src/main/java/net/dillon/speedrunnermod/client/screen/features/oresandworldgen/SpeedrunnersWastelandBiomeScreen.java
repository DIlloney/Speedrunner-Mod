package net.dillon.speedrunnermod.client.screen.features.oresandworldgen;

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
public class SpeedrunnersWastelandBiomeScreen extends AbstractFeatureScreen {

    public SpeedrunnersWastelandBiomeScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.ores_and_worldgen.speedrunners_wasteland"), false, false, true);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunners_wasteland";
    }

    @Override
    public int getPageNumber() {
        return 1;
    }

    @Override
    protected Identifier getDownscaledImage() {
        return Identifier.of(SpeedrunnerMod.MOD_ID, "textures/gui/features/screenshots/speedrunners_wasteland_biome.png");
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
        return ScreenType.STARTER;
    }
}