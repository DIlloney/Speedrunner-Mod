package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.Leaderboards;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class LeaderboardsScreen extends AbstractModScreen {

    public LeaderboardsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.leaderboards"));
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        ButtonWidget submitSpeedrun = this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, Text.translatable("speedrunnermod.menu.leaderboards.submit").formatted(getSubmitSpeedrunColor()), (button) -> {
            this.openLink(ModLinks.LEADERBOARDS_SUBMISSION_LINK, true);
        }, (button, matrices, x, y) -> {
            if (!options().main.leaderboardsMode) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.leaderboards_mode_disabled.tooltip"), 200), x, y);
            } else if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.cannot_submit_speedrun.tooltip"), 200), x, y);
            } else {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.submit_speedrun.tooltip"), 200), x, y);
            }
        }));
        submitSpeedrun.active = options().main.leaderboardsMode && Leaderboards.isEligibleForLeaderboardRuns();
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.MENU_LEADERBOARD_VIEW, (button) -> {
            this.openLink(ModLinks.LEADERBOARDS_LINK, true);
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.MENU_LEADERBOARD_SPREADSHEET, (button) -> {
            this.openLink(ModLinks.LEADERBOARDS_SPREADSHEET_LINK, true);
        }));

        super.init();
    }

    /**
     * Gets submit a speedrun button text color.
     */
    private static Formatting getSubmitSpeedrunColor() {
        if (options().main.leaderboardsMode) {
            if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                return Formatting.RED;
            } else {
                return Formatting.GREEN;
            }
        } else {
            return Formatting.RED;
        }
    }

    @Override
    protected int columns() {
        return 2;
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