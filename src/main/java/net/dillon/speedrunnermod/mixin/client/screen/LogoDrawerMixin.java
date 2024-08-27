package net.dillon.speedrunnermod.mixin.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(LogoDrawer.class)
public class LogoDrawerMixin {

    /**
     * Renders the {@code Speedrunner's Edition} text on the title screen.
     */
    @Inject(method = "draw(Lnet/minecraft/client/gui/DrawContext;IFI)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIFFIIII)V", ordinal = 1), cancellable = true)
    private void speedrunnerEdition(DrawContext context, int screenWidth, float alpha, int y2, CallbackInfo ci) {
        ci.cancel();
        int i = screenWidth / 2 - 137;
        context.drawTexture(LogoDrawer.EDITION_TEXTURE, i + 58, y2 + 37, 0.0f, 0.0f, 184, 14, 184, 16);
    }
}