package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {

    @Shadow @Final ServerWorld world;
    @Shadow UUID dragonUuid;
    UUID witherUuid;

    @Overwrite
    private EnderDragonEntity createDragon() {
        this.world.getWorldChunk(new BlockPos(0, 128, 0));
        EnderDragonEntity enderDragonEntity = (EnderDragonEntity) EntityType.ENDER_DRAGON.create(this.world);
        enderDragonEntity.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
        enderDragonEntity.refreshPositionAndAngles(0.0D, 128.0D, 0.0D, this.world.random.nextFloat() * 360.0F, 0.0F);
        WitherEntity witherEntity = (WitherEntity) EntityType.WITHER.create(this.world);
        witherEntity.refreshPositionAndAngles(0.0D, 96.0D, 0.0D, this.world.random.nextFloat() * 360.0F, 0.0F);
        this.world.spawnEntity(enderDragonEntity);
        if (SpeedrunnerMod.CONFIG.enableChallengeMode) {
            this.world.spawnEntity(witherEntity);
            this.witherUuid = witherEntity.getUuid();
        }
        this.dragonUuid = enderDragonEntity.getUuid();
        return enderDragonEntity;
    }
}