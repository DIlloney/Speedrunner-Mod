package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.screen.MainScreen;
import net.dillon.speedrunnermod.client.util.ModIcons;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
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
    private ButtonWidget optionsButton, dillon8775YouTubeButton;

    public OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void addButtons(CallbackInfo ci) {
        if (options().client.socialButtons) {
            this.dillon8775YouTubeButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (button) -> {
                this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
                    if (openInBrowser) {
                        Util.getOperatingSystem().open(ModLinks.DILLON8775_YOUTUBE);
                    }
                    this.client.setScreen(this);
                }, ModLinks.DILLON8775_YOUTUBE, true));
            }).dimensions(this.width / 2 - 124, this.height / 6 + 168, 20, 20).build());
        }

        this.optionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (button) -> {
            this.client.setScreen(new MainScreen(this, this.settings));
        }).dimensions(this.width / 2 - 179, this.height / 6 + 128 - 6, 20, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        SpeedrunnerMod.info("RENDERED!");
        context.drawTexture(ModIcons.SPEEDRUNNER_MOD_ICON, (this.width / 2) - 178, this.optionsButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);
        if (options().client.socialButtons) {
            context.drawTexture(ModIcons.DILLON8775_ICON, this.width / 2 - 123, this.dillon8775YouTubeButton.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);
        }
    }
}