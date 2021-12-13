package net.dilloney.speedrunnermod.client.gui.screen;

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
    private static final String SODIUM_MOD_LINK = "https://www.curseforge.com/minecraft/mc-mods/sodium";
    private static final String LITHIUM_MOD_LINK = "https://www.curseforge.com/minecraft/mc-mods/lithium";
    private static final String PHOSPHOR_MOD_LINK = "https://www.curseforge.com/minecraft/mc-mods/phosphor";
    private static final String LAZY_DFU_LINK = "https://www.curseforge.com/minecraft/mc-mods/lazydfu";
    private static final String KRYPTON_LINK = "https://www.curseforge.com/minecraft/mc-mods/krypton";
    private final Screen parent;

    protected ResourcesScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.resources"));
        this.parent = parent;
    }

    protected void init() {
        super.init();
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.mod_showcase_video"), (buttonWidget) -> this.client.setScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(MOD_SHOWCASE_LINK);
            }

            this.client.setScreen(this);
        }, MOD_SHOWCASE_LINK, true)), new ButtonWidget.TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                ResourcesScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.mod_showcase_video.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.mod_showcase_video.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.wiki"), (buttonWidget) -> this.client.setScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(WIKI_LINK);
            }

            this.client.setScreen(this);
        }, WIKI_LINK, true)), new ButtonWidget.TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                ResourcesScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.wiki.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.wiki.tooltip"));
            }
        }));
        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.sodium"), (buttonWidget) -> this.client.setScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(SODIUM_MOD_LINK);
            }

            this.client.setScreen(this);
        }, SODIUM_MOD_LINK, true))));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.lithium"), (buttonWidget) -> this.client.setScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(LITHIUM_MOD_LINK);
            }

            this.client.setScreen(this);
        }, LITHIUM_MOD_LINK, true))));
        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.phosphor"), (buttonWidget) -> this.client.setScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(PHOSPHOR_MOD_LINK);
            }

            this.client.setScreen(this);
        }, PHOSPHOR_MOD_LINK, true))));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.lazy_dfu"), (buttonWidget) -> this.client.setScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(LAZY_DFU_LINK);
            }

            this.client.setScreen(this);
        }, LAZY_DFU_LINK, true))));
        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.krypton"), (buttonWidget) -> this.client.setScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(KRYPTON_LINK);
            }

            this.client.setScreen(this);
        }, KRYPTON_LINK, true))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> this.client.setScreen(this.parent)));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}