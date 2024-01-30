package net.dillon8775.speedrunnermod.client.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class ModsScreen extends GameOptionsScreen {
    private static final String SODIUM_MOD_LINK = "https://modrinth.com/mod/sodium/versions?g=1.18.1";
    private static final String LITHIUM_MOD_LINK = "https://modrinth.com/mod/lithium/versions?g=1.18.1";
    private static final String PHOSPHOR_MOD_LINK = "https://modrinth.com/mod/phosphor/versions?g=1.18.1";
    private static final String SPEEDRUN_IGT_MOD_LINK = "https://modrinth.com/mod/speedrunigt/versions?g=1.18.1";
    private static final String LAZYDFU_MOD_LINK = "https://modrinth.com/mod/lazydfu/versions?g=1.18.1";
    private static final String KRYPTON_MOD_LINK = "https://modrinth.com/mod/krypton/versions?g=1.18.1";
    private final Screen parent;

    public ModsScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.title.resources.mods"));
        this.parent = parent;
    }

    protected void init() {
        super.init();

        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;

        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.title.resources.mods.sodium"), (buttonWidget) ->  {
            Util.getOperatingSystem().open(SODIUM_MOD_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("speedrunnermod.title.resources.mods.sodium.tooltip"), x, y)));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.title.resources.mods.lithium"), (buttonWidget) -> {
            Util.getOperatingSystem().open(LITHIUM_MOD_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("speedrunnermod.title.resources.mods.lithium.tooltip"), x, y)));

        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.title.resources.mods.phosphor"), (buttonWidget) -> {
            Util.getOperatingSystem().open(PHOSPHOR_MOD_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("speedrunnermod.title.resources.mods.phosphor.tooltip"), x, y)));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.title.resources.mods.speedrunigt"), (buttonWidget) -> {
            Util.getOperatingSystem().open(SPEEDRUN_IGT_MOD_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("speedrunnermod.title.resources.mods.speedrunigt.tooltip"), x, y)));

        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.title.resources.mods.lazydfu"), (buttonWidget) -> {
            Util.getOperatingSystem().open(LAZYDFU_MOD_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("speedrunnermod.title.resources.mods.lazydfu.tooltip"), x, y)));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.title.resources.mods.krypton"), (buttonWidget) -> {
            Util.getOperatingSystem().open(KRYPTON_MOD_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("speedrunnermod.title.resources.mods.krypton.tooltip"), x, y)));

        height += 24;
        ButtonWidget optifine;
        optifine = this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.title.resources.mods.optifine"), (buttonWidget) -> {
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("speedrunnermod.title.resources.mods.optifine.tooltip"), x, y)));
        optifine.active = false;

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.setScreen(new ResourcesScreen(this.parent, MinecraftClient.getInstance().options));
        }));
    }

    public void onClose() {
        this.client.setScreen(new ResourcesScreen(this.parent, MinecraftClient.getInstance().options));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}