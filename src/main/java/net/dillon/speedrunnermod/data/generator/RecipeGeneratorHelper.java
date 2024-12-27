package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.List;

/**
 * A helper class to easily create recipes.
 */
public class RecipeGeneratorHelper extends RecipeGenerator {
    private final RegistryEntryLookup<Item> itemLookup;

    protected RecipeGeneratorHelper(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
        this.itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
    }

    /**
     * Creates a smelting, campfire cooking, and smoker recipe.
     */
    protected void createCookableFood(ItemConvertible input, ItemConvertible output, boolean vanilla) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItem(input), RecipeCategory.FOOD, output, 0.35F, 20)
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output)) : getItemPath(output));
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItem(input), RecipeCategory.FOOD, output, 0.35F, 60)
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output)+"_from_campfire_cooking") : getItemPath(output)+"_from_campfire_cooking");
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItem(input), RecipeCategory.FOOD, output, 0.35F, 20)
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output)+"_from_smoking") : getItemPath(output)+"_from_smoking");
    }

    /**
     * Creates a {@code smithing table} recipe.
     */
    public void offerGoldenSpeedrunnerUpgradeRecipe(Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItem(ModItems.GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.ofItem(input),
                        this.ingredientFromTag(ModItemTags.GOLDEN_SPEEDRUNNER_TOOL_MATERIALS),
                        category,
                        result
                )
                .criterion("has_netherite_ingot", this.conditionsFromTag(ModItemTags.GOLDEN_SPEEDRUNNER_TOOL_MATERIALS))
                .offerTo(this.exporter, getItemPath(result) + "_smithing");
    }

    /**
     * Creates a smeltable and blastable material.
     */
    public void offerBurnableMaterial(List<ItemConvertible> inputs, ItemConvertible output, float exp, String group, boolean vanilla) {
        offerNewSmelting(inputs, RecipeCategory.MISC, output, exp, 20, group, vanilla);
        offerNewBlasting(inputs, RecipeCategory.MISC, output, exp, 20, group, vanilla);
    }

    public void offerNewSmelting(List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group, boolean vanilla) {
        this.offerMultipleOptionsH(RecipeSerializer.SMELTING, SmeltingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_smelting", vanilla);
    }

    public void offerNewBlasting(List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group, boolean vanilla) {
        this.offerMultipleOptionsH(RecipeSerializer.BLASTING, BlastingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_blasting", vanilla);
    }

    public final <T extends AbstractCookingRecipe> void offerMultipleOptionsH(RecipeSerializer<T> serializer, AbstractCookingRecipe.RecipeFactory<T> recipeFactory, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group, String suffix, boolean vanilla) {
        for (ItemConvertible itemConvertible : inputs) {
            CookingRecipeJsonBuilder.create(Ingredient.ofItem(itemConvertible), category, output, experience, cookingTime, serializer, recipeFactory)
                    .group(group)
                    .criterion(hasItem(itemConvertible), this.conditionsFromItem(itemConvertible))
                    .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output) + suffix + "_" + getItemPath(itemConvertible)) : getItemPath(output) + suffix + "_" + getItemPath(itemConvertible));
        }
    }

    /**
     * Creates a {@code boat} and {@code chest boat} recipe.
     */
    protected void createBoatSet(ItemConvertible boat, ItemConvertible chestBoat, ItemConvertible planks) {
        this.offerBoatRecipe(boat, planks);
        this.offerChestBoatRecipe(chestBoat, boat);
    }

    /**
     * Creates a {@code sign} recipe.
     */
    protected void createSign(ItemConvertible sign, ItemConvertible plank) {
        this.createShaped(RecipeCategory.DECORATIONS, sign, 3)
                .input('#', plank)
                .input('X', ModItemTags.STICKS)
                .pattern("###")
                .pattern(" X ")
                .criterion("has_plank", this.conditionsFromItem(plank))
                .offerTo(this.exporter);
    }

    protected void createGoldenFoodItem(ItemConvertible input, ItemConvertible output) {
        this.createShaped(RecipeCategory.FOOD, output)
                .input('#', ConventionalItemTags.GOLD_NUGGETS)
                .input('i', input)
                .pattern("###")
                .pattern("#i#")
                .pattern("###")
                .criterion("has_food", this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code fence recipe} with speedrunner sticks.
     */
    public final CraftingRecipeJsonBuilder createModdedFenceRecipe(ItemConvertible output, ItemConvertible input) {
        int i = output == Blocks.NETHER_BRICK_FENCE ? 6 : 3;
        Item item = output == Blocks.NETHER_BRICK_FENCE ? Items.NETHER_BRICK : ModItems.SPEEDRUNNER_STICK;
        return this.createShaped(RecipeCategory.DECORATIONS, output, i).input('W', input).input('#', item).pattern("W#W").pattern("W#W");
    }

    /**
     * Creates a {@code fence gate recipe} with speedrunner sticks.
     */
    public final CraftingRecipeJsonBuilder createModdedFenceGateRecipe(ItemConvertible output, ItemConvertible input) {
        return this.createShaped(RecipeCategory.REDSTONE, output).input('#', ModItems.SPEEDRUNNER_STICK).input('W', input).pattern("#W#").pattern("#W#");
    }

    /**
     * Creates a {@code sword} recipe.
     */
    protected void createSword(TagKey<Item> material, ItemConvertible output, boolean vanilla) {
        this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .criterion("has_material", this.conditionsFromTag(material))
                .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output)) : getItemPath(output));
    }


    /**
     * Creates a {@code shovel} recipe.
     */
    protected void createShovel(TagKey<Item> material, ItemConvertible output, boolean vanilla) {
        this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .criterion("has_material", this.conditionsFromTag(material))
                .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output)) : getItemPath(output));
    }

    /**
     * Creates a {@code pickaxe} recipe.
     */
    protected void createPickaxe(TagKey<Item> material, ItemConvertible output, boolean vanilla) {
        this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .criterion("has_material", this.conditionsFromTag(material))
                .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output)) : getItemPath(output));
    }

    /**
     * Creates an {@code axe} recipe.
     */
    protected void createAxe(TagKey<Item> material, ItemConvertible output, boolean vanilla) {
        this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .criterion("has_material", this.conditionsFromTag(material))
                .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output)) : getItemPath(output));
    }

    /**
     * Creates a {@code hoe} recipe.
     */
    protected void createHoe(TagKey<Item> material, ItemConvertible output, boolean vanilla) {
        this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .criterion("has_material", this.conditionsFromTag(material))
                .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output)) : getItemPath(output));
    }

    /**
     * Creates a {@code helmet} recipe.
     */
    protected void createHelmet(ItemConvertible input, ItemConvertible output) {
        this.createShaped(RecipeCategory.COMBAT, output)
                .input('X', input)
                .pattern("XXX")
                .pattern("X X")
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code chestplate} recipe.
     */
    protected void createChestplate(ItemConvertible input, ItemConvertible output) {
        this.createShaped(RecipeCategory.COMBAT, output)
                .input('X', input)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code leggings} recipe.
     */
    protected void createLeggings(ItemConvertible input, ItemConvertible output) {
        this.createShaped(RecipeCategory.COMBAT, output)
                .input('X', input)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code boot} recipe.
     */
    protected void createBoots(ItemConvertible input, ItemConvertible output) {
        this.createShaped(RecipeCategory.COMBAT, output)
                .input('X', input)
                .pattern("X X")
                .pattern("XXX")
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    /**
     * Returns in the {@code vanilla} folder, without the {@code "minecraft:"} namespace.
     */
    protected String vanillaWithoutNamespace(String str) {
        return vanilla(removeNamespace(str));
    }

    /**
     * Removes the {@code "minecraft:"} when returning a string.
     */
    protected String removeNamespace(String str) {
        return str.substring(10);
    }

    /**
     * Returns the path for a recipe, in the {@code vanilla} folder.
     */
    protected String vanilla(String name) {
        return "vanilla/"+name;
    }

    /**
     * Creates a banner recipe with the {@code Speedrunner Mod sticks tag.}
     */
    @Override
    public void offerBannerRecipe(ItemConvertible output, ItemConvertible inputWool) {
        createShaped(RecipeCategory.DECORATIONS, output)
                .input('#', inputWool)
                .input('|', ModItemTags.STICKS)
                .pattern("###")
                .pattern("###")
                .pattern(" | ")
                .group("banner")
                .criterion(hasItem(inputWool), this.conditionsFromItem(inputWool))
                .offerTo(this.exporter, vanilla(removeNamespace(output.toString())));
    }

    /**
     * This method does absolutely nothing. Just needed to create a functional helper class.
     */
    @Override
    public void generate() {
    }
}