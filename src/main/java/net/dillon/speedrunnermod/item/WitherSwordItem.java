package net.dillon.speedrunnermod.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A sword with very low durability, but can one-shot wither skeletons, and guarantees a wither skeleton skull.
 */
public class WitherSwordItem extends SwordItem {

    public WitherSwordItem(Settings settings) {
        super(ModToolMaterials.WITHER_SWORD, 9, -2.4F, settings);
    }

    /**
     * Kills wither skeletons instantly, and makes them drop a wither skull.
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof WitherSkeletonEntity witherSkeleton) {
            if (witherSkeleton.getRandom().nextInt(100) < 45) {
                witherSkeleton.dropItem(witherSkeleton.getServer().getWorld(witherSkeleton.getEntityWorld().getRegistryKey()), Items.WITHER_SKELETON_SKULL);
                stack.damage(ModToolMaterials.WITHER_SWORD.durability(), attacker, EquipmentSlot.MAINHAND);
            }
            witherSkeleton.kill(witherSkeleton.getServer().getWorld(witherSkeleton.getEntityWorld().getRegistryKey()));
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.wither_sword.tooltip.line1").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.speedrunnermod.wither_sword.tooltip.line2").formatted(Formatting.GRAY));
        }
    }
}