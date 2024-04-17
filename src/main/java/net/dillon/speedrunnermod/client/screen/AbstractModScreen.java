package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Used to create basic {@link net.dillon.speedrunnermod.SpeedrunnerMod} screens.
 */
@Environment(EnvType.CLIENT)
public abstract class AbstractModScreen extends GameOptionsScreen {
    protected boolean alreadySettingToIneligibleScreen = false;
    protected File configFile;
    protected final File configDirectory = new File(FabricLoader.getInstance().getConfigDir().toUri());
    protected final GameOptions options = MinecraftClient.getInstance().options;
    protected final Screen parent;
    protected ButtonWidget helpButton, saveButton, openOptionsFileButton, resetOptionsButton, openOptionsDirectoryButton, doneButton;
    protected OptionListWidget optionList;
    protected CustomButtonListWidget buttonList;
    protected final List<ClickableWidget> buttons = new ArrayList<>();

    public AbstractModScreen(Screen parent, GameOptions options, Text title) {
        super(parent, options, title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        if (isOptionsScreen()) {
            this.saveButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.SAVE, (button) -> {
                this.close();
            }).dimensions(this.getButtonsLeftSide(), this.getDoneButtonsHeight(), 100, 20).build());

            this.openOptionsFileButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_OPEN_OPTIONS_FILE, (button) -> {
                this.close();
                Util.getOperatingSystem().open(this.configFile);
            }).dimensions(this.getButtonsMiddle(), this.getDoneButtonsHeight(), 100, 20).build());

            this.resetOptionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.RESET, (button) -> {
                this.client.setScreen(new ResetOptionsConfirmScreen(this.parent, MinecraftClient.getInstance().options));
            }).dimensions(this.getButtonsRightSide(), this.getDoneButtonsHeight(), 100, 20).build());

            this.helpButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (button) -> {
                this.openLink(ModLinks.OPTIONS_EXPLANATION, true);
            }).dimensions(this.getButtonsRightSide() + 104, this.getDoneButtonsHeight(), 20, 20).build());
            this.openOptionsDirectoryButton = this.addDrawableChild(ButtonWidget.builder(Text.literal("D."), (button) -> {
                this.close();
                Util.getOperatingSystem().open(this.configDirectory);
            }).dimensions(this.getButtonsRightSide() + 128, this.getDoneButtonsHeight(), 20, 20).build());
        } else {
            if (this.buttonList != null) {
                this.buttonList.addAll(this.buttons);
                this.addSelectableChild(this.buttonList);
            }
            this.doneButton = this.addDrawableChild(ButtonWidget.builder(this.getDoneText(), (button) -> this.doneButtonFunction()).dimensions(this.width / 2 - 100, this.height - 29, 200, 20).build());
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
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderCustomText(context);

        if (this.shouldRenderVersionText()) {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int farRightSide = rightSide + 267;
            int height = this.height - 24;
            context.drawCenteredTextWithShadow(this.textRenderer, SpeedrunnerMod.VERSION, farRightSide, height, 16777215);
        }

        if (this.shouldRenderTitleText()) {
            context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 15, 16777215);
        }

        super.render(context, mouseX, mouseY, delta);
        if (this.isOptionsScreen()) {
            this.optionList.render(context, mouseX, mouseY, delta);
            context.drawTexture(new Identifier("speedrunnermod:textures/gui/question_mark.png"), this.getButtonsRightSide() + 106, this.helpButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        } else if (buttonList != null) {
            this.buttonList.render(context, mouseX, mouseY, delta);
        }
        this.renderCustomObjects(context);
        this.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.isOptionsScreen() || this.buttonList != null) {
            this.renderBackgroundTexture(context);
        } else {
            super.renderBackground(context, mouseX, mouseY, delta);
        }
    }

    /**
     * Renders tooltips on certain buttons.
     */
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.isOptionsScreen()) {
            if (this.helpButton.isHovered()) {
                context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.help"), 200), mouseX, mouseY);
            }
            if (this.openOptionsDirectoryButton.isHovered()) {
                context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(Text.translatable("speedrunnermod.directory"), 200), mouseX, mouseY);
            }
        }
    }

    /**
     * An easier way to open a link in a mod screen.
     */
    protected void openLink(String link, boolean trusted) {
        this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
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
            this.client.disconnect(new MessageScreen(Text.translatable("menu.savingLevel")));
        } else {
            this.client.disconnect();
        }
    }

    /**
     * The function for the {@code "done"} button.
     */
    protected void doneButtonFunction() {
        this.client.setScreen(this.parent);
    }

    /**
     * Prevents the buttons from being duplicated onto the screen.
     */
    protected void clearButtons() {
        this.buttons.clear();
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
    protected void renderCustomText(DrawContext context) {
    }

    /**
     * Render custom objects on a mod screen.
     */
    protected void renderCustomObjects(DrawContext context) {
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