package net.dillon8775.speedrunnermod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * A sword with very low durability, but can one-shot wither skeletons, and makes them drop a wither skeleton skull.
 */
public class WitherSwordItem extends SwordItem {

    public WitherSwordItem(Settings settings) {
        super(ModToolMaterials.WITHER_SWORD, 3, -2.4F, settings.group(ItemGroup.COMBAT));
    }

    /**
     * Kills wither skeletons instantly, and makes them drop a wither skull.
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof WitherSkeletonEntity witherSkeleton) {
            witherSkeleton.dropItem(Items.WITHER_SKELETON_SKULL);
            witherSkeleton.kill();
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            tooltip.add(new TranslatableText("item.speedrunnermod.wither_sword.tooltip.line1").formatted(Formatting.GRAY));
            tooltip.add(new TranslatableText("item.speedrunnermod.wither_sword.tooltip.line2").formatted(Formatting.GRAY));
        }
    }
}