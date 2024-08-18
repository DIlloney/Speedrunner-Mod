package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.dillon.speedrunnermod.util.TimeCalculator;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An item that teleports nearby piglin to the player upon right-clicking.
 */
public class PiglinAwakenerItem extends Item implements StateOfTheArtItem {
    private boolean confirm = !options().client.confirmMessages;

    public PiglinAwakenerItem(Settings settings) {
        super(settings.maxCount(16));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (options().main.stateOfTheArtItems) {
                if (world.getRegistryKey() == World.NETHER) {
                    List<PiglinEntity> piglins = world.getEntitiesByClass(PiglinEntity.class, player.getBoundingBox().expand(options().advanced.piglinAwakenerPiglinDistanceXYZ[0], options().advanced.piglinAwakenerPiglinDistanceXYZ[1], options().advanced.piglinAwakenerPiglinDistanceXYZ[2]), entity -> true);

                    if (!piglins.isEmpty()) {
                        boolean isSafe = player.getAbilities().creativeMode;
                        boolean hasGold = player.getInventory().contains(new ItemStack(Items.GOLD_INGOT));
                        if (player.getAbilities().creativeMode) {
                            hasGold = true;
                        }
                        for (ItemStack armorItems : player.getArmorItems()) {
                            Item item = armorItems.getItem();
                            if (item instanceof ArmorItem armorItem && armorItem.getDefaultStack().isIn(ModItemTags.PIGLIN_SAFE_ARMOR)) {
                                isSafe = true;
                            }
                        }

                        if (isSafe) {
                            if (hasGold) {
                                if (confirm) {
                                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_ANGRY, SoundCategory.HOSTILE, 3.0F, 1.0F);
                                    player.getItemCooldownManager().set(this, TickCalculator.seconds(20));
                                    if (!player.getAbilities().creativeMode) {
                                        stack.decrement(1);
                                    }
                                    new Timer().schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            int maxNumberOfPiglin = 0;
                                            for (PiglinEntity piglin : piglins) {
                                                if (!piglin.isBaby() && !piglin.hasCustomName()) {
                                                    if (world.random.nextFloat() < 0.50F) {
                                                        piglin.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, TickCalculator.minutes(2), 0, false, true, false));
                                                    }
                                                    double x = !player.isSneaking() ? player.getX() + world.random.nextInt(7) - 3 : player.getX();
                                                    double y = !player.isSneaking() ? player.getY() + world.random.nextDouble() * (2.0 - 0.5) + 0.5 : player.getY();
                                                    double z = !player.isSneaking() ? player.getZ() + world.random.nextInt(7) - 3 : player.getZ();
                                                    piglin.teleport(x, y, z, false);
                                                    maxNumberOfPiglin++;
                                                }
                                                if (maxNumberOfPiglin >= SpeedrunnerMod.getMaximumAmountOfPiglinAllowedViaPiglinAwakener()) {
                                                    break;
                                                }
                                            }
                                        }
                                    }, TimeCalculator.secondsToMilliseconds(2));
                                } else {
                                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.HOSTILE, 3.0F, 1.0F);
                                    player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.got_piglins").formatted(ItemUtil.toFormatting(Formatting.GOLD, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                                    player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.confirm"), false);
                                }
                                if (options().client.confirmMessages) {
                                    confirm = !confirm;
                                }
                                player.swingHand(hand, true);
                                return TypedActionResult.success(stack);
                            } else {
                                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.NEUTRAL, 3.0F, 1.0F);
                                player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.no_gold_ingot").formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                            }
                        } else {
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.NEUTRAL, 1.5F, 1.0F);
                            player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.unsafe").formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                        }
                    } else {
                        player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.couldnt_find_piglins").formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                    }
                } else {
                    player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.wrong_dimension").formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(ItemUtil.toFormatting(Formatting.GOLD, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
            }
        }

        return TypedActionResult.consume(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.piglin_awakener.tooltip"));
            ItemUtil.stateOfTheArtItem(tooltip);
        }
    }
}