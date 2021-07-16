package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
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
        if (SpeedrunnerMod.CONFIG.modifiedBlockHardnessValues) {
            return new BedBlock(color, AbstractBlock.Settings.of(Material.WOOL, (blockState) -> {
                return blockState.get(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WHITE;
            }).sounds(BlockSoundGroup.WOOD).breakInstantly().nonOpaque());
        } else {
            return new BedBlock(color, AbstractBlock.Settings.of(Material.WOOL, (state) -> {
                return state.get(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WHITE_GRAY;
            }).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque());
        }
    }
}