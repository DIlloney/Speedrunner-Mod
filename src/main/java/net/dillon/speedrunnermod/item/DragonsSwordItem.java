package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.util.MathUtil;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A weapon that {@code one-shots} the {@code ender dragon.}
 */
public class DragonsSwordItem extends SwordItem {

    public DragonsSwordItem(Settings settings) {
        super(ModToolMaterials.DRAGONS_SWORD, 9, -2.4F, settings);
    }

    /**
     * Kills the ender dragon instantly.
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof EnderDragonEntity dragon && options().stateOfTheArtItems.isDragonsSwordEnabled()) {
            if (!DOOM_MODE) {
                dragon.setHealth(0.0F);
            } else {
                attacker.serverDamage(attacker.getDamageSources().mobAttack(attacker), MathUtil.randomFloat(2.0F, 3.0F));
                attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TickCalculator.seconds(5), 0, false, true, true));
                attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, TickCalculator.seconds(2), 0, false, true, true));
                if (attacker instanceof PlayerEntity) {
                    ((PlayerEntity)attacker).sendMessage(Text.translatable("item.speedrunnermod.dragons_sword.failed").formatted(Formatting.LIGHT_PURPLE), false);
                }
            }
            stack.damage(ModToolMaterials.DRAGONS_SWORD.durability(), attacker, EquipmentSlot.MAINHAND);
        }
        return super.postHit(stack, target, attacker);
    }

    /**
     * The Dragon's sword always has an enchantment glint.
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.dragons_sword.tooltip").formatted(DOOM_MODE ? Formatting.STRIKETHROUGH : Formatting.WHITE));
            if (DOOM_MODE) {
                tooltip.add(Text.translatable("item.speedrunnermod.dragons_sword.doom_mode").formatted(Formatting.RED));
            }
            ItemUtil.stateOfTheArtItem(tooltip);
        }
    }
}