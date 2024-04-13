package net.dillon.speedrunnermod.mixin.client.hud;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Environment(EnvType.CLIENT)
@Mixin(DebugHud.class)
public class DebugModText {

    @Inject(method = "getRightText", at = @At("RETURN"))
    private void getRightText(CallbackInfoReturnable<List<String>> cir) {
        List<String> returnValue = cir.getReturnValue();
        returnValue.add(SpeedrunnerMod.THE_SPEEDRUNNER_MOD_STRING + " " + SpeedrunnerMod.MOD_VERSION);
        if (DOOM_MODE) {
            returnValue.add("What's that? Doom Mode? Oh, flip.");
        }
    }
}