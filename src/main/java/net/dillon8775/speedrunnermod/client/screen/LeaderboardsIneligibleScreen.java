package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.Leaderboards;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon8775.speedrunnermod.SpeedrunnerMod.warn;

@Environment(EnvType.CLIENT)
public class LeaderboardsIneligibleScreen extends AbstractModScreen {
    public static boolean needsRestart = false;
    protected static boolean needsRestartFromEnablingLeaderboardsMode = false;

    public LeaderboardsIneligibleScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_LEADERBOARDS);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), this.getButtonsHeight(), 100, 20, needsRestartFromEnablingLeaderboardsMode ? ModTexts.RESTART_NOW : Leaderboards.noOptionsWereChanged() ? ModTexts.FIX_AND_RESTART : ModTexts.REVERT_CHANGES, (buttonWidget) -> {
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
        }, (button, matrices, x, y) -> {
            if (needsRestartFromEnablingLeaderboardsMode) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.restart_now.tooltip"), 200), x, y);
            } else if (Leaderboards.noOptionsWereChanged()) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.fix_and_restart.tooltip"), 200), x, y);
            } else {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.revert_changes.tooltip"), 200), x, y);
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsMiddle(), this.getButtonsHeight(), 100, 20, needsRestartFromEnablingLeaderboardsMode ? ModTexts.REVERT_CHANGES : ModTexts.DISABLE_LEADERBOARDS_MODE_AND_RESTART, (buttonWidget) -> {
            if (needsRestartFromEnablingLeaderboardsMode) {
                this.revertChanges();
            } else {
                this.quitWorld();
                Leaderboards.disableLeaderboardsMode();
                this.client.scheduleStop();
            }
        }, (button, matrices, x, y) -> {
            if (!needsRestartFromEnablingLeaderboardsMode) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.disable_leaderboards_mode_and_restart.tooltip"), 200), x, y);
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), this.getButtonsHeight(), 100, 20, ModTexts.IGNORE, (buttonWidget) -> {
            Leaderboards.sendIgnoreWarning();
            if (needsRestart && !needsRestartFromEnablingLeaderboardsMode) {
                this.client.setScreen(new RestartRequiredScreen(this.parent, options));
            } else {
                this.client.setScreen(new ModMenuScreen(this.parent, this.options));
            }
        }, (button, matrices, x, y) -> {
            if (!needsRestartFromEnablingLeaderboardsMode) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.ignore.tooltip"), 200), x, y);
            } else {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.ignore_restart.tooltip"), 200), x, y);
            }
        }));

        if (!needsRestartFromEnablingLeaderboardsMode) {
            height += 36;
            this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, height, 200, 20, ModTexts.VIEW_INELIGIBLE_OPTIONS, (buttonWidget) -> {
                LeaderboardsIneligibleOptionsScreen.fromInitialBoot = false;
                Leaderboards.checkForIneligibleOptions();
                this.client.setScreen(new LeaderboardsIneligibleOptionsScreen(this.parent, options));
            }));

            this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ModTexts.VISIT_SUBMISSION_PAGE, (button) -> {
                this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.LEADERBOARDS_SUBMISSION_LINK);
                    }
                    this.client.setScreen(this);
                }, ModLinks.LEADERBOARDS_SUBMISSION_LINK, true));
            }));
        }
    }

    @Override
    public void renderCustomText(MatrixStack matrices) {
        if (needsRestartFromEnablingLeaderboardsMode) {
            drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart_required.line1"), this.width / 2, 110, 16777215);
            drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart_required.line2"), this.width / 2, 130, 16777215);
        } else if (Leaderboards.noOptionsWereChanged()) {
            drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart.line1"), this.width / 2, 80, 16777215);
            drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart.line2"), this.width / 2, 100, 16777215);
            drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.restart.line3"), this.width / 2, 120, 16777215);
        } else {
            drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible.line1"), this.width / 2, 80, 16777215);
            drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible.line2"), this.width / 2, 100, 16777215);
            drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line3"), this.width / 2, 120, 16777215);
        }
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