package net.dillon8775.speedrunnermod.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ModTradeOffers {

    public static class MaxedEnchantBookFactory implements TradeOffers.Factory {
        private final int experience;

        public MaxedEnchantBookFactory(int experience) {
            this.experience = experience;
        }

        public TradeOffer create(Entity entity, Random random) {
            List<Enchantment> list = (List) Registry.ENCHANTMENT.stream().filter(Enchantment::isAvailableForEnchantedBookOffer).collect(Collectors.toList());
            Enchantment enchantment = (Enchantment)list.get(random.nextInt(list.size()));
            int i = MathHelper.nextInt(random, enchantment.getMaxLevel(), enchantment.getMaxLevel());
            ItemStack itemStack = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, i));
            int l = 3 + random.nextInt(5) * i;
            if (enchantment.isTreasure()) {
                l *= 2;
            }

            if (l > 64) {
                l = 64;
            }

            return new TradeOffer(new ItemStack(Items.EMERALD, l), new ItemStack(Items.BOOK), itemStack, 24, this.experience, 0.10F);
        }
    }

    /*
    Unused, but don't want to delete the code.

    public static class EnchantBookFactory implements TradeOffers.Factory {
        private final int experience;

        public EnchantBookFactory(int experience) {
            this.experience = experience;
        }

        public TradeOffer create(Entity entity, Random random) {
            List<Enchantment> list = (List) Registry.ENCHANTMENT.stream().filter(Enchantment::isAvailableForEnchantedBookOffer).collect(Collectors.toList());
            Enchantment enchantment = (Enchantment)list.get(random.nextInt(list.size()));
            int i = MathHelper.nextInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel());
            ItemStack itemStack = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, i));
            int l = 3 + random.nextInt(5) * i;
            if (enchantment.isTreasure()) {
                l *= 2;
            }

            if (l > 64) {
                l = 64;
            }

            return new TradeOffer(new ItemStack(Items.EMERALD, l), new ItemStack(Items.BOOK), itemStack, 24, this.experience, 0.10F);
        }
    } */
}