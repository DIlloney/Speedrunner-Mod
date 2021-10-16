package net.dilloney.speedrunnermod.util;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.block.ModBlocks;
import net.dilloney.speedrunnermod.client.render.SpeedrunnerShieldRenderer;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.option.OptionsFileManager;
import net.dilloney.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe;
import net.dilloney.speedrunnermod.tag.ModBlockTags;
import net.dilloney.speedrunnermod.tag.ModItemTags;
import net.dilloney.speedrunnermod.util.timer.TickHandler;
import net.dilloney.speedrunnermod.util.timer.data.DataStorage;
import net.dilloney.speedrunnermod.world.gen.feature.ModConfiguredFeatures;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.io.File;

public class ModRegistry {

    public static void loadOptions() {
        OptionsFileManager.loadAll();
    }

    public static void loadItems() {
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_ingot"), ModItems.SPEEDRUNNER_INGOT);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_nugget"), ModItems.SPEEDRUNNER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_sword"), ModItems.SPEEDRUNNER_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shovel"), ModItems.SPEEDRUNNER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_pickaxe"), ModItems.SPEEDRUNNER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_axe"), ModItems.SPEEDRUNNER_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_hoe"), ModItems.SPEEDRUNNER_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_helmet"), ModItems.SPEEDRUNNER_HELMET);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_chestplate"), ModItems.SPEEDRUNNER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_leggings"), ModItems.SPEEDRUNNER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_boots"), ModItems.SPEEDRUNNER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_bow"), ModItems.SPEEDRUNNER_BOW);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_crossbow"), ModItems.SPEEDRUNNER_CROSSBOW);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shears"), ModItems.SPEEDRUNNER_SHEARS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_flint_and_steel"), ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shield"), ModItems.SPEEDRUNNER_SHIELD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_bulk"), ModItems.SPEEDRUNNER_BULK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "rotten_speedrunner_bulk"), ModItems.ROTTEN_SPEEDRUNNER_BULK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "igneous_rock"), ModItems.IGNEOUS_ROCK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_sword"), ModItems.GOLDEN_SPEEDRUNNER_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_shovel"), ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_pickaxe"), ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_axe"), ModItems.GOLDEN_SPEEDRUNNER_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_hoe"), ModItems.GOLDEN_SPEEDRUNNER_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_helmet"), ModItems.GOLDEN_SPEEDRUNNER_HELMET);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_chestplate"), ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_leggings"), ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "golden_speedrunner_boots"), ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "cooked_piglin_pork"), ModItems.COOKED_PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "piglin_pork"), ModItems.PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "cooked_flesh"), ModItems.COOKED_FLESH);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "inferno_eye"), ModItems.INFERNO_EYE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "annul_eye"), ModItems.ANNUL_EYE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_block"), ModItems.SPEEDRUNNER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_ore"), ModItems.SPEEDRUNNER_ORE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "nether_speedrunner_ore"), ModItems.NETHER_SPEEDRUNNER_ORE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "igneous_ore"), ModItems.IGNEOUS_ORE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "nether_igneous_ore"), ModItems.NETHER_IGNEOUS_ORE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_sword"), ModItems.ANDESITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_shovel"), ModItems.ANDESITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_pickaxe"), ModItems.ANDESITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_axe"), ModItems.ANDESITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_hoe"), ModItems.ANDESITE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_sword"), ModItems.BLACKSTONE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_shovel"), ModItems.BLACKSTONE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_pickaxe"), ModItems.BLACKSTONE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_axe"), ModItems.BLACKSTONE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_hoe"), ModItems.BLACKSTONE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_sword"), ModItems.DIORITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_shovel"), ModItems.DIORITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_pickaxe"), ModItems.DIORITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_axe"), ModItems.DIORITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_hoe"), ModItems.DIORITE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_sword"), ModItems.GRANITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_shovel"), ModItems.GRANITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_pickaxe"), ModItems.GRANITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_axe"), ModItems.GRANITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_hoe"), ModItems.GRANITE_HOE);
    }

    public static void loadBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_block"), ModBlocks.SPEEDRUNNER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_ore"), ModBlocks.SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_speedrunner_ore"), ModBlocks.NETHER_SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "igneous_ore"), ModBlocks.IGNEOUS_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_igneous_ore"), ModBlocks.NETHER_IGNEOUS_ORE);
    }

    public static void loadConfiguredFeatures() {
        RegistryKey<ConfiguredFeature<?, ?>> speedrunnerOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "speedrunner_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, speedrunnerOre.getValue(), ModConfiguredFeatures.SPEEDRUNNER_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> netherSpeedrunnerOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "nether_speedrunner_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, netherSpeedrunnerOre.getValue(), ModConfiguredFeatures.NETHER_SPEEDRUNNER_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> igneousOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "igneous_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, igneousOre.getValue(), ModConfiguredFeatures.IGNEOUS_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> netherIgneousOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "nether_igneous_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, netherIgneousOre.getValue(), ModConfiguredFeatures.NETHER_IGNEOUS_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> diamondOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "diamond_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, diamondOre.getValue(), ModConfiguredFeatures.DIAMOND_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> ancientDebris = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "ancient_debris_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ancientDebris.getValue(), ModConfiguredFeatures.ANCIENT_DEBRIS);
    }

    public static void loadItemTags() {
        ModItemTags.IRON_INGOTS = TagRegistry.item(new Identifier("speedrunnermod", "iron_ingots"));
        ModItemTags.IRON_NUGGETS = TagRegistry.item(new Identifier("speedrunnermod", "iron_nuggets"));
        ModItemTags.IRON_BLOCKS = TagRegistry.item(new Identifier("speedrunnermod", "iron_blocks"));
        ModItemTags.COBBLESTONES = TagRegistry.item(new Identifier("speedrunnermod", "cobblestones"));
    }

    public static void loadBlockTags() {
        ModBlockTags.ZERO_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0_hardness"));
        ModBlockTags.ZERO_ONE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-1_hardness"));
        ModBlockTags.ZERO_TWO_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-2_hardness"));
        ModBlockTags.ZERO_THREESEVEN_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-37_hardness"));
        ModBlockTags.ZERO_FOUR_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-4_hardness"));
        ModBlockTags.ZERO_FIVE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-5_hardness"));
        ModBlockTags.ZERO_SIX_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-6_hardness"));
        ModBlockTags.ZERO_SIXFIVE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-65_hardness"));
        ModBlockTags.ZERO_SEVEN_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-7_hardness"));
        ModBlockTags.ZERO_EIGHT_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-8_hardness"));
        ModBlockTags.ONE_ZERO_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/1-0_hardness"));
        ModBlockTags.ONE_THREE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/1-3_hardness"));
        ModBlockTags.ONE_FOUR_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/1-4_hardness"));
        ModBlockTags.ONE_FIVE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/1-5_hardness"));
        ModBlockTags.ONE_SIX_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/1-6_hardness"));
        ModBlockTags.TWO_ZERO_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/2-0_hardness"));
        ModBlockTags.TWO_FIVE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/2-5_hardness"));
        ModBlockTags.THREE_ZERO_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/3-0_hardness"));
        ModBlockTags.TEN_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/10_hardness"));
        ModBlockTags.TWENTY_FIVE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/25_hardness"));
    }

    public static void loadMiscellaneous() {
        Registry.register(Registry.RECIPE_SERIALIZER, "speedrunnermod:crafting_special_speedrunner_shield_decoration", SpeedrunnerShieldDecorationRecipe.SPEEDRUNNER_SHIELD_DECORATION_RECIPE);
        loadUniqueItems();
    }

    private static void loadUniqueItems() {
        UniqueItemRegistry.SHEARS.addItemToRegistry(ModItems.SPEEDRUNNER_SHEARS);
        UniqueItemRegistry.SHEARS.addItemToRegistry(Items.SHEARS);
        UniqueItemRegistry.SHIELD.addItemToRegistry(ModItems.SPEEDRUNNER_SHIELD);
        UniqueItemRegistry.SHIELD.addItemToRegistry(Items.SHIELD);
        UniqueItemRegistry.BOW.addItemToRegistry(ModItems.SPEEDRUNNER_BOW);
        UniqueItemRegistry.BOW.addItemToRegistry(Items.BOW);
        UniqueItemRegistry.CROSSBOW.addItemToRegistry(ModItems.SPEEDRUNNER_CROSSBOW);
        UniqueItemRegistry.CROSSBOW.addItemToRegistry(Items.CROSSBOW);
        UniqueItemRegistry.FLINT_AND_STEEL.addItemToRegistry(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        UniqueItemRegistry.FLINT_AND_STEEL.addItemToRegistry(Items.FLINT_AND_STEEL);
        UniqueItemRegistry.TNT_BLOCK_IGNITERS.addItemToRegistry(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        UniqueItemRegistry.TNT_BLOCK_IGNITERS.addItemToRegistry(Items.FLINT_AND_STEEL);
        UniqueItemRegistry.TNT_BLOCK_IGNITERS.addItemToRegistry(Items.FIRE_CHARGE);
    }

    @Environment(EnvType.CLIENT)
    public static void loadTimer() {
        if (SpeedrunnerMod.OPTIONS.timer) {
            OptionsFileManager.TimerFileManager.loadTimer();
            MinecraftClient client = MinecraftClient.getInstance();
            File configDir = FabricLoader.getInstance().getConfigDir().toFile();
            DataStorage store = DataStorage.of(new File(configDir, "speedrunnermod-config_storage.json"));
            store.refreshBests("");
            TickHandler tickHandler = new TickHandler(client, store, OptionsFileManager.TimerFileManager.of(configDir));
            HudRenderCallback.EVENT.register((__, ___) -> tickHandler.tick());
        }
    }

    @Environment(EnvType.CLIENT)
    public static void loadModels() {
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW, new Identifier("pull"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getActiveItem() != itemStack ? 0.0F : (float)(itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
            }
        });
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW, new Identifier("pulling"), (itemStack, clientWorld, livingEntity) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("pull"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return CrossbowItem.isCharged(itemStack) ? 0.0F : (float)(itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(itemStack);
            }
        });
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("pulling"), (itemStack, clientWorld, livingEntity) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack && !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F;
        });
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("charged"), (itemStack, clientWorld, livingEntity) -> {
            return livingEntity != null && CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F;
        });
        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, new Identifier("firework"), (itemStack, clientWorld, livingEntity) -> {
            return livingEntity != null && CrossbowItem.isCharged(itemStack) && CrossbowItem.hasProjectile(itemStack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });

        FabricModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_SHIELD, new Identifier("blocking"), (itemStack, clientWorld, livingEntity) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });

        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SPEEDRUNNER_SHIELD, new SpeedrunnerShieldRenderer());

        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlaxTexture, registry) -> {
            if (atlaxTexture.getId() == SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE) {
                registry.register(new Identifier("speedrunnermod:entity/speedrunner_shield_base"));
            }
        });
    }
}
