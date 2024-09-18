package net.dillon.speedrunnermod.enchantment;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * All Speedrunner Mod {@code enchantments.}
 */
public class ModEnchantments {
    // For boots, grants the player the speed effect, amplifier increased for each level
    public static final RegistryKey<Enchantment> DASH = registerEnchantment("dash");
    // For items that have a "cooldown" (shields, ender pearls, chorus fruit, etc.), this lowers the cooldown on those items
    public static final RegistryKey<Enchantment> COOLDOWN = registerEnchantment("cooldown");

    /**
     * Registers an {@code enchantment.}
     */
    private static RegistryKey<Enchantment> registerEnchantment(String path) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SpeedrunnerMod.MOD_ID, path));
    }

    public static void initializeEnchantments() {
        info("Initialized enchantments.");
    }
}