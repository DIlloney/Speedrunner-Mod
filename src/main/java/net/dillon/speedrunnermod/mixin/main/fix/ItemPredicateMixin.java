package net.dillon.speedrunnermod.mixin.main.fix;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerShearsItem;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemPredicate.class)
public class ItemPredicateMixin {

    /**
     * Fixes {@code speedrunner shears} not working as {@code "shears".}
     */
    @Author(Authors.UNKNOWN)
    @ModifyVariable(method = "test", at = @At("HEAD"), argsOnly = true)
    private ItemStack fixSpeedrunnerShears(ItemStack stack) {
        if (stack.getItem().getDefaultStack().isOf(ModItems.SPEEDRUNNER_SHEARS)) {
            ItemStack itemStack = new ItemStack(Items.SHEARS);
            itemStack.setCount(stack.getCount());
            itemStack.set(DataComponentTypes.TOOL, SpeedrunnerShearsItem.createSpeedrunnerShears());
            return itemStack;
        }

        return stack;
    }
}