package com.dilloney.speedrunnermod.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.TooltipSupplier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

import java.util.function.Consumer;

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
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 8, 150, 20, new TranslatableText("speedrunnermod.wiki"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.speedrunnermod.net/");
                }

                this.client.openScreen(this);
            }, "https://www.speedrunnermod.net/", true));
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                SocialsScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.wiki.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.wiki.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height / 8, 150, 20, new TranslatableText("speedrunnermod.curseforge"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.curseforge.com/minecraft/mc-mods/speedrunner-mod");
                }

                this.client.openScreen(this);
            }, "https://www.curseforge.com/minecraft/mc-mods/speedrunner-mod", true));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 5, 150, 20, new TranslatableText("speedrunnermod.discord"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://discord.gg/Qu8utnCwkq");
                }

                this.client.openScreen(this);
            }, "https://discord.gg/Qu8utnCwkq", true));
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                SocialsScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.discord.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.discord.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height / 5, 150, 20, new TranslatableText("speedrunnermod.github"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://github.com/DIlloney/Speedrunner-Mod/");
                }

                this.client.openScreen(this);
            }, "https://github.com/DIlloney/Speedrunner-Mod/", true));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 5 + 27, 150, 20, new TranslatableText("speedrunnermod.youtube"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.youtube.com/channel/UCNZVI8pFpzn-eXEZsyDEagg");
                }

                this.client.openScreen(this);
            }, "https://www.youtube.com/channel/UCNZVI8pFpzn-eXEZsyDEagg", true));
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                SocialsScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.youtube.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.youtube.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height / 5 + 27, 150, 20, new TranslatableText("speedrunnermod.twitch"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.twitch.tv/dilloney");
                }

                this.client.openScreen(this);
            }, "https://www.twitch.tv/dilloney", true));
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                SocialsScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.twitch.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.twitch.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.openScreen(this.parent);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.list.render(matrices, mouseX, mouseY, delta);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}