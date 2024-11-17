package net.dillon.speedrunnermod.mixin.main.entity.giant;

import net.dillon.speedrunnermod.entity.Giant;
import net.dillon.speedrunnermod.entity.GiantAttackGoal;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.util.MathUtil;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
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
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

/**
 * The {@code Giant Boss (doom mode)} exclusive.
 * <p>- Constantly regenerates health</p>
 * <p>- Knocks back players and mobs when attacking</p>
 * <p>- Teleports to the middle of the end island if it falls into the void</p>
 * <p>- Random chance of spawning TNT as a defence mechanism</p>
 * <p>- Immune to explosions, fall damage, fire and lava damage, and cannot go through end portals or gateways</p>
 * <p>- Summons TNT upon death</p>
 * <p>- And more...</p>
 */
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

    /**
     * Drops more experience upon death when using looting.
     */
    @Override
    public int getXpToDrop(ServerWorld world) {
        int looting = attackingPlayer != null ? EnchantmentHelper.getEquipmentLevel(ItemUtil.enchantment((GiantEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 150 : 0;
        this.experiencePoints = 50 + looting;
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
    }

    /**
     * Gives the Giant a bossbar and other navigation functions.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (DOOM_MODE) {
            this.bossBar = new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS);
            this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
            this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0.0F);
            this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0.0F);
            this.waterNavigation = new SwimNavigation(this, this.getWorld());
            this.landNavigation = new MobNavigation(this, this.getWorld());
        }
    }

    /**
     * @author Dillon8775
     * @reason Attributes for the Giant, including maximum health, movement speed, attack damage, and knockback amount.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createGiantAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 300.0D : 100.0D;
        final double genericMovementSpeed = DOOM_MODE ? 0.3500000528343624D : 0.5D;
        final double genericAttackDamage = DOOM_MODE ? 10.0D : 50.0D;
        final double genericAttackKnockback = DOOM_MODE ? 1.0D : 0.0D;
        return HostileEntity.createHostileAttributes().add(EntityAttributes.MAX_HEALTH, genericMaxHealth).add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.ATTACK_KNOCKBACK, genericAttackKnockback);
    }

    /**
     * Gives the Giant different goals, to be able to swim, look around, attack other entities, etc.
     */
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

    /**
     * Ticks the bossbar and heals the giant progressively.
     */
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

    /**
     * Teleports the Giant to the middle of the end island if it falls into the void.
     */
    @Override
    public void attemptTickInVoid() {
        if (DOOM_MODE) {
            if (this.getWorld() instanceof ServerWorld && this.getWorld().getRegistryKey() == World.END) {
                if (this.getY() < (double)(this.getWorld().getBottomY() - 64)) {
                    this.teleport(0, 96, 0, true);
                    if (!this.isSilent()) {
                        this.getWorld().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 10.0F, 1.0F);
                        this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 10.0F, 1.0F);
                    }
                }
            }
        } else {
            super.attemptTickInVoid();
        }
    }

    /**
     * Summons TNT upon death and plays a fitting sound effect.
     */
    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (DOOM_MODE) {
            this.onGiantDeath();
            if (!this.isSilent()) {
                if (attackingPlayer != null && attackingPlayer instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity)attackingPlayer).networkHandler.sendPacket(new PlaySoundS2CPacket(SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, SoundCategory.BLOCKS, this.attackingPlayer.getX(), this.attackingPlayer.getY(), this.attackingPlayer.getZ(), 1.0F, 1.0F, this.getWorld().getRandom().nextLong()));
                }
            }
        }
    }

    /**
     * Handles damaging for the Giant.
     */
    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
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
                    projectile.getOwner().damage(world, projectile.getOwner().getDamageSources().generic(), MathUtil.randomFloat(1.0F, 3.0F));
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
                this.onGiantDamageDropFood(world);
            }
        }

        return super.damage(world, source, amount);
    }

    /**
     * Handles attacking for the Giant.
     */
    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        if (DOOM_MODE) {
            this.getWorld().sendEntityStatus(this, (byte)4);
        }
        return DOOM_MODE ? Giant.tryAttack(world, this, (LivingEntity)target) : super.tryAttack(world, target);
    }

    /**
     * Handles knockback for the Giant.
     */
    @Override
    protected void knockback(LivingEntity target) {
        if (DOOM_MODE) {
            Giant.knockback(this, target);
        }
    }

    /**
     * Handles movements for the Giant.
     */
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

    /**
     * Handles swimming for the Giant.
     */
    @Override
    public void updateSwimming() {
        super.updateSwimming();
        if (DOOM_MODE) {
            if (!this.getWorld().isClient) {
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

    /**
     * Checks if the Giant can despawn.
     */
    @Override
    public void checkDespawn() {
        if (DOOM_MODE) {
            if (this.getWorld().getDifficulty() == Difficulty.PEACEFUL && this.isDisallowedInPeaceful()) {
                this.discard();
            } else {
                this.despawnCounter = 0;
            }
        }
    }

    /**
     * Makes the Giant immune to fall damage.
     */
    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return !DOOM_MODE;
    }

    /**
     * Prevents the Giant from getting status effects.
     */
    @Override
    public boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source) {
        return !DOOM_MODE;
    }

    /**
     * Makes the Giant immune to fire and lava damage.
     */
    @Override
    public boolean isFireImmune() {
        return DOOM_MODE;
    }

    /**
     * Makes the Giant immune to explosion damage.
     */
    @Override
    public boolean isImmuneToExplosion(Explosion explosion) {
        return DOOM_MODE;
    }

    /**
     * Prevents the Giant from being able to be ridden.
     */
    @Override
    public boolean canStartRiding(Entity entity) {
        return !DOOM_MODE && super.canStartRiding(entity);
    }

    /**
     * Prevents the Giant from being able to use portals.
     */
    @Override
    public boolean canUsePortals(boolean allowVehicles) {
        return !DOOM_MODE;
    }

    /**
     * Sets the bossbars name to {@code "Giant".}
     */
    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        if (DOOM_MODE) {
            this.bossBar.setName(this.getDisplayName());
        }
    }

    /**
     * Detects when a player is {@code in range} of the Giant, and then {@code displays} the bossbar on that players screen.
     */
    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        if (DOOM_MODE) {
            this.bossBar.addPlayer(player);
        }
    }

    /**
     * Detects when the player gets {@code out of range} of the Giant, and then {@code removes} the bossbar from that players screen.
     */
    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        if (DOOM_MODE) {
            this.bossBar.removePlayer(player);
        }
    }

    /**
     * Puts the Giant's sound under the {@code "hostile"} category (neutral if doom mode is disabled).
     */
    @Override
    public SoundCategory getSoundCategory() {
        return DOOM_MODE ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
    }

    /**
     * Returns the {@code ambient sound} of a Giant.
     */
    @Override
    public SoundEvent getAmbientSound() {
        return DOOM_MODE ? SoundEvents.ENTITY_ZOMBIE_AMBIENT : null;
    }

    /**
     * Returns the {@code hurt sound} of a Giant.
     */
    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return DOOM_MODE ? SoundEvents.ENTITY_ZOMBIE_HURT : SoundEvents.ENTITY_GENERIC_HURT;
    }

    /**
     * Returns the {@code death sound} of a Giant.
     */
    @Override
    public SoundEvent getDeathSound() {
        return DOOM_MODE ? SoundEvents.ENTITY_ZOMBIE_DEATH : SoundEvents.ENTITY_GENERIC_DEATH;
    }

    /**
     * Applies the {@code stepping sound} for a Giant.
     */
    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (DOOM_MODE) {
            this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.25F, 1.0F);
        }
    }

    /**
     * Returns the {@code volume} of a Giant.
     */
    @Override
    public float getSoundVolume() {
        return DOOM_MODE ? 5.0F : 1.0F;
    }

    /**
     * Returns true if the Giant is attacking while underwater.
     */
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
     * Drops rotten flesh randomly when the Giant is damaged.
     */
    @Unique
    private void onGiantDamageDropFood(ServerWorld serverWorld) {
        for (int i = 0; i < 3; i++) {
            this.dropItem(serverWorld, Items.ROTTEN_FLESH);
        }

        if (this.random.nextFloat() < 0.3F) {
            for (int i = 0; i < 2; i++) {
                this.dropItem(serverWorld, Items.ROTTEN_FLESH);
            }
        }

        if (this.random.nextFloat() < 0.2F) {
            for (int i = 0; i < 2; i++) {
                this.dropItem(serverWorld, ModItems.COOKED_FLESH);
            }
        }
    }

    /**
     * Spawns four TNT entities around the giant, randomly, when damaged.
     */
    @Unique
    private void onGiantDamage() {
        for (int i = 0; i < 4; i++) {
            TntEntity tnt = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
            tnt.setFuse(100);
            int x = i == 0 || i == 2 ? 5 : -5;
            int z = i == 0 || i == 1 ? 5 : -5;
            tnt.refreshPositionAndAngles(this.getX() + x, this.getY() + 25, this.getZ() + z, 0.0F, 0.0F);
            this.getWorld().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
            this.getWorld().spawnEntity(tnt);
        }
    }

    /**
     * Spawns TNT (13 exactly) entities around the giant upon dying.
     */
    @Unique
    private void onGiantDeath() {
        TntEntity tntEntity = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity.setInvulnerable(true);
        tntEntity.setFuse(100);
        tntEntity.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity1 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity1.setFuse(100);
        tntEntity1.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity2 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity2.setFuse(100);
        tntEntity2.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity3 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity3.setFuse(100);
        tntEntity3.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity4 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity4.setFuse(100);
        tntEntity4.refreshPositionAndAngles(this.getX() + 5, this.getY() + 50, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity5 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity5.setFuse(100);
        tntEntity5.refreshPositionAndAngles(this.getX() - 5, this.getY() + 50, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity6 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity6.setFuse(100);
        tntEntity6.refreshPositionAndAngles(this.getX() + 5, this.getY() + 50, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity7 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity7.setFuse(100);
        tntEntity7.refreshPositionAndAngles(this.getX() - 5, this.getY() + 50, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity8 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity8.setFuse(120);
        tntEntity8.refreshPositionAndAngles(this.getX() + 5, this.getY() + 75, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity9 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity9.setFuse(120);
        tntEntity9.refreshPositionAndAngles(this.getX() - 5, this.getY() + 75, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity10 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity10.setFuse(120);
        tntEntity10.refreshPositionAndAngles(this.getX() + 5, this.getY() + 75, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity11 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity11.setFuse(120);
        tntEntity11.refreshPositionAndAngles(this.getX() - 5, this.getY() + 75, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity12 = EntityType.TNT.create(this.getWorld(), SpawnReason.TRIGGERED);
        tntEntity12.setFuse(140);
        tntEntity12.refreshPositionAndAngles(this.getX(), this.getY() + 100, this.getZ(), 0.0F, 0.0F);
        this.getWorld().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
        this.getWorld().spawnEntity(tntEntity);
        this.getWorld().spawnEntity(tntEntity1);
        this.getWorld().spawnEntity(tntEntity2);
        this.getWorld().spawnEntity(tntEntity3);
        this.getWorld().spawnEntity(tntEntity4);
        this.getWorld().spawnEntity(tntEntity5);
        this.getWorld().spawnEntity(tntEntity6);
        this.getWorld().spawnEntity(tntEntity7);
        this.getWorld().spawnEntity(tntEntity8);
        this.getWorld().spawnEntity(tntEntity9);
        this.getWorld().spawnEntity(tntEntity10);
        this.getWorld().spawnEntity(tntEntity11);
        this.getWorld().spawnEntity(tntEntity12);
    }
}