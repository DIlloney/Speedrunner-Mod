package net.dillon8775.speedrunnermod.mixin.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.screen.ModMenuScreen;
import net.dillon8775.speedrunnermod.client.util.ModLinks;
import net.dillon8775.speedrunnermod.client.util.ModTexts;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {
    @Shadow @Final
    private GameOptions settings;
    @Unique
    private ButtonWidget optionsButton;
    @Unique
    private ButtonWidget dillon8775YouTubeButton;

    public OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void addButtons(CallbackInfo ci) {
        dillon8775YouTubeButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 124, this.height / 6 + 168, 20, 20, ModTexts.BLANK, (button) -> {
            this.client.setScreen(new ConfirmChatLinkScreen(openInBrowser -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open(ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK);
                }
                this.client.setScreen(this);
            }, ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK, true));
        }));

        if (options().client.modButtonType == ModOptions.ModButtonType.LOGO) {
            optionsButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 179, this.height / 6 + 120 - 6, 20, 20, ModTexts.BLANK, (button) -> {
                this.client.setScreen(new ModMenuScreen(this, this.settings));
            }));
        } else {
            optionsButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 144 - 6, 310, 20, Text.translatable("speedrunnermod.title"), (button) -> {
                this.client.setScreen(new ModMenuScreen(this, this.settings));
            }));
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderTextures(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        RenderSystem.setShaderTexture(0, SpeedrunnerMod.DILLON8775_ICON);
        drawTexture(matrices, this.width / 2 - 123, dillon8775YouTubeButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);

        if (options().client.modButtonType == ModOptions.ModButtonType.LOGO) {
            RenderSystem.setShaderTexture(0, SpeedrunnerMod.SPEEDRUNNER_MOD_ICON);
            drawTexture(matrices, (this.width / 2) - 178, optionsButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);
        }
    }
}