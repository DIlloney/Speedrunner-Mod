package net.dillon.speedrunnermod.mixin.main.fix;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.screen.SmithingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SmithingScreenHandler.class)
public class SmithingScreenHandlerMixin {

    /**
     * @author Dillon8775
     * @reason Allows the {@code speedrunners workbench} block screen to open correctly.
     */
    @Overwrite
    public boolean canUse(BlockState state) {
        return state.isOf(ModBlocks.SPEEDRUNNERS_WORKBENCH);
    }
}