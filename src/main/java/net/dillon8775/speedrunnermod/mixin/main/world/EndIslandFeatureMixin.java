<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.EndIslandFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(EndIslandFeature.class)
public class EndIslandFeatureMixin {

    /**
     * If {@code doom mode} is {@code ON,} then the end island will generate with doom stone.
     */
    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;END_STONE:Lnet/minecraft/block/Block;"))
    private Block changeBaseBlock() {
        return DOOM_MODE ? ModBlocks.DOOM_STONE : Blocks.END_STONE;
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.EndIslandFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(EndIslandFeature.class)
public class EndIslandFeatureMixin {

    /**
     * If {@code doom mode} is {@code ON,} then the end island will generate with doom stone.
     */
    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;END_STONE:Lnet/minecraft/block/Block;"))
    private Block changeBaseBlock() {
        return DOOM_MODE ? ModBlocks.DOOM_STONE : Blocks.END_STONE;
    }
>>>>>>> Stashed changes
}