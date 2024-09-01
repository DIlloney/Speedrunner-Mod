package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.screen.options.ClientOptionsScreen;
import net.dillon.speedrunnermod.client.screen.options.FastWorldCreationOptionsScreen;
import net.dillon.speedrunnermod.client.screen.options.MainOptionsScreen;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

/**
 * The {@code options} screen for the Speedrunner Mod, consisting of all the option categories.
 */
@Environment(EnvType.CLIENT)
public class ModOptionsScreen extends AbstractModScreen {

    public ModOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.options"));
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttons.add(0, ButtonWidget.builder(ModTexts.MENU_OPTIONS_MAIN, (button) -> {
            this.client.setScreen(new MainOptionsScreen(this, options));
        }).build());

        this.buttons.add(1, ButtonWidget.builder(ModTexts.MENU_FAST_WORLD_CREATION, (button) -> {
            this.client.setScreen(new FastWorldCreationOptionsScreen(this, options));
        }).build());

        this.buttons.add(2, ButtonWidget.builder(ModTexts.MENU_OPTIONS_CLIENT, (button) -> {
            this.client.setScreen(new ClientOptionsScreen(this, options));
        }).build());

        this.buttons.add(3, ButtonWidget.builder(ModTexts.MENU_OPTIONS_RESET, (button) -> {
            this.client.setScreen(new ResetOptionsConfirmScreen(this, options));
        }).build());

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.buttons.get(0).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.MENU_OPTIONS_MAIN_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.buttons.get(1).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.MENU_FAST_WORLD_CREATION_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.buttons.get(2).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.MENU_OPTIONS_CLIENT_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.buttons.get(3).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.MENU_OPTIONS_RESET_TOOLTIP, 200), mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
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