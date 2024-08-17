package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow
    public static final CubeMapRenderer PANORAMA_RENDERER;

    static {
        if (options().client.customPanorama) {
            PANORAMA_RENDERER = new CubeMapRenderer(Identifier.of(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/panorama"));
        } else {
            PANORAMA_RENDERER = new CubeMapRenderer(Identifier.ofVanilla("textures/gui/title/background/panorama"));
        }
    }
}