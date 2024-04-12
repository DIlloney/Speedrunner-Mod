<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.Leaderboards;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon8775.speedrunnermod.SpeedrunnerMod.warn;

@Environment(EnvType.CLIENT)
public class LeaderboardsSafeScreen extends AbstractModScreen {

    public LeaderboardsSafeScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_LEADERBOARDS);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 102;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 100, 20, ModTexts.FIX_AND_RESTART, (buttonWidget) -> {
            info("Fixing options! Re-launch to apply changes.");
            Leaderboards.fixOptions();
            ModOptions.saveConfig();
            this.client.scheduleStop();
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.fix_and_restart.tooltip"), 200), x, y)));
        this.addDrawableChild(new ButtonWidget(this.getButtonsMiddle(), height, 100, 20, ModTexts.DISABLE_LEADERBOARDS_MODE_AND_RESTART, (buttonWidget) -> {
            Leaderboards.disableLeaderboardsMode();
            this.client.scheduleStop();
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.disable_leaderboards_mode_and_restart.tooltip"), 200), x, y)));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 100, 20, ModTexts.IGNORE, (buttonWidget) -> {
            Leaderboards.sendIgnoreWarning();
            this.client.setScreen(new TitleScreen(false));
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.ignore.tooltip"), 200), x, y)));

        height += 36;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, height, 200, 20, ModTexts.VIEW_INELIGIBLE_OPTIONS, (buttonWidget) -> {
            Leaderboards.checkForIneligibleOptions();
            this.client.setScreen(new LeaderboardsIneligibleOptionsScreen(null, MinecraftClient.getInstance().options));
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ModTexts.VISIT_SUBMISSION_PAGE, (button) -> {
            this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open(ModLinks.LEADERBOARDS_SUBMISSION_LINK);
                }
                this.client.setScreen(this);
            }, ModLinks.LEADERBOARDS_SUBMISSION_LINK, true));
        }));
    }

    @Override
    public void close() {
        warn("Cannot close screen! Please select an option.");
    }

    @Override
    public void renderCustomText(MatrixStack matrices) {
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line1"), this.width / 2, 80, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line2"), this.width / 2, 100, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line3"), this.width / 2, 120, 16777215);
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
=======
package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.Leaderboards;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon8775.speedrunnermod.SpeedrunnerMod.warn;

@Environment(EnvType.CLIENT)
public class LeaderboardsSafeScreen extends AbstractModScreen {

    public LeaderboardsSafeScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_LEADERBOARDS);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 102;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 100, 20, ModTexts.FIX_AND_RESTART, (buttonWidget) -> {
            info("Fixing options! Re-launch to apply changes.");
            Leaderboards.fixOptions();
            ModOptions.saveConfig();
            this.client.scheduleStop();
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.fix_and_restart.tooltip"), 200), x, y)));
        this.addDrawableChild(new ButtonWidget(this.getButtonsMiddle(), height, 100, 20, ModTexts.DISABLE_LEADERBOARDS_MODE_AND_RESTART, (buttonWidget) -> {
            Leaderboards.disableLeaderboardsMode();
            this.client.scheduleStop();
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.disable_leaderboards_mode_and_restart.tooltip"), 200), x, y)));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 100, 20, ModTexts.IGNORE, (buttonWidget) -> {
            Leaderboards.sendIgnoreWarning();
            this.client.setScreen(new TitleScreen(false));
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.ignore.tooltip"), 200), x, y)));

        height += 36;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, height, 200, 20, ModTexts.VIEW_INELIGIBLE_OPTIONS, (buttonWidget) -> {
            Leaderboards.checkForIneligibleOptions();
            this.client.setScreen(new LeaderboardsIneligibleOptionsScreen(null, MinecraftClient.getInstance().options));
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ModTexts.VISIT_SUBMISSION_PAGE, (button) -> {
            this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open(ModLinks.LEADERBOARDS_SUBMISSION_LINK);
                }
                this.client.setScreen(this);
            }, ModLinks.LEADERBOARDS_SUBMISSION_LINK, true));
        }));
    }

    @Override
    public void close() {
        warn("Cannot close screen! Please select an option.");
    }

    @Override
    public void renderCustomText(MatrixStack matrices) {
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line1"), this.width / 2, 80, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line2"), this.width / 2, 100, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("speedrunnermod.leaderboards.ineligible_options.line3"), this.width / 2, 120, 16777215);
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
>>>>>>> Stashed changes
}