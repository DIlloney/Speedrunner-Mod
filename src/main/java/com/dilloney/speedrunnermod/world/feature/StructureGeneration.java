package com.dilloney.speedrunnermod.world.feature;

import com.dilloney.speedrunnermod.mixins.StructuresConfigAccessor;
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
            map.computeIfPresent(StructureFeature.IGLOO, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getIglooSpacing(), OPTIONS.getIglooSeparation(), 14357618);
            });
            map.computeIfPresent(StructureFeature.JUNGLE_PYRAMID, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getJunglePyramidSpacing(), OPTIONS.getJunglePyramidSeparation(), 14357619);
            });
            map.computeIfPresent(StructureFeature.SWAMP_HUT, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getSwampHutSpacing(), OPTIONS.getSwampHutSeparation(), 14357620);
            });
            map.computeIfPresent(StructureFeature.PILLAGER_OUTPOST, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getPillagerOutpostSpacing(), OPTIONS.getPillagerOutpostSeparation(), 165745296);
            });
            map.computeIfPresent(StructureFeature.MONUMENT, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getMonumentSpacing(), OPTIONS.getMonumentSeparation(), 10387313);
            });
            map.computeIfPresent(StructureFeature.SHIPWRECK, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getShipwreckSpacing(), OPTIONS.getShipwreckSeparation(), 165745295);
            });
            map.computeIfPresent(StructureFeature.OCEAN_RUIN, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getOceanRuinSpacing(), OPTIONS.getOceanRuinSeparation(), 14357621);
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
            map.computeIfPresent(StructureFeature.MANSION, (structureFeature, structureConfig) -> {
                return new StructureConfig(OPTIONS.getMansionSpacing(), OPTIONS.getMansionSeparation(), 10387319);
            });

            ImmutableMap<StructureFeature<?>, StructureConfig> immutableMap = ImmutableMap.copyOf(map);

            ((StructuresConfigAccessor)world.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(immutableMap);
        });
    }
}