package net.dillon.speedrunnermod.mixin.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(LogoDrawer.class)
public class LogoDrawerMixin {

    @Inject(method = "draw(Lnet/minecraft/client/util/math/MatrixStack;IFI)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/LogoDrawer;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIFFIIII)V", ordinal = 0), cancellable = true)
    private void speedrunnerEdition(MatrixStack matrices, int screenWidth, float alpha, int y2, CallbackInfo ci) {
        ci.cancel();
        int i = screenWidth / 2 - 137;
        LogoDrawer.drawTexture(matrices, i + 58, y2 + 37, 0.0f, 0.0f, 184, 14, 184, 16);
    }
}