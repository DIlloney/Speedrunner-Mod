package net.dillon.speedrunnermod.client.keybind;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerModClient.isSimpleKeybindsLoaded;

/**
 * All of the {@code Speedrunner mod} keybinds.
 */
@Environment(EnvType.CLIENT)
public class ModKeybindings {
    public static final String MOD_KEYBINDS = "speedrunnermod.keybinds";
    public static KeyBinding resetKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.create_new_world", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, MOD_KEYBINDS));
    public static KeyBinding fogKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.toggle_fog", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, MOD_KEYBINDS));
    public static KeyBinding fullbrightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.toggle_fullbright", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, MOD_KEYBINDS));
    public static KeyBinding hitboxesKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.toggle_hitboxes", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, MOD_KEYBINDS));
    public static KeyBinding chunkBordersKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.toggle_chunk_borders", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, MOD_KEYBINDS));

    public static void clinit() {
        info("Initialized keybinds.");
        if (isSimpleKeybindsLoaded()) {
            info("Simple Keybinds mod is loaded, compatibility has been added.");
        }
    }
}