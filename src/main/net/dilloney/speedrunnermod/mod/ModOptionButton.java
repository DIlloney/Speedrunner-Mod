package net.dilloney.speedrunnermod.mod;

import net.dilloney.speedrunnermod.client.ModMenuScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(OptionsScreen.class)
public class ModOptionButton extends Screen {

    public ModOptionButton(Text title) {
        super(title);
    }

    @Shadow @Final
    private GameOptions settings;

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 144 - 6, 310, 20, new TranslatableText("speedrunnermod.title"), (button) -> {
            this.client.openScreen(new ModMenuScreen(this, this.settings));
        }));
    }
}