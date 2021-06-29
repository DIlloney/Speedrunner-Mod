package com.dilloney.speedrunnermod;

import com.dilloney.speedrunnermod.items.ModItems;
import com.google.gson.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.DoubleConsumer;

import static java.lang.Float.POSITIVE_INFINITY;
import static net.minecraft.util.Formatting.GREEN;

@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(BRIGHTEN_BIND);
        KeyBindingHelper.registerKeyBinding(RAISE_BIND);
        KeyBindingHelper.registerKeyBinding(LOWER_BIND);
        loadConfig();
        ClientTickEvents.END_CLIENT_TICK.register(this::onEndTick);

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW.asItem(), new Identifier("pull"),
                (itemStack, clientWorld, livingEntity) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    } else {
                        return livingEntity.getActiveItem() != itemStack ? 0.0F
                                : (float) (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
                    }
                });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW.asItem(), new Identifier("pulling"),
                (itemStack, clientWorld, livingEntity) -> {
                    return livingEntity != null && livingEntity.isUsingItem()
                            && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
                });

        System.out.println("Speedrunner Mod loaded successfully! version = 1.14 | mcversion = 1.16.5");
    }

    public static final Gson GSON = new Gson();
    public static double minBrightness = 1.0;
    public static double maxBrightness = 5.0;
    public static double prevGamma = POSITIVE_INFINITY;
    public static double step = 0.5;

    private static final KeyBinding BRIGHTEN_BIND = new KeyBinding(
            "key.speedrunnermod.brighten",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "category.speedrunnermod.title"
    );

    private static final KeyBinding RAISE_BIND = new KeyBinding(
            "key.speedrunnermod.raise",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_EQUAL,
            "category.speedrunnermod.title"
    );

    private static final KeyBinding LOWER_BIND = new KeyBinding(
            "key.speedrunnermod.lower",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_MINUS,
            "category.speedrunnermod.title"
    );

    private void loadConfig() {
        try {
            JsonObject config = GSON.fromJson(new String(Files.readAllBytes(getConfigPath())), JsonObject.class);
            asDouble(config.get("min"), min -> minBrightness = min);
            asDouble(config.get("max"), max -> maxBrightness = max);
            asDouble(config.get("step"), step -> SpeedrunnerModClient.step = step);
            asDouble(config.get("previous"), prev -> prevGamma = prev);
        }
        catch (IOException | JsonSyntaxException ex) {
            logException(ex, "failed to load config");
        }
    }

    public static void saveConfig() {
        JsonObject config = new JsonObject();
        config.addProperty("min", minBrightness);
        config.addProperty("max", maxBrightness);
        config.addProperty("step", step);
        config.addProperty("previous", prevGamma);
        try {
            Files.write(getConfigPath(), GSON.toJson(config).getBytes());
        }
        catch (IOException ex) {
            logException(ex, "failed to save config");
        }
    }

    private static Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir().resolve("speedrunnermodbrightness.json");
    }

    private void asDouble(JsonElement element, DoubleConsumer onSuccess) {
        if (element != null && element.isJsonPrimitive() && ((JsonPrimitive) element).isNumber()) {
            onSuccess.accept(element.getAsDouble());
        }
    }

    private void onEndTick(MinecraftClient client) {
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
            client.inGameHud.setOverlayMessage(new TranslatableText("overlay.speedrunnermod.set")
                    .append(String.format(" %d%%", Math.round(gamma * 100))).styled(s -> s.withColor(GREEN)), false);
        }
    }

    public static void logException(Exception ex, String message) {
        System.err.printf("[SpeedrunnerMod Brightness] %s (%s: %s)", message, ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }
}
