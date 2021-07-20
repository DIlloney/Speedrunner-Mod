package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.dilloney.speedrunnermod.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {

    @Overwrite
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (SpeedrunnerMod.CONFIG.enableChallengeMode) {
            entity.handleFallDamage(fallDistance + 0.5F, 1.0F, DamageSource.FALL);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            entity.handleFallDamage(fallDistance, 0.6F, DamageSource.FALL);
        } else {
            entity.handleFallDamage(fallDistance, 1.0F, DamageSource.FALL);
        }
    }

    @Inject(method = "onBreak", at = @At("TAIL"))
    private void onBreakMod(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo callbackInfo) {
        if (SpeedrunnerMod.CONFIG.enableChallengeMode) {
            if (!state.isOf(Blocks.GRASS) && !state.isOf(Blocks.TALL_GRASS) && !state.isOf(Blocks.SEAGRASS) && !state.isOf(Blocks.TALL_SEAGRASS) && !state.isOf(Blocks.KELP) && !state.isOf(Blocks.KELP_PLANT) && !state.isOf(Blocks.NETHERRACK) && !state.isOf(Blocks.CRIMSON_NYLIUM) && !state.isOf(Blocks.WARPED_NYLIUM) && !state.isOf(Blocks.NETHER_WART) && !state.isOf(Blocks.WATER) && !state.isOf(Blocks.LAVA) && !state.isOf(Blocks.CHEST) && !state.isOf(Blocks.TRAPPED_CHEST) && !state.isOf(Blocks.ENDER_CHEST) && !state.isOf(Blocks.BARREL) && !state.isOf(Blocks.VINE) && !state.isOf(Blocks.WEEPING_VINES_PLANT) && !state.isOf(Blocks.TWISTING_VINES_PLANT) && !state.isOf(Blocks.FERN) && !state.isOf(Blocks.LARGE_FERN) && !state.isOf(Blocks.DEAD_BUSH) && !state.isOf(Blocks.SEA_PICKLE) && !state.isIn(BlockTags.FLOWERS) && !state.isIn(BlockTags.TALL_FLOWERS) && !state.isIn(BlockTags.SMALL_FLOWERS) && !state.isOf(Blocks.BROWN_MUSHROOM) && !state.isOf(Blocks.RED_MUSHROOM) && !state.isOf(Blocks.BAMBOO) && !state.isOf(Blocks.HANGING_ROOTS) && !state.isOf(Blocks.CHORUS_FLOWER) && !state.isOf(Blocks.CHORUS_PLANT) && !state.isOf(Blocks.TORCH) && !state.isOf(Blocks.WALL_TORCH) && !state.isOf(Blocks.SOUL_TORCH) && !state.isOf(Blocks.SOUL_WALL_TORCH) && !state.isOf(Blocks.REDSTONE_TORCH) && !state.isOf(Blocks.REDSTONE_WALL_TORCH) && !state.isOf(Blocks.SCAFFOLDING) && !state.isOf(Blocks.SLIME_BLOCK) && !state.isOf(Blocks.HONEY_BLOCK) && !state.isOf(Blocks.SPAWNER) && !state.isOf(Blocks.BEDROCK) && !state.isOf(Blocks.BARRIER) && !state.isOf(Blocks.COMMAND_BLOCK) && !state.isOf(Blocks.CHAIN_COMMAND_BLOCK) && !state.isOf(Blocks.REPEATING_COMMAND_BLOCK) && !state.isOf(Blocks.END_PORTAL) && !state.isOf(Blocks.END_PORTAL_FRAME) && !state.isOf(Blocks.END_GATEWAY) && !state.isOf(Blocks.NETHER_PORTAL) && !state.isIn(BlockTags.CROPS) && !state.isIn(BlockTags.CAVE_VINES) && !state.isIn(BlockTags.FIRE) && !state.isIn(BlockTags.LEAVES) && !state.isIn(BlockTags.GUARDED_BY_PIGLINS) && !player.isCreative() && player.isPlayer()) {
                if (world.random.nextFloat() < 0.06F) {
                    ZombieEntity zombieEntity = (ZombieEntity) EntityType.ZOMBIE.create(world);
                    zombieEntity.refreshPositionAndAngles(pos, pos.getY() + 1, 0);
                    zombieEntity.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
                    zombieEntity.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
                    zombieEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1));
                    world.spawnEntity(zombieEntity);
                } else if (world.random.nextFloat() < 0.05F) {
                    SkeletonEntity skeletonEntity = (SkeletonEntity)EntityType.SKELETON.create(world);
                    skeletonEntity.refreshPositionAndAngles(pos, pos.getY() + 1, 0);
                    skeletonEntity.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
                    skeletonEntity.equipStack(EquipmentSlot.CHEST, new ItemStack(ModItems.SPEEDRUNNER_CHESTPLATE));
                    skeletonEntity.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
                    skeletonEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1));
                    world.spawnEntity(skeletonEntity);
                } else if (world.random.nextFloat() < 0.03F) {
                    CreeperEntity creeperEntity = (CreeperEntity)EntityType.CREEPER.create(world);
                    creeperEntity.refreshPositionAndAngles(pos, pos.getY() + 1, 0);
                    world.spawnEntity(creeperEntity);
                } else if (world.random.nextFloat() < 0.02F) {
                    VindicatorEntity vindicatorEntity = (VindicatorEntity)EntityType.VINDICATOR.create(world);
                    vindicatorEntity.refreshPositionAndAngles(pos, pos.getY() + 1, 0);
                    vindicatorEntity.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
                    vindicatorEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1));
                    world.spawnEntity(vindicatorEntity);
                } else if (world.random.nextFloat() < 0.01F) {
                    EvokerEntity evokerEntity = (EvokerEntity) EntityType.EVOKER.create(world);
                    evokerEntity.refreshPositionAndAngles(pos, pos.getY() + 1, 0);
                    evokerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1));
                    world.spawnEntity(evokerEntity);
                }
            }
        }
    }
}