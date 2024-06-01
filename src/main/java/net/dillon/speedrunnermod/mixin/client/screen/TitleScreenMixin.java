package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.screen.MainScreen;
import net.dillon.speedrunnermod.client.screen.features.FeaturesScreen;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

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
    private ButtonWidget featuresButton, createWorldButton, optionsButton, wikiButton, dillon8775YouTubeButton, nuzlandYouTubeButton, mannyQuesoYouTubeButton;

    public TitleScreenMixin(Text title) {
        super(title);
    }

    /**
     * Adds additional buttons to the title screen.
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void addButtons(CallbackInfo ci) {
        this.featuresButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.client.setScreen(new FeaturesScreen(this, MinecraftClient.getInstance().options));
        }).dimensions(this.width / 2 - 124, this.height / 4 + 48, 20, 20).build());

        if (options().advanced.showResetButton) {
            this.createWorldButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                CreateWorldScreen.create(this.client, this);
            }).dimensions(this.width / 2 - 124, this.height / 4 + 72, 20, 20).build());
            this.createWorldButton.active = options().client.fastWorldCreation;
        }

        this.optionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.client.setScreen(new MainScreen(this, MinecraftClient.getInstance().options));
        }).dimensions(this.width / 2 - 124, this.height / 4 + 96, 20, 20).build());

        if (options().client.socialButtons) {
            this.dillon8775YouTubeButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.DILLON8775_YOUTUBE);
                    }
                    this.client.setScreen(this);
                }, ModLinks.DILLON8775_YOUTUBE, true));
            }).dimensions(this.width / 2 + 104, this.height / 4 + 48, 20, 20).build());

            this.nuzlandYouTubeButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.NUZLAND_YOUTUBE);
                    }
                    this.client.setScreen(this);
                }, ModLinks.NUZLAND_YOUTUBE, true));
            }).dimensions(this.width / 2 + 104, this.height / 4 + 72, 20, 20).build());

            this.mannyQuesoYouTubeButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.MANNYQUESO_YOUTUBE);
                    }
                    this.client.setScreen(this);
                }, ModLinks.MANNYQUESO_YOUTUBE, true));
            }).dimensions(this.width / 2 + 104, this.height / 4 + 96, 20, 20).build());
        }

        this.wikiButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
            this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open(ModLinks.WIKI);
                }
                this.client.setScreen(this);
            }, ModLinks.WIKI, true));
        }).dimensions(options().client.socialButtons ? this.width / 2 + 128 : this.width / 2 + 104, this.height / 4 + 96, 20, 20).build());
    }

    /**
     * Adds additional textures to the title screen.
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.drawTexture(new Identifier(SpeedrunnerMod.MOD_ID, "textures/item/golden_speedrunner_upgrade_smithing_template.png"), this.width / 2 - 122, this.featuresButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);

        if (options().advanced.showResetButton) {
            context.drawTexture(new Identifier(SpeedrunnerMod.MOD_ID, "textures/item/speedrunner_boots.png"), this.width / 2 - 122, createWorldButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        }

        context.drawTexture(SpeedrunnerMod.SPEEDRUNNER_MOD_ICON, (this.width / 2) - 123, optionsButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);

        if (options().client.socialButtons) {
            context.drawTexture(SpeedrunnerMod.DILLON8775_ICON, this.width / 2 + 105, dillon8775YouTubeButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);

            context.drawTexture(SpeedrunnerMod.NUZLAND_ICON, this.width / 2 + 105, nuzlandYouTubeButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);

            context.drawTexture(SpeedrunnerMod.MANNYQUESO_ICON, this.width / 2 + 105, mannyQuesoYouTubeButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);
        }

        context.drawTexture(SpeedrunnerMod.WIKI_ICON, options().client.socialButtons ? this.width / 2 + 130 : this.width / 2 + 106, wikiButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);

        this.renderTooltips(context, mouseX, mouseY);

        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(g * 255.0F) << 24;
        context.drawTextWithShadow(this.textRenderer, SpeedrunnerMod.THE_SPEEDRUNNER_MOD_STRING + " " + SpeedrunnerMod.MOD_VERSION, 2, this.height - 20, 16777215 | l);
    }

    /**
     * Renders the tooltips on the title screen buttons.
     */
    @Unique
    private void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (featuresButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.FEATURES_TOOLTIP, 200), mouseX, mouseY);
        }

        if (options().advanced.showResetButton && createWorldButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(options().client.fastWorldCreation ? ModTexts.CREATE_WORLD_BUTTON_TOOLTIP : ModTexts.CREATE_WORLD_BUTTON_DISABLED_TOOLTIP, 200), mouseX, mouseY);
        }

        if (optionsButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.OPTIONS_TOOLTIP, 200), mouseX, mouseY);
        }

        if (options().client.socialButtons) {
            if (dillon8775YouTubeButton.isHovered()) {
                context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.DILLON8775_YOUTUBE_TOOLTIP, 200), mouseX, mouseY);
            }

            if (nuzlandYouTubeButton.isHovered()) {
                context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.NUZLAND_YOUTUBE_TOOLTIP, 200), mouseX, mouseY);
            }

            if (mannyQuesoYouTubeButton.isHovered()) {
                context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.MANNYQUESO_YOUTUBE_TOOLTIP, 200), mouseX, mouseY);
            }
        }

        if (wikiButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.WIKI_TOOLTIP, 200), mouseX, mouseY);
        }
    }

    static {
        if (options().client.customPanorama) {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/panorama"));
        } else {
            PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
        }
    }
}