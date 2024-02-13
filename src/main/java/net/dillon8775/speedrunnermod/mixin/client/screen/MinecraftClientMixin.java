package net.dillon8775.speedrunnermod.mixin.client.screen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.client.gui.screen.SafeBootScreen;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.warn;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow
    public abstract void setScreen(@Nullable Screen screen);

    /**
     * <p>Adds the {@code Safe Mode} feature.</p>
     * <p>If the speedrunner mod detects broken options (see {@link ModOptions#safeCheck()}), then the game will load into the {@link SafeBootScreen}.</p>
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void safeBoot(RunArgs args, CallbackInfo ci) {
        if (SpeedrunnerMod.safeBoot) {
            this.setScreen(new SafeBootScreen());
            warn("Booted into safe mode, due to corrupt options. It is recommended that you fix these options before proceeding.");
        }
    }
}