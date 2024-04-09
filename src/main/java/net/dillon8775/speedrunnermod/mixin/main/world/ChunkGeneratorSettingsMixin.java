package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(ChunkGeneratorSettings.class)
public class ChunkGeneratorSettingsMixin {

    /**
     * Changes the base block that generates in the end biomes.
     */
    @ModifyArgs(method = "createEndSettings", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/ChunkGeneratorSettings;<init>(Lnet/minecraft/world/gen/chunk/GenerationShapeConfig;Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;Lnet/minecraft/world/gen/noise/NoiseRouter;Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$MaterialRule;Ljava/util/List;IZZZZ)V"))
    private static void changeBaseBlock(Args args) {
        if (DOOM_MODE) {
            args.set(1, ModBlocks.DOOM_STONE.getDefaultState());
            args.set(4, VanillaSurfaceRules.block(ModBlocks.DOOM_STONE));
        }
    }
}