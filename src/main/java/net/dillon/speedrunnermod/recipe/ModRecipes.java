package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * All Speedrunner Mod {@code custom recipes.}
 */
public class ModRecipes {

    /**
     * Initializes all Speedrunner Mod {@code custom recipes.}
     */
    public static void registerCustomRecipes() {
        register("crafting_special_speedrunner_shield_decoration", SpeedrunnerShieldDecorationRecipe.SPEEDRUNNER_SHIELD_DECORATION_RECIPE);
    }

    /**
     * Registers a {@code special crafting recipe.}
     */
    private static void register(String path, SpecialRecipeSerializer<?> specialCraftingRecipe) {
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(SpeedrunnerMod.MOD_ID, path), specialCraftingRecipe);
    }
}