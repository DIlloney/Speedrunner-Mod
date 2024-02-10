package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

/**
 * The {@link SpeedrunnerMod} item group.
 */
public class ModItemGroup {
    public static final ItemGroup SPEEDRUNNER_MOD = FabricItemGroupBuilder.create(
            new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_mod_item_group"))
            .icon(() -> new ItemStack(ModBlockItems.SPEEDRUNNER_BLOCK))
            .appendItems(itemStacks -> {
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_INGOT));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_NUGGET));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_BLOCK));
                itemStacks.add(new ItemStack(ModItems.RAW_SPEEDRUNNER));
                itemStacks.add(new ItemStack(ModBlockItems.RAW_SPEEDRUNNER_BLOCK));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_ORE));
                itemStacks.add(new ItemStack(ModBlockItems.DEEPSLATE_SPEEDRUNNER_ORE));
                itemStacks.add(new ItemStack(ModBlockItems.NETHER_SPEEDRUNNER_ORE));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_LOG));
                itemStacks.add(new ItemStack(ModBlockItems.STRIPPED_SPEEDRUNNER_LOG));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_WOOD));
                itemStacks.add(new ItemStack(ModBlockItems.STRIPPED_SPEEDRUNNER_WOOD));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_LEAVES));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_SAPLING));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_PLANKS));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_STICK));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_SLAB));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_STAIRS));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_FENCE));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_FENCE_GATE));
                itemStacks.add(new ItemStack(ModBlockItems.WOODEN_SPEEDRUNNER_TRAPDOOR));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_TRAPDOOR));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_BUTTON));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_PRESSURE_PLATE));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE));
                itemStacks.add(new ItemStack(ModBlockItems.WOODEN_SPEEDRUNNER_DOOR));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_DOOR));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNER_SIGN));
                itemStacks.add(new ItemStack(ModBlockItems.SPEEDRUNNERS_WORKBENCH));
                itemStacks.add(new ItemStack(ModBlockItems.DEAD_SPEEDRUNNER_BUSH));
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
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.DASH, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.DASH, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.DASH, 3)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.COOL, 1)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.COOL, 2)));
                itemStacks.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ModEnchantments.COOL, 3)));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_BOW));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_CROSSBOW));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_SHEARS));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_FLINT_AND_STEEL));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_SHIELD));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNERS_EYE));
                itemStacks.add(new ItemStack(ModItems.INFERNO_EYE));
                itemStacks.add(new ItemStack(ModItems.ANNUL_EYE));
                itemStacks.add(new ItemStack(ModItems.SPEEDRUNNER_BOAT));
                itemStacks.add(new ItemStack(ModItems.CRIMSON_BOAT));
                itemStacks.add(new ItemStack(ModItems.WARPED_BOAT));
                itemStacks.add(new ItemStack(ModItems.WITHER_SWORD));
                itemStacks.add(new ItemStack(ModItems.WITHER_BONE));
                itemStacks.add(new ItemStack(ModItems.IGNEOUS_ROCK));
                itemStacks.add(new ItemStack(ModBlockItems.IGNEOUS_ORE));
                itemStacks.add(new ItemStack(ModBlockItems.DEEPSLATE_IGNEOUS_ORE));
                itemStacks.add(new ItemStack(ModBlockItems.NETHER_IGNEOUS_ORE));
                itemStacks.add(new ItemStack(ModBlockItems.EXPERIENCE_ORE));
                itemStacks.add(new ItemStack(ModBlockItems.DEEPSLATE_EXPERIENCE_ORE));
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
                itemStacks.add(new ItemStack(ModItems.COOKED_FLESH));
            }).build();
}