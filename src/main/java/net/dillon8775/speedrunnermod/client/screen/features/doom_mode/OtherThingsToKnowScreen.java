package net.dillon8775.speedrunnermod.client.screen.features.doom_mode;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.screen.RestartRequiredScreen;
import net.dillon8775.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon8775.speedrunnermod.client.screen.features.ScreenType;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Environment(EnvType.CLIENT)
public class OtherThingsToKnowScreen extends AbstractFeatureScreen {

    public OtherThingsToKnowScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.doom_mode.other_things_to_know").formatted(Formatting.RED), 5, false, false);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(new ButtonWidget(this.getButtonsWidth(), height, 150, 20, ModTexts.OK, (buttonWidget) -> {
            this.close();
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsWidth(), height, 150, 20, ModTexts.ENABLE_DOOM_MODE, (buttonWidget) -> {
            if (!DOOM_MODE) {
                this.client.setScreen(new RestartRequiredScreen(this.parent, MinecraftClient.getInstance().options));
            }
            SpeedrunnerMod.options().main.doomMode = true;
        }));
    }

    @Override
    protected @NotNull String linesKey() {
        return "other_things_to_know";
    }

    @Override
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected int getImageWidth() {
        return 0;
    }

    @Override
    protected int getImageHeight() {
        return 0;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    protected @NotNull ScreenCategories getScreenCategory() {
        return ScreenCategories.DOOM_MODE;
    }

    @Override
    protected int getScreenLines() {
        return 5;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}