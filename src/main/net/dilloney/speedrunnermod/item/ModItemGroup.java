package net.dilloney.speedrunnermod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static final ItemGroup SPEEDRUNNER_MOD_ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier("speedrunnermod", "speedrunner_mod_item_group"))
            .icon(() -> new ItemStack(ModItems.SPEEDRUNNER_BLOCK))
            .appendItems(itemStacks -> {
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_INGOT));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_NUGGET));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_BLOCK));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_ORE));
                itemStacks.add(new ItemStack(ModItems.NETHER_SPEEDRUNNER_ORE));
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
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_CROSSBOW));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_SHEARS));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_FLINT_AND_STEEL));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_SHIELD));
                itemStacks.add(new ItemStack(ModItems.INFERNO_EYE));
                itemStacks.add(new ItemStack(ModItems.ANNUL_EYE));
                itemStacks.add(new ItemStack(ModItems.IGNEOUS_ROCK));
                itemStacks.add(new ItemStack(ModItems.IGNEOUS_ORE));
                itemStacks.add(new ItemStack(ModItems.NETHER_IGNEOUS_ORE));
                itemStacks.add(new ItemStack(ModItems.PIGLIN_PORK));
                itemStacks.add(new ItemStack(ModItems.COOKED_PIGLIN_PORK));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_BULK));
                itemStacks.add(new ItemStack(ModItems.ROTTEN_SPEEDRUNNER_BULK));
                itemStacks.add(new ItemStack(ModItems.COOKED_FLESH));
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