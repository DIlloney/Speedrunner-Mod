<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(TheEndBiomeCreator.class)
public class TheEndBiomeCreatorMixin {

    @Overwrite
    private static Biome createEndBiome(GenerationSettings.Builder builder) {
        SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addEndMobs(builder2);
        return DOOM_MODE ?
                new Biome.Builder().precipitation(Biome.Precipitation.NONE).temperature(0.5f).downfall(0.5f).effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(0xA080A0).skyColor(0).particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.030F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder2.build()).generationSettings(builder.build()).build() :
                new Biome.Builder().precipitation(Biome.Precipitation.NONE).temperature(0.5f).downfall(0.5f).effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(0xA080A0).skyColor(0).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder2.build()).generationSettings(builder.build()).build();
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(TheEndBiomeCreator.class)
public class TheEndBiomeCreatorMixin {

    @Overwrite
    private static Biome createEndBiome(GenerationSettings.Builder builder) {
        SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addEndMobs(builder2);
        return DOOM_MODE ?
                new Biome.Builder().precipitation(Biome.Precipitation.NONE).temperature(0.5f).downfall(0.5f).effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(0xA080A0).skyColor(0).particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.030F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder2.build()).generationSettings(builder.build()).build() :
                new Biome.Builder().precipitation(Biome.Precipitation.NONE).temperature(0.5f).downfall(0.5f).effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(0xA080A0).skyColor(0).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder2.build()).generationSettings(builder.build()).build();
    }
>>>>>>> Stashed changes
}