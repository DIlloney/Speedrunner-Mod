package com.dilloney.speedrunnermod.blocks;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class ModBlocks {

    public static final Block SPEEDRUNNER_BLOCK = new Block(FabricBlockSettings.of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool()
            .strength(5.0F, 6.0F)
            .sounds(BlockSoundGroup.METAL));

    public static final Block SPEEDRUNNER_ORE = new SpeedrunnerOreBlockProperties(FabricBlockSettings.of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DEEPSLATE_SPEEDRUNNER_ORE = new DeepslateSpeedrunnerOreBlockProperties(FabricBlockSettings.of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool()
            .strength(4.5F, 4.5F)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static final Block SPEEDRUNNER_NETHER_ORE = new SpeedrunnerNetherOreBlockProperties(FabricBlockSettings.of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES, 0)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.NETHER_GOLD_ORE));

    public static final Block IGNEOUS_ORE = new IgneousOreBlockProperties(FabricBlockSettings.of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DEEPSLATE_IGNEOUS_ORE = new DeepslateIgneousOreBlockProperties(FabricBlockSettings.of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool()
            .strength(4.5F, 4.5F)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static final Block IGNEOUS_NETHER_ORE = new IgneousNetherOreBlockProperties(FabricBlockSettings.of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.NETHER_ORE));

    public static final BlockItem SPEEDRUNNER_BLOCK_ITEM = new BlockItem(ModBlocks.SPEEDRUNNER_BLOCK,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MATERIALS).rarity(Rarity.COMMON));

    public static final BlockItem SPEEDRUNNER_ORE_BLOCK_ITEM = new BlockItem(ModBlocks.SPEEDRUNNER_ORE,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS).rarity(Rarity.COMMON));

    public static final BlockItem DEEPSLATE_SPEEDRUNNER_ORE_BLOCK_ITEM = new BlockItem(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS).rarity(Rarity.COMMON));

    public static final BlockItem NETHER_SPEEDRUNNER_ORE_BLOCK_ITEM = new BlockItem(ModBlocks.SPEEDRUNNER_NETHER_ORE,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS).rarity(Rarity.COMMON));

    public static final BlockItem IGNEOUS_ORE_BLOCK_ITEM = new BlockItem(ModBlocks.IGNEOUS_ORE,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS).rarity(Rarity.COMMON));

    public static final BlockItem DEEPSLATE_IGNEOUS_ORE_BLOCK_ITEM = new BlockItem(ModBlocks.DEEPSLATE_IGNEOUS_ORE,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS).rarity(Rarity.COMMON));

    public static final BlockItem NETHER_IGNEOUS_ORE_BLOCK_ITEM = new BlockItem(ModBlocks.IGNEOUS_NETHER_ORE,
            new Item.Settings().group(SpeedrunnerMod.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.BUILDING_BLOCKS).rarity(Rarity.COMMON));

    public static class SpeedrunnerOreBlockProperties extends Block {

        public SpeedrunnerOreBlockProperties(Settings settings) {
            super(settings);
        }

        protected int getExperienceWhenMined(Random random){
            return MathHelper.nextInt(random, 64, 128);
        }

        public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack){
            super.onStacksDropped(state, world, pos, stack);
            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                int i = this.getExperienceWhenMined(world.random);
                if (i > 0) {
                    this.dropExperience(world, pos, i);
                }
            }
        }
    }

    public static class DeepslateSpeedrunnerOreBlockProperties extends Block {

        public DeepslateSpeedrunnerOreBlockProperties(Settings settings) {
            super(settings);
        }

        protected int getExperienceWhenMined(Random random){
            return MathHelper.nextInt(random, 72, 192);
        }

        public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack){
            super.onStacksDropped(state, world, pos, stack);
            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                int i = this.getExperienceWhenMined(world.random);
                if (i > 0) {
                    this.dropExperience(world, pos, i);
                }
            }
        }
    }

    public static class SpeedrunnerNetherOreBlockProperties extends Block {

        public SpeedrunnerNetherOreBlockProperties(Settings settings) {
            super(settings);
        }

        protected int getExperienceWhenMined(Random random){
            return MathHelper.nextInt(random, 32, 64);
        }

        public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack){
            super.onStacksDropped(state, world, pos, stack);
            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                int i = this.getExperienceWhenMined(world.random);
                if (i > 0) {
                    this.dropExperience(world, pos, i);
                }
            }
        }
    }

    public static class IgneousOreBlockProperties extends Block {

        public IgneousOreBlockProperties(Settings settings) {
            super(settings);
        }

        protected int getExperienceWhenMined(Random random){
            return MathHelper.nextInt(random, 2, 6);
        }

        public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack){
            super.onStacksDropped(state, world, pos, stack);
            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                int i = this.getExperienceWhenMined(world.random);
                if (i > 0) {
                    this.dropExperience(world, pos, i);
                }
            }
        }
    }

    public static class DeepslateIgneousOreBlockProperties extends Block {

        public DeepslateIgneousOreBlockProperties(Settings settings) {
            super(settings);
        }

        protected int getExperienceWhenMined(Random random){
            return MathHelper.nextInt(random, 3, 8);
        }

        public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack){
            super.onStacksDropped(state, world, pos, stack);
            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                int i = this.getExperienceWhenMined(world.random);
                if (i > 0) {
                    this.dropExperience(world, pos, i);
                }
            }
        }
    }

    public static class IgneousNetherOreBlockProperties extends Block {

        public IgneousNetherOreBlockProperties(Settings settings) {
            super(settings);
        }

        protected int getExperienceWhenMined(Random random){
            return MathHelper.nextInt(random, 2, 6);
        }

        public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack){
            super.onStacksDropped(state, world, pos, stack);
            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                int i = this.getExperienceWhenMined(world.random);
                if (i > 0) {
                    this.dropExperience(world, pos, i);
                }
            }
        }
    }
}
