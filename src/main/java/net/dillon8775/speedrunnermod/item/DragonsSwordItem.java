<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.util.MathUtil;
import net.dillon8775.speedrunnermod.util.TickCalculator;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.*;

/**
 * A weapon that can be used to instantly kill the ender dragon.
 */
public class DragonsSwordItem extends SwordItem {
    public DragonsSwordItem(Settings settings) {
        super(ModToolMaterials.DRAGONS_SWORD, 9, -2.4F, settings.rarity(Rarity.EPIC).group(ItemGroup.COMBAT));
    }

    /**
     * Kills the ender dragon instantly.
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (options().advanced.disableDragonsSword) {
            info("Entity " + attacker.getName().toString() + " (" + attacker.getUuidAsString() + ") tried to use Dragon's Sword, but is disabled!");
        } else if (target instanceof EnderDragonEntity dragon) {
            if (!DOOM_MODE) {
                stack.damage(ModToolMaterials.DRAGONS_SWORD.getDurability(), attacker, (e) -> attacker.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                dragon.setHealth(0.0F);
            } else {
                attacker.damage(DamageSource.mob(target), MathUtil.randomFloat(2.0F, 3.0F));
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
=======
package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.util.MathUtil;
import net.dillon8775.speedrunnermod.util.TickCalculator;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.*;

/**
 * A weapon that can be used to instantly kill the ender dragon.
 */
public class DragonsSwordItem extends SwordItem {
    public DragonsSwordItem(Settings settings) {
        super(ModToolMaterials.DRAGONS_SWORD, 9, -2.4F, settings.rarity(Rarity.EPIC).group(ItemGroup.COMBAT));
    }

    /**
     * Kills the ender dragon instantly.
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (options().advanced.disableDragonsSword) {
            info("Entity " + attacker.getName().toString() + " (" + attacker.getUuidAsString() + ") tried to use Dragon's Sword, but is disabled!");
        } else if (target instanceof EnderDragonEntity dragon) {
            if (!DOOM_MODE) {
                stack.damage(ModToolMaterials.DRAGONS_SWORD.getDurability(), attacker, (e) -> attacker.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                dragon.setHealth(0.0F);
            } else {
                attacker.damage(DamageSource.mob(target), MathUtil.randomFloat(2.0F, 3.0F));
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
>>>>>>> Stashed changes
}