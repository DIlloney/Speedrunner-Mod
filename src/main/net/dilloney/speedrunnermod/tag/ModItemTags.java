package net.dilloney.speedrunnermod.tag;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ModItemTags {

    public static Tag<Item> IRON_INGOTS;
    public static Tag<Item> IRON_NUGGETS;
    public static Tag<Item> IRON_BLOCKS;

    public static void init() {
        ModItemTags.IRON_INGOTS = TagRegistry.item(new Identifier(SpeedrunnerMod.MOD_ID, "iron_ingots"));
        ModItemTags.IRON_NUGGETS = TagRegistry.item(new Identifier(SpeedrunnerMod.MOD_ID, "iron_nuggets"));
        ModItemTags.IRON_BLOCKS = TagRegistry.item(new Identifier(SpeedrunnerMod.MOD_ID, "iron_blocks"));
    }
}