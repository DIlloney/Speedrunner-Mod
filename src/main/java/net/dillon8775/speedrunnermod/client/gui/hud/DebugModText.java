package net.dillon8775.speedrunnermod.client.gui.hud;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(DebugHud.class)
public class DebugModText {

    @Inject(method = "getRightText", at = @At("RETURN"), cancellable = true)
    private void getRightText(CallbackInfoReturnable<List<String>> cir) {
        List<String> returnValue = (List<String>)cir.getReturnValue();
        returnValue.add(SpeedrunnerMod.THE_SPEEDRUNNER_MOD_STRING + " " + SpeedrunnerMod.MOD_VERSION);
    }
}