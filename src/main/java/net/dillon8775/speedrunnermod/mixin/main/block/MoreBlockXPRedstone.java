package net.dillon8775.speedrunnermod.mixin.main.block;

import net.dillon8775.speedrunnermod.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RedstoneOreBlock.class)
public class MoreBlockXPRedstone extends Block {

    public MoreBlockXPRedstone(Settings settings) {
        super(settings);
    }

    /**
     * Makes redstone ores drop more experience when mined, triple that amount if in the {@code Speedrunner's Wasteland} biome.
     * <p>Done separately because the {@link RedstoneOreBlock} is an entirely separate class from {@link net.minecraft.block.OreBlock}</p>
     */
    @Inject(method = "onStacksDropped", at = @At("TAIL"))
    private void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean dropExperience, CallbackInfo ci) {
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            int f;
            int i;
            if (world.getBiome(pos) == ModBiomes.SPEEDRUNNERS_WASTELAND_KEY) {
                if (state.isOf(Blocks.REDSTONE_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 52;
                    i = 4 + world.random.nextInt(11) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DEEPSLATE_REDSTONE_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 76;
                    i = 4 + world.random.nextInt(11) + f;
                    this.dropExperience(world, pos, i);
                }
            } else {
                if (state.isOf(Blocks.REDSTONE_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 48;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DEEPSLATE_REDSTONE_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 72;
                    this.dropExperience(world, pos, f);
                }
            }
        }
    }
}