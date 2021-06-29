package com.dilloney.speedrunnermod.mixins.world;

import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.registry.DynamicRegistryManager;
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
public class StrongholdFeatureStartMixin extends StructureStart<DefaultFeatureConfig> {

    @Shadow @Final long seed;

    public StrongholdFeatureStartMixin(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
        super(feature, chunkX, chunkZ, box, references, seed);
    }

    @Overwrite
    public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
        int var8 = 0;

        net.minecraft.structure.StrongholdGenerator.Start start;
        do {
            this.children.clear();
            this.boundingBox = BlockBox.empty();
            this.random.setCarverSeed(this.seed + (long)(var8++), i, j);
            StrongholdGenerator.init();
            start = new net.minecraft.structure.StrongholdGenerator.Start(this.random, (i << 4) + 2, (j << 4) + 2);
            this.children.add(start);
            start.fillOpenings(start, this.children, this.random);
            List list = start.pieces;

            while(!list.isEmpty()) {
                int l = this.random.nextInt(list.size());
                StructurePiece structurePiece = (StructurePiece)list.remove(l);
                structurePiece.fillOpenings(start, this.children, this.random);
            }

            this.setBoundingBoxFromChildren();
            this.randomUpwardTranslation(this.random, 32, 63);
        } while(this.children.isEmpty() || start.portalRoom == null);
    }
}