package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

public class ModRecipes {

    public static void init() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(SpeedrunnerMod.MOD_ID, "crafting_special_speedrunner_shield_decoration"), SpeedrunnerShieldDecorationRecipe.SPEEDRUNNER_SHIELD_DECORATION_RECIPE);

        info("Registered speedrunner shield decoration recipe.");
    }
}