package net.dillon.speedrunnermod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Better than iron, worse than diamond, deals more damage to withers and giants.
 */
public class SpeedrunnerSwordItem extends SwordItem {

    public SpeedrunnerSwordItem(int attackDamage, Settings settings) {
        super(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, attackDamage, -2.4F, settings.group(ItemGroup.COMBAT));
    }

    /**
     * Deals more damage to withers, and giants under certain conditions.
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof WitherEntity) {
            target.damage(DamageSource.player((PlayerEntity)attacker), getAttackDamage() * 4.45F);
        } else if (target instanceof GiantEntity giant) {
            if (!(giant.getHealth() <= 150)) {
                target.damage(DamageSource.player((PlayerEntity)attacker), getAttackDamage() * 2.25F);
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            tooltip.add(new TranslatableText("item.speedrunnermod.speedrunner_sword.tooltip.line1").formatted(Formatting.GRAY));
            if (DOOM_MODE) {
                tooltip.add(new TranslatableText("item.speedrunnermod.speedrunner_sword.tooltip.line2"));
            }
        }
    }
}