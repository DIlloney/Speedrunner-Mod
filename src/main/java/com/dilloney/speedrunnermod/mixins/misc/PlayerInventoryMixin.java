package com.dilloney.speedrunnermod.mixins.misc;

import java.util.Iterator;
import java.util.List;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.dilloney.speedrunnermod.util.PersistanceItems;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    @Shadow @Final List<DefaultedList<ItemStack>> field_7543; PlayerInventory playerinv = (PlayerInventory)(Object)this;

    @Overwrite
    public void dropAll() {
        if (SpeedrunnerMod.CONFIG.manhuntMode) {
            Iterator var1 = this.field_7543.iterator();

            while(var1.hasNext()) {
                List<ItemStack> list = (List)var1.next();

                for(int i = 0; i < list.size(); ++i) {
                    ItemStack itemStack = (ItemStack)list.get(i);
                    if (!itemStack.isEmpty()) {
                        if (itemStack.hasNbt() && itemStack.getNbt().contains("huntercompass") && itemStack.getNbt().getBoolean("huntercompass")) {
                            PersistanceItems newItem = new PersistanceItems(this.playerinv.player.getUuidAsString(), itemStack.copy());
                            PersistanceItems.addItems(newItem);
                        } else {
                            this.playerinv.player.dropItem(itemStack, true, false);
                            list.set(i, ItemStack.EMPTY);
                        }
                    }
                }
            }
        }
    }
}