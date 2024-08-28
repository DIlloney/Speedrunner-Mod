package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.client.screen.MainScreen;
import net.dillon.speedrunnermod.client.util.ModIcons;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    @Shadow @Final
    private boolean showMenu;
    @Unique
    private ButtonWidget optionsButton, createWorldButton, dillon8775YouTubeButton, discordButton, wikiButton;

    public GameMenuScreenMixin(Text title, ButtonWidget createWorldButton) {
        super(title);
    }

    /**
     * Adds additional buttons to the game menu screen (discord, options button, etc.)
     */
    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void addButtons(CallbackInfo ci) {
        if (this.showMenu) {
            if (options().advanced.showResetButton) {
                this.createWorldButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                    if (this.client.inGameHud != null) {
                        this.client.inGameHud.getChatHud().clear(false);
                    }
                    this.client.world.disconnect();
                    this.client.disconnect(new MessageScreen(Text.translatable("speedrunnermod.menu.generating_new_world")));
                    CreateWorldScreen.create(this.client, this);
                }).dimensions(this.width / 2 - 4 - 120 - 2, this.height / 4 + 72 - 16, 20, 20).build());
                this.createWorldButton.active = options().client.fastWorldCreation && this.client.isInSingleplayer() && this.client.isIntegratedServerRunning() && !this.client.getServer().isRemote();
            }

            this.optionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new MainScreen(this, MinecraftClient.getInstance().options));
            }).dimensions(this.width / 2 - 4 - 120 - 2, this.height / 4 + 96 - 16, 20, 20).build());

            if (options().client.socialButtons) {
                this.dillon8775YouTubeButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                    this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                        if (openInBrowser) {
                            Util.getOperatingSystem().open(ModLinks.DILLON8775_YOUTUBE);
                        }
                        this.client.setScreen(this);
                    }, ModLinks.DILLON8775_YOUTUBE, true));
                }).dimensions(this.width / 2 + 106, this.height / 4 + 48 - 16, 20, 20).build());
            }

            this.discordButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.DISCORD);
                    }
                    this.client.setScreen(this);
                }, ModLinks.DISCORD, false));
            }).dimensions(this.width / 2 + 106, this.height / 4 + 72 - 16, 20, 20).build());

            this.wikiButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.WIKI);
                    }
                    this.client.setScreen(this);
                }, ModLinks.WIKI, true));
            }).dimensions(this.width / 2 + 106, this.height / 4 + 96 - 16, 20, 20).build());
        }
    }

    /**
     * Renders additional textures on the game menu screen.
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.showMenu) {
            context.drawTexture(Identifier.of("speedrunnermod:textures/gui/speedrunner_mod.png"), this.width / 2 - 4 - 58 - 2, this.height / 4 - 26 + 2, 0.0F, 0.0F, 129, 16, 129, 16);

            if (options().advanced.showResetButton) {
                context.drawTexture(Identifier.of("speedrunnermod:textures/item/speedrunner_boots.png"), this.width / 2 - 4 - 118 - 2, createWorldButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            }

            context.drawTexture(ModIcons.SPEEDRUNNER_MOD_ICON, this.width / 2 - 4 - 119 - 2, optionsButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);

            if (options().client.socialButtons) {
                context.drawTexture(ModIcons.DILLON8775_ICON, this.width / 2 - 4 + 114 - 3, dillon8775YouTubeButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);
            }

            context.drawTexture(ModIcons.DISCORD_ICON, this.width / 2 - 4 + 114 - 2, discordButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            context.drawTexture(ModIcons.WIKI_ICON, this.width / 2 - 4 + 114 - 2, wikiButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);

            this.renderTooltips(context, mouseX, mouseY);
        }
    }

    /**
     * Renders tooltips on certain buttons.
     */
    @Unique
    private void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (options().advanced.showResetButton && createWorldButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(options().client.fastWorldCreation ? ModTexts.CREATE_WORLD_BUTTON_TOOLTIP : ModTexts.CREATE_WORLD_BUTTON_DISABLED_TOOLTIP, 200), mouseX, mouseY);
        }

        if (this.optionsButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.OPTIONS_TOOLTIP, 200), mouseX, mouseY);
        }

        if (options().client.socialButtons) {
            if (dillon8775YouTubeButton.isHovered()) {
                context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.DILLON8775_YOUTUBE_TOOLTIP, 200), mouseX, mouseY);
            }
        }

        if (this.discordButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.DISCORD_TOOLTIP, 200), mouseX, mouseY);
        }

        if (this.wikiButton.isHovered()) {
            context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(ModTexts.WIKI_TOOLTIP, 200), mouseX, mouseY);
        }
    }
}