package net.dillon8775.speedrunnermod.mixin.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.client.screen.ModMenuScreen;
import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.option.ClientModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
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
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Shadow @Final
    private boolean doBackgroundFade;
    @Shadow
    private long backgroundFadeStart;
    @Shadow @Final
    private static Identifier EDITION_TITLE_TEXTURE;
    @Shadow
    public static final CubeMapRenderer PANORAMA_CUBE_MAP;
    private static final Text OPTIONS_TOOLTIP = new TranslatableText("speedrunnermod.title.options.tooltip");
    private static final Text CREATE_WORLD_BUTTON_TOOLTIP = new TranslatableText("speedrunnermod.create_world_button.desc");
    private static final Text CREATE_WORLD_BUTTON_DISABLED_TOOLTIP = new TranslatableText("speedrunnermod.create_world_button.disabled");
    private static final Text DISCORD_TOOLTIP = new TranslatableText("speedrunnermod.menu.socials.discord.tooltip");
    private static final Text WEBPAGE_TOOLTIP = new TranslatableText("speedrunnermod.menu.title_screen.socials.webpage.tooltip");
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

    /**
     * Adds additional buttons to the title screen.
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void addButtons(CallbackInfo ci) {
        createWorldButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 72, 20, 20, new LiteralText(""), (buttonWidget) -> {
            this.client.setScreen(CreateWorldScreen.create(this));
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, SpeedrunnerModClient.clientOptions().fastWorldCreation ? CREATE_WORLD_BUTTON_TOOLTIP : CREATE_WORLD_BUTTON_DISABLED_TOOLTIP, x, y)));
        createWorldButton.active = SpeedrunnerModClient.clientOptions().fastWorldCreation;

        optionsButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 48 + 24 * 2, 20, 20, new LiteralText(""), (buttonWidget) -> {
            this.client.setScreen(new ModMenuScreen(this, MinecraftClient.getInstance().options));
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, OPTIONS_TOOLTIP, x, y)));

        if (SpeedrunnerModClient.clientOptions().socialButtons) {
            dillon8775YouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 48, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK);
            }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, DILLON8775_YOUTUBE_TOOLTIP, x, y)));

            discordButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 104, this.height / 4 + 72, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(ModLinks.DISCORD_LINK);
            }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, DISCORD_TOOLTIP, x, y)));

            webpageButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 104, this.height / 4 + 96, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(ModLinks.WEBPAGE_LINK);
            }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, WEBPAGE_TOOLTIP, x, y)));

            nuzlandYouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 128, this.height / 4 + 72, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(ModLinks.NUZLAND_YOUTUBE_CHANNEL_LINK);
            }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, NUZLAND_YOUTUBE_TOOLTIP, x, y)));

            mannyQuesoYouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 128, this.height / 4 + 96, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(ModLinks.MANNYQUESO_YOUTUBE_CHANNEL_LINK);
            }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, MANNYQUESO_YOUTUBE_TOOLTIP, x, y)));
        }
    }

    /**
     * <p>Fixes the {@code speedrunner edition} logo rendering incorrectly.</p>
     * <p>Using {@link ModifyArgs}, we can get the values inside the "drawTexture" method, and overwrite those values.</p>
     */
    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIFFIIII)V"))
    private void speedrunnerEdition(Args args) {
        int j = this.width / 2 - 137;
        args.set(1, j + 58);
        args.set(5, 184);
        args.set(7, 184);
    }

    /**
     * Adds additional textures to the title screen.
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void renderButtons(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/item/speedrunner_boots.png"));
        drawTexture(matrices, this.width / 2 - 122, createWorldButton.y + 2, 0.0F, 0.0F, 16, 16, 16, 16);

        RenderSystem.setShaderTexture(0, SpeedrunnerMod.SPEEDRUNNER_MOD_ICON);
        drawTexture(matrices, (this.width / 2) - 123, optionsButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);

        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(g * 255.0F) << 24;
        drawStringWithShadow(matrices, this.textRenderer, SpeedrunnerMod.THE_SPEEDRUNNER_MOD_STRING + " " + SpeedrunnerMod.MOD_VERSION, 2, this.height - 20, 16777215 | l);

        if (SpeedrunnerModClient.clientOptions().socialButtons) {
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
        }
    }

    static {
        if (SpeedrunnerModClient.clientOptions().panorama == ClientModOptions.Panorama.SPEEDRUNNER_MOD) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/speedrunnermod/panorama"));
        } else if (SpeedrunnerModClient.clientOptions().panorama == ClientModOptions.Panorama.EASIER_SPEEDRUNNING) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/easierspeedrunning/panorama"));
        } else if (SpeedrunnerModClient.clientOptions().panorama == ClientModOptions.Panorama.NIGHT) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/night/panorama"));
        } else if (SpeedrunnerModClient.clientOptions().panorama == ClientModOptions.Panorama.CAVE) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/cave/panorama"));
        } else if (SpeedrunnerModClient.clientOptions().panorama == ClientModOptions.Panorama.CLASSIC) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/classic/panorama"));
        } else if (SpeedrunnerModClient.clientOptions().panorama == ClientModOptions.Panorama.EMPTY) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/empty/panorama"));
        } else {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
        }
    }
}