package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
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
    private void onStacksDroppedMod(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo callbackInfo) {
        if (SpeedrunnerMod.CONFIG.modifiedLootTables) {
            if (state.isOf(Blocks.IRON_ORE)) {
                int i = 1 + world.random.nextInt(2);
                this.dropExperience(world, pos, i);
            } else if (state.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
                int i = 1 + world.random.nextInt(3);
                this.dropExperience(world, pos, i);
            } else if (state.isOf(Blocks.GOLD_ORE)) {
                int i = 1 + world.random.nextInt(5);
                this.dropExperience(world, pos, i);
            } else if (state.isOf(Blocks.DEEPSLATE_GOLD_ORE)) {
                int i = 1 + world.random.nextInt(6);
                this.dropExperience(world, pos, i);
            }
        } if (state.isOf(Blocks.NETHER_GOLD_ORE)) {
            int i = 1 + world.random.nextInt(2);
            this.dropExperience(world, pos, i);
        }
    }
}