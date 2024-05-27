package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class LeaderboardsScreen extends AbstractModScreen {
    protected ButtonWidget submitSpeedrunButton;

    public LeaderboardsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.leaderboards"));
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.submitSpeedrunButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.leaderboards.submit").formatted(getSubmitSpeedrunColor()), (button) -> {
            this.openLink(ModLinks.LEADERBOARDS_SUBMISSION_LINK, true);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.submitSpeedrunButton.active = options().main.leaderboardsMode && Leaderboards.isEligibleForLeaderboardRuns();
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_LEADERBOARDS_VIEW, (button) -> {
            this.openLink(ModLinks.LEADERBOARDS_LINK, true);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_LEADERBOARDS_SPREADSHEET, (button) -> {
            this.openLink(ModLinks.LEADERBOARDS_SPREADSHEET_LINK, true);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.submitSpeedrunButton.isHovered()) {
            if (!options().main.leaderboardsMode) {
                context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.leaderboards_mode_disabled.tooltip"), 200), mouseX, mouseY);
            } else if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.cannot_submit_speedrun.tooltip"), 200), mouseX, mouseY);
            } else {
                context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.submit_speedrun.tooltip"), 200), mouseX, mouseY);
            }
        }
        super.renderTooltips(context, mouseX, mouseY);
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