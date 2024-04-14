package net.dillon.speedrunnermod.mixin.main.block;

import net.dillon.speedrunnermod.tag.ModBlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class FasterBlockBreaking {
    @Shadow @Final
    private float hardness;

    /**
     * Applies the {@code Faster Block Breaking} option to certain blocks.
     */
    @Inject(method = "getHardness", at = @At("HEAD"), cancellable = true)
    private void applyFasterBlockBreaking(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (options().main.fasterBlockBreaking) {
            cir.setReturnValue(this.hardness / options().main.blockBreakingMultiplier);

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_HARDNESS)) {
                cir.setReturnValue(0.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_ONE_HARDNESS)) {
                cir.setReturnValue(0.1F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_TWO_HARDNESS)) {
                cir.setReturnValue(0.2F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_THREEFIVE_HARDNESS)) {
                cir.setReturnValue(0.35F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_THREESEVEN_HARDNESS)) {
                cir.setReturnValue(0.37F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_FOUR_HARDNESS)) {
                cir.setReturnValue(0.4F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_FIVE_HARDNESS)) {
                cir.setReturnValue(0.5F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_SIX_HARDNESS)) {
                cir.setReturnValue(0.6F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_SIXFIVE_HARDNESS)) {
                cir.setReturnValue(0.65F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_SEVEN_HARDNESS)) {
                cir.setReturnValue(0.7F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_EIGHT_HARDNESS)) {
                cir.setReturnValue(0.8F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ONE_HARDNESS)) {
                cir.setReturnValue(1.0F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ONE_THREE_HARDNESS)) {
                cir.setReturnValue(1.3F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ONE_FOUR_HARDNESS)) {
                cir.setReturnValue(1.4F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ONE_FIVE_HARDNESS)) {
                cir.setReturnValue(1.5F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ONE_SIX_HARDNESS)) {
                cir.setReturnValue(1.6F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.TWO_HARDNESS)) {
                cir.setReturnValue(2.0F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.TWO_FIVE_HARDNESS)) {
                cir.setReturnValue(2.5F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.THREE_HARDNESS)) {
                cir.setReturnValue(3.0F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.FOUR_FIVE_HARDNESS)) {
                cir.setReturnValue(4.5F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.FIVE_HARDNESS)) {
                cir.setReturnValue(5.0F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.SIX_HARDNESS)) {
                cir.setReturnValue(6.0F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.SEVEN_HARDNESS)) {
                cir.setReturnValue(7.0F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.EIGHT_HARDNESS)) {
                cir.setReturnValue(8.0F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.NINE_HARDNESS)) {
                cir.setReturnValue(9.0F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.TEN_HARDNESS)) {
                cir.setReturnValue(10.0F / options().main.blockBreakingMultiplier);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.TWENTY_FIVE_HARDNESS)) {
                cir.setReturnValue(25.0F / options().main.blockBreakingMultiplier);
            }
        }
    }
}