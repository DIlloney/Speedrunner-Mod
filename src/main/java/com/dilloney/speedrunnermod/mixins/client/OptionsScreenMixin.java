package com.dilloney.speedrunnermod.mixins.client;

import com.dilloney.speedrunnermod.client.ClothConfigScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void initMod(CallbackInfo callbackInfo) {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 144 - 6, 310, 20, new TranslatableText("options.speedrunnermod"), (button) -> {
            this.client.setScreen(ClothConfigScreen.getConfigBuilder().build());
        }));
    }
}