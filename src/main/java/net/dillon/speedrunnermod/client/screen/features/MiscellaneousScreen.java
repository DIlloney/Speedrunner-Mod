package net.dillon.speedrunnermod.client.screen.features;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.CustomButtonListWidget;
import net.dillon.speedrunnermod.client.screen.features.miscellaneous.*;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class MiscellaneousScreen extends AbstractModScreen {

    public MiscellaneousScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.miscellaneous"));
    }

    @Override
    protected void init() {
        this.buttonList = this.addDrawable(new CustomButtonListWidget(this.client, this.width, this.height - 64, 32, 25));

        this.clearButtons();

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategory.MISCELLANEOUS, "reset_key").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new ResetKeyScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategory.MISCELLANEOUS, "fog_key").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new FogKeyScreen(this.parent, this.options));
        }).build());

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategory.MISCELLANEOUS, "faster_block_breaking").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new FasterBlockBreakingScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategory.MISCELLANEOUS, "icarus_mode").copy().formatted(Formatting.DARK_GRAY), (button) -> {
            this.client.setScreen(new ICarusModeScreen(this.parent, this.options));
        }).build());

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategory.MISCELLANEOUS, "infini_pearl_mode").copy().formatted(Formatting.BLUE), (button) -> {
            this.client.setScreen(new InfiniPearlModeScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategory.MISCELLANEOUS, "piglin_bartering").copy().formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new PiglinBarteringScreen(this.parent, this.options));
        }).build());

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategory.MISCELLANEOUS, "piglin_pork").copy().formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new PiglinPorkScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategory.MISCELLANEOUS, "no_more_piglin_brutes").copy().formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new NoMorePiglinBrutesScreen(this.parent, this.options));
        }).build());

        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategory.MISCELLANEOUS, "tripled_drops").copy().formatted(Formatting.YELLOW), (button) -> {
            this.client.setScreen(new TripledDropsScreen(this.parent, this.options));
        }).build());
        this.buttons.add(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategory.MISCELLANEOUS, "more").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new MoreScreen(this.parent, this.options));
        }).build());

        super.init();
    }

    @Override
    protected void doneButtonFunction() {
        this.close();
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