package net.dillon.speedrunnermod.datagen;

import net.dillon.speedrunnermod.item.ModBlockItems;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

/**
 * Contains the entries of all new or already existing item tags.
 */
public class ModItemTagGenerator extends FabricTagProvider.ItemTagProvider {

    public ModItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModItemTags.BOWS)
                .add(Items.BOW)
                .add(ModItems.SPEEDRUNNER_BOW);

        getOrCreateTagBuilder(ModItemTags.SPEED_BOOTS)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        getOrCreateTagBuilder(ModItemTags.SHIELDS)
                .add(Items.SHIELD)
                .add(ModItems.SPEEDRUNNER_SHIELD);

        getOrCreateTagBuilder(ModItemTags.COOLDOWN_ENCHANTMENT_ITEMS)
                .forceAddTag(ModItemTags.SHIELDS)
                .add(Items.ENDER_PEARL)
                .add(Items.CHORUS_FRUIT);

        getOrCreateTagBuilder(ModItemTags.CROSSBOWS)
                .add(Items.CROSSBOW)
                .add(ModItems.SPEEDRUNNER_CROSSBOW);

        getOrCreateTagBuilder(ModItemTags.SPEEDRUNNER_TOOLS)
                .add(ModItems.SPEEDRUNNER_SWORD)
                .add(ModItems.SPEEDRUNNER_SHOVEL)
                .add(ModItems.SPEEDRUNNER_PICKAXE)
                .add(ModItems.SPEEDRUNNER_AXE)
                .add(ModItems.SPEEDRUNNER_HOE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SWORD)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL)
                .add(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_AXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HOE);

        getOrCreateTagBuilder(ModItemTags.DOOM_STONE_SAFE_TOOLS)
                .forceAddTag(ModItemTags.SPEEDRUNNER_TOOLS);

        getOrCreateTagBuilder(ModItemTags.FASTER_BOATS)
                .add(ModItems.SPEEDRUNNER_BOAT);

        getOrCreateTagBuilder(ModItemTags.FASTER_CHEST_BOATS)
                .add(ModItems.SPEEDRUNNER_CHEST_BOAT);

        getOrCreateTagBuilder(ModItemTags.FIREPROOF_BOATS)
                .add(ModItems.SPEEDRUNNER_BOAT)
                .add(ModItems.CRIMSON_BOAT)
                .add(ModItems.WARPED_BOAT);

        getOrCreateTagBuilder(ModItemTags.FIREPROOF_CHEST_BOATS)
                .add(ModItems.SPEEDRUNNER_CHEST_BOAT)
                .add(ModItems.CRIMSON_CHEST_BOAT)
                .add(ModItems.WARPED_CHEST_BOAT);

        getOrCreateTagBuilder(ModItemTags.FIREPROOF_ITEMS)
                .add(Items.BLAZE_ROD)
                .add(Items.BLAZE_POWDER)
                .add(Items.FIRE_CHARGE);

        getOrCreateTagBuilder(ModItemTags.FLINT_AND_STEELS)
                .add(Items.FLINT_AND_STEEL)
                .add(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);

        getOrCreateTagBuilder(ModItemTags.GOLDEN_FOOD_ITEMS)
                .add(Items.GOLDEN_APPLE)
                .add(Items.ENCHANTED_GOLDEN_APPLE)
                .add(Items.GOLDEN_CARROT)
                .add(ModItems.GOLDEN_PIGLIN_PORK)
                .add(ModItems.GOLDEN_BEEF)
                .add(ModItems.GOLDEN_PORKCHOP)
                .add(ModItems.GOLDEN_MUTTON)
                .add(ModItems.GOLDEN_CHICKEN)
                .add(ModItems.GOLDEN_RABBIT)
                .add(ModItems.GOLDEN_COD)
                .add(ModItems.GOLDEN_SALMON)
                .add(ModItems.GOLDEN_BREAD)
                .add(ModItems.GOLDEN_POTATO)
                .add(ModItems.GOLDEN_BEETROOT);

        getOrCreateTagBuilder(ModItemTags.IGNITABLES)
                .forceAddTag(ModItemTags.FLINT_AND_STEELS)
                .add(Items.FIRE_CHARGE);

        getOrCreateTagBuilder(ModItemTags.IRON_INGOTS)
                .add(Items.IRON_INGOT)
                .add(ModItems.SPEEDRUNNER_INGOT);

        getOrCreateTagBuilder(ModItemTags.IRON_NUGGETS)
                .add(Items.IRON_NUGGET)
                .add(ModItems.SPEEDRUNNER_NUGGET);

        getOrCreateTagBuilder(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)
                .forceAddTag(ModItemTags.GOLDEN_FOOD_ITEMS)
                .add(Items.ENDER_PEARL)
                .add(Items.BLAZE_POWDER);

        getOrCreateTagBuilder(ModItemTags.PIGLIN_SAFE_ARMOR)
                .add(Items.GOLDEN_HELMET)
                .add(Items.GOLDEN_CHESTPLATE)
                .add(Items.GOLDEN_LEGGINGS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HELMET)
                .add(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        getOrCreateTagBuilder(ModItemTags.SHIELDS)
                .add(Items.SHIELD)
                .add(ModItems.SPEEDRUNNER_SHIELD);

        getOrCreateTagBuilder(ModItemTags.SHEARS)
                .add(Items.SHEARS)
                .add(ModItems.SPEEDRUNNER_SHEARS);

        getOrCreateTagBuilder(ModItemTags.SPEED_BOOTS)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        getOrCreateTagBuilder(ModItemTags.SPEEDRUNNER_ARMOR)
                .add(ModItems.SPEEDRUNNER_HELMET)
                .add(ModItems.SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.SPEEDRUNNER_LEGGINGS)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HELMET)
                .add(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        getOrCreateTagBuilder(ModItemTags.STACK_TO_64)
                .forceAddTag(ItemTags.BEDS)
                .forceAddTag(ItemTags.BOATS)
                .forceAddTag(ItemTags.CHEST_BOATS)
                .forceAddTag(ItemTags.SIGNS)
                .forceAddTag(ItemTags.BANNERS)
                .add(Items.POTION)
                .add(Items.SPLASH_POTION)
                .add(Items.LINGERING_POTION)
                .add(Items.ENDER_PEARL)
                .add(Items.EGG)
                .add(Items.SNOWBALL)
                .add(Items.ARMOR_STAND)
                .add(Items.HONEY_BOTTLE)
                .add(Items.BUCKET)
                .add(Items.TOTEM_OF_UNDYING)
                .add(ModItems.DRAGONS_PEARL)
                .add(ModItems.PIGLIN_AWAKENER)
                .add(ModItems.ENDER_THRUSTER)
                .add(ModItems.BLAZE_SPOTTER)
                .add(ModItems.RAID_ERADICATOR);

        getOrCreateTagBuilder(ModItemTags.STICKS)
                .add(Items.STICK)
                .add(ModItems.SPEEDRUNNER_STICK);

        getOrCreateTagBuilder(ModItemTags.Block.DOOM_LOGS)
                .add(ModBlockItems.DOOM_LOG)
                .add(ModBlockItems.STRIPPED_DOOM_LOG);

        getOrCreateTagBuilder(ModItemTags.Block.EXPERIENCE_ORES)
                .add(ModBlockItems.EXPERIENCE_ORE)
                .add(ModBlockItems.DEEPSLATE_EXPERIENCE_ORE)
                .add(ModBlockItems.NETHER_EXPERIENCE_ORE);

        getOrCreateTagBuilder(ModItemTags.Block.IGNEOUS_ORES)
                .add(ModBlockItems.IGNEOUS_ORE)
                .add(ModBlockItems.DEEPSLATE_IGNEOUS_ORE)
                .add(ModBlockItems.NETHER_IGNEOUS_ORE);

        getOrCreateTagBuilder(ModItemTags.Block.IRON_BLOCKS)
                .add(Items.IRON_BLOCK)
                .add(ModBlockItems.SPEEDRUNNER_BLOCK);

        getOrCreateTagBuilder(ModItemTags.Block.NETHER_PORTAL_BASE_BLOCKS)
                .add(Items.OBSIDIAN)
                .add(Items.CRYING_OBSIDIAN);

        getOrCreateTagBuilder(ModItemTags.Block.SMITHING_TABLES)
                .add(Items.SMITHING_TABLE)
                .add(ModBlockItems.SPEEDRUNNERS_WORKBENCH);

        getOrCreateTagBuilder(ModItemTags.Block.SPEEDRUNNER_LOGS)
                .add(ModBlockItems.SPEEDRUNNER_LOG)
                .add(ModBlockItems.STRIPPED_SPEEDRUNNER_LOG)
                .add(ModBlockItems.SPEEDRUNNER_WOOD)
                .add(ModBlockItems.STRIPPED_SPEEDRUNNER_WOOD)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_LOG)
                .add(ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_LOG)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_WOOD)
                .add(ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD);

        getOrCreateTagBuilder(ModItemTags.Block.SPEEDRUNNER_FUELS)
                .forceAddTag(ModItemTags.Block.SPEEDRUNNER_LOGS)
                .add(ModBlockItems.SPEEDRUNNER_SAPLING)
                .add(ModBlockItems.SPEEDRUNNER_SLAB)
                .add(ModBlockItems.SPEEDRUNNER_STAIRS)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_TRAPDOOR)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE)
                .add(ModBlockItems.SPEEDRUNNER_FENCE)
                .add(ModBlockItems.SPEEDRUNNER_FENCE_GATE)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_BUTTON)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_BUSH);

        getOrCreateTagBuilder(ModItemTags.Block.SPEEDRUNNER_ORES)
                .add(ModBlockItems.SPEEDRUNNER_ORE)
                .add(ModBlockItems.DEEPSLATE_SPEEDRUNNER_ORE)
                .add(ModBlockItems.NETHER_SPEEDRUNNER_ORE);

        getOrCreateTagBuilder(ModItemTags.Block.SPEEDRUNNER_SAPLING_PLACEABLES)
                .forceAddTag(ItemTags.SAND)
                .add(Items.NETHERRACK)
                .add(Items.CRIMSON_NYLIUM)
                .add(Items.WARPED_NYLIUM);

        getOrCreateTagBuilder(ModItemTags.Block.SPEEDRUNNER_SIGNS)
                .add(ModBlockItems.SPEEDRUNNER_SIGN)
                .add(ModBlockItems.SPEEDRUNNER_HANGING_SIGN);

        getOrCreateTagBuilder(ItemTags.BOATS)
                .add(ModItems.SPEEDRUNNER_BOAT)
                .add(ModItems.CRIMSON_BOAT)
                .add(ModItems.WARPED_BOAT);

        getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
                .add(ModItems.SPEEDRUNNER_CHEST_BOAT)
                .add(ModItems.CRIMSON_CHEST_BOAT)
                .add(ModItems.WARPED_CHEST_BOAT);

        getOrCreateTagBuilder(ItemTags.DOORS)
                .add(ModBlockItems.SPEEDRUNNER_DOOR);

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_DOOR);

        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(ModBlockItems.SPEEDRUNNER_FENCE_GATE);

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(ModBlockItems.SPEEDRUNNER_FENCE);

        getOrCreateTagBuilder(ItemTags.LEAVES)
                .add(ModBlockItems.SPEEDRUNNER_LEAVES)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_LEAVES)
                .add(ModBlockItems.DOOM_LEAVES);

        getOrCreateTagBuilder(ItemTags.LOGS)
                .forceAddTag(ModItemTags.Block.SPEEDRUNNER_LOGS)
                .forceAddTag(ModItemTags.Block.DOOM_LOGS);

        getOrCreateTagBuilder(ItemTags.PIGLIN_FOOD)
                .add(ModItems.PIGLIN_PORK)
                .add(ModItems.COOKED_PIGLIN_PORK)
                .add(ModItems.GOLDEN_PIGLIN_PORK);

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlockItems.SPEEDRUNNER_PLANKS);

        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(ModBlockItems.SPEEDRUNNER_SAPLING);

        getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(Items.ANDESITE)
                .add(Items.DIORITE)
                .add(Items.GRANITE)
                .add(Items.MOSSY_COBBLESTONE)
                .add(Items.END_STONE);

        getOrCreateTagBuilder(ItemTags.TRAPDOORS)
                .add(ModBlockItems.SPEEDRUNNER_TRAPDOOR);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .forceAddTag(ModItemTags.SPEEDRUNNER_ARMOR);

        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_BUTTON);

        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);

        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(ModBlockItems.SPEEDRUNNER_SLAB);

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(ModBlockItems.SPEEDRUNNER_STAIRS);

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_TRAPDOOR);
    }
}