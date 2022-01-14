package net.dilloney.speedrunnermod.block;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block SPEEDRUNNER_BLOCK = new Block(FabricBlockSettings.of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool()
            .strength(5.0F, 6.0F)
            .sounds(BlockSoundGroup.METAL));

    public static final Block SPEEDRUNNER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.STONE));

    public static final Block NETHER_SPEEDRUNNER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES, 0)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.NETHER_GOLD_ORE));

    public static final Block IGNEOUS_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.STONE));

    public static final Block NETHER_IGNEOUS_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.NETHER_ORE));

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_block"), ModBlocks.SPEEDRUNNER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_ore"), ModBlocks.SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "nether_speedrunner_ore"), ModBlocks.NETHER_SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "igneous_ore"), ModBlocks.IGNEOUS_ORE);
        Registry.register(Registry.BLOCK, new Identifier(SpeedrunnerMod.MOD_ID, "nether_igneous_ore"), ModBlocks.NETHER_IGNEOUS_ORE);
        if (SpeedrunnerMod.options().advanced.debugMode) {
            SpeedrunnerMod.LOGGER.debug("Initialized Speedrunner Mod blocks");
        }
    }
}