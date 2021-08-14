package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(OreBlock.class)
public class OreBlockMixin extends Block {

    protected OreBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "onStacksDropped", at = @At("TAIL"))
    private void makeCertainOresDropExperienceWhenMined(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo callbackInfo) {
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0 && OPTIONS.modifiedLootTables) {
            int f;
            int i;
            if (state.isOf(Blocks.GOLD_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 32;
                i = 2 + world.random.nextInt(5) + f;
                this.dropExperience(world, pos, i);
            } else if (state.isOf(Blocks.DEEPSLATE_GOLD_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 48;
                i = 2 + world.random.nextInt(5) + f;
                this.dropExperience(world, pos, i);
            } else if (state.isOf(Blocks.IRON_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 32;
                i = 1 + world.random.nextInt(2) + f;
                this.dropExperience(world, pos, i);
            } else if (state.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 48;
                i = 1 + world.random.nextInt(2) + f;
                this.dropExperience(world, pos, i);
            } else if (state.isOf(Blocks.COAL_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 12;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.DEEPSLATE_COAL_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 16;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.NETHER_GOLD_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 24;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.LAPIS_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 32;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.DEEPSLATE_LAPIS_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 48;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.DIAMOND_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 32;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 48;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.REDSTONE_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 16;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.DEEPSLATE_REDSTONE_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 24;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.EMERALD_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 32;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.DEEPSLATE_EMERALD_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 48;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.NETHER_QUARTZ_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 24;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(ModBlocks.SPEEDRUNNER_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 64;
                i = 64 + world.random.nextInt(64) + f;
                this.dropExperience(world, pos, i);
            } else if (state.isOf(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 72;
                i = 72 + world.random.nextInt(56) + f;
                this.dropExperience(world, pos, i);
            } else if (state.isOf(ModBlocks.NETHER_SPEEDRUNNER_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 24;
                i = 64 + world.random.nextInt(32) + f;
                this.dropExperience(world, pos, i);
            } else if (state.isOf(ModBlocks.IGNEOUS_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 32;
                i = 2 + world.random.nextInt(6) + f;
                this.dropExperience(world, pos, i);
            } else if (state.isOf(ModBlocks.DEEPSLATE_IGNEOUS_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 48;
                i = 2 + world.random.nextInt(6) + f;
                this.dropExperience(world, pos, i);
            } else if (state.isOf(ModBlocks.NETHER_IGNEOUS_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 24;
                i = 2 + world.random.nextInt(6) + f;
                this.dropExperience(world, pos, i);
            }
        }
    }
}