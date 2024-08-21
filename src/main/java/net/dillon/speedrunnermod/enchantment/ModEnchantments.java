package net.dillon.speedrunnermod.enchantment;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;

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

    /**
     * The enchantments that can be offered with the {@link net.dillon.speedrunnermod.village.ModTradeOffers.MaxedEnchantBookFactory} trades.
     */
    public static List<RegistryKey<Enchantment>> availableForRetiredSpeedrunnerTrades() {
        return List.of(Enchantments.PROTECTION, Enchantments.FEATHER_FALLING, Enchantments.THORNS, Enchantments.SHARPNESS, Enchantments.FIRE_ASPECT, Enchantments.LOOTING, Enchantments.FORTUNE, Enchantments.POWER, Enchantments.INFINITY, Enchantments.MENDING);
    }
}