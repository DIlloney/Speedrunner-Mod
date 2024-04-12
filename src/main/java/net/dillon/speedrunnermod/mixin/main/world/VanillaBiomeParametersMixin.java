package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VanillaBiomeParameters.class)
public class VanillaBiomeParametersMixin {
    @Shadow @Final @Mutable
    private RegistryKey<Biome>[][] commonBiomes;

    /**
     * Changes biome generation, according to {@code Better Biomes} and {@code Custom Biomes}.
     * <p>Also allows the {@code Speedrunner's Wasteland} biome to generate, since there isn't really any other way to do it in <b>1.18.1.</b></p>
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (SpeedrunnerMod.options().main.betterBiomes && SpeedrunnerMod.options().main.customBiomes) {
            this.commonBiomes = new RegistryKey[][]{
                    {ModBiomes.SPEEDRUNNERS_WASTELAND_KEY,
                            ModBiomes.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.PLAINS,
                            BiomeKeys.DESERT,
                            BiomeKeys.SAVANNA},
                    {BiomeKeys.PLAINS,
                            ModBiomes.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.FOREST,
                            BiomeKeys.TAIGA,
                            BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
                    {BiomeKeys.FLOWER_FOREST,
                            ModBiomes.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.FOREST,
                            BiomeKeys.DESERT,
                            BiomeKeys.DARK_FOREST},
                    {BiomeKeys.SAVANNA,
                            BiomeKeys.SAVANNA,
                            BiomeKeys.FOREST,
                            BiomeKeys.JUNGLE,
                            BiomeKeys.PLAINS},
                    {BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            ModBiomes.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT}};
        } else if (SpeedrunnerMod.options().main.betterBiomes) {
            this.commonBiomes = new RegistryKey[][]{
                    {BiomeKeys.PLAINS,
                            BiomeKeys.PLAINS,
                            BiomeKeys.PLAINS,
                            BiomeKeys.DESERT,
                            BiomeKeys.SAVANNA},
                    {BiomeKeys.PLAINS,
                            BiomeKeys.PLAINS,
                            BiomeKeys.FOREST,
                            BiomeKeys.TAIGA,
                            BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
                    {BiomeKeys.FLOWER_FOREST,
                            BiomeKeys.PLAINS,
                            BiomeKeys.FOREST,
                            BiomeKeys.DESERT,
                            BiomeKeys.DARK_FOREST},
                    {BiomeKeys.SAVANNA,
                            BiomeKeys.SAVANNA,
                            BiomeKeys.FOREST,
                            BiomeKeys.JUNGLE,
                            BiomeKeys.PLAINS},
                    {BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT}};
        } else if (SpeedrunnerMod.options().main.customBiomes) {
            this.commonBiomes = new RegistryKey[][]{
                    {ModBiomes.SPEEDRUNNERS_WASTELAND_KEY,
                            ModBiomes.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.SNOWY_PLAINS,
                            BiomeKeys.SNOWY_TAIGA,
                            BiomeKeys.TAIGA},
                    {BiomeKeys.PLAINS,
                            ModBiomes.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.FOREST,
                            BiomeKeys.TAIGA,
                            BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
                    {BiomeKeys.FLOWER_FOREST,
                            BiomeKeys.PLAINS,
                            BiomeKeys.FOREST,
                            BiomeKeys.BIRCH_FOREST,
                            BiomeKeys.DARK_FOREST},
                    {BiomeKeys.SAVANNA,
                            BiomeKeys.SAVANNA,
                            BiomeKeys.FOREST,
                            BiomeKeys.JUNGLE,
                            BiomeKeys.JUNGLE},
                    {BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT}};
        } else {
            this.commonBiomes = new RegistryKey[][]{
                    {BiomeKeys.SNOWY_PLAINS,
                            BiomeKeys.SNOWY_PLAINS,
                            BiomeKeys.SNOWY_PLAINS,
                            BiomeKeys.SNOWY_TAIGA,
                            BiomeKeys.TAIGA},
                    {BiomeKeys.PLAINS,
                            BiomeKeys.PLAINS,
                            BiomeKeys.FOREST,
                            BiomeKeys.TAIGA,
                            BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
                    {BiomeKeys.FLOWER_FOREST,
                            BiomeKeys.PLAINS,
                            BiomeKeys.FOREST,
                            BiomeKeys.BIRCH_FOREST,
                            BiomeKeys.DARK_FOREST},
                    {BiomeKeys.SAVANNA,
                            BiomeKeys.SAVANNA,
                            BiomeKeys.FOREST,
                            BiomeKeys.JUNGLE,
                            BiomeKeys.JUNGLE},
                    {BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT}};
        }
    }
}