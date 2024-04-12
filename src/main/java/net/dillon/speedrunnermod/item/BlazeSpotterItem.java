package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An item that teleports the player to the nearest blaze spawner.
 */
public class BlazeSpotterItem extends Item {
    private boolean confirm = !options().client.confirmMessages;

    public BlazeSpotterItem(Settings settings) {
        super(settings.maxCount(options().main.stackUnstackables ? 64 : 16));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (!options().advanced.disableBlazeSpotter) {
                if (world.getRegistryKey() == World.NETHER) {
                    BlockPos blazeSpawnerPos = this.findNearestBlazeSpawner((ServerWorld)world, player.getBlockPos());
                    if (blazeSpawnerPos != null) {
                        if (confirm) {
                            player.teleport(blazeSpawnerPos.getX() + 0.5F, blazeSpawnerPos.getY() + 1.0F, blazeSpawnerPos.getZ() + 0.5F, true);
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 1.0F, 1.0F);
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.HOSTILE, 3.0F, 0.6F);
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, TickCalculator.seconds(world.random.nextInt(4) + 7), 0, false, true, true));
                            player.getItemCooldownManager().set(this, TickCalculator.seconds(10));
                            if (!player.getAbilities().creativeMode) {
                                itemStack.decrement(1);
                            }
                            SpeedrunnerMod.debug("Teleported player" + player.getName().toString() + " (UUID: " + player.getUuidAsString() + ") to nearest blaze spawner, at X = " + player.getX() + ", Y = " + player.getY() + ", Z = " + player.getZ() + ".");
                        } else {
                            player.sendMessage(Text.translatable("item.speedrunnermod.blaze_spotter.found_blaze_spawner").formatted(ItemUtil.toFormatting(Formatting.GOLD, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                            player.sendMessage(Text.translatable("item.speedrunnermod.blaze_spotter.confirm"), false);
                        }
                        if (options().client.confirmMessages) {
                            confirm = !confirm;
                        }
                        player.swingHand(hand, true);
                        return TypedActionResult.success(player.getStackInHand(hand));
                    } else {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 3.0F);
                        player.sendMessage(Text.translatable("item.speedrunnermod.blaze_spotter.couldnt_find_spawner").formatted(ItemUtil.toFormatting(Formatting.GOLD, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                    }
                } else {
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 5.0F);
                    player.sendMessage(Text.translatable("item.speedrunnermod.blaze_spotter.wrong_dimension").formatted(ItemUtil.toFormatting(Formatting.GOLD, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(ItemUtil.toFormatting(Formatting.GOLD, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                info("Player " + player.getName().toString() + " (" + player.getUuidAsString() + ") tried to use Blaze Spotter, but is disabled!");
            }
        }

        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.blaze_spotter.tooltip"));
        }
    }

    /**
     * Finds the nearest blaze spawner.
     */
    @ChatGPT
    private BlockPos findNearestBlazeSpawner(ServerWorld world, BlockPos fortressPos) {
        for (BlockPos pos : BlockPos.iterate(fortressPos.add(options().advanced.blazeSpotterDistanceXYZ[0], options().advanced.blazeSpotterDistanceXYZ[1], options().advanced.blazeSpotterDistanceXYZ[2]), fortressPos.add(options().advanced.blazeSpotterDistanceXYZ[3], options().advanced.blazeSpotterDistanceXYZ[4], options().advanced.blazeSpotterDistanceXYZ[5]))) {
            if (world.getBlockState(pos).getBlock() == Blocks.SPAWNER) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof MobSpawnerBlockEntity) {
                    MobSpawnerBlockEntity spawnerBlockEntity = (MobSpawnerBlockEntity) blockEntity;
                    if (spawnerBlockEntity.getLogic().getRenderedEntity(world, world.random, pos).getType() == EntityType.BLAZE) {
                        if (!world.getBlockState(pos.up()).isAir() || !world.getBlockState(pos.up(1)).isAir()) {
                            SpeedrunnerMod.debug("Detected blocks above blaze spawner were not air, so setting to air.");
                            for (int i = 1; i < 3; i++) {
                                world.setBlockState(pos.up(i), Blocks.AIR.getDefaultState(), 3);
                            }
                        }
                        return pos.toImmutable();
                    }
                }
            }
        }

        return null;
    }
}