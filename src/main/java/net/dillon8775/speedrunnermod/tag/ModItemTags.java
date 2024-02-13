package net.dillon8775.speedrunnermod.tag;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

/**
 * {@link SpeedrunnerMod} item tags.
 */
public class ModItemTags {
    public static Tag.Identified<Item> SPEEDRUNNER_ORES = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_ores"));
    public static Tag.Identified<Item> IGNEOUS_ORES = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "igneous_ores"));
    public static Tag.Identified<Item> SPEEDRUNNER_LOGS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_logs"));
    public static Tag.Identified<Item> SPEEDRUNNER_PARTICLE_BLOCKS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_particle_blocks"));
    public static Tag.Identified<Item> EXPERIENCE_ORES = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "experience_ores"));
    public static Tag.Identified<Item> NETHER_PORTAL_BASE_BLOCKS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "nether_portal_base_blocks"));
    public static Tag.Identified<Item> SMITHING_TABLES = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "smithing_tables"));
    public static Tag.Identified<Item> COOLDOWN_ENCHANTMENT_ITEMS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "cooldown_enchantment_items"));
    public static Tag.Identified<Item> BUFFED_ITEMS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "buffed_items"));
    public static Tag.Identified<Item> FIREPROOF_ITEMS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "fireproof_items"));
    public static Tag.Identified<Item> BARTERING_ITEMS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "bartering_items"));
    public static Tag.Identified<Item> PIGLIN_SAFE_ARMOR = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "piglin_safe_armor"));
    public static Tag.Identified<Item> FIREPROOF_BOAT_BASE_BLOCKS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "fireproof_boat_base_blocks"));
    public static Tag.Identified<Item> FASTER_BOAT_BASE_BLOCKS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "faster_boat_base_blocks"));
    public static Tag.Identified<Item> FLINT_AND_STEELS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "flint_and_steels"));
    public static Tag.Identified<Item> IGNITABLES = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "ignitables"));
    public static Tag.Identified<Item> SPEEDRUNNER_FUELS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_fuels"));
    public static Tag.Identified<Item> BOWS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "bows"));
    public static Tag.Identified<Item> CROSSBOWS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "crossbows"));
    public static Tag.Identified<Item> SHIELDS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "shields"));
    public static Tag.Identified<Item> SHEARS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "shears"));
    public static Tag.Identified<Item> STICKS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "sticks"));
    public static Tag.Identified<Item> IRON_INGOTS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "iron_ingots"));
    public static Tag.Identified<Item> IRON_NUGGETS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "iron_nuggets"));
    public static Tag.Identified<Item> IRON_BLOCKS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "iron_blocks"));

    public static void init() {
        info("Initialized item tags.");
    }
}