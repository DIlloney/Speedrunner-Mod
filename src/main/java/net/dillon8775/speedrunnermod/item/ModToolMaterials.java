package net.dillon8775.speedrunnermod.item;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    SPEEDRUNNER_SHOVEL_AXE_HOE(2, 500, 11.0F, 0.0F, 17, () -> {
        return Ingredient.ofItems(ModItems.SPEEDRUNNER_INGOT);
    }),
    SPEEDRUNNER_SWORD_PICKAXE(2, 500, 11.0F, 0.5F, 17, () -> {
        return Ingredient.ofItems(ModItems.SPEEDRUNNER_INGOT);
    }),
    GOLDEN_SPEEDRUNNER(0, 72, 13.0F, 0.0F, 25, () -> {
        return Ingredient.ofItems(Items.GOLD_INGOT);
    }),
    WITHER_SWORD(1, 27, 4.0F, 1.5F, 7, () -> {
        return Ingredient.ofItems(ModItems.WITHER_BONE);
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

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeedMultiplier;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
