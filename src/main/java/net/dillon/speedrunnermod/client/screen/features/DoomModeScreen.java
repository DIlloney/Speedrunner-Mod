package net.dillon.speedrunnermod.client.screen.features;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.CustomButtonListWidget;
import net.dillon.speedrunnermod.client.screen.features.doom_mode.*;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class DoomModeScreen extends AbstractModScreen {

    public DoomModeScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.doom_mode"));
    }

    @Override
    protected void init() {
        this.buttonList = this.addDrawableChild(new CustomButtonListWidget(this.client, this.width, this.height - 64, 32, 25));

        this.clearButtons();

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.DOOM_MODE, "basics").copy().formatted(Formatting.RED), (button) -> {
            this.client.setScreen(new BasicsScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.DOOM_MODE, "bosses").copy().formatted(Formatting.LIGHT_PURPLE), (button) -> {
            this.client.setScreen(new BossesScreen(this.parent, this.options));
        }).build());

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.DOOM_MODE, "giant").copy().formatted(Formatting.GREEN).copy().formatted(Formatting.BOLD), (button) -> {
            this.client.setScreen(new GiantScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.DOOM_MODE, "doom_blocks").copy().formatted(Formatting.RED), (button) -> {
            this.client.setScreen(new DoomBlocksScreen(this.parent, this.options));
        }).build());

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.DOOM_MODE, "other_things_to_know"), (button) -> {
            this.client.setScreen(new OtherThingsToKnowScreen(this.parent, this.options));
        }).build());

        super.init();
    }

    @Override
    public void close() {
        this.client.setScreen(new FeaturesScreen(this.parent, this.options));
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}