package net.dillon.speedrunnermod.block;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.util.MathUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.component.DataComponentTypes;
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

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A block that allows transferring of enchantments to other items.
 * <p>Also used as the retired speedrunner's job site block, acts like a smithing table.</p>
 */
public class SpeedrunnersWorkbenchBlock extends SmithingTableBlock {

    public SpeedrunnersWorkbenchBlock(Settings settings) {
        super(settings);
    }

    @Override @ChatGPT(Credit.PARTIAL_CREDIT)
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient && player.getMainHandStack().hasEnchantments() && !player.getOffHandStack().isEmpty()) {
            ItemStack mainHandStack = player.getMainHandStack();
            ItemStack offHandStack = player.getOffHandStack();
            ItemEnchantmentsComponent mainHandEnchantments = EnchantmentHelper.getEnchantments(mainHandStack);
            ItemEnchantmentsComponent offHandEnchantments = EnchantmentHelper.getEnchantments(offHandStack);
            ItemEnchantmentsComponent.Builder mainHandBuilder = new ItemEnchantmentsComponent.Builder(mainHandEnchantments);
            ItemEnchantmentsComponent.Builder offHandBuilder = new ItemEnchantmentsComponent.Builder(offHandEnchantments);

            ItemEnchantmentsComponent enchantmentsToRemove = null;

            int totalTransferred = 0;
            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : mainHandEnchantments.getEnchantmentEntries()) {
                RegistryEntry registryEntry = entry.getKey();

                boolean offhandHasEnchantments = EnchantmentHelper.hasEnchantments(offHandStack);
                if (offhandHasEnchantments) {
                    for (RegistryEntry<Enchantment> registryEntry2 : offHandBuilder.getEnchantments()) {
                        boolean allIsCompatible = true;

                        for (RegistryEntry<Enchantment> existingEnchantment : offHandBuilder.getEnchantments()) {
                            if (!Enchantment.canBeCombined(existingEnchantment, registryEntry) && !existingEnchantment.equals(registryEntry)) {
                                allIsCompatible = false;
                                break;
                            }
                        }

                        boolean canUpgrade = registryEntry2.equals(registryEntry) && offHandBuilder.getLevel(registryEntry2) < mainHandBuilder.getLevel(registryEntry);
                        if (allIsCompatible && (Enchantment.canBeCombined(registryEntry, registryEntry2) || canUpgrade)) {
                            EnchantmentHelper.apply(offHandStack, builder -> builder.add(entry.getKey(), mainHandBuilder.getLevel(entry.getKey())));
                            player.sendMessage(Text.translatable("speedrunnermod.transferred_enchantments").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                            if (canUpgrade) {
                                player.sendMessage(Text.translatable("speedrunnermod.enchantment_levels_upgraded"), false);
                            }
                            totalTransferred++;
                        } else {
                            if (!Enchantment.canBeCombined(registryEntry, registryEntry2)) {
                                if (totalTransferred > 0) {
                                    player.sendMessage(Text.translatable("speedrunnermod.some_incompatible_enchantments_failed"), false);
                                } else {
                                    player.sendMessage(Text.translatable("speedrunnermod.incompatible_enchantments_failed").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                                }
                            }
                        }
                    }
                } else {
                    EnchantmentHelper.set(offHandStack, mainHandEnchantments);
                }
            }

//            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : enchantmentsToRemove.getEnchantmentEntries()) {
//                enchantmentsToRemove.getEnchantmentEntries().remove(entry);
//            }
//
//            if (enchantmentsToRemove != null) {
//                EnchantmentHelper.set(mainHandStack, enchantmentsToRemove);
//            }

            int cost = MathUtil.multiplyBySelf(enchantmentsToRemove.getSize());
            if (cost > options().main.anvilCostLimit && options().main.anvilCostLimit != 50) {
                cost = options().main.anvilCostLimit;
            }

            if (player.getAbilities().creativeMode) {
                cost = 0;
            }

            if (totalTransferred != 0 && player.experienceLevel >= cost) {
                EnchantmentHelper.set(mainHandStack, mainHandEnchantments);
                EnchantmentHelper.set(offHandStack, offHandEnchantments);
                world.playSound(null, pos, SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
                player.addExperienceLevels(-cost);
                player.setStackInHand(player.getActiveHand(), mainHandStack);
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