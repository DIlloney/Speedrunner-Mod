package com.dilloney.speedrunnermod.mixins.world;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.RuinedPortalFeature;
import net.minecraft.world.gen.feature.RuinedPortalFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Mixin(RuinedPortalFeature.Start.class)
public abstract class RuinedPortalFeatureMixin extends StructureStart<RuinedPortalFeatureConfig> {

    public RuinedPortalFeatureMixin(StructureFeature<RuinedPortalFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
        super(feature, chunkX, chunkZ, box, references, seed);
    }

    @Overwrite
    public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, RuinedPortalFeatureConfig ruinedPortalFeatureConfig) {
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
        BlockPos blockPos2 = (new ChunkPos(i, j)).getStartPos();
        BlockBox blockBox = structure.method_27267(blockPos2, blockRotation, blockPos, blockMirror);
        Vec3i vec3i = blockBox.getCenter();
        int k = vec3i.getX();
        int l = vec3i.getZ();
        int m = chunkGenerator.getHeight(k, l, RuinedPortalStructurePiece.getHeightmapType(verticalPlacement6)) - 1;
        int n = method_27211(this.random, chunkGenerator, verticalPlacement6, properties.airPocket, m, blockBox.getBlockCountY(), blockBox);
        BlockPos blockPos3 = new BlockPos(blockPos2.getX(), n, blockPos2.getZ());
        if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.MOUNTAIN || ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.OCEAN || ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.STANDARD) {
            properties.cold = method_27209(blockPos3, biome);
        }

        this.children.add(new RuinedPortalStructurePiece(blockPos3, verticalPlacement6, properties, identifier2, structure, blockRotation, blockMirror, blockPos));
        this.setBoundingBoxFromChildren();
    }

    private static int method_27211(Random random, ChunkGenerator chunkGenerator, RuinedPortalStructurePiece.VerticalPlacement verticalPlacement, boolean bl, int i, int j, BlockBox blockBox) {
        int s;
        if (verticalPlacement == RuinedPortalStructurePiece.VerticalPlacement.IN_NETHER) {
            if (bl) {
                s = choose(random, 32, 100);
            } else if (random.nextFloat() < 0.5F) {
                s = choose(random, 27, 29);
            } else {
                s = choose(random, 29, 100);
            }
        } else {
            int p;
            if (verticalPlacement == RuinedPortalStructurePiece.VerticalPlacement.IN_MOUNTAIN) {
                p = i - j;
                s = choosePlacementHeight(random, 70, p);
            } else if (verticalPlacement == RuinedPortalStructurePiece.VerticalPlacement.UNDERGROUND) {
                p = i - j;
                s = choosePlacementHeight(random, 15, p);
            } else if (verticalPlacement == RuinedPortalStructurePiece.VerticalPlacement.PARTLY_BURIED) {
                s = i - j + choose(random, 2, 8);
            } else {
                s = i;
            }
        }

        List<BlockPos> list = ImmutableList.of(new BlockPos(blockBox.minX, 0, blockBox.minZ), new BlockPos(blockBox.maxX, 0, blockBox.minZ), new BlockPos(blockBox.minX, 0, blockBox.maxZ), new BlockPos(blockBox.maxX, 0, blockBox.maxZ));
        List<BlockView> list2 = (List)list.stream().map((blockPos) -> {
            return chunkGenerator.getColumnSample(blockPos.getX(), blockPos.getZ());
        }).collect(Collectors.toList());
        net.minecraft.world.Heightmap.Type type = verticalPlacement == RuinedPortalStructurePiece.VerticalPlacement.ON_OCEAN_FLOOR ? net.minecraft.world.Heightmap.Type.OCEAN_FLOOR_WG : net.minecraft.world.Heightmap.Type.WORLD_SURFACE_WG;
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        int t;
        for(t = s; t > 15; --t) {
            int u = 0;
            mutable.set(0, t, 0);
            Iterator var14 = list2.iterator();

            while(var14.hasNext()) {
                BlockView blockView = (BlockView)var14.next();
                BlockState blockState = blockView.getBlockState(mutable);
                if (blockState != null && type.getBlockPredicate().test(blockState)) {
                    ++u;
                    if (u == 3) {
                        return t;
                    }
                }
            }
        }

        return t;
    }

    private static boolean method_27209(BlockPos blockPos, Biome biome) {
        return biome.getTemperature(blockPos) < 0.15F;
    }

    private static int choose(Random random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private static int choosePlacementHeight(Random random, int min, int max) {
        return min < max ? choose(random, min, max) : max;
    }
}
