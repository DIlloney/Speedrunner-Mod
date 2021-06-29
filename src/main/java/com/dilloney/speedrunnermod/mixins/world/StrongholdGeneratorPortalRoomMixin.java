package com.dilloney.speedrunnermod.mixins.world;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(StrongholdGenerator.PortalRoom.class)
public class StrongholdGeneratorPortalRoomMixin extends StrongholdGenerator.Piece {

    @Shadow boolean spawnerPlaced;

    protected StrongholdGeneratorPortalRoomMixin(StructurePieceType structurePieceType, int i, BlockBox blockBox) {
        super(structurePieceType, i, blockBox);
    }

    @Overwrite
    public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
        this.fillWithOutline(world, boundingBox, 0, 0, 0, 10, 7, 15, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
        this.generateEntrance(world, random, boundingBox, StrongholdGenerator.Piece.EntranceType.GRATES, 4, 1, 0);
        int i = 6;
        this.fillWithOutline(world, boundingBox, 1, i, 1, 1, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
        this.fillWithOutline(world, boundingBox, 9, i, 1, 9, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
        this.fillWithOutline(world, boundingBox, 2, i, 1, 8, i, 2, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
        this.fillWithOutline(world, boundingBox, 2, i, 14, 8, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
        this.fillWithOutline(world, boundingBox, 1, 1, 1, 2, 1, 4, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
        this.fillWithOutline(world, boundingBox, 8, 1, 1, 9, 1, 4, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
        this.fillWithOutline(world, boundingBox, 1, 1, 1, 1, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 9, 1, 1, 9, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
        this.fillWithOutline(world, boundingBox, 3, 1, 8, 7, 1, 12, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
        this.fillWithOutline(world, boundingBox, 4, 1, 9, 6, 1, 11, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
        BlockState blockState = (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true);
        BlockState blockState2 = (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true)).with(PaneBlock.EAST, true);

        int k;
        for(k = 3; k < 14; k += 2) {
            this.fillWithOutline(world, boundingBox, 0, 3, k, 0, 4, k, blockState, blockState, false);
            this.fillWithOutline(world, boundingBox, 10, 3, k, 10, 4, k, blockState, blockState, false);
        }

        for(k = 2; k < 9; k += 2) {
            this.fillWithOutline(world, boundingBox, k, 3, 15, k, 4, 15, blockState2, blockState2, false);
        }

        BlockState blockState3 = (BlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
        this.fillWithOutline(world, boundingBox, 4, 1, 5, 6, 1, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
        this.fillWithOutline(world, boundingBox, 4, 2, 6, 6, 2, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
        this.fillWithOutline(world, boundingBox, 4, 3, 7, 6, 3, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);

        for(int l = 4; l <= 6; ++l) {
            this.addBlock(world, blockState3, l, 1, 4, boundingBox);
            this.addBlock(world, blockState3, l, 2, 5, boundingBox);
            this.addBlock(world, blockState3, l, 3, 6, boundingBox);
        }

        BlockState blockState4 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
        BlockState blockState5 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
        BlockState blockState6 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
        BlockState blockState7 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);
        boolean bl = true;
        boolean[] bls = new boolean[12];

        for(int m = 0; m < bls.length; ++m) {
            bls[m] = random.nextFloat() > 0.75F;
            bl &= bls[m];
        }

        this.addBlock(world, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[0]), 4, 3, 8, boundingBox);
        this.addBlock(world, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[1]), 5, 3, 8, boundingBox);
        this.addBlock(world, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[2]), 6, 3, 8, boundingBox);
        this.addBlock(world, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[3]), 4, 3, 12, boundingBox);
        this.addBlock(world, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[4]), 5, 3, 12, boundingBox);
        this.addBlock(world, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[5]), 6, 3, 12, boundingBox);
        this.addBlock(world, (BlockState)blockState6.with(EndPortalFrameBlock.EYE, bls[6]), 3, 3, 9, boundingBox);
        this.addBlock(world, (BlockState)blockState6.with(EndPortalFrameBlock.EYE, bls[7]), 3, 3, 10, boundingBox);
        this.addBlock(world, (BlockState)blockState6.with(EndPortalFrameBlock.EYE, bls[8]), 3, 3, 11, boundingBox);
        this.addBlock(world, (BlockState)blockState7.with(EndPortalFrameBlock.EYE, bls[9]), 7, 3, 9, boundingBox);
        this.addBlock(world, (BlockState)blockState7.with(EndPortalFrameBlock.EYE, bls[10]), 7, 3, 10, boundingBox);
        this.addBlock(world, (BlockState)blockState7.with(EndPortalFrameBlock.EYE, bls[11]), 7, 3, 11, boundingBox);
        if (bl) {
            BlockState blockState8 = Blocks.END_PORTAL.getDefaultState();
            this.addBlock(world, blockState8, 4, 3, 9, boundingBox);
            this.addBlock(world, blockState8, 5, 3, 9, boundingBox);
            this.addBlock(world, blockState8, 6, 3, 9, boundingBox);
            this.addBlock(world, blockState8, 4, 3, 10, boundingBox);
            this.addBlock(world, blockState8, 5, 3, 10, boundingBox);
            this.addBlock(world, blockState8, 6, 3, 10, boundingBox);
            this.addBlock(world, blockState8, 4, 3, 11, boundingBox);
            this.addBlock(world, blockState8, 5, 3, 11, boundingBox);
            this.addBlock(world, blockState8, 6, 3, 11, boundingBox);
        }

        if (!this.spawnerPlaced) {
            BlockPos blockPos = this.offsetPos(5, 3, 6);
            if (boundingBox.contains(blockPos)) {
                this.spawnerPlaced = true;
                world.setBlockState(blockPos, Blocks.SPAWNER.getDefaultState(), 2);
                BlockEntity blockEntity = world.getBlockEntity(blockPos);
                if (blockEntity instanceof MobSpawnerBlockEntity) {
                    ((MobSpawnerBlockEntity)blockEntity).getLogic().setEntityId(EntityType.SILVERFISH);
                }
            }
        }

        return true;
    }
}
