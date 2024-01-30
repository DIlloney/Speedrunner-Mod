package net.dillon8775.speedrunnermod.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModFuels {

    public static void init() {
        FuelRegistry registry = FuelRegistry.INSTANCE;
        registry.add(ModItems.SPEEDRUNNER_LOG, 400);
        registry.add(ModItems.STRIPPED_SPEEDRUNNER_LOG, 400);
        registry.add(ModItems.SPEEDRUNNER_WOOD, 400);
        registry.add(ModItems.STRIPPED_SPEEDRUNNER_WOOD, 400);
        registry.add(ModItems.SPEEDRUNNER_SAPLING, 200);
        registry.add(ModItems.SPEEDRUNNER_PLANKS, 400);
        registry.add(ModItems.SPEEDRUNNER_STICK, 200);
        registry.add(ModItems.SPEEDRUNNER_BOAT, 300);
        registry.add(ModItems.SPEEDRUNNER_SLAB, 300);
        registry.add(ModItems.SPEEDRUNNER_STAIRS, 400);
        registry.add(ModItems.SPEEDRUNNER_TRAPDOOR, 400);
        registry.add(ModItems.SPEEDRUNNER_PRESSURE_PLATE, 400);
        registry.add(ModItems.SPEEDRUNNER_FENCE, 400);
        registry.add(ModItems.SPEEDRUNNER_FENCE_GATE, 400);
        registry.add(ModItems.SPEEDRUNNER_DOOR, 300);
        registry.add(ModItems.SPEEDRUNNER_BUTTON, 200);
        registry.add(ModItems.DEAD_SPEEDRUNNER_BUSH, 200);
    }
}