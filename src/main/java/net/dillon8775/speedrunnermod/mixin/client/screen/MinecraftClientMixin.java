package net.dillon8775.speedrunnermod.mixin.client.screen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.client.screen.SafeBootScreen;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.warn;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    private final long SAVE_INTERVAL = 10000;
    @Shadow
    public abstract void setScreen(@Nullable Screen screen);
    @Shadow @Final
    public GameOptions options;
    private long lastSaveTime = 0;

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

    @Inject(at = @At("HEAD"), method = "close")
    private void close(CallbackInfo info) {
        options.write();
    }

    /**
     * Allows compatibility with Sodium with the extra brightness feature.
     */
    @Inject(at = @At("HEAD"), method = "setScreen")
    private void setScreenInject(Screen screen, CallbackInfo info) {
        if (screen instanceof GameMenuScreen && System.currentTimeMillis() - lastSaveTime > SAVE_INTERVAL) {
            lastSaveTime = System.currentTimeMillis();
        }

        if (screen != null && screen.getClass().getSimpleName().equals("SodiumOptionsGUI")) {
            try {
                List<?> optionPages = (List<?>) get(screen, "me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI", "pages");
                List<?> optionGroups = (List<?>) get(optionPages.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionPage", "groups");
                List<?> options = (List<?>) get(optionGroups.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionGroup", "options");
                Object sliderControl = get(options.get(2), "me.jellysquid.mods.sodium.client.gui.options.OptionImpl", "control");
                Class<?> sliderControlClass = Class.forName("me.jellysquid.mods.sodium.client.gui.options.control.SliderControl");
                setInt(sliderControl, sliderControlClass, "min", (int) (1.0D * 100));
                setInt(sliderControl, sliderControlClass, "max", (int) (5.0D * 100));
                setInt(sliderControl, sliderControlClass, "interval", 10);
            } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException ex) {
                SpeedrunnerModClient.logException(ex, "an exception occurred during the manipulation of the sodium options gui");
            }
        }
    }

    @Unique
    private Object get(Object instance, String className, String name) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Field f = Class.forName(className).getDeclaredField(name);
        f.setAccessible(true);
        return f.get(instance);
    }

    @Unique
    private void setInt(Object instance, Class<?> clazz, String field, int value) throws NoSuchFieldException, IllegalAccessException {
        Field f = clazz.getDeclaredField(field);
        f.setAccessible(true);
        f.setInt(instance, value);
    }
}