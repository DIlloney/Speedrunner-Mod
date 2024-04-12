package net.dillon.speedrunnermod.client.screen.features.doom_mode;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.screen.RestartRequiredScreen;
import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategories;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.dillon.speedrunnermod.client.util.ModTexts;
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

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Environment(EnvType.CLIENT)
public class OtherThingsToKnowScreen extends AbstractFeatureScreen {

    public OtherThingsToKnowScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.doom_mode.other_things_to_know").formatted(Formatting.RED), 5, false, false);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(ButtonWidget.builder(ModTexts.OK, button -> this.close()).dimensions(this.getButtonsWidth(), height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.ENABLE_DOOM_MODE, button -> {
            if (!DOOM_MODE) {
                this.client.setScreen(new RestartRequiredScreen(this.parent, MinecraftClient.getInstance().options));
            }
            SpeedrunnerMod.options().main.doomMode = true;
        }).dimensions(this.getButtonsWidth(), height, 150, 20).build());
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