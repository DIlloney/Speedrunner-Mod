package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.MathUtil;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A weapon that can be used to instantly kill the ender dragon.
 */
public class DragonsSwordItem extends SwordItem {
    public DragonsSwordItem(Settings settings) {
        super(ModToolMaterials.DRAGONS_SWORD, 9, -2.4F, settings.rarity(Rarity.EPIC));
    }

    /**
     * Kills the ender dragon instantly.
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof EnderDragonEntity dragon && options().main.stateOfTheArtItems) {
            if (!DOOM_MODE) {
                stack.damage(ModToolMaterials.DRAGONS_SWORD.getDurability(), attacker, (e) -> attacker.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                dragon.setHealth(0.0F);
            } else {
                attacker.damage(attacker.getDamageSources().mobAttack(attacker), MathUtil.randomFloat(2.0F, 3.0F));
                attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TickCalculator.seconds(5), 0, false, true, true));
                attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, TickCalculator.seconds(2), 0, false, true, true));
                if (attacker instanceof PlayerEntity) {
                    ((PlayerEntity)attacker).sendMessage(Text.translatable("item.speedrunnermod.dragons_sword.failed").formatted(Formatting.LIGHT_PURPLE), false);
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }

    /**
     * The Dragon's sword will always have an enchantment glint.
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.dragons_sword.tooltip").formatted(DOOM_MODE ? Formatting.STRIKETHROUGH : Formatting.WHITE));
            if (DOOM_MODE) {
                tooltip.add(Text.translatable("item.speedrunnermod.dragons_sword.doom_mode").formatted(Formatting.RED));
            }
        }
    }
}