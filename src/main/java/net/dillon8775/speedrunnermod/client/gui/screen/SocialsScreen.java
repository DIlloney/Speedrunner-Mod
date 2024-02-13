package net.dillon8775.speedrunnermod.client.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class SocialsScreen extends GameOptionsScreen {
    private static final String CURSEFORGE_LINK = "https://www.curseforge.com/minecraft/mc-mods/speedrunner-mod";
    private static final String MODRINTH_LINK = "https://modrinth.com/mod/speedrunner-mod";
    private static final String GITHUB_LINK = "https://github.com/Dillon8775/Speedrunner-Mod";
    public static final String DISCORD_LINK = "https://discord.gg/jvHXkdPRWJ";
    public static final String WEBPAGE_LINK = "https://sites.google.com/view/dillon8775";
    public static final String SPEEDRUNNER_MOD_WEBPAGE_LINK = "https://sites.google.com/view/dillon8775/the-speedrunner-mod";
    public static final String DILLON8775_YOUTUBE_CHANNEL_LINK = "https://www.youtube.com/@dillon8775";
    public static final String MANNYQUESO_YOUTUBE_CHANNEL_LINK = "https://www.youtube.com/@mannyqueso";
    public static final String NUZLAND_YOUTUBE_CHANNEL_LINK = "https://www.youtube.com/@chargeblader9305";
    public static final String MOD_SHOWCASE_VIDEO_LINK = "Coming soon!";
    private final Screen parent;

    protected SocialsScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.socials"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;

        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.socials.curseforge"), (buttonWidget) -> {
            Util.getOperatingSystem().open(CURSEFORGE_LINK);
        }));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.socials.modrinth"), (buttonWidget) -> {
            Util.getOperatingSystem().open(MODRINTH_LINK);
        }));

        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.socials.github"), (buttonWidget) -> {
            Util.getOperatingSystem().open(GITHUB_LINK);
        }));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.socials.discord"), (buttonWidget) -> {
            Util.getOperatingSystem().open(DISCORD_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("speedrunnermod.menu.socials.discord.tooltip"), x, y)));

        height += 24;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.socials.webpage"), (buttonWidget) -> {
            Util.getOperatingSystem().open(WEBPAGE_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("speedrunnermod.menu.socials.webpage.tooltip"), x, y)));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.socials.youtube"), (buttonWidget) -> {
            Util.getOperatingSystem().open(DILLON8775_YOUTUBE_CHANNEL_LINK);
        }));

        height += 24;
        ButtonWidget modShowcaseVideo;
        modShowcaseVideo = this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.menu.socials.mod_showcase_video"), (buttonWidget) -> {
            Util.getOperatingSystem().open(MOD_SHOWCASE_VIDEO_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("speedrunnermod.menu.socials.mod_showcase_video.tooltip"), x, y)));
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