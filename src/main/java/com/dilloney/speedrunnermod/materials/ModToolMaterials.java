package com.dilloney.speedrunnermod.materials;

import com.dilloney.speedrunnermod.items.ModItems;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    ANDESITE_EXCLUDING_SHOVEL(1, 131, 4.0F, 0.0F, 5, () -> {
        return Ingredient.ofItems(Items.ANDESITE);
    }),
    ANDESITE_SHOVEL(1, 131, 4.0F, 0.5F, 5, () -> {
        return Ingredient.ofItems(Items.ANDESITE);
    }),
    BLACKSTONE_EXCLUDING_SHOVEL(1, 131, 4.0F, 0.0F, 5, () -> {
        return Ingredient.ofItems(Items.BLACKSTONE);
    }),
    BLACKSTONE_SHOVEL(1, 131, 4.0F, 0.5F, 5, () -> {
        return Ingredient.ofItems(Items.BLACKSTONE);
    }),
    DIORITE_EXCLUDING_SHOVEL(1, 131, 4.0F, 0.0F, 5, () -> {
        return Ingredient.ofItems(Items.DIORITE);
    }),
    DIORITE_SHOVEL(1, 131, 4.0F, 0.5F, 5, () -> {
        return Ingredient.ofItems(Items.DIORITE);
    }),
    GRANITE_EXCLUDING_SHOVEL(1, 131, 4.0F, 0.0F, 5, () -> {
        return Ingredient.ofItems(Items.GRANITE);
    }),
    GRANITE_SHOVEL(1, 131, 4.0F, 0.5F, 5, () -> {
        return Ingredient.ofItems(Items.GRANITE);
    }),
    SPEEDRUNNER_SHOVEL_AXE_HOE(2, 500, 11.0F, 0.0F, 11, () -> {
        return Ingredient.ofItems(ModItems.SPEEDRUNNER_INGOT);
    }),
    SPEEDRUNNER_SWORD_PICKAXE(2, 500, 11.0F, 0.5F, 11, () -> {
        return Ingredient.ofItems(ModItems.SPEEDRUNNER_INGOT);
    });

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeedMultiplier;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    ModToolMaterials(int miningLevel, int itemDurability, float miningSpeedMultiplier, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<>(repairIngredient);
    }

    @Override
    public int getDurability() { return this.itemDurability; }

    @Override
    public float getMiningSpeedMultiplier() { return this.miningSpeedMultiplier; }

    @Override
    public float getAttackDamage() { return this.attackDamage; }

    @Override
    public int getMiningLevel() { return this.miningLevel; }

    @Override
    public int getEnchantability() { return this.enchantability; }

    @Override
    public Ingredient getRepairIngredient() { return this.repairIngredient.get(); }
}
