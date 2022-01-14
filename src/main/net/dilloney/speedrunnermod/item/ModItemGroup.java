package net.dilloney.speedrunnermod.item;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static final ItemGroup SPEEDRUNNER_MOD = FabricItemGroupBuilder.create(
            new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_mod_item_group"))
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
                itemStacks.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_SWORD));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_AXE));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_HOE));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_HELMET));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_BOOTS));
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
                itemStacks.add(new ItemStack(ModItems.GOLDEN_PIGLIN_PORK));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_BEEF));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_PORKCHOP));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_MUTTON));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_CHICKEN));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_RABBIT));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_COD));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_SALMON));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_BREAD));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_POTATO));
                itemStacks.add(new ItemStack(ModItems.GOLDEN_BEETROOT));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_BULK));
                itemStacks.add(new ItemStack(ModItems.ROTTEN_SPEEDRUNNER_BULK));
                itemStacks.add(new ItemStack(ModItems.COOKED_FLESH)); }).build();
}