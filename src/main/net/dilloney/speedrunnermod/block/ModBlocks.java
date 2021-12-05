package net.dilloney.speedrunnermod.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block SPEEDRUNNER_BLOCK = new Block(FabricBlockSettings.of(Material.METAL)
            .requiresTool()
            .strength(5.0F, 6.0F)
            .sounds(BlockSoundGroup.METAL));

    public static final Block RAW_SPEEDRUNNER_BLOCK = new Block(FabricBlockSettings.of(Material.STONE)
            .requiresTool()
            .strength(5.0F, 6.0F)
            .sounds(BlockSoundGroup.STONE));

    public static final Block SPEEDRUNNER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.STONE), UniformIntProvider.create(1, 2));

    public static final Block DEEPSLATE_SPEEDRUNNER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool()
            .strength(4.5F, 4.5F)
            .sounds(BlockSoundGroup.DEEPSLATE), UniformIntProvider.create(1, 2));

    public static final Block NETHER_SPEEDRUNNER_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.NETHER_GOLD_ORE), UniformIntProvider.create(0, 1));

    public static final Block IGNEOUS_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.STONE), UniformIntProvider.create(2, 6));

    public static final Block DEEPSLATE_IGNEOUS_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool()
            .strength(4.5F, 4.5F)
            .sounds(BlockSoundGroup.DEEPSLATE), UniformIntProvider.create(2, 6));

    public static final Block NETHER_IGNEOUS_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.NETHER_ORE), UniformIntProvider.create(2, 6));

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_block"), ModBlocks.SPEEDRUNNER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "raw_speedrunner_block"), ModBlocks.RAW_SPEEDRUNNER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_ore"), ModBlocks.SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "deepslate_speedrunner_ore"), ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_speedrunner_ore"), ModBlocks.NETHER_SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "igneous_ore"), ModBlocks.IGNEOUS_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "deepslate_igneous_ore"), ModBlocks.DEEPSLATE_IGNEOUS_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_igneous_ore"), ModBlocks.NETHER_IGNEOUS_ORE);
    }
}