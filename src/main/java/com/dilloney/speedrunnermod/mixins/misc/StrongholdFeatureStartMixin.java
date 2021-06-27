package com.dilloney.speedrunnermod.mixins.misc;

import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StrongholdFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(StrongholdFeature.Start.class)
public abstract class StrongholdFeatureStartMixin extends MarginedStructureStart<DefaultFeatureConfig> {

    @Shadow @Final long seed;

    public StrongholdFeatureStartMixin(StructureFeature<DefaultFeatureConfig> structureFeature, ChunkPos chunkPos, int i, long l) {
        super(structureFeature, chunkPos, i, l);
    }

    @Overwrite
    public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
        int var8 = 0;

        net.minecraft.structure.StrongholdGenerator.Start start;
        do {
            this.clearChildren();
            this.random.setCarverSeed(this.seed + (long)(var8++), chunkPos.x, chunkPos.z);
            StrongholdGenerator.init();
            start = new net.minecraft.structure.StrongholdGenerator.Start(this.random, chunkPos.getOffsetX(2), chunkPos.getOffsetZ(2));
            this.addPiece(start);
            start.fillOpenings(start, this, this.random);
            List list = start.pieces;

            while(!list.isEmpty()) {
                int j = this.random.nextInt(list.size());
                StructurePiece structurePiece = (StructurePiece)list.remove(j);
                structurePiece.fillOpenings(start, this, this.random);
            }

            this.randomUpwardTranslation(this.random, 32, 64);
        } while(this.hasNoChildren() || start.portalRoom == null);

    }
}
