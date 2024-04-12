package net.dillon.speedrunnermod.mixin.main.entity.giant;

import net.dillon.speedrunnermod.entity.Giant;
import net.dillon.speedrunnermod.entity.GiantAttackGoal;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.MathUtil;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(GiantEntity.class)
public class GiantEntityMixin extends HostileEntity implements Giant {
    @Unique
    protected SwimNavigation waterNavigation;
    @Unique
    protected MobNavigation landNavigation;
    @Unique
    boolean targetingUnderwater;
    @Unique
    private ServerBossBar bossBar;

    public GiantEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getXpToDrop() {
        int looting = attackingPlayer != null ? EnchantmentHelper.getLooting(attackingPlayer) * 150 : 0;
        this.experiencePoints = 50 + looting;
        if (this.experiencePoints > 0) {
            int i = this.experiencePoints;

            int j;
            for(j = 0; j < this.armorItems.size(); ++j) {
                if (!this.armorItems.get(j).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                    i += 1 + this.random.nextInt(3);
                }
            }

            for(j = 0; j < this.handItems.size(); ++j) {
                if (!this.handItems.get(j).isEmpty() && this.handDropChances[j] <= 1.0F) {
                    i += 1 + this.random.nextInt(3);
                }
            }

            return i;
        } else {
            return this.experiencePoints;
        }
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (DOOM_MODE) {
            this.bossBar = new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS);
            this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
            this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0.0F);
            this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0.0F);
            this.waterNavigation = new SwimNavigation(this, world);
            this.landNavigation = new MobNavigation(this, world);
        }
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createGiantAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 300.0D : 100.0D;
        final double genericMovementSpeed = DOOM_MODE ? 0.3500000528343624D : 0.5D;
        final double genericAttackDamage = DOOM_MODE ? 10.0D : 50.0D;
        final double genericAttackKnockback = DOOM_MODE ? 1.0D : 0.0D;
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback);
    }

    @Override
    protected void initGoals() {
        if (DOOM_MODE) {
            this.goalSelector.add(1, new SwimGoal(this));
            this.goalSelector.add(2, new GiantAttackGoal((GiantEntity) (Object) this, 1.0D, false));
            this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
            this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 32.0F));
            this.goalSelector.add(8, new LookAroundGoal(this));
            this.targetSelector.add(2, new RevengeGoal(this));
            this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
            this.targetSelector.add(3, new ActiveTargetGoal<>(this, MobEntity.class, true));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (DOOM_MODE) {
            if (this.age % 10 == 0) {
                this.heal(0.6F);
            }

            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
        }
    }

    @Override
    public void attemptTickInVoid() {
        if (DOOM_MODE) {
            if (this.world instanceof ServerWorld && this.world.getRegistryKey() == World.END) {
                if (this.getY() < (double)(this.world.getBottomY() - 64)) {
                    this.teleport(0, 96, 0, true);
                    if (!this.isSilent()) {
                        this.world.playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 10.0F, 1.0F);
                        this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 10.0F, 1.0F);
                    }
                }
            }
        } else {
            super.attemptTickInVoid();
        }
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (DOOM_MODE) {
            this.onGiantDeath();
            if (!this.isSilent()) {
                if (attackingPlayer != null && attackingPlayer instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity)attackingPlayer).networkHandler.sendPacket(new PlaySoundS2CPacket(SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, SoundCategory.BLOCKS, this.attackingPlayer.getX(), this.attackingPlayer.getY(), this.attackingPlayer.getZ(), 1.0F, 1.0F, this.world.getRandom().nextLong()));
                }
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (DOOM_MODE) {
            Entity entity = source.getSource();

            if (entity instanceof WitherSkullEntity ||
                    entity instanceof IronGolemEntity ||
                    entity instanceof RavagerEntity ||
                    entity instanceof VindicatorEntity ||
                    entity instanceof ZombieEntity ||
                    entity instanceof EnderDragonEntity ||
                    entity instanceof EndermanEntity ||
                    entity instanceof VexEntity ||
                    entity instanceof EvokerEntity ||
                    entity instanceof EvokerFangsEntity ||
                    entity instanceof AreaEffectCloudEntity) {
                return false;
            }

            if (this.getHealth() <= 150 && entity instanceof ProjectileEntity projectile) {
                if (projectile.getOwner() != null) {
                    projectile.getOwner().damage(DamageSource.GENERIC, MathUtil.randomFloat(1.0F, 3.0F));
                }
                return false;
            }

            if (this.getHealth() <= 100 && entity instanceof PlayerEntity) {
                this.heal(MathUtil.randomFloat(1.15F, 2.95F));
            }

            if (this.random.nextFloat() < 0.10F) {
                this.onGiantDamage();
            }

            if (this.random.nextFloat() < 0.05F && this.getHealth() <= 250) {
                this.onGiantDamageDropFood();
            }
        }

        return super.damage(source, amount);
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (DOOM_MODE) {
            this.world.sendEntityStatus(this, (byte)4);
        }
        return DOOM_MODE ? Giant.tryAttack(this, (LivingEntity)target) : super.tryAttack(target);
    }

    @Override
    protected void knockback(LivingEntity target) {
        if (DOOM_MODE) {
            Giant.knockback(this, target);
        }
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (DOOM_MODE) {
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
        if (DOOM_MODE) {
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
        if (DOOM_MODE) {
            if (this.world.getDifficulty() == Difficulty.PEACEFUL && this.isDisallowedInPeaceful()) {
                this.discard();
            } else {
                this.despawnCounter = 0;
            }
        }
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return !DOOM_MODE;
    }

    @Override
    public boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source) {
        return !DOOM_MODE;
    }

    @Override
    public boolean isFireImmune() {
        return DOOM_MODE;
    }

    @Override
    public boolean isImmuneToExplosion() {
        return DOOM_MODE;
    }

    @Override
    public boolean canStartRiding(Entity entity) {
        return !DOOM_MODE && super.canStartRiding(entity);
    }

    @Override
    public boolean canUsePortals() {
        return !DOOM_MODE;
    }

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        if (DOOM_MODE) {
            this.bossBar.setName(this.getDisplayName());
        }
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        if (DOOM_MODE) {
            this.bossBar.addPlayer(player);
        }
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        if (DOOM_MODE) {
            this.bossBar.removePlayer(player);
        }
    }

    @Override
    public EntityGroup getGroup() {
        return DOOM_MODE ? EntityGroup.UNDEAD : EntityGroup.DEFAULT;
    }

    @Override
    public SoundCategory getSoundCategory() {
        return DOOM_MODE ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return DOOM_MODE ? SoundEvents.ENTITY_ZOMBIE_AMBIENT : null;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return DOOM_MODE ? SoundEvents.ENTITY_ZOMBIE_HURT : SoundEvents.ENTITY_GENERIC_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return DOOM_MODE ? SoundEvents.ENTITY_ZOMBIE_DEATH : SoundEvents.ENTITY_GENERIC_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (DOOM_MODE) {
            this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.25F, 1.0F);
        }
    }

    @Override
    public float getSoundVolume() {
        return DOOM_MODE ? 5.0F : 1.0F;
    }

    @Unique
    boolean isTargetingUnderwater() {
        if (this.targetingUnderwater) {
            return true;
        } else {
            LivingEntity livingEntity = this.getTarget();
            return livingEntity != null && livingEntity.isTouchingWater();
        }
    }

    /**
     * Drops rotten flesh randomly when the giant is damaged.
     */
    @Unique
    private void onGiantDamageDropFood() {
        for (int i = 0; i < 3; i++) {
            this.dropItem(Items.ROTTEN_FLESH);
        }

        if (this.random.nextFloat() < 0.3F) {
            for (int i = 0; i < 2; i++) {
                this.dropItem(Items.ROTTEN_FLESH);
            }
        }

        if (this.random.nextFloat() < 0.2F) {
            for (int i = 0; i < 2; i++) {
                this.dropItem(ModItems.COOKED_FLESH);
            }
        }
    }

    /**
     * Spawns four TNT entities around the giant, randomly, when damaged.
     */
    @Unique
    private void onGiantDamage() {
        for (int i = 0; i < 4; i++) {
            TntEntity tnt = EntityType.TNT.create(this.world);
            tnt.setFuse(100);
            int x = i == 0 || i == 2 ? 5 : -5;
            int z = i == 0 || i == 1 ? 5 : -5;
            tnt.refreshPositionAndAngles(this.getX() + x, this.getY() + 25, this.getZ() + z, 0.0F, 0.0F);
            this.world.playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
            this.world.spawnEntity(tnt);
        }
    }

    /**
     * Spawns TNT (13 exactly) entities around the giant upon dying.
     */
    @Unique
    private void onGiantDeath() {
        TntEntity tntEntity = EntityType.TNT.create(this.world);
        tntEntity.setInvulnerable(true);
        tntEntity.setFuse(100);
        tntEntity.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity1 = EntityType.TNT.create(this.world);
        tntEntity1.setFuse(100);
        tntEntity1.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity2 = EntityType.TNT.create(this.world);
        tntEntity2.setFuse(100);
        tntEntity2.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity3 = EntityType.TNT.create(this.world);
        tntEntity3.setFuse(100);
        tntEntity3.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity4 = EntityType.TNT.create(this.world);
        tntEntity4.setFuse(100);
        tntEntity4.refreshPositionAndAngles(this.getX() + 5, this.getY() + 50, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity5 = EntityType.TNT.create(this.world);
        tntEntity5.setFuse(100);
        tntEntity5.refreshPositionAndAngles(this.getX() - 5, this.getY() + 50, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity6 = EntityType.TNT.create(this.world);
        tntEntity6.setFuse(100);
        tntEntity6.refreshPositionAndAngles(this.getX() + 5, this.getY() + 50, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity7 = EntityType.TNT.create(this.world);
        tntEntity7.setFuse(100);
        tntEntity7.refreshPositionAndAngles(this.getX() - 5, this.getY() + 50, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity8 = EntityType.TNT.create(this.world);
        tntEntity8.setFuse(120);
        tntEntity8.refreshPositionAndAngles(this.getX() + 5, this.getY() + 75, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity9 = EntityType.TNT.create(this.world);
        tntEntity9.setFuse(120);
        tntEntity9.refreshPositionAndAngles(this.getX() - 5, this.getY() + 75, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity10 = EntityType.TNT.create(this.world);
        tntEntity10.setFuse(120);
        tntEntity10.refreshPositionAndAngles(this.getX() + 5, this.getY() + 75, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity11 = EntityType.TNT.create(this.world);
        tntEntity11.setFuse(120);
        tntEntity11.refreshPositionAndAngles(this.getX() - 5, this.getY() + 75, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity12 = EntityType.TNT.create(this.world);
        tntEntity12.setFuse(140);
        tntEntity12.refreshPositionAndAngles(this.getX(), this.getY() + 100, this.getZ(), 0.0F, 0.0F);
        this.world.playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
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
}