package com.dilloney.speedrunnermod.mixins.block;

import com.dilloney.speedrunnermod.tag.ModBlockTags;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {

    /**
     * Makes the Fire Block work correctly when trying to light a nether portal.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    private static boolean shouldLightPortalAt(World world, BlockPos pos, Direction direction) {
        if (!isOverworldOrNether(world)) {
            return false;
        } else {
            BlockPos.Mutable mutable = pos.mutableCopy();
            boolean bl = false;
            Direction[] var5 = Direction.values();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Direction direction2 = var5[var7];
                if (world.getBlockState(mutable.set(pos).move(direction2)).isIn(ModBlockTags.NETHER_PORTAL_BASE_BLOCKS)) {
                    bl = true;
                    break;
                }
            }

            if (!bl) {
                return false;
            } else {
                Direction.Axis axis = direction.getAxis().isHorizontal() ? direction.rotateYCounterclockwise().getAxis() : Direction.Type.HORIZONTAL.randomAxis(world.random);
                return AreaHelper.getNewPortal(world, pos, axis).isPresent();
            }
        }
    }

    /**
     * Allows nether portals to be built in the end.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    private static boolean isOverworldOrNether(World world) {
        if (OPTIONS.netherPortalsInTheEnd) {
            return world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER || world.getRegistryKey() == World.END;
        } else {
            return world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER;
        }
    }
}