package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EnderPearlItem.class)
public class EnderPearlItemMixin extends Item {

    public EnderPearlItemMixin(Settings settings) {
        super(settings);
    }

    /**
     * Allows ender pearls to work correctly when {@code Infini Pearl mode} is on, also adds the {@code cooldown enchantment.}
     */
    @Author(Authors.DUNCANRUNS)
    @Overwrite
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        boolean bl = EnchantmentHelper.getLevel(ItemUtil.enchantment(player, Enchantments.INFINITY), itemStack) > 0;
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.random.nextFloat() * 0.4F + 0.8F));
        int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ItemUtil.enchantment(player, ModEnchantments.COOLDOWN), player);
        int cooldown = coolEnchantment > 3 ? 0 : coolEnchantment == 3 ? 5 : coolEnchantment == 2 ? 10 : coolEnchantment == 1 ? 15 : 20;
        player.getItemCooldownManager().set(this, cooldown);

        if (!world.isClient) {
            EnderPearlEntity enderPearlEntity = new EnderPearlEntity(world, player);
            enderPearlEntity.setItem(itemStack);
            enderPearlEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(enderPearlEntity);
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!player.getAbilities().creativeMode && !bl) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}