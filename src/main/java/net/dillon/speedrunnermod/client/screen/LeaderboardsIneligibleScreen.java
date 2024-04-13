package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerMod.warn;

@Environment(EnvType.CLIENT)
public class LeaderboardsIneligibleScreen extends AbstractModScreen {
    public static boolean needsRestart = false;
    protected static boolean needsRestartFromEnablingLeaderboardsMode = false;
    protected ButtonWidget leftButton, middleButton, rightButton, viewIneligibleOptionsButton, visitSubmissionPageButton;

    public LeaderboardsIneligibleScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_LEADERBOARDS);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();
        this.leftButton = this.addDrawableChild(ButtonWidget.builder(needsRestartFromEnablingLeaderboardsMode ? ModTexts.RESTART_NOW : Leaderboards.noOptionsWereChanged() ? ModTexts.FIX_AND_RESTART : ModTexts.REVERT_CHANGES, (buttonWidget) -> {
            if (needsRestartFromEnablingLeaderboardsMode) {
                this.quitWorld();
                this.client.scheduleStop();
            } else if (Leaderboards.noOptionsWereChanged()) {
                this.quitWorld();
                info("Fixing options! Re-launch to apply changes.");
                Leaderboards.fixOptions();
                ModOptions.saveConfig();
                this.client.scheduleStop();
            } else {
                this.revertChanges();
            }
        }).dimensions(this.getButtonsLeftSide(), this.getButtonsHeight(), 100, 20).build());
        this.middleButton = this.addDrawableChild(ButtonWidget.builder(needsRestartFromEnablingLeaderboardsMode ? ModTexts.REVERT_CHANGES : ModTexts.DISABLE_LEADERBOARDS_MODE_AND_RESTART, (buttonWidget) -> {
        }).dimensions(this.getButtonsMiddle(), this.getButtonsHeight(), 100, 20

        ).build());
        this.rightButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.IGNORE, (buttonWidget) -> {
            Leaderboards.sendIgnoreWarning();
            if (needsRestart && !needsRestartFromEnablingLeaderboardsMode) {
                this.client.setScreen(new RestartRequiredScreen(this.parent, options));
            } else {
                this.client.setScreen(new ModMenuScreen(this.parent, this.options));
            }
        }).dimensions(this.getButtonsRightSide(), this.getButtonsHeight(), 100, 20).build());

        if (!needsRestartFromEnablingLeaderboardsMode) {
            height += 36;
            this.viewIneligibleOptionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.VIEW_INELIGIBLE_OPTIONS, (buttonWidget) -> {
                LeaderboardsIneligibleOptionsScreen.fromInitialBoot = false;
                Leaderboards.checkForIneligibleOptions();
                this.client.setScreen(new LeaderboardsIneligibleOptionsScreen(this.parent, options));
            }).dimensions(this.width / 2 - 100, height, 200, 20).build());

            this.visitSubmissionPageButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.VISIT_SUBMISSION_PAGE, (button) -> {
                this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.LEADERBOARDS_SUBMISSION_LINK);
                    }
                    this.client.setScreen(this);
                }, ModLinks.LEADERBOARDS_SUBMISSION_LINK, true));
            }).dimensions(this.width / 2 - 100, this.height - 29, 200, 20).build());
        }
    }

    @Override
    public void renderCustomText(MatrixStack matrices) {
        if (needsRestartFromEnablingLeaderboardsMode) {
            drawCenteredTextWithShadow(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart_required.line1"), this.width / 2, 110, 16777215);
            drawCenteredTextWithShadow(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart_required.line2"), this.width / 2, 130, 16777215);
        } else if (Leaderboards.noOptionsWereChanged()) {
            drawCenteredTextWithShadow(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart.line1"), this.width / 2, 80, 16777215);
            drawCenteredTextWithShadow(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart.line2"), this.width / 2, 100, 16777215);
            drawCenteredTextWithShadow(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart.line3"), this.width / 2, 120, 16777215);
        } else {
            drawCenteredTextWithShadow(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible.line1"), this.width / 2, 80, 16777215);
            drawCenteredTextWithShadow(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible.line2"), this.width / 2, 100, 16777215);
            drawCenteredTextWithShadow(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line3"), this.width / 2, 120, 16777215);
        }
    }

    @Override
    protected void renderTooltips(MatrixStack matrices, int mouseX, int mouseY) {
        if (this.leftButton.isHovered()) {
            if (needsRestartFromEnablingLeaderboardsMode) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.restart_now.tooltip"), 200), mouseX, mouseY);
            } else if (Leaderboards.noOptionsWereChanged()) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.fix_and_restart.tooltip"), 200), mouseX, mouseY);
            } else {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.revert_changes.tooltip"), 200), mouseX, mouseY);
            }
        }
        if (this.middleButton.isHovered()) {
            if (needsRestartFromEnablingLeaderboardsMode) {
                this.revertChanges();
            } else {
                this.quitWorld();
                Leaderboards.disableLeaderboardsMode();
                this.client.scheduleStop();
            }
        }
        if (this.rightButton.isHovered()) {
            if (!needsRestartFromEnablingLeaderboardsMode) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.ignore.tooltip"), 200), mouseX, mouseY);
            } else {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.ignore_restart.tooltip"), 200), mouseX, mouseY);
            }
        }
        super.renderTooltips(matrices, mouseX, mouseY);
    }

    private void revertChanges() {
        Leaderboards.revertChanges();
        ModOptions.saveConfig();
        info("Changes reverted.");
        this.client.setScreen(this.parent);
    }

    @Override
    protected int getButtonsHeight() {
        return needsRestartFromEnablingLeaderboardsMode ? this.height / 6 + 126 : this.height / 6 + 106;
    }

    @Override
    public void close() {
        warn("Cannot close screen! Please choose an option.");
    }

    @Override
    protected int columns() {
        return 3;
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