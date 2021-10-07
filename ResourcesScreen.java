package com.dilloney.speedrunnermod.client;

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
class ResourcesScreen extends Screen {
    private final Screen parent;
    private ButtonListWidget list;

    protected ResourcesScreen(Screen parent) {
        super(new TranslatableText("speedrunnermod.resources"));
        this.parent = parent;
    }

    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 8, 150, 20, new TranslatableText("speedrunnermod.mod_review_video"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.youtube.com/watch?v=6MmH5B3zciA");
                }

                this.client.openScreen(this);
            }, "https://www.youtube.com/watch?v=6MmH5B3zciA", true));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height / 8, 150, 20, new TranslatableText("speedrunnermod.timer_mod"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.mediafire.com/file/ilgkuz6z6xbj31v/speedruntimer-1.17-port.jar/file");
                }

                this.client.openScreen(this);
            }, "https://www.mediafire.com/file/ilgkuz6z6xbj31v/speedruntimer-1.17-port.jar/file", true));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 5, 150, 20, new TranslatableText("speedrunnermod.sodium"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://github.com/CaffeineMC/sodium-fabric/releases");
                }

                this.client.openScreen(this);
            }, "https://github.com/CaffeineMC/sodium-fabric/releases/tag/mc1.17.1-0.3.2", true));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height / 5, 150, 20, new TranslatableText("speedrunnermod.lithium"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.curseforge.com/minecraft/mc-mods/lithium");
                }

                this.client.openScreen(this);
            }, "https://www.curseforge.com/minecraft/mc-mods/lithium", true));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 5 + 27, 150, 20, new TranslatableText("speedrunnermod.auto_reset_script"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://www.mediafire.com/file/xrage3x00aq5711/AutoReset.ahk/file");
                }

                this.client.openScreen(this);
            }, "https://www.mediafire.com/file/xrage3x00aq5711/AutoReset.ahk/file", true));
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