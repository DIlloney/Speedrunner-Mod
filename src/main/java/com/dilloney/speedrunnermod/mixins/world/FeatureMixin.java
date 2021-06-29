package com.dilloney.speedrunnermod.mixins.world;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Feature.class)
public class FeatureMixin {

    @Overwrite
    public static boolean isSoil(Block block) {
        return block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.PODZOL || block == Blocks.COARSE_DIRT || block == Blocks.MYCELIUM || block == Blocks.SAND || block == Blocks.RED_SAND;
    }
}
