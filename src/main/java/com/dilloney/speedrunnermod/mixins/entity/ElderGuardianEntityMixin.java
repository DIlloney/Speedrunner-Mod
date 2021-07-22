package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Iterator;
import java.util.List;

@Mixin(ElderGuardianEntity.class)
public class ElderGuardianEntityMixin extends GuardianEntity {

    public ElderGuardianEntityMixin(EntityType<? extends GuardianEntity> entityType, World world) {
        super(entityType, world);
        this.setPersistent();
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createElderGuardianAttributes() {
        if (SpeedrunnerMod.CONFIG.difficulty == 1) {
            return GuardianEntity.createGuardianAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 25.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
            return GuardianEntity.createGuardianAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 28.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
            return GuardianEntity.createGuardianAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 32.0D);
        } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
            return GuardianEntity.createGuardianAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 36.0D);
        } else {
            return GuardianEntity.createGuardianAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 25.0D);
        }
    }

    @Overwrite
    public void mobTick() {
        super.mobTick();
        boolean i = true;
        if ((this.age + this.getId()) % 600 == 0 && SpeedrunnerMod.CONFIG.doomMode) {
            StatusEffect statusEffect = StatusEffects.MINING_FATIGUE;
            List<ServerPlayerEntity> list = ((ServerWorld)this.world).getPlayers((serverPlayerEntityx) -> {
                return this.squaredDistanceTo(serverPlayerEntityx) < 3000.0D && serverPlayerEntityx.interactionManager.isSurvivalLike();
            });
            boolean j = true;
            boolean k = true;
            boolean l = true;
            Iterator var7 = list.iterator();

            label33:
            while(true) {
                ServerPlayerEntity serverPlayerEntity;
                do {
                    if (!var7.hasNext()) {
                        break label33;
                    }

                    serverPlayerEntity = (ServerPlayerEntity)var7.next();
                } while(serverPlayerEntity.hasStatusEffect(statusEffect) && serverPlayerEntity.getStatusEffect(statusEffect).getAmplifier() >= 2 && serverPlayerEntity.getStatusEffect(statusEffect).getDuration() >= 1200);

                serverPlayerEntity.networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.ELDER_GUARDIAN_EFFECT, this.isSilent() ? 0.0F : 1.0F));
                serverPlayerEntity.addVelocity(0.0F, 1.0F, 0.0F);
                serverPlayerEntity.damage(DamageSource.FREEZE, 6.0F);
                serverPlayerEntity.addStatusEffect(new StatusEffectInstance(statusEffect, 6000, 2));
            }
        } else {
            if ((this.age + this.getId()) % 6000 == 0) {
                StatusEffect statusEffect = StatusEffects.MINING_FATIGUE;
                List<ServerPlayerEntity> list = ((ServerWorld)this.world).getPlayers((serverPlayerEntityx) -> {
                    return this.squaredDistanceTo(serverPlayerEntityx) < 1250.0D && serverPlayerEntityx.interactionManager.isSurvivalLike();
                });
                boolean j = true;
                boolean k = true;
                boolean l = true;
                Iterator var7 = list.iterator();

                label33:
                while(true) {
                    ServerPlayerEntity serverPlayerEntity;
                    do {
                        if (!var7.hasNext()) {
                            break label33;
                        }

                        serverPlayerEntity = (ServerPlayerEntity)var7.next();
                    } while(serverPlayerEntity.hasStatusEffect(statusEffect) && serverPlayerEntity.getStatusEffect(statusEffect).getAmplifier() >= 2 && serverPlayerEntity.getStatusEffect(statusEffect).getDuration() >= 1200);

                    serverPlayerEntity.networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.ELDER_GUARDIAN_EFFECT, this.isSilent() ? 0.0F : 1.0F));
                    if (SpeedrunnerMod.CONFIG.difficulty == 1) {
                        serverPlayerEntity.addStatusEffect(new StatusEffectInstance(statusEffect, 600, 2));
                    } else if (SpeedrunnerMod.CONFIG.difficulty == 2) {
                        serverPlayerEntity.addStatusEffect(new StatusEffectInstance(statusEffect, 800, 2));
                    } else if (SpeedrunnerMod.CONFIG.difficulty == 3) {
                        serverPlayerEntity.addStatusEffect(new StatusEffectInstance(statusEffect, 1200, 2));
                    } else if (SpeedrunnerMod.CONFIG.difficulty == 4) {
                        serverPlayerEntity.addStatusEffect(new StatusEffectInstance(statusEffect, 2400, 2));
                    } else {
                        serverPlayerEntity.addStatusEffect(new StatusEffectInstance(statusEffect, 600, 2));
                    }
                }
            }
        }

        if (!this.hasPositionTarget()) {
            this.setPositionTarget(this.getBlockPos(), 16);
        }
    }
}