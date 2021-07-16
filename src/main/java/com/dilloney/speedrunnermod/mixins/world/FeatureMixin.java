package com.dilloney.speedrunnermod.mixins.world;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.world.gen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Feature.class)
public class FeatureMixin {

    @Overwrite
    public static boolean isSoil(BlockState state) {
        if (SpeedrunnerMod.CONFIG.modifiedWorldGeneration) {
            return state.isIn(BlockTags.DIRT) || state.isOf(Blocks.SAND) || state.isOf(Blocks.RED_SAND);
        } else {
            return state.isIn(BlockTags.DIRT);
        }
    }
}