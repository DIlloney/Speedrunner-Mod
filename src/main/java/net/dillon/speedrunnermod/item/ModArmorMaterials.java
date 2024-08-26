package net.dillon.speedrunnermod.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.EnumMap;

/**
 * All Speedrunner Mod {@code armor materials} (for helmets, chestplates, leggings, and boots).
 */
public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> SPEEDRUNNER = ArmorMaterials.register("speedrunner", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.LEGGINGS, 6);
        map.put(ArmorItem.Type.CHESTPLATE, 7);
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.BODY, 8);
    }), 16, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(ModItems.SPEEDRUNNER_INGOT);
    });
    public static final RegistryEntry<ArmorMaterial> GOLDEN_SPEEDRUNNER = ArmorMaterials.register("golden_speedrunner", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.LEGGINGS, 4);
        map.put(ArmorItem.Type.CHESTPLATE, 6);
        map.put(ArmorItem.Type.HELMET, 2);
    }), 27, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(Items.GOLD_INGOT);
    });
}