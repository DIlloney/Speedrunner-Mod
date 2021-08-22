package com.dilloney.speedrunnermod.mixins.entity.player;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.dilloney.speedrunnermod.util.manhunt.PersistanceItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    @Shadow @Final List<DefaultedList<ItemStack>> combinedInventory; PlayerInventory playerinv = (PlayerInventory)(Object)this;
    @Shadow @Final PlayerEntity player;

    /**
     * Makes the Compass work correctly when Manhunt Mode is enabled.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public void dropAll() {
        Iterator var1 = this.combinedInventory.iterator();
        int i;
        ItemStack itemStack;
        if (SpeedrunnerMod.OPTIONS.manhuntMode) {
            while(var1.hasNext()) {
                List<ItemStack> list = (List)var1.next();

                for(i = 0; i < list.size(); ++i) {
                    itemStack = (ItemStack)list.get(i);
                    if (!itemStack.isEmpty()) {
                        if (itemStack.hasTag() && itemStack.getTag().contains("huntercompass") && itemStack.getTag().getBoolean("huntercompass")) {
                            PersistanceItems newItem = new PersistanceItems(this.playerinv.player.getUuidAsString(), itemStack.copy());
                            PersistanceItems.addItems(newItem);
                            this.player.dropItem(itemStack, true, false);
                            list.set(i, ItemStack.EMPTY);
                        }
                    }
                }
            }
        } else {
            while(var1.hasNext()) {
                List<ItemStack> list = (List)var1.next();

                for(i = 0; i < list.size(); ++i) {
                    itemStack = (ItemStack)list.get(i);
                    if (!itemStack.isEmpty()) {
                        this.player.dropItem(itemStack, true, false);
                        list.set(i, ItemStack.EMPTY);
                    }
                }
            }
        }
    }
}