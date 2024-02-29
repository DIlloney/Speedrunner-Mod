package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Iterator;
import java.util.List;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(ElderGuardianEntity.class)
public class ElderGuardianEntityMixin extends GuardianEntity {

    public ElderGuardianEntityMixin(EntityType<? extends GuardianEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop(PlayerEntity player) {
        this.experiencePoints = 10 + EnchantmentHelper.getLooting(player) * 72;
        return super.getXpToDrop(player);
    }

    /**
     * Modifies elder guardian attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createElderGuardianAttributes() {
        final double genericMovementSpeed = 0.30000001192092896D;
        final double genericAttackDamage = DOOM_MODE ? 8.0D : 4.0D;
        final double genericMaxHealth = DOOM_MODE ? 50.0D : 25.0D;
        return GuardianEntity.createGuardianAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth);
    }

    /**
     * Decreases the range that an elder guardian can detect a player, and also decreases the mining fatigue duration.
     */
    @Overwrite
    public void mobTick() {
        super.mobTick();
        final int i = DOOM_MODE ? 600 : 6000;
        if ((this.age + this.getId()) % i == 0) {
            StatusEffect statusEffect = StatusEffects.MINING_FATIGUE;
            List<ServerPlayerEntity> list = ((ServerWorld)this.world).getPlayers((serverPlayerEntityx) -> {
                final double d = DOOM_MODE ? 3000.0D : 1250.0D;
                return this.squaredDistanceTo(serverPlayerEntityx) < d && serverPlayerEntityx.interactionManager.isSurvivalLike();
            });
            Iterator<ServerPlayerEntity> var7 = list.iterator();

            label33:
            while(true) {
                ServerPlayerEntity serverPlayerEntity;
                do {
                    if (!var7.hasNext()) {
                        break label33;
                    }

                    serverPlayerEntity = var7.next();
                } while(serverPlayerEntity.hasStatusEffect(statusEffect) && serverPlayerEntity.getStatusEffect(statusEffect).getAmplifier() >= 2 && serverPlayerEntity.getStatusEffect(statusEffect).getDuration() >= 1200);

                serverPlayerEntity.networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.ELDER_GUARDIAN_EFFECT, this.isSilent() ? 0.0F : 1.0F));
                final int duration = DOOM_MODE ? 6000 : 600;
                serverPlayerEntity.addStatusEffect(new StatusEffectInstance(statusEffect, duration, 2));
            }
        }

        if (!this.hasPositionTarget()) {
            this.setPositionTarget(this.getBlockPos(), 16);
        }
    }
}