
package com.dilloney.speedrunnermod.util;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashSet;
import java.util.Set;

public final class UniqueItemRegistry {

    public static final UniqueItemRegistry SHEARS = new UniqueItemRegistry(Items.SHEARS);
    public static final UniqueItemRegistry BOW = new UniqueItemRegistry(Items.BOW);
    public static final UniqueItemRegistry CROSSBOW = new UniqueItemRegistry(Items.CROSSBOW);
    public static final UniqueItemRegistry FISHING_ROD = new UniqueItemRegistry(Items.FISHING_ROD);
    public static final UniqueItemRegistry CARROT_ON_A_STICK = new UniqueItemRegistry(Items.CARROT_ON_A_STICK);
    public static final UniqueItemRegistry WARPED_FUNGUS_ON_A_STICK = new UniqueItemRegistry(Items.WARPED_FUNGUS_ON_A_STICK);

    private final Set<Item> itemList;

    final Item defaultItem;

    private UniqueItemRegistry(Item defaultItem) {
        this.defaultItem = defaultItem;
        itemList = new HashSet<>();
    }

    public void addItemToRegistry(Item item) {
        itemList.add(item);
    }

    public boolean isItemInRegistry(Item item) {
        return itemList.contains(item);
    }
}
