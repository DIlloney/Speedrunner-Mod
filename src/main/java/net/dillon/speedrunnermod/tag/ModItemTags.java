package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * {@link SpeedrunnerMod} item tags.
 */
public class ModItemTags {
    public static TagKey<Item> SPEEDRUNNER_ORES = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_ores"));
    public static TagKey<Item> IGNEOUS_ORES = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "igneous_ores"));
    public static TagKey<Item> SPEEDRUNNER_LOGS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_logs"));
    public static TagKey<Item> SPEEDRUNNER_PARTICLE_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_particle_blocks"));
    public static TagKey<Item> EXPERIENCE_ORES = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "experience_ores"));
    public static TagKey<Item> NETHER_PORTAL_BASE_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "nether_portal_base_blocks"));
    public static TagKey<Item> SMITHING_TABLES = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "smithing_tables"));
    public static TagKey<Item> COOLDOWN_ENCHANTMENT_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "cooldown_enchantment_items"));
    public static TagKey<Item> BUFFED_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "buffed_items"));
    public static TagKey<Item> FIREPROOF_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "fireproof_items"));
    public static TagKey<Item> PIGLIN_SAFE_ARMOR = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "piglin_safe_armor"));
    public static TagKey<Item> PIGLIN_AWAKENER_CRAFTABLES = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "piglin_awakener_craftables"));
    public static TagKey<Item> DOOM_SAFE_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "doom_safe_items"));
    public static TagKey<Item> FIREPROOF_BOAT_BASE_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "fireproof_boat_base_blocks"));
    public static TagKey<Item> FASTER_BOAT_BASE_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "faster_boat_base_blocks"));
    public static TagKey<Item> STACK_TO_64 = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "stack_to_64"));
    public static TagKey<Item> GOLDEN_FOOD_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "golden_food_items"));
    public static TagKey<Item> FLINT_AND_STEELS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "flint_and_steels"));
    public static TagKey<Item> IGNITABLES = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "ignitables"));
    public static TagKey<Item> SPEEDRUNNER_FUELS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_fuels"));
    public static TagKey<Item> BOWS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "bows"));
    public static TagKey<Item> CROSSBOWS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "crossbows"));
    public static TagKey<Item> SHIELDS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "shields"));
    public static TagKey<Item> SHEARS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "shears"));
    public static TagKey<Item> STICKS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "sticks"));
    public static TagKey<Item> IRON_INGOTS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "iron_ingots"));
    public static TagKey<Item> IRON_NUGGETS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "iron_nuggets"));
    public static TagKey<Item> IRON_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier(SpeedrunnerMod.MOD_ID, "iron_blocks"));

    public static void init() {
        info("Registered item tags.");
    }
}