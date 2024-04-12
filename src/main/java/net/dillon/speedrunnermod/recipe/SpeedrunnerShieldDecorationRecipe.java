package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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

    @Override
    public boolean matches(CraftingInventory craftingInventory, World world) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;

        for(int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack3 = craftingInventory.getStack(i);
            if (!itemStack3.isEmpty()) {
                if (itemStack3.getItem() instanceof BannerItem) {
                    if (!itemStack2.isEmpty()) {
                        return false;
                    }

                    itemStack2 = itemStack3;
                } else {
                    if (!itemStack3.isOf(ModItems.SPEEDRUNNER_SHIELD)) {
                        return false;
                    }

                    if (!itemStack.isEmpty()) {
                        return false;
                    }

                    if (itemStack3.getSubNbt("BlockEntityTag") != null) {
                        return false;
                    }

                    itemStack = itemStack3;
                }
            }
        }

        return !itemStack.isEmpty() && !itemStack2.isEmpty();
    }

    @Override
    public ItemStack craft(CraftingInventory craftingInventory) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;

        for(int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack3 = craftingInventory.getStack(i);
            if (!itemStack3.isEmpty()) {
                if (itemStack3.getItem() instanceof BannerItem) {
                    itemStack = itemStack3;
                } else if (itemStack3.isOf(ModItems.SPEEDRUNNER_SHIELD)) {
                    itemStack2 = itemStack3.copy();
                }
            }
        }

        if (!itemStack2.isEmpty()) {
            NbtCompound nbtCompound = itemStack.getSubNbt("BlockEntityTag");
            NbtCompound nbtCompound2 = nbtCompound == null ? new NbtCompound() : nbtCompound.copy();
            nbtCompound2.putInt("Base", ((BannerItem) itemStack.getItem()).getColor().getId());
            itemStack2.setSubNbt("BlockEntityTag", nbtCompound2);
        }
        return itemStack2;
    }

    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return SPEEDRUNNER_SHIELD_DECORATION_RECIPE;
    }
}