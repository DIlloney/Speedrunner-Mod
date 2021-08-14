package com.dilloney.speedrunnermod.mixins.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Shadow final static Identifier END_SKY;

    static {
        if (OPTIONS.doomMode) {
            END_SKY = new Identifier("textures/environment/end_sky_doom_mode.png");
        } else {
            END_SKY = new Identifier("textures/environment/end_sky.png");
        }
    }
}