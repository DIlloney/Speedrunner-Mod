package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class ExternalScreen extends AbstractModScreen {
    protected ButtonWidget discordButton, webpageButton;

    protected ExternalScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_EXTERNAL);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(ButtonWidget.builder(ModTexts.CURSEFORGE, (buttonWidget) -> {
            this.openLink(ModLinks.CURSEFORGE_LINK, false);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MODRINTH, (buttonWidget) -> {
            this.openLink(ModLinks.MODRINTH_LINK, false);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.GITHUB, (buttonWidget) -> {
            this.openLink(ModLinks.GITHUB_LINK, false);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.discordButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.DISCORD, (buttonWidget) -> {
            this.openLink(ModLinks.DISCORD_LINK, false);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.webpageButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.WEBPAGE, (buttonWidget) -> {
            this.openLink(ModLinks.WEBPAGE_LINK, true);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.YOUTUBE, (buttonWidget) -> {
            this.openLink(ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK, true);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MOD_SHOWCASE_VIDEO, (buttonWidget) -> {
            this.openLink(ModLinks.MOD_SHOWCASE_VIDEO_LINK, true);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());

        super.init();
    }

    @Override
    protected void renderTooltips(MatrixStack matrices, int mouseX, int mouseY) {
        if (this.discordButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.DISCORD_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.webpageButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.WEBPAGE_TOOLTIP, 200), mouseX, mouseY);
        }
        super.renderTooltips(matrices, mouseX, mouseY);
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