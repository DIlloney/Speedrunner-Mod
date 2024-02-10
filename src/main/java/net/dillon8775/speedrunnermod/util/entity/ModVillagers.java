package net.dillon8775.speedrunnermod.util.entity;

import com.google.common.collect.ImmutableSet;
import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.util.ModTradeOffers;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.mixin.object.builder.PointOfInterestTypeAccessor;
import net.fabricmc.fabric.mixin.object.builder.VillagerProfessionAccessor;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

public class ModVillagers {
    public static final PointOfInterestType RETIRED_SPEEDRUNNER_POI = registerPOI("speedrunner_poi", ModBlocks.SPEEDRUNNERS_WORKBENCH);
    public static final VillagerProfession RETIRED_SPEEDRUNNER = registerProfessions(RETIRED_SPEEDRUNNER_POI);

    public static VillagerProfession registerProfessions(PointOfInterestType type) {
        return Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("speedrunnermod", "retired_speedrunner"), VillagerProfessionAccessor.create("retired_speedrunner", type, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_ARMORER));
    }

    public static PointOfInterestType registerPOI(String name, Block block) {
        return Registry.register(Registry.POINT_OF_INTEREST_TYPE, new Identifier("speedrunnermod", name), PointOfInterestTypeAccessor.callCreate(name, ImmutableSet.copyOf(block.getStateManager().getStates()), 3, 1));
    }

    public static void init() {
        TradeOfferHelper.registerVillagerOffers(RETIRED_SPEEDRUNNER, 1, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(ModItems.SPEEDRUNNER_INGOT, 1, 24, 3));
        });
        TradeOfferHelper.registerVillagerOffers(RETIRED_SPEEDRUNNER, 1, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.BOOK, 1, 3, 12, 4));
        });
        TradeOfferHelper.registerVillagerOffers(RETIRED_SPEEDRUNNER, 1, factories -> {
            factories.add(new ModTradeOffers.MaxedEnchantBookFactory(5));
        });
        TradeOfferHelper.registerVillagerOffers(RETIRED_SPEEDRUNNER, 2, factories -> {
            factories.add(new TradeOffers.ProcessItemFactory(Items.COOKED_BEEF, 1, ModItems.GOLDEN_BEEF, 1, 64, 5));
        });
        TradeOfferHelper.registerVillagerOffers(RETIRED_SPEEDRUNNER, 2, factories -> {
            factories.add(new ModTradeOffers.MaxedEnchantBookFactory(7));
        });
        TradeOfferHelper.registerVillagerOffers(RETIRED_SPEEDRUNNER, 3, factories -> {
            factories.add(new TradeOffers.SellPotionHoldingItemFactory(Items.WATER_BUCKET, 1, Items.SPLASH_POTION, 1, 1, 12, 9));
        });
        TradeOfferHelper.registerVillagerOffers(RETIRED_SPEEDRUNNER, 3, factories -> {
            factories.add(new ModTradeOffers.MaxedEnchantBookFactory(10));
        });
        TradeOfferHelper.registerVillagerOffers(RETIRED_SPEEDRUNNER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.GOLDEN_APPLE, 4, 3, 21, 10));
        });
        TradeOfferHelper.registerVillagerOffers(RETIRED_SPEEDRUNNER, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.ENCHANTED_GOLDEN_APPLE, 12, 1, 3, 20));
        });
        TradeOfferHelper.registerVillagerOffers(RETIRED_SPEEDRUNNER, 5, factories -> {
            factories.add(new ModTradeOffers.MaxedEnchantBookFactory(20));
        });
        TradeOfferHelper.registerVillagerOffers(RETIRED_SPEEDRUNNER, 5, factories -> {
            factories.add(new TradeOffers.SellEnchantedToolFactory(Items.NETHERITE_CHESTPLATE, 24, 1, 100, 2));
        });
        PointOfInterestTypeAccessor.callSetup(RETIRED_SPEEDRUNNER_POI);

        info("Initialized villager trades.");
    }
}