package com.dilloney.speedrunnermod.item;

import com.dilloney.speedrunnermod.sound.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;

public final class SpeedrunnerMusicDiscItem extends MusicDiscItem {

    public SpeedrunnerMusicDiscItem(Settings settings) {
        super(7, ModSoundEvents.SPEEDRUNNER_MUSIC_DISC, settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).maxCount(1));
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 1.5F;
    }
}