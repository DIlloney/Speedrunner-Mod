package com.dilloney.speedrunnermod.bases;

import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class ToolBases {

    public static class PickaxeBase extends PickaxeItem {
        public PickaxeBase(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super (material, attackDamage, attackSpeed, settings);
        }
    }

    public static class AxeBase extends AxeItem {
        public AxeBase(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super (material, attackDamage, attackSpeed, settings);
        }
    }

    public static class HoeBase extends HoeItem {
        public HoeBase(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super (material, attackDamage, attackSpeed, settings);
        }
    }
}
