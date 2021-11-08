package net.dilloney.speedrunnermod.misc.recipe;

import net.dilloney.speedrunnermod.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SpeedrunnerShieldDecorationRecipe extends SpecialCraftingRecipe {
    public static SpecialRecipeSerializer<SpeedrunnerShieldDecorationRecipe> SPEEDRUNNER_SHIELD_DECORATION_RECIPE = new SpecialRecipeSerializer<>(SpeedrunnerShieldDecorationRecipe::new);

    public SpeedrunnerShieldDecorationRecipe(Identifier identifier) {
        super(identifier);
    }

    public boolean matches(CraftingInventory craftingInventory, World world) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;

        for (int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack3 = craftingInventory.getStack(i);
            if (!itemStack3.isEmpty()) {
                if (itemStack3.getItem() instanceof BannerItem) {
                    if (!itemStack2.isEmpty()) {
                        return false;
                    }

                    itemStack2 = itemStack3;
                } else {
                    if (!(itemStack3.getItem() == ModItems.SPEEDRUNNER_SHIELD)) {
                        return false;
                    }

                    if (!itemStack.isEmpty()) {
                        return false;
                    }

                    if (itemStack3.getSubTag("BlockEntityTag") != null) {
                        return false;
                    }

                    itemStack = itemStack3;
                }
            }
        }

        return !itemStack.isEmpty() && !itemStack2.isEmpty();
    }

    public ItemStack craft(CraftingInventory craftingInventory) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;

        for (int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack3 = craftingInventory.getStack(i);
            if (!itemStack3.isEmpty()) {
                if (itemStack3.getItem() instanceof BannerItem) {
                    itemStack = itemStack3;
                } else if (itemStack3.getItem() == ModItems.SPEEDRUNNER_SHIELD) {
                    itemStack2 = itemStack3.copy();
                }
            }
        }

        if (!itemStack2.isEmpty()) {
            CompoundTag compoundTag = itemStack.getSubTag("BlockEntityTag");
            CompoundTag compoundTag2 = compoundTag == null ? new CompoundTag() : compoundTag.copy();
            compoundTag2.putInt("Base", ((BannerItem) itemStack.getItem()).getColor().getId());
            itemStack2.putSubTag("BlockEntityTag", compoundTag2);
        }
        return itemStack2;
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return SPEEDRUNNER_SHIELD_DECORATION_RECIPE;
    }
}