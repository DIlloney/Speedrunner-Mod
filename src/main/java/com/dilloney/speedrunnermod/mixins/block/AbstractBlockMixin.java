package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.tag.ModBlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockMixin {

    @Inject(method = "getHardness", at = @At("HEAD"), cancellable = true)
    private void changeBlockHardnessForCertainBlocks(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> callbackInfoReturnable) {
        if (OPTIONS.modifiedBlockHardness) {
            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_ONE_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(0.1F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_TWO_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(0.2F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_THREESEVEN_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(0.37F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_FOUR_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(0.4F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_FIVE_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(0.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SIX_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(0.6F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SIXFIVE_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(0.65F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SEVEN_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(0.7F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_EIGHT_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(0.8F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_ZERO_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(1.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_THREE_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(1.3F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_FOUR_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(1.4F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_FIVE_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(1.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_SIX_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(1.6F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TWO_ZERO_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(2.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TWO_FIVE_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(2.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TEN_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(10.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TWENTY_FIVE_HARDNESS)) {
                callbackInfoReturnable.setReturnValue(25.0F);
            }
        }
    }
}