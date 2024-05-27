package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.screen.features.FeaturesScreen;
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
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class ModMenuScreen extends AbstractModScreen {
    protected ButtonWidget optionsButton, featuresButton, resourcesButton, externalButton, creditsButton, leaderboardsButton, easierSpeedrunningModButton, doomModeButton;

    public ModMenuScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.optionsButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.options").formatted(getOptionsTextColor()), (button) -> {
            RestartRequiredScreen.getCurrentOptions();
            Leaderboards.getCurrentLeaderboardsMode();
            if (options().main.leaderboardsMode) {
                Leaderboards.getCurrentOptions();
            }
            this.client.setScreen(new ModOptionsScreen(this, options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());

        this.featuresButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_FEATURES, (button) -> {
            this.client.setScreen(new FeaturesScreen(this, options));
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.resourcesButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_RESOURCES, (button) -> {
            this.client.setScreen(new ResourcesScreen(this, options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());

        this.externalButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_EXTERNAL, (button) -> {
            this.client.setScreen(new ExternalScreen(this, options));
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());

        height += 24;
        this.creditsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_CREDITS, (button) -> {
            this.client.setScreen(new ModCreditsScreen(this, options));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());

        this.leaderboardsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_LEADERBOARDS, (button) -> {
            this.client.setScreen(new LeaderboardsScreen(this, this.options));
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());
        this.leaderboardsButton.active = false;

        height += 24;
        this.easierSpeedrunningModButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.EASIER_SPEEDRUNNING_MOD, (button) -> {
            this.openLink(ModLinks.EASIER_SPEEDRUNNING_MOD_WIKI_LINK, true);
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());

        this.doomModeButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_DOOM_MODE, (button) -> {
            if (SecretDoomModeScreen.doomModeButtonAlreadyClicked > 0) {
                this.client.setScreen(new SecretDoomModeScreen.ScreenFive(this, options));
            } else {
                this.client.setScreen(new SecretDoomModeScreen(this, options));
            }
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());
        this.doomModeButton.visible = options().main.doomMode;

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.optionsButton.isHovered()) {
            if (options().main.leaderboardsMode) {
                if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                    context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.MENU_OPTIONS_ACTION_NEEDED, 200), mouseX, mouseY);
                } else {
                    context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.MENU_OPTIONS_SAFE, 200), mouseX, mouseY);
                }
            }
        }
        if (this.featuresButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.MENU_FEATURES_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.resourcesButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.MENU_RESOURCES_TOOLTIP, 200), mouseX, mouseY);
        }
        if (this.leaderboardsButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.MENU_LEADERBOARDS_DISABLED, 200), mouseX, mouseY);
        }
        if (this.easierSpeedrunningModButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.client.textRenderer.wrapLines(ModTexts.EASIER_SPEEDRUNNING_MOD_TOOLTIP, 200), mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    protected void renderCustomObjects(DrawContext context) {
        int middle = this.width / 2 - 69;
        int height = 15;
        context.drawTexture(new Identifier("speedrunnermod:textures/gui/speedrunner_mod.png"), middle, height, 0.0F, 0.0F, 129, 16, 129, 16);
    }

    /**
     * Sets the color of the options button, depending on if leaderboards mode is on, and if the options meet the leaderboards criteria.
     */
    private static Formatting getOptionsTextColor() {
        if (options().main.leaderboardsMode) {
            if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                return Formatting.RED;
            } else {
                return Formatting.GREEN;
            }
        } else {
            return Formatting.AQUA;
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
        return false;
    }
}