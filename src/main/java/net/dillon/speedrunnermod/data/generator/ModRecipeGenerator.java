package net.dillon.speedrunnermod.data.generator;

import com.google.common.collect.ImmutableList;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

/**
 * Used to create and modify {@code speedrunner mod and vanilla recipes.}
 */
public class ModRecipeGenerator extends FabricRecipeProvider {
    private static final ImmutableList<ItemConvertible> DIAMOND_ORES = ImmutableList.of(Items.DIAMOND_ORE, Items.DEEPSLATE_DIAMOND_ORE);
    private static final ImmutableList<ItemConvertible> EMERALD_ORES = ImmutableList.of(Items.EMERALD_ORE, Items.DEEPSLATE_EMERALD_ORE);
    private static final ImmutableList<ItemConvertible> GOLD_ORES = ImmutableList.of(Items.GOLD_ORE, Items.DEEPSLATE_GOLD_ORE, Items.NETHER_GOLD_ORE, Items.RAW_GOLD);
    private static final ImmutableList<ItemConvertible> IRON_ORES = ImmutableList.of(Items.IRON_ORE, Items.DEEPSLATE_IRON_ORE, Items.RAW_IRON);
    private static final ImmutableList<ItemConvertible> LAPIS_ORES = ImmutableList.of(Items.LAPIS_ORE, Items.DEEPSLATE_LAPIS_ORE);
    private static final ImmutableList<ItemConvertible> REDSTONE_ORES = ImmutableList.of(Items.REDSTONE_ORE, Items.DEEPSLATE_REDSTONE_ORE);

    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {

            @Override
            public void generate() {
                ModRecipeGeneratorHelper helper = new ModRecipeGeneratorHelper(wrapperLookup, exporter);

                helper.createAxe(ItemTags.DIAMOND_TOOL_MATERIALS, Items.DIAMOND_AXE, true);
                helper.createAxe(ItemTags.GOLD_TOOL_MATERIALS, Items.GOLDEN_AXE, true);
                helper.createAxe(ItemTags.IRON_TOOL_MATERIALS, Items.IRON_AXE, true);
                helper.createAxe(ItemTags.STONE_TOOL_MATERIALS, Items.STONE_AXE, true);
                helper.createAxe(ItemTags.WOODEN_TOOL_MATERIALS, Items.WOODEN_AXE, true);

                helper.createHoe(ItemTags.DIAMOND_TOOL_MATERIALS, Items.DIAMOND_HOE, true);
                helper.createHoe(ItemTags.GOLD_TOOL_MATERIALS, Items.GOLDEN_HOE, true);
                helper.createHoe(ItemTags.IRON_TOOL_MATERIALS, Items.IRON_HOE, true);
                helper.createHoe(ItemTags.STONE_TOOL_MATERIALS, Items.STONE_HOE, true);
                helper.createHoe(ItemTags.WOODEN_TOOL_MATERIALS, Items.WOODEN_HOE, true);

                helper.createPickaxe(ItemTags.DIAMOND_TOOL_MATERIALS, Items.DIAMOND_PICKAXE, true);
                helper.createPickaxe(ItemTags.GOLD_TOOL_MATERIALS, Items.GOLDEN_PICKAXE, true);
                helper.createPickaxe(ItemTags.IRON_TOOL_MATERIALS, Items.IRON_PICKAXE, true);
                helper.createPickaxe(ItemTags.STONE_TOOL_MATERIALS, Items.STONE_PICKAXE, true);
                helper.createPickaxe(ItemTags.WOODEN_TOOL_MATERIALS, Items.WOODEN_PICKAXE, true);

                helper.createShovel(ItemTags.DIAMOND_TOOL_MATERIALS, Items.DIAMOND_SHOVEL, true);
                helper.createShovel(ItemTags.GOLD_TOOL_MATERIALS, Items.GOLDEN_SHOVEL, true);
                helper.createShovel(ItemTags.IRON_TOOL_MATERIALS, Items.IRON_SHOVEL, true);
                helper.createShovel(ItemTags.STONE_TOOL_MATERIALS, Items.STONE_SHOVEL, true);
                helper.createShovel(ItemTags.WOODEN_TOOL_MATERIALS, Items.WOODEN_SHOVEL, true);

                helper.createSword(ItemTags.DIAMOND_TOOL_MATERIALS, Items.DIAMOND_SWORD, true);
                helper.createSword(ItemTags.GOLD_TOOL_MATERIALS, Items.GOLDEN_SWORD, true);
                helper.createSword(ItemTags.IRON_TOOL_MATERIALS, Items.IRON_SWORD, true);
                helper.createSword(ItemTags.STONE_TOOL_MATERIALS, Items.STONE_SWORD, true);
                helper.createSword(ItemTags.WOODEN_TOOL_MATERIALS, Items.WOODEN_SWORD, true);

                helper.offerBurnableMaterial(DIAMOND_ORES, Items.DIAMOND, 30, "diamond", true);
                helper.offerBurnableMaterial(EMERALD_ORES, Items.EMERALD, 300, "emerald", true);
                helper.offerBurnableMaterial(GOLD_ORES, Items.GOLD_INGOT, 150, "gold_ingot", true);
                helper.offerBurnableMaterial(IRON_ORES, Items.IRON_INGOT, 100, "iron_ingot", true);
                helper.offerBurnableMaterial(LAPIS_ORES, Items.LAPIS_LAZULI, 100, "lapis_lazuli", true);
                helper.offerBurnableMaterial(REDSTONE_ORES, Items.REDSTONE, 100, "redstone", true);

                helper.createCookableFood(Items.POTATO, Items.BAKED_POTATO, true);
                helper.createCookableFood(Items.BEEF, Items.COOKED_BEEF, true);
                helper.createCookableFood(Items.CHICKEN, Items.COOKED_CHICKEN, true);
                helper.createCookableFood(Items.COD, Items.COOKED_COD, true);
                helper.createCookableFood(Items.MUTTON, Items.COOKED_MUTTON, true);
                helper.createCookableFood(Items.PORKCHOP, Items.COOKED_PORKCHOP, true);
                helper.createCookableFood(Items.RABBIT, Items.COOKED_RABBIT, true);
                helper.createCookableFood(Items.SALMON, Items.COOKED_SALMON, true);
                helper.createCookableFood(ModItems.ROTTEN_SPEEDRUNNER_BULK, Items.ROTTEN_FLESH, true);
                helper.createCookableFood(Items.ROTTEN_FLESH, ModItems.COOKED_FLESH, false);
                helper.createCookableFood(ModItems.PIGLIN_PORK, ModItems.COOKED_PIGLIN_PORK, false);

                helper.offerBannerRecipe(Items.BLACK_BANNER, Blocks.BLACK_WOOL);
                helper.offerBannerRecipe(Items.BLUE_BANNER, Blocks.BLUE_WOOL);
                helper.offerBannerRecipe(Items.BROWN_BANNER, Blocks.BROWN_WOOL);
                helper.offerBannerRecipe(Items.CYAN_BANNER, Blocks.CYAN_WOOL);
                helper.offerBannerRecipe(Items.GRAY_BANNER, Blocks.GRAY_WOOL);
                helper.offerBannerRecipe(Items.GREEN_BANNER, Blocks.GREEN_WOOL);
                helper.offerBannerRecipe(Items.LIGHT_BLUE_BANNER, Blocks.LIGHT_BLUE_WOOL);
                helper.offerBannerRecipe(Items.LIGHT_GRAY_BANNER, Blocks.LIGHT_GRAY_WOOL);
                helper.offerBannerRecipe(Items.LIME_BANNER, Blocks.LIME_WOOL);
                helper.offerBannerRecipe(Items.MAGENTA_BANNER, Blocks.MAGENTA_WOOL);
                helper.offerBannerRecipe(Items.ORANGE_BANNER, Blocks.ORANGE_WOOL);
                helper.offerBannerRecipe(Items.PINK_BANNER, Blocks.PINK_WOOL);
                helper.offerBannerRecipe(Items.PURPLE_BANNER, Blocks.PURPLE_WOOL);
                helper.offerBannerRecipe(Items.RED_BANNER, Blocks.RED_WOOL);
                helper.offerBannerRecipe(Items.WHITE_BANNER, Blocks.WHITE_WOOL);
                helper.offerBannerRecipe(Items.YELLOW_BANNER, Blocks.YELLOW_WOOL);

                CookingRecipeJsonBuilder.createSmelting(
                        Ingredient.ofItems(
                                Items.IRON_PICKAXE,
                                Items.IRON_SHOVEL,
                                Items.IRON_AXE,
                                Items.IRON_HOE,
                                Items.IRON_SWORD,
                                Items.IRON_HELMET,
                                Items.IRON_CHESTPLATE,
                                Items.IRON_LEGGINGS,
                                Items.IRON_BOOTS,
                                Items.IRON_HORSE_ARMOR,
                                Items.CHAINMAIL_HELMET,
                                Items.CHAINMAIL_CHESTPLATE,
                                Items.CHAINMAIL_LEGGINGS,
                                Items.CHAINMAIL_BOOTS
                        ),
                        RecipeCategory.MISC,
                        Items.IRON_NUGGET,
                        100.0F,
                        20
                );

                this.createShaped(RecipeCategory.TRANSPORTATION, Blocks.ACTIVATOR_RAIL, 6)
                        .input('#', Blocks.REDSTONE_TORCH)
                        .input('S', Items.STICK)
                        .input('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("XSX")
                        .pattern("X#X")
                        .pattern("XSX")
                        .criterion("has_rail", this.conditionsFromItem(Blocks.RAIL))
                        .offerTo(this.exporter, helper.vanilla("activator_rail"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.ANVIL)
                        .input('I', ModItemTags.Block.IRON_BLOCKS)
                        .input('i', ConventionalItemTags.IRON_INGOTS)
                        .pattern("III")
                        .pattern(" i ")
                        .pattern("iii")
                        .criterion("has_iron_block", this.conditionsFromTag(ModItemTags.Block.IRON_BLOCKS))
                        .offerTo(this.exporter, helper.vanilla("anvil"));

                this.createShaped(RecipeCategory.DECORATIONS, Items.ARMOR_STAND)
                        .input('/', ModItemTags.STICKS)
                        .input('_', Blocks.SMOOTH_STONE_SLAB)
                        .pattern("///")
                        .pattern(" / ")
                        .pattern("/_/")
                        .criterion("has_stone_slab", this.conditionsFromItem(Blocks.SMOOTH_STONE_SLAB))
                        .offerTo(this.exporter, helper.vanilla("armor_stand"));

                this.createShaped(RecipeCategory.COMBAT, Items.ARROW, 4)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.FLINT)
                        .input('Y', Items.FEATHER)
                        .pattern("X")
                        .pattern("#")
                        .pattern("Y")
                        .criterion("has_feather", this.conditionsFromItem(Items.FEATHER))
                        .criterion("has_flint", this.conditionsFromItem(Items.FLINT))
                        .offerTo(this.exporter, helper.vanilla("arrow"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.BLAST_FURNACE)
                        .input('#', Blocks.SMOOTH_STONE)
                        .input('X', Blocks.FURNACE)
                        .input('I', ConventionalItemTags.IRON_INGOTS)
                        .pattern("III")
                        .pattern("IXI")
                        .pattern("###")
                        .criterion("has_smooth_stone", this.conditionsFromItem(Blocks.SMOOTH_STONE))
                        .offerTo(this.exporter, helper.vanilla("blast_furnace"));

                this.createShaped(RecipeCategory.COMBAT, Items.BOW)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.STRING)
                        .pattern(" #X")
                        .pattern("# X")
                        .pattern(" #X")
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .offerTo(this.exporter, helper.vanilla("bow"));

                this.createShaped(RecipeCategory.MISC, Items.BUCKET)
                        .input('#', ConventionalItemTags.IRON_INGOTS)
                        .pattern("# #")
                        .pattern(" # ")
                        .criterion("has_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.vanilla("bucket"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.CAMPFIRE)
                        .input('L', ItemTags.LOGS)
                        .input('S', ModItemTags.STICKS)
                        .input('C', ItemTags.COALS)
                        .pattern(" S ")
                        .pattern("SCS")
                        .pattern("LLL")
                        .criterion("has_stick", this.conditionsFromTag(ModItemTags.STICKS))
                        .criterion("has_coal", this.conditionsFromTag(ItemTags.COALS))
                        .offerTo(this.exporter, helper.vanilla("campfire"));

                this.createShaped(RecipeCategory.BREWING, Blocks.CAULDRON)
                        .input('#', ConventionalItemTags.IRON_INGOTS)
                        .pattern("# #")
                        .pattern("# #")
                        .pattern("###")
                        .criterion("has_water_bucket", this.conditionsFromItem(Items.WATER_BUCKET))
                        .offerTo(this.exporter, helper.vanilla("cauldron"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.CHAIN)
                        .input('I', ConventionalItemTags.IRON_INGOTS)
                        .input('N', ConventionalItemTags.IRON_NUGGETS)
                        .pattern("N")
                        .pattern("I")
                        .pattern("N")
                        .criterion("has_iron_nugget", this.conditionsFromTag(ConventionalItemTags.IRON_NUGGETS))
                        .criterion("has_iron_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.vanilla("chain"));

                this.createShaped(RecipeCategory.TOOLS, Items.COMPASS)
                        .input('#', ConventionalItemTags.IRON_INGOTS)
                        .input('X', Items.REDSTONE)
                        .pattern(" # ")
                        .pattern("#X#")
                        .pattern(" # ")
                        .criterion("has_redstone", this.conditionsFromItem(Items.REDSTONE))
                        .offerTo(this.exporter, helper.vanilla("compass"));

                this.createShaped(RecipeCategory.COMBAT, Items.CROSSBOW)
                        .input('~', Items.STRING)
                        .input('#', Items.STICK)
                        .input('&', Items.IRON_INGOT)
                        .input('$', Blocks.TRIPWIRE_HOOK)
                        .pattern("#&#")
                        .pattern("~$~")
                        .pattern(" # ")
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .criterion("has_iron_ingot", this.conditionsFromItem(Items.IRON_INGOT))
                        .criterion("has_tripwire_hook", this.conditionsFromItem(Blocks.TRIPWIRE_HOOK))
                        .group("crossbows")
                        .offerTo(this.exporter, helper.vanilla("crossbow"));

                this.createShaped(RecipeCategory.TRANSPORTATION, Blocks.DETECTOR_RAIL, 6)
                        .input('R', Items.REDSTONE)
                        .input('#', Blocks.STONE_PRESSURE_PLATE)
                        .input('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("X X")
                        .pattern("X#X")
                        .pattern("XRX")
                        .criterion("has_rail", this.conditionsFromItem(Blocks.RAIL))
                        .offerTo(this.exporter, helper.vanilla("detector_rail"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.DISPENSER)
                        .input('R', Items.REDSTONE)
                        .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                        .input('X', Items.BOW)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("#R#")
                        .criterion("has_bow", this.conditionsFromItem(Items.BOW))
                        .offerTo(this.exporter, helper.vanilla("dispenser"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.DROPPER)
                        .input('R', Items.REDSTONE)
                        .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                        .pattern("###")
                        .pattern("# #")
                        .pattern("#R#")
                        .criterion("has_redstone", this.conditionsFromItem(Items.REDSTONE))
                        .offerTo(this.exporter, helper.vanilla("dropper"));

                this.createShaped(RecipeCategory.TOOLS, Items.FISHING_ROD)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.STRING)
                        .pattern("  #")
                        .pattern(" #X")
                        .pattern("# X")
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .offerTo(this.exporter, helper.vanilla("fishing_rod"));

                this.createShapeless(RecipeCategory.TOOLS, Items.FLINT_AND_STEEL)
                        .input(Items.IRON_INGOT)
                        .input(Items.FLINT)
                        .criterion("has_flint", this.conditionsFromItem(Items.FLINT))
                        .criterion("has_obsidian", this.conditionsFromItem(Blocks.OBSIDIAN))
                        .group("flint_and_steels")
                        .offerTo(this.exporter, helper.vanilla("flint_and_steel"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.GRINDSTONE)
                        .input('I', ModItemTags.STICKS)
                        .input('-', Blocks.STONE_SLAB)
                        .input('#', ItemTags.PLANKS)
                        .pattern("I-I")
                        .pattern("# #")
                        .criterion("has_stone_slab", this.conditionsFromItem(Blocks.STONE_SLAB))
                        .offerTo(this.exporter, helper.vanilla("grindstone"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.HOPPER)
                        .input('C', Blocks.CHEST)
                        .input('I', ConventionalItemTags.IRON_INGOTS)
                        .pattern("I I")
                        .pattern("ICI")
                        .pattern(" I ")
                        .criterion("has_iron_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.vanilla("hopper"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.IRON_BARS, 16)
                        .input('#', ConventionalItemTags.IRON_INGOTS)
                        .pattern("###")
                        .pattern("###")
                        .criterion("has_iron_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.vanilla("iron_bars"));

                this.createShaped(RecipeCategory.DECORATIONS, Items.ITEM_FRAME)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.LEATHER)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("###")
                        .criterion("has_leather", this.conditionsFromItem(Items.LEATHER))
                        .offerTo(this.exporter, helper.vanilla("item_frame"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.LADDER, 3)
                        .input('#', ModItemTags.STICKS)
                        .pattern("# #")
                        .pattern("###")
                        .pattern("# #")
                        .criterion("has_stick", this.conditionsFromItem(Items.STICK))
                        .offerTo(this.exporter, helper.vanilla("ladder"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.LANTERN)
                        .input('#', Items.TORCH)
                        .input('X', ConventionalItemTags.IRON_NUGGETS)
                        .pattern("XXX")
                        .pattern("X#X")
                        .pattern("XXX")
                        .criterion("has_nugget", this.conditionsFromTag(ConventionalItemTags.IRON_NUGGETS))
                        .criterion("has_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.vanilla("lantern"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.LEVER)
                        .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                        .input('X', Items.STICK)
                        .pattern("X")
                        .pattern("#")
                        .criterion("has_cobblestone", this.conditionsFromTag(ItemTags.STONE_CRAFTING_MATERIALS))
                        .offerTo(this.exporter, helper.vanilla("stone_crafting_materials"));

                this.createShaped(RecipeCategory.TRANSPORTATION, Items.MINECART)
                        .input('#', ConventionalItemTags.IRON_INGOTS)
                        .pattern("# #")
                        .pattern("###")
                        .criterion("has_iron_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.vanilla("minecart"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.OBSERVER)
                        .input('Q', Items.QUARTZ)
                        .input('R', Items.REDSTONE)
                        .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                        .pattern("###")
                        .pattern("RRQ")
                        .pattern("###")
                        .criterion("has_quartz", this.conditionsFromItem(Items.QUARTZ))
                        .offerTo(this.exporter, helper.vanilla("observer"));

                this.createShaped(RecipeCategory.DECORATIONS, Items.PAINTING)
                        .input('#', ModItemTags.STICKS)
                        .input('X', ItemTags.WOOL)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("###")
                        .criterion("has_wool", this.conditionsFromTag(ItemTags.WOOL))
                        .offerTo(this.exporter, helper.vanilla("painting"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.PISTON)
                        .input('R', Items.REDSTONE)
                        .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                        .input('T', ItemTags.PLANKS)
                        .input('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("TTT")
                        .pattern("#X#")
                        .pattern("#R#")
                        .criterion("has_redstone", this.conditionsFromItem(Items.REDSTONE))
                        .offerTo(this.exporter, helper.vanilla("piston"));

                this.createShaped(RecipeCategory.TRANSPORTATION, Blocks.POWERED_RAIL, 6)
                        .input('R', Items.REDSTONE)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.GOLD_INGOT)
                        .pattern("X X")
                        .pattern("X#X")
                        .pattern("XRX")
                        .criterion("has_rail", this.conditionsFromItem(Blocks.RAIL))
                        .offerTo(this.exporter, helper.vanilla("powered_rail"));

                this.createShaped(RecipeCategory.TRANSPORTATION, Blocks.RAIL, 16)
                        .input('#', ModItemTags.STICKS)
                        .input('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("X X")
                        .pattern("X#X")
                        .pattern("X X")
                        .criterion("has_minecart", this.conditionsFromItem(Items.MINECART))
                        .offerTo(this.exporter, helper.vanilla("rail"));

                this.createShaped(RecipeCategory.BUILDING_BLOCKS, Blocks.RAW_IRON_BLOCK)
                        .input('#', Items.RAW_IRON)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .criterion("has_raw_iron", this.conditionsFromItem(Items.RAW_IRON))
                        .group("raw_iron_blocks")
                        .offerTo(this.exporter, helper.vanilla("raw_iron_block"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.REDSTONE_TORCH)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.REDSTONE)
                        .pattern("X")
                        .pattern("#")
                        .criterion("has_redstone", this.conditionsFromItem(Items.REDSTONE))
                        .group("torches")
                        .offerTo(this.exporter, helper.vanilla("redstone_torch"));

                this.createShaped(RecipeCategory.TOOLS, Items.SHEARS)
                        .input('#', Items.IRON_INGOT)
                        .pattern(" #")
                        .pattern("# ")
                        .criterion("has_iron_ingot", this.conditionsFromItem(Items.IRON_INGOT))
                        .group("shears")
                        .offerTo(this.exporter, helper.vanilla("shears"));

                this.createShaped(RecipeCategory.COMBAT, Items.SHIELD)
                        .input('W', ItemTags.WOODEN_TOOL_MATERIALS)
                        .input('o', Items.IRON_INGOT)
                        .pattern("WoW")
                        .pattern("WWW")
                        .pattern(" W ")
                        .criterion("has_iron_ingot", this.conditionsFromItem(Items.IRON_INGOT))
                        .group("shields")
                        .offerTo(this.exporter, helper.vanilla("shield"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.SOUL_CAMPFIRE)
                        .input('L', ItemTags.LOGS)
                        .input('S', ModItemTags.STICKS)
                        .input('#', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                        .pattern(" S ")
                        .pattern("S#S")
                        .pattern("LLL")
                        .criterion("has_soul_sand", this.conditionsFromTag(ItemTags.SOUL_FIRE_BASE_BLOCKS))
                        .offerTo(this.exporter, helper.vanilla("soul_campfire"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.SOUL_LANTERN)
                        .input('#', Items.SOUL_TORCH)
                        .input('X', ConventionalItemTags.IRON_NUGGETS)
                        .pattern("XXX")
                        .pattern("X#X")
                        .pattern("XXX")
                        .criterion("has_soul_torch", this.conditionsFromItem(Items.SOUL_TORCH))
                        .offerTo(this.exporter, helper.vanilla("soul_lantern"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.SOUL_TORCH, 4)
                        .input('X', Ingredient.ofItems(Items.COAL, Items.CHARCOAL))
                        .input('#', ModItemTags.STICKS)
                        .input('S', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                        .pattern("X")
                        .pattern("#")
                        .pattern("S")
                        .criterion("has_soul_sand", this.conditionsFromTag(ItemTags.SOUL_FIRE_BASE_BLOCKS))
                        .group("torches")
                        .offerTo(this.exporter, helper.vanilla("soul_torch"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.STONECUTTER)
                        .input('I', ConventionalItemTags.IRON_INGOTS)
                        .input('#', Blocks.STONE)
                        .pattern(" I ")
                        .pattern("###")
                        .criterion("has_stone", this.conditionsFromItem(Blocks.STONE))
                        .offerTo(this.exporter, helper.vanilla("stonecutter"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.TORCH, 4)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Ingredient.ofItems(Items.COAL, Items.CHARCOAL))
                        .pattern("X")
                        .pattern("#")
                        .criterion("has_stone_pickaxe", this.conditionsFromItem(Items.STONE_PICKAXE))
                        .group("torches")
                        .offerTo(this.exporter, helper.vanilla("torch"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.TRIPWIRE_HOOK, 2)
                        .input('#', ItemTags.PLANKS)
                        .input('S', ModItemTags.STICKS)
                        .input('I', ConventionalItemTags.IRON_INGOTS)
                        .pattern("I")
                        .pattern("S")
                        .pattern("#")
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .offerTo(this.exporter, helper.vanilla("tripwire_hook"));

                // SPEEDRUNNER MOD "VANILLA" RECIPES
                this.createShaped(RecipeCategory.MISC, Blocks.OAK_PLANKS)
                        .input('/', Items.STICK)
                        .pattern("//")
                        .pattern("//")
                        .criterion("has_stick", this.conditionsFromItem(Items.STICK))
                        .offerTo(this.exporter, "oak_planks_from_sticks");

                this.createShaped(RecipeCategory.MISC, Blocks.OBSIDIAN)
                        .input('#', ModItems.IGNEOUS_ROCK)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .criterion("has_igneous_rock", this.conditionsFromItem(ModItems.IGNEOUS_ROCK))
                        .offerTo(this.exporter, "obsidian_from_igneous_rocks");

                this.createShapeless(RecipeCategory.MISC, Items.STRING)
                        .input(ItemTags.WOOL)
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .offerTo(this.exporter, "string_from_wool");

                // SPEEDRUNNER MOD ITEM & BLOCK RECIPES

            }
        };
    }

    @Override
    public String getName() {
        return "Speedrunner Mod Recipe Generator";
    }
}