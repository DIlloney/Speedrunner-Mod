package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.TntBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TntBlock.class)
public class TntBlockMixin extends Block {

    @Deprecated
    public TntBlockMixin(Settings settings) {
        super(settings);
    }

    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean allowSpeedrunnerFlintAndSteelsOnTntBlocks(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.FLINT_AND_STEELS) || stack.isOf(Items.FIRE_CHARGE);
    }
}