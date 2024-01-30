package net.dillon8775.speedrunnermod.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.client.gui.screen.ModMenuScreen;
import net.dillon8775.speedrunnermod.client.gui.screen.SocialsScreen;
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
public class TitleScreenMixin extends Screen {
    @Shadow @Final
    private boolean doBackgroundFade;
    @Shadow
    private long backgroundFadeStart;
    @Shadow @Final
    private static Identifier EDITION_TITLE_TEXTURE;
    private static final Text OPTIONS_TOOLTIP = new TranslatableText("speedrunnermod.title.options.tooltip");
    private static final Text CREATE_WORLD_BUTTON_TOOLTIP = new TranslatableText("speedrunnermod.create_world_button.desc");
    private static final Text CREATE_WORLD_BUTTON_DISABLED_TOOLTIP = new TranslatableText("speedrunnermod.create_world_button.disabled");
    private static final Text DISCORD_TOOLTIP = new TranslatableText("speedrunnermod.menu.socials.discord.tooltip");
    private static final Text WEBPAGE_TOOLTIP = new TranslatableText("speedrunnermod.menu.socials.webpage.tooltip");
    private static final Text DILLON8775_YOUTUBE_TOOLTIP = new TranslatableText("speedrunnermod.dillon8775_youtube.tooltip");
    private static final Text MANNYQUESO_YOUTUBE_TOOLTIP = new TranslatableText("speedrunnermod.mannyqueso_youtube.tooltip");
    private static final Text NUZLAND_YOUTUBE_TOOLTIP = new TranslatableText("speedrunnermod.nuzland_youtube.tooltip");
    private ButtonWidget optionsButton;
    private ButtonWidget createWorldButton;
    private ButtonWidget dillon8775YouTubeButton;
    private ButtonWidget discordButton;
    private ButtonWidget webpageButton;
    private ButtonWidget nuzlandYouTubeButton;
    private ButtonWidget mannyQuesoYouTubeButton;
    public TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        createWorldButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 72, 20, 20, new LiteralText(""), (buttonWidget) -> {
            this.client.setScreen(CreateWorldScreen.create(this));
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, SpeedrunnerModClient.clOptions().fastWorldCreation ? CREATE_WORLD_BUTTON_TOOLTIP : CREATE_WORLD_BUTTON_DISABLED_TOOLTIP, x, y)));
        createWorldButton.active = SpeedrunnerModClient.clOptions().fastWorldCreation;

        optionsButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 48 + 24 * 2, 20, 20, new LiteralText(""), (buttonWidget) -> {
            this.client.setScreen(new ModMenuScreen(this, MinecraftClient.getInstance().options));
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, OPTIONS_TOOLTIP, x, y)));

        if (SpeedrunnerModClient.clOptions().socialButtons) {
            dillon8775YouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 48, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(SocialsScreen.DILLON8775_YOUTUBE_CHANNEL_LINK);
            }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, DILLON8775_YOUTUBE_TOOLTIP, x, y)));

            discordButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 104, this.height / 4 + 72, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(SocialsScreen.DISCORD_LINK);
            }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, DISCORD_TOOLTIP, x, y)));

            webpageButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 104, this.height / 4 + 96, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(SocialsScreen.WEBPAGE_LINK);
            }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, WEBPAGE_TOOLTIP, x, y)));

            nuzlandYouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 128, this.height / 4 + 72, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(SocialsScreen.NUZLAND_YOUTUBE_CHANNEL_LINK);
            }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, NUZLAND_YOUTUBE_TOOLTIP, x, y)));

            mannyQuesoYouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 128, this.height / 4 + 96, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(SocialsScreen.MANNYQUESO_YOUTUBE_CHANNEL_LINK);
            }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, MANNYQUESO_YOUTUBE_TOOLTIP, x, y)));
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        final int j = this.width / 2 - 137;
        RenderSystem.setShaderTexture(0, EDITION_TITLE_TEXTURE);
        drawTexture(matrices, j + 58, 67, 0.0F, 0.0F, 184, 14, 184, 16);

        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/item/speedrunner_boots.png"));
        drawTexture(matrices, this.width / 2 - 122, createWorldButton.y + 2, 0.0F, 0.0F, 16, 16, 16, 16);

        RenderSystem.setShaderTexture(0, SpeedrunnerMod.SPEEDRUNNER_MOD_ICON);
        drawTexture(matrices, (this.width / 2) - 123, optionsButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);

        if (SpeedrunnerModClient.clOptions().socialButtons) {
            RenderSystem.setShaderTexture(0, SpeedrunnerMod.DILLON8775_ICON);
            drawTexture(matrices, this.width / 2 - 123, dillon8775YouTubeButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);

            RenderSystem.setShaderTexture(0, SpeedrunnerMod.DISCORD_ICON);
            drawTexture(matrices, this.width / 2 + 106, discordButton.y + 2, 0.0F, 0.0F, 16, 16, 16, 16);

            RenderSystem.setShaderTexture(0, SpeedrunnerMod.WEBPAGE_ICON);
            drawTexture(matrices, this.width / 2 + 106, webpageButton.y + 2, 0.0F, 0.0F, 16, 16, 16, 16);

            RenderSystem.setShaderTexture(0, SpeedrunnerMod.NUZLAND_ICON);
            drawTexture(matrices, this.width / 2 + 129, nuzlandYouTubeButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);

            RenderSystem.setShaderTexture(0, SpeedrunnerMod.MANNYQUESO_ICON);
            drawTexture(matrices, this.width / 2 + 129, mannyQuesoYouTubeButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);

            float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
            float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
            int l = MathHelper.ceil(g * 255.0F) << 24;
            drawStringWithShadow(matrices, this.textRenderer, "Speedrunner Mod " + SpeedrunnerMod.MOD_VERSION, 2, this.height - 20, 16777215 | l);
        }
    }
}