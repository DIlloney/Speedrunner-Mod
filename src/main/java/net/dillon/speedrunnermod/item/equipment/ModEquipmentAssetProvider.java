package net.dillon.speedrunnermod.item.equipment;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.RegistryKey;

import java.util.function.BiConsumer;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

@Environment(EnvType.CLIENT)
public class ModEquipmentAssetProvider {

    public static void bootstrap(BiConsumer<RegistryKey<EquipmentAsset>, EquipmentModel> equipmentBiConsumer) {
        equipmentBiConsumer.accept(ModEquipmentAssetKeys.SPEEDRUNNER, createHumanoidOnlyModel("speedrunner"));
        equipmentBiConsumer.accept(ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER, createHumanoidOnlyModel("golden_speedrunner"));
    }

    private static EquipmentModel createHumanoidOnlyModel(String id) {
        return EquipmentModel.builder().addHumanoidLayers(ofSpeedrunnerMod(id)).build();
    }
}