package net.dillon.speedrunnermod.tag;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code item tags.}
 */
public class ModItemTags {
    public static TagKey<Item> BOWS = of("bows");
    public static TagKey<Item> COOLDOWN_ENCHANTMENT_ITEMS = of("cooldown_enchantment_items");
    public static TagKey<Item> CROSSBOWS = of("crossbows");
    public static TagKey<Item> DOOM_STONE_SAFE_TOOLS = of("doom_stone_safe_tools");
    public static TagKey<Item> FASTER_BOATS = of("faster_boats");
    public static TagKey<Item> FASTER_CHEST_BOATS = of("faster_chest_boats");
    public static TagKey<Item> FIREPROOF_BOATS = of("fireproof_boats");
    public static TagKey<Item> FIREPROOF_CHEST_BOATS = of("fireproof_chest_boats");
    public static TagKey<Item> FIREPROOF_ITEMS = of("fireproof_items");
    public static TagKey<Item> FLINT_AND_STEELS = of("flint_and_steels");
    public static TagKey<Item> GOLDEN_FOOD_ITEMS = of("golden_food_items");
    public static TagKey<Item> IGNITABLES = of("ignitables");
    public static TagKey<Item> IRON_INGOTS = of("iron_ingots");
    public static TagKey<Item> IRON_NUGGETS = of("iron_nuggets");
    public static TagKey<Item> PIGLIN_AWAKENER_CRAFTABLES = of("piglin_awakener_craftables");
    public static TagKey<Item> SCULK_SENSOR_SAFE_BOOTS = of("sculk_sensor_safe_boots");
    public static TagKey<Item> SHIELDS = of("shields");
    public static TagKey<Item> SHEARS = of("shears");
    public static TagKey<Item> SPEED_BOOTS = of("speed_boots");
    public static TagKey<Item> SPEEDRUNNER_ARMOR = of("speedrunner_armor");
    public static TagKey<Item> SPEEDRUNNER_TOOLS = of("speedrunner_tools");
    public static TagKey<Item> STATE_OF_THE_ART_ITEMS = of("state_of_the_art_items");
    public static TagKey<Item> STICKS = of("sticks");

    /**
     * Item tags that also have a block tag.
     */
    public static class Block {
        public static TagKey<Item> DEAD_SPEEDRUNNER_LOGS = of("dead_speedrunner_logs");
        public static TagKey<Item> DOOM_LOGS = of("doom_logs");
        public static TagKey<Item> EXPERIENCE_ORES = of("experience_ores");
        public static TagKey<Item> IGNEOUS_ORES = of("igneous_ores");
        public static TagKey<Item> IRON_BLOCKS = of("iron_blocks");
        public static TagKey<Item> NETHER_PORTAL_BASE_BLOCKS = of("nether_portal_base_blocks");
        public static TagKey<Item> SMITHING_TABLES = of("smithing_tables");
        public static TagKey<Item> SPEEDRUNNER_FUELS = of("speedrunner_fuels");
        public static TagKey<Item> SPEEDRUNNER_LOGS = of("speedrunner_logs");
        public static TagKey<Item> SPEEDRUNNER_ORES = of("speedrunner_ores");
        public static TagKey<Item> SPEEDRUNNER_PLANKS = of("speedrunner_planks");
        public static TagKey<Item> SPEEDRUNNER_SAPLING_PLACEABLES = of("speedrunner_sapling_placeables");
        public static TagKey<Item> SPEEDRUNNER_SIGNS = of("speedrunner_signs");
        public static TagKey<Item> TEXTURE_CREATOR_MANNYQUESO = of("texture_creator_mannyqueso");
        public static TagKey<Item> TEXTURE_CREATOR_KREVIKUS = of("texture_creator_krevikus");
    }

    /**
     * Registers an {@code item tag.}
     */
    private static TagKey<Item> of(String path) {
        return TagKey.of(RegistryKeys.ITEM, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code item tags.}
     */
    public static void initializeItemTags() {}
}