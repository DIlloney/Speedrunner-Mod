package net.dillon8775.speedrunnermod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.client.screen.features.FeaturesScreen;
import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.Leaderboards;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class ModMenuScreen extends AbstractModScreen {

    public ModMenuScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE);
    }

    @Override
    protected void init() {
        int height = this.getButtonsHeight();

        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, new TranslatableText("menu.options").formatted(getOptionsTextColor()), (button) -> {
            RestartRequiredScreen.getCurrentOptions();
            Leaderboards.getCurrentLeaderboardsMode();
            if (options().main.leaderboardsMode) {
                Leaderboards.getCurrentOptions();
            }
            this.client.setScreen(new ModOptionsScreen(this, options));
        }, (button, matrices, x, y) -> {
            if (options().main.leaderboardsMode) {
                if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                    this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.MENU_OPTIONS_ACTION_NEEDED, 200), x, y);
                } else {
                    this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.MENU_OPTIONS_SAFE, 200), x, y);
                }
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.featuresText(), (button) -> {
            this.client.setScreen(new FeaturesScreen(this, options));
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.MENU_FEATURES_TOOLTIP, 200), x, y)));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.MENU_RESOURCES, (button) -> {
            this.client.setScreen(new ResourcesScreen(this, options));
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.MENU_RESOURCES_TOOLTIP, 200), x, y)));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.MENU_EXTERNAL, (button) -> {
            this.client.setScreen(new ExternalScreen(this, options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.MENU_CREDITS, (button) -> {
            this.client.setScreen(new CreditsScreen(this, options));
        }));
        this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.MENU_LEADERBOARDS, (button) -> {
            this.client.setScreen(new LeaderboardsScreen(this, this.options));
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(this.getButtonsLeftSide(), height, 150, 20, ModTexts.EASIER_SPEEDRUNNING_MOD, (button) -> {
            this.openLink(ModLinks.EASIER_SPEEDRUNNING_MOD_WIKI_LINK, true);
        }, (button, matrices, x, y) -> this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(ModTexts.EASIER_SPEEDRUNNING_MOD_TOOLTIP, 200), x, y)));
        ButtonWidget doomModeButton = this.addDrawableChild(new ButtonWidget(this.getButtonsRightSide(), height, 150, 20, ModTexts.MENU_DOOM_MODE, (button) -> {
            if (SecretDoomModeScreen.doomModeButtonAlreadyClicked > 0) {
                this.client.setScreen(new SecretDoomModeScreen.ScreenFive(this, options));
            } else {
                this.client.setScreen(new SecretDoomModeScreen(this, options));
            }
        }));
        doomModeButton.visible = options().main.doomMode;

        super.init();
    }

    @Override
    protected void renderCustomObjects(MatrixStack matrices) {
        int middle = this.width / 2 - 69;
        int height = 15;
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/speedrunner_mod.png"));
        drawTexture(matrices, middle, height, 0.0F, 0.0F, 129, 16, 129, 16);
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