package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.dillon.speedrunnermod.world.feature.ModConfiguredFeatures;
import net.dillon.speedrunnermod.world.feature.ModPlacedFeatures;
import net.dillon.speedrunnermod.world.feature.WastelandConfiguredFeatures;
import net.dillon.speedrunnermod.world.feature.WastelandPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

/**
 * See {@link net.dillon.speedrunnermod.world} for more.
 */
public class SpeedrunnerModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        SpeedrunnerMod.info("Initializing speedrunner mod data generator!");

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModBlockTagGenerator::new);
        pack.addProvider(ModEnchantmentGenerator::new);
        pack.addProvider(ModEnchantmentTagGenerator::new);
        pack.addProvider(ModItemTagGenerator::new);
        pack.addProvider(ModFluidTagGenerator::new);
        pack.addProvider(ModBlockLootTableGenerator::new);
        pack.addProvider(ModWorldGenerator::new);
        pack.addProvider(ModModelGenerator::new);

        SpeedrunnerMod.info("Finished running through data generator.");
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, WastelandConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, WastelandPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);
    }
}