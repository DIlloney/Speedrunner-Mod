package com.dilloney.speedrunnermod;

import com.dilloney.speedrunnermod.blocks.ModBlocks;
import com.dilloney.speedrunnermod.items.ModItems;
import com.dilloney.speedrunnermod.registry.ModRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class SpeedrunnerMod implements ModInitializer {

    @Override
    public void onInitialize() {
        ModRegistry.registerItems();
        ModRegistry.registerBlocks();
        ModRegistry.registerBlockItems();
        ModRegistry.registerStructureConfigs();
        ModRegistry.registerConfiguredFeatures();
        ModRegistry.registerUniqueItems();
}

    public static final ItemGroup SPEEDRUNNER_MOD_ITEM_GROUP = FabricItemGroupBuilder.create(
        new Identifier("speedrunnermod", "speedrunner_mod_item_group"))
            .icon(() -> new ItemStack(ModBlocks.SPEEDRUNNER_BLOCK_ITEM))
            .appendItems(itemStacks -> {
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_INGOT));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_NUGGET));
                itemStacks.add(new ItemStack(ModBlocks.SPEEDRUNNER_BLOCK_ITEM));
                itemStacks.add(new ItemStack(ModBlocks.SPEEDRUNNER_ORE_BLOCK_ITEM));
                itemStacks.add(new ItemStack(ModBlocks.NETHER_SPEEDRUNNER_ORE_BLOCK_ITEM));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_SWORD));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_SHOVEL));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_PICKAXE));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_AXE));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_HOE));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_HELMET));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_CHESTPLATE));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_LEGGINGS));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_BOOTS));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_BOW));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_SHEARS));
                itemStacks.add(new ItemStack(ModItems.EYE_OF_INFERNO));
                itemStacks.add(new ItemStack(ModItems.EYE_OF_ANNUL));
                itemStacks.add(new ItemStack(ModItems.IGNEOUS_ROCK));
                itemStacks.add(new ItemStack(ModBlocks.IGNEOUS_ORE_BLOCK_ITEM));
                itemStacks.add(new ItemStack(ModBlocks.NETHER_IGNEOUS_ORE_BLOCK_ITEM));
                itemStacks.add(new ItemStack(ModItems.COOKED_PIGLIN_PORK));
                itemStacks.add(new ItemStack(ModItems.RAW_PIGLIN_PORK));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_BULK));
                itemStacks.add(new ItemStack(ModItems.ROTTEN_SPEEDRUNNER_BULK));
                itemStacks.add(new ItemStack(ModItems.COOKED_FLESH));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_STEAK));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_PORKCHOP));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_MUTTON));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_CHICKEN));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_RABBIT));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_COD));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_SALMON));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_BREAD));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_POTATO));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_BEETROOT));
                itemStacks.add(new ItemStack(ModItems.BLACKSTONE_SWORD));
                itemStacks.add(new ItemStack(ModItems.BLACKSTONE_SHOVEL));
                itemStacks.add(new ItemStack(ModItems.BLACKSTONE_PICKAXE));
                itemStacks.add(new ItemStack(ModItems.BLACKSTONE_AXE));
                itemStacks.add(new ItemStack(ModItems.BLACKSTONE_HOE));
                itemStacks.add(new ItemStack(ModItems.ANDESITE_SWORD));
                itemStacks.add(new ItemStack(ModItems.ANDESITE_SHOVEL));
                itemStacks.add(new ItemStack(ModItems.ANDESITE_PICKAXE));
                itemStacks.add(new ItemStack(ModItems.ANDESITE_AXE));
                itemStacks.add(new ItemStack(ModItems.ANDESITE_HOE));
                itemStacks.add(new ItemStack(ModItems.GRANITE_SWORD));
                itemStacks.add(new ItemStack(ModItems.GRANITE_SHOVEL));
                itemStacks.add(new ItemStack(ModItems.GRANITE_PICKAXE));
                itemStacks.add(new ItemStack(ModItems.GRANITE_AXE));
                itemStacks.add(new ItemStack(ModItems.GRANITE_HOE));
                itemStacks.add(new ItemStack(ModItems.DIORITE_SWORD));
                itemStacks.add(new ItemStack(ModItems.DIORITE_SHOVEL));
                itemStacks.add(new ItemStack(ModItems.DIORITE_PICKAXE));
                itemStacks.add(new ItemStack(ModItems.DIORITE_AXE));
                itemStacks.add(new ItemStack(ModItems.DIORITE_HOE)); }).build();
}
