package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.world.ModWorldGen;
import net.dillon8775.speedrunnermod.world.feature.ModPlacedFeatures;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

    /**
     * Allows the {@code Speedrunner Mod overworld ores} to generate.
     */
    @Inject(method = "addDefaultOres(Lnet/minecraft/world/biome/GenerationSettings$Builder;Z)V", at = @At("TAIL"))
    private static void addModdedOres(GenerationSettings.Builder builder, boolean largeCopperOreBlob, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_MIDDLE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SPEEDRUNNER_SMALL);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_IGNEOUS);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_EXPERIENCE);
    }

    /**
     * Allows the {@code Speedrunner Mod nether ores} to generate.
     */
    @Inject(method = "addNetherMineables", at = @At("TAIL"))
    private static void addNetherModdedOres(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModPlacedFeatures.ORE_SPEEDRUNNER_NETHER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModPlacedFeatures.ORE_IGNEOUS_NETHER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModPlacedFeatures.ORE_EXPERIENCE_NETHER);
    }

    /**
     * Changes mob spawning, according to {@code Doom Mode}.
     */
    @Overwrite
    public static void addMonsters(net.minecraft.world.biome.SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, boolean drowned) {
        ModWorldGen.modifyMonsterSpawns(builder, zombieWeight, zombieVillagerWeight, skeletonWeight, drowned);
    }

    /**
     * Makes animals spawn more commonly, according to the {@code Mob Spawning Rate} option.
     */
    @Overwrite
    public static void addFarmAnimals(net.minecraft.world.biome.SpawnSettings.Builder builder) {
        ModWorldGen.makeAnimalsMoreCommon(builder);
    }

    /**
     * Makes dolphins spawn more commonly, according to the {@code Mob Spawning Rate} option.
     */
    @Overwrite
    public static void addWarmOceanMobs(net.minecraft.world.biome.SpawnSettings.Builder builder, int squidWeight, int squidMinGroupSize) {
        ModWorldGen.makeDolphinsMoreCommon(builder, squidWeight, squidMinGroupSize);
    }

    /**
     * Changes the end dimension mob spawning, according to {@code Doom Mode}.
     */
    @Overwrite
    public static void addEndMobs(net.minecraft.world.biome.SpawnSettings.Builder builder) {
        ModWorldGen.modifyEndMonsterSpawning(builder);
    }
}