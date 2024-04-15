package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(WardenEntity.class)
public class WardenEntityMixin extends HostileEntity {
    @Unique
    private ServerBossBar bossBar;

    public WardenEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Gives the warden a bossbar.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void createBossBar(EntityType<? extends HostileEntity> entityType, World world, CallbackInfo ci) {
        this.bossBar = new ServerBossBar(this.getDisplayName(), BossBar.Color.BLUE, BossBar.Style.PROGRESS);
    }

    /**
     * Ticks the bossbar.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void tickBossBar(CallbackInfo ci) {
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    /**
     * Makes the warden weaker.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder addAttributes() {
        double genericMaxHealth = DOOM_MODE ? 400.0 : 200.0;
        double genericMovementSpeed = DOOM_MODE ? 0.4 : 0.2;
        double genericKnockbackResistance = DOOM_MODE ? 1.0 : 0.65;
        double genericAttackKnockback = DOOM_MODE ? 2.0 : 1.0;
        double genericAttackDamage = DOOM_MODE ? 30.0 : 15.0;
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, genericKnockbackResistance)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
    }
}