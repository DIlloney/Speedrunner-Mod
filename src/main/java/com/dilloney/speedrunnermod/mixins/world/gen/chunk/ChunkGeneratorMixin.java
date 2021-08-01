package com.dilloney.speedrunnermod.mixins.world.gen.chunk;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.google.common.collect.Lists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StrongholdConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {

    @Shadow @Final BiomeSource populationSource;
    @Shadow @Final StructuresConfig structuresConfig;
    @Shadow @Final long worldSeed;
    @Shadow @Final List<ChunkPos> strongholds;

    @Overwrite
    private void generateStrongholdPositions() {
        if (this.strongholds.isEmpty()) {
            StrongholdConfig strongholdConfig = this.structuresConfig.getStronghold();
            if (strongholdConfig != null && strongholdConfig.getCount() != 0) {
                List<Biome> list = Lists.newArrayList();
                Iterator var3 = this.populationSource.getBiomes().iterator();

                while(var3.hasNext()) {
                    Biome biome = (Biome)var3.next();
                    if (biome.getGenerationSettings().hasStructureFeature(StructureFeature.STRONGHOLD)) {
                        list.add(biome);
                    }
                }

                if (SpeedrunnerMod.CONFIG.makeStructuresMoreCommon) {
                    int i = 4;
                    int j = strongholdConfig.getCount();
                    int k = strongholdConfig.getSpread();
                    Random random = new Random();
                    random.setSeed(this.worldSeed);
                    double d = random.nextDouble() * 3.141592653589793D * 2.0D;
                    int l = 0;
                    int m = 0;

                    for(int n = 0; n < j; ++n) {
                        double e = (double)(4 * i + i * m * 6) + (random.nextDouble() - 0.5D) * (double)i * 2.5D;
                        int o = (int)Math.round(Math.cos(d) * e);
                        int p = (int)Math.round(Math.sin(d) * e);
                        BiomeSource var10000 = this.populationSource;
                        int var10001 = ChunkSectionPos.getOffsetPos(o, 8);
                        int var10003 = ChunkSectionPos.getOffsetPos(p, 8);
                        Objects.requireNonNull(list);
                        BlockPos blockPos = var10000.locateBiome(var10001, 0, var10003, 112, list::contains, random);
                        if (blockPos != null) {
                            o = ChunkSectionPos.getSectionCoord(blockPos.getX());
                            p = ChunkSectionPos.getSectionCoord(blockPos.getZ());
                        }

                        this.strongholds.add(new ChunkPos(o, p));
                        d += 6.283185307179586D / (double)k;
                        ++l;
                        if (l == k) {
                            ++m;
                            l = 0;
                            k += 2 * k / (m + 1);
                            k = Math.min(k, j - n);
                            d += random.nextDouble() * 3.141592653589793D * 2.0D;
                        }
                    }
                } else {
                    int i = strongholdConfig.getDistance();
                    int j = strongholdConfig.getCount();
                    int k = strongholdConfig.getSpread();
                    Random random = new Random();
                    random.setSeed(this.worldSeed);
                    double d = random.nextDouble() * 3.141592653589793D * 2.0D;
                    int l = 0;
                    int m = 0;

                    for(int n = 0; n < j; ++n) {
                        double e = (double)(4 * i + i * m * 6) + (random.nextDouble() - 0.5D) * (double)i * 2.5D;
                        int o = (int)Math.round(Math.cos(d) * e);
                        int p = (int)Math.round(Math.sin(d) * e);
                        BiomeSource var10000 = this.populationSource;
                        int var10001 = ChunkSectionPos.getOffsetPos(o, 8);
                        int var10003 = ChunkSectionPos.getOffsetPos(p, 8);
                        Objects.requireNonNull(list);
                        BlockPos blockPos = var10000.locateBiome(var10001, 0, var10003, 112, list::contains, random);
                        if (blockPos != null) {
                            o = ChunkSectionPos.getSectionCoord(blockPos.getX());
                            p = ChunkSectionPos.getSectionCoord(blockPos.getZ());
                        }

                        this.strongholds.add(new ChunkPos(o, p));
                        d += 6.283185307179586D / (double)k;
                        ++l;
                        if (l == k) {
                            ++m;
                            l = 0;
                            k += 2 * k / (m + 1);
                            k = Math.min(k, j - n);
                            d += random.nextDouble() * 3.141592653589793D * 2.0D;
                        }
                    }
                }
            }
        }
    }
}