package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.EnumMap;

/**
 * All Speedrunner Mod {@code armor materials} (for helmets, chestplates, leggings, and boots).
 */
public interface ModArmorMaterials {
    ArmorMaterial SPEEDRUNNER = new ArmorMaterial(30, Util.make(new EnumMap(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 2);
        map.put(EquipmentType.CHESTPLATE, 7);
        map.put(EquipmentType.LEGGINGS, 6);
        map.put(EquipmentType.BOOTS, 2);
        map.put(EquipmentType.BODY, 8);
    }), 16, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_ARMOR_PATH);
    ArmorMaterial GOLDEN_SPEEDRUNNER = new ArmorMaterial(11, Util.make(new EnumMap(EquipmentType.class), map -> {
        map.put(EquipmentType.HELMET, 2);
        map.put(EquipmentType.CHESTPLATE, 6);
        map.put(EquipmentType.LEGGINGS, 4);
        map.put(EquipmentType.BOOTS, 2);
        map.put(EquipmentType.BODY, 8);
    }), 27, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, ModItemTags.GOLDEN_SPEEDRUNNER_TOOL_MATERIALS, ModItems.GOLDEN_SPEEDRUNNER_ARMOR_PATH);
}