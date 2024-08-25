package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.tag.ModEnchantmentTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * Contains all the entries for new or already existing enchantment tags.
 */
public class ModEnchantmentTagGenerator extends FabricTagProvider.EnchantmentTagProvider {

    public ModEnchantmentTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES)
                .add(Enchantments.PROTECTION)
                .add(Enchantments.FEATHER_FALLING)
                .add(Enchantments.THORNS)
                .add(Enchantments.SHARPNESS)
                .add(Enchantments.FIRE_ASPECT)
                .add(Enchantments.LOOTING)
                .add(Enchantments.FORTUNE)
                .add(Enchantments.POWER)
                .add(Enchantments.INFINITY)
                .add(Enchantments.MENDING);
    }
}