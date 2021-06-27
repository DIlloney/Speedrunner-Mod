package com.dilloney.speedrunnermod.mixins.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Blocks.class)
public class BlocksMixin {

    @Overwrite
    private static BedBlock createBedBlock(DyeColor color) {
        return new BedBlock(color, AbstractBlock.Settings.of(Material.WOOL, (blockState) -> {
            return blockState.get(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WHITE;
        }).sounds(BlockSoundGroup.WOOD).breakInstantly().nonOpaque());
    }
}
