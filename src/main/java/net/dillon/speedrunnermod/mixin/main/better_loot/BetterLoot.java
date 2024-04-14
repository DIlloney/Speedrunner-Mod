package net.dillon.speedrunnermod.mixin.main.better_loot;

import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ApplyBonusLootFunction.OreDrops.class)
public class BetterLoot {

    /**
     * Applies better loot drops from ores.
     */
    @Overwrite
    public int getValue(Random random, int initialCount, int enchantmentLevel) {
        return enchantmentLevel > 0 ? initialCount * (enchantmentLevel + 1) : initialCount;
    }
}