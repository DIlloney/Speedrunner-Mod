package net.dillon.speedrunnermod.client.screen.features.blocksanditems;

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
public class DeadSpeedrunnerWoodScreen extends AbstractFeatureScreen {

    public DeadSpeedrunnerWoodScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items.dead_speedrunner_wood"), true, false);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "dead_speedrunner_wood";
    }

    @Override
    public int getPageNumber() {
        return 5;
    }

    @Override
    protected Identifier getImage() {
        return Identifier.of(SpeedrunnerMod.MOD_ID, "textures/gui/features/blocks/dead_speedrunner_wood.png");
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
    protected Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.BLOCKS_AND_ITEMS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}