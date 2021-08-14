package com.dilloney.speedrunnermod.item;

import com.dilloney.speedrunnermod.sound.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.tag.BlockTags;

public final class SpeedrunnerItem extends Item {

    public SpeedrunnerItem(Settings settings) {
        super(settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 1.5F;
    }

    static final class SpeedrunnerBow extends BowItem {

        SpeedrunnerBow(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).maxCount(1).maxDamage(768));
        }

        @Override
        public int getEnchantability() {
            return 17;
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }

    }

    public static final class SpeedrunnerCrossbow extends CrossbowItem {

        SpeedrunnerCrossbow(Item.Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).maxCount(1).maxDamage(652));
        }

        @Override
        public boolean isUsedOnRelease(ItemStack stack) {
            return stack.getItem() == ModItems.SPEEDRUNNER_CROSSBOW;
        }

        @Override
        public int getMaxUseTime(ItemStack stack) {
            return getPullTime(stack) + 3;
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }

        @Override
        public int getEnchantability() {
            return 17;
        }

        public static int getPullTime(ItemStack stack) {
            int i = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, stack);
            return i == 0 ? 20 : 20 - 5 * i;
        }
    }

    static final class SpeedrunnerFlintAndSteel extends FlintAndSteelItem {

        SpeedrunnerFlintAndSteel(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).maxCount(1).maxDamage(128));
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }
    }

    static final class SpeedrunnerShears extends ShearsItem {

        SpeedrunnerShears(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).maxCount(1).maxDamage(476));
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            if (!state.isOf(Blocks.COBWEB) && !state.isIn(BlockTags.LEAVES)) {
                if (state.isIn(BlockTags.WOOL)) {
                    return 7.5F;
                } else {
                    return !state.isOf(Blocks.VINE) && !state.isOf(Blocks.GLOW_LICHEN) ? super.getMiningSpeedMultiplier(stack, state) : 2.0F;
                }
            } else {
                return 17.0F;
            }
        }
    }

    static final class SpeedrunnerFishingRod extends FishingRodItem {

        SpeedrunnerFishingRod(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.TOOLS).maxCount(1).maxDamage(128));
        }

        @Override
        public int getEnchantability() {
            return 17;
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }
    }

    static final class SpeedrunnerCarrotOnAStick extends OnAStickItem {

        SpeedrunnerCarrotOnAStick(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).maxDamage(50), EntityType.PIG, 3);
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }
    }

    static final class SpeedrunnerWarpedFungusOnAStick extends OnAStickItem {

        SpeedrunnerWarpedFungusOnAStick(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).maxDamage(150), EntityType.STRIDER, 1);
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }
    }

    static final class SpeedrunnerMusicDisc extends MusicDiscItem {

        SpeedrunnerMusicDisc(Settings settings) {
            super(7, ModSoundEvents.SPEEDRUNNER_MUSIC_DISC, settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).maxCount(1));
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }
    }

    static final class SpeedrunnerShield extends ShieldItem {

        SpeedrunnerShield(Settings settings) {
            super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.COMBAT).maxCount(1).maxDamage(672));
        }

        @Override
        public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
            return 1.5F;
        }

        public boolean canRepair(ItemStack stack, ItemStack ingredient) {
            return ingredient.isOf(ModItems.SPEEDRUNNER_INGOT);
        }
    }
}