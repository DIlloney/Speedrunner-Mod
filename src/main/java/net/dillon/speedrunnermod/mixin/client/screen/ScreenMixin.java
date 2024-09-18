package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Applies the custom panorama.
 */
@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow
    public static final RotatingCubeMapRenderer ROTATING_PANORAMA_RENDERER;
    @Shadow @Final
    public static CubeMapRenderer PANORAMA_RENDERER;

    static {
        if (options().client.customPanorama.getCurrentValue()) {
            ROTATING_PANORAMA_RENDERER = new RotatingCubeMapRenderer(new CubeMapRenderer(Identifier.of(SpeedrunnerMod.MOD_ID, "textures/gui/title/background/panorama")));
        } else {
            ROTATING_PANORAMA_RENDERER = new RotatingCubeMapRenderer(PANORAMA_RENDERER);
        }
    }
}