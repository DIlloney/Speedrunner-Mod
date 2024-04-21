package net.dillon.speedrunnermod.client.screen.features.tools_and_armor;

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
public class DragonsSwordScreen extends AbstractFeatureScreen {

    public DragonsSwordScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor.dragons_sword"), true, true);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "dragons_sword";
    }

    @Override
    public int getPageNumber() {
        return 4;
    }

    @Override
    protected @NotNull Identifier getImage() {
        return new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/features/items/dragons_sword.png");
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
    protected @NotNull Identifier getCraftingRecipeImage() {
        return new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/features/recipes/dragons_sword_crafting_recipe.png");
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