package net.dillon8775.speedrunnermod.mixin.main.worldgen;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.world.gen.feature.ModFeatures;
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

/**
 * Makes strongholds smaller, and easier to navigate.
 * <p>Applies the opposite effect if {@code Doom Mode} is on.</p>
 */
@Mixin(StrongholdGenerator.class)
public class StrongholdGeneration {
    @Shadow
    private static final StrongholdGenerator.PieceData[] ALL_PIECES = ModFeatures.STRONGHOLD_GENERATION;

    @Mixin(StrongholdGenerator.PortalRoom.class)
    public abstract static class PortalRoomMixin extends StrongholdGenerator.Piece {
        @Shadow
        private boolean spawnerPlaced;

        public PortalRoomMixin(StructurePieceType structurePieceType, int i, BlockBox blockBox) {
            super(structurePieceType, i, blockBox);
        }

        @Overwrite
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pos) {
            this.fillWithOutline(world, chunkBox, 0, 0, 0, 10, 7, 15, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.generateEntrance(world, random, chunkBox, EntranceType.GRATES, 4, 1, 0);
            int i = 6;
            this.fillWithOutline(world, chunkBox, 1, i, 1, 1, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(world, chunkBox, 9, i, 1, 9, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(world, chunkBox, 2, i, 1, 8, i, 2, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(world, chunkBox, 2, i, 14, 8, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(world, chunkBox, 1, 1, 1, 2, 1, 4, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(world, chunkBox, 8, 1, 1, 9, 1, 4, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(world, chunkBox, 1, 1, 1, 1, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
            this.fillWithOutline(world, chunkBox, 9, 1, 1, 9, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
            this.fillWithOutline(world, chunkBox, 3, 1, 8, 7, 1, 12, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(world, chunkBox, 4, 1, 9, 6, 1, 11, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
            BlockState blockState = (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true);
            BlockState blockState2 = (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true)).with(PaneBlock.EAST, true);

            int j;
            for(j = 3; j < 14; j += 2) {
                this.fillWithOutline(world, chunkBox, 0, 3, j, 0, 4, j, blockState, blockState, false);
                this.fillWithOutline(world, chunkBox, 10, 3, j, 10, 4, j, blockState, blockState, false);
            }

            for(j = 2; j < 9; j += 2) {
                this.fillWithOutline(world, chunkBox, j, 3, 15, j, 4, 15, blockState2, blockState2, false);
            }

            BlockState j1 = (BlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
            this.fillWithOutline(world, chunkBox, 4, 1, 5, 6, 1, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(world, chunkBox, 4, 2, 6, 6, 2, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(world, chunkBox, 4, 3, 7, 6, 3, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);

            for(int k = 4; k <= 6; ++k) {
                this.addBlock(world, j1, k, 1, 4, chunkBox);
                this.addBlock(world, j1, k, 2, 5, chunkBox);
                this.addBlock(world, j1, k, 3, 6, chunkBox);
            }

            BlockState k = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
            BlockState blockState3 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
            BlockState blockState4 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
            BlockState blockState5 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);
            boolean bl = true;
            boolean[] bls = new boolean[12];

            for(int l = 0; l < bls.length; ++l) {
                final float chance = SpeedrunnerMod.options().main.doomMode ? 0.99F : 0.7F;
                bls[l] = random.nextFloat() > chance;
                bl &= bls[l];
            }

            this.addBlock(world, (BlockState)k.with(EndPortalFrameBlock.EYE, bls[0]), 4, 3, 8, chunkBox);
            this.addBlock(world, (BlockState)k.with(EndPortalFrameBlock.EYE, bls[1]), 5, 3, 8, chunkBox);
            this.addBlock(world, (BlockState)k.with(EndPortalFrameBlock.EYE, bls[2]), 6, 3, 8, chunkBox);
            this.addBlock(world, (BlockState)blockState3.with(EndPortalFrameBlock.EYE, bls[3]), 4, 3, 12, chunkBox);
            this.addBlock(world, (BlockState)blockState3.with(EndPortalFrameBlock.EYE, bls[4]), 5, 3, 12, chunkBox);
            this.addBlock(world, (BlockState)blockState3.with(EndPortalFrameBlock.EYE, bls[5]), 6, 3, 12, chunkBox);
            this.addBlock(world, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[6]), 3, 3, 9, chunkBox);
            this.addBlock(world, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[7]), 3, 3, 10, chunkBox);
            this.addBlock(world, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[8]), 3, 3, 11, chunkBox);
            this.addBlock(world, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[9]), 7, 3, 9, chunkBox);
            this.addBlock(world, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[10]), 7, 3, 10, chunkBox);
            this.addBlock(world, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[11]), 7, 3, 11, chunkBox);
            if (bl) {
                BlockState l = Blocks.END_PORTAL.getDefaultState();
                this.addBlock(world, l, 4, 3, 9, chunkBox);
                this.addBlock(world, l, 5, 3, 9, chunkBox);
                this.addBlock(world, l, 6, 3, 9, chunkBox);
                this.addBlock(world, l, 4, 3, 10, chunkBox);
                this.addBlock(world, l, 5, 3, 10, chunkBox);
                this.addBlock(world, l, 6, 3, 10, chunkBox);
                this.addBlock(world, l, 4, 3, 11, chunkBox);
                this.addBlock(world, l, 5, 3, 11, chunkBox);
                this.addBlock(world, l, 6, 3, 11, chunkBox);
            }

            if (!this.spawnerPlaced) {
                BlockPos l = this.offsetPos(5, 3, 6);
                if (chunkBox.contains(l)) {
                    this.spawnerPlaced = true;
                    world.setBlockState(l, Blocks.SPAWNER.getDefaultState(), 2);
                    BlockEntity blockEntity = world.getBlockEntity(l);
                    if (blockEntity instanceof MobSpawnerBlockEntity) {
                        ((MobSpawnerBlockEntity)blockEntity).getLogic().setEntityId(EntityType.SILVERFISH);
                    }
                }
            }

        }
    }
}