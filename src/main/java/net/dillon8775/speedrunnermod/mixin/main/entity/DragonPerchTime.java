package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Mixin(EnderDragonFight.class)
public class DragonPerchTime {
    @Shadow
    @Final
    private ServerWorld world;
    @Shadow
    private UUID dragonUuid;

    @Overwrite
    private EnderDragonEntity createDragon() {
        this.world.getWorldChunk(new BlockPos(0, 128, 0));
        EnderDragonEntity enderDragonEntity = (EnderDragonEntity) EntityType.ENDER_DRAGON.create(this.world);
        enderDragonEntity.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
        enderDragonEntity.refreshPositionAndAngles(0.0D, 128.0D, 0.0D, this.world.random.nextFloat() * 360.0F, 0.0F);
        this.world.spawnEntity(enderDragonEntity);
        this.dragonUuid = enderDragonEntity.getUuid();
        if (SpeedrunnerMod.options().main.dragonPerchTime >= 21) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    enderDragonEntity.getPhaseManager().setPhase(PhaseType.LANDING);
                }
            }, SpeedrunnerMod.options().main.dragonPerchTime * 1000);
        }

        if (SpeedrunnerMod.options().main.doomMode) {
            WitherEntity witherEntity = (WitherEntity)EntityType.WITHER.create(this.world);
            witherEntity.refreshPositionAndAngles(0.0D, 196.0D, 0.0D, this.world.random.nextFloat() * 360.0F, 0.0F);
            this.world.spawnEntity(witherEntity);
            GiantEntity giantEntity = (GiantEntity)EntityType.GIANT.create(this.world);
            giantEntity.refreshPositionAndAngles(0.0D, 96.0D, 0.0D, this.world.random.nextFloat() * 240.0F, 0.0F);
            this.world.spawnEntity(giantEntity);
        }
        return enderDragonEntity;
    }
}