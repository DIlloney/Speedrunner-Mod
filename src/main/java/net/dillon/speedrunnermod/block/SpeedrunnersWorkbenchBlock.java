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

            int totalTransferred = 0;
            int cost = 0;
            boolean successWithEnchantments = false;
            boolean successWithNoEnchantments = false;
            boolean fail = false;
            boolean wasUpgraded = false;
            boolean someIncFailed = false;
            boolean incompatibleEnchantmentsFailed = false;
            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : mainHandEnchantments.getEnchantmentEntries()) {
                RegistryEntry registryEntry = entry.getKey();
                Enchantment enchantment = (Enchantment)registryEntry.value();

                boolean offhandHasEnchantments = EnchantmentHelper.hasEnchantments(offHandStack);
                boolean allIsCompatible = true;
                if (offhandHasEnchantments) {
                    for (RegistryEntry<Enchantment> registryEntry2 : offHandBuilder.getEnchantments()) {

                        for (RegistryEntry<Enchantment> existingEnchantment : offHandBuilder.getEnchantments()) {
                            if (!Enchantment.canBeCombined(existingEnchantment, registryEntry) && !existingEnchantment.equals(registryEntry)) {
                                allIsCompatible = false;
                                break;
                            }
                        }

                        boolean canUpgrade = registryEntry2.equals(registryEntry) && offHandBuilder.getLevel(registryEntry2) < mainHandBuilder.getLevel(registryEntry);
                        if (allIsCompatible && (Enchantment.canBeCombined(registryEntry, registryEntry2) || canUpgrade)) {

                            totalTransferred++;
                            cost = initializeCost(player, totalTransferred);

                            if (totalTransferred != 0 && player.experienceLevel >= cost) {
                                successWithEnchantments = true;
                                if (canUpgrade) {
                                    wasUpgraded = true;
                                }
                                applyEnchantments(enchantment, offHandStack, mainHandBuilder, entry);
                                SpeedrunnerMod.error("HAS enchantment transfer type");
                            } else {
                                fail = true;
                            }
                        } else {
                            if (!Enchantment.canBeCombined(registryEntry, registryEntry2)) {
                                if (totalTransferred > 0) {
                                    someIncFailed = true;
                                } else {
                                    incompatibleEnchantmentsFailed = true;
                                }
                            }
                        }
                    }
                } else {
                    totalTransferred++;
                    cost = initializeCost(player, totalTransferred);
                    if (totalTransferred != 0 && player.experienceLevel >= cost) {
                        successWithNoEnchantments = true;
                        SpeedrunnerMod.error("NO enchantment transfer type");
                        applyEnchantments(enchantment, offHandStack, mainHandBuilder, entry);
                    } else {
                        fail = true;
                    }
                }
            }

            if (successWithEnchantments) {
                player.sendMessage(Text.translatable("speedrunnermod.transferred_enchantments").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                if (wasUpgraded) {
                    player.sendMessage(Text.translatable("speedrunnermod.enchantment_levels_upgraded"), false);
                }
                success(world, pos, player, mainHandStack, cost);
            } else if (successWithNoEnchantments) {
                success(world, pos, player, mainHandStack, cost);
            } else if (fail) {
                fail(player, cost);
            }

            if (someIncFailed) {
                player.sendMessage(Text.translatable("speedrunnermod.some_incompatible_enchantments_failed"), false);
            }
            if (incompatibleEnchantmentsFailed) {
                player.sendMessage(Text.translatable("speedrunnermod.incompatible_enchantments_failed").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
            }

            return ActionResult.success(true);
        } else {
            return super.onUse(state, world, pos, player, hit);
        }
    }

    /**
     * Applies the transferred enchantments to the item.
     */
    private static void applyEnchantments(Enchantment enchantment, ItemStack offHandStack, ItemEnchantmentsComponent.Builder mainHandBuilder, Object2IntMap.Entry<RegistryEntry<Enchantment>> entry) {
        if (enchantment.isAcceptableItem(offHandStack)) {
            EnchantmentHelper.apply(offHandStack, builder -> builder.add(entry.getKey(), mainHandBuilder.getLevel(entry.getKey())));
        } else {
            EnchantmentHelper.apply(offHandStack, builder -> builder.remove(enchantmentRegistryEntry -> !enchantmentRegistryEntry.value().isAcceptableItem(offHandStack)));
        }
    }

    /**
     * Corrects the {@code cost} variable to equal the total amount of enchantments transferred multiplied by itself.
     */
    private static int initializeCost(PlayerEntity player, int totalTransferred) {
        int cost = MathUtil.multiplyBySelf(totalTransferred);

        if (cost > options().main.anvilCostLimit && options().main.anvilCostLimit != 50) {
            cost = options().main.anvilCostLimit;
        }

        if (player.getAbilities().creativeMode) {
            cost = 0;
        }

        return cost;
    }

    /**
     * A successful enchantment transfer.
     */
    private static void success(World world, BlockPos pos, PlayerEntity player, ItemStack mainHandStack, int cost) {
        world.playSound(null, pos, SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
        player.addExperienceLevels(-cost);
        player.setStackInHand(player.getActiveHand(), mainHandStack);
    }

    /**
     * Failed to transfer any enchantments.
     */
    private static void fail(PlayerEntity player, int cost) {
        player.sendMessage(Text.translatable("speedrunnermod.no_enchantments_transferred").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
        if (!(player.experienceLevel >= cost)) {
            player.sendMessage(Text.translatable("speedrunnermod.levels_needed", cost), false);
        }
    }
}