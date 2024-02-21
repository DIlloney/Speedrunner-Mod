package net.dillon8775.speedrunnermod.item;

import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.dillon8775.speedrunnermod.option.ClientModOptions;
import net.dillon8775.speedrunnermod.util.ItemUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * An eye of ender that locates most overworld structures.
 */
public class SpeedrunnersEyeItem extends Item {
    private String structureType = "Village";
    private StructureFeature<?> type = StructureFeature.VILLAGE;

    public SpeedrunnersEyeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld)world;
            if (player.isSneaking() && serverWorld.getRegistryKey().equals(World.OVERWORLD)) {
                switch (structureType) {
                    case "Village" -> {
                        structureType = "Ruined Portal";
                        type = StructureFeature.RUINED_PORTAL;
                    }
                    case "Ruined Portal" -> {
                        structureType = "Shipwreck";
                        type = StructureFeature.SHIPWRECK;
                    }
                    case "Shipwreck" -> {
                        structureType = "Ocean Monument";
                        type = StructureFeature.MONUMENT;
                    }
                    case "Ocean Monument" -> {
                        structureType = "Woodland Mansion";
                        type = StructureFeature.MANSION;
                    }
                    case "Woodland Mansion" -> {
                        structureType = "Desert Pyramid";
                        type = StructureFeature.DESERT_PYRAMID;
                    }
                    case "Desert Pyramid" -> {
                        structureType = "Village";
                        type = StructureFeature.VILLAGE;
                    }
                }

                player.sendMessage(new TranslatableText("item.speedrunnermod.speedrunners_eye.looking_for", structureType).formatted(ClientModOptions.isActionbar() ? Formatting.AQUA : Formatting.WHITE), ClientModOptions.isActionbar());
                return TypedActionResult.success(itemStack);
            }
        }

        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            if (serverWorld.getRegistryKey().equals(World.NETHER) || serverWorld.getRegistryKey().equals(World.END)) {
                return TypedActionResult.consume(itemStack);
            }
        }

        if (!player.isSneaking()) {
            if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld)world;

                serverWorld.getRegistryKey(); {
                    ItemUtil.findStructureAndShoot(world, player, itemStack, type, hand);

                    BlockPos blockPos = ((ServerWorld)world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld)world, type, player.getBlockPos(), 100, false);
                    BlockPos playerpos = player.getBlockPos();
                    int structureDistance = MathHelper.floor(ItemUtil.getDistance(playerpos.getX(), playerpos.getZ(), blockPos.getX(), blockPos.getZ()));
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                    player.sendMessage(new TranslatableText("item.speedrunnermod.speedrunners_eye.blocks_away", structureType, structureDistance).formatted(ClientModOptions.isActionbar() ? Formatting.AQUA : Formatting.WHITE), ClientModOptions.isActionbar());
                    return TypedActionResult.success(itemStack);
                }
            }
        }

        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (SpeedrunnerModClient.clientOptions().itemTooltips) {
            tooltip.add(new TranslatableText("item.speedrunnermod.speedrunners_eye.tooltip"));
        }
        tooltip.add(new TranslatableText("item.speedrunnermod.speedrunners_eye.looking_for.tooltip", structureType));
    }
}