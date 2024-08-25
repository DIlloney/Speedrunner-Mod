package net.dillon.speedrunnermod.village;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModEnchantmentTags;
import net.dillon.speedrunnermod.util.ItemUtil;
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
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;

import java.util.Optional;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * Contains the Retired Speedrunner's trades and offers.
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
            factories.add(new MaxedEnchantBookFactory(3, 4, 0.2F, 12, ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 2, factories -> {
            factories.add(new TradeOffers.ProcessItemFactory(Items.COOKED_BEEF, 1, 1, ModItems.GOLDEN_BEEF, 1, 24, 5, 0.2F));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 2, factories -> {
            factories.add(new MaxedEnchantBookFactory(3, 6, 0.0F, 12, ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 2, factories -> {
            factories.add(new ModTradeOffers.SellItemFactorySpeedrunnerIngot(ModItems.GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE, 2, 1, 12, 5));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 3, factories -> {
            factories.add(new TradeOffers.SellPotionHoldingItemFactory(Items.WATER_BUCKET, 1, Items.SPLASH_POTION, 1, 1, 12, 9));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 3, factories -> {
            factories.add(new MaxedEnchantBookFactory(3, 10, 0.0F, 16, ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 3, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModItems.ENDER_THRUSTER, 3, 1, 12, 9));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.GOLDEN_APPLE, 4, 3, 16, 12));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.ENCHANTED_GOLDEN_APPLE, 12, 1, 3, 14));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModItems.DRAGONS_PEARL, 3, 1, 12, 12));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModItems.INFINI_PEARL, 24, 1, 1, 14));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 3, 1, 9, 15));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 5, factories -> {
            factories.add(new MaxedEnchantBookFactory(3, 20, 0.0F, 24, ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER, 5, factories -> {
            factories.add(new ModTradeOffers.SellMaxedEnchantedNetheriteChestplateFactory(12, 3, 25, 1.35F));
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
            return new TradeOffer(new TradedItem(ModItems.SPEEDRUNNER_INGOT, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
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
            return new TradeOffer(new TradedItem(Items.IRON_INGOT, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class MaxedEnchantBookFactory implements TradeOffers.Factory {
        private final int basePrice;
        private final int experience;
        private final float priceMultiplier;
        private final int maxUses;
        private final TagKey<Enchantment> possibleEnchantments;

        public MaxedEnchantBookFactory(int basePrice, int experience, float priceMultiplier, int maxUses, TagKey<Enchantment> possibleEnchantments) {
            this.basePrice = basePrice;
            this.experience = experience;
            this.priceMultiplier = priceMultiplier;
            this.maxUses = maxUses;
            this.possibleEnchantments = possibleEnchantments;
        }

        public TradeOffer create(Entity entity, Random random) {
            int l;
            ItemStack itemStack;
            Optional<RegistryEntry<Enchantment>> optional = entity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getRandomEntry(this.possibleEnchantments, random);
            if (!optional.isEmpty()) {
                RegistryEntry<Enchantment> registryEntry = optional.get();
                Enchantment enchantment = registryEntry.value();
                itemStack = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(registryEntry, enchantment.getMaxLevel()));
                l = this.basePrice;

                if (l > 10) {
                    l = 10;
                }
            } else {
                l = 1;
                itemStack = new ItemStack(Items.BOOK);
            }
            return new TradeOffer(new TradedItem(Items.EMERALD, l), Optional.of(new TradedItem(Items.BOOK)), itemStack, this.maxUses, this.experience, this.priceMultiplier);
        }
    }

    public static class SellMaxedEnchantedToolFactory implements TradeOffers.Factory {
        private final ItemStack tool;
        private final int price;
        private final int maxUses;
        private final int experience;
        private final float multiplier;
        private final TagKey<Enchantment> possibleEnchantments;

        public SellMaxedEnchantedToolFactory(Item item, int price, int maxUses, int experience, TagKey<Enchantment> possibleEnchantments) {
            this(item, price, maxUses, experience, 0.05F, possibleEnchantments);
        }

        public SellMaxedEnchantedToolFactory(Item item, int price, int maxUses, int experience, float multiplier, TagKey<Enchantment> possibleEnchantments) {
            this.tool = new ItemStack(item);
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
            this.possibleEnchantments = possibleEnchantments;
        }

        @Override
        public TradeOffer create(Entity entity, Random random) {
            int i = random.nextInt(4) + 30;
            DynamicRegistryManager dynamicRegistryManager = entity.getWorld().getRegistryManager();
            Optional<RegistryEntryList.Named<Enchantment>> optional = dynamicRegistryManager.get(RegistryKeys.ENCHANTMENT).getEntryList(this.possibleEnchantments);
            ItemStack itemStack = EnchantmentHelper.enchant(random, new ItemStack(this.tool.getItem()), i, dynamicRegistryManager, optional);
            return new TradeOffer(new TradedItem(Items.EMERALD, this.price), itemStack, this.maxUses, this.experience, this.multiplier);
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
            itemStack.addEnchantment(ItemUtil.enchantment(entity, Enchantments.PROTECTION), random.nextInt(3) + 3);
            itemStack.addEnchantment(ItemUtil.enchantment(entity, Enchantments.UNBREAKING), random.nextInt(3) + 3);
            if (random.nextBoolean()) {
                itemStack.addEnchantment(ItemUtil.enchantment(entity, Enchantments.MENDING), 1);
            }
            if (random.nextDouble() < 0.35) {
                itemStack.addEnchantment(ItemUtil.enchantment(entity, Enchantments.THORNS), random.nextInt(3) + 2);
            }
            if (i == 1 && random.nextDouble() < 0.40) {
                itemStack.addEnchantment(ItemUtil.enchantment(entity, Enchantments.RESPIRATION), random.nextInt(2) + 2);
            }
            if (i == 3 && random.nextDouble() < 0.25) {
                itemStack.addEnchantment(ItemUtil.enchantment(entity, Enchantments.SWIFT_SNEAK), random.nextInt(3) + 2);
            }
            if (i == 4) {
                if (random.nextDouble() < 0.40) {
                    itemStack.addEnchantment(ItemUtil.enchantment(entity, ModEnchantments.DASH), random.nextInt(3) + 2);
                }
                if (random.nextDouble() < 0.35) {
                    itemStack.addEnchantment(ItemUtil.enchantment(entity, Enchantments.FEATHER_FALLING), random.nextInt(2) + 3);
                }
            }
            return new TradeOffer(new TradedItem(Items.EMERALD, this.price), itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }
}