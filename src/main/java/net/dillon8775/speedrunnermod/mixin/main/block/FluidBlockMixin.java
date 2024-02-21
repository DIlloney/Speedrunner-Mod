package net.dillon8775.speedrunnermod.mixin.main.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FluidBlock.class)
public class FluidBlockMixin {

    /**
     * Makes water replace lava with basalt instead of obsidian when any nether biome.
     */
    @Redirect(method = "receiveNeighborFluids", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;OBSIDIAN:Lnet/minecraft/block/Block;"))
    private Block change(World world, BlockPos pos, BlockState state) {
        return world.getBiome(pos).getCategory() == Biome.Category.NETHER ? Blocks.BASALT : Blocks.OBSIDIAN;
    }
}