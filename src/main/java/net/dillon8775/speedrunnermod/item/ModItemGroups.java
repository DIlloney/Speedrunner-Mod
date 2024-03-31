package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

/**
 * The {@link SpeedrunnerMod} item group.
 */
public class ModItemGroups {

    public static final ItemGroup SPEEDRUNNER_MOD = FabricItemGroupBuilder.create(
            new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_mod_item_group"))
            .icon(() -> new ItemStack(ModBlockItems.SPEEDRUNNER_BLOCK))
            .appendItems(item -> {
                item.add(new ItemStack(ModItems.SPEEDRUNNER_INGOT));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_NUGGET));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_BLOCK));
                item.add(new ItemStack(ModItems.RAW_SPEEDRUNNER));
                item.add(new ItemStack(ModBlockItems.RAW_SPEEDRUNNER_BLOCK));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_ORE));
                item.add(new ItemStack(ModBlockItems.DEEPSLATE_SPEEDRUNNER_ORE));
                item.add(new ItemStack(ModBlockItems.NETHER_SPEEDRUNNER_ORE));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_LOG));
                item.add(new ItemStack(ModBlockItems.STRIPPED_SPEEDRUNNER_LOG));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_WOOD));
                item.add(new ItemStack(ModBlockItems.STRIPPED_SPEEDRUNNER_WOOD));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_LEAVES));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_SAPLING));
                item.add(new ItemStack(ModBlockItems.DEAD_SPEEDRUNNER_LOG));
                item.add(new ItemStack(ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_LOG));
                item.add(new ItemStack(ModBlockItems.DEAD_SPEEDRUNNER_WOOD));
                item.add(new ItemStack(ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD));
                item.add(new ItemStack(ModBlockItems.DEAD_SPEEDRUNNER_LEAVES));
                item.add(new ItemStack(ModBlockItems.DEAD_SPEEDRUNNER_BUSH));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_PLANKS));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_STICK));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_SLAB));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_STAIRS));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_FENCE));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_FENCE_GATE));
                item.add(new ItemStack(ModBlockItems.WOODEN_SPEEDRUNNER_TRAPDOOR));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_TRAPDOOR));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_BUTTON));
                item.add(new ItemStack(ModBlockItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE));
                item.add(new ItemStack(ModBlockItems.WOODEN_SPEEDRUNNER_DOOR));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_DOOR));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNER_SIGN));
                item.add(new ItemStack(ModBlockItems.SPEEDRUNNERS_WORKBENCH));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_SWORD));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_SHOVEL));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_PICKAXE));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_AXE));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_HOE));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_HELMET));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_CHESTPLATE));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_LEGGINGS));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_BOOTS));
                item.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_SWORD));
                item.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL));
                item.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE));
                item.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_AXE));
                item.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_HOE));
                item.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_HELMET));
                item.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE));
                item.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS));
                item.add(new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_BOOTS));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_BOW));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_CROSSBOW));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_SHEARS));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_FLINT_AND_STEEL));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_SHIELD));
                item.add(new ItemStack(ModItems.SPEEDRUNNERS_EYE));
                item.add(new ItemStack(ModItems.ENDER_THRUSTER));
                item.add(new ItemStack(ModItems.INFERNO_EYE));
                item.add(new ItemStack(ModItems.PIGLIN_AWAKENER));
                item.add(new ItemStack(ModItems.BLAZE_SPOTTER));
                item.add(new ItemStack(ModItems.RAID_ERADICATOR));
                item.add(new ItemStack(ModItems.ANNUL_EYE));
                item.add(new ItemStack(ModItems.DRAGONS_PEARL));
                item.add(new ItemStack(ModItems.DRAGONS_SWORD));
                item.add(new ItemStack(ModItems.WITHER_SWORD));
                item.add(new ItemStack(ModItems.WITHER_BONE));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_BOAT));
                item.add(new ItemStack(ModItems.CRIMSON_BOAT));
                item.add(new ItemStack(ModItems.WARPED_BOAT));
                for (int i = 1; i < 7; i++) {
                    item.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(i >= 4 ? ModEnchantments.COOL : ModEnchantments.DASH, i >= 4 ? i - 3 : i)));
                }
                item.add(new ItemStack(ModItems.IGNEOUS_ROCK));
                item.add(new ItemStack(ModBlockItems.IGNEOUS_ORE));
                item.add(new ItemStack(ModBlockItems.DEEPSLATE_IGNEOUS_ORE));
                item.add(new ItemStack(ModBlockItems.NETHER_IGNEOUS_ORE));
                item.add(new ItemStack(ModBlockItems.EXPERIENCE_ORE));
                item.add(new ItemStack(ModBlockItems.DEEPSLATE_EXPERIENCE_ORE));
                item.add(new ItemStack(ModBlockItems.NETHER_EXPERIENCE_ORE));
                item.add(new ItemStack(ModBlockItems.DOOM_STONE));
                item.add(new ItemStack(ModBlockItems.DOOM_LOG));
                item.add(new ItemStack(ModBlockItems.STRIPPED_DOOM_LOG));
                item.add(new ItemStack(ModBlockItems.DOOM_LEAVES));
                item.add(new ItemStack(ModItems.PIGLIN_PORK));
                item.add(new ItemStack(ModItems.COOKED_PIGLIN_PORK));
                item.add(new ItemStack(ModItems.GOLDEN_PIGLIN_PORK));
                item.add(new ItemStack(ModItems.GOLDEN_BEEF));
                item.add(new ItemStack(ModItems.GOLDEN_PORKCHOP));
                item.add(new ItemStack(ModItems.GOLDEN_MUTTON));
                item.add(new ItemStack(ModItems.GOLDEN_CHICKEN));
                item.add(new ItemStack(ModItems.GOLDEN_RABBIT));
                item.add(new ItemStack(ModItems.GOLDEN_COD));
                item.add(new ItemStack(ModItems.GOLDEN_SALMON));
                item.add(new ItemStack(ModItems.GOLDEN_BREAD));
                item.add(new ItemStack(ModItems.GOLDEN_POTATO));
                item.add(new ItemStack(ModItems.GOLDEN_BEETROOT));
                item.add(new ItemStack(ModItems.SPEEDRUNNER_BULK));
                item.add(new ItemStack(ModItems.ROTTEN_SPEEDRUNNER_BULK));
                item.add(new ItemStack(ModItems.COOKED_FLESH));
            }).build();

    public static void init() {
        SpeedrunnerMod.info("Initialized item groups.");
    }
}