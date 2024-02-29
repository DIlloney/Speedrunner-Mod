package net.dillon8775.speedrunnermod.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.tag.ItemTags;

/**
 * <p>The {@link net.dillon8775.speedrunnermod.SpeedrunnerMod} shield, which has a faster cooldown, and more durability.</p>
 * <p>See {@link net.dillon8775.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe} and {@link net.dillon8775.speedrunnermod.client.render.SpeedrunnerShieldRenderer} for more.</p>
 */
public class SpeedrunnerShieldItem extends ShieldItem {

    public SpeedrunnerShieldItem(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(ModItems.SPEEDRUNNER_INGOT) || ingredient.isIn(ItemTags.PLANKS) || super.canRepair(stack, ingredient);
    }
}