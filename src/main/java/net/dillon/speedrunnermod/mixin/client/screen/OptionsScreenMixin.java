package net.dillon.speedrunnermod.mixin.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.screen.ModMenuScreen;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
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

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

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
        dillon8775YouTubeButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (button) -> {
            this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                if (openInBrowser) {
                    Util.getOperatingSystem().open(ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK);
                }
                this.client.setScreen(this);
            }, ModLinks.DILLON8775_YOUTUBE_CHANNEL_LINK, true));
        }).dimensions(this.width / 2 - 124, this.height / 6 + 168, 20, 20).build());

        if (options().client.modButtonType == ModOptions.ModButtonType.LOGO) {
            optionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (button) -> {
                this.client.setScreen(new ModMenuScreen(this, this.settings));
            }).dimensions(this.width / 2 - 179, this.height / 6 + 120 - 6, 20, 20).build());
        } else {
            optionsButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.title"), (button) -> {
                this.client.setScreen(new ModMenuScreen(this, this.settings));
            }).dimensions(this.width / 2 + 5, this.height / 6 + 144 - 6, 150, 20).build());
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderTextures(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        RenderSystem.setShaderTexture(0, SpeedrunnerMod.DILLON8775_ICON);
        drawTexture(matrices, this.width / 2 - 123, dillon8775YouTubeButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);

        if (options().client.modButtonType == ModOptions.ModButtonType.LOGO) {
            RenderSystem.setShaderTexture(0, SpeedrunnerMod.SPEEDRUNNER_MOD_ICON);
            drawTexture(matrices, (this.width / 2) - 178, optionsButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);
        }
    }
}