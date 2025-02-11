package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.dillon.speedrunnermod.util.TimeCalculator;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An item that forces the {@code ender dragon} to {@code perch.}
 */
public class DragonsPearlItem extends Item {

    public DragonsPearlItem(Settings settings) {
        super(settings.maxCount(16).rarity(Rarity.EPIC));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient) {
            if (options().stateOfTheArtItems.isDragonsPearlEnabled()) {
                if (world.getRegistryKey() == World.END) {
                    List<EnderDragonEntity> dragons = world.getEntitiesByClass(EnderDragonEntity.class, player.getBoundingBox().expand(options().advanced.dragonsPearlDragonDistanceXYZ[0], options().advanced.dragonsPearlDragonDistanceXYZ[1], options().advanced.dragonsPearlDragonDistanceXYZ[2]), entity -> true);

                    if (!dragons.isEmpty()) {
                        EnderDragonEntity enderDragon = dragons.get(0);
                        if (!isDragonAlreadyPerchingOrPerched(enderDragon) && !isDragonDead(enderDragon)) {
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 2.0F, 0.3F);
                            player.getItemCooldownManager().set(this.getDefaultStack(), TickCalculator.seconds(30));
                            if (!player.getAbilities().creativeMode) {
                                itemStack.decrement(1);
                            }
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    enderDragon.getPhaseManager().setPhase(PhaseType.LANDING);
                                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.HOSTILE, 3.0F, 0.65F);
                                }
                            }, TimeCalculator.secondsToMilliseconds(2));
                            return ActionResult.SUCCESS;
                        } else {
                            if (!isDragonDead(enderDragon)) {
                                if (isDragonSitting(enderDragon)) {
                                    player.sendMessage(Text.translatable("item.speedrunnermod.dragons_pearl.already_perched").formatted(Formatting.LIGHT_PURPLE), options().client.itemMessages.isActionbar());
                                } else {
                                    player.sendMessage(Text.translatable("item.speedrunnermod.dragons_pearl.already_perching").formatted(Formatting.LIGHT_PURPLE), options().client.itemMessages.isActionbar());
                                }
                            } else {
                                player.sendMessage(Text.translatable("item.speedrunnermod.dragons_pearl.dragon_dead").formatted(Formatting.LIGHT_PURPLE), options().client.itemMessages.isActionbar());
                            }
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 5.0F);
                        }
                    } else {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 3.0F);
                        player.sendMessage(Text.translatable("item.speedrunnermod.dragons_pearl.cannot_find_dragon").formatted(Formatting.LIGHT_PURPLE), options().client.itemMessages.isActionbar());
                    }
                } else {
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 5.0F);
                    player.sendMessage(Text.translatable("item.speedrunnermod.dragons_pearl.wrong_dimension").formatted(Formatting.LIGHT_PURPLE), options().client.itemMessages.isActionbar());
                }
                player.swingHand(hand, true);
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(ItemUtil.toFormatting(Formatting.LIGHT_PURPLE, Formatting.WHITE)), options().client.itemMessages.isActionbar());
            }
        }

        return ActionResult.SUCCESS;
    }

    /**
     * Determines if the ender dragon is sitting or not.
     */
    private static boolean isDragonSitting(EnderDragonEntity enderDragon) {
        return enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.SITTING_SCANNING ||
                enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.SITTING_FLAMING ||
                enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.SITTING_ATTACKING;
    }

    /**
     * Determines if the ender dragon is perching or already perched.
     */
    private static boolean isDragonAlreadyPerchingOrPerched(EnderDragonEntity enderDragon) {
        return enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.LANDING || isDragonSitting(enderDragon);
    }

    /**
     * Checks to see if the ender dragon is dead.
     */
    private static boolean isDragonDead(EnderDragonEntity enderDragon) {
        return enderDragon.getHealth() < 1.0F;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.dragons_pearl.tooltip"));
            ItemUtil.stateOfTheArtItem(tooltip);
        }
    }
}