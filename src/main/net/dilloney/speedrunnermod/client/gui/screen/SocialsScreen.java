package net.dilloney.speedrunnermod.client.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class SocialsScreen extends GameOptionsScreen {
    public static final String DILLONEY_REWIND_2022_LINK = "https://www.youtube.com/watch?v=FlkFMG5sXQI";
    public static final String DILLONEY_YOUTUBE_CHANNEL_LINK = "https://www.youtube.com/channel/UCNZVI8pFpzn-eXEZsyDEagg";
    public static final String MOD_SHOWCASE_VIDEO_LINK = "https://www.youtube.com/watch?v=Aue9X3RcZAM";
    public static final String DISCORD_LINK = "https://discord.gg/b9HZ4MVFUV";
    public static final String TWITTER_LINK = "https://twitter.com/dilloney7";
    public static final String WEBPAGE_LINK = "https://sites.google.com/view/dilloney";
    public static final String ICE_AND_FIRE_PLAYLIST_LINK = "https://www.youtube.com/playlist?list=PLMq8d1rtsQN_BXKeCnEwLlz9shx0fmtre";
    public static final String GEOMETRY_DASH_PLAYLIST_LINK = "https://www.youtube.com/playlist?list=PLMq8d1rtsQN8AX4dYuA6X7R6kdTwtuLnU";
    public static final String MANNYQUESO_YOUTUBE_CHANNEL_LINK = "https://www.youtube.com/channel/UC6hympnK4biWoAZ2L79brVg";
    private static final String CURSEFORGE_LINK = "https://www.curseforge.com/minecraft/mc-mods/speedrunner-mod";
    private static final String PLANET_MINECRAFT_LINK = "https://www.planetminecraft.com/member/dilloney/";
    private final Screen parent;

    protected SocialsScreen(Screen parent, GameOptions options) {
        super(parent, options, new TranslatableText("speedrunnermod.socials"));
        this.parent = parent;
    }

    protected void init() {
        super.init();
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.socials.youtube"), (buttonWidget) -> {
            Util.getOperatingSystem().open(DILLONEY_YOUTUBE_CHANNEL_LINK);
        }));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.socials.discord"), (buttonWidget) -> {
            Util.getOperatingSystem().open(DISCORD_LINK);
        }));
        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.socials.twitter"), (buttonWidget) -> {
            Util.getOperatingSystem().open(TWITTER_LINK);
        }));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.socials.webpage"), (buttonWidget) -> {
            Util.getOperatingSystem().open(WEBPAGE_LINK);
        }));
        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.socials.curseforge"), (buttonWidget) -> {
            Util.getOperatingSystem().open(CURSEFORGE_LINK);
        }));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.socials.planetminecraft"), (buttonWidget) -> {
            Util.getOperatingSystem().open(PLANET_MINECRAFT_LINK);
        }));
        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("speedrunnermod.socials.mod_showcase_video"), (buttonWidget) -> {
            Util.getOperatingSystem().open(MOD_SHOWCASE_VIDEO_LINK);
        }));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("speedrunnermod.socials.dilloney_rewind_2022"), (buttonWidget) -> {
            Util.getOperatingSystem().open(DILLONEY_REWIND_2022_LINK);
        }));

        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.openScreen(this.parent);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}