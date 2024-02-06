package net.dillon8775.speedrunnermod.enchantment;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {
    public static final Enchantment DASH = Registry.register(Registry.ENCHANTMENT, new Identifier(SpeedrunnerMod.MOD_ID, "dash"),
            new DashEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.FEET));

    public static void init() {
    }
}