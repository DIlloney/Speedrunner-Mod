package net.dilloney.speedrunnermod.tag;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ModBlockTags {

    public static Tag.Identified<Block> ZERO_HARDNESS;
    public static Tag.Identified<Block> ZERO_ONE_HARDNESS;
    public static Tag.Identified<Block> ZERO_TWO_HARDNESS;
    public static Tag.Identified<Block> ZERO_THREESEVEN_HARDNESS;
    public static Tag.Identified<Block> ZERO_FOUR_HARDNESS;
    public static Tag.Identified<Block> ZERO_FIVE_HARDNESS;
    public static Tag.Identified<Block> ZERO_SIX_HARDNESS;
    public static Tag.Identified<Block> ZERO_SIXFIVE_HARDNESS;
    public static Tag.Identified<Block> ZERO_SEVEN_HARDNESS;
    public static Tag.Identified<Block> ZERO_EIGHT_HARDNESS;
    public static Tag.Identified<Block> ONE_ZERO_HARDNESS;
    public static Tag.Identified<Block> ONE_THREE_HARDNESS;
    public static Tag.Identified<Block> ONE_FOUR_HARDNESS;
    public static Tag.Identified<Block> ONE_FIVE_HARDNESS;
    public static Tag.Identified<Block> ONE_SIX_HARDNESS;
    public static Tag.Identified<Block> TWO_ZERO_HARDNESS;
    public static Tag.Identified<Block> TWO_FIVE_HARDNESS;
    public static Tag.Identified<Block> THREE_ZERO_HARDNESS;
    public static Tag.Identified<Block> TEN_HARDNESS;
    public static Tag.Identified<Block> TWENTY_FIVE_HARDNESS;

    public static void init() {
        ModBlockTags.ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0_hardness"));
        ModBlockTags.ZERO_ONE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-1_hardness"));
        ModBlockTags.ZERO_TWO_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-2_hardness"));
        ModBlockTags.ZERO_THREESEVEN_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-37_hardness"));
        ModBlockTags.ZERO_FOUR_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-4_hardness"));
        ModBlockTags.ZERO_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-5_hardness"));
        ModBlockTags.ZERO_SIX_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-6_hardness"));
        ModBlockTags.ZERO_SIXFIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-65_hardness"));
        ModBlockTags.ZERO_SEVEN_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-7_hardness"));
        ModBlockTags.ZERO_EIGHT_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-8_hardness"));
        ModBlockTags.ONE_ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-0_hardness"));
        ModBlockTags.ONE_THREE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-3_hardness"));
        ModBlockTags.ONE_FOUR_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-4_hardness"));
        ModBlockTags.ONE_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-5_hardness"));
        ModBlockTags.ONE_SIX_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-6_hardness"));
        ModBlockTags.TWO_ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/2-0_hardness"));
        ModBlockTags.TWO_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/2-5_hardness"));
        ModBlockTags.THREE_ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/3-0_hardness"));
        ModBlockTags.TEN_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/10_hardness"));
        ModBlockTags.TWENTY_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/25_hardness"));
    }
}