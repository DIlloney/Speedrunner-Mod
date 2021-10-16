package net.dilloney.speedrunnermod.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
class SocialsScreen extends Screen {
    private final Screen parent;
    private ButtonListWidget list;

    protected SocialsScreen(Screen parent) {
        super(new TranslatableText("speedrunnermod.socials"));
        this.parent = parent;
    }

    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.addButton(new ButtonWidget(this.width / 2 - 155, 40, 150, 20, new TranslatableText("speedrunnermod.wiki"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.speedrunnermod.net/");
                }

                this.client.openScreen(this);
            }, "https://www.speedrunnermod.net/", false));
        }, (buttonWidget, matrixStack, i, j) -> {
            this.renderTooltip(matrixStack, new TranslatableText("speedrunnermod.wiki.tooltip"), i, j);
        }));
        this.addButton(new ButtonWidget(this.width / 2 + 5, 40, 150, 20, new TranslatableText("speedrunnermod.curseforge"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.curseforge.com/minecraft/mc-mods/speedrunner-mod");
                }

                this.client.openScreen(this);
            }, "https://www.curseforge.com/minecraft/mc-mods/speedrunner-mod", false));
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 155, 65, 150, 20, new TranslatableText("speedrunnermod.discord"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://discord.gg/Qu8utnCwkq");
                }

                this.client.openScreen(this);
            }, "https://discord.gg/Qu8utnCwkq", false));
        }, (buttonWidget, matrixStack, i, j) -> {
            this.renderTooltip(matrixStack, new TranslatableText("speedrunnermod.discord.tooltip"), i, j);
        }));
        this.addButton(new ButtonWidget(this.width / 2 + 5, 65, 150, 20, new TranslatableText("speedrunnermod.github"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://github.com/DIlloney/Speedrunner-Mod/");
                }

                this.client.openScreen(this);
            }, "https://github.com/DIlloney/Speedrunner-Mod/", false));
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 155, 90, 150, 20, new TranslatableText("speedrunnermod.youtube"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.youtube.com/channel/UCNZVI8pFpzn-eXEZsyDEagg");
                }

                this.client.openScreen(this);
            }, "https://www.youtube.com/channel/UCNZVI8pFpzn-eXEZsyDEagg", false));
        }, (buttonWidget, matrixStack, i, j) -> {
            this.renderTooltip(matrixStack, new TranslatableText("speedrunnermod.youtube.tooltip"), i, j);
        }));
        this.addButton(new ButtonWidget(this.width / 2 + 5, 90, 150, 20, new TranslatableText("speedrunnermod.twitch"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.twitch.tv/dilloney");
                }

                this.client.openScreen(this);
            }, "https://www.twitch.tv/dilloney", false));
        }, (buttonWidget, matrixStack, i, j) -> {
            this.renderTooltip(matrixStack, new TranslatableText("speedrunnermod.twitch.tooltip"), i, j);
        }));
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
}