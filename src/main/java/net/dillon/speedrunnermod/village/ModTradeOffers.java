package net.dillon.speedrunnermod.village;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * {@link net.dillon.speedrunnermod.SpeedrunnerMod} villager trades.
 */
public class ModTradeOffers {

    public static void init() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 1, factories -> {
            factories.add(new TradeOffers.BuyItemFactory(ModItems.SPEEDRUNNER_INGOT, 1, 24, 3));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 1, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.BOOK, 1, 3, 12, 4));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 1, factories -> {
            factories.add(new MaxedEnchantBookFactory(3, 24, 12, 0.2F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 2, factories -> {
            factories.add(new TradeOffers.ProcessItemFactory(Items.COOKED_BEEF, 1, 1, ModItems.GOLDEN_BEEF, 1, 1, 64, 5));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 2, factories -> {
            factories.add(new MaxedEnchantBookFactory(3, 24, 6, 0.0F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 2, factories -> {
            factories.add(new ModTradeOffers.SellItemFactorySpeedrunnerIngot(ModItems.GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE, 2, 1, 9, 6));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 3, factories -> {
            factories.add(new TradeOffers.SellPotionHoldingItemFactory(Items.WATER_BUCKET, 1, Items.SPLASH_POTION, 1, 1, 12, 9));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 3, factories -> {
            factories.add(new MaxedEnchantBookFactory(3, 24, 15, 0.0F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 3, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModItems.ENDER_THRUSTER, 3, 1, 12, 20));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.GOLDEN_APPLE, 4, 3, 21, 10));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.ENCHANTED_GOLDEN_APPLE, 12, 1, 3, 20));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModItems.DRAGONS_PEARL, 3, 1, 12, 25));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 3, 1, 9, 25));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 5, factories -> {
            factories.add(new MaxedEnchantBookFactory(3, 32, 24, 0.0F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 5, factories -> {
            factories.add(new ModTradeOffers.SellMaxedEnchantedNetheriteChestplateFactory(12, 1, 100, 1.35F));
        });

        info("Registered the Retired Speedrunner villager trades.");
    }

    public static class SellItemFactorySpeedrunnerIngot implements TradeOffers.Factory {
        private final ItemStack sell;
        private final int price;
        private final int count;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellItemFactorySpeedrunnerIngot(Item item, int price, int count, int experience) {
            this(new ItemStack(item), price, count, 12, experience);
        }

        public SellItemFactorySpeedrunnerIngot(Item item, int price, int count, int maxUses, int experience) {
            this(new ItemStack(item), price, count, maxUses, experience);
        }

        public SellItemFactorySpeedrunnerIngot(ItemStack stack, int price, int count, int maxUses, int experience) {
            this(stack, price, count, maxUses, experience, 0.0F);
        }

        public SellItemFactorySpeedrunnerIngot(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
            this.sell = stack;
            this.price = price;
            this.count = count;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        @Override
        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(new ItemStack(ModItems.SPEEDRUNNER_INGOT, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class SellItemFactoryIronIngot implements TradeOffers.Factory {
        private final ItemStack sell;
        private final int price;
        private final int count;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellItemFactoryIronIngot(Item item, int price, int count, int experience) {
            this(new ItemStack(item), price, count, 12, experience);
        }

        public SellItemFactoryIronIngot(ItemStack stack, int price, int count, int maxUses, int experience) {
            this(stack, price, count, maxUses, experience, 0.0F);
        }

        public SellItemFactoryIronIngot(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
            this.sell = stack;
            this.price = price;
            this.count = count;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        @Override
        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(new ItemStack(Items.IRON_INGOT, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class MaxedEnchantBookFactory implements TradeOffers.Factory {
        private final int basePrice;
        private final int maxUses;
        private final float priceMultiplier;
        private final int experience;

        public MaxedEnchantBookFactory(int basePrice, int maxUses, int experience, float priceMultiplier) {
            this.basePrice = basePrice;
            this.maxUses = maxUses;
            this.experience = experience;
            this.priceMultiplier = priceMultiplier;
        }

        public TradeOffer create(Entity entity, Random random) {
            List<Enchantment> list = ModEnchantments.availableForRetiredSpeedrunnerTrades();
            Enchantment enchantment = list.get(random.nextInt(list.size()));
            int overMaxLevel = enchantment.getMaxLevel() == 1 ? 0 : 1 + random.nextInt(4);
            int maxLevel = MathHelper.nextInt(random, enchantment.getMaxLevel(), enchantment.getMaxLevel() + overMaxLevel);
            ItemStack itemStack = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, maxLevel));
            int price = this.basePrice;
            if (enchantment.isTreasure()) {
                price += 1;
            }

            if (price > 10) {
                price = 10;
            }

            return new TradeOffer(new ItemStack(Items.EMERALD, price), new ItemStack(Items.BOOK), itemStack, this.maxUses, this.experience, this.priceMultiplier);
        }
    }

    public static class SellMaxedEnchantedToolFactory implements TradeOffers.Factory {
        private final ItemStack tool;
        private final int price;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellMaxedEnchantedToolFactory(Item item, int price, int maxUses, int experience) {
            this(item, price, maxUses, experience, 0.05f);
        }

        public SellMaxedEnchantedToolFactory(Item item, int price, int maxUses, int experience, float multiplier) {
            this.tool = new ItemStack(item);
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        @Override
        public TradeOffer create(Entity entity, Random random) {
            int i = random.nextInt(4) + 30;
            ItemStack itemStack = EnchantmentHelper.enchant(random, new ItemStack(this.tool.getItem()), i, true);
            return new TradeOffer(new ItemStack(Items.EMERALD, this.price), itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class SellMaxedEnchantedNetheriteChestplateFactory implements TradeOffers.Factory {
        private final int price;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellMaxedEnchantedNetheriteChestplateFactory(int price, int maxUses, int experience, float multiplier) {
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        @Override
        public TradeOffer create(Entity entity, Random random) {
            int i = random.nextInt(4) + 1;
            ItemStack itemStack = new ItemStack(i == 1 ? Items.NETHERITE_HELMET : i == 2 ? Items.NETHERITE_CHESTPLATE : i == 3 ? Items.NETHERITE_LEGGINGS : Items.NETHERITE_BOOTS);
            itemStack.addEnchantment(Enchantments.PROTECTION, random.nextInt(3) + 3);
            itemStack.addEnchantment(Enchantments.UNBREAKING, random.nextInt(3) + 3);
            if (random.nextBoolean()) {
                itemStack.addEnchantment(Enchantments.MENDING, 1);
            }
            if (random.nextDouble() < 0.35) {
                itemStack.addEnchantment(Enchantments.THORNS, random.nextInt(3) + 2);
            }
            if (i == 1 && random.nextDouble() < 0.40) {
                itemStack.addEnchantment(Enchantments.RESPIRATION, random.nextInt(2) + 2);
            }
            if (i == 3 && random.nextDouble() < 0.25) {
                itemStack.addEnchantment(Enchantments.SWIFT_SNEAK, random.nextInt(3) + 2);
            }
            if (i == 4) {
                if (random.nextDouble() < 0.40) {
                    itemStack.addEnchantment(ModEnchantments.DASH, random.nextInt(3) + 2);
                }
                if (random.nextDouble() < 0.35) {
                    itemStack.addEnchantment(Enchantments.FEATHER_FALLING, random.nextInt(2) + 3);
                }
            }
            return new TradeOffer(new ItemStack(Items.EMERALD, this.price), itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }
}