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
    protected ButtonWidget sodiumButton, lithiumButton, phosphorButton, speedrunIGTButton, lazyDFUButton, kryptonButton, optiFineButton;

    public ModsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_MODS);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.sodiumButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.SODIUM, (buttonWidget) -> {
            this.openLink(ModLinks.SODIUM_MOD_LINK, false);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.lithiumButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.LITHIUM, (buttonWidget) -> {
            this.openLink(ModLinks.LITHIUM_MOD_LINK, false);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.phosphorButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.PHOSPHOR, (buttonWidget) -> {
            this.openLink(ModLinks.PHOSPHOR_MOD_LINK, false);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.speedrunIGTButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.SPEEDRUN_IGT, (buttonWidget) -> {
            this.openLink(ModLinks.SPEEDRUN_IGT_MOD_LINK, false);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.lazyDFUButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.LAZYDFU, (buttonWidget) -> {
            this.openLink(ModLinks.LAZYDFU_MOD_LINK, false);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.kryptonButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.KRYPTON, (buttonWidget) -> {
            this.openLink(ModLinks.KRYPTON_MOD_LINK, false);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.optiFineButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.OPTIFINE, (buttonWidget) -> {}).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        optiFineButton.active = false;

        super.init();
    }

    @Override
    protected void doneButtonFunction() {
        this.close();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.sodiumButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.SODIUM_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.lithiumButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.LITHIUM_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.phosphorButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.PHOSPHOR_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.speedrunIGTButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.SPEEDRUN_IGT_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.lazyDFUButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.LAZYDFU_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.kryptonButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.KRYPTON_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.optiFineButton.isHovered()) {
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