package com.dilloney.speedrunnermod.mixins.world.dimension;

import com.dilloney.speedrunnermod.tag.ModBlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AreaHelper.class)
public class AreaHelperMixin {

    @Shadow static final AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK;

    static {
        IS_VALID_FRAME_BLOCK = (state, world, pos) -> {
            return state.isIn(ModBlockTags.NETHER_PORTAL_BASE_BLOCKS);
        };
    }
}