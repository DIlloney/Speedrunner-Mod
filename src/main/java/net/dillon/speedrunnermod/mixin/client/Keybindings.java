package net.dillon.speedrunnermod.mixin.client;

import net.dillon.speedrunnermod.SpeedrunnerModClient;
import net.dillon.speedrunnermod.client.keybind.ModKeybindings;
import net.dillon.speedrunnermod.client.util.ModTexts;
import net.dillon.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Implements all keybindings functions into the game.
 */
@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class Keybindings {
    @Shadow @Final
    public InGameHud inGameHud;
    @Shadow
    public ClientWorld world;
    @Shadow
    public abstract void disconnect(Screen screen);
    @Shadow
    public abstract void setScreen(@Nullable Screen screen);
    @Shadow
    public abstract boolean isInSingleplayer();
    @Shadow
    public abstract boolean isIntegratedServerRunning();
    @Shadow @Nullable
    public abstract IntegratedServer getServer();
    @Shadow @Final
    public GameOptions options;

    /**
     * Ensures that the {@code fullbright} option is correctly initialized when launching the game.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void checkGamma(RunArgs args, CallbackInfo ci) {
        options().client.fullBright.set(MinecraftClient.getInstance().options.getGamma().getValue() >= 10.0D);
        ModOptions.saveConfig();
    }

    /**
     * All speedrunner mod {@code keybinding} functions.
     */
    @Inject(at = @At("TAIL"), method = "handleInputEvents")
    private void handleInputEvents(CallbackInfo info) {
        while (ModKeybindings.resetKey.wasPressed()) {
            if (this.isInSingleplayer() && this.isIntegratedServerRunning() && !this.getServer().isRemote()) {
                if (options().client.fastWorldCreation.getCurrentValue()) {
                    if (this.inGameHud != null) {
                        this.inGameHud.getChatHud().clear(false);
                    }
                    assert this.world != null;
                    this.world.disconnect();
                    this.disconnect(new MessageScreen(Text.translatable("speedrunnermod.menu.generating_new_world")));
                    CreateWorldScreen.create(MinecraftClient.getInstance(), null);
                } else {
                    debugWarn("\"Fast World Creation\" is OFF, please enable to use this feature.");
                }
            } else {
                debugWarn("You must be in singleplayer to create new worlds.");
            }
        }

        while (ModKeybindings.hitboxesKey.wasPressed()) {
            boolean bl = !MinecraftClient.getInstance().getEntityRenderDispatcher().shouldRenderHitboxes();
            MinecraftClient.getInstance().getEntityRenderDispatcher().setRenderHitboxes(bl);
            debugWarn(bl ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off");
        }

        while (ModKeybindings.chunkBordersKey.wasPressed()) {
            boolean bl = MinecraftClient.getInstance().debugRenderer.toggleShowChunkBorder();
            debugWarn(bl ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off");
        }

        while (ModKeybindings.fogKey.wasPressed()) {
            if (options().mixins.backgroundRendererMixin.getCurrentValue()) {
                options().client.fog.invert();
                ModOptions.saveConfig();
                MinecraftClient.getInstance().worldRenderer.reload();
                debugWarn(options().client.fog.getCurrentValue() ? "speedrunnermod.toggle_fog.on" : "speedrunnermod.toggle_fog.off");
            } else {
                debugWarn("speedrunnermod.fog.mixin_disabled");
            }
        }

        while (ModKeybindings.fullbrightKey.wasPressed()) {
            options().client.fullBright.invert();
            ModOptions.saveConfig();
            MinecraftClient.getInstance().options.getGamma().setValue(options().client.fullBright.getCurrentValue() ? SpeedrunnerModClient.getMaxBrightness() : 1.0D);
            debugWarn(options().client.fullBright.getCurrentValue() ? "speedrunnermod.toggle_fullbright.on" : "speedrunnermod.toggle_fullbright.off");
            MinecraftClient.getInstance().options.write();
        }
    }

    /**
     * Displays the {@code [Debug Warn]:} prefix when sending a debug message.
     */
    @Unique
    private void debugWarn(String string, Object... objects) {
        this.inGameHud.getChatHud().addMessage((ModTexts.BLANK).copy().append((Text.translatable("debug.prefix")).formatted(Formatting.YELLOW, Formatting.BOLD)).append(" ").append(Text.translatable(string, objects)));
    }
}