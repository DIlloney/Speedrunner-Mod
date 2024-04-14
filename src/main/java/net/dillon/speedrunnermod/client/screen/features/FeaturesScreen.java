package net.dillon.speedrunnermod.client.screen.features;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class FeaturesScreen extends AbstractModScreen {

    public FeaturesScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_FEATURES);
    }

    @Override
    protected void init() {
        int height = this.height / 6 - 12;

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.blocks_and_items"), (buttonWidget) -> {
            this.client.setScreen(new BlocksAndItemsScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.tools_and_armor"), (buttonWidget) -> {
            this.client.setScreen(new ToolsAndArmorScreen(this.parent, this.options));
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.ores_and_worldgen"), (buttonWidget) -> {
            this.client.setScreen(new OresAndWorldgenScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.doom_mode"), (buttonWidget) -> {
            this.client.setScreen(new DoomModeScreen(this.parent, this.options));
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.miscellaneous"), (buttonWidget) -> {
            this.client.setScreen(new MiscellaneousScreen(this.parent, this.options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());

        super.init();
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