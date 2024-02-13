package net.dillon8775.speedrunnermod.mixin.client;

import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.option.ClientModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.debug;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class Keybindings {
    private final long SAVE_INTERVAL = 10000;
    @Shadow
    private GameOptions options;
    @Shadow @Final
    private InGameHud inGameHud;
    @Shadow
    private ClientWorld world;
    @Shadow
    abstract void disconnect(Screen screen);
    @Shadow
    abstract void setScreen(@Nullable Screen screen);
    private long lastSaveTime = 0;

    /**
     * Applies the {@code speedrunner mod keybinds} to the game.
     */
    @Inject(at = @At("TAIL"), method = "handleInputEvents")
    private void handleInputEvents(CallbackInfo info) {
        while (ClientModOptions.resetKey.isPressed()) {
            if (this.inGameHud != null) {
                this.inGameHud.getChatHud().clear(false);
            }
            this.world.disconnect();
            this.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
            this.setScreen(CreateWorldScreen.create(null));
        }

        while (ClientModOptions.fogKey.wasPressed()) {
            SpeedrunnerModClient.clientOptions().fog = !SpeedrunnerModClient.clientOptions().fog;
            ClientModOptions.saveClientConfig();
            MinecraftClient.getInstance().worldRenderer.reload();
            TranslatableText message = new TranslatableText(SpeedrunnerModClient.clientOptions().fog ? "speedrunnermod.toggle_fog_on" : "speedrunnermod.toggle_fog_off");
            this.inGameHud.getChatHud().addMessage(message);
        }

        while (ClientModOptions.hitboxesKey.wasPressed()) {
            boolean bl = !MinecraftClient.getInstance().getEntityRenderDispatcher().shouldRenderHitboxes();
            String hitboxes = bl ? "ON" : "OFF";
            MinecraftClient.getInstance().getEntityRenderDispatcher().setRenderHitboxes(bl);
            TranslatableText message = new TranslatableText(bl ? "speedrunnermod.toggle_hitboxes_on" : "speedrunnermod.toggle_hitboxes_off");
            this.inGameHud.getChatHud().addMessage(message);
            debug("Toggled hitboxes " + hitboxes);
        }

        while (ClientModOptions.chunkBordersKey.wasPressed()) {
            boolean bl = MinecraftClient.getInstance().debugRenderer.toggleShowChunkBorder();
            String chunkBorders = bl ? "ON" : "OFF";
            TranslatableText message = new TranslatableText(bl ? "speedrunnermod.toggle_chunk_borders_on" : "speedrunnermod.toggle_chunk_borders_off");
            this.inGameHud.getChatHud().addMessage(message);
            debug("Toggled chunk borders " + chunkBorders);
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