package com.dilloney.speedrunnermod.mixins.world.structure;

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

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(StrongholdGenerator.class)
public class StrongholdGeneratorMixin {

    @Shadow static final StrongholdGenerator.PieceData[] ALL_PIECES;

    static {
        if (OPTIONS.doomMode) {
            ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 10), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 50, 10), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 75, 10), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, 6) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 3;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 20, 1) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 5;
                }
            }};
        } else if (OPTIONS.getModDifficulty() <= 2 && OPTIONS.modifiedWorldGeneration) {
            ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 5, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 5, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 3, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 10, 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 4;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 200, 3) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 2;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 200, 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength);
                }
            }};
        } else if (OPTIONS.getModDifficulty() >= 3 && OPTIONS.modifiedWorldGeneration) {
            ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 25, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 5, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 5, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 5, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 5, 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 4;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 50, 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 3;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 50, 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength);
                }
            }};
        } else {
            ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 40, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 20, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 20, 0), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 10, 6), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 5, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 5, 4), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 5, 4), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 10, 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 4;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 20, 1) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 5;
                }
            }};
        }
    }

    @Mixin(StrongholdGenerator.PortalRoom.class)
    public static class PortalRoomMixin extends StrongholdGenerator.Piece {

        @Shadow boolean spawnerPlaced;

        protected PortalRoomMixin(StructurePieceType structurePieceType, int i, BlockBox blockBox) {
            super(structurePieceType, i, blockBox);
        }

        /**
         * Changes the how {@code Portal Room} generates in a {@code Stronghold}.
         * @author Dilloney
         * @reason
         */
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
            BlockState blockState = (BlockState) ((BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true);
            BlockState blockState2 = (BlockState) ((BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true)).with(PaneBlock.EAST, true);

            int k;
            for (k = 3; k < 14; k += 2) {
                this.fillWithOutline(world, boundingBox, 0, 3, k, 0, 4, k, blockState, blockState, false);
                this.fillWithOutline(world, boundingBox, 10, 3, k, 10, 4, k, blockState, blockState, false);
            }

            for (k = 2; k < 9; k += 2) {
                this.fillWithOutline(world, boundingBox, k, 3, 15, k, 4, 15, blockState2, blockState2, false);
            }

            BlockState blockState3 = (BlockState) Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
            this.fillWithOutline(world, boundingBox, 4, 1, 5, 6, 1, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(world, boundingBox, 4, 2, 6, 6, 2, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(world, boundingBox, 4, 3, 7, 6, 3, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);

            for (int l = 4; l <= 6; ++l) {
                this.addBlock(world, blockState3, l, 1, 4, boundingBox);
                this.addBlock(world, blockState3, l, 2, 5, boundingBox);
                this.addBlock(world, blockState3, l, 3, 6, boundingBox);
            }

            BlockState blockState4 = (BlockState) Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
            BlockState blockState5 = (BlockState) Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
            BlockState blockState6 = (BlockState) Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
            BlockState blockState7 = (BlockState) Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);
            boolean bl = true;
            boolean[] bls = new boolean[12];

            for (int m = 0; m < bls.length; ++m) {
                if (OPTIONS.modifiedWorldGeneration) {
                    if (OPTIONS.getModDifficulty() <= 2 && !OPTIONS.doomMode) {
                        bls[m] = random.nextFloat() > 0.7F;
                    } else if (OPTIONS.doomMode) {
                        bls[m] = random.nextFloat() > 0.99F;
                    }
                } else {
                    bls[m] = random.nextFloat() > 0.9F;
                }
                bl &= bls[m];
            }

            this.addBlock(world, (BlockState) blockState4.with(EndPortalFrameBlock.EYE, bls[0]), 4, 3, 8, boundingBox);
            this.addBlock(world, (BlockState) blockState4.with(EndPortalFrameBlock.EYE, bls[1]), 5, 3, 8, boundingBox);
            this.addBlock(world, (BlockState) blockState4.with(EndPortalFrameBlock.EYE, bls[2]), 6, 3, 8, boundingBox);
            this.addBlock(world, (BlockState) blockState5.with(EndPortalFrameBlock.EYE, bls[3]), 4, 3, 12, boundingBox);
            this.addBlock(world, (BlockState) blockState5.with(EndPortalFrameBlock.EYE, bls[4]), 5, 3, 12, boundingBox);
            this.addBlock(world, (BlockState) blockState5.with(EndPortalFrameBlock.EYE, bls[5]), 6, 3, 12, boundingBox);
            this.addBlock(world, (BlockState) blockState6.with(EndPortalFrameBlock.EYE, bls[6]), 3, 3, 9, boundingBox);
            this.addBlock(world, (BlockState) blockState6.with(EndPortalFrameBlock.EYE, bls[7]), 3, 3, 10, boundingBox);
            this.addBlock(world, (BlockState) blockState6.with(EndPortalFrameBlock.EYE, bls[8]), 3, 3, 11, boundingBox);
            this.addBlock(world, (BlockState) blockState7.with(EndPortalFrameBlock.EYE, bls[9]), 7, 3, 9, boundingBox);
            this.addBlock(world, (BlockState) blockState7.with(EndPortalFrameBlock.EYE, bls[10]), 7, 3, 10, boundingBox);
            this.addBlock(world, (BlockState) blockState7.with(EndPortalFrameBlock.EYE, bls[11]), 7, 3, 11, boundingBox);
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
}