package net.dillon8775.speedrunnermod.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

/**
 * {@link net.dillon8775.speedrunnermod.SpeedrunnerMod} fuels (objects that can be used as a fuel source in a furnace).
 */
public class ModFuels {

    public static void init() {
        FuelRegistry registry = FuelRegistry.INSTANCE;
        registry.add(ModBlockItems.SPEEDRUNNER_LOG, 400);
        registry.add(ModBlockItems.STRIPPED_SPEEDRUNNER_LOG, 400);
        registry.add(ModBlockItems.SPEEDRUNNER_WOOD, 400);
        registry.add(ModBlockItems.STRIPPED_SPEEDRUNNER_WOOD, 400);
        registry.add(ModBlockItems.SPEEDRUNNER_SAPLING, 200);
        registry.add(ModBlockItems.SPEEDRUNNER_PLANKS, 400);
        registry.add(ModItems.SPEEDRUNNER_STICK, 200);
        registry.add(ModItems.SPEEDRUNNER_BOAT, 300);
        registry.add(ModBlockItems.SPEEDRUNNER_SLAB, 300);
        registry.add(ModBlockItems.SPEEDRUNNER_STAIRS, 400);
        registry.add(ModBlockItems.WOODEN_SPEEDRUNNER_TRAPDOOR, 400);
        registry.add(ModBlockItems.SPEEDRUNNER_PRESSURE_PLATE, 400);
        registry.add(ModBlockItems.SPEEDRUNNER_FENCE, 400);
        registry.add(ModBlockItems.SPEEDRUNNER_FENCE_GATE, 400);
        registry.add(ModBlockItems.WOODEN_SPEEDRUNNER_DOOR, 300);
        registry.add(ModBlockItems.SPEEDRUNNER_BUTTON, 200);
        registry.add(ModBlockItems.DEAD_SPEEDRUNNER_BUSH, 200);

        info("Initialized fuels.");
    }
}