package net.dilloney.speedrunnermod.client;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
class LeaderboardsScreen extends Screen {
    private final Screen parent;
    private ButtonListWidget list;

    protected LeaderboardsScreen(Screen parent) {
        super(new TranslatableText("speedrunnermod.leaderboards"));
        this.parent = parent;
    }

    protected void init() {
        ButtonWidget official_leaderboards_page = this.addButton(new ButtonWidget(this.width / 2 - 155, 40, 310, 20, new TranslatableText("speedrunnermod.official_leaderboards_page"), (buttonWidget) -> {
            this.client.openScreen(this.parent);
        }, (buttonWidget, matrixStack, i, j) -> {
            this.renderTooltip(matrixStack, new TranslatableText("speedrunnermod.official_leaderboards_page.tooltip"), i, j);
        }));
        official_leaderboards_page.active = false;
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        ButtonWidget submit_speedrun = this.addButton(new ButtonWidget(this.width / 2 + 5, 65, 150, 20, new TranslatableText("speedrunnermod.submit_speedrun"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://docs.google.com/forms/d/e/1FAIpQLSdzsi0_PZRGbG8iRuO11qWwN7tkZyL9KRNRzmdm2G-gI2w_yA/viewform");
                }

                this.client.openScreen(this);
            }, "https://docs.google.com/forms/d/e/1FAIpQLSdzsi0_PZRGbG8iRuO11qWwN7tkZyL9KRNRzmdm2G-gI2w_yA/viewform", false));
        }, (buttonWidget, matrixStack, i, j) -> {
            this.renderTooltip(matrixStack, new TranslatableText("speedrunnermod.submit_speedrun.tooltip"), i, j);
        }));
        submit_speedrun.active = false;
        ButtonWidget rules = this.addButton(new ButtonWidget(this.width / 2 - 155, 65, 150, 20, new TranslatableText("speedrunnermod.rules.title"), (buttonWidget) -> {
            this.client.openScreen(RulesScreen.openScreen().build());
        }, (buttonWidget, matrixStack, i, j) -> {
            this.renderTooltip(matrixStack, new TranslatableText("speedrunnermod.rules.tooltip"), i, j);
        }));
        rules.active = false;
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.openScreen(this.parent);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.list.render(matrices, mouseX, mouseY, delta);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 12, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    static class RulesScreen {

        protected static ConfigBuilder openScreen() {

            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(MinecraftClient.getInstance().currentScreen).setTitle(new TranslatableText("speedrunnermod.rules"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            ConfigCategory rulesScreen = builder.getOrCreateCategory(new TranslatableText("speedrunnermod.rules"));

            rulesScreen.addEntry(entryBuilder.startTextDescription(new TranslatableText("speedrunnermod.rules.all")).build());
            return builder;
        }
    }
}