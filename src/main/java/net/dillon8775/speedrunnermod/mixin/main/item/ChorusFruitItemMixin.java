<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.item;

import net.dillon8775.speedrunnermod.enchantment.CooldownEnchantment;
import net.dillon8775.speedrunnermod.enchantment.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ChorusFruitItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ChorusFruitItem.class)
public class ChorusFruitItemMixin extends Item {

    public ChorusFruitItemMixin(Settings settings) {
        super(settings);
    }

    /**
     * Adds the cooldown for the {@link CooldownEnchantment}.
     */
    @Overwrite
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if (!world.isClient) {
            double d = user.getX();
            double e = user.getY();
            double f = user.getZ();
            for (int i = 0; i < 16; ++i) {
                double g = user.getX() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                double h = MathHelper.clamp(user.getY() + (double)(user.getRandom().nextInt(16) - 8), (double)world.getBottomY(), (double)(world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1));
                double j = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                if (user.hasVehicle()) {
                    user.stopRiding();
                }
                if (!user.teleport(g, h, j, true)) continue;
                SoundEvent soundEvent = user instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                world.playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0f, 1.0f);
                user.playSound(soundEvent, 1.0f, 1.0f);
                break;
            }
            if (user instanceof PlayerEntity) {
                int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ModEnchantments.COOLDOWN, user);
                int cooldown = coolEnchantment > 3 ? 0 : coolEnchantment == 3 ? 5 : coolEnchantment == 2 ? 10 : coolEnchantment == 1 ? 15 : 20;
                ((PlayerEntity)user).getItemCooldownManager().set(this, cooldown);
            }
        }
        return itemStack;
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.item;

import net.dillon8775.speedrunnermod.enchantment.CooldownEnchantment;
import net.dillon8775.speedrunnermod.enchantment.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ChorusFruitItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ChorusFruitItem.class)
public class ChorusFruitItemMixin extends Item {

    public ChorusFruitItemMixin(Settings settings) {
        super(settings);
    }

    /**
     * Adds the cooldown for the {@link CooldownEnchantment}.
     */
    @Overwrite
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if (!world.isClient) {
            double d = user.getX();
            double e = user.getY();
            double f = user.getZ();
            for (int i = 0; i < 16; ++i) {
                double g = user.getX() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                double h = MathHelper.clamp(user.getY() + (double)(user.getRandom().nextInt(16) - 8), (double)world.getBottomY(), (double)(world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1));
                double j = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                if (user.hasVehicle()) {
                    user.stopRiding();
                }
                if (!user.teleport(g, h, j, true)) continue;
                SoundEvent soundEvent = user instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                world.playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0f, 1.0f);
                user.playSound(soundEvent, 1.0f, 1.0f);
                break;
            }
            if (user instanceof PlayerEntity) {
                int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ModEnchantments.COOLDOWN, user);
                int cooldown = coolEnchantment > 3 ? 0 : coolEnchantment == 3 ? 5 : coolEnchantment == 2 ? 10 : coolEnchantment == 1 ? 15 : 20;
                ((PlayerEntity)user).getItemCooldownManager().set(this, cooldown);
            }
        }
        return itemStack;
    }
>>>>>>> Stashed changes
}