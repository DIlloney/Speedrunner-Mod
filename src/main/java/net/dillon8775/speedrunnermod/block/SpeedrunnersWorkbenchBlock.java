package net.dillon8775.speedrunnermod.block;

import net.dillon8775.speedrunnermod.option.ModOptions;
import net.dillon8775.speedrunnermod.util.ItemUtil;
import net.dillon8775.speedrunnermod.util.MathUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * A block that allows transferring of enchantments to other items.
 * <p>Also used as the retired speedrunner's job site block, acts like a smithing table.</p>
 */
public class SpeedrunnersWorkbenchBlock extends SmithingTableBlock {

    public SpeedrunnersWorkbenchBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && player.getMainHandStack().hasEnchantments()) {
            ItemStack mainHandStack = player.getMainHandStack();
            ItemStack offHandStack = player.getOffHandStack();
            Map<Enchantment, Integer> mainHandEnchantments = EnchantmentHelper.get(mainHandStack);
            Map<Enchantment, Integer> offHandEnchantments = EnchantmentHelper.get(offHandStack);

            List<Enchantment> enchantmentsToRemove = new ArrayList<>();
            int totalTransferred = 0;
            for (Map.Entry<Enchantment, Integer> entry: mainHandEnchantments.entrySet()) {
                Enchantment enchantment = entry.getKey();
                int level = entry.getValue();

                if (enchantment.isAcceptableItem(offHandStack)) {
                    offHandEnchantments.put(enchantment, level);
                    enchantmentsToRemove.add(enchantment);
                    totalTransferred++;
                }
            }

            for (Enchantment enchantment : enchantmentsToRemove) {
                mainHandEnchantments.remove(enchantment);
            }

            int cost = MathUtil.multiplyBySelf(enchantmentsToRemove.size());
            if (cost > options().main.anvilCostLimit && options().main.anvilCostLimit != 50) {
                cost = options().main.anvilCostLimit;
            }

            if (player.getAbilities().creativeMode) {
                cost = 0;
            }

            if (totalTransferred != 0 && player.experienceLevel >= cost) {
                EnchantmentHelper.set(mainHandEnchantments, mainHandStack);
                EnchantmentHelper.set(offHandEnchantments, offHandStack);
                player.sendMessage(new TranslatableText("speedrunnermod.transferred_enchantments").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                world.playSound(null, pos, SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
                player.addExperienceLevels(-cost);
                player.setStackInHand(hand, mainHandStack);
            } else {
                player.sendMessage(new TranslatableText("speedrunnermod.no_enchantments_transferred").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                if (!(player.experienceLevel >= cost)) {
                    player.sendMessage(new TranslatableText("speedrunnermod.levels_needed", cost), false);
                }
            }
            return ActionResult.SUCCESS;
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }
}