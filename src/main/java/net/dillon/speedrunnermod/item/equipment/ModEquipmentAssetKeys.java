package net.dillon.speedrunnermod.item.equipment;

import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

public interface ModEquipmentAssetKeys {
    RegistryKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = RegistryKey.ofRegistry(ofSpeedrunnerMod("equipment_asset"));
    RegistryKey<EquipmentAsset> SPEEDRUNNER = register("speedrunner");
    RegistryKey<EquipmentAsset> GOLDEN_SPEEDRUNNER = register("golden_speedrunner");

    static RegistryKey<EquipmentAsset> register(String name) {
        return RegistryKey.of(REGISTRY_KEY, ofSpeedrunnerMod(name));
    }
}