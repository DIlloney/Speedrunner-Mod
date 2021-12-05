package net.dilloney.speedrunnermod.mixin.client;

import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.Option;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(EnvType.CLIENT)
public class ModMixinsClient {

    @Mixin(net.minecraft.client.render.BackgroundRenderer.class)
    public static class FogFeature {

        @Overwrite
        public static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
            SpeedrunnerModClient.applyNewFog(camera, fogType, viewDistance, thickFog);
        }
    }

    @Mixin(net.minecraft.client.gui.screen.VideoOptionsScreen.class)
    public static class FogOption {
        @Shadow
        private static final Option[] OPTIONS = SpeedrunnerModClient.newVideoOptions();
    }

    @Mixin(net.minecraft.client.MinecraftClient.class)
    public interface GameOptionsAccessor {
        @Accessor("options")
        GameOptions getGameOptions();
    }

    @Mixin(net.minecraft.server.network.ServerPlayerEntity.class)
    public interface SeenCreditsAccessor {
        @Accessor("seenCredits")
        boolean seenCredits();
    }
}