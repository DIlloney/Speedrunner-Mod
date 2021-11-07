package net.dilloney.speedrunnermod.mod;

import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.lang.Math.abs;

/**
 * This class contains everything that needs to be changed in the game by using {@link Mixin}s. Client-side only.
 * <p> Please read {@link ModFeatures} for more information. </p>
 * <p> {@linkplain DebugModText}, {@linkplain ModButtonFunction}, {@linkplain ModButtonGM}, {@linkplain ModButtonTS}, {@linkplain ModOptionButton} </p>
 * <p> {@linkplain FogFeature} </p>
 * <p> {@linkplain BrightnessFeature}. </p>
 */
@Environment(EnvType.CLIENT)
public class ModFeaturesClient {

    @Mixin(BackgroundRenderer.class)
    public static class FogFeature {

        @Overwrite
        public static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
            SpeedrunnerModClient.injectNewFog(camera, fogType, viewDistance, thickFog);
        }
    }

    @Mixin(VideoOptionsScreen.class)
    public static class FogOption {

        @Shadow
        private static final Option[] OPTIONS = SpeedrunnerModClient.newVideoOptions();
    }

    @Mixin(DoubleOption.class)
    public static class BrightnessFeature {

        @Shadow @Final @Mutable
        BiFunction<GameOptions, DoubleOption, Text> displayStringGetter;

        @Shadow @Mutable
        double min, max;

        @Inject(method = "<init>", at = @At("RETURN"))
        private void init(String key, double min, double max, float step, Function<GameOptions, Double> getter, BiConsumer<GameOptions, Double> setter, BiFunction<GameOptions, DoubleOption, Text> displayStringGetter, Function<MinecraftClient, List<OrderedText>> function, CallbackInfo info) {
            if (key.equals("options.gamma")) {
                this.min = SpeedrunnerModClient.minBrightness;
                this.max = SpeedrunnerModClient.maxBrightness;
                this.displayStringGetter = this::displayStringGetter;
            }
        }

        private Text displayStringGetter(GameOptions gameOptions, DoubleOption doubleOption) {
            double threshold = 0.025;
            return new TranslatableText("options.gamma").append(": ").append(abs(gameOptions.gamma) < threshold ? new TranslatableText("options.gamma.min") : abs(gameOptions.gamma - 1) < threshold ? new TranslatableText("options.gamma.max") : new LiteralText(Math.round(gameOptions.gamma * 100) + "%"));
        }
    }

    @Mixin(MinecraftClient.class)
    public interface GameOptionsAccessor {
        @Accessor("options")
        GameOptions getGameOptions();
    }

    @Mixin(ServerPlayerEntity.class)
    public interface SeenCreditsAccessor {
        @Accessor("seenCredits")
        boolean seenCredits();
    }
}
