package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

public class ModEnchantmentTags {
    public static TagKey<Enchantment> RETIRED_SPEEDRUNNER_TRADES = of("retired_speedrunner_trades");

    /**
     * Registers an {@code enchantment tag.}
     */
    private static TagKey<Enchantment> of(String path) {
        return TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SpeedrunnerMod.MOD_ID, path));
    }

    public static void initializeEnchantmentTags() {
        info("Initialized enchantment tags.");
    }
}