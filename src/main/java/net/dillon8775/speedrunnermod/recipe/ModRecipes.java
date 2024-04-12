<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.recipe;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

public class ModRecipes {

    public static void init() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(SpeedrunnerMod.MOD_ID, "crafting_special_speedrunner_shield_decoration"), SpeedrunnerShieldDecorationRecipe.SPEEDRUNNER_SHIELD_DECORATION_RECIPE);

        info("Initialized custom recipes.");
    }
=======
package net.dillon8775.speedrunnermod.recipe;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

public class ModRecipes {

    public static void init() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(SpeedrunnerMod.MOD_ID, "crafting_special_speedrunner_shield_decoration"), SpeedrunnerShieldDecorationRecipe.SPEEDRUNNER_SHIELD_DECORATION_RECIPE);

        info("Initialized custom recipes.");
    }
>>>>>>> Stashed changes
}