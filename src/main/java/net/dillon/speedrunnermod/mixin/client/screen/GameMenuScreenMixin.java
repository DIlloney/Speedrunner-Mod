package net.dillon.speedrunnermod.mixin.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.screen.ModMenuScreen;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
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
    private final ButtonWidget constructorCreateWorldButton;
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

    public GameMenuScreenMixin(Text title, ButtonWidget constructorCreateWorldButton) {
        super(title);
        this.constructorCreateWorldButton = constructorCreateWorldButton;
    }

    /**
     * Adds additional buttons to the title screen.
     */
    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void addButtons(CallbackInfo ci) {
        if (showMenu) {
            if (options().advanced.showResetButton) {
                createWorldButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 4 - 120 - 2, this.height / 4 + 72 - 16, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
                    if (this.client.inGameHud != null) {
                        this.client.inGameHud.getChatHud().clear(false);
                    }
                    this.client.world.disconnect();
                    this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
                    this.client.setScreen(CreateWorldScreen.create(this));
                }));
                createWorldButton.active = options().client.fastWorldCreation && this.client.isInSingleplayer() && this.client.isIntegratedServerRunning() && !this.client.getServer().isRemote();
            }

            optionsButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 4 - 120 - 2, this.height / 4 + 96 - 16, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
                this.client.setScreen(new ModMenuScreen(this, MinecraftClient.getInstance().options));
            }));

            if (options().client.socialButtons) {
                dillon8775YouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 4 - 120 - 2, this.height / 4 + 48 - 16, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
                    this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                        if (openInBrowser) {
                            Util.getOperatingSystem().open(ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK);
                        }
                        this.client.setScreen(this);
                    }, ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK, true));
                }));

                discordButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 106, this.height / 4 + 72 - 16, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
                    this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                        if (openInBrowser) {
                            Util.getOperatingSystem().open(ModLinks.DISCORD_LINK);
                        }
                        this.client.setScreen(this);
                    }, ModLinks.DISCORD_LINK, false));
                }));

                webpageButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 106, this.height / 4 + 96 - 16, 20, 20, ModTexts.BLANK, (buttonWidget) -> {
                    this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                        if (openInBrowser) {
                            Util.getOperatingSystem().open(ModLinks.SPEEDRUNNER_MOD_WEBPAGE_LINK);
                        }
                        this.client.setScreen(this);
                    }, ModLinks.SPEEDRUNNER_MOD_WEBPAGE_LINK, true));
                }));
            }
        }
    }

    /**
     * Renders additional textures on the pause menu screen.
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void renderTextures(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (showMenu) {
            RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/gui/speedrunner_mod.png"));
            drawTexture(matrices, this.width / 2 - 4 - 58 - 2, this.height / 4 - 26 + 2, 0.0F, 0.0F, 129, 16, 129, 16);

            if (options().advanced.showResetButton) {
                RenderSystem.setShaderTexture(0, new Identifier("speedrunnermod:textures/item/speedrunner_boots.png"));
                drawTexture(matrices, this.width / 2 - 4 - 118 - 2, createWorldButton.y + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            }

            RenderSystem.setShaderTexture(0, SpeedrunnerMod.SPEEDRUNNER_MOD_ICON);
            drawTexture(matrices, this.width / 2 - 4 - 119 - 2, optionsButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);

            if (options().client.socialButtons) {
                RenderSystem.setShaderTexture(0, SpeedrunnerMod.DILLON8775_ICON);
                drawTexture(matrices, this.width / 2 - 4 - 119 - 2, dillon8775YouTubeButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);
                RenderSystem.setShaderTexture(0, SpeedrunnerMod.DISCORD_ICON);
                drawTexture(matrices, this.width / 2 - 4 + 114 - 2, discordButton.y + 2, 0.0F, 0.0F, 16, 16, 16, 16);

                RenderSystem.setShaderTexture(0, SpeedrunnerMod.WEBPAGE_ICON);
                drawTexture(matrices, this.width / 2 - 4 + 114 - 2, webpageButton.y + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            }
        }
    }
}