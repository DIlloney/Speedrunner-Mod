package net.dillon.speedrunnermod.client.screen.features;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.features.ores_and_worldgen.*;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class OresAndWorldgenScreen extends AbstractModScreen {

    public OresAndWorldgenScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.features.ores_and_worldgen"));
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.fText(ModTexts.fBfL + "SPR.'s." + ModTexts.fR + ModTexts.fB + " Wasteland Biome"), (button) -> {
            this.client.setScreen(new SpeedrunnersWastelandBiomeScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.ORES_AND_WORLDGEN, "speedrunner_ores").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerOresScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.ORES_AND_WORLDGEN, "experience_ores").formatted(Formatting.GREEN), (button) -> {
            this.client.setScreen(new ExperienceOresScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.ORES_AND_WORLDGEN, "igneous_ores").formatted(Formatting.DARK_GRAY), (button) -> {
            this.client.setScreen(new IgneousOresScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.ORES_AND_WORLDGEN, "common_ores").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new CommonOresScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, new LiteralText("Structure Spawn Rates!").formatted(Formatting.GREEN), (button) -> {
            this.client.setScreen(new StructuresScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, new LiteralText("Structure Generation").formatted(Formatting.RED), (button) -> {
            this.client.setScreen(new FortressesBastionsAndStrongholdsScreen(this.parent, this.options));
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