package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ModOptionsScreen extends AbstractModScreen {

    public ModOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.options"));
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_OPTIONS, (button) -> {
            this.client.setScreen(new MainOptionsScreen(this, options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_FAST_WORLD_CREATION, (button) -> {
            this.client.setScreen(new FastWorldCreationOptionsScreen(this, options));
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_OPTIONS_CLIENT, (button) -> {
            this.client.setScreen(new ClientOptionsScreen(this, options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_OPTIONS_RESET, (button) -> {
            this.client.setScreen(new ResetOptionsConfirmScreen(this, options));
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

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