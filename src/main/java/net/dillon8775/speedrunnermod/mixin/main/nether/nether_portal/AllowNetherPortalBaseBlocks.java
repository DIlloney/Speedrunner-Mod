package net.dillon8775.speedrunnermod.mixin.main.nether.nether_portal;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.tag.ModBlockTags;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractFireBlock.class)
public class AllowNetherPortalBaseBlocks {

    @Redirect(method = "shouldLightPortalAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private static boolean netherPortalBaseBlocks(BlockState state, Block block) {
        return state.isIn(ModBlockTags.NETHER_PORTAL_BASE_BLOCKS);
    }

    /**
     * Allows nether portals to be built in all dimensions if {@code Global Nether Portals} is on.
     */
    @Overwrite
    private static boolean isOverworldOrNether(World world) {
        if (SpeedrunnerMod.options().globalNetherPortals) {
            return world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER || world.getRegistryKey() == World.END;
        } else {
            return world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER;
        }
    }
}