package net.dillon8775.speedrunnermod.world.gen;

import com.google.common.collect.ImmutableMap;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.mixin.structure.StructuresConfigAccessor;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.HashMap;
import java.util.Map;

public class StructureSpawnRates {
    private static int VILLAGE_SPACING;
    private static int VILLAGE_SEPARATION;
    private static int DESERT_PYRAMID_SPACING;
    private static int DESERT_PYRAMID_SEPARATION;
    private static int JUNGLE_PYRAMID_SPACING;
    private static int JUNGLE_PYRAMID_SEPARATION;
    private static int END_CITY_SPACING;
    private static int END_CITY_SEPARATION;
    private static int MANSION_SPACING;
    private static int MANSION_SEPARATION;
    private static int RUINED_PORTAL_SPACING;
    private static int RUINED_PORTAL_SEPARATION;
    private static int SHIPWRECK_SPACING;
    private static int SHIPWRECK_SEPARATION;
    private static int BASTION_SPACING;
    private static int BASTION_SEPARATION;
    private static int FORTRESS_SPACING;
    private static int FORTRESS_SEPARATION;

    /**
     * <p> Credit goes to {@code Frqnny}. </p>
     */
    public static void init() {
        ServerWorldEvents.LOAD.register((server, world) -> {
            Map<StructureFeature<?>, StructureConfig> map = new HashMap<>(world.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());

            map.computeIfPresent(StructureFeature.VILLAGE, (structureFeature, structureConfig) -> {
                return new StructureConfig(VILLAGE_SPACING, VILLAGE_SEPARATION, 10387312);
            });
            map.computeIfPresent(StructureFeature.DESERT_PYRAMID, (structureFeature, structureConfig) -> {
                return new StructureConfig(DESERT_PYRAMID_SPACING, DESERT_PYRAMID_SEPARATION, 14357617);
            });
            map.computeIfPresent(StructureFeature.JUNGLE_PYRAMID, (structureFeature, structureConfig) -> {
                return new StructureConfig(JUNGLE_PYRAMID_SPACING, JUNGLE_PYRAMID_SEPARATION, 14357619);
            });
            map.computeIfPresent(StructureFeature.END_CITY, (structureFeature, structureConfig) -> {
                return new StructureConfig(END_CITY_SPACING, END_CITY_SEPARATION, 10387313);
            });
            map.computeIfPresent(StructureFeature.MANSION, (structureFeature, structureConfig) -> {
                return new StructureConfig(MANSION_SPACING, MANSION_SEPARATION, 10387319);
            });
            map.computeIfPresent(StructureFeature.RUINED_PORTAL, (structureFeature, structureConfig) -> {
                return new StructureConfig(RUINED_PORTAL_SPACING, RUINED_PORTAL_SEPARATION, 34222645);
            });
            map.computeIfPresent(StructureFeature.SHIPWRECK, (structureFeature, structureConfig) -> {
                return new StructureConfig(SHIPWRECK_SPACING, SHIPWRECK_SEPARATION, 165745295);
            });
            map.computeIfPresent(StructureFeature.BASTION_REMNANT, (structureFeature, structureConfig) -> {
                return new StructureConfig(BASTION_SPACING, BASTION_SEPARATION, 30084232);
            });
            map.computeIfPresent(StructureFeature.FORTRESS, (structureFeature, structureConfig) -> {
                return new StructureConfig(FORTRESS_SPACING, FORTRESS_SEPARATION, 30084232);
            });

            ImmutableMap<StructureFeature<?>, StructureConfig> immutableMap = ImmutableMap.copyOf(map);

            ((StructuresConfigAccessor)world.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(immutableMap);
        });
    }

    public static void setValues() {
        if (SpeedrunnerMod.options().main.structureSpawnRate == ModOptions.Main.StructureSpawnRate.VERY_COMMON) {
            VILLAGE_SPACING = 10;
            VILLAGE_SEPARATION = 8;
            DESERT_PYRAMID_SPACING = 8;
            DESERT_PYRAMID_SEPARATION = 7;
            JUNGLE_PYRAMID_SPACING = 8;
            JUNGLE_PYRAMID_SEPARATION = 7;
            END_CITY_SPACING = 5;
            END_CITY_SEPARATION = 4;
            MANSION_SPACING = 16;
            MANSION_SEPARATION = 8;
            RUINED_PORTAL_SPACING = 7;
            RUINED_PORTAL_SEPARATION = 6;
            SHIPWRECK_SPACING = 8;
            SHIPWRECK_SEPARATION = 7;
            BASTION_SPACING = 7;
            BASTION_SEPARATION = 6;
            FORTRESS_SPACING = 6;
            FORTRESS_SEPARATION = 5;
        } else if (SpeedrunnerMod.options().main.structureSpawnRate == ModOptions.Main.StructureSpawnRate.COMMON) {
            VILLAGE_SPACING = 16;
            VILLAGE_SEPARATION = 9;
            DESERT_PYRAMID_SPACING = 10;
            DESERT_PYRAMID_SEPARATION = 8;
            JUNGLE_PYRAMID_SPACING = 10;
            JUNGLE_PYRAMID_SEPARATION = 8;
            END_CITY_SPACING = 7;
            END_CITY_SEPARATION = 6;
            MANSION_SPACING = 25;
            MANSION_SEPARATION = 20;
            RUINED_PORTAL_SPACING = 9;
            RUINED_PORTAL_SEPARATION = 8;
            SHIPWRECK_SPACING = 10;
            SHIPWRECK_SEPARATION = 8;
            BASTION_SPACING = 9;
            BASTION_SEPARATION = 8;
            FORTRESS_SPACING = 8;
            FORTRESS_SEPARATION = 7;
        } else if (SpeedrunnerMod.options().main.structureSpawnRate == ModOptions.Main.StructureSpawnRate.NORMAL) {
            VILLAGE_SPACING = 20;
            VILLAGE_SEPARATION = 8;
            DESERT_PYRAMID_SPACING = 20;
            DESERT_PYRAMID_SEPARATION = 8;
            JUNGLE_PYRAMID_SPACING = 20;
            JUNGLE_PYRAMID_SEPARATION = 8;
            END_CITY_SPACING = 15;
            END_CITY_SEPARATION = 10;
            MANSION_SPACING = 40;
            MANSION_SEPARATION = 20;
            RUINED_PORTAL_SPACING = 16;
            RUINED_PORTAL_SEPARATION = 9;
            SHIPWRECK_SPACING = 20;
            SHIPWRECK_SEPARATION = 8;
            BASTION_SPACING = 16;
            BASTION_SEPARATION = 9;
            FORTRESS_SPACING = 15;
            FORTRESS_SEPARATION = 8;
        } else if (SpeedrunnerMod.options().main.structureSpawnRate == ModOptions.Main.StructureSpawnRate.UNCOMMON) {
            VILLAGE_SPACING = 34;
            VILLAGE_SEPARATION = 8;
            DESERT_PYRAMID_SPACING = 32;
            DESERT_PYRAMID_SEPARATION = 8;
            JUNGLE_PYRAMID_SPACING = 32;
            JUNGLE_PYRAMID_SEPARATION = 8;
            END_CITY_SPACING = 20;
            END_CITY_SEPARATION = 11;
            MANSION_SPACING = 80;
            MANSION_SEPARATION = 20;
            RUINED_PORTAL_SPACING = 40;
            RUINED_PORTAL_SEPARATION = 15;
            SHIPWRECK_SPACING = 24;
            SHIPWRECK_SEPARATION = 4;
            BASTION_SPACING = 27;
            BASTION_SEPARATION = 4;
            FORTRESS_SPACING = 27;
            FORTRESS_SEPARATION = 4;
        } else if (SpeedrunnerMod.options().main.structureSpawnRate == ModOptions.Main.StructureSpawnRate.RARE) {
            VILLAGE_SPACING = 42;
            VILLAGE_SEPARATION = 10;
            DESERT_PYRAMID_SPACING = 40;
            DESERT_PYRAMID_SEPARATION = 10;
            JUNGLE_PYRAMID_SPACING = 40;
            JUNGLE_PYRAMID_SEPARATION = 10;
            END_CITY_SPACING = 25;
            END_CITY_SEPARATION = 16;
            MANSION_SPACING = 100;
            MANSION_SEPARATION = 20;
            RUINED_PORTAL_SPACING = 50;
            RUINED_PORTAL_SEPARATION = 16;
            SHIPWRECK_SPACING = 30;
            SHIPWRECK_SEPARATION = 9;
            BASTION_SPACING = 35;
            BASTION_SEPARATION = 9;
            FORTRESS_SPACING = 34;
            FORTRESS_SEPARATION = 8;
        } else if (SpeedrunnerMod.options().main.structureSpawnRate == ModOptions.Main.StructureSpawnRate.VERY_RARE) {
            VILLAGE_SPACING = 52;
            VILLAGE_SEPARATION = 16;
            DESERT_PYRAMID_SPACING = 50;
            DESERT_PYRAMID_SEPARATION = 16;
            JUNGLE_PYRAMID_SPACING = 50;
            JUNGLE_PYRAMID_SEPARATION = 12;
            END_CITY_SPACING = 30;
            END_CITY_SEPARATION = 18;
            MANSION_SPACING = 120;
            MANSION_SEPARATION = 25;
            RUINED_PORTAL_SPACING = 60;
            RUINED_PORTAL_SEPARATION = 20;
            SHIPWRECK_SPACING = 40;
            SHIPWRECK_SEPARATION = 10;
            BASTION_SPACING = 40;
            BASTION_SEPARATION = 10;
            FORTRESS_SPACING = 40;
            FORTRESS_SEPARATION = 10;
        } else {
            SpeedrunnerMod.options().main.structureSpawnRate = ModOptions.Main.StructureSpawnRate.COMMON;
        }
    }
}