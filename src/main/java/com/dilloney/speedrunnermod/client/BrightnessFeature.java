package com.dilloney.speedrunnermod.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

import static java.lang.Float.POSITIVE_INFINITY;
import static net.minecraft.util.Formatting.GREEN;

public class BrightnessFeature {

    public static double minBrightness = 1.0;
    public static double maxBrightness = 5.0;
    public static double prevGamma = POSITIVE_INFINITY;
    public static double step = 0.5;

    public static final KeyBinding BRIGHTEN_BIND = new KeyBinding(
            "key.speedrunnermod.brighten",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "category.speedrunnermod.title"
    );

    public static final KeyBinding RAISE_BIND = new KeyBinding(
            "key.speedrunnermod.raise",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_EQUAL,
            "category.speedrunnermod.title"
    );

    public static final KeyBinding LOWER_BIND = new KeyBinding(
            "key.speedrunnermod.lower",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_MINUS,
            "category.speedrunnermod.title"
    );

    public static void onEndTick(MinecraftClient client) {
        boolean show = false;
        while (BRIGHTEN_BIND.wasPressed()) {
            double temp = client.options.gamma;
            client.options.gamma = MathHelper.clamp(prevGamma, minBrightness, maxBrightness);
            prevGamma = temp;
            show = true;
        }
        double gamma = client.options.gamma;
        while (RAISE_BIND.wasPressed()) {
            gamma += step;
        }
        while (LOWER_BIND.wasPressed()) {
            gamma -= step;
        }
        gamma = MathHelper.clamp(gamma, minBrightness, maxBrightness);
        if (client.options.gamma != gamma) {
            client.options.gamma = gamma;
            show = true;
        }
        if (show) {
            client.inGameHud.setOverlayMessage(new TranslatableText("overlay.speedrunnermod.set").append(String.format(" %d%%", Math.round(gamma * 100))).styled(s -> s.withColor(GREEN)), false);
        }
    }
}
