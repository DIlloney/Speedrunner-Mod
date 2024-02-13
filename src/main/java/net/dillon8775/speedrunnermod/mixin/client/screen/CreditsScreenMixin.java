package net.dillon8775.speedrunnermod.mixin.client.screen;

import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CreditsScreen.class)
public class CreditsScreenMixin extends Screen {

    public CreditsScreenMixin(Text title) {
        super(title);
    }

    /**
     * <p>Fixes the speedrunner edition logo not rendering correctly on the title screen.</p>
     * <p>Using {@link ModifyArgs}, we can get the values inside of the "drawTexture" method, and overwrite those values.</p>
     */
    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/CreditsScreen;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIFFIIII)V"))
    private void fixSpeedrunnerEditionRendering(Args args) {
        args.set(5, 184);
        args.set(7, 184);
    }
}