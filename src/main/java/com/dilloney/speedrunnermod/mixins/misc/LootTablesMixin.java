package com.dilloney.speedrunnermod.mixins.misc;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.loot.LootTables;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(LootTables.class)
public class LootTablesMixin {

    @Shadow @Final static Set<Identifier> LOOT_TABLES;

    @Shadow @Final static Identifier SIMPLE_DUNGEON_CHEST;
    @Shadow @Final static Identifier ABANDONED_MINESHAFT_CHEST;
    @Shadow @Final static Identifier NETHER_BRIDGE_CHEST;
    @Shadow @Final static Identifier STRONGHOLD_CROSSING_CHEST;
    @Shadow @Final static Identifier STRONGHOLD_CORRIDOR_CHEST;
    @Shadow @Final static Identifier DESERT_PYRAMID_CHEST;
    @Shadow @Final static Identifier BURIED_TREASURE_CHEST;
    @Shadow @Final static Identifier SHIPWRECK_SUPPLY_CHEST;
    @Shadow @Final static Identifier SHIPWRECK_TREASURE_CHEST;
    @Shadow @Final static Identifier PIGLIN_BARTERING_GAMEPLAY;

    static {
        if (SpeedrunnerMod.CONFIG.modifiedLootTables) {
            SIMPLE_DUNGEON_CHEST = register("chests/simple_dungeon_modified");
            ABANDONED_MINESHAFT_CHEST = register("chests/abandoned_mineshaft_modified");
            NETHER_BRIDGE_CHEST = register("chests/nether_bridge_modified");
            STRONGHOLD_CROSSING_CHEST = register("chests/stronghold_crossing_modified");
            STRONGHOLD_CORRIDOR_CHEST = register("chests/stronghold_corridor_modified");
            DESERT_PYRAMID_CHEST = register("chests/desert_pyramid_modified");
            BURIED_TREASURE_CHEST = register("chests/buried_treasure_modified");
            SHIPWRECK_SUPPLY_CHEST = register("chests/shipwreck_supply_modified");
            SHIPWRECK_TREASURE_CHEST = register("chests/shipwreck_treasure_modified");
            PIGLIN_BARTERING_GAMEPLAY = register("gameplay/piglin_bartering_modified");
        } else {
            SIMPLE_DUNGEON_CHEST = register("chests/simple_dungeon_default");
            ABANDONED_MINESHAFT_CHEST = register("chests/abandoned_mineshaft_default");
            NETHER_BRIDGE_CHEST = register("chests/nether_bridge_default");
            STRONGHOLD_CROSSING_CHEST = register("chests/stronghold_crossing_default");
            STRONGHOLD_CORRIDOR_CHEST = register("chests/stronghold_corridor_default");
            DESERT_PYRAMID_CHEST = register("chests/desert_pyramid_default");
            BURIED_TREASURE_CHEST = register("chests/buried_treasure_default");
            SHIPWRECK_SUPPLY_CHEST = register("chests/shipwreck_supply_default");
            SHIPWRECK_TREASURE_CHEST = register("chests/shipwreck_treasure_default");
            PIGLIN_BARTERING_GAMEPLAY = register("gameplay/piglin_bartering_default");
        }
    }

    private static Identifier register(String id) {
        return registerLootTable(new Identifier(id));
    }

    private static Identifier registerLootTable(Identifier id) {
        if (LOOT_TABLES.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }
}