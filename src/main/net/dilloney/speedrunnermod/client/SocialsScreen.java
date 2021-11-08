package net.dilloney.speedrunnermod.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
class SocialsScreen extends GameOptionsScreen {
    private static final String CURSEFORGE_LINK = "https://www.curseforge.com/minecraft/mc-mods/speedrunner-mod";
    private static final String DISCORD_LINK = "https://discord.gg/Qu8utnCwkq";
    private static final String GITHUB_LINK = "https://github.com/Dilloney/Speedrunner-Mod/";
    private static final String YOUTUBE_LINK = "https://www.youtube.com/channel/UCNZVI8pFpzn-eXEZsyDEagg";
    private static final String TWITCH_LINK = "https://www.twitch.tv/dilloney";
    private final Screen parent;

    protected SocialsScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.socials"));
        this.parent = parent;
    }

    protected void init() {
        this.addButton(new ButtonWidget(this.width / 2 - 155, 40, 150, 20, new TranslatableText("speedrunnermod.curseforge"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(CURSEFORGE_LINK);
            }

            this.client.openScreen(this);
        }, CURSEFORGE_LINK, false))));
        this.addButton(new ButtonWidget(this.width / 2 + 5, 40, 150, 20, new TranslatableText("speedrunnermod.discord"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(DISCORD_LINK);
            }

            this.client.openScreen(this);
        }, DISCORD_LINK, false)), (buttonWidget, matrixStack, i, j) -> this.renderTooltip(matrixStack, new TranslatableText("speedrunnermod.discord.tooltip"), i, j)));
        this.addButton(new ButtonWidget(this.width / 2 - 155, 65, 150, 20, new TranslatableText("speedrunnermod.github"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(GITHUB_LINK);
            }

            this.client.openScreen(this);
        }, GITHUB_LINK, false))));
        this.addButton(new ButtonWidget(this.width / 2 + 5, 65, 150, 20, new TranslatableText("speedrunnermod.youtube"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(YOUTUBE_LINK);
            }

            this.client.openScreen(this);
        }, YOUTUBE_LINK, true)), (buttonWidget, matrixStack, i, j) -> this.renderTooltip(matrixStack, new TranslatableText("speedrunnermod.youtube.tooltip"), i, j)));
        this.addButton(new ButtonWidget(this.width / 2 - 155, 90, 150, 20, new TranslatableText("speedrunnermod.twitch"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(TWITCH_LINK);
            }

            this.client.openScreen(this);
        }, TWITCH_LINK, false)), (buttonWidget, matrixStack, i, j) -> this.renderTooltip(matrixStack, new TranslatableText("speedrunnermod.twitch.tooltip"), i, j)));
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> this.client.openScreen(this.parent)));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 12, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}