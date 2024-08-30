package net.dillon.speedrunnermod.world.api;

import com.mojang.datafixers.util.Pair;
import net.dillon.speedrunnermod.world.biome.ModBiomeKeys;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

/**
 * The {@code region class} for the {@code Speedrunner's Wasteland biome.}
 */
public class ModOverworldRegion extends Region {

    public ModOverworldRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    /**
     * Replaces certain instances of {@code plains biomes} with the {@code Speedrunner's Wasteland biome}, making the biome appear in the world.
     */
    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
            modifiedVanillaOverworldBuilder.replaceBiome(BiomeKeys.PLAINS, ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY);
        });
    }
}