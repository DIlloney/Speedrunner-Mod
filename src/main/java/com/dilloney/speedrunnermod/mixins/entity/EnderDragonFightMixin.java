package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {

    public EnderDragonFightMixin(ServerWorld world, long gatewaysSeed, NbtCompound nbt) {
        this.bossBar = (ServerBossBar)(new ServerBossBar(new TranslatableText("entity.minecraft.ender_dragon"), BossBar.Color.PINK, BossBar.Style.PROGRESS)).setDragonMusic(true).setThickenFog(false);
    }

    @Shadow @Final ServerBossBar bossBar;
    @Shadow @Final ServerWorld world;

    @Inject(method = "createDragon", at = @At("TAIL"))
    private void createDragonMod(CallbackInfoReturnable callbackInfoReturnable) {
        WitherEntity witherEntity = (WitherEntity) EntityType.WITHER.create(this.world);
        witherEntity.refreshPositionAndAngles(0.0D, 96.0D, 0.0D, this.world.random.nextFloat() * 360.0F, 0.0F);
        if (SpeedrunnerMod.CONFIG.doomMode) {
            this.world.spawnEntity(witherEntity);
        }
    }
}