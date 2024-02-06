package net.dillon8775.speedrunnermod.tag;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    public static Tag.Identified<Block> SPEEDRUNNER_ORES = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_ores"));
    public static Tag.Identified<Block> IGNEOUS_ORES = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "igneous_ores"));
    public static Tag.Identified<Block> SPEEDRUNNER_LOGS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_logs"));
    public static Tag.Identified<Block> NETHER_PORTAL_BASE_BLOCKS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "nether_portal_base_blocks"));
    public static Tag.Identified<Block> SMITHING_TABLES = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "smithing_tables"));
    public static Tag.Identified<Block> FIREPROOF_BOAT_BASE_BLOCKS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "fireproof_boat_base_blocks"));

    public static class BlockHardness {
        public static Tag.Identified<Block> ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0_hardness"));
        public static Tag.Identified<Block> ZERO_ONE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-1_hardness"));
        public static Tag.Identified<Block> ZERO_TWO_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-2_hardness"));
        public static Tag.Identified<Block> ZERO_THREESEVEN_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-37_hardness"));
        public static Tag.Identified<Block> ZERO_FOUR_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-4_hardness"));
        public static Tag.Identified<Block> ZERO_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-5_hardness"));
        public static Tag.Identified<Block> ZERO_SIX_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-6_hardness"));
        public static Tag.Identified<Block> ZERO_SIXFIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-65_hardness"));
        public static Tag.Identified<Block> ZERO_SEVEN_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-7_hardness"));
        public static Tag.Identified<Block> ZERO_EIGHT_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/0-8_hardness"));
        public static Tag.Identified<Block> ONE_ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-0_hardness"));
        public static Tag.Identified<Block> ONE_THREE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-3_hardness"));
        public static Tag.Identified<Block> ONE_FOUR_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-4_hardness"));
        public static Tag.Identified<Block> ONE_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-5_hardness"));
        public static Tag.Identified<Block> ONE_SIX_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/1-6_hardness"));
        public static Tag.Identified<Block> TWO_ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/2-0_hardness"));
        public static Tag.Identified<Block> TWO_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/2-5_hardness"));
        public static Tag.Identified<Block> THREE_ZERO_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/3-0_hardness"));
        public static Tag.Identified<Block> TEN_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/10_hardness"));
        public static Tag.Identified<Block> TWENTY_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(SpeedrunnerMod.MOD_ID, "block_hardness/25_hardness"));
    }

    public static void init() {
    }
}