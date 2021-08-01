package com.dilloney.speedrunnermod.util;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class PersistanceItems {
    private static ArrayList<PersistanceItems> persistentPlayers = new ArrayList();
    private String player;
    private ItemStack itemStack;

    public PersistanceItems(String player, ItemStack itemStack) {
        this.player = player;
        this.itemStack = itemStack;
    }

    public String getPlayerUUID() {
        return this.player;
    }

    public ItemStack getStack() {
        return this.itemStack;
    }

    public static void addItems(PersistanceItems player) {
        persistentPlayers.add(player);
    }

    public static ArrayList<PersistanceItems> getItems() {
        return persistentPlayers;
    }
}