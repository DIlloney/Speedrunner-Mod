package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SpawnerBlock.class)
public class SpawnerBlockMixin extends Block {

    public SpawnerBlockMixin(Settings settings) {
        super(settings);
    }

    @Overwrite
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        int i = 125 + world.random.nextInt(175) + world.random.nextInt(180);
        this.dropExperience(world, pos, i);
        if (SpeedrunnerMod.CONFIG.doomMode) {
            BlazeEntity blazeEntity = (BlazeEntity)EntityType.BLAZE.create(world);
            blazeEntity.refreshPositionAndAngles(pos, pos.getY() + 0.5F, 0);
            VindicatorEntity vindicatorEntity = (VindicatorEntity)EntityType.VINDICATOR.create(world);
            vindicatorEntity.refreshPositionAndAngles(pos, pos.getY() + 0.5F, 0);
            vindicatorEntity.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_AXE));
            world.spawnEntity(blazeEntity);
            world.spawnEntity(vindicatorEntity);
            world.setBlockState(pos, Blocks.LAVA.getDefaultState());
        }
    }
}