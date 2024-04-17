package net.dillon.speedrunnermod.client.screen.features;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.CustomButtonListWidget;
import net.dillon.speedrunnermod.client.screen.features.ores_and_worldgen.*;
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
public class OresAndWorldgenScreen extends AbstractModScreen {

    public OresAndWorldgenScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.ores_and_worldgen"));
    }

    @Override
    protected void init() {
        this.buttonList = this.addDrawableChild(new CustomButtonListWidget(this.client, this.width, this.height - 64, 32, 25));

        this.clearButtons();

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.ORES_AND_WORLDGEN, "speedrunners_wasteland").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnersWastelandBiomeScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.ORES_AND_WORLDGEN, "speedrunner_ores").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerOresScreen(this.parent, this.options));
        }).build());

        height += 24;
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.ORES_AND_WORLDGEN, "experience_ores").copy().formatted(Formatting.GREEN), (button) -> {
            this.client.setScreen(new ExperienceOresScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.ORES_AND_WORLDGEN, "igneous_ores").copy().formatted(Formatting.DARK_GRAY), (button) -> {
            this.client.setScreen(new IgneousOresScreen(this.parent, this.options));
        }).build());

        height += 24;
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.ORES_AND_WORLDGEN, "common_ores").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new CommonOresScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(Text.literal("Structure Spawn Rates!").copy().formatted(Formatting.GREEN), (button) -> {
            this.client.setScreen(new StructuresScreen(this.parent, this.options));
        }).build());

        height += 24;
        this.buttons.add(ButtonWidget.builder(Text.literal("Structure Generation").copy().formatted(Formatting.RED), (button) -> {
            this.client.setScreen(new FortressesBastionsAndStrongholdsScreen(this.parent, this.options));
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