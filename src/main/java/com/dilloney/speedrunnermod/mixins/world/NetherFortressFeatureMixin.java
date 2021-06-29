package com.dilloney.speedrunnermod.mixins.world;

import net.minecraft.entity.EntityType;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.NetherFortressFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetherFortressFeature.class)
public class NetherFortressFeatureMixin {

    @Shadow @Final static Pool<SpawnSettings.SpawnEntry> MONSTER_SPAWNS;

    static {
        MONSTER_SPAWNS = Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4), new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 5, 1, 3), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 3), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 3));
    }
}