package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.dillon8775.speedrunnermod.util.ItemUtil;
import net.dillon8775.speedrunnermod.util.MathUtil;
import net.dillon8775.speedrunnermod.util.TickCalculator;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * An item that can be used to teleport to the surface.
 */
public class EnderThrusterItem extends Item {
    private boolean confirm = !options().client.confirmMessages;

    public EnderThrusterItem(Settings settings) {
        super(settings.maxCount(1).group(ItemGroup.MISC));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (!options().advanced.disableEnderThruster) {
                if (!(world.getRegistryKey() == World.NETHER)) {
                    int y = world.getTopY(Heightmap.Type.MOTION_BLOCKING, player.getBlockX(), player.getBlockZ());
                    BlockPos pos = new BlockPos(player.getBlockX(), y - 1, player.getBlockZ());
                    double playerY = MathUtil.roundToOneDecimalPlace(player.getY());

                    if (y != playerY && !(playerY > y)) {
                        if (confirm) {
                            player.getItemCooldownManager().set(this, TickCalculator.seconds(5));
                            if (!player.getAbilities().creativeMode) {
                                itemStack.decrement(1);
                            }

                            if (world.getBlockState(pos).getBlock() == Blocks.WATER) {
                                world.setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
                            } else if (world.getBlockState(pos).getBlock() == Blocks.LAVA) {
                                world.setBlockState(pos, Blocks.BASALT.getDefaultState());
                            } else {
                                world.setBlockState(pos, ModBlocks.THRUSTER_BLOCK.getDefaultState());
                            }

                            boolean isAir = world.getBlockState(pos.up()).isAir() && world.getBlockState(pos.up(1)).isAir();
                            if (!isAir) {
                                for (int i = 1; i < 3; i++) {
                                    world.setBlockState(pos.up(i), Blocks.AIR.getDefaultState(), 3);
                                    SpeedrunnerMod.debug("Removing non-air blocks for ender thruster. (" + player.getName().toString() + ", " + player.getUuidAsString() + ").");
                                }
                            }

                            player.teleport(player.getX(), y, player.getZ(), true);
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                            SpeedrunnerMod.debug("Teleported player " + player.getName().toString() + " (" + player.getUuidAsString() + ") to X = " + player.getX() + ", Y = " + player.getY() + ", Z = " + player.getZ() + "with Ender Thruster.");
                        } else {
                            player.sendMessage(Text.translatable("item.speedrunnermod.ender_thruster.confirm").formatted(Formatting.WHITE), false);
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        }
                        if (options().client.confirmMessages) {
                            confirm = !confirm;
                        }
                        player.swingHand(hand, true);
                        return TypedActionResult.success(itemStack);
                    } else {
                        player.sendMessage(Text.translatable("item.speedrunnermod.ender_thruster.couldnt_teleport"), ModOptions.ItemMessages.isActionbar());
                    }
                } else {
                    player.sendMessage(Text.translatable("item.speedrunnermod.ender_thruster.wrong_dimension").formatted(ItemUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), ModOptions.ItemMessages.isActionbar());
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled"), false);
                info("Player " + player.getName().toString() + " (" + player.getUuidAsString() + ") tried to use Ender Thruster, but is disabled!");
            }
        }

        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.ender_thruster.tooltip"));
        }
    }
}