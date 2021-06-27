package com.dilloney.speedrunnermod.items;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
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

public class EyeOfInfernoItem extends Item {

    String structureType = "Fortress";
    StructureFeature<?> type = StructureFeature.FORTRESS;

    public EyeOfInfernoItem(Settings settings) { super(settings); }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getStackInHand(handIn);

        playerIn.setCurrentHand(handIn);

        if(!worldIn.isClient) {
            ServerWorld serverWorld = (ServerWorld)worldIn;

            if(playerIn.isSneaking() && serverWorld.getRegistryKey().equals(World.NETHER)) {
                if (structureType.equals("Fortress")) {
                    structureType = "Bastion";
                    type = StructureFeature.BASTION_REMNANT;
                }
                else if (structureType.equals("Bastion")) {
                    structureType = "Fortress";
                    type = StructureFeature.FORTRESS;
                }

                playerIn.sendMessage((new TranslatableText("item.speedrunnermod.eyeofinferno.looking_for", structureType).formatted(Formatting.RED)), true);

                return TypedActionResult.success(itemStack);
            }
        }

        if(!worldIn.isClient) {
            ServerWorld serverWorld = (ServerWorld)worldIn;

            if(serverWorld.getRegistryKey().equals(World.OVERWORLD) || serverWorld.getRegistryKey().equals(World.END)) {

                playerIn.sendMessage((new TranslatableText("item.speedrunnermod.eyeofinferno.wrong_dimension", structureType).formatted(Formatting.RED)), true);

                return TypedActionResult.consume(itemStack);
            }
        }

        if(!playerIn.isSneaking()) {
            if(!worldIn.isClient) {
                ServerWorld serverWorld = (ServerWorld)worldIn;

                serverWorld.getRegistryKey(); {
                    findStructureAndShoot(worldIn, playerIn, itemStack, type, handIn);

                    return TypedActionResult.success(itemStack);
                }
            }
        }

        return TypedActionResult.consume(itemStack);
    }


    private static void findStructureAndShoot(World worldIn, PlayerEntity playerIn, ItemStack itemstack, StructureFeature<?> type, Hand handIn) {

        BlockPos locpos;
        locpos = ((ServerWorld)worldIn).getChunkManager().getChunkGenerator().locateStructure((ServerWorld)worldIn, type, playerIn.getBlockPos(),100, false);

        ItemStack itemStack = playerIn.getStackInHand(handIn);

        EyeOfEnderEntity finderentity = new EyeOfEnderEntity(worldIn, playerIn.getX(), playerIn.getBodyY(0.5D), playerIn.getZ());
        finderentity.setItem(itemstack);
        finderentity.initTargetPos(locpos);
        worldIn.spawnEntity(finderentity);

        if (playerIn instanceof ServerPlayerEntity) {
            Criteria.USED_ENDER_EYE.trigger((ServerPlayerEntity)playerIn, locpos);
        }

        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));
        worldIn.syncWorldEvent((PlayerEntity)null, 1003, playerIn.getBlockPos(), 0);

        if (!playerIn.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
    }
}
