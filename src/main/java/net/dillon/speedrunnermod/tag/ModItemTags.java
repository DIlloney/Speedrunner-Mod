package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * {@link SpeedrunnerMod} item tags.
 */
public class ModItemTags {
    public static TagKey<Item> BOWS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "bows"));
    public static TagKey<Item> COOLDOWN_ENCHANTMENT_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "cooldown_enchantment_items"));
    public static TagKey<Item> CROSSBOWS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "crossbows"));
    public static TagKey<Item> DOOM_STONE_SAFE_TOOLS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "doom_stone_safe_tools"));
    public static TagKey<Item> FASTER_BOATS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "faster_boats"));
    public static TagKey<Item> FIREPROOF_BOATS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "fireproof_boats"));
    public static TagKey<Item> FIREPROOF_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "fireproof_items"));
    public static TagKey<Item> FLINT_AND_STEELS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "flint_and_steels"));
    public static TagKey<Item> GOLDEN_FOOD_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "golden_food_items"));
    public static TagKey<Item> IGNITABLES = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "ignitables"));
    public static TagKey<Item> IRON_INGOTS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "iron_ingots"));
    public static TagKey<Item> IRON_NUGGETS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "iron_nuggets"));
    public static TagKey<Item> PIGLIN_AWAKENER_CRAFTABLES = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "piglin_awakener_craftables"));
    public static TagKey<Item> PIGLIN_SAFE_ARMOR = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "piglin_safe_armor"));
    public static TagKey<Item> SHIELDS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "shields"));
    public static TagKey<Item> SHEARS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "shears"));
    public static TagKey<Item> SPEED_BOOTS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "speed_boots"));
    public static TagKey<Item> SPEEDRUNNER_ARMOR = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_armor"));
    public static TagKey<Item> SPEEDRUNNER_TOOLS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_tools"));
    public static TagKey<Item> STACK_TO_64 = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "stack_to_64"));
    public static TagKey<Item> STICKS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "sticks"));

    /**
     * Item tags that also have a block tag.
     */
    public static class Block {
        public static TagKey<Item> DOOM_LOGS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "doom_logs"));
        public static TagKey<Item> EXPERIENCE_ORES = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "experience_ores"));
        public static TagKey<Item> IGNEOUS_ORES = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "igneous_ores"));
        public static TagKey<Item> IRON_BLOCKS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "iron_blocks"));
        public static TagKey<Item> NETHER_PORTAL_BASE_BLOCKS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "nether_portal_base_blocks"));
        public static TagKey<Item> SMITHING_TABLES = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "smithing_tables"));
        public static TagKey<Item> SPEEDRUNNER_FUELS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_fuels"));
        public static TagKey<Item> SPEEDRUNNER_LOGS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_logs"));
        public static TagKey<Item> SPEEDRUNNER_ORES = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_ores"));
        public static TagKey<Item> SPEEDRUNNER_SAPLING_PLACEABLES = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_sapling_placeables"));
        public static TagKey<Item> SPEEDRUNNER_SIGNS = TagKey.of(Registry.ITEM_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_signs"));
    }

    public static void init() {
        info("Registered item tags.");
    }
}