package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;

@Environment(EnvType.CLIENT)
public class ModsScreen extends AbstractModScreen {

    public ModsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_MODS);
    }

    @Override
    protected void init() {
        this.buttonList = this.addDrawableChild(new CustomButtonListWidget(this.client, this.width, this.height - 64, 32, 25));
        this.clearButtons();

        this.buttons.add(0, ButtonWidget.builder(ModTexts.SODIUM, (buttonWidget) -> {
            this.openLink(ModLinks.SODIUM_MOD_LINK, false);
        }).build());

        this.buttons.add(1, ButtonWidget.builder(ModTexts.LITHIUM, (buttonWidget) -> {
            this.openLink(ModLinks.LITHIUM_MOD_LINK, false);
        }).build());

        this.buttons.add(2, ButtonWidget.builder(ModTexts.PHOSPHOR, (buttonWidget) -> {
            this.openLink(ModLinks.PHOSPHOR_MOD_LINK, false);
        }).build());

        this.buttons.add(3, ButtonWidget.builder(ModTexts.SPEEDRUN_IGT, (buttonWidget) -> {
            this.openLink(ModLinks.SPEEDRUN_IGT_MOD_LINK, false);
        }).build());

        this.buttons.add(4, ButtonWidget.builder(ModTexts.LAZYDFU, (buttonWidget) -> {
            this.openLink(ModLinks.LAZYDFU_MOD_LINK, false);
        }).build());

        this.buttons.add(5, ButtonWidget.builder(ModTexts.KRYPTON, (buttonWidget) -> {
            this.openLink(ModLinks.KRYPTON_MOD_LINK, false);
        }).build());

        this.buttons.add(6, ButtonWidget.builder(ModTexts.SIMPLE_KEYBINDS, (buttonWidget) -> {
            this.openLink(ModLinks.SIMPLE_KEYBINDS_MOD_LINK, false);
        }).build());

        this.buttons.add(7, ButtonWidget.builder(ModTexts.OPTIFINE, (buttonWidget) -> {}).build());
        this.buttons.get(7).active = false;

        super.init();
    }

    @Override
    protected void doneButtonFunction() {
        this.close();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.buttons.get(0).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.SODIUM_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.buttons.get(1).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.LITHIUM_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.buttons.get(2).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.PHOSPHOR_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.buttons.get(3).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.SPEEDRUN_IGT_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.buttons.get(4).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.LAZYDFU_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.buttons.get(5).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.KRYPTON_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.buttons.get(6).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.SIMPLE_KEYBINDS_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.buttons.get(7).isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.OPTIFINE_TOOLTIP, 200), mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    public void close() {
        this.client.setScreen(new ResourcesScreen(this.parent, MinecraftClient.getInstance().options));
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