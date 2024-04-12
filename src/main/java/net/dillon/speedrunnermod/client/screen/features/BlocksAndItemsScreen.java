package net.dillon.speedrunnermod.client.screen.features;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.features.blocks_and_items.*;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class BlocksAndItemsScreen extends AbstractModScreen {

    public BlocksAndItemsScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.features.blocks_and_items"));
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunner_ingots").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerIngotsScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunner_nuggets").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerNuggetsScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunner_blocks").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerBlocksScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunner_wood").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerWoodScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "more_boats").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new MoreBoatsScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "igneous_rocks").formatted(Formatting.DARK_GRAY), (button) -> {
            this.client.setScreen(new IgneousRocksScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "eye_of_inferno").formatted(Formatting.RED), (button) -> {
            this.client.setScreen(new EyeOfInfernoScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "eye_of_annul").formatted(Formatting.LIGHT_PURPLE), (button) -> {
            this.client.setScreen(new EyeOfAnnulScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunners_eye").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnersEyeScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "dragons_pearl").formatted(Formatting.LIGHT_PURPLE), (button) -> {
            this.client.setScreen(new DragonsPearlScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "piglin_awakener").formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new PiglinAwakenerScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "blaze_spotter").formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new BlazeSpotterScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "raid_eradicator").formatted(Formatting.DARK_GRAY), (button) -> {
            this.client.setScreen(new RaidEradicatorScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "ender_thruster").formatted(Formatting.BLUE), (button) -> {
            this.client.setScreen(new EnderThrusterScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunner_bulk").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnerBulkScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "speedrunners_workbench").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new SpeedrunnersWorkbenchScreen(this.parent, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "retired_speedrunner").formatted(Formatting.AQUA), (button) -> {
            this.client.setScreen(new RetiredSpeedrunnerScreen(this.parent, this.options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, this.getButtonsWidth(), 20, ModTexts.featureTitleText(ScreenCategories.BLOCKS_AND_ITEMS, "golden_food_items").formatted(Formatting.GOLD), (button) -> {
            this.client.setScreen(new GoldenFoodItemsScreen(this.parent, this.options));
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