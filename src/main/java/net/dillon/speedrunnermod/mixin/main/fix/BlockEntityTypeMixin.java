package net.dillon.speedrunnermod.mixin.main.fix;

import net.minecraft.block.BlockState;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {

    /**
     * Fixes {@code speedrunner signs} not working correctly.
     */
    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
    private void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
        if (BlockEntityType.SIGN.equals(this) && (state.getBlock() instanceof SignBlock || BlockEntityType.HANGING_SIGN.equals(this) && (state.getBlock() instanceof SignBlock) || state.getBlock() instanceof WallSignBlock)) {
            info.setReturnValue(true);
        }
    }
}