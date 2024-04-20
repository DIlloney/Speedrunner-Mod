package net.dillon.speedrunnermod.client.screen.features.ores_and_worldgen;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.dillon.speedrunnermod.client.screen.features.blocks_and_items.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.client.screen.features.more.ResetKeyScreen;
import net.dillon.speedrunnermod.client.screen.features.tools_and_armor.SpeedrunnerArmorScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class FortressesBastionsAndStrongholdsScreen extends AbstractFeatureScreen {

    public FortressesBastionsAndStrongholdsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.ores_and_worldgen.fortresses_bastions_and_strongholds"), false, false, new ResetKeyScreen(parent, options), Text.translatable("speedrunnermod.menu.features.more"), new SpeedrunnerIngotsScreen(parent, options), Text.translatable("speedrunnermod.menu.features.blocks_and_items"), new SpeedrunnerArmorScreen(parent, options), Text.translatable("speedrunnermod.menu.features.tools_and_armor"), false, null, null);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "fortresses_bastions_and_strongholds";
    }

    @Override
    public int getPageNumber() {
        return this.getMaxPages();
    }

    @Override
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected void renderCustomImage(DrawContext context) {
        context.drawTexture(new Identifier("speedrunnermod:textures/gui/screens/stronghold_gen.png"), this.width / 2 - 260, 200, 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
        context.drawTexture(new Identifier("speedrunnermod:textures/gui/screens/nether_fortress_gen.png"), this.width / 2 + 97, 200, 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
    }

    @Override
    protected int getImageWidth() {
        return 165;
    }

    @Override
    protected int getImageHeight() {
        return 93;
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
        return ScreenType.FINAL;
    }
}