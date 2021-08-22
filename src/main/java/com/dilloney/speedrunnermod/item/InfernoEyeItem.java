package com.dilloney.speedrunnermod.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;

public class InfernoEyeItem extends Item {

    String structureType = "Fortress";
    StructureFeature<?> type = StructureFeature.FORTRESS;

    public InfernoEyeItem(Settings settings) {
        super(settings.group(ModItemGroup.SPEEDRUNNER_MOD_ITEM_GROUP).group(ItemGroup.MISC).fireproof());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if(!world.isClient) {
            ServerWorld serverWorld = (ServerWorld)world;
            if(player.isSneaking() && serverWorld.getRegistryKey().equals(World.NETHER)) {
                if (structureType.equals("Fortress")) {
                    structureType = "Bastion";
                    type = StructureFeature.BASTION_REMNANT;
                }
                else if (structureType.equals("Bastion")) {
                    structureType = "Fortress";
                    type = StructureFeature.FORTRESS;
                }

                player.sendMessage((new TranslatableText("item.speedrunnermod.eye_of_inferno.looking_for", structureType).formatted(Formatting.RED)), true);
                return TypedActionResult.success(itemStack);
            }
        }

        if(!world.isClient) {
            ServerWorld serverWorld = (ServerWorld)world;
            if(serverWorld.getRegistryKey().equals(World.OVERWORLD) || serverWorld.getRegistryKey().equals(World.END)) {
                player.sendMessage((new TranslatableText("item.speedrunnermod.eye_of_inferno.wrong_dimension").formatted(Formatting.RED)), true);
                return TypedActionResult.consume(itemStack);
            }
        }

        if(!player.isSneaking()) {
            if(!world.isClient) {
                ServerWorld serverWorld = (ServerWorld)world;

                serverWorld.getRegistryKey(); { findStructureAndShoot(world, player, itemStack, type, hand);

                    player.sendMessage((new TranslatableText("item.speedrunnermod.eye_of_inferno.located", structureType).formatted(Formatting.RED)), true);
                    return TypedActionResult.success(itemStack);
                }
            }
        }

        return TypedActionResult.consume(itemStack);
    }

    /**
     * @author kwpugh
     */
    private static void findStructureAndShoot(World world, PlayerEntity player, ItemStack itemstack, StructureFeature<?> type, Hand hand) {
        BlockPos locpos;
        locpos = ((ServerWorld)world).getChunkManager().getChunkGenerator().locateStructure((ServerWorld)world, type, player.getBlockPos(),100, false);

        ItemStack itemStack = player.getStackInHand(hand);

        EyeOfEnderEntity finderentity = new EyeOfEnderEntity(world, player.getX(), player.getBodyY(0.5D), player.getZ());
        finderentity.setItem(itemstack);
        finderentity.initTargetPos(locpos);
        world.spawnEntity(finderentity);

        if (player instanceof ServerPlayerEntity) {
            Criteria.USED_ENDER_EYE.trigger((ServerPlayerEntity)player, locpos);
        }

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        world.syncWorldEvent((PlayerEntity)null, 1003, player.getBlockPos(), 0);

        if (!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
    }
}