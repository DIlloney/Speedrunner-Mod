package com.dilloney.speedrunnermod.world.feature;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.dilloney.speedrunnermod.mixins.misc.StructuresConfigAccessor;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.HashMap;
import java.util.Map;

public final class StructureGeneration {

    public static void modifiedStructureGeneration() {
        if (SpeedrunnerMod.CONFIG.makeStructuresMoreCommon) {
            ServerWorldEvents.LOAD.register((server, world) -> {
                Map<StructureFeature<?>, StructureConfig> map = new HashMap<>(world.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());

                map.computeIfPresent(StructureFeature.RUINED_PORTAL, (structureFeature, structureConfig) -> {
                    return new StructureConfig(9, 8, 34222645);
                });
                map.computeIfPresent(StructureFeature.VILLAGE, (structureFeature, structureConfig) -> {
                    return new StructureConfig(16, 9, 10387312);
                });
                map.computeIfPresent(StructureFeature.DESERT_PYRAMID, (structureFeature, structureConfig) -> {
                    return new StructureConfig(10, 8, 14357617);
                });
                map.computeIfPresent(StructureFeature.SHIPWRECK, (structureFeature, structureConfig) -> {
                    return new StructureConfig(10, 8, 165745295);
                });
                map.computeIfPresent(StructureFeature.FORTRESS, (structureFeature, structureConfig) -> {
                    return new StructureConfig(8, 7, 30084232);
                });
                map.computeIfPresent(StructureFeature.BASTION_REMNANT, (structureFeature, structureConfig) -> {
                    return new StructureConfig(9, 8, 30084232);
                });

                ImmutableMap<StructureFeature<?>, StructureConfig> immutableMap = ImmutableMap.copyOf(map);

                ((StructuresConfigAccessor) world.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(immutableMap);
            });
        }
    }
}
