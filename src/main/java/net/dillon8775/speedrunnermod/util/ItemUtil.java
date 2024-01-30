package net.dillon8775.speedrunnermod.util;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;

public class ItemUtil {

    public static void findStructureAndShoot(World world, PlayerEntity player, ItemStack itemstack, StructureFeature<?> type, Hand hand) {
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

        world.syncWorldEvent((PlayerEntity)null, 1003, player.getBlockPos(), 0);

        if (!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
    }

    public static float getDistance(int x1, int z1, int x2, int z2) {
        int i = x2 - x1;
        int j = z2 - z1;

        return MathHelper.sqrt((float) (i * i + j * j));
    }
}