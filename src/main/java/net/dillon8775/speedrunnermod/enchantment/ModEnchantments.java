package net.dillon8775.speedrunnermod.enchantment;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

/**
 * All of the {@code speedrunner mod} enchantments.
 */
public class ModEnchantments {
    public static final Enchantment DASH = Registry.register(Registry.ENCHANTMENT, new Identifier(SpeedrunnerMod.MOD_ID, "dash"),
            new DashEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.FEET));
    public static final Enchantment COOL = Registry.register(Registry.ENCHANTMENT, new Identifier(SpeedrunnerMod.MOD_ID, "cooldown"),
            new CooldownEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));

    public static void init() {
        info("Initialized enchantments.");
    }
}