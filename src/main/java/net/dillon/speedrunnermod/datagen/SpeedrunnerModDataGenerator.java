package net.dillon.speedrunnermod.datagen;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * See {@link net.dillon.speedrunnermod.world} for more.
 */
public class SpeedrunnerModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        SpeedrunnerMod.info("Initializing speedrunner mod data generator!");

        fabricDataGenerator.addProvider(ModItemTagGenerator::new);
        fabricDataGenerator.addProvider(ModBlockTagGenerator::new);
        fabricDataGenerator.addProvider(ModFluidTagGenerator::new);
        fabricDataGenerator.addProvider(ModBlockLootTableGenerator::new);
        fabricDataGenerator.addProvider(ModModelGenerator::new);

        SpeedrunnerMod.info("Finished running through data generator.");
    }
}