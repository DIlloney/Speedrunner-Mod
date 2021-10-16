package net.dilloney.speedrunnermod.client;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.TooltipSupplier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.function.Consumer;

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
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 40, 150, 20, new TranslatableText("speedrunnermod.options.title"), (buttonWidget) -> {
            this.client.openScreen(ModOptionsScreen.openScreen().build());
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, 40, 150, 20, new TranslatableText("speedrunnermod.miscellaneous.title"), (buttonWidget) -> {
            this.client.openScreen(new MiscellaneousScreen(this));
        }));
        ButtonWidget structure_settings = this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 65, 150, 20, new TranslatableText("speedrunnermod.structure_settings.title"), (buttonWidget) -> {
            this.client.openScreen(StructureOptionsScreen.openScreen().build());
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                if (!SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon) {
                    ModMenuScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.structure_settings.inactive.tooltip"), mouseX, mouseY);
                }
            }

            public void supply(Consumer<Text> consumer) {
                if (!SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon) {
                    consumer.accept(new TranslatableText("speedrunnermod.structure_settings.inactive.tooltip"));
                }
            }
        }));
        structure_settings.active = SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon;
        ButtonWidget leaderboards = this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, 65, 150, 20, new TranslatableText("speedrunnermod.leaderboards.title"), (buttonWidget) -> {
            this.client.openScreen(this.parent);
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                ModMenuScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.leaderboards.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.leaderboards.tooltip"));
            }
        }));
        leaderboards.active = false;
        ButtonWidget timer = this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 90, 150, 20, new TranslatableText("speedrunnermod.timer.title"), (buttonWidget) -> {
            this.client.openScreen(this.parent);
        }, new TooltipSupplier() {
            public void onTooltip(ButtonWidget button, MatrixStack matrices, int mouseX, int mouseY) {
                ModMenuScreen.this.renderTooltip(matrices, new TranslatableText("speedrunnermod.timer.tooltip"), mouseX, mouseY);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(new TranslatableText("speedrunnermod.timer.tooltip"));
            }
        }));
        timer.active = false;
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, 90, 150, 20, new TranslatableText("speedrunnermod.resources.title"), (buttonWidget) -> {
            this.client.openScreen(new ResourcesScreen(this));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 115, 150, 20, new TranslatableText("speedrunnermod.socials.title"), (buttonWidget) -> {
            this.client.openScreen(new SocialsScreen(this));
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> {
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