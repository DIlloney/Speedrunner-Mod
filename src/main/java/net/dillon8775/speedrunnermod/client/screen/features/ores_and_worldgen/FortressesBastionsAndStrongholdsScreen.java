<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.client.screen.features.ores_and_worldgen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenType;
import net.dillon8775.speedrunnermod.client.screen.features.blocks_and_items.SpeedrunnerIngotsScreen;
import net.dillon8775.speedrunnermod.client.screen.features.miscellaneous.ResetKeyScreen;
import net.dillon8775.speedrunnermod.client.screen.features.tools_and_armor.SpeedrunnerArmorScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class FortressesBastionsAndStrongholdsScreen extends AbstractFeatureScreen {

    public FortressesBastionsAndStrongholdsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.ores_and_worldgen.fortresses_bastions_and_strongholds").formatted(Formatting.RED), 7, false, false, new ResetKeyScreen(parent, options), Text.translatable("speedrunnermod.menu.features.miscellaneous"), new SpeedrunnerIngotsScreen(parent, options), Text.translatable("speedrunnermod.menu.features.blocks_and_items"), new SpeedrunnerArmorScreen(parent, options), Text.translatable("speedrunnermod.menu.features.tools_and_armor"), false, null, null);
    }

    @Override
    protected @NotNull String linesKey() {
        return "fortresses_bastions_and_strongholds";
    }

    @Override
    protected @NotNull Identifier getImage() {
        return null;
    }

    @Override
    protected void renderCustomImage(MatrixStack matrices) {
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/screens/stronghold_gen.png"));
        drawTexture(matrices, this.width / 2 - 260, 200, 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());

        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/screens/nether_fortress_gen.png"));
        drawTexture(matrices, this.width / 2 + 97, 200, 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
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
    protected @NotNull Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.ORES_AND_WORLDGEN;
    }

    @Override
    protected int getScreenLines() {
        return 4;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FINAL;
    }
=======
package net.dillon8775.speedrunnermod.client.screen.features.ores_and_worldgen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenType;
import net.dillon8775.speedrunnermod.client.screen.features.blocks_and_items.SpeedrunnerIngotsScreen;
import net.dillon8775.speedrunnermod.client.screen.features.miscellaneous.ResetKeyScreen;
import net.dillon8775.speedrunnermod.client.screen.features.tools_and_armor.SpeedrunnerArmorScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class FortressesBastionsAndStrongholdsScreen extends AbstractFeatureScreen {

    public FortressesBastionsAndStrongholdsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.ores_and_worldgen.fortresses_bastions_and_strongholds").formatted(Formatting.RED), 7, false, false, new ResetKeyScreen(parent, options), Text.translatable("speedrunnermod.menu.features.miscellaneous"), new SpeedrunnerIngotsScreen(parent, options), Text.translatable("speedrunnermod.menu.features.blocks_and_items"), new SpeedrunnerArmorScreen(parent, options), Text.translatable("speedrunnermod.menu.features.tools_and_armor"), false, null, null);
    }

    @Override
    protected @NotNull String linesKey() {
        return "fortresses_bastions_and_strongholds";
    }

    @Override
    protected @NotNull Identifier getImage() {
        return null;
    }

    @Override
    protected void renderCustomImage(MatrixStack matrices) {
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/screens/stronghold_gen.png"));
        drawTexture(matrices, this.width / 2 - 260, 200, 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());

        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/screens/nether_fortress_gen.png"));
        drawTexture(matrices, this.width / 2 + 97, 200, 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
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
    protected @NotNull Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.ORES_AND_WORLDGEN;
    }

    @Override
    protected int getScreenLines() {
        return 4;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FINAL;
    }
>>>>>>> Stashed changes
}