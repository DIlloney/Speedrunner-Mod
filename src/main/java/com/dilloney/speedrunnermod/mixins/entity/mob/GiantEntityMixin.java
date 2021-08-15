package com.dilloney.speedrunnermod.mixins.entity.mob;

import com.dilloney.speedrunnermod.sound.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(GiantEntity.class)
public class GiantEntityMixin extends HostileEntity {
    protected SwimNavigation waterNavigation;
    protected MobNavigation landNavigation;
    boolean targetingUnderwater;
    private ServerBossBar bossBar;

    protected GiantEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo callbackInfo) {
        if (OPTIONS.doomMode) {
            this.bossBar = (ServerBossBar)(new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS));
            this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
            this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0.0F);
            this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0.0F);
            this.waterNavigation = new SwimNavigation(this, world);
            this.landNavigation = new MobNavigation(this, world);
        }
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createGiantAttributes() {
        return OPTIONS.doomMode ? HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3500000528343624D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0D).add(EntityAttributes.GENERIC_ARMOR, 1.0D) : HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 50.0D);
    }

    @Override
    protected void initGoals() {
        if (OPTIONS.doomMode) {
            this.goalSelector.add(1, new SwimGoal(this));
            this.goalSelector.add(2, new GiantAttackGoal((GiantEntity)(Object)this, 1.0D, false));
            this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
            this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 32.0F));
            this.goalSelector.add(8, new LookAroundGoal(this));
            this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
            this.targetSelector.add(1, new FollowTargetGoal(this, PlayerEntity.class, true));
            this.targetSelector.add(3, new FollowTargetGoal(this, VillagerEntity.class, true));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (OPTIONS.doomMode) {
            if (this.age % 10 == 0) {
                this.heal(0.5F);
            }

            if (this.world instanceof ServerWorld && this.world.getRegistryKey() == World.END) {
                if (this.getY() < (double)(this.world.getBottomY() - 64)) {
                    this.teleport(0, 96, 0, true);
                    if (!this.isSilent()) {
                        this.world.playSound((PlayerEntity)null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 10.0F, 1.0F);
                        this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 10.0F, 1.0F);
                    }
                }
            }
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
        }
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (OPTIONS.doomMode) {
            this.onGiantDeath();
            if (!this.isSilent()) {
                this.world.playSound((PlayerEntity)null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, SoundCategory.AMBIENT, 10.0F, 1.0F);
                this.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, 10.0F, 1.0F);
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (OPTIONS.doomMode) {
            Entity entity = source.getSource();
            if (entity instanceof WitherSkullEntity) {
                return false;
            }

            if (this.getHealth() < 150) {
                if (entity instanceof ProjectileEntity || entity instanceof EnderDragonEntity || entity instanceof IronGolemEntity) {
                    return false;
                }
            }

            if (this.getHealth() < 50) {
                if (entity instanceof AreaEffectCloudEntity) {
                    return false;
                }
            }

            if (this.world.random.nextFloat() < 0.10F) {
                this.onGiantDamage();
            }
        }
        return super.damage(source, amount);
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (OPTIONS.doomMode) {
            if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
                this.updateVelocity(0.01F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.9D));
            } else {
                super.travel(movementInput);
            }
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public void updateSwimming() {
        super.updateSwimming();
        if (OPTIONS.doomMode) {
            if (!this.world.isClient) {
                if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
                    this.navigation = this.waterNavigation;
                    this.setSwimming(true);
                } else {
                    this.navigation = this.landNavigation;
                    this.setSwimming(false);
                }
            }
        }
    }

    @Override
    public void checkDespawn() {
        if (OPTIONS.doomMode) {
            if (this.world.getDifficulty() == Difficulty.PEACEFUL && this.isDisallowedInPeaceful()) {
                this.discard();
            } else {
                this.despawnCounter = 0;
            }
        }
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return !OPTIONS.doomMode;
    }

    @Override
    public boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source) {
        return !OPTIONS.doomMode;
    }

    @Override
    public boolean isFireImmune() {
        return OPTIONS.doomMode;
    }

    @Override
    public boolean isImmuneToExplosion() {
        return OPTIONS.doomMode;
    }

    @Override
    public boolean canUsePortals() {
        return !OPTIONS.doomMode;
    }

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        if (OPTIONS.doomMode) {
            this.bossBar.setName(this.getDisplayName());
        }
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        if (OPTIONS.doomMode) {
            this.bossBar.addPlayer(player);
        }
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        if (OPTIONS.doomMode) {
            this.bossBar.removePlayer(player);
        }
    }

    @Override
    public EntityGroup getGroup() {
        if (OPTIONS.doomMode) {
            return EntityGroup.UNDEAD;
        } else {
            return EntityGroup.DEFAULT;
        }
    }

    @Override
    public SoundCategory getSoundCategory() {
        if (OPTIONS.doomMode) {
            return SoundCategory.HOSTILE;
        } else {
            return SoundCategory.NEUTRAL;
        }
    }

    @Override
    public SoundEvent getAmbientSound() {
        if (OPTIONS.doomMode) {
            return ModSoundEvents.ENTITY_GIANT_AMBIENT;
        } else {
            return null;
        }
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        if (OPTIONS.doomMode) {
            return ModSoundEvents.ENTITY_GIANT_HURT;
        } else {
            return SoundEvents.ENTITY_GENERIC_HURT;
        }
    }

    @Override
    public SoundEvent getDeathSound() {
        if (OPTIONS.doomMode) {
            return ModSoundEvents.ENTITY_GIANT_DEATH;
        } else {
            return SoundEvents.ENTITY_GENERIC_DEATH;
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (OPTIONS.doomMode) {
            this.playSound(ModSoundEvents.ENTITY_GIANT_STEP, 0.25F, 1.0F);
        }
    }

    @Override
    public float getSoundVolume() {
        if (OPTIONS.doomMode) {
            return 5.0F;
        } else {
            return 1.0F;
        }
    }

    boolean isTargetingUnderwater() {
        if (this.targetingUnderwater) {
            return true;
        } else {
            LivingEntity livingEntity = this.getTarget();
            return livingEntity != null && livingEntity.isTouchingWater();
        }
    }

    @Deprecated
    protected final void onGiantDamage() {
        TntEntity tntEntity = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity.setFuse(100);
        tntEntity.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity1 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity1.setFuse(100);
        tntEntity1.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity2 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity2.setFuse(100);
        tntEntity2.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity3 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity3.setFuse(100);
        tntEntity3.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        this.world.playSound((PlayerEntity)null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
        this.world.spawnEntity(tntEntity);
        this.world.spawnEntity(tntEntity1);
        this.world.spawnEntity(tntEntity2);
        this.world.spawnEntity(tntEntity3);
    }

    @Deprecated
    protected final void onGiantDeath() {
        TntEntity tntEntity = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity.setInvulnerable(true);
        tntEntity.setFuse(100);
        tntEntity.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity1 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity1.setFuse(100);
        tntEntity1.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity2 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity2.setFuse(100);
        tntEntity2.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity3 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity3.setFuse(100);
        tntEntity3.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity4 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity4.setFuse(100);
        tntEntity4.refreshPositionAndAngles(this.getX() + 5, this.getY() + 50, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity5 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity5.setFuse(100);
        tntEntity5.refreshPositionAndAngles(this.getX() - 5, this.getY() + 50, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity6 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity6.setFuse(100);
        tntEntity6.refreshPositionAndAngles(this.getX() + 5, this.getY() + 50, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity7 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity7.setFuse(100);
        tntEntity7.refreshPositionAndAngles(this.getX() - 5, this.getY() + 50, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity8 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity8.setFuse(120);
        tntEntity8.refreshPositionAndAngles(this.getX() + 5, this.getY() + 75, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity9 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity9.setFuse(120);
        tntEntity9.refreshPositionAndAngles(this.getX() - 5, this.getY() + 75, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity10 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity10.setFuse(120);
        tntEntity10.refreshPositionAndAngles(this.getX() + 5, this.getY() + 75, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity11 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity11.setFuse(120);
        tntEntity11.refreshPositionAndAngles(this.getX() - 5, this.getY() + 75, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity12 = (TntEntity)EntityType.TNT.create(this.world);
        tntEntity12.setFuse(140);
        tntEntity12.refreshPositionAndAngles(this.getX(), this.getY() + 100, this.getZ(), 0.0F, 0.0F);
        this.world.playSound((PlayerEntity)null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
        this.world.spawnEntity(tntEntity);
        this.world.spawnEntity(tntEntity1);
        this.world.spawnEntity(tntEntity2);
        this.world.spawnEntity(tntEntity3);
        this.world.spawnEntity(tntEntity4);
        this.world.spawnEntity(tntEntity5);
        this.world.spawnEntity(tntEntity6);
        this.world.spawnEntity(tntEntity7);
        this.world.spawnEntity(tntEntity8);
        this.world.spawnEntity(tntEntity9);
        this.world.spawnEntity(tntEntity10);
        this.world.spawnEntity(tntEntity11);
        this.world.spawnEntity(tntEntity12);
    }

    static class GiantAttackGoal extends MeleeAttackGoal {
        private final GiantEntity giant;
        private int ticks;

        GiantAttackGoal(GiantEntity giant, double speed, boolean pauseWhenMobIdle) {
            super(giant, speed, pauseWhenMobIdle);
            this.giant = giant;
        }

        public void start() {
            super.start();
            this.ticks = 0;
        }

        public void stop() {
            super.stop();
            this.giant.setAttacking(false);
        }

        public void tick() {
            super.tick();
            ++this.ticks;
            this.giant.setAttacking(this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2);
        }
    }
}
