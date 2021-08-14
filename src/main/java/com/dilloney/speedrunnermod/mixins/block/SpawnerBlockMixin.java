package com.dilloney.speedrunnermod.mixins.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(SpawnerBlock.class)
public class SpawnerBlockMixin extends Block {

    protected SpawnerBlockMixin(Settings settings) {
        super(settings);
    }

    @Overwrite
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        int f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 128;
        int i = 512 + world.random.nextInt(512) + f;
        this.dropExperience(world, pos, i);
        if (OPTIONS.doomMode) {
            world.setBlockState(pos, Blocks.LAVA.getDefaultState());
        }
    }
}