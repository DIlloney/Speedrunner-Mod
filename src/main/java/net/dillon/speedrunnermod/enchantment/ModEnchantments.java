package net.dillon.speedrunnermod.enchantment;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * All of the {@code speedrunner mod} enchantments.
 */
public class ModEnchantments {
    public static final RegistryKey<Enchantment> DASH = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SpeedrunnerMod.MOD_ID, "dash"));
    public static final RegistryKey<Enchantment> COOLDOWN = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SpeedrunnerMod.MOD_ID, "cooldown"));

    public static void init() {
        info("Initialized enchantments.");
    }
}