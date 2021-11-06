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
class ResourcesScreen extends GameOptionsScreen {
    private static final String MOD_SHOWCASE_LINK = "https://www.youtube.com/watch?v=6MmH5B3zciA";
    private static final String WIKI_LINK = "https://www.speedrunnermod.net/";
    private static final String SODIUM_MOD_LINK = "https://www.curseforge.com/minecraft/mc-mods/sodium/files";
    private static final String LITHIUM_MOD_LINK = "https://www.curseforge.com/minecraft/mc-mods/lithium";
    private final Screen parent;

    protected ResourcesScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.resources"));
        this.parent = parent;
    }

    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 40, 150, 20, new TranslatableText("speedrunnermod.mod_showcase_video"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(MOD_SHOWCASE_LINK);
            }

            this.client.openScreen(this);
        }, MOD_SHOWCASE_LINK, false)), new ButtonWidget.TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                ResourcesScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.mod_showcase_video.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.mod_showcase_video.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, 40, 150, 20, new TranslatableText("speedrunnermod.wiki"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(WIKI_LINK);
            }

            this.client.openScreen(this);
        }, WIKI_LINK, false)), new ButtonWidget.TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                ResourcesScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.wiki.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.wiki.tooltip"));
            }
        }));
        ButtonWidget timer_mod = this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 65, 150, 20, new TranslatableText("speedrunnermod.timer_mod"), (buttonWidget) -> {}, new ButtonWidget.TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                ResourcesScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.timer_mod.unavailable"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.timer_mod.unavailable"));
            }
        }));
        timer_mod.active = false;
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, 65, 150, 20, new TranslatableText("speedrunnermod.sodium"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(SODIUM_MOD_LINK);
            }

            this.client.openScreen(this);
        }, SODIUM_MOD_LINK, false))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 90, 150, 20, new TranslatableText("speedrunnermod.lithium"), (buttonWidget) -> this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(LITHIUM_MOD_LINK);
            }

            this.client.openScreen(this);
        }, LITHIUM_MOD_LINK, false))));
        ButtonWidget prosphor = this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, 90, 150, 20, new TranslatableText("speedrunnermod.phosphor"), (buttonWidget) -> {}, new ButtonWidget.TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                ResourcesScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.phosphor.unavailable"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.phosphor.unavailable"));
            }
        }));
        prosphor.active = false;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> this.client.openScreen(this.parent)));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 12, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}