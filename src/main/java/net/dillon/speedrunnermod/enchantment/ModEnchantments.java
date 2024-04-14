package net.dillon.speedrunnermod.enchantment;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * All of the {@code speedrunner mod} enchantments.
 */
public class ModEnchantments {
    public static final Enchantment DASH = Registry.register(Registries.ENCHANTMENT, new Identifier(SpeedrunnerMod.MOD_ID, "dash"),
            new DashEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.FEET));
    public static final Enchantment COOLDOWN = Registry.register(Registries.ENCHANTMENT, new Identifier(SpeedrunnerMod.MOD_ID, "cooldown"),
            new CooldownEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static void init() {
        info("Initialized enchantments.");
    }

    /**
     * The enchantments that can be offered with the {@link net.dillon.speedrunnermod.village.ModTradeOffers.MaxedEnchantBookFactory} trades.
     */
    public static List<Enchantment> availableForRetiredSpeedrunnerTrades() {
        return List.of(Enchantments.PROTECTION, Enchantments.FEATHER_FALLING, Enchantments.THORNS, Enchantments.SHARPNESS, Enchantments.FIRE_ASPECT, Enchantments.LOOTING, Enchantments.FORTUNE, Enchantments.POWER, Enchantments.INFINITY, Enchantments.MENDING);
    }
}