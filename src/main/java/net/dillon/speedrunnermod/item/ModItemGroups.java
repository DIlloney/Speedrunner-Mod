package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * The {@link SpeedrunnerMod} item group.
 */
public class ModItemGroups {

    public static ItemGroup SPEEDRUNNER_MOD = FabricItemGroup.builder(new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_mod_item_group"))
            .displayName(Text.literal("Speedrunner Mod"))
            .icon(() -> new ItemStack(ModBlockItems.SPEEDRUNNER_BLOCK)).build();

    private static void addToItemGroup(Item item) {
        ItemGroupEvents.modifyEntriesEvent(SPEEDRUNNER_MOD).register(entries -> entries.add(item));
    }

    private static void addToItemGroup(ItemStack stack) {
        ItemGroupEvents.modifyEntriesEvent(SPEEDRUNNER_MOD).register(entries -> entries.add(stack));
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void init() {
        addToItemGroup(ModItems.SPEEDRUNNER_INGOT);
        addToItemGroup(ModItems.SPEEDRUNNER_NUGGET);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_BLOCK);
        addToItemGroup(ModItems.RAW_SPEEDRUNNER);
        addToItemGroup(ModBlockItems.RAW_SPEEDRUNNER_BLOCK);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_ORE);
        addToItemGroup(ModBlockItems.DEEPSLATE_SPEEDRUNNER_ORE);
        addToItemGroup(ModBlockItems.NETHER_SPEEDRUNNER_ORE);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_LOG);
        addToItemGroup(ModBlockItems.STRIPPED_SPEEDRUNNER_LOG);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_WOOD);
        addToItemGroup(ModBlockItems.STRIPPED_SPEEDRUNNER_WOOD);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_LEAVES);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_SAPLING);
        addToItemGroup(ModBlockItems.DEAD_SPEEDRUNNER_LOG);
        addToItemGroup(ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_LOG);
        addToItemGroup(ModBlockItems.DEAD_SPEEDRUNNER_WOOD);
        addToItemGroup(ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        addToItemGroup(ModBlockItems.DEAD_SPEEDRUNNER_LEAVES);
        addToItemGroup(ModBlockItems.DEAD_SPEEDRUNNER_BUSH);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_PLANKS);
        addToItemGroup(ModItems.SPEEDRUNNER_STICK);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_SLAB);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_STAIRS);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_FENCE);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_FENCE_GATE);
        addToItemGroup(ModBlockItems.WOODEN_SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(ModBlockItems.WOODEN_SPEEDRUNNER_BUTTON);
        addToItemGroup(ModBlockItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        addToItemGroup(ModBlockItems.WOODEN_SPEEDRUNNER_DOOR);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_DOOR);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_SIGN);
        addToItemGroup(ModBlockItems.SPEEDRUNNER_HANGING_SIGN);
        addToItemGroup(ModBlockItems.SPEEDRUNNERS_WORKBENCH);
        addToItemGroup(ModItems.SPEEDRUNNER_SWORD);
        addToItemGroup(ModItems.SPEEDRUNNER_SHOVEL);
        addToItemGroup(ModItems.SPEEDRUNNER_PICKAXE);
        addToItemGroup(ModItems.SPEEDRUNNER_AXE);
        addToItemGroup(ModItems.SPEEDRUNNER_HOE);
        addToItemGroup(ModItems.SPEEDRUNNER_HELMET);
        addToItemGroup(ModItems.SPEEDRUNNER_CHESTPLATE);
        addToItemGroup(ModItems.SPEEDRUNNER_LEGGINGS);
        addToItemGroup(ModItems.SPEEDRUNNER_BOOTS);
        addToItemGroup(ModItems.GOLDEN_SPEEDRUNNER_SWORD);
        addToItemGroup(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
        addToItemGroup(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
        addToItemGroup(ModItems.GOLDEN_SPEEDRUNNER_AXE);
        addToItemGroup(ModItems.GOLDEN_SPEEDRUNNER_HOE);
        addToItemGroup(ModItems.GOLDEN_SPEEDRUNNER_HELMET);
        addToItemGroup(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
        addToItemGroup(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
        addToItemGroup(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
        addToItemGroup(ModItems.SPEEDRUNNER_BOW);
        addToItemGroup(ModItems.SPEEDRUNNER_CROSSBOW);
        addToItemGroup(ModItems.SPEEDRUNNER_SHEARS);
        addToItemGroup(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        addToItemGroup(ModItems.SPEEDRUNNER_SHIELD);
        addToItemGroup(ModItems.SPEEDRUNNERS_EYE);
        addToItemGroup(ModItems.ENDER_THRUSTER);
        addToItemGroup(ModBlockItems.THRUSTER_BLOCK);
        addToItemGroup(ModItems.INFERNO_EYE);
        addToItemGroup(ModItems.PIGLIN_AWAKENER);
        addToItemGroup(ModItems.BLAZE_SPOTTER);
        addToItemGroup(ModItems.RAID_ERADICATOR);
        addToItemGroup(ModItems.ANNUL_EYE);
        addToItemGroup(ModItems.DRAGONS_PEARL);
        addToItemGroup(ModItems.DRAGONS_SWORD);
        addToItemGroup(ModItems.WITHER_SWORD);
        addToItemGroup(ModItems.WITHER_BONE);
        addToItemGroup(ModItems.SPEEDRUNNER_BOAT);
        addToItemGroup(ModItems.SPEEDRUNNER_CHEST_BOAT);
        addToItemGroup(ModItems.CRIMSON_BOAT);
        addToItemGroup(ModItems.CRIMSON_CHEST_BOAT);
        addToItemGroup(ModItems.WARPED_BOAT);
        addToItemGroup(ModItems.WARPED_CHEST_BOAT);
        for (int i = 1; i < 7; i++) {
            addToItemGroup(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(i >= 4 ? ModEnchantments.COOLDOWN : ModEnchantments.DASH, i >= 4 ? i - 3 : i)));
        }
        addToItemGroup(ModItems.IGNEOUS_ROCK);
        addToItemGroup(ModBlockItems.IGNEOUS_ORE);
        addToItemGroup(ModBlockItems.DEEPSLATE_IGNEOUS_ORE);
        addToItemGroup(ModBlockItems.NETHER_IGNEOUS_ORE);
        addToItemGroup(ModBlockItems.EXPERIENCE_ORE);
        addToItemGroup(ModBlockItems.DEEPSLATE_EXPERIENCE_ORE);
        addToItemGroup(ModBlockItems.NETHER_EXPERIENCE_ORE);
        addToItemGroup(ModBlockItems.DOOM_STONE);
        addToItemGroup(ModBlockItems.DOOM_LOG);
        addToItemGroup(ModBlockItems.STRIPPED_DOOM_LOG);
        addToItemGroup(ModBlockItems.DOOM_LEAVES);
        addToItemGroup(ModItems.PIGLIN_PORK);
        addToItemGroup(ModItems.COOKED_PIGLIN_PORK);
        addToItemGroup(ModItems.GOLDEN_PIGLIN_PORK);
        addToItemGroup(ModItems.GOLDEN_BEEF);
        addToItemGroup(ModItems.GOLDEN_PORKCHOP);
        addToItemGroup(ModItems.GOLDEN_MUTTON);
        addToItemGroup(ModItems.GOLDEN_CHICKEN);
        addToItemGroup(ModItems.GOLDEN_RABBIT);
        addToItemGroup(ModItems.GOLDEN_COD);
        addToItemGroup(ModItems.GOLDEN_SALMON);
        addToItemGroup(ModItems.GOLDEN_BREAD);
        addToItemGroup(ModItems.GOLDEN_POTATO);
        addToItemGroup(ModItems.GOLDEN_BEETROOT);
        addToItemGroup(ModItems.SPEEDRUNNER_BULK);
        addToItemGroup(ModItems.ROTTEN_SPEEDRUNNER_BULK);
        addToItemGroup(ModItems.COOKED_FLESH);

        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.SPEEDRUNNER_INGOT);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.SPEEDRUNNER_NUGGET);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.RAW_SPEEDRUNNER);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_SWORD);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_SHOVEL);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_PICKAXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_AXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_HOE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_HELMET);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_CHESTPLATE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_LEGGINGS);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_BOOTS);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_BOW);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_CROSSBOW);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_SHEARS);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_SHIELD);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_SWORD);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_AXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_HOE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_HELMET);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.SPEEDRUNNER_BULK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.ROTTEN_SPEEDRUNNER_BULK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.COOKED_FLESH);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.PIGLIN_PORK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.COOKED_PIGLIN_PORK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_PIGLIN_PORK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_BEEF);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_PORKCHOP);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_MUTTON);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_CHICKEN);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_RABBIT);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_COD);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_SALMON);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_BREAD);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_POTATO);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_BEETROOT);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.IGNEOUS_ROCK);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.SPEEDRUNNER_STICK);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_CHEST_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.CRIMSON_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.CRIMSON_CHEST_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.WARPED_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.WARPED_CHEST_BOAT);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.WITHER_BONE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.WITHER_SWORD);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.ANNUL_EYE);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.SPEEDRUNNERS_EYE);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.INFERNO_EYE);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.PIGLIN_AWAKENER);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.BLAZE_SPOTTER);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.RAID_ERADICATOR);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.ENDER_THRUSTER);
        addToItemGroup(ItemGroups.COMBAT, ModItems.DRAGONS_SWORD);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.DRAGONS_PEARL);

        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.STRIPPED_SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.STRIPPED_SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_LEAVES);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_SAPLING);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_PLANKS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_SLAB);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_FENCE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_FENCE_GATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.WOODEN_SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.WOODEN_SPEEDRUNNER_BUTTON);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_DOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_BUSH);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModBlockItems.SPEEDRUNNERS_WORKBENCH);
        addToItemGroup(ItemGroups.INGREDIENTS, ModBlockItems.SPEEDRUNNER_BLOCK);
        addToItemGroup(ItemGroups.INGREDIENTS, ModBlockItems.RAW_SPEEDRUNNER_BLOCK);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.THRUSTER_BLOCK);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEEPSLATE_SPEEDRUNNER_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.NETHER_SPEEDRUNNER_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.IGNEOUS_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEEPSLATE_IGNEOUS_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.NETHER_IGNEOUS_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.EXPERIENCE_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEEPSLATE_EXPERIENCE_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.NETHER_EXPERIENCE_ORE);

        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DOOM_STONE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DOOM_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.STRIPPED_DOOM_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DOOM_LEAVES);

        SpeedrunnerMod.info("Initialized and modified item groups.");
    }
}