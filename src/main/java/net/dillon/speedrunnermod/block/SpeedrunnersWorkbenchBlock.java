package net.dillon.speedrunnermod.block;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.util.MathUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A block that allows transferring of enchantments to other items.
 * <p>Also used as the retired speedrunner's job site block, acts like a smithing table.</p>
 */
public class SpeedrunnersWorkbenchBlock extends SmithingTableBlock {

    public SpeedrunnersWorkbenchBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient && player.getMainHandStack().hasEnchantments()) {
            ItemStack mainHandStack = player.getMainHandStack();
            ItemStack offHandStack = player.getOffHandStack();
            ItemEnchantmentsComponent mainHandEnchantments mainHandEnchantments = EnchantmentHelper.getEnchantments(mainHandStack);
            ItemEnchantmentsComponent offHandEnchantments = EnchantmentHelper.getEnchantments(offHandStack);

            List<Enchantment> enchantmentsToRemove = new ArrayList<>();
            int totalTransferred = 0;
//            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : mainHandEnchantments.getEnchantmentEntries()) {
//                RegistryEntry<?> registryEntry = entry.getKey();
//                Enchantment enchantment = (Enchantment)registryEntry.value();
//                int level = entry.getIntValue();
//
//                if (enchantment.isAcceptableItem(offHandStack)) {
//                    EnchantmentHelper.set(offHandStack, mainHandEnchantments);
//                    enchantmentsToRemove.add(enchantment);
//                }
//            }
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
                EnchantmentHelper.set(mainHandStack, mainHandEnchantments);
                EnchantmentHelper.set(offHandStack, offHandEnchantments);
                player.sendMessage(Text.translatable("speedrunnermod.transferred_enchantments").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                world.playSound(null, pos, SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
                player.addExperienceLevels(-cost);
                player.setStackInHand(hand, mainHandStack);
            } else {
                player.sendMessage(Text.translatable("speedrunnermod.no_enchantments_transferred").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                if (!(player.experienceLevel >= cost)) {
                    player.sendMessage(Text.translatable("speedrunnermod.levels_needed", cost), false);
                }
            }
            return ActionResult.SUCCESS;
        } else {
            return super.onUse(state, world, pos, player, hit);
        }
    }
}