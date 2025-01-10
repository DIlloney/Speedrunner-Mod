package net.dillon.speedrunnermod.tag;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

public class ModEnchantmentTags {
    public static TagKey<Enchantment> RETIRED_SPEEDRUNNER_TRADES = of("retired_speedrunner_trades");

    /**
     * Registers an {@code enchantment tag.}
     */
    private static TagKey<Enchantment> of(String path) {
        return TagKey.of(RegistryKeys.ENCHANTMENT, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code enchantment tags.}
     */
    public static void initializeEnchantmentTags() {}
}