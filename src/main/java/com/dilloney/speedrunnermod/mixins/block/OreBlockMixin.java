package com.dilloney.speedrunnermod.mixins.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OreBlock.class)
public class OreBlockMixin extends Block {

    public OreBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "onStacksDropped", at = @At("TAIL"))
    private void onStacksDroppedModified(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo callbackInfo) {
        if (state.isOf(Blocks.IRON_ORE)) {
            int i = 1 + world.random.nextInt(2);
            this.dropExperience(world, pos, i);
        }
        if (state.isOf(Blocks.GOLD_ORE)) {
            int i = 1 + world.random.nextInt(5);
            this.dropExperience(world, pos, i);
        }
    }
}
