package com.dilloney.speedrunnermod.mixins.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(OreBlock.class)
public class OreBlockMixin extends Block {
    public OreBlockMixin(AbstractBlock.Settings settings) {
            super(settings);
        }

    @Overwrite
    public int getExperienceWhenMined(Random random) {
        if (this == Blocks.COAL_ORE) {
            return MathHelper.nextInt(random, 1, 2);
        } else if (this == Blocks.DIAMOND_ORE) {
            return MathHelper.nextInt(random, 3, 7);
        } else if (this == Blocks.EMERALD_ORE) {
            return MathHelper.nextInt(random, 3, 7);
        } else if (this == Blocks.LAPIS_ORE) {
            return MathHelper.nextInt(random, 2, 5);
        } else if (this == Blocks.IRON_ORE) {
            return MathHelper.nextInt(random, 1, 2);
        } else if (this == Blocks.GOLD_ORE) {
            return MathHelper.nextInt(random, 2, 4);
        } else if (this == Blocks.NETHER_QUARTZ_ORE) {
            return MathHelper.nextInt(random, 2, 5);
        } else {
            return this == Blocks.NETHER_GOLD_ORE ? MathHelper.nextInt(random, 1, 2) : 0;
        }
    }
}