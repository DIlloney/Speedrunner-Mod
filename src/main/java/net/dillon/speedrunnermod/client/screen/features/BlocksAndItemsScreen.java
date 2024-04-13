package net.dillon.speedrunnermod.client.screen.features;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.features.blocks_and_items.*;
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
public class BlocksAndItemsScreen extends AbstractModScreen {

    public BlocksAndItemsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items"));
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunner_ingots").copy().formatted(Formatting.AQUA), button -> {
            this.client.setScreen(new SpeedrunnerIngotsScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunner_nuggets").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerNuggetsScreen(this.parent, this.options));
        }).dimensions(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunner_blocks").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerBlocksScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunner_wood").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerWoodScreen(this.parent, this.options));
        }).dimensions(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "more_boats").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new MoreBoatsScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "igneous_rocks").copy().formatted(Formatting.DARK_GRAY), (button) -> {
            this.client.setScreen(new IgneousRocksScreen(this.parent, this.options));
        }).dimensions(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "eye_of_inferno").copy().formatted(Formatting.RED), (button) -> {
            this.client.setScreen(new EyeOfInfernoScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "eye_of_annul").copy().formatted(Formatting.LIGHT_PURPLE), (button) -> {
            this.client.setScreen(new EyeOfAnnulScreen(this.parent, this.options));
        }).dimensions(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunners_eye").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnersEyeScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "dragons_pearl").copy().formatted(Formatting.LIGHT_PURPLE), (button) -> {
            this.client.setScreen(new DragonsPearlScreen(this.parent, this.options));
        }).dimensions(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "piglin_awakener").copy().formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new PiglinAwakenerScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "blaze_spotter").copy().formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new BlazeSpotterScreen(this.parent, this.options));
        }).dimensions(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "raid_eradicator").copy().formatted(Formatting.DARK_GRAY), (button) -> {
            this.client.setScreen(new RaidEradicatorScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "ender_thruster").copy().formatted(Formatting.BLUE), (button) -> {
            this.client.setScreen(new EnderThrusterScreen(this.parent, this.options));
        }).dimensions(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunner_bulk").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerBulkScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunners_workbench").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnersWorkbenchScreen(this.parent, this.options));
        }).dimensions(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "retired_speedrunner").copy().formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new RetiredSpeedrunnerScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "golden_food_items").copy().copy().formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new GoldenFoodItemsScreen(this.parent, this.options));
        }).dimensions(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20).build());

        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            this.close();
        }).dimensions(this.getButtonsMiddle(), this.getDoneButtonsHeight(), 200, 20).build());
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