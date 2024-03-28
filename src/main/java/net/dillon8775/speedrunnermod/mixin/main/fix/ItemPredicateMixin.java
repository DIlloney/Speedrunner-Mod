package net.dillon8775.speedrunnermod.mixin.main.fix;

import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.util.Author;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemPredicate.class)
public class ItemPredicateMixin {

    /**
     * Fixes the speedrunner shears not working correctly.
     */
    @Author("UNKNOWN")
    @ModifyVariable(method = "test", at = @At("HEAD"))
    private ItemStack fixSpeedrunnerShears(ItemStack stack) {
        if (stack.getItem().getDefaultStack().isOf(ModItems.SPEEDRUNNER_SHEARS)) {
            ItemStack itemStack = new ItemStack(Items.SHEARS);
            itemStack.setCount(stack.getCount());
            itemStack.setNbt(stack.getOrCreateNbt());
            return itemStack;
        }

        return stack;
    }
}