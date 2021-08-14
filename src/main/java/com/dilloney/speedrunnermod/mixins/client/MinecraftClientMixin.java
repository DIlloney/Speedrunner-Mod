package com.dilloney.speedrunnermod.mixins.client;

import com.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow GameOptions options;

    @Inject(method = "close", at = @At("HEAD"))
    private void writeOptions(CallbackInfo info) {
        options.write();
    }

    @Inject(method = "openScreen", at = @At("HEAD"))
    private void changeMaxBrightnessOptionSodium(Screen screen, CallbackInfo info) {
        if (screen != null && screen.getClass().getSimpleName().equals("SodiumOptionsGUI")) {
            try {
                List<?> optionPages = (List<?>) get(screen, "me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI", "pages");
                List<?> optionGroups = (List<?>) get(optionPages.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionPage", "groups");
                List<?> options = (List<?>) get(optionGroups.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionGroup", "options");
                Object sliderControl = get(options.get(1), "me.jellysquid.mods.sodium.client.gui.options.OptionImpl", "control");
                Class<?> sliderControlClass = Class.forName("me.jellysquid.mods.sodium.client.gui.options.control.SliderControl");
                setInt(sliderControl, sliderControlClass, "min", (int) (SpeedrunnerModClient.minBrightness * 100));
                setInt(sliderControl, sliderControlClass, "max", (int) (SpeedrunnerModClient.maxBrightness * 100));
            }
            catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Object get(Object instance, String className, String name) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Field f = Class.forName(className).getDeclaredField(name);
        f.setAccessible(true);
        return f.get(instance);
    }

    private void setInt(Object instance, Class<?> clazz, String field, int value) throws NoSuchFieldException, IllegalAccessException {
        Field f = clazz.getDeclaredField(field);
        f.setAccessible(true);
        f.setInt(instance, value);
    }
}