package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.dilloney.speedrunnermod.items.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {

    @Inject(method = "onStacksDropped", at = @At("HEAD"))
    private void onStacksDroppedMod(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo callbackInfo) {
        if (SpeedrunnerMod.CONFIG.enableChallengeMode) {
            if (!state.isOf(Blocks.FIRE) && !state.isOf(Blocks.SOUL_FIRE) && !state.isOf(Blocks.GRASS) && !state.isOf(Blocks.TALL_GRASS) && !state.isOf(Blocks.SEAGRASS) && !state.isOf(Blocks.TALL_SEAGRASS) && !state.isOf(Blocks.KELP) && !state.isOf(Blocks.KELP_PLANT) && !state.isOf(Blocks.NETHERRACK) && !state.isOf(Blocks.CRIMSON_NYLIUM) && !state.isOf(Blocks.WARPED_NYLIUM) && !state.isOf(Blocks.NETHER_WART) && !state.isOf(Blocks.GOLD_BLOCK) && !state.isOf(Blocks.WATER) && !state.isOf(Blocks.LAVA) && !state.isOf(Blocks.CHEST) && !state.isOf(Blocks.TRAPPED_CHEST) && !state.isOf(Blocks.ENDER_CHEST) && !state.isOf(Blocks.BARREL) && !state.isIn(BlockTags.CROPS) && !state.isOf(Blocks.WEEPING_VINES_PLANT) && !state.isOf(Blocks.TWISTING_VINES_PLANT) && !state.isOf(Blocks.FERN) && !state.isOf(Blocks.LARGE_FERN) && !state.isOf(Blocks.DEAD_BUSH) && !state.isOf(Blocks.SEA_PICKLE) && !state.isOf(Blocks.DANDELION) && !state.isOf(Blocks.POPPY) && !state.isOf(Blocks.BLUE_ORCHID) && !state.isOf(Blocks.ALLIUM) && !state.isOf(Blocks.AZURE_BLUET) && !state.isOf(Blocks.RED_TULIP) && !state.isOf(Blocks.ORANGE_TULIP) && !state.isOf(Blocks.WHITE_TULIP) && !state.isOf(Blocks.PINK_TULIP) && !state.isOf(Blocks.OXEYE_DAISY) && !state.isOf(Blocks.CORNFLOWER) && !state.isOf(Blocks.LILY_OF_THE_VALLEY) && !state.isOf(Blocks.WITHER_ROSE) && !state.isOf(Blocks.SUNFLOWER) && !state.isOf(Blocks.LILAC) && !state.isOf(Blocks.PEONY) && !state.isOf(Blocks.ROSE_BUSH) && !state.isOf(Blocks.BROWN_MUSHROOM) && !state.isOf(Blocks.RED_MUSHROOM) && !state.isOf(Blocks.BAMBOO) && !state.isOf(Blocks.HANGING_ROOTS) && !state.isOf(Blocks.CHORUS_FLOWER) && !state.isOf(Blocks.CHORUS_PLANT) && !state.isOf(Blocks.TORCH) && !state.isOf(Blocks.WALL_TORCH) && !state.isOf(Blocks.SOUL_TORCH) && !state.isOf(Blocks.SOUL_WALL_TORCH) && !state.isOf(Blocks.REDSTONE_TORCH) && !state.isOf(Blocks.REDSTONE_WALL_TORCH) && !state.isOf(Blocks.SCAFFOLDING) && !state.isOf(Blocks.SLIME_BLOCK) && !state.isOf(Blocks.HONEY_BLOCK) && !state.isOf(Blocks.SPAWNER) && !state.isOf(Blocks.BEDROCK) && !state.isOf(Blocks.END_PORTAL) && !state.isOf(Blocks.END_PORTAL_FRAME) && !state.isOf(Blocks.END_GATEWAY) && !state.isOf(Blocks.NETHER_PORTAL) && !state.isOf(Blocks.CAVE_VINES) && !state.isOf(Blocks.CAVE_VINES_PLANT) && !state.isOf(Blocks.OAK_LEAVES) && !state.isOf(Blocks.SPRUCE_LEAVES) && !state.isOf(Blocks.BIRCH_LEAVES) && !state.isOf(Blocks.SPRUCE_LEAVES) && !state.isOf(Blocks.JUNGLE_LEAVES) && !state.isOf(Blocks.ACACIA_LEAVES) && !state.isOf(Blocks.DARK_OAK_LEAVES) && !state.isOf(Blocks.AZALEA_LEAVES) && !state.isOf(Blocks.FLOWERING_AZALEA_LEAVES)) {
                if (world.random.nextFloat() < 0.10F) {
                    ZombieEntity zombieEntity = (ZombieEntity)EntityType.ZOMBIE.create(world);
                    zombieEntity.refreshPositionAndAngles(pos, pos.getY() + 1, 0);
                    zombieEntity.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
                    zombieEntity.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
                    world.spawnEntity(zombieEntity);
                } else if (world.random.nextFloat() < 0.020F) {
                    SkeletonEntity skeletonEntity = (SkeletonEntity)EntityType.SKELETON.create(world);
                    skeletonEntity.refreshPositionAndAngles(pos, pos.getY() + 1, 0);
                    skeletonEntity.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
                    skeletonEntity.equipStack(EquipmentSlot.CHEST, new ItemStack(ModItems.SPEEDRUNNER_CHESTPLATE));
                    skeletonEntity.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
                    world.spawnEntity(skeletonEntity);
                } else if (world.random.nextFloat() < 0.015F) {
                    CreeperEntity creeperEntity = (CreeperEntity)EntityType.CREEPER.create(world);
                    creeperEntity.refreshPositionAndAngles(pos, pos.getY() + 1, 0);
                    world.spawnEntity(creeperEntity);
                } else if (world.random.nextFloat() < 0.010F) {
                    VindicatorEntity vindicatorEntity = (VindicatorEntity)EntityType.VINDICATOR.create(world);
                    vindicatorEntity.refreshPositionAndAngles(pos, pos.getY() + 1, 0);
                    vindicatorEntity.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_AXE));
                    world.spawnEntity(vindicatorEntity);
                } else if (world.random.nextFloat() < 0.005F) {
                    EvokerEntity evokerEntity = (EvokerEntity) EntityType.EVOKER.create(world);
                    evokerEntity .refreshPositionAndAngles(pos, pos.getY() + 1, 0);
                    world.spawnEntity(evokerEntity);
                } else if (world.random.nextFloat() < 0.003F) {
                    world.setTimeOfDay(18000);
                }
            }
        }
    }
}