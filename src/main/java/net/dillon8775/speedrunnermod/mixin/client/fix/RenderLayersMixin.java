package net.dillon8775.speedrunnermod.mixin.client.fix;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Environment(EnvType.CLIENT)
@Mixin(RenderLayers.class)
public class RenderLayersMixin {
    private static Map<Fluid, RenderLayer> LAVA = Util.make(Maps.newHashMap(), hashMap -> {
        RenderLayer renderLayer = RenderLayer.getTranslucent();
        hashMap.put(Fluids.FLOWING_LAVA, renderLayer);
        hashMap.put(Fluids.LAVA, renderLayer);
    });

    /**
     * Allows modded boats to render correctly when in lava.
     */
    @Inject(method = "getFluidLayer", at = @At("RETURN"), cancellable = true)
    private static void renderLava(FluidState state, CallbackInfoReturnable<RenderLayer> cir) {
        RenderLayer lavaRenderLayer = LAVA.get(state.getFluid());
        if (lavaRenderLayer != null) {
            cir.setReturnValue(lavaRenderLayer);
        }
    }
}