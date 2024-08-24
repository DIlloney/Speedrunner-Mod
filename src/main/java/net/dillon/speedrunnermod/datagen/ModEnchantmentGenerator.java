package net.dillon.speedrunnermod.datagen;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

/**
 * Used to create the JSON files for the Speedrunner Mod enchantments.
 */
public class ModEnchantmentGenerator extends FabricDynamicRegistryProvider {

    public ModEnchantmentGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        RegistryWrapper<Item> itemLookup = registries.getWrapperOrThrow(RegistryKeys.ITEM);

        register(entries, ModEnchantments.DASH, Enchantment.builder(
                Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE), // The items that this enchantment can be applied to
                        11, // Probability of appearing in the enchantment table
                        3, // Maximum enchantment level
                        Enchantment.leveledCost(1, 1), // Cost per level (base)
                        Enchantment.leveledCost(1, 3), // Cost per level (maximum)
                        7, // Anvil applying cost
                        AttributeModifierSlot.FEET)));

        register(entries, ModEnchantments.COOLDOWN, Enchantment.builder(
                        Enchantment.definition(
                                itemLookup.getOrThrow(ModItemTags.COOLDOWN_ENCHANTMENT_ITEMS),
                                10,
                                3,
                                Enchantment.leveledCost(1, 1),
                                Enchantment.leveledCost(1, 3),
                                5,
                                AttributeModifierSlot.HAND)));
    }

    @Author(Authors.TURTYWURTY)
    private static void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.getValue()), resourceConditions);
    }

    @Override
    public String getName() {
        return "Speedrunner Mod Enchantments";
    }
}