package net.dilloney.speedrunnermod.tag;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ModItemTags {

    public static Tag.Identified<Item> IRON_INGOTS;
    public static Tag.Identified<Item> IRON_NUGGETS;
    public static Tag.Identified<Item> IRON_BLOCKS;

    public static void init() {
        ModItemTags.IRON_INGOTS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "iron_ingots"));
        ModItemTags.IRON_NUGGETS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "iron_nuggets"));
        ModItemTags.IRON_BLOCKS = TagFactory.ITEM.create(new Identifier(SpeedrunnerMod.MOD_ID, "iron_blocks"));
        if (SpeedrunnerMod.options().advanced.debugMode) {
            SpeedrunnerMod.LOGGER.debug("Loaded item tags");
        }
    }
}