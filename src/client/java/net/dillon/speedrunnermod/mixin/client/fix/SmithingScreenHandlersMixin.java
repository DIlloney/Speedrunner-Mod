package net.dillon.speedrunnermod.mixin.client.fix;

import net.dillon.speedrunnermod.tag.ModBlockTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.screen.SmithingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(SmithingScreenHandler.class)
public class SmithingScreenHandlersMixin {

    /**
     * Fixes the {@code Speedrunner's Workbench} GUI screen not opening when right-clicking.
     */
    @Inject(method = "canUse", at = @At("RETURN"), cancellable = true)
    private void canUseSpeedrunnersWorkbench(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(state.isIn(ModBlockTags.SMITHING_TABLES));
    }
}