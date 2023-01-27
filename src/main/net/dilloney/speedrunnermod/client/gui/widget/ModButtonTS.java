package net.dilloney.speedrunnermod.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.client.gui.screen.ModMenuScreen;
import net.dilloney.speedrunnermod.client.gui.screen.SocialsScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class ModButtonTS extends Screen {
    @Shadow @Final
    private boolean doBackgroundFade;
    @Shadow
    private long backgroundFadeStart;
    @Shadow @Final
    private static Identifier EDITION_TITLE_TEXTURE;
    private static final Text CREATE_WORLD_BUTTON_DESCRIPTION = new TranslatableText("speedrunnermod.create_world_button.desc");
    private static final Text CREATE_WORLD_BUTTON_ERROR = new TranslatableText("speedrunnermod.create_world_button.error");
    private static final Text DILLONEY_YOUTUBE_HOVERED_TEXT = new TranslatableText("speedrunnermod.dilloney_youtube.title_screen_tooltip");
    private static final Text MOD_SHOWCASE_VIDEO_HOVERED_TEXT = new TranslatableText("speedrunnermod.mod_showcase_video.title_screen_tooltip");
    private static final Text DILLONEY_REWIND_2022_HOVERED_TEXT = new TranslatableText("speedrunnermod.dilloney_rewind_2022.title_screen_tooltip");
    private static final Text DISCORD_HOVERED_TEXT = new TranslatableText("speedrunnermod.discord.title_screen_tooltip");
    private static final Text TWITTER_HOVERED_TEXT = new TranslatableText("speedrunnermod.twitter.title_screen_tooltip");
    private static final Text WEBPAGE_HOVERED_TEXT = new TranslatableText("speedrunnermod.webpage.title_screen_tooltip");
    private static final Text ICE_AND_FIRE_PLAYLIST_HOVERED_TEXT = new TranslatableText("speedrunnermod.ice_and_fire_playlist.tooltip");
    private static final Text GEOMETRY_DASH_HOVERED_TEXT = new TranslatableText("speedrunnermod.geometry_dash_playlist.tooltip");
    private static final Text MANNYQUESO_HOVERED_TEXT = new TranslatableText("speedrunnermod.mannyqueso_youtube.title_screen_tooltip");
    private ButtonWidget createWorldButton;
    private ButtonWidget dilloneyRewind2022Button;
    private ButtonWidget dilloneyYouTubeButton;
    private ButtonWidget modShowcaseVideoButton;
    private ButtonWidget discordButton;
    private ButtonWidget twitterButton;
    private ButtonWidget webpageButton;
    private ButtonWidget iceAndFirePlaylistButton;
    private ButtonWidget geometryDashPlaylistButton;
    private ButtonWidget mannyQuesoYouTubeButton;

    public ModButtonTS(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        dilloneyRewind2022Button = this.addDrawableChild(new ButtonWidget(this.width / 2 + 2, this.height / 4 + 156, 124, 20, new TranslatableText("speedrunnermod.dilloney_rewind_2022.title_screen"), (buttonWidget) -> {
            Util.getOperatingSystem().open(SocialsScreen.DILLONEY_REWIND_2022_LINK);
        }));
        dilloneyYouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 48, 20, 20, new LiteralText(""), (buttonWidget) -> {
            Util.getOperatingSystem().open(SocialsScreen.DILLONEY_YOUTUBE_CHANNEL_LINK);
        }));
        modShowcaseVideoButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 156, 124, 20, new TranslatableText("speedrunnermod.mod_showcase_video"), (buttonWidget) -> {
            Util.getOperatingSystem().open(SocialsScreen.MOD_SHOWCASE_VIDEO_LINK);
        }));
        discordButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 104, this.height / 4 + 48, 20, 20, new LiteralText(""), (buttonWidget) -> {
            Util.getOperatingSystem().open(SocialsScreen.DISCORD_LINK);
        }));
        twitterButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 104, this.height / 4 + 72, 20, 20, new LiteralText(""), (buttonWidget) -> {
            Util.getOperatingSystem().open(SocialsScreen.TWITTER_LINK);
        }));
        webpageButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 104, this.height / 4 + 96, 20, 20, new LiteralText(""), (buttonWidget) -> {
            Util.getOperatingSystem().open(SocialsScreen.WEBPAGE_LINK);
        }));
        iceAndFirePlaylistButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 128, this.height / 4 + 48, 20, 20, new LiteralText(""), (buttonWidget) -> {
            Util.getOperatingSystem().open(SocialsScreen.ICE_AND_FIRE_PLAYLIST_LINK);
        }));
        geometryDashPlaylistButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 128, this.height / 4 + 72, 20, 20, new LiteralText(""), (buttonWidget) -> {
            Util.getOperatingSystem().open(SocialsScreen.GEOMETRY_DASH_PLAYLIST_LINK);
        }));
        mannyQuesoYouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 128, this.height / 4 + 96, 20, 20, new LiteralText(""), (buttonWidget) -> {
            Util.getOperatingSystem().open(SocialsScreen.MANNYQUESO_YOUTUBE_CHANNEL_LINK);
        }));

        createWorldButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 72, 20, 20, new LiteralText(""), (buttonWidget) -> {
            this.client.openScreen(CreateWorldScreen.create(this));
        }));
        createWorldButton.active = SpeedrunnerModClient.clOptions().autoCreateWorld;

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 48 + 24 * 2, 20, 20, new LiteralText(""), (buttonWidget) -> {
            this.client.openScreen(new ModMenuScreen(this, MinecraftClient.getInstance().options));
        }));
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        final int j = this.width / 2 - 137;
        RenderSystem.setShaderTexture(0, EDITION_TITLE_TEXTURE);
        drawTexture(matrices, j + 58, 67, 0.0F, 0.0F, 184, 14, 184, 16);

        if (dilloneyRewind2022Button.isHovered()) {
            drawCenteredText(matrices, this.textRenderer, DILLONEY_REWIND_2022_HOVERED_TEXT, this.width / 2, this.height / 4 + 35, 16777215);
        }
        if (dilloneyYouTubeButton.isHovered()) {
            drawCenteredText(matrices, this.textRenderer, DILLONEY_YOUTUBE_HOVERED_TEXT, this.width / 2 - 114, this.height / 4 + 35, 16777215);
        }
        if (modShowcaseVideoButton.isHovered()) {
            drawCenteredText(matrices, this.textRenderer, MOD_SHOWCASE_VIDEO_HOVERED_TEXT, this.width / 2, this.height / 4 + 35, 16777215);
        }
        if (discordButton.isHovered()) {
            drawCenteredText(matrices, this.textRenderer, DISCORD_HOVERED_TEXT, this.width / 2 + 114, this.height / 4 + 35, 16777215);
        }
        if (twitterButton.isHovered()) {
            drawCenteredText(matrices, this.textRenderer, TWITTER_HOVERED_TEXT, this.width / 2 + 114, this.height / 4 + 35, 16777215);
        }
        if (webpageButton.isHovered()) {
            drawCenteredText(matrices, this.textRenderer, WEBPAGE_HOVERED_TEXT, this.width / 2 + 114, this.height / 4 + 35, 16777215);
        }
        if (iceAndFirePlaylistButton.isHovered()) {
            drawCenteredText(matrices, this.textRenderer, ICE_AND_FIRE_PLAYLIST_HOVERED_TEXT, this.width / 2 + 114, this.height / 4 + 35, 16777215);
        }
        if (geometryDashPlaylistButton.isHovered()) {
            drawCenteredText(matrices, this.textRenderer, GEOMETRY_DASH_HOVERED_TEXT, this.width / 2 + 114, this.height / 4 + 35, 16777215);
        }
        if (mannyQuesoYouTubeButton.isHovered()) {
            drawCenteredText(matrices, this.textRenderer, MANNYQUESO_HOVERED_TEXT, this.width / 2 + 114, this.height / 4 + 35, 16777215);
        }

        RenderSystem.setShaderTexture(0, new Identifier("dilloney:textures/dilloney.png"));
        drawTexture(matrices, this.width / 2 - 123, this.height / 4 + 47 + 2, 0.0F, 0.0F, 18, 18, 18, 18);
        RenderSystem.setShaderTexture(0, SpeedrunnerMod.SPEEDRUNNER_MOD_ICON);
        drawTexture(matrices, (this.width / 2) - 123, this.height / 4 + 47 + 24 * 2 + 2, 0.0F, 0.0F, 18, 18, 18, 18);
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/item/speedrunner_boots.png"));
        drawTexture(matrices, this.width / 2 - 122, this.height / 4 + 74, 0.0F, 0.0F, 16, 16, 16, 16);

        RenderSystem.setShaderTexture(0, new Identifier("dilloney:textures/discord.png"));
        drawTexture(matrices, this.width / 2 + 106, this.height / 4 + 50, 0.0F, 0.0F, 16, 16, 16, 16);
        RenderSystem.setShaderTexture(0, new Identifier("dilloney:textures/twitter.png"));
        drawTexture(matrices, this.width / 2 + 106, this.height / 4 + 74, 0.0F, 0.0F, 16, 16, 16, 16);
        RenderSystem.setShaderTexture(0, new Identifier("dilloney:textures/webpage.png"));
        drawTexture(matrices, this.width / 2 + 106, this.height / 4 + 98, 0.0F, 0.0F, 16, 16, 16, 16);

        RenderSystem.setShaderTexture(0, new Identifier("dilloney:textures/iceandfire.png"));
        drawTexture(matrices, this.width / 2 + 129, this.height / 4 + 49, 0.0F, 0.0F, 18, 18, 18, 18);
        RenderSystem.setShaderTexture(0, new Identifier("dilloney:textures/geometrydash.png"));
        drawTexture(matrices, this.width / 2 + 129, this.height / 4 + 73, 0.0F, 0.0F, 18, 18, 18, 18);
        RenderSystem.setShaderTexture(0, new Identifier("dilloney:textures/mannyqueso.jpg"));
        drawTexture(matrices, this.width / 2 + 129, this.height / 4 + 97, 0.0F, 0.0F, 18, 18, 18, 18);

        if (createWorldButton.isHovered()) {
            if (SpeedrunnerModClient.clOptions().autoCreateWorld) {
                drawCenteredText(matrices, this.textRenderer, CREATE_WORLD_BUTTON_DESCRIPTION, this.width / 2 - 114, this.height / 4 + 35, 16777215);
            } else {
                drawCenteredText(matrices, this.textRenderer, CREATE_WORLD_BUTTON_ERROR, this.width / 2 - 114, this.height / 4 + 35, 16777215);
            }
        }
    }

    @Inject(method = "render", at = @At("TAIL"), cancellable = true)
    private void renderText(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(g * 255.0F) << 24;
        drawStringWithShadow(matrices, this.textRenderer, "Speedrunner Mod " + SpeedrunnerMod.MOD_VERSION, 2, this.height - 20, 16777215 | l);
    }
}