package net.dilloney.speedrunnermod.tag;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ModBlockTags {

    public static Tag<Block> ZERO_HARDNESS;
    public static Tag<Block> ZERO_ONE_HARDNESS;
    public static Tag<Block> ZERO_TWO_HARDNESS;
    public static Tag<Block> ZERO_THREESEVEN_HARDNESS;
    public static Tag<Block> ZERO_FOUR_HARDNESS;
    public static Tag<Block> ZERO_FIVE_HARDNESS;
    public static Tag<Block> ZERO_SIX_HARDNESS;
    public static Tag<Block> ZERO_SIXFIVE_HARDNESS;
    public static Tag<Block> ZERO_SEVEN_HARDNESS;
    public static Tag<Block> ZERO_EIGHT_HARDNESS;
    public static Tag<Block> ONE_ZERO_HARDNESS;
    public static Tag<Block> ONE_THREE_HARDNESS;
    public static Tag<Block> ONE_FOUR_HARDNESS;
    public static Tag<Block> ONE_FIVE_HARDNESS;
    public static Tag<Block> ONE_SIX_HARDNESS;
    public static Tag<Block> TWO_ZERO_HARDNESS;
    public static Tag<Block> TWO_FIVE_HARDNESS;
    public static Tag<Block> THREE_ZERO_HARDNESS;
    public static Tag<Block> TEN_HARDNESS;
    public static Tag<Block> TWENTY_FIVE_HARDNESS;

    public static void init() {
        ModBlockTags.ZERO_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0_hardness"));
        ModBlockTags.ZERO_ONE_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-1_hardness"));
        ModBlockTags.ZERO_TWO_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-2_hardness"));
        ModBlockTags.ZERO_THREESEVEN_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-37_hardness"));
        ModBlockTags.ZERO_FOUR_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-4_hardness"));
        ModBlockTags.ZERO_FIVE_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-5_hardness"));
        ModBlockTags.ZERO_SIX_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-6_hardness"));
        ModBlockTags.ZERO_SIXFIVE_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-65_hardness"));
        ModBlockTags.ZERO_SEVEN_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-7_hardness"));
        ModBlockTags.ZERO_EIGHT_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-8_hardness"));
        ModBlockTags.ONE_ZERO_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-0_hardness"));
        ModBlockTags.ONE_THREE_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-3_hardness"));
        ModBlockTags.ONE_FOUR_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-4_hardness"));
        ModBlockTags.ONE_FIVE_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-5_hardness"));
        ModBlockTags.ONE_SIX_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-6_hardness"));
        ModBlockTags.TWO_ZERO_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/2-0_hardness"));
        ModBlockTags.TWO_FIVE_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/2-5_hardness"));
        ModBlockTags.THREE_ZERO_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/3-0_hardness"));
        ModBlockTags.TEN_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/10_hardness"));
        ModBlockTags.TWENTY_FIVE_HARDNESS = TagRegistry.block(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/25_hardness"));
    }
}