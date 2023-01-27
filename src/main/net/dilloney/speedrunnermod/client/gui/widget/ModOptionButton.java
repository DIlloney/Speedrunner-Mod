package net.dilloney.speedrunnermod.client.gui.widget;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.client.gui.screen.ModMenuScreen;
import net.dilloney.speedrunnermod.client.gui.screen.SocialsScreen;
import net.dilloney.speedrunnermod.option.CLModOptions;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class ModOptionButton extends Screen {
    @Shadow @Final
    private GameOptions settings;

    public ModOptionButton(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        this.addButton(new ButtonWidget(this.width / 2 - 124, this.height / 6 + 168, 20, 20, new LiteralText(""), (button) -> {
            Util.getOperatingSystem().open(SocialsScreen.DILLONEY_YOUTUBE_CHANNEL_LINK);
        }));

        if (SpeedrunnerModClient.clOptions().modButtonType == CLModOptions.ModButtonType.LOGO) {
            this.addButton(new ButtonWidget(this.width / 2 - 179, this.height / 6 + 120 - 6, 20, 20, new LiteralText(""), (button) -> {
                this.client.openScreen(new ModMenuScreen(this, this.settings));
            }));
        } else {
            this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 144 - 6, 310, 20, new TranslatableText("speedrunnermod.title"), (button) -> {
                this.client.openScreen(new ModMenuScreen(this, this.settings));
            }));
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderModOptionsButtonTexture(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.client.getTextureManager().bindTexture(new Identifier("dilloney:textures/dilloney.png"));
        drawTexture(matrices, this.width / 2 - 123, this.height / 6 + 169, 0.0F, 0.0F, 18, 18, 18, 18);

        if (SpeedrunnerModClient.clOptions().modButtonType == CLModOptions.ModButtonType.LOGO) {
            this.client.getTextureManager().bindTexture(SpeedrunnerMod.SPEEDRUNNER_MOD_ICON);
            drawTexture(matrices, (this.width / 2) - 178, this.height / 6 + 119 - 6 + 2, 0.0F, 0.0F, 18, 18, 18, 18);
        }
    }
}