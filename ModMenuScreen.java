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

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Environment(EnvType.CLIENT)
public class ModMenuScreen extends Screen {
    private final Screen parent;
    private ButtonListWidget list;

    public ModMenuScreen(Screen parent) {
        super(new TranslatableText("speedrunnermod.title"));
        this.parent = parent;
    }

    protected void init() {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        int i = 0;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 8, 150, 20, new TranslatableText("speedrunnermod.options.title"), (buttonWidget) -> {
            this.client.openScreen(ModOptionsScreen.openScreen().build());
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height / 8, 150, 20, new TranslatableText("speedrunnermod.doom_mode"), (buttonWidget) -> {
            if (OPTIONS.doomMode) {
                this.client.openScreen(DoomModeOptionsScreen.openScreen().build());
            } else {
                this.client.openScreen(DoomModeOptionsScreen.openWarningScreen().build());
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 5, 150, 20, new TranslatableText("speedrunnermod.miscellaneous.title"), (buttonWidget) -> {
            this.client.openScreen(new MiscellaneousScreen(this));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height / 5, 150, 20, new TranslatableText("speedrunnermod.structure_settings.title"), (buttonWidget) -> {
            if (OPTIONS.makeStructuresMoreCommon) {
                this.client.openScreen(StructureOptionsScreen.openScreen().build());
            } else {
                this.client.openScreen(StructureOptionsScreen.openWarningScreen().build());
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 5 + 27, 150, 20, new TranslatableText("speedrunnermod.resources.title"), (buttonWidget) -> {
            this.client.openScreen(new ResourcesScreen(this));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height / 5 + 27, 150, 20, new TranslatableText("speedrunnermod.socials.title"), (buttonWidget) -> {
            this.client.openScreen(new SocialsScreen(this));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height - 27, 150, 20, new TranslatableText("speedrunnermod.submit_speedrun"), (buttonWidget) -> {
            this.client.openScreen(new ConfirmChatLinkScreen((openInBrowser) -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open("https://docs.google.com/forms/d/e/1FAIpQLSdzsi0_PZRGbG8iRuO11qWwN7tkZyL9KRNRzmdm2G-gI2w_yA/viewform");
                }

                this.client.openScreen(this);
            }, "https://docs.google.com/forms/d/e/1FAIpQLSdzsi0_PZRGbG8iRuO11qWwN7tkZyL9KRNRzmdm2G-gI2w_yA/viewform", true));
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                ModMenuScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.submit_speedrun.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.submit_speedrun.tooltip"));
            }
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, this.height - 27, 150, 20, ScreenTexts.DONE, (button) -> {
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