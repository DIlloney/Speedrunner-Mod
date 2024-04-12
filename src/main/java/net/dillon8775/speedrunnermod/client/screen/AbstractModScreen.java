package net.dillon8775.speedrunnermod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.Leaderboards;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.io.File;
import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * Used to create basic {@link net.dillon8775.speedrunnermod.SpeedrunnerMod} screens.
 */
@Environment(EnvType.CLIENT)
public abstract class AbstractModScreen extends GameOptionsScreen {
    protected boolean alreadySettingToIneligibleScreen = false;
    protected File configFile;
    protected final File configDirectory = new File(FabricLoader.getInstance().getConfigDir().toUri());
    protected final GameOptions options = MinecraftClient.getInstance().options;
    protected final Screen parent;
    protected ButtonWidget helpButton;
    protected ButtonListWidget list;

    public AbstractModScreen(Screen parent, GameOptions options, Text title) {
        super(parent, options, title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        if (isOptionsScreen()) {
            this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), this.getDoneButtonsHeight(), 100, 20, ModTexts.SAVE, (button) -> {
                this.close();
            }));

            this.addDrawableChild(new ButtonWidget(this.getButtonsMiddle(), this.getDoneButtonsHeight(), 100, 20, ModTexts.MENU_OPEN_OPTIONS_FILE, (button) -> {
                this.close();
                Util.getOperatingSystem().open(this.configFile);
            }));

            this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), this.getDoneButtonsHeight(), 100, 20, ModTexts.RESET, (button) -> {
                this.client.setScreen(new ResetOptionsConfirmScreen(this.parent, MinecraftClient.getInstance().options));
            }));

            this.helpButton = this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide() + 104, this.getDoneButtonsHeight(), 20, 20, ModTexts.BLANK, (button) -> {
                this.openLink(ModLinks.OPTIONS_EXPLANATION, true);
            }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(new TranslatableText("speedrunnermod.help"), 200), x, y)));
            this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide() + 128, this.getDoneButtonsHeight(), 20, 20, new LiteralText("D."), (button) -> {
                this.close();
                Util.getOperatingSystem().open(this.configDirectory);
            }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(new TranslatableText("speedrunnermod.directory"), 200), x, y)));
        } else {
            this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, this.getDoneText(), (button) -> this.client.setScreen(this.parent)));
        }
    }

    @Override
    public void close() {
        if (this.isOptionsScreen()) {
            ModOptions.saveConfig();
            info("Saved changes.");

            LeaderboardsIneligibleScreen.needsRestart = false;
            LeaderboardsIneligibleScreen.needsRestartFromEnablingLeaderboardsMode = false;
            this.alreadySettingToIneligibleScreen = false;

            if (options().main.leaderboardsMode) {
                if (Leaderboards.wasLeaderboardsModeChanged()) {
                    LeaderboardsIneligibleScreen.needsRestartFromEnablingLeaderboardsMode = true;
                }

                if (LeaderboardsIneligibleScreen.needsRestartFromEnablingLeaderboardsMode) {
                    this.client.setScreen(new LeaderboardsIneligibleScreen(this.parent, options));
                } else if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                    if (RestartRequiredScreen.needsRestart()) {
                        LeaderboardsIneligibleScreen.needsRestart = true;
                    }
                    this.alreadySettingToIneligibleScreen = true;
                    this.client.setScreen(new LeaderboardsIneligibleScreen(this.parent, this.options));
                } else if (!this.alreadySettingToIneligibleScreen && Leaderboards.wasLeaderboardsModeChanged() || RestartRequiredScreen.needsRestart()) {
                    this.client.setScreen(new RestartRequiredScreen(this.parent, options));
                } else {
                    this.client.setScreen(this.parent);
                }
            } else if (RestartRequiredScreen.needsRestart()) {
                this.client.setScreen(new RestartRequiredScreen(this.parent, this.options));
            } else {
                this.client.setScreen(this.parent);
            }
        } else {
            super.close();
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.renderCustomText(matrices);

        if (this.isOptionsScreen()) {
            this.list.render(matrices, mouseX, mouseY, delta);
            List<OrderedText> list = getHoveredButtonTooltip(this.list, mouseX, mouseY);
            if (list != null) {
                this.renderOrderedTooltip(matrices, list, mouseX, mouseY);
            }
        }

        if (this.shouldRenderVersionText()) {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int farRightSide = rightSide + 267;
            int height = this.height - 24;
            drawCenteredText(matrices, this.textRenderer, SpeedrunnerMod.VERSION, farRightSide, height, 16777215);
        }

        if (this.shouldRenderTitleText()) {
            drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        }

        super.render(matrices, mouseX, mouseY, delta);
        if (isOptionsScreen()) {
            RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/question_mark.png"));
            drawTexture(matrices, this.getButtonsRightSide() + 106, this.helpButton.y + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        }
        this.renderCustomObjects(matrices);
    }

    /**
     * An easier way to open a link in a mod screen.
     */
    protected void openLink(String link, boolean trusted) {
        this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(link);
            }
            this.client.setScreen(this);
        }, link, trusted));
    }

    /**
     * Quits a world.
     */
    protected void quitWorld() {
        if (this.client.isInSingleplayer()) {
            this.client.world.disconnect();
            this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
        } else {
            this.client.disconnect();
        }
    }

    /**
     * Gets the "left side" of a screen.
     */
    protected int getButtonsLeftSide() {
        return this.columns() == 3 ? this.width / 2 - 50 - 105 : this.columns() == 2 ? this.width / 2 - 155 : this.width / 2 - 160;
    }

    /**
     * Gets the "middle" (or center) of a screen.
     */
    protected int getButtonsMiddle() {
        return this.columns() == 2 ? this.width / 2 - 100 : this.width / 2 - 50;
    }

    /**
     * Gets the "right side" of a screen.
     */
    protected int getButtonsRightSide() {
        return this.columns() == 3 ? this.width / 2 - 50 + 105 : this.columns() == 2 ? this.getButtonsLeftSide() + 160 : this.width / 2 + 60;
    }

    /**
     * Gets the width of the buttons on a screen.
     */
    protected int getButtonsWidth() {
        if (this.columns() == 2) {
            return 150;
        } else {
            return 100;
        }
    }

    /**
     * Gets the height of buttons on a screen.
     * <p>To add another row of buttons, add 24 to this variable.</p>
     */
    protected int getButtonsHeight() {
        return this.height / 6 - 12;
    }

    /**
     * Gets the "done" buttons height, typically at the bottom of a screen.
     */
    protected int getDoneButtonsHeight() {
        return this.height - 29;
    }

    /**
     * Gets the text that should be displayed on the typical "Done" button.
     */
    protected Text getDoneText() {
        return ScreenTexts.DONE;
    }

    /**
     * Render custom text on a mod screen.
     * <p>NEVER {@link Override} the render method, use this method instead.</p>
     */
    protected void renderCustomText(MatrixStack matrices) {
    }

    /**
     * Render custom objects on a mod screen.
     */
    protected void renderCustomObjects(MatrixStack matrices) {
    }

    /**
     * Determines how many columns should be displayed on the screen.
     */
    protected abstract int columns();

    /**
     * Determines if the screen should render the "Version: v#.#" text.
     */
    protected abstract boolean shouldRenderVersionText();

    /**
     * Determines if the screen is an options screen.
     */
    protected abstract boolean isOptionsScreen();

    /**
     * Determines if the screen should render the title text.
     */
    protected abstract boolean shouldRenderTitleText();
}