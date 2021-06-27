package com.dilloney.speedrunnermod.mixins.misc;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.NetherFortressFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(NetherFortressFeature.class)
public class NetherFortressFeatureMixin {

    @Shadow @Final static List<SpawnSettings.SpawnEntry> MONSTER_SPAWNS;

    static {
        MONSTER_SPAWNS = ImmutableList.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 25, 3, 5), new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 5, 1, 2), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 5, 1, 3), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 3), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 3));
    }
}
