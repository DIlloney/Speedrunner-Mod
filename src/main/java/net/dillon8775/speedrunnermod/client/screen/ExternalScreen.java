package net.dillon8775.speedrunnermod.client.screen;

import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class ExternalScreen extends GameOptionsScreen {
    private final Screen parent;

    protected ExternalScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_EXTERNAL);
        this.parent = parent;
    }

    @Override
    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;

        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, ModTexts.CURSEFORGE, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.CURSEFORGE_LINK);
        }));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, ModTexts.MODRINTH, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.MODRINTH_LINK);
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, ModTexts.GITHUB, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.GITHUB_LINK);
        }));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, ModTexts.DISCORD, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.DISCORD_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, ModTexts.DISCORD_TOOLTIP, x, y)));

        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, ModTexts.WEBPAGE, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.WEBPAGE_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, ModTexts.WEBPAGE_TOOLTIP, x, y)));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, ModTexts.YOUTUBE, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK);
        }));

        height += 24;
        ButtonWidget modShowcaseVideo;
        modShowcaseVideo = this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, ModTexts.MOD_SHOWCASE_VIDEO, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.MOD_SHOWCASE_VIDEO_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, ModTexts.MOD_SHOWCASE_VIDEO_TOOLTIP, x, y)));
        modShowcaseVideo.active = false;

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.setScreen(this.parent);
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}