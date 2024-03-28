package net.dillon8775.speedrunnermod.client.screen.features;

import net.dillon8775.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon8775.speedrunnermod.client.screen.features.miscellaneous.*;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class MiscellaneousScreen extends AbstractModScreen {

    public MiscellaneousScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.features.miscellaneous"));
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.MISCELLANEOUS, "reset_key").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new ResetKeyScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.MISCELLANEOUS, "fog_key").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new FogKeyScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.MISCELLANEOUS, "faster_block_breaking").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new FasterBlockBreakingScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.MISCELLANEOUS, "icarus_mode").formatted(Formatting.DARK_GRAY), (button) -> {
            this.client.setScreen(new ICarusModeScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.MISCELLANEOUS, "infini_pearl_mode").formatted(Formatting.BLUE), (button) -> {
            this.client.setScreen(new InfiniPearlModeScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.MISCELLANEOUS, "piglin_bartering").formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new PiglinBarteringScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.MISCELLANEOUS, "piglin_pork").formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new PiglinPorkScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.MISCELLANEOUS, "no_more_piglin_brutes").formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new NoMorePiglinBrutesScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.MISCELLANEOUS, "tripled_drops").formatted(Formatting.YELLOW), (button) -> {
            this.client.setScreen(new TripledDropsScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.MISCELLANEOUS, "more").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new MoreScreen(this.parent, this.options));
        }));

        this.addDrawableChild(new ButtonWidget(this.getButtonsMiddle(), this.getDoneButtonsHeight(), 200, 20, ScreenTexts.DONE, (button) -> {
            this.close();
        }));
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