package com.dilloney.speedrunnermod.mixins.misc;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;

@Mixin(StructuresConfig.class)
public interface StructuresConfigAccessor {
    @Mutable @Accessor("structures")
    void setStructures(Map<StructureFeature<?>, StructureConfig> structures);
}