package com.dilloney.speedrunnermod.mixins.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(Blocks.class)
public class BlocksMixin {

    /**
     * Makes the Bed Block instaBreak.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    private static BedBlock createBedBlock(DyeColor color) {
        if (OPTIONS.modifiedBlockHardness) {
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