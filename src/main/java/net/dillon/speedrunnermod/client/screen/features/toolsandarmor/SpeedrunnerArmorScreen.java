package net.dillon.speedrunnermod.client.screen.features.toolsandarmor;

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
public class SpeedrunnerArmorScreen extends AbstractFeatureScreen {

    public SpeedrunnerArmorScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor.speedrunner_armor"), true, false, true);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunner_armor";
    }

    @Override
    public int getPageNumber() {
        return 1;
    }

    @Override
    protected void renderCustomImage(DrawContext context) {
        context.drawTexture(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/features/other/speedrunner_armor.png"), this.width / 2 + 65, 160, 0.0F, 0.0F, 83, 151, 83, 151);
    }

    @Override
    protected @NotNull Identifier getImage() {
        return new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/features/items/speedrunner_chestplate.png");
    }

    @Override
    protected int getImageWidth() {
        return 32;
    }

    @Override
    protected int getImageHeight() {
        return 30;
    }

    @Override
    protected @NotNull Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.STARTER;
    }
}