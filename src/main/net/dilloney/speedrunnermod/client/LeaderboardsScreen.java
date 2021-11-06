package net.dilloney.speedrunnermod.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
class LeaderboardsScreen extends GameOptionsScreen {
    private static final String LEADERBOARDS_PAGE_LINK = "https://docs.google.com/spreadsheets/d/1d_BYPoYwAcnC7VqnggRkB9odSzS6j6O1A_70rwtT_D4/view";
    private static final String RULES_LINK = "https://www.speedrunnermod.net/leaderboards";
    private static final String SUBMIT_SPEEDRUN_LINK = "https://docs.google.com/forms/d/e/1FAIpQLSdzsi0_PZRGbG8iRuO11qWwN7tkZyL9KRNRzmdm2G-gI2w_yA/viewform";
    private final Screen parent;

    protected LeaderboardsScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.leaderboards"));
        this.parent = parent;
    }

    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 40, 310, 20, new TranslatableText("speedrunnermod.official_leaderboards_page"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(LEADERBOARDS_PAGE_LINK);
            }

            this.client.openScreen(this);
        }, LEADERBOARDS_PAGE_LINK, false)), new ButtonWidget.TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                LeaderboardsScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.official_leaderboards_page.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.official_leaderboards_page.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 65, 150, 20, new TranslatableText("speedrunnermod.rules.title"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(RULES_LINK);
            }

            this.client.openScreen(this);
        }, RULES_LINK, false)), new ButtonWidget.TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                LeaderboardsScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.rules.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.rules.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, 65, 150, 20, new TranslatableText("speedrunnermod.submit_speedrun"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(SUBMIT_SPEEDRUN_LINK);
            }

            this.client.openScreen(this);
        }, SUBMIT_SPEEDRUN_LINK, false)), new ButtonWidget.TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                LeaderboardsScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.submit_speedrun.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.submit_speedrun.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> this.client.openScreen(this.parent)));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 12, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}