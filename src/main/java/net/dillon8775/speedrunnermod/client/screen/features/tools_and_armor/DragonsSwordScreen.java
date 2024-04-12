<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.client.screen.features.tools_and_armor;

import net.dillon8775.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class DragonsSwordScreen extends AbstractFeatureScreen {

    public DragonsSwordScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor.dragons_sword").formatted(Formatting.LIGHT_PURPLE), 3, true, true);
    }

    @Override
    protected @NotNull String linesKey() {
        return "dragons_sword";
    }

    @Override
    protected @NotNull Identifier getImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/dragons_sword.png");
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
        return new Identifier("speedrunnermod:textures/gui/screens/dragons_sword_crafting_recipe.png");
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.TOOLS_AND_ARMOR;
    }

    @Override
    protected int getScreenLines() {
        return 3;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
=======
package net.dillon8775.speedrunnermod.client.screen.features.tools_and_armor;

import net.dillon8775.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class DragonsSwordScreen extends AbstractFeatureScreen {

    public DragonsSwordScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor.dragons_sword").formatted(Formatting.LIGHT_PURPLE), 3, true, true);
    }

    @Override
    protected @NotNull String linesKey() {
        return "dragons_sword";
    }

    @Override
    protected @NotNull Identifier getImage() {
        return new Identifier("speedrunnermod:textures/gui/screens/dragons_sword.png");
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
        return new Identifier("speedrunnermod:textures/gui/screens/dragons_sword_crafting_recipe.png");
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.TOOLS_AND_ARMOR;
    }

    @Override
    protected int getScreenLines() {
        return 3;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
>>>>>>> Stashed changes
}