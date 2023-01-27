package net.dilloney.speedrunnermod.util.entity.modifications;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

public class VillagerTradeModifications {

    @Mixin(TradeOffers.SellEnchantedToolFactory.class)
    public static class SellEnchantedToolFactoryMixin {
        @Shadow @Final
        private ItemStack tool;
        @Shadow @Final
        int basePrice;
        @Shadow @Final
        private int maxUses;
        @Shadow @Final
        private int experience;
        @Shadow @Final
        private float multiplier;

        @Overwrite
        public TradeOffer create(Entity entity, Random random) {
            int i = 30 + random.nextInt(33);
            ItemStack itemStack = EnchantmentHelper.enchant(random, new ItemStack(this.tool.getItem()), i, false);
            int j = Math.min(this.basePrice, 12);
            ItemStack itemStack2 = new ItemStack(Items.EMERALD, j);
            return new TradeOffer(itemStack2, itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }
}