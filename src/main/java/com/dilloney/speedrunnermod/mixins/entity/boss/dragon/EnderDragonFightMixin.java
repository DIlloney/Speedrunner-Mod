package com.dilloney.speedrunnermod.mixins.entity.boss.dragon;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {

    @Shadow @Final ServerWorld world;
    @Shadow @Final @Mutable ServerBossBar bossBar;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo callbackInfo) {
        if (SpeedrunnerMod.CONFIG.doomMode) {
            this.bossBar = (ServerBossBar)(new ServerBossBar(new TranslatableText("entity.minecraft.ender_dragon"), BossBar.Color.PINK, BossBar.Style.PROGRESS)).setDragonMusic(true);
        }
    }

    @Inject(method = "createDragon", at = @At("TAIL"))
    private void createDragonMod(CallbackInfoReturnable callbackInfoReturnable) {
        if (SpeedrunnerMod.CONFIG.doomMode) {
            WitherEntity witherEntity = (WitherEntity)EntityType.WITHER.create(this.world);
            GiantEntity giantEntity = (GiantEntity)EntityType.GIANT.create(this.world);
            witherEntity.refreshPositionAndAngles(0.0D, 96.0D, 0.0D, this.world.random.nextFloat() * 270.0F, 0.0F);
            giantEntity.refreshPositionAndAngles(0.0D, 192.0D, -0.0D, this.world.random.nextFloat() * 270.0F, 0.0F);
            this.world.spawnEntity(witherEntity);
            this.world.spawnEntity(giantEntity);
        }
    }
}
