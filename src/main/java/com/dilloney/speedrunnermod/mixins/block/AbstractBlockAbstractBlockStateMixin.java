package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockAbstractBlockStateMixin {

    @Inject(at = @At("HEAD"), method = "getHardness", cancellable = true)
    private void getHardnessMod(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> info) {
        if (SpeedrunnerMod.CONFIG.modifiedBlockHardness) {
            if (world.getBlockState(pos).getBlock() == Blocks.STONE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GRANITE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_GRANITE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DIORITE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_DIORITE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ANDESITE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_ANDESITE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK) {
                info.setReturnValue(0.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DIRT) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.COARSE_DIRT) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PODZOL) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.COBBLESTONE) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.OAK_PLANKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SPRUCE_PLANKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BIRCH_PLANKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JUNGLE_PLANKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ACACIA_PLANKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_OAK_PLANKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SAND) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.RED_SAND) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GRAVEL) {
                info.setReturnValue(0.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.OAK_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SPRUCE_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BIRCH_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JUNGLE_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ACACIA_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_OAK_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_OAK_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_SPRUCE_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_BIRCH_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_JUNGLE_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_ACACIA_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_DARK_OAK_LOG) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.OAK_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SPRUCE_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BIRCH_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JUNGLE_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ACACIA_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_OAK_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_OAK_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_SPRUCE_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_BIRCH_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_JUNGLE_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_ACACIA_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_DARK_OAK_WOOD) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.OAK_LEAVES) {
                info.setReturnValue(0.1F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SPRUCE_LEAVES) {
                info.setReturnValue(0.1F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BIRCH_LEAVES) {
                info.setReturnValue(0.1F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JUNGLE_LEAVES) {
                info.setReturnValue(0.1F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ACACIA_LEAVES) {
                info.setReturnValue(0.1F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_OAK_LEAVES) {
                info.setReturnValue(0.1F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SANDSTONE) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CHISELED_SANDSTONE) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CUT_SANDSTONE) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.COBWEB) {
                info.setReturnValue(2.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WHITE_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ORANGE_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.MAGENTA_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.LIGHT_BLUE_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.YELLOW_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.LIME_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PINK_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GRAY_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.LIGHT_GRAY_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CYAN_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PURPLE_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BLUE_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BROWN_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GREEN_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.RED_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BLACK_WOOL) {
                info.setReturnValue(0.65F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GOLD_BLOCK) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.IRON_BLOCK) {
                info.setReturnValue(3.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BOOKSHELF) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.MOSSY_COBBLESTONE) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN) {
                info.setReturnValue(35.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.OAK_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CHEST) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DIAMOND_BLOCK) {
                info.setReturnValue(4.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRAFTING_TABLE) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.FURNACE) {
                info.setReturnValue(2.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.OAK_DOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.COBBLESTONE_STAIRS) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.IRON_DOOR) {
                info.setReturnValue(3.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ICE) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SNOW_BLOCK) {
                info.setReturnValue(0.1F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CLAY) {
                info.setReturnValue(0.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JUKEBOX) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.OAK_FENCE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PUMPKIN) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.NETHERRACK) {
                info.setReturnValue(0.37F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SOUL_SAND) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SOUL_SOIL) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BASALT) {
                info.setReturnValue(1.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_BASALT) {
                info.setReturnValue(1.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GLOWSTONE) {
                info.setReturnValue(0.2F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CARVED_PUMPKIN) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JACK_O_LANTERN) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.OAK_TRAPDOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SPRUCE_TRAPDOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BIRCH_TRAPDOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JUNGLE_TRAPDOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ACACIA_TRAPDOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_OAK_TRAPDOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STONE_BRICKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.MOSSY_STONE_BRICKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRACKED_STONE_BRICKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CHISELED_STONE_BRICKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.IRON_BARS) {
                info.setReturnValue(3.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CHAIN) {
                info.setReturnValue(3.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.MELON) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.VINE) {
                info.setReturnValue(0.1F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.OAK_FENCE_GATE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STONE_BRICK_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.MYCELIUM) {
                info.setReturnValue(0.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.NETHER_BRICKS) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.NETHER_BRICK_FENCE) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.NETHER_BRICK_STAIRS) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ENCHANTING_TABLE) {
                info.setReturnValue(3.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CAULDRON) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.END_STONE) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SANDSTONE_STAIRS) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ENDER_CHEST) {
                info.setReturnValue(15.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.EMERALD_BLOCK) {
                info.setReturnValue(4.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SPRUCE_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BIRCH_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JUNGLE_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BEACON) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.COBBLESTONE_WALL) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.MOSSY_COBBLESTONE_WALL) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ANVIL) {
                info.setReturnValue(3.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CHIPPED_ANVIL) {
                info.setReturnValue(3.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DAMAGED_ANVIL) {
                info.setReturnValue(3.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.TRAPPED_CHEST) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.REDSTONE_BLOCK) {
                info.setReturnValue(3.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.QUARTZ_BLOCK) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CHISELED_QUARTZ_BLOCK) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.QUARTZ_PILLAR) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.QUARTZ_STAIRS) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DROPPER) {
                info.setReturnValue(2.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ACACIA_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_OAK_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.IRON_TRAPDOOR) {
                info.setReturnValue(3.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PRISMARINE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PRISMARINE_BRICKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_PRISMARINE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PRISMARINE_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PRISMARINE_BRICK_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PRISMARINE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PRISMARINE_BRICK_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_PRISMARINE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SEA_LANTERN) {
                info.setReturnValue(0.2F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.HAY_BLOCK) {
                info.setReturnValue(0.2F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.COAL_BLOCK) {
                info.setReturnValue(3.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PACKED_ICE) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.RED_SANDSTONE) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CHISELED_RED_SANDSTONE) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CUT_RED_SANDSTONE) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.RED_SANDSTONE_STAIRS) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.OAK_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SPRUCE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BIRCH_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JUNGLE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ACACIA_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_OAK_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STONE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOOTH_STONE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SANDSTONE_SLAB) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CUT_SANDSTONE_SLAB) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.COBBLESTONE_SLAB) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STONE_BRICK_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.NETHER_BRICK_SLAB) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.QUARTZ_SLAB) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.RED_SANDSTONE_SLAB) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CUT_RED_SANDSTONE_SLAB) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOOTH_STONE) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOOTH_SANDSTONE) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOOTH_QUARTZ) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOOTH_RED_SANDSTONE) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SPRUCE_FENCE_GATE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BIRCH_FENCE_GATE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JUNGLE_FENCE_GATE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ACACIA_FENCE_GATE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_OAK_FENCE_GATE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SPRUCE_FENCE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BIRCH_FENCE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JUNGLE_FENCE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ACACIA_FENCE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_OAK_FENCE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SPRUCE_DOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BIRCH_DOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.JUNGLE_DOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ACACIA_DOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DARK_OAK_DOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.END_STONE_BRICKS) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DIRT_PATH) {
                info.setReturnValue(0.55F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.FROSTED_ICE) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.MAGMA_BLOCK) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.NETHER_WART_BLOCK) {
                info.setReturnValue(0.6F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.RED_NETHER_BRICKS) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BONE_BLOCK) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DRIED_KELP_BLOCK) {
                info.setReturnValue(0.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BLUE_ICE) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_GRANITE_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOOTH_RED_SANDSTONE_STAIRS) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.MOSSY_STONE_BRICK_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.END_STONE_BRICK_STAIRS) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STONE_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOOTH_SANDSTONE_STAIRS) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOOTH_QUARTZ_STAIRS) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GRANITE_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ANDESITE_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.RED_NETHER_BRICK_STAIRS) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_ANDESITE_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DIORITE_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_GRANITE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOOTH_RED_SANDSTONE_SLAB) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.MOSSY_STONE_BRICK_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_DIORITE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.MOSSY_COBBLESTONE_SLAB) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.END_STONE_BRICK_SLAB) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOOTH_SANDSTONE_SLAB) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOOTH_QUARTZ_SLAB) {
                info.setReturnValue(0.8F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GRANITE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ANDESITE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.RED_NETHER_BRICK_SLAB) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_ANDESITE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DIORITE_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.PRISMARINE_WALL) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.RED_SANDSTONE_WALL) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.MOSSY_STONE_BRICK_WALL) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GRANITE_WALL) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STONE_BRICK_WALL) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.NETHER_BRICK_WALL) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ANDESITE_WALL) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.RED_NETHER_BRICK_WALL) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SANDSTONE_WALL) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.END_STONE_BRICK_WALL) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DIORITE_WALL) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.LOOM) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BARREL) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMOKER) {
                info.setReturnValue(2.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BLAST_FURNACE) {
                info.setReturnValue(2.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CARTOGRAPHY_TABLE) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.FLETCHING_TABLE) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GRINDSTONE) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.LECTERN) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SMITHING_TABLE) {
                info.setReturnValue(1.6F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BELL) {
                info.setReturnValue(3.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.LANTERN) {
                info.setReturnValue(2.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SOUL_LANTERN) {
                info.setReturnValue(2.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WARPED_STEM) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_WARPED_STEM) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WARPED_HYPHAE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_WARPED_HYPHAE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WARPED_NYLIUM) {
                info.setReturnValue(0.37F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WARPED_WART_BLOCK) {
                info.setReturnValue(0.6F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRIMSON_STEM) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_CRIMSON_STEM) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRIMSON_HYPHAE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_CRIMSON_HYPHAE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRIMSON_NYLIUM) {
                info.setReturnValue(0.37F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SHROOMLIGHT) {
                info.setReturnValue(0.6F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRIMSON_PLANKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WARPED_PLANKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRIMSON_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WARPED_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRIMSON_FENCE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WARPED_FENCE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRIMSON_TRAPDOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WARPED_TRAPDOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRIMSON_FENCE_GATE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WARPED_FENCE_GATE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRIMSON_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WARPED_STAIRS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRIMSON_DOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.WARPED_DOOR) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.NETHERITE_BLOCK) {
                info.setReturnValue(25.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.ANCIENT_DEBRIS) {
                info.setReturnValue(15.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRYING_OBSIDIAN) {
                info.setReturnValue(10.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.RESPAWN_ANCHOR) {
                info.setReturnValue(35.0F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.LODESTONE) {
                info.setReturnValue(2.50F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BLACKSTONE) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BLACKSTONE_STAIRS) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BLACKSTONE_WALL) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.BLACKSTONE_SLAB) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_BLACKSTONE) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_BLACKSTONE_BRICKS) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CHISELED_POLISHED_BLACKSTONE) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_BLACKSTONE_BRICK_SLAB) {
                info.setReturnValue(1.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_BLACKSTONE_BRICK_WALL) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.GILDED_BLACKSTONE) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_BLACKSTONE_STAIRS) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_BLACKSTONE_SLAB) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_BLACKSTONE_WALL) {
                info.setReturnValue(1.4F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CHISELED_NETHER_BRICKS) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRACKED_NETHER_BRICKS) {
                info.setReturnValue(1.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.QUARTZ_BRICKS) {
                info.setReturnValue(0.7F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE) {
                info.setReturnValue(2.3F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.COBBLED_DEEPSLATE) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.COBBLED_DEEPSLATE_STAIRS) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.COBBLED_DEEPSLATE_SLAB) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.COBBLED_DEEPSLATE_WALL) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_DEEPSLATE) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_DEEPSLATE_STAIRS) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_DEEPSLATE_SLAB) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.POLISHED_DEEPSLATE_WALL) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE_TILES) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE_TILE_STAIRS) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE_TILE_SLAB) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE_TILE_WALL) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE_BRICKS) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE_BRICK_STAIRS) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE_BRICK_SLAB) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE_BRICK_WALL) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CHISELED_DEEPSLATE) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRACKED_DEEPSLATE_BRICKS) {
                info.setReturnValue(2.5F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.CRACKED_DEEPSLATE_TILES) {
                info.setReturnValue(2.5F);
            }
        }
    }
}
