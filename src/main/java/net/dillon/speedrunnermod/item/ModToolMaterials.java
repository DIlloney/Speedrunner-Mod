package net.dillon.speedrunnermod.item;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

/**
 * All Speedrunner Mod {@code tool materials} (for pickaxes, axes, swords, etc.)
 */
public enum ModToolMaterials implements ToolMaterial {
    SPEEDRUNNER_SHOVEL_AXE_HOE(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 11.0F, 0.0F, 17, () -> {
        return Ingredient.ofItems(ModItems.SPEEDRUNNER_INGOT);
    }),
    SPEEDRUNNER_SWORD_PICKAXE(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 11.0F, 0.5F, 17, () -> {
        return Ingredient.ofItems(ModItems.SPEEDRUNNER_INGOT);
    }),
    GOLDEN_SPEEDRUNNER(BlockTags.INCORRECT_FOR_GOLD_TOOL, 72, 13.0F, 0.0F, 25, () -> {
        return Ingredient.ofItems(Items.GOLD_INGOT);
    }),
    WITHER_SWORD(BlockTags.INCORRECT_FOR_STONE_TOOL, 27, 4.0F, 1.5F, 7, () -> {
        return Ingredient.ofItems(ModItems.WITHER_BONE);
    }),
    DRAGONS_SWORD(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2036, 14.0F, 0.0F, 30, () -> {
        return Ingredient.ofItems(ModItems.DRAGONS_PEARL);
    });

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(TagKey<Block> inverseTag, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
