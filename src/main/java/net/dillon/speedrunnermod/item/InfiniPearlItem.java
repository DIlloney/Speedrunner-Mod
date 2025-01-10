package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An {@code ender pearl} like item that does not get consumed nor do damage upon use.
 */
public class InfiniPearlItem extends EnderPearlItem {

    public InfiniPearlItem(Settings settings) {
        super(settings.maxCount(1).component(DataComponentTypes.CUSTOM_NAME, Text.translatable("item.speedrunnermod.infini_pearl").formatted(Formatting.AQUA).formatted(Formatting.ITALIC)));
    }

    /**
     * Acts pretty much exactly like an {@code ender pearl,} just removing the item decrement and entity damage.
     */
    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.random.nextFloat() * 0.4F + 0.8F));
        int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ItemUtil.enchantment(player, ModEnchantments.COOLDOWN), player);
        int cooldown = coolEnchantment > 3 ? 0 : coolEnchantment == 3 ? 5 : coolEnchantment == 2 ? 10 : coolEnchantment == 1 ? 15 : 20;
        player.getItemCooldownManager().set(this.getDefaultStack(), cooldown);

        if (world instanceof ServerWorld serverWorld) {
            ProjectileEntity.spawnWithVelocity(EnderPearlEntity::new, serverWorld, itemStack, player, 0.0F, 1.5F, 1.0F);
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));

        return ActionResult.SUCCESS;
    }

    /**
     * The InfiniPearl always has an enchantment glint.
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.infini_pearl.tooltip.line1"));
            tooltip.add(Text.translatable("item.speedrunnermod.infini_pearl.tooltip.line2"));
        }
    }
}