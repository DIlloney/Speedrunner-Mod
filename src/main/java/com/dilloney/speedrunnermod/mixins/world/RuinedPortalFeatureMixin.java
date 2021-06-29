package com.dilloney.speedrunnermod.mixins.world;

import net.minecraft.structure.RuinedPortalStructurePiece;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.RuinedPortalFeature;
import net.minecraft.world.gen.feature.RuinedPortalFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RuinedPortalFeature.Start.class)
public class RuinedPortalFeatureMixin extends StructureStart<RuinedPortalFeatureConfig> {

    public RuinedPortalFeatureMixin(StructureFeature<RuinedPortalFeatureConfig> feature, ChunkPos pos, int references, long seed) {
        super(feature, pos, references, seed);
    }

    @Overwrite
    public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, RuinedPortalFeatureConfig ruinedPortalFeatureConfig, HeightLimitView heightLimitView) {
        RuinedPortalStructurePiece.Properties properties = new RuinedPortalStructurePiece.Properties();
        RuinedPortalStructurePiece.VerticalPlacement verticalPlacement6;
        if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.DESERT) {
            verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
            properties.airPocket = false;
            properties.mossiness = 0.0F;
        } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.JUNGLE) {
            verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
            properties.airPocket = this.random.nextFloat() < 0.5F;
            properties.mossiness = 0.8F;
            properties.overgrown = true;
            properties.vines = true;
        } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.SWAMP) {
            verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_OCEAN_FLOOR;
            properties.airPocket = false;
            properties.mossiness = 0.5F;
            properties.vines = true;
        } else {
            boolean bl2;
            if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.MOUNTAIN) {
                bl2 = this.random.nextFloat() < 0.5F;
                verticalPlacement6 = bl2 ? RuinedPortalStructurePiece.VerticalPlacement.IN_MOUNTAIN : RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
                properties.airPocket = bl2 || this.random.nextFloat() < 0.5F;
            } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.OCEAN) {
                verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_OCEAN_FLOOR;
                properties.airPocket = false;
                properties.mossiness = 0.8F;
            } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.NETHER) {
                verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.IN_NETHER;
                properties.airPocket = this.random.nextFloat() < 0.5F;
                properties.mossiness = 0.0F;
                properties.replaceWithBlackstone = true;
            } else {
                bl2 = this.random.nextFloat() < 0.5F;
                verticalPlacement6 = bl2 ? RuinedPortalStructurePiece.VerticalPlacement.UNDERGROUND : RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
                properties.airPocket = bl2 || this.random.nextFloat() < 0.5F;
            }
        }

        Identifier identifier2;
        if (this.random.nextFloat() < 0.05F) {
            identifier2 = new Identifier(RuinedPortalFeature.RARE_PORTAL_STRUCTURE_IDS[this.random.nextInt(RuinedPortalFeature.RARE_PORTAL_STRUCTURE_IDS.length)]);
        } else {
            identifier2 = new Identifier(RuinedPortalFeature.COMMON_PORTAL_STRUCTURE_IDS[this.random.nextInt(RuinedPortalFeature.COMMON_PORTAL_STRUCTURE_IDS.length)]);
        }

        Structure structure = structureManager.getStructureOrBlank(identifier2);
        BlockRotation blockRotation = (BlockRotation) Util.getRandom(BlockRotation.values(), this.random);
        BlockMirror blockMirror = this.random.nextFloat() < 0.5F ? BlockMirror.NONE : BlockMirror.FRONT_BACK;
        BlockPos blockPos = new BlockPos(structure.getSize().getX() / 2, 0, structure.getSize().getZ() / 2);
        BlockPos blockPos2 = chunkPos.getStartPos();
        BlockBox blockBox = structure.calculateBoundingBox(blockPos2, blockRotation, blockPos, blockMirror);
        BlockPos blockPos3 = blockBox.getCenter();
        int i = blockPos3.getX();
        int j = blockPos3.getZ();
        int k = chunkGenerator.getHeight(i, j, RuinedPortalStructurePiece.getHeightmapType(verticalPlacement6), heightLimitView) - 1;
        int l = RuinedPortalFeature.getFloorHeight(this.random, chunkGenerator, verticalPlacement6, properties.airPocket, k, blockBox.getBlockCountY(), blockBox, heightLimitView);
        BlockPos blockPos4 = new BlockPos(blockPos2.getX(), l, blockPos2.getZ());
        if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.MOUNTAIN || ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.OCEAN || ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.STANDARD) {
            properties.cold = RuinedPortalFeature.isColdAt(blockPos4, biome);
        }

        this.addPiece(new RuinedPortalStructurePiece(structureManager, blockPos4, verticalPlacement6, properties, identifier2, structure, blockRotation, blockMirror, blockPos));
    }
}
