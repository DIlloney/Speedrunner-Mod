package com.dilloney.speedrunnermod.world.feature;

import com.dilloney.speedrunnermod.mixins.misc.StructuresConfigAccessor;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.HashMap;
import java.util.Map;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

public final class StructureGeneration {

    public static void registerModifiedStructureGeneration() {
        ServerWorldEvents.LOAD.register((server, world) -> {
            Map<StructureFeature<?>, StructureConfig> map = new HashMap<>(world.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());

            map.computeIfPresent(StructureFeature.RUINED_PORTAL, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getRuinedPortalSpacing(), OPTIONS.getRuinedPortalSeparation(), 34222645);
            });
            map.computeIfPresent(StructureFeature.VILLAGE, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getVillageSpacing(), OPTIONS.getVillageSeparation(), 10387312);
            });
            map.computeIfPresent(StructureFeature.DESERT_PYRAMID, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getDesertPyramidSpacing(), OPTIONS.getDesertPyramidSeparation(), 14357617);
            });
            map.computeIfPresent(StructureFeature.SHIPWRECK, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getShipwreckSpacing(), OPTIONS.getShipwreckSeparation(), 165745295);
            });
            map.computeIfPresent(StructureFeature.FORTRESS, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getFortressSpacing(), OPTIONS.getFortressSeparation(), 30084232);
            });
            map.computeIfPresent(StructureFeature.BASTION_REMNANT, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getBastionSpacing(), OPTIONS.getBastionSeparation(), 30084232);
            });
            map.computeIfPresent(StructureFeature.END_CITY, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getEndCitySpacing(), OPTIONS.getEndCitySeparation(), 30084232);
            });

            ImmutableMap<StructureFeature<?>, StructureConfig> immutableMap = ImmutableMap.copyOf(map);

            ((StructuresConfigAccessor) world.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(immutableMap);
        });
    }
}
