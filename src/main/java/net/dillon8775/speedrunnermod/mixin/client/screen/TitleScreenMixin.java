package net.dillon8775.speedrunnermod.mixin.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.screen.ModMenuScreen;
import net.dillon8775.speedrunnermod.client.screen.features.FeaturesScreen;
import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Shadow @Final
    private boolean doBackgroundFade;
    @Shadow
    private long backgroundFadeStart;
    @Shadow
    public static final CubeMapRenderer PANORAMA_CUBE_MAP;
    @Unique
    private static final Text OPTIONS_TOOLTIP = new TranslatableText("speedrunnermod.title.options.tooltip");
    @Unique
    private static final Text CREATE_WORLD_BUTTON_TOOLTIP = new TranslatableText("speedrunnermod.create_world_button.desc");
    @Unique
    private static final Text CREATE_WORLD_BUTTON_DISABLED_TOOLTIP = new TranslatableText("speedrunnermod.create_world_button.disabled");
    @Unique
    private static final Text DISCORD_TOOLTIP = new TranslatableText("speedrunnermod.menu.external.discord.tooltip");
    @Unique
    private static final Text WEBPAGE_TOOLTIP = new TranslatableText("speedrunnermod.menu.title_screen.external.webpage.tooltip");
    @Unique
    private static final Text DILLON8775_YOUTUBE_TOOLTIP = new TranslatableText("speedrunnermod.dillon8775_youtube.tooltip");
    @Unique
    private static final Text MANNYQUESO_YOUTUBE_TOOLTIP = new TranslatableText("speedrunnermod.mannyqueso_youtube.tooltip");
    @Unique
    private static final Text NUZLAND_YOUTUBE_TOOLTIP = new TranslatableText("speedrunnermod.nuzland_youtube.tooltip");
    @Unique
    private ButtonWidget optionsButton;
    @Unique
    private ButtonWidget createWorldButton;
    @Unique
    private ButtonWidget dillon8775YouTubeButton;
    @Unique
    private ButtonWidget discordButton;
    @Unique
    private ButtonWidget webpageButton;
    @Unique
    private ButtonWidget nuzlandYouTubeButton;
    @Unique
    private ButtonWidget mannyQuesoYouTubeButton;
    public TitleScreenMixin(Text title) {
        super(title);
    }

    /**
     * Adds additional buttons to the title screen.
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void addButtons(CallbackInfo ci) {
        if (options().advanced.showResetButton) {
            createWorldButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 72, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(CreateWorldScreen.create(this));
            }));
            createWorldButton.active = options().client.fastWorldCreation;
        }

        int l = this.height / 4 + 48;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l + 72 + 36, 98, 20, ModTexts.MENU_FEATURES, button -> this.client.setScreen(new FeaturesScreen(this, MinecraftClient.getInstance().options))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 2, l + 72 + 36, 98, 20, ModTexts.MENU_WIKI, button -> {
            this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open(ModLinks.SPEEDRUNNER_MOD_WEBPAGE_LINK);
                }
                this.client.setScreen(this);
            }, ModLinks.SPEEDRUNNER_MOD_WEBPAGE_LINK, true));
        }));

        optionsButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 48 + 24 * 2, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
            this.client.setScreen(new ModMenuScreen(this, MinecraftClient.getInstance().options));
        }));

        if (options().client.socialButtons) {
            dillon8775YouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 4 + 48, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK);
                    }
                    this.client.setScreen(this);
                }, ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK, true));
            }));

            discordButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 104, this.height / 4 + 72, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.DISCORD_LINK);
                    }
                    this.client.setScreen(this);
                }, ModLinks.DISCORD_LINK, false));
            }));

            webpageButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 104, this.height / 4 + 96, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.WEBPAGE_LINK);
                    }
                    this.client.setScreen(this);
                }, ModLinks.WEBPAGE_LINK, true));
            }));

            nuzlandYouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 128, this.height / 4 + 72, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.NUZLAND_YOUTUBE_CHANNEL_LINK);
                    }
                    this.client.setScreen(this);
                }, ModLinks.NUZLAND_YOUTUBE_CHANNEL_LINK, true));
            }));

            mannyQuesoYouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 128, this.height / 4 + 96, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.MANNYQUESO_YOUTUBE_CHANNEL_LINK);
                    }
                    this.client.setScreen(this);
                }, ModLinks.MANNYQUESO_YOUTUBE_CHANNEL_LINK, true));
            }));
        }
    }

    /**
     * <p>Fixes the {@code speedrunner edition} logo rendering incorrectly.</p>
     * <p>Using {@link ModifyArgs}, we can get the values inside the "drawTexture" method, and overwrite those values.</p>
     */
    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIFFIIII)V"))
    private void speedrunnerEdition(Args args) {
        if (options().advanced.fixSpeedrunnerEditionTextOffset) {
            int j = this.width / 2 - 137;
            args.set(1, j + 58);
            args.set(5, 184);
            args.set(7, 184);
        }
    }

    /**
     * Adds additional textures to the title screen.
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void renderButtons(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (options().advanced.showResetButton) {
            RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/item/speedrunner_boots.png"));
            drawTexture(matrices, this.width / 2 - 122, createWorldButton.y + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        }

        RenderSystem.setShaderTexture(0, SpeedrunnerMod.SPEEDRUNNER_MOD_ICON);
        drawTexture(matrices, (this.width / 2) - 123, optionsButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);

        if (options().client.socialButtons) {
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

        this.renderTooltips(matrices, mouseX, mouseY);

        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(g * 255.0F) << 24;
        drawStringWithShadow(matrices, this.textRenderer, SpeedrunnerMod.THE_SPEEDRUNNER_MOD_STRING + " " + SpeedrunnerMod.MOD_VERSION, 2, this.height - 20, 16777215 | l);
    }

    /**
     * Renders the tooltips on the title screen buttons.
     */
    @Unique
    private void renderTooltips(MatrixStack matrices, int mouseX, int mouseY) {
        if (options().advanced.showResetButton && createWorldButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(options().client.fastWorldCreation ? CREATE_WORLD_BUTTON_TOOLTIP : CREATE_WORLD_BUTTON_DISABLED_TOOLTIP, 200), mouseX, mouseY);
        }

        if (optionsButton.isHovered()) {
            this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(OPTIONS_TOOLTIP, 200), mouseX, mouseY);
        }

        if (options().client.socialButtons) {
            if (dillon8775YouTubeButton.isHovered()) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(DILLON8775_YOUTUBE_TOOLTIP, 200), mouseX, mouseY);
            }

            if (discordButton.isHovered()) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(DISCORD_TOOLTIP, 200), mouseX, mouseY);
            }

            if (webpageButton.isHovered()) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(WEBPAGE_TOOLTIP, 200), mouseX, mouseY);
            }

            if (nuzlandYouTubeButton.isHovered()) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(NUZLAND_YOUTUBE_TOOLTIP, 200), mouseX, mouseY);
            }

            if (mannyQuesoYouTubeButton.isHovered()) {
                this.renderOrderedTooltip(matrices, this.client.textRenderer.wrapLines(MANNYQUESO_YOUTUBE_TOOLTIP, 200), mouseX, mouseY);
            }
        }
    }

    static {
        switch (options().client.panorama) {
            case SPEEDRUNNER_MOD -> PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/speedrunnermod/panorama"));
            case EASIER_SPEEDRUNNING -> PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/easierspeedrunning/panorama"));
            case NIGHT -> PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/night/panorama"));
            case CAVE -> PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/cave/panorama"));
            case CLASSIC -> PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/classic/panorama"));
            case EMPTY -> PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/empty/panorama"));
            case OLD_SPEEDRUNNER_MOD -> PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/oldspeedrunnermod/panorama"));
            default -> PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
        }
    }
}