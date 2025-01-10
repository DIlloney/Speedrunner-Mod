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
        this.initializeCustomButtonListWidget();

        this.buttons.add(0, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.blocks_and_items"), (buttonWidget) -> {
            this.client.setScreen(new BlocksAndItemsScreen(this.parent, this.options));
        }).build());

        this.buttons.add(1, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.tools_and_armor"), (buttonWidget) -> {
            this.client.setScreen(new ToolsAndArmorScreen(this.parent, this.options));
        }).build());

        this.buttons.add(2, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.ores_and_worldgen"), (buttonWidget) -> {
            this.client.setScreen(new OresAndWorldgenScreen(this.parent, this.options));
        }).build());

        this.buttons.add(3, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.doom_mode"), (buttonWidget) -> {
            this.client.setScreen(new DoomModeScreen(this.parent, this.options));
        }).build());

        this.buttons.add(4, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.more"), (buttonWidget) -> {
            this.client.setScreen(new MoreScreen(this.parent, this.options));
        }).build());

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