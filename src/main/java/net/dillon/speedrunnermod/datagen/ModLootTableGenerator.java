package net.dillon.speedrunnermod.datagen;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

/**
 * Generates block loot tables.
 * <p>Vanilla loot tables are modified separately, this is only used to generate the {@code speedrunner mod block loot tables.}</p>
 */
public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    private static final float[] SPEEDRUNNER_SAPLING_DROP_CHANCE = new float[]{0.075F, 0.0800F, 0.093333336F, 0.15F};
    private static final float[] SPEEDRUNNER_LEAVES_STICK_DROP_CHANCE = new float[]{0.65F,  0.06555558F, 0.70F, 0.075F, 0.1F};

    public ModLootTableGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockLootTables() {
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_BUSH, (Block block) -> BlockLootTableGenerator.dropsWithShears(block, applyExplosionDecay(block, ItemEntry.builder(ModItems.SPEEDRUNNER_STICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 9))))));
        addDrop(ModBlocks.SPEEDRUNNER_LEAVES, (Block block) -> speedrunnerLeavesDrops(block, ModBlocks.SPEEDRUNNER_SAPLING, SPEEDRUNNER_SAPLING_DROP_CHANCE));
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_LEAVES, (Block block) -> speedrunnerLeavesDrops(block, ModBlocks.SPEEDRUNNER_SAPLING, SPEEDRUNNER_SAPLING_DROP_CHANCE));

        addPottedPlantDrop(ModBlocks.POTTED_DEAD_SPEEDRUNNER_BUSH);
        addPottedPlantDrop(ModBlocks.POTTED_SPEEDRUNNER_SAPLING);

        addDrop(ModBlocks.RAW_SPEEDRUNNER_BLOCK);
        addDrop(ModBlocks.SPEEDRUNNER_BLOCK);
        addDrop(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON);
        addDrop(ModBlocks.SPEEDRUNNER_FENCE);
        addDrop(ModBlocks.SPEEDRUNNER_FENCE_GATE);
        addDrop(ModBlocks.SPEEDRUNNER_SAPLING);
        addDrop(ModBlocks.SPEEDRUNNER_SLAB, BlockLootTableGenerator::slabDrops);
        addDrop(ModBlocks.SPEEDRUNNER_STAIRS);
        addDrop(ModBlocks.SPEEDRUNNERS_WORKBENCH);

        addDrop(ModBlocks.SPEEDRUNNER_DOOR);
        addDrop(ModBlocks.WOODEN_SPEEDRUNNER_DOOR);
        addDrop(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        addDrop(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        addDrop(ModBlocks.SPEEDRUNNER_TRAPDOOR);
        addDrop(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR);

        addOreDrops();
        addWoodDrops();
        addSignDrops();
    }

    private void addOreDrops() {
        addDrop(ModBlocks.SPEEDRUNNER_ORE, (Block block) -> oreDrops(block, ModItems.SPEEDRUNNER_INGOT));
        addDrop(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE, (Block block) -> oreDrops(block, ModItems.SPEEDRUNNER_INGOT));
        addDrop(ModBlocks.NETHER_SPEEDRUNNER_ORE, (Block block) -> BlockLootTableGenerator.dropsWithSilkTouch(block, applyExplosionDecay(block, ItemEntry.builder(ModItems.SPEEDRUNNER_NUGGET).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 6)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));

        addDrop(ModBlocks.IGNEOUS_ORE, (Block block) -> igneousOreDrops(block, 2));
        addDrop(ModBlocks.DEEPSLATE_IGNEOUS_ORE, (Block block) -> igneousOreDrops(block, 2));
        addDrop(ModBlocks.NETHER_IGNEOUS_ORE, (Block block) -> igneousOreDrops(block, 4));

        addDropWithSilkTouch(ModBlocks.EXPERIENCE_ORE);
        addDropWithSilkTouch(ModBlocks.DEEPSLATE_EXPERIENCE_ORE);
        addDropWithSilkTouch(ModBlocks.NETHER_EXPERIENCE_ORE);
    }

    private void addWoodDrops() {
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_LOG);
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_WOOD);
        addDrop(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);
        addDrop(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        addDrop(ModBlocks.SPEEDRUNNER_LOG);
        addDrop(ModBlocks.STRIPPED_SPEEDRUNNER_LOG);
        addDrop(ModBlocks.SPEEDRUNNER_WOOD);
        addDrop(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD);
        addDrop(ModBlocks.SPEEDRUNNER_PLANKS);
    }

    private void addSignDrops() {
        addDrop(ModBlocks.SPEEDRUNNER_SIGN);
        addDrop(ModBlocks.SPEEDRUNNER_WALL_SIGN);
    }

    private LootTable.Builder igneousOreDrops(Block dropWithSilkTouch, int min) {
        return BlockLootTableGenerator.dropsWithSilkTouch(dropWithSilkTouch, applyExplosionDecay(dropWithSilkTouch, ItemEntry.builder(ModItems.IGNEOUS_ROCK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, 6))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }

    private LootTable.Builder speedrunnerLeavesDrops(Block leaves, Block drop, float ... chance) {
        return BlockLootTableGenerator.dropsWithSilkTouchOrShears(leaves, addSurvivesExplosionCondition(leaves, ItemEntry.builder(drop)).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, chance))).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS).with(addSurvivesExplosionCondition(leaves, ItemEntry.builder(Items.APPLE))).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.50F, 0.05555558F, 0.35F, 0.07F, 0.1F)).with(applyExplosionDecay(leaves, ItemEntry.builder(ModItems.SPEEDRUNNER_STICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2))))).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, SPEEDRUNNER_LEAVES_STICK_DROP_CHANCE)));
    }
}