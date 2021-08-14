package com.dilloney.speedrunnermod.mixins.world.gen.feature;

import net.minecraft.entity.EntityType;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.NetherFortressFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(NetherFortressFeature.class)
public class NetherFortressFeatureMixin {

    @Shadow final static Pool<SpawnSettings.SpawnEntry> MONSTER_SPAWNS;

    static {
        if (OPTIONS.doomMode) {
            MONSTER_SPAWNS = Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 50, 1, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 75, 4, 12), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 20, 1, 4), new SpawnSettings.SpawnEntry(EntityType.SILVERFISH, 25, 1, 2));
        } else if (OPTIONS.getModDifficulty() == 1 || OPTIONS.getModDifficulty() == 2) {
            MONSTER_SPAWNS = Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4), new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 3), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 3), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 3));
        } else if (OPTIONS.getModDifficulty() == 3 || OPTIONS.getModDifficulty() == 4) {
            MONSTER_SPAWNS = Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 10, 1, 3), new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 5, 1, 2), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 8, 1, 2), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 3), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 5, 1, 4), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 3, 1, 4));
        } else {
            MONSTER_SPAWNS = Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4), new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 3), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 3), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 3));
        }
    }
}