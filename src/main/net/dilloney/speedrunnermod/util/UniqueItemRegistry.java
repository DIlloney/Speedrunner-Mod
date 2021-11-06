package net.dilloney.speedrunnermod.util;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashSet;
import java.util.Set;

/**
 * A helper class used for allowing modded items to work correctly (1.16 exclusive)
 * <p> Thanks to {@code oroarmor} for this. </p>
 */
@Deprecated
public class UniqueItemRegistry {

    public static final UniqueItemRegistry SHEARS = new UniqueItemRegistry(Items.SHEARS);
    public static final UniqueItemRegistry SHIELD = new UniqueItemRegistry(Items.SHIELD);
    public static final UniqueItemRegistry BOW = new UniqueItemRegistry(Items.BOW);
    public static final UniqueItemRegistry CROSSBOW = new UniqueItemRegistry(Items.CROSSBOW);
    public static final UniqueItemRegistry FLINT_AND_STEEL = new UniqueItemRegistry(Items.FLINT_AND_STEEL);
    public static final UniqueItemRegistry TNT_BLOCK_IGNITERS = new UniqueItemRegistry(Items.FLINT_AND_STEEL);

    private final Set<Item> itemList;

    private final Item defaultItem;

    private UniqueItemRegistry(Item defaultItem) {
        this.defaultItem = defaultItem;
        itemList = new HashSet<>();
    }

    public void addItemToRegistry(Item item) {
        itemList.add(item);
    }

    public Item getDefaultItem(Item item) {
        if (isItemInRegistry(item)) {
            return defaultItem;
        }
        return item;
    }

    public boolean isItemInRegistry(Item item) {
        return itemList.contains(item);
    }
}