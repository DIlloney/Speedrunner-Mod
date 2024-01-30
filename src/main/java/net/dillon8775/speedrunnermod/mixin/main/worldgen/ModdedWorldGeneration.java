package net.dillon8775.speedrunnermod.mixin.main.worldgen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.world.biome.ModBiomes;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Mixin(ConfiguredStructureFeatures.class)
public class ModdedWorldGeneration {
    @Shadow @Final
    private static ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> VILLAGE_PLAINS, VILLAGE_TAIGA;
    @Shadow @Final
    private static ConfiguredStructureFeature<MineshaftFeatureConfig, ? extends StructureFeature<MineshaftFeatureConfig>> MINESHAFT;
    @Shadow @Final
    private static ConfiguredStructureFeature<RuinedPortalFeatureConfig, ? extends StructureFeature<RuinedPortalFeatureConfig>> RUINED_PORTAL, RUINED_PORTAL_DESERT, RUINED_PORTAL_MOUNTAIN;
    @Shadow @Final
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> STRONGHOLD;

    /**
     * Allows default structures to generate in the {@code Speedrunner's Wasteland biome}, and changes default biome generation according to {@code Custom Biomes}.
     */
    @Inject(method = "registerAll", at = @At("TAIL"))
    private static void registerAll(BiConsumer<ConfiguredStructureFeature<?, ?>, RegistryKey<Biome>> registrar, CallbackInfo ci) {
        ConfiguredStructureFeatures.register(registrar, VILLAGE_TAIGA, ModBiomes.SPEEDRUNNERS_WASTELAND_KEY);
        ConfiguredStructureFeatures.register(registrar, MINESHAFT, ModBiomes.SPEEDRUNNERS_WASTELAND_KEY);
        ConfiguredStructureFeatures.register(registrar, RUINED_PORTAL, ModBiomes.SPEEDRUNNERS_WASTELAND_KEY);
        ConfiguredStructureFeatures.register(registrar, RUINED_PORTAL_DESERT, ModBiomes.SPEEDRUNNERS_WASTELAND_KEY);
        ConfiguredStructureFeatures.register(registrar, RUINED_PORTAL_MOUNTAIN, ModBiomes.SPEEDRUNNERS_WASTELAND_KEY);
        if (SpeedrunnerMod.options().advanced.customBiomes) {
            ConfiguredStructureFeatures.register(registrar, VILLAGE_PLAINS, BiomeKeys.SUNFLOWER_PLAINS);
            ConfiguredStructureFeatures.register(registrar, VILLAGE_PLAINS, BiomeKeys.FOREST);
            ConfiguredStructureFeatures.register(registrar, VILLAGE_PLAINS, BiomeKeys.FLOWER_FOREST);
            ConfiguredStructureFeatures.register(registrar, VILLAGE_TAIGA, BiomeKeys.WINDSWEPT_HILLS);
        }
    }
}