package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerMod.warn;

@Environment(EnvType.CLIENT)
public class SpeedrunIGTMissingScreen extends AbstractModScreen {
    protected ButtonWidget leftButton, middleButton, rightButton;

    public SpeedrunIGTMissingScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_SPEEDRUN_IGT_MISSING);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.leftButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.DOWNLOAD_AND_INSTALL, (buttonWidget) -> {
            this.openLink(ModLinks.SPEEDRUN_IGT_MOD_LINK, false);
        }).dimensions(this.getButtonsLeftSide(), height, 100, 20).build());
        this.middleButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.DISABLE_LEADERBOARDS_MODE_AND_RESTART, (buttonWidget) -> {
            Leaderboards.disableLeaderboardsMode();
            this.client.scheduleStop();
        }).dimensions(this.getButtonsMiddle(), height, 100, 20).build());
        this.rightButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.CLOSE_GAME, (buttonWidget) -> {
            info("Closing game!");
            this.client.scheduleStop();
        }).dimensions(this.getButtonsRightSide(), height, 100, 20).build());
    }

    @Override
    protected void renderTooltips(MatrixStack matrices, int mouseX, int mouseY) {
        if (this.leftButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.download_and_install.tooltip"), 200), mouseX, mouseY);
        }
        if (this.middleButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.disable_leaderboards_mode_and_restart.tooltip"), 200), mouseX, mouseY);
        }
        super.renderTooltips(matrices, mouseX, mouseY);
    }

    @Override
    public void close() {
        warn("Cannot close screen! Please select an option.");
    }

    @Override
    public void renderCustomText(MatrixStack matrices) {
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.speedrun_igt_missing.line1"), this.width / 2, 90, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.speedrun_igt_missing.line2"), this.width / 2, 110, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.speedrun_igt_missing.line3"), this.width / 2, 130, 16777215);
    }

    @Override
    protected int columns() {
        return 3;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return true;
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