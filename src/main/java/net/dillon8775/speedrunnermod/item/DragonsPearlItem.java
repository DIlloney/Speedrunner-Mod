package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.dillon8775.speedrunnermod.util.TickCalculator;
import net.dillon8775.speedrunnermod.util.TimeCalculator;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * An item that makes the ender dragon instantly perch upon use.
 */
public class DragonsPearlItem extends Item {

    public DragonsPearlItem(Settings settings) {
        super(settings.maxCount(16).rarity(Rarity.EPIC).group(ItemGroup.MISC));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient) {
            if (!options().advanced.disableDragonsPearl) {
                if (world.getRegistryKey() == World.END) {
                    List<EnderDragonEntity> dragons = world.getEntitiesByClass(EnderDragonEntity.class, player.getBoundingBox().expand(options().advanced.dragonsPearlDragonDistanceXYZ[0], options().advanced.dragonsPearlDragonDistanceXYZ[1], options().advanced.dragonsPearlDragonDistanceXYZ[2]), entity -> true);

                    if (!dragons.isEmpty()) {
                        EnderDragonEntity enderDragon = dragons.get(0);
                        boolean isSitting = enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.SITTING_SCANNING ||
                                enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.SITTING_FLAMING ||
                                enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.SITTING_ATTACKING;
                        boolean isAlreadyPerchingOrPerched =
                                        enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.LANDING || isSitting;
                        boolean isDead = enderDragon.getHealth() < 1.0F;
                        if (!isAlreadyPerchingOrPerched && !isDead) {
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 2.0F, 0.3F);
                            player.getItemCooldownManager().set(this, TickCalculator.seconds(10));
                            if (!player.getAbilities().creativeMode) {
                                itemStack.decrement(1);
                            }
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    enderDragon.getPhaseManager().setPhase(PhaseType.LANDING);
                                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.HOSTILE, 3.0F, 0.65F);
                                    SpeedrunnerMod.debug("Perched ender dragon. (UUID = " + enderDragon.getUuidAsString());
                                }
                            }, TimeCalculator.secondsToMilliseconds(2));
                            return TypedActionResult.success(itemStack);
                        } else {
                            if (!isDead) {
                                if (isSitting) {
                                    player.sendMessage(new TranslatableText("item.speedrunnermod.dragons_pearl.already_perched").formatted(Formatting.LIGHT_PURPLE), ModOptions.ItemMessages.isActionbar());
                                } else {
                                    player.sendMessage(new TranslatableText("item.speedrunnermod.dragons_pearl.already_perching").formatted(Formatting.LIGHT_PURPLE), ModOptions.ItemMessages.isActionbar());
                                }
                            } else {
                                player.sendMessage(new TranslatableText("item.speedrunnermod.dragons_pearl.dragon_dead").formatted(Formatting.LIGHT_PURPLE), ModOptions.ItemMessages.isActionbar());
                            }
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 5.0F);
                        }
                    } else {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 3.0F);
                        player.sendMessage(new TranslatableText("item.speedrunnermod.dragons_pearl.cannot_find_dragon").formatted(Formatting.LIGHT_PURPLE), ModOptions.ItemMessages.isActionbar());
                    }
                } else {
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 5.0F);
                    player.sendMessage(new TranslatableText("item.speedrunnermod.dragons_pearl.wrong_dimension").formatted(Formatting.LIGHT_PURPLE), ModOptions.ItemMessages.isActionbar());
                }
                player.swingHand(hand, true);
            } else {
                player.sendMessage(new TranslatableText("item.speedrunnermod.item_disabled"), false);
                info("Player " + player.getName().asString() + " (" + player.getUuidAsString() + ") tried to use Dragon's Pearl, but is disabled!");
            }
        }

        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            tooltip.add(new TranslatableText("item.speedrunnermod.dragons_pearl.tooltip"));
        }
    }
}