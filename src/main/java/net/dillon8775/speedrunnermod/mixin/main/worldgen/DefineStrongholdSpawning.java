package net.dillon8775.speedrunnermod.mixin.main.worldgen;

import com.mojang.serialization.Codec;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.MarginedStructureFeature;
import net.minecraft.world.gen.feature.StrongholdFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

/**
 * Changes stronghold generation.
 */
@Mixin(StrongholdFeature.class)
public abstract class DefineStrongholdSpawning extends MarginedStructureFeature<DefaultFeatureConfig> {

    public DefineStrongholdSpawning(Codec<DefaultFeatureConfig> codec, StructureGeneratorFactory<DefaultFeatureConfig> structureGeneratorFactory) {
        super(codec, structureGeneratorFactory);
    }

    @Overwrite
    public static void addPieces(StructurePiecesCollector collector, net.minecraft.structure.StructurePiecesGenerator.Context<DefaultFeatureConfig> context) {
        int var2 = 0;

        StrongholdGenerator.Start start;
        do {
            collector.clear();
            context.random().setCarverSeed(context.seed() + (long)(var2++), context.chunkPos().x, context.chunkPos().z);
            StrongholdGenerator.init();
            start = new StrongholdGenerator.Start(context.random(), context.chunkPos().getOffsetX(2), context.chunkPos().getOffsetZ(2));
            collector.addPiece(start);
            start.fillOpenings(start, collector, context.random());
            List list = start.pieces;

            while(!list.isEmpty()) {
                int j = context.random().nextInt(list.size());
                StructurePiece structurePiece = (StructurePiece)list.remove(j);
                structurePiece.fillOpenings(start, collector, context.random());
            }

            final int topY = SpeedrunnerMod.options().main.doomMode ? 0 : 36;
            final int bottomY = SpeedrunnerMod.options().main.doomMode ? -48 : 25;
            collector.shiftInto(topY, bottomY, context.random(), 10);
        } while(collector.isEmpty() || start.portalRoom == null);

    }
}