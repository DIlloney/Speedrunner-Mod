package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
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
public class ModRecipeGeneratorHelper extends RecipeGenerator {
    private final RegistryEntryLookup<Item> itemLookup;

    protected ModRecipeGeneratorHelper(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
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
     * Creates a smeltable and blastable material.
     */
    public void offerBurnableMaterial(List<ItemConvertible> inputs, ItemConvertible output, int exp, String group, boolean vanilla) {
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