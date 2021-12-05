package net.dilloney.speedrunnermod.client.gui.widget;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.client.gui.screen.ModMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.screen.options.OptionsScreen.class)
public class ModOptionButton extends Screen {
    @Shadow @Final
    private GameOptions settings;

    public ModOptionButton(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        this.addButton(new ButtonWidget(this.width / 2 - 179, this.height / 6 + 120 - 6, 20, 20, new LiteralText(""), (button) -> {
            this.client.openScreen(new ModMenuScreen(this, this.settings));
        }));
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderModOptionsButtonTexture(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.client.getTextureManager().bindTexture(SpeedrunnerMod.SPEEDRUNNER_INGOT);
        drawTexture(matrices, (this.width / 2) - 177, this.height / 6 + 120 - 6 + 2, 0.0F, 0.0F, 16, 16, 16, 16);
    }
}