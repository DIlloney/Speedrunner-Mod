//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dilloney.speedrunnermod.util;

import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class Speedrunners {
    private static ArrayList<String> playerList = new ArrayList();

    public static void removeRunner(String player) {
        playerList.remove(player);
    }

    public static void addRunner(String player) {
        playerList.add(player);
    }

    public static ArrayList<String> getRunners(World world, Boolean names) {
        if (!names) {
            return playerList;
        } else {
            ArrayList<String> nameList = new ArrayList();
            Iterator var3 = playerList.iterator();

            while(var3.hasNext()) {
                String str = (String)var3.next();
                nameList.add(world.getPlayerByUuid(UUID.fromString(str)).getEntityName().toString());
            }

            return nameList;
        }
    }
}