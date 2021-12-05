package net.dilloney.speedrunnermod.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.item.ModFoodComponents;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.util.entity.Giant;
import net.dilloney.speedrunnermod.world.gen.feature.ModOrePlacedFeatures;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.structure.*;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;
import java.util.function.BiConsumer;

public class ModMixins {
    private static final String DEFAULT_ATTRIBUTE_CONTAINER = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;";

    @Mixin(net.minecraft.block.AbstractBlock.AbstractBlockState.class)
    public static class AbstractBlockStateMixin {

        @Inject(method = "getHardness", at = @At("HEAD"), cancellable = true)
        private void getHardness(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
            SpeedrunnerMod.applyBlockHardnessValues(world, pos, cir);
        }
    }

    @Mixin(net.minecraft.block.Block.class)
    public static class BlockMixin {

        @ModifyArg(method = "onLandedUpon", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;handleFallDamage(FFLnet/minecraft/entity/damage/DamageSource;)Z"), index = 1)
        private float onLandedUpon(float damageMultiplier) {
            return SpeedrunnerMod.options().main.doomMode ? 1.0F : 0.7F;
        }
    }

    @Mixin(net.minecraft.loot.function.ApplyBonusLootFunction.OreDrops.class)
    public static class ApplyBonusLootFunctionMixin {

        @Overwrite
        public int getValue(Random random, int initialCount, int enchantmentLevel) {
            return enchantmentLevel > 0 ? initialCount * (enchantmentLevel + 1) : initialCount;
        }
    }

    @Mixin(net.minecraft.block.OreBlock.class)
    public static class OreBlockMixin extends Block {

        public OreBlockMixin(Settings settings) {
            super(settings);
        }

        @Inject(method = "onStacksDropped", at = @At("TAIL"))
        public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                if (state.isOf(Blocks.GOLD_ORE)) {
                    int gold = 2 + world.random.nextInt(4);
                    this.dropExperience(world, pos, gold);
                } else if (state.isOf(Blocks.DEEPSLATE_GOLD_ORE)) {
                    int dgold = 2 + world.random.nextInt(4);
                    this.dropExperience(world, pos, dgold);
                } else if (state.isOf(Blocks.IRON_ORE)) {
                    int iron = 1 + world.random.nextInt(1);
                    this.dropExperience(world, pos, iron);
                } else if (state.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
                    int diron = 1 + world.random.nextInt(1);
                    this.dropExperience(world, pos, diron);
                }
            }
        }
    }

    @Mixin(value = net.minecraft.entity.boss.dragon.EnderDragonEntity.class, priority = 999)
    public abstract static class EnderDragonEntityMixin extends MobEntity {
        @Shadow
        private EndCrystalEntity connectedCrystal;
        @Shadow
        private int damageDuringSitting;
        @Shadow
        abstract boolean parentDamage(DamageSource source, float amount);
        @Shadow @Final
        private EnderDragonPart head;
        @Shadow @Final
        private PhaseManager phaseManager;

        public EnderDragonEntityMixin(EntityType<? extends EnderDragonEntity> entityType, World world) {
            super(entityType, world);
        }

        @ModifyArg(method = "createEnderDragonAttributes", at = @At(value = "INVOKE", target = DEFAULT_ATTRIBUTE_CONTAINER), index = 1)
        private static double genericMaxHealth(double baseValue) {
            return SpeedrunnerMod.options().main.doomMode ? 500.0D : 100.0D;
        }

        @Overwrite
        private void tickWithEndCrystals() {
            if (this.connectedCrystal != null) {
                if (this.connectedCrystal.isRemoved()) {
                    this.connectedCrystal = null;
                } else if (this.age % 10 == 0 && this.getHealth() < this.getMaxHealth()) {
                    final float i = SpeedrunnerMod.options().main.doomMode ? 1.7F : 0.1F;
                    this.setHealth(this.getHealth() + i);
                }
            }

            if (this.random.nextInt(10) == 0) {
                List<EndCrystalEntity> list = this.world.getNonSpectatingEntities(EndCrystalEntity.class, this.getBoundingBox().expand(32.0D));
                EndCrystalEntity endCrystalEntity = null;
                double d = 1.7976931348623157E308D;
                Iterator var5 = list.iterator();

                while(var5.hasNext()) {
                    EndCrystalEntity endCrystalEntity2 = (EndCrystalEntity)var5.next();
                    double e = endCrystalEntity2.squaredDistanceTo(this);
                    if (e < d) {
                        d = e;
                        endCrystalEntity = endCrystalEntity2;
                    }
                }

                this.connectedCrystal = endCrystalEntity;
            }

        }

        @ModifyArg(method = "damageLivingEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 1)
        private float damageLivingEntities(float amount) {
            return SpeedrunnerMod.options().main.doomMode ? 12.0F : 3.0F;
        }

        @Overwrite
        public boolean damagePart(EnderDragonPart part, DamageSource source, float amount) {
            if (this.phaseManager.getCurrent().getType() == PhaseType.DYING) {
                return false;
            } else {
                amount = this.phaseManager.getCurrent().modifyDamageTaken(source, amount);
                if (part != this.head) {
                    amount = amount / 4.0F + Math.min(amount, 1.0F);
                }

                if (amount < 0.01F) {
                    return false;
                } else {
                    if (source.getAttacker() instanceof PlayerEntity || source.isExplosive()) {
                        float f = this.getHealth();
                        this.parentDamage(source, amount);
                        if (this.isDead() && !this.phaseManager.getCurrent().isSittingOrHovering()) {
                            this.setHealth(1.0F);
                            this.phaseManager.setPhase(PhaseType.DYING);
                        }

                        if (this.phaseManager.getCurrent().isSittingOrHovering()) {
                            this.damageDuringSitting = (int)((float)this.damageDuringSitting + (f - this.getHealth()));
                            final float g = SpeedrunnerMod.options().main.doomMode ? 0.18F : 0.60F;
                            if ((float)this.damageDuringSitting > g * this.getMaxHealth()) {
                                this.damageDuringSitting = 0;
                                this.phaseManager.setPhase(PhaseType.TAKEOFF);
                            }
                        }
                    }

                    return true;
                }
            }
        }

        @ModifyArg(method = "crystalDestroyed", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;damagePart(Lnet/minecraft/entity/boss/dragon/EnderDragonPart;Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 2)
        private float crystalDestroyed(float amount) {
            return SpeedrunnerMod.options().main.doomMode ? 3.0F : 20.0F;
        }
    }

    @Mixin(net.minecraft.entity.boss.dragon.EnderDragonFight.class)
    public static class EnderDragonFightMixin {
        @Shadow @Final
        private ServerWorld world;
        @Shadow
        private UUID dragonUuid;

        @Overwrite
        private EnderDragonEntity createDragon() {
            this.world.getWorldChunk(new BlockPos(0, 128, 0));
            EnderDragonEntity enderDragonEntity = (EnderDragonEntity)EntityType.ENDER_DRAGON.create(this.world);
            enderDragonEntity.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
            enderDragonEntity.refreshPositionAndAngles(0.0D, 128.0D, 0.0D, this.world.random.nextFloat() * 360.0F, 0.0F);
            this.world.spawnEntity(enderDragonEntity);
            this.dragonUuid = enderDragonEntity.getUuid();
            if (SpeedrunnerMod.options().main.getDragonPerchTime() >= 21) {
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        enderDragonEntity.getPhaseManager().setPhase(PhaseType.LANDING);
                    }
                }, SpeedrunnerMod.options().main.getDragonPerchTime() * 1000);
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

    @Mixin(net.minecraft.entity.boss.WitherEntity.class)
    public static class WitherEntityMixin {

        @ModifyArg(method = "createWitherAttributes", at = @At(value = "INVOKE", target = DEFAULT_ATTRIBUTE_CONTAINER), index = 1)
        private static double genericMaxHealth(double baseValue) {
            return SpeedrunnerMod.options().main.doomMode ? 300.0D : 100.0D;
        }
    }

    @Mixin(net.minecraft.entity.mob.AbstractSkeletonEntity.class)
    public static class AbstractSkeletonEntityMixin extends HostileEntity {

        public AbstractSkeletonEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @ModifyArg(method = "createAbstractSkeletonAttributes", at = @At(value = "INVOKE", target = DEFAULT_ATTRIBUTE_CONTAINER), index = 1)
        private static double genericMovementSpeed(double baseValue) {
            return SpeedrunnerMod.options().main.doomMode ? 0.3D : 0.25D;
        }

        @ModifyVariable(method = "updateAttackType", at = @At("STORE"), ordinal = 0)
        private int updateAttackType(int x) {
            int i = SpeedrunnerMod.options().main.doomMode ? 5 : 20;
            if (this.world.getDifficulty() != Difficulty.HARD) {
                i = SpeedrunnerMod.options().main.doomMode ? 10 : 20;
            }
            return i;
        }
    }

    @Mixin(net.minecraft.entity.mob.BlazeEntity.class)
    public static class BlazeEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createBlazeAttributes() {
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 48.0D : 16.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
        }

        @Mixin(net.minecraft.entity.mob.BlazeEntity.ShootFireballGoal.class)
        public abstract static class ShootFireballGoalMixin extends Goal {
            @Shadow
            private int fireballsFired;
            @Shadow
            private int fireballCooldown;
            @Shadow
            private int targetNotVisibleTicks;
            @Shadow @Final
            private BlazeEntity blaze;
            @Shadow
            abstract double getFollowRange();

            @Overwrite
            public void tick() {
                --this.fireballCooldown;
                LivingEntity livingEntity = this.blaze.getTarget();
                if (livingEntity != null) {
                    boolean bl = this.blaze.getVisibilityCache().canSee(livingEntity);
                    if (bl) {
                        this.targetNotVisibleTicks = 0;
                    } else {
                        ++this.targetNotVisibleTicks;
                    }

                    double d = this.blaze.squaredDistanceTo(livingEntity);
                    if (d < 4.0D) {
                        if (!bl) {
                            return;
                        }

                        if (this.fireballCooldown <= 0) {
                            this.fireballCooldown = 20;
                            this.blaze.tryAttack(livingEntity);
                        }

                        this.blaze.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0D);
                    } else if (d < this.getFollowRange() * this.getFollowRange() && bl) {
                        double e = livingEntity.getX() - this.blaze.getX();
                        double f = livingEntity.getBodyY(0.5D) - this.blaze.getBodyY(0.5D);
                        double g = livingEntity.getZ() - this.blaze.getZ();
                        if (this.fireballCooldown <= 0) {
                            ++this.fireballsFired;
                            if (this.fireballsFired == 1) {
                                this.fireballCooldown = 60;
                                this.blaze.setFireActive(true);
                            } else if (this.fireballsFired <= 4) {
                                this.fireballCooldown = 6;
                            } else {
                                this.fireballCooldown = SpeedrunnerMod.options().main.doomMode ? 60 : 120;
                                this.fireballsFired = 0;
                                this.blaze.setFireActive(false);
                            }

                            if (this.fireballsFired > 1) {
                                double h = Math.sqrt(Math.sqrt(d)) * 0.5D;
                                if (!this.blaze.isSilent()) {
                                    this.blaze.world.syncWorldEvent((PlayerEntity)null, 1018, this.blaze.getBlockPos(), 0);
                                }

                                for(int i = 0; i < 1; ++i) {
                                    SmallFireballEntity smallFireballEntity = new SmallFireballEntity(this.blaze.world, this.blaze, e + this.blaze.getRandom().nextGaussian() * h, f, g + this.blaze.getRandom().nextGaussian() * h);
                                    smallFireballEntity.setPosition(smallFireballEntity.getX(), this.blaze.getBodyY(0.5D) + 0.5D, smallFireballEntity.getZ());
                                    this.blaze.world.spawnEntity(smallFireballEntity);
                                }
                            }
                        }

                        this.blaze.getLookControl().lookAt(livingEntity, 10.0F, 10.0F);
                    } else if (this.targetNotVisibleTicks < 5) {
                        this.blaze.getMoveControl().moveTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 1.0D);
                    }

                    super.tick();
                }
            }
        }
    }

    @Mixin(net.minecraft.entity.mob.CaveSpiderEntity.class)
    public static class CaveSpiderEntityMixin extends SpiderEntity {

        public CaveSpiderEntityMixin(EntityType<? extends SpiderEntity> entityType, World world) {
            super(entityType, world);
        }

        @Inject(method = "tryAttack", at = @At(value = "RETURN", ordinal = 0))
        private void tryAttack(Entity target, CallbackInfoReturnable cir) {
            if (SpeedrunnerMod.options().main.doomMode && target instanceof PlayerEntity) {
                ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
            }
        }
    }

    @Mixin(net.minecraft.entity.mob.CreeperEntity.class)
    public abstract static class CreeperEntityMixin extends HostileEntity implements SkinOverlayOwner {
        @Shadow
        int explosionRadius;
        @Shadow
        abstract void ignite();

        public CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @ModifyArg(method = "createCreeperAttributes", at = @At(value = "INVOKE", target = DEFAULT_ATTRIBUTE_CONTAINER), index = 1)
        private static double genericMovementSpeed(double baseValue) {
            return SpeedrunnerMod.options().main.doomMode ? 0.3D : 0.25D;
        }

        @Overwrite
        public ActionResult interactMob(PlayerEntity player, Hand hand) {
            ItemStack itemStack = player.getStackInHand(hand);
            Explosion.DestructionType destructionType = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
            float f = this.shouldRenderOverlay() ? 2.0F : 1.0F;
            if (getCreeperIgnitions(player, hand)) {
                this.world.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
                if (!this.world.isClient && SpeedrunnerMod.options().main.doomMode) {
                    this.discard();
                    this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, destructionType);
                    this.world.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ITEM_BREAK, this.getSoundCategory(), 1.5F, this.random.nextFloat() * 0.4F + 0.8F);
                    itemStack.damage(1, player, (playerx) -> {
                        playerx.sendToolBreakStatus(hand);
                    });
                } else if (!this.world.isClient) {
                    this.ignite();
                    itemStack.damage(1, player, (playerx) -> {
                        playerx.sendToolBreakStatus(hand);
                    });
                }

                return ActionResult.success(this.world.isClient);
            } else {
                return super.interactMob(player, hand);
            }
        }

        private static boolean getCreeperIgnitions(PlayerEntity player, Hand hand) {
            ItemStack itemStack = player.getStackInHand(hand);
            return itemStack.getItem() == Items.FLINT_AND_STEEL || itemStack.getItem() == ModItems.SPEEDRUNNER_FLINT_AND_STEEL;
        }
    }

    @Mixin(net.minecraft.entity.mob.ElderGuardianEntity.class)
    public static class ElderGuardianEntityMixin extends GuardianEntity {

        public ElderGuardianEntityMixin(EntityType<? extends GuardianEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createElderGuardianAttributes() {
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 50.0D : 25.0D;
            return GuardianEntity.createGuardianAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth);
        }

        @Overwrite
        public void mobTick() {
            super.mobTick();
            final int i = SpeedrunnerMod.options().main.doomMode ? 600 : 6000;
            if ((this.age + this.getId()) % i == 0) {
                StatusEffect statusEffect = StatusEffects.MINING_FATIGUE;
                List<ServerPlayerEntity> list = ((ServerWorld)this.world).getPlayers((serverPlayerEntityx) -> {
                    final double d = SpeedrunnerMod.options().main.doomMode ? 3000.0D : 1250.0D;
                    return this.squaredDistanceTo(serverPlayerEntityx) < d && serverPlayerEntityx.interactionManager.isSurvivalLike();
                });
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
                    final int duration = SpeedrunnerMod.options().main.doomMode ? 6000 : 600;
                    serverPlayerEntity.addStatusEffect(new StatusEffectInstance(statusEffect, duration, 2));
                }
            }

            if (!this.hasPositionTarget()) {
                this.setPositionTarget(this.getBlockPos(), 16);
            }
        }
    }

    @Mixin(net.minecraft.entity.mob.EndermanEntity.class)
    public static class EndermanEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createEndermanAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 60.0D : 25.0D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 64.0D : 12.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
        }
    }

    @Mixin(net.minecraft.entity.mob.EndermiteEntity.class)
    public static class EndermiteEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createEndermiteAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.25D : 0.15D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 2.0D : 0.01D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }
    }

    @Mixin(net.minecraft.entity.mob.GhastEntity.class)
    public static class GhastEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createGhastAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 20.0D : 5.0D;
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 100.0D : 16.0D;
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
        }

        @Mixin(net.minecraft.entity.mob.GhastEntity.ShootFireballGoal.class)
        public static class ShootFireballGoalMixin {
            @Shadow @Final
            private GhastEntity ghast;
            @Shadow
            private int cooldown;

            @Overwrite
            public void tick() {
                LivingEntity livingEntity = this.ghast.getTarget();
                double d = 64.0D;
                if (livingEntity.squaredDistanceTo(this.ghast) < 4096.0D && this.ghast.canSee(livingEntity)) {
                    World world = this.ghast.world;
                    ++this.cooldown;
                    if (this.cooldown == 10 && !this.ghast.isSilent()) {
                        world.syncWorldEvent((PlayerEntity)null, 1015, this.ghast.getBlockPos(), 0);
                    }

                    if (this.cooldown == 20) {
                        double e = 4.0D;
                        Vec3d vec3d = this.ghast.getRotationVec(1.0F);
                        double f = livingEntity.getX() - (this.ghast.getX() + vec3d.x * 4.0D);
                        double g = livingEntity.getBodyY(0.5D) - (0.5D + this.ghast.getBodyY(0.5D));
                        double h = livingEntity.getZ() - (this.ghast.getZ() + vec3d.z * 4.0D);
                        if (!this.ghast.isSilent()) {
                            world.syncWorldEvent((PlayerEntity)null, 1016, this.ghast.getBlockPos(), 0);
                        }

                        FireballEntity fireballEntity = new FireballEntity(world, this.ghast, f, g, h, this.ghast.getFireballStrength());
                        fireballEntity.setPosition(this.ghast.getX() + vec3d.x * 4.0D, this.ghast.getBodyY(0.5D) + 0.5D, fireballEntity.getZ() + vec3d.z * 4.0D);
                        world.spawnEntity(fireballEntity);
                        this.cooldown = SpeedrunnerMod.options().main.doomMode ? -5 : -40;
                        if (SpeedrunnerMod.options().main.killGhastUponFireball) {
                            this.ghast.kill();
                        }
                    }
                } else if (this.cooldown > 0) {
                    --this.cooldown;
                }

                this.ghast.setShooting(this.cooldown > 10);
            }
        }
    }

    /**
     * Welcome to the new boss in {@code Doom Mode}.
     */
    @Mixin(net.minecraft.entity.mob.GiantEntity.class)
    public static class GiantEntityMixin extends HostileEntity implements Giant {
        protected SwimNavigation waterNavigation;
        protected MobNavigation landNavigation;
        boolean targetingUnderwater;
        private ServerBossBar bossBar;

        public GiantEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Inject(method = "<init>", at = @At("TAIL"))
        private void init(CallbackInfo ci) {
            if (SpeedrunnerMod.options().main.doomMode) {
                this.bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS));
                this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
                this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0.0F);
                this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0.0F);
                this.waterNavigation = new SwimNavigation(this, world);
                this.landNavigation = new MobNavigation(this, world);
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createGiantAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 300.0D : 100.0D;
            final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.3500000528343624D : 0.5D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 10.0D : 50.0D;
            final double genericAttackKnockback = SpeedrunnerMod.options().main.doomMode ? 1.0D : 0.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback);
        }

        protected void initGoals() {
            if (SpeedrunnerMod.options().main.doomMode) {
                this.goalSelector.add(1, new SwimGoal(this));
                this.goalSelector.add(2, new GiantAttackGoal((GiantEntity) (Object) this, 1.0D, false));
                this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
                this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 32.0F));
                this.goalSelector.add(8, new LookAroundGoal(this));
                this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
                this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
                this.targetSelector.add(3, new ActiveTargetGoal(this, MobEntity.class, true));
            }
        }

        public void tick() {
            super.tick();
            if (SpeedrunnerMod.options().main.doomMode) {
                if (this.age % 10 == 0) {
                    this.heal(0.6F);
                }

                this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
            }
        }

        public void attemptTickInVoid() {
            if (SpeedrunnerMod.options().main.doomMode) {
                if (this.world instanceof ServerWorld && this.world.getRegistryKey() == World.END) {
                    if (this.getY() <= -64) {
                        this.teleport(0, 96, 0, true);
                        if (!this.isSilent()) {
                            this.world.playSound((PlayerEntity) null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 10.0F, 1.0F);
                            this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 10.0F, 1.0F);
                        }
                    }
                }
            } else {
                super.attemptTickInVoid();
            }
        }

        public void onDeath(DamageSource source) {
            super.onDeath(source);
            if (SpeedrunnerMod.options().main.doomMode) {
                this.onGiantDeath();
                if (!this.isSilent()) {
                    this.world.playSound((PlayerEntity) null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, SoundCategory.AMBIENT, 1.0F, 1.0F);
                    this.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0F, 1.0F);
                }
            }
        }

        public boolean damage(DamageSource source, float amount) {
            if (SpeedrunnerMod.options().main.doomMode) {
                Entity entity = source.getSource();

                if (entity instanceof WitherSkullEntity || entity instanceof IronGolemEntity || entity instanceof RavagerEntity || entity instanceof VindicatorEntity || entity instanceof ZombieEntity || entity instanceof EnderDragonEntity) {
                    return false;
                }

                if (this.getHealth() <= 150) {
                    if (entity instanceof ProjectileEntity) {
                        return false;
                    }
                }

                if (this.getHealth() <= 50) {
                    if (entity instanceof AreaEffectCloudEntity) {
                        return false;
                    }
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

        public boolean tryAttack(Entity target) {
            if (SpeedrunnerMod.options().main.doomMode) {
                this.world.sendEntityStatus(this, (byte)4);
            }
            return SpeedrunnerMod.options().main.doomMode ? Giant.tryAttack(this, (LivingEntity)target) : super.tryAttack(target);
        }

        protected void knockback(LivingEntity target) {
            if (SpeedrunnerMod.options().main.doomMode) {
                Giant.knockback(this, target);
            }
        }

        public void travel(Vec3d movementInput) {
            if (SpeedrunnerMod.options().main.doomMode) {
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

        public void updateSwimming() {
            super.updateSwimming();
            if (SpeedrunnerMod.options().main.doomMode) {
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

        public void checkDespawn() {
            if (SpeedrunnerMod.options().main.doomMode) {
                if (this.world.getDifficulty() == Difficulty.PEACEFUL && this.isDisallowedInPeaceful()) {
                    this.discard();
                } else {
                    this.despawnCounter = 0;
                }
            }
        }

        public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
            return !SpeedrunnerMod.options().main.doomMode;
        }

        public boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source) {
            return !SpeedrunnerMod.options().main.doomMode;
        }

        public boolean isFireImmune() {
            return SpeedrunnerMod.options().main.doomMode;
        }

        public boolean isImmuneToExplosion() {
            return SpeedrunnerMod.options().main.doomMode;
        }

        public boolean canStartRiding(Entity entity) {
            return !SpeedrunnerMod.options().main.doomMode && super.canStartRiding(entity);
        }

        public boolean canUsePortals() {
            return !SpeedrunnerMod.options().main.doomMode;
        }

        public void setCustomName(@Nullable Text name) {
            super.setCustomName(name);
            if (SpeedrunnerMod.options().main.doomMode) {
                this.bossBar.setName(this.getDisplayName());
            }
        }

        public void onStartedTrackingBy(ServerPlayerEntity player) {
            super.onStartedTrackingBy(player);
            if (SpeedrunnerMod.options().main.doomMode) {
                this.bossBar.addPlayer(player);
            }
        }

        public void onStoppedTrackingBy(ServerPlayerEntity player) {
            super.onStoppedTrackingBy(player);
            if (SpeedrunnerMod.options().main.doomMode) {
                this.bossBar.removePlayer(player);
            }
        }

        public EntityGroup getGroup() {
            return SpeedrunnerMod.options().main.doomMode ? EntityGroup.UNDEAD : EntityGroup.DEFAULT;
        }

        public SoundCategory getSoundCategory() {
            return SpeedrunnerMod.options().main.doomMode ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
        }

        public SoundEvent getAmbientSound() {
            return SpeedrunnerMod.options().main.doomMode ? SoundEvents.ENTITY_ZOMBIE_AMBIENT : null;
        }

        public SoundEvent getHurtSound(DamageSource source) {
            return SpeedrunnerMod.options().main.doomMode ? SoundEvents.ENTITY_ZOMBIE_HURT : SoundEvents.ENTITY_GENERIC_HURT;
        }

        public SoundEvent getDeathSound() {
            return SpeedrunnerMod.options().main.doomMode ? SoundEvents.ENTITY_ZOMBIE_DEATH : SoundEvents.ENTITY_GENERIC_DEATH;
        }

        protected void playStepSound(BlockPos pos, BlockState state) {
            if (SpeedrunnerMod.options().main.doomMode) {
                this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.25F, 1.0F);
            }
        }

        public float getSoundVolume() {
            return SpeedrunnerMod.options().main.doomMode ? 5.0F : 1.0F;
        }

        boolean isTargetingUnderwater() {
            if (this.targetingUnderwater) {
                return true;
            } else {
                LivingEntity livingEntity = this.getTarget();
                return livingEntity != null && livingEntity.isTouchingWater();
            }
        }

        private void onGiantDamageDropFood() {
            this.dropItem(Items.ROTTEN_FLESH);
            this.dropItem(Items.ROTTEN_FLESH);
            this.dropItem(Items.ROTTEN_FLESH);
            if (this.random.nextFloat() < 0.3F) {
                this.dropItem(Items.ROTTEN_FLESH);
                this.dropItem(Items.ROTTEN_FLESH);
            }

            if (this.random.nextFloat() < 0.2F) {
                this.dropItem(ModItems.COOKED_FLESH);
                this.dropItem(ModItems.COOKED_FLESH);
            }
        }

        private void onGiantDamage() {
            TntEntity tntEntity = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity.setFuse(100);
            tntEntity.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
            TntEntity tntEntity1 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity1.setFuse(100);
            tntEntity1.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
            TntEntity tntEntity2 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity2.setFuse(100);
            tntEntity2.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
            TntEntity tntEntity3 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity3.setFuse(100);
            tntEntity3.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
            this.world.playSound((PlayerEntity) null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
            this.world.spawnEntity(tntEntity);
            this.world.spawnEntity(tntEntity1);
            this.world.spawnEntity(tntEntity2);
            this.world.spawnEntity(tntEntity3);
        }

        private void onGiantDeath() {
            TntEntity tntEntity = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity.setInvulnerable(true);
            tntEntity.setFuse(100);
            tntEntity.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
            TntEntity tntEntity1 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity1.setFuse(100);
            tntEntity1.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
            TntEntity tntEntity2 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity2.setFuse(100);
            tntEntity2.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
            TntEntity tntEntity3 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity3.setFuse(100);
            tntEntity3.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
            TntEntity tntEntity4 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity4.setFuse(100);
            tntEntity4.refreshPositionAndAngles(this.getX() + 5, this.getY() + 50, this.getZ() + 5, 0.0F, 0.0F);
            TntEntity tntEntity5 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity5.setFuse(100);
            tntEntity5.refreshPositionAndAngles(this.getX() - 5, this.getY() + 50, this.getZ() + 5, 0.0F, 0.0F);
            TntEntity tntEntity6 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity6.setFuse(100);
            tntEntity6.refreshPositionAndAngles(this.getX() + 5, this.getY() + 50, this.getZ() - 5, 0.0F, 0.0F);
            TntEntity tntEntity7 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity7.setFuse(100);
            tntEntity7.refreshPositionAndAngles(this.getX() - 5, this.getY() + 50, this.getZ() - 5, 0.0F, 0.0F);
            TntEntity tntEntity8 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity8.setFuse(120);
            tntEntity8.refreshPositionAndAngles(this.getX() + 5, this.getY() + 75, this.getZ() + 5, 0.0F, 0.0F);
            TntEntity tntEntity9 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity9.setFuse(120);
            tntEntity9.refreshPositionAndAngles(this.getX() - 5, this.getY() + 75, this.getZ() + 5, 0.0F, 0.0F);
            TntEntity tntEntity10 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity10.setFuse(120);
            tntEntity10.refreshPositionAndAngles(this.getX() + 5, this.getY() + 75, this.getZ() - 5, 0.0F, 0.0F);
            TntEntity tntEntity11 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity11.setFuse(120);
            tntEntity11.refreshPositionAndAngles(this.getX() - 5, this.getY() + 75, this.getZ() - 5, 0.0F, 0.0F);
            TntEntity tntEntity12 = (TntEntity) EntityType.TNT.create(this.world);
            tntEntity12.setFuse(140);
            tntEntity12.refreshPositionAndAngles(this.getX(), this.getY() + 100, this.getZ(), 0.0F, 0.0F);
            this.world.playSound((PlayerEntity) null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
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

        private static class GiantAttackGoal extends MeleeAttackGoal {
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

    @Mixin(net.minecraft.entity.mob.GuardianEntity.class)
    public static class GuardianEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createGuardianAttributes() {
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 7.0D : 3.0D;
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 24.0D : 8.0;
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 35.0D : 15.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange).add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth);
        }
    }

    @Mixin(net.minecraft.entity.mob.HoglinEntity.class)
    public static class HoglinEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createHoglinAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 60.0D : 25.0D;
            final double genericKnockbackResistance = SpeedrunnerMod.options().main.doomMode ? 0.7000000238518589D : 0.6000000238418579D;
            final double genericAttackKnockback = SpeedrunnerMod.options().main.doomMode ? 1.2D : 0.5D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, genericKnockbackResistance).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }
    }

    @Mixin(net.minecraft.entity.mob.MagmaCubeEntity.class)
    public static class MagmaCubeEntityMixin extends SlimeEntity {

        public MagmaCubeEntityMixin(EntityType<? extends SlimeEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public int getTicksUntilNextJump() {
            return SpeedrunnerMod.options().main.doomMode ? 20 : 100;
        }

        @Overwrite
        public float getDamageAmount() {
            float f = SpeedrunnerMod.options().main.doomMode ? 2.2F : 1.5F;
            return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * f;
        }
    }

    @Mixin(net.minecraft.entity.mob.PiglinBrain.class)
    public static class PiglinBrainMixin {

        @Overwrite
        public static boolean getNearestZombifiedPiglin(PiglinEntity piglin) {
            Brain<PiglinEntity> brain = piglin.getBrain();
            if (brain.hasMemoryModule(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED)) {
                LivingEntity livingEntity = (LivingEntity)brain.getOptionalMemory(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED).get();
                return piglin.isInRange(livingEntity, 2.0D);
            } else {
                return false;
            }
        }
    }

    @Mixin(net.minecraft.entity.mob.PiglinEntity.class)
    public static class PiglinEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createPiglinAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 24.0D : 16.0D;
            final double genericAttackDamage =  SpeedrunnerMod.options().main.doomMode ? 6.0D : 2.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }
    }

    @Mixin(net.minecraft.entity.mob.PiglinBruteEntity.class)
    public static class PiglinBruteEntityMixin {

        @ModifyArg(method = "createPiglinBruteAttributes", at = @At(value = "INVOKE", target = DEFAULT_ATTRIBUTE_CONTAINER, ordinal = 0), index = 1)
        private static double genericMaxHealth(double baseValue) {
            return SpeedrunnerMod.options().main.doomMode ? 25.0D : 50.0D;
        }
    }

    @Mixin(net.minecraft.entity.mob.PillagerEntity.class)
    public static class PillagerEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createPillagerAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 32.0D : 12.0D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0;
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 32.0D : 16.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
        }
    }

    @Mixin(net.minecraft.entity.mob.RavagerEntity.class)
    public static class RavagerEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createRavagerAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 100.0D : 50.0D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 16.0D : 10.0D;
            final double genericAttackKnockback = SpeedrunnerMod.options().main.doomMode ? 1.6D : 1.1D;
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 48.0D : 32.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.75D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
        }

        @Inject(method = "tryAttack", at = @At("RETURN"))
        private void tryAttack(Entity target, CallbackInfoReturnable cir) {
            if (SpeedrunnerMod.options().main.doomMode && target instanceof PlayerEntity) {
                ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
            }
        }
    }

    @Mixin(net.minecraft.entity.mob.ShulkerEntity.class)
    public abstract static class ShulkerEntityMixin extends GolemEntity {
        @Shadow @Final
        private static UUID COVERED_ARMOR_BONUS_ID;
        @Shadow
        private static final EntityAttributeModifier COVERED_ARMOR_BONUS = new EntityAttributeModifier(COVERED_ARMOR_BONUS_ID, "Covered armor bonus", 10.0D, EntityAttributeModifier.Operation.ADDITION);
        @Shadow
        abstract boolean isClosed();
        @Shadow
        abstract void spawnNewShulker();

        public ShulkerEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
            super(entityType, world);
        }

        @ModifyArg(method = "createShulkerAttributes", at = @At(value = "INVOKE", target = DEFAULT_ATTRIBUTE_CONTAINER), index = 1)
        private static double genericMaxHealth(double baseValue) {
            return SpeedrunnerMod.options().main.doomMode ? 32.0D : 20.0D;
        }

        @Overwrite
        public boolean damage(DamageSource source, float amount) {
            Entity entity2;
            if (this.isClosed()) {
                entity2 = source.getSource();
                if (SpeedrunnerMod.options().main.doomMode) {
                    if (entity2 instanceof PersistentProjectileEntity) {
                        return false;
                    }
                } else {
                    if (entity2 instanceof PersistentProjectileEntity && this.random.nextFloat() < 0.25F) {
                        return false;
                    }
                }
            }

            if (!super.damage(source, amount)) {
                return false;
            } else {
                if (source.isProjectile()) {
                    entity2 = source.getSource();
                    if (entity2 != null && entity2.getType() == EntityType.SHULKER_BULLET) {
                        this.spawnNewShulker();
                    }
                }

                return true;
            }
        }
    }

    @Mixin(net.minecraft.entity.mob.SilverfishEntity.class)
    public static class SilverfishEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createSilverfishAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.25D : 0.15D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 2.0D : 0.01D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }

        @Mixin(net.minecraft.entity.mob.SilverfishEntity.CallForHelpGoal.class)
        public static class CallForHelpGoalMixin {
            @Shadow
            int delay;

            @Redirect(method = "onHurt", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/SilverfishEntity$CallForHelpGoal;delay:I", ordinal = 0))
            private int onHurt(SilverfishEntity.CallForHelpGoal callForHelpGoal) {
                return this.delay = SpeedrunnerMod.options().main.doomMode ? 20 : 100;
            }
        }
    }

    @Mixin(net.minecraft.entity.mob.SkeletonEntity.class)
    public abstract static class SkeletonEntityMixin extends AbstractSkeletonEntity {

        public SkeletonEntityMixin(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
            super(entityType, world);
        }

        protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
            PersistentProjectileEntity persistentProjectileEntity = super.createArrowProjectile(arrow, damageModifier);
            if (persistentProjectileEntity instanceof ArrowEntity && SpeedrunnerMod.options().main.doomMode) {
                ((ArrowEntity)persistentProjectileEntity).addEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
            }

            return persistentProjectileEntity;
        }
    }

    @Mixin(net.minecraft.entity.mob.SlimeEntity.class)
    public static class SlimeEntityMixin {

        @Overwrite
        public int getTicksUntilNextJump() {
            return SpeedrunnerMod.options().main.doomMode ? 20 : 100;
        }
    }

    @Mixin(net.minecraft.entity.mob.SpiderEntity.class)
    public static class SpiderEntityMixin extends HostileEntity {

        public SpiderEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        public boolean tryAttack(Entity target) {
            if (!super.tryAttack(target)) {
                return false;
            } else {
                if (target instanceof PlayerEntity) {
                    if (SpeedrunnerMod.options().main.doomMode) {
                        ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
                    }
                }

                return true;
            }
        }
    }

    @Mixin(net.minecraft.entity.mob.VexEntity.class)
    public static class VexEntityMixin extends HostileEntity {
        @Shadow
        boolean alive;
        @Shadow
        int lifeTicks;

        public VexEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createVexAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 7.0D : 14.0D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 3.0D : 4.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }

        @Overwrite
        public void tick() {
            this.noClip = !SpeedrunnerMod.options().main.doomMode;
            super.tick();
            this.noClip = false;
            this.setNoGravity(true);
            if (this.alive && --this.lifeTicks <= 0) {
                this.lifeTicks = 20;
                final float amount = SpeedrunnerMod.options().main.doomMode ? 100.0F : 1.0F;
                this.damage(DamageSource.STARVE, amount);
            }
        }

        public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
            return !SpeedrunnerMod.options().main.doomMode;
        }
    }

    @Mixin(net.minecraft.entity.mob.VindicatorEntity.class)
    public abstract static class VindicatorEntityMixin extends IllagerEntity {

        public VindicatorEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createVindicatorAttributes() {
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 48.0D : 12.0D;
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 20.0D : 24.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange).add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D);
        }

        public boolean tryAttack(Entity target) {
            if (!super.tryAttack(target)) {
                return false;
            } else {
                if (SpeedrunnerMod.options().main.doomMode && target instanceof PlayerEntity) {
                    ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
                }

                return true;
            }
        }
    }

    @Mixin(net.minecraft.entity.mob.WitchEntity.class)
    public static class WitchEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createWitchAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 26.0D : 14.0D;
            final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.35D : 0.25D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed);
        }
    }

    @Mixin(net.minecraft.entity.mob.WitherSkeletonEntity.class)
    public abstract static class WitherSkeletonEntityMixin extends AbstractSkeletonEntity {

        public WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world) {
            super(entityType, world);
        }

        @ModifyArg(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeInstance;setBaseValue(D)V"))
        private double genericAttackDamage(double baseValue) {
            return SpeedrunnerMod.options().main.doomMode ? 10.0D : 1.0D;
        }

        @ModifyArg(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;I)V"), index = 1)
        private int tryAttack(int x) {
            return SpeedrunnerMod.options().main.doomMode ? 200 : 60;
        }

        @Inject(method = "tryAttack", at = @At("RETURN"))
        private void tryAttack(Entity target, CallbackInfoReturnable cir) {
            if (SpeedrunnerMod.options().main.doomMode && target instanceof PlayerEntity) {
                ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
            }
        }
    }

    @Mixin(net.minecraft.entity.mob.ZoglinEntity.class)
    public static class ZoglinEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createZoglinAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 60.0D : 25.0D;
            final double genericKnockbackResistance = SpeedrunnerMod.options().main.doomMode ? 0.7000000238518589D : 0.6000000238418579D;
            final double genericAttackKnockback = SpeedrunnerMod.options().main.doomMode ? 1.2D : 0.5D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, genericKnockbackResistance).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }
    }

    @Mixin(net.minecraft.entity.mob.ZombieEntity.class)
    public static class ZombieEntityMixin extends HostileEntity {

        public ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createZombieAttributes() {
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 50.0D : 25.0D;
            final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.33000000417232513D : 0.23000000417232513D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 7.0D : 2.0D;
            final double genericArmor = SpeedrunnerMod.options().main.doomMode ? 2.0D : 1.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_ARMOR, genericArmor).add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
        }

        public boolean tryAttack(Entity target) {
            if (!super.tryAttack(target)) {
                return false;
            } else {
                if (SpeedrunnerMod.options().main.doomMode && target instanceof PlayerEntity) {
                    ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
                }

                return true;
            }
        }
    }

    @Mixin(net.minecraft.entity.mob.ZombifiedPiglinEntity.class)
    public static class ZombifiedPiglinEntityMixin extends ZombieEntity {

        public ZombifiedPiglinEntityMixin(EntityType<? extends ZombieEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createZombifiedPiglinAttributes() {
            final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.33000000427232513D : 0.23000000427232513D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 7.0D : 2.0D;
            return ZombieEntity.createZombieAttributes().add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS, 0.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }

        public boolean tryAttack(Entity target) {
            if (!super.tryAttack(target)) {
                return false;
            } else {
                if (SpeedrunnerMod.options().main.doomMode && target instanceof PlayerEntity) {
                    ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
                }

                return true;
            }
        }
    }

    @Mixin(net.minecraft.entity.passive.DolphinEntity.class)
    public static class DolphinEntityMixin {
        @Shadow
        private static final TargetPredicate CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(20.0D).ignoreVisibility();

        @Mixin(net.minecraft.entity.passive.DolphinEntity.SwimWithPlayerGoal.class)
        public static class SwimWithPlayerGoalMixin {

            @ModifyArg(method = "start", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;I)V"), index = 1)
            private int start(int x) {
                return 200;
            }
        }
    }

    @Mixin(net.minecraft.entity.passive.IronGolemEntity.class)
    public static class IronGolemEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createIronGolemAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 100.0D : 50.0D;
            final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.3D : 0.25D;
            final double genericKnockbackResistance = SpeedrunnerMod.options().main.doomMode ? 0.7D : 0.5D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 20.0D : 7.0D;
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, genericKnockbackResistance).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }
    }

    @Mixin(net.minecraft.entity.passive.SheepEntity.class)
    public static abstract class SheepEntityMixin extends AnimalEntity {

        public SheepEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
            super(entityType, world);
        }

        @ModifyVariable(method = "sheared", at = @At("STORE"))
        private int sheared(int x) {
            return 6 + this.random.nextInt(4);
        }
    }

    @Mixin(net.minecraft.entity.player.PlayerEntity.class)
    public abstract static class PlayerEntityMixin extends LivingEntity {

        public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
            super(entityType, world);
        }

        @Shadow
        abstract ItemCooldownManager getItemCooldownManager();

        @Inject(method = "takeShieldHit", at = @At("TAIL"))
        private void takeShieldHit(LivingEntity attacker, CallbackInfo ci) {
            if (SpeedrunnerMod.options().main.doomMode) {
                if (attacker instanceof GiantEntity) {
                    this.getItemCooldownManager().set(Items.SHIELD, 200);
                    this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 180);
                    this.clearActiveItem();
                    this.world.sendEntityStatus(this, (byte)30);
                }
            }
        }
    }

    @Mixin(net.minecraft.entity.projectile.thrown.EnderPearlEntity.class)
    public abstract static class EnderPearlEntityMixin extends ThrownItemEntity {

        public EnderPearlEntityMixin(EntityType<? extends EnderPearlEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public void onCollision(HitResult hitResult) {
            super.onCollision(hitResult);
            boolean ifb = EnchantmentHelper.getLevel(Enchantments.INFINITY, super.getItem()) > 0;

            for(int i = 0; i < 32; ++i) {
                this.world.addParticle(ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * 2.0D, this.getZ(), this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
            }

            if (!this.world.isClient && !this.isRemoved()) {
                Entity entity = this.getOwner();
                if (entity instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
                    if (serverPlayerEntity.networkHandler.getConnection().isOpen() && serverPlayerEntity.world == this.world && !serverPlayerEntity.isSleeping()) {
                        if (!ifb && this.random.nextFloat() < 0.05F && this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
                            EndermiteEntity endermiteEntity = (EndermiteEntity)EntityType.ENDERMITE.create(this.world);
                            endermiteEntity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch());
                            this.world.spawnEntity(endermiteEntity);
                        }

                        if (entity.hasVehicle()) {
                            serverPlayerEntity.requestTeleportAndDismount(this.getX(), this.getY(), this.getZ());
                        } else {
                            entity.requestTeleport(this.getX(), this.getY(), this.getZ());
                        }

                        entity.fallDistance = 0.0F;
                        if (!ifb) {
                            if (SpeedrunnerMod.options().main.doomMode) {
                                if (!serverPlayerEntity.isCreative() || !serverPlayerEntity.isSpectator()) {
                                    ((ServerPlayerEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
                                }
                            }
                            final float amount = SpeedrunnerMod.options().main.doomMode ? 5.0F : 2.0F;
                            entity.damage(DamageSource.FALL, amount);
                        }
                    }
                } else if (entity != null) {
                    entity.requestTeleport(this.getX(), this.getY(), this.getZ());
                    entity.fallDistance = 0.0F;
                }

                this.discard();
            }
        }
    }

    @Mixin(net.minecraft.entity.projectile.DragonFireballEntity.class)
    public static class DragonFireballEntityMixin {

        @ModifyArg(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;II)V"), index = 2)
        private int onCollision(int x) {
            return SpeedrunnerMod.options().main.doomMode ? 1 : 0;
        }
    }

    @Mixin(net.minecraft.entity.projectile.SmallFireballEntity.class)
    public static class SmallFireballEntityMixin {

        @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
        private int onEntityHit(int x) {
            return SpeedrunnerMod.options().main.doomMode ? 6 : 3;
        }

        @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 1)
        private float onEntityHit(float x) {
            return SpeedrunnerMod.options().main.doomMode ? 5.0F : 3.0F;
        }
    }

    @Mixin(net.minecraft.entity.Entity.class)
    public static class EntityMixin {

        @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
        private int setOnFireFromLava(int x) {
            return SpeedrunnerMod.options().main.doomMode ? 15 : 7;
        }

        @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
        private float setOnFireFromLava(float x) {
            return SpeedrunnerMod.options().main.doomMode ? 4.0F : 2.0F;
        }
    }

    @Mixin(net.minecraft.entity.LivingEntity.class)
    public abstract static class LivingEntityMixin extends Entity {

        public LivingEntityMixin(EntityType<?> type, World world) {
            super(type, world);
        }

        @Overwrite
        public int getNextAirOnLand(int air) {
            return Math.min(air + 8, this.getMaxAir());
        }
    }

    @Mixin(net.minecraft.entity.EyeOfEnderEntity.class)
    public abstract static class EyeOfEnderEntityMixin extends Entity implements FlyingItemEntity {
        @Shadow
        private double targetX, targetY, targetZ;
        @Shadow
        private int lifespan;

        public EyeOfEnderEntityMixin(EntityType<? extends EyeOfEnderEntity> type, World world) {
            super(type, world);
        }

        @Overwrite
        public void tick() {
            super.tick();
            Vec3d vec3d = this.getVelocity();
            Explosion.DestructionType destructionType = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
            double d = this.getX() + vec3d.x;
            double e = this.getY() + vec3d.y;
            double f = this.getZ() + vec3d.z;
            double g = vec3d.horizontalLength();
            this.setPitch(ProjectileEntity.updateRotation(this.prevPitch, (float)(MathHelper.atan2(vec3d.y, g) * 57.2957763671875D)));
            this.setYaw(ProjectileEntity.updateRotation(this.prevYaw, (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D)));
            if (!this.world.isClient) {
                double h = this.targetX - d;
                double i = this.targetZ - f;
                float j = (float)Math.sqrt(h * h + i * i);
                float k = (float)MathHelper.atan2(i, h);
                double l = MathHelper.lerp(0.0025D, g, (double)j);
                double m = vec3d.y;
                if (j < 1.0F) {
                    l *= 0.8D;
                    m *= 0.8D;
                }

                int n = this.getY() < this.targetY ? 1 : -1;
                vec3d = new Vec3d(Math.cos((double)k) * l, m + ((double)n - m) * 0.014999999664723873D, Math.sin((double)k) * l);
                this.setVelocity(vec3d);
            }

            float o = 0.25F;
            if (this.isTouchingWater()) {
                for(int p = 0; p < 4; ++p) {
                    this.world.addParticle(ParticleTypes.BUBBLE, d - vec3d.x * 0.25D, e - vec3d.y * 0.25D, f - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
                }
            } else if (this.getStack().getItem() == Items.ENDER_EYE && !this.isTouchingWater() || this.getStack().getItem() == ModItems.ANNUL_EYE && !this.isTouchingWater()) {
                this.world.addParticle(ParticleTypes.PORTAL, d - vec3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, e - vec3d.y * 0.25D - 0.5D, f - vec3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, vec3d.x, vec3d.y, vec3d.z);
            } else if (this.getStack().getItem() == ModItems.INFERNO_EYE && !this.isTouchingWater()) {
                this.world.addParticle(ParticleTypes.SMOKE, this.getParticleX(0.5D), this.getRandomBodyY(), this.getParticleZ(0.5D), 0.0D, 0.0D, 0.0D);
            }

            if (!this.world.isClient) {
                this.setPosition(d, e, f);
                ++this.lifespan;
                if (this.lifespan > 40 && !this.world.isClient) {
                    this.discard();
                    this.world.spawnEntity(new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), this.getStack()));
                    if (SpeedrunnerMod.options().main.doomMode) {
                        this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 3.0F, destructionType);
                    } else if (this.getStack().getItem() == Items.ENDER_EYE || this.getStack().getItem() == ModItems.ANNUL_EYE) {
                        this.playSound(SoundEvents.ENTITY_ENDER_EYE_DEATH, 1.0F, 1.0F);
                    } else if (this.getStack().getItem() == ModItems.INFERNO_EYE) {
                        this.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1.0F, 1.0F);
                    }
                }
            } else {
                this.setPos(d, e, f);
            }
        }
    }

    @Mixin(net.minecraft.entity.ItemEntity.class)
    public static class ItemEntityMixin {

        @Inject(method = "isFireImmune", at = @At("RETURN"))
        public boolean isFireImmune(CallbackInfoReturnable cir) {
            ItemEntity item = (ItemEntity)(Object)this;
            ItemStack stack = item.getStack();

            if (stack.isOf(Items.BLAZE_ROD) || stack.isOf(Items.BLAZE_POWDER)) {
                return true;
            }

            return cir.getReturnValueZ();
        }
    }

    @Mixin(net.minecraft.entity.SpawnRestriction.class)
    public static class SpawnRestrictionMixin {

        static {
            if (SpeedrunnerMod.options().main.doomMode) {
                SpawnRestriction.register(EntityType.PIGLIN_BRUTE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpeedrunnerMod::canPiglinBruteSpawn);
            }
        }
    }

    @Mixin(net.minecraft.item.EnderPearlItem.class)
    public static class EnderPearlItemMixin extends Item {

        public EnderPearlItemMixin(Settings settings) {
            super(settings);
        }

        @Overwrite
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            ItemStack itemStack = user.getStackInHand(hand);
            boolean bl = EnchantmentHelper.getLevel(Enchantments.INFINITY, itemStack) > 0;
            world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.random.nextFloat() * 0.4F + 0.8F));
            user.getItemCooldownManager().set(this, 20);
            if (!world.isClient) {
                EnderPearlEntity enderPearlEntity = new EnderPearlEntity(world, user);
                enderPearlEntity.setItem(itemStack);
                enderPearlEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(enderPearlEntity);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.getAbilities().creativeMode && !bl) {
                itemStack.decrement(1);
            }

            return TypedActionResult.success(itemStack, world.isClient());
        }
    }

    @Mixin(net.minecraft.item.FoodComponents.class)
    public static class FoodComponentsMixin {
        @Shadow
        private static final FoodComponent APPLE = ModFoodComponents.APPLE, BAKED_POTATO = ModFoodComponents.BAKED_POTATO, BEEF = ModFoodComponents.BEEF, BEETROOT = ModFoodComponents.BEETROOT, BREAD = ModFoodComponents.BREAD, CARROT = ModFoodComponents.CARROT, CHICKEN = ModFoodComponents.CHICKEN, CHORUS_FRUIT = ModFoodComponents.CHORUS_FRUIT, COD = ModFoodComponents.COD, COOKED_BEEF = ModFoodComponents.COOKED_BEEF, COOKED_CHICKEN = ModFoodComponents.COOKED_CHICKEN, COOKED_COD = ModFoodComponents.COOKED_COD, COOKED_MUTTON = ModFoodComponents.COOKED_MUTTON, COOKED_PORKCHOP = ModFoodComponents.COOKED_PORKCHOP, COOKED_RABBIT = ModFoodComponents.COOKED_RABBIT, COOKED_SALMON = ModFoodComponents.COOKED_SALMON, COOKIE = ModFoodComponents.COOKIE, DRIED_KELP = ModFoodComponents.DRIED_KELP, ENCHANTED_GOLDEN_APPLE = ModFoodComponents.ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE = ModFoodComponents.GOLDEN_APPLE, GOLDEN_CARROT = ModFoodComponents.GOLDEN_CARROT, HONEY_BOTTLE = ModFoodComponents.HONEY_BOTTLE, MELON_SLICE = ModFoodComponents.MELON_SLICE, MUTTON = ModFoodComponents.MUTTON, POISONOUS_POTATO = ModFoodComponents.POISONOUS_POTATO, PORKCHOP = ModFoodComponents.PORKCHOP, POTATO = ModFoodComponents.POTATO, PUFFERFISH = ModFoodComponents.PUFFERFISH, PUMPKIN_PIE = ModFoodComponents.PUMPKIN_PIE, RABBIT = ModFoodComponents.RABBIT, ROTTEN_FLESH = ModFoodComponents.ROTTEN_FLESH, SALMON = ModFoodComponents.SALMON, SPIDER_EYE = ModFoodComponents.SPIDER_EYE, SWEET_BERRIES = ModFoodComponents.SWEET_BERRIES, GLOW_BERRIES = ModFoodComponents.GLOW_BERRIES, TROPICAL_FISH = ModFoodComponents.TROPICAL_FISH;
    }

    @Mixin(net.minecraft.server.network.ServerPlayerEntity.class)
    public abstract static class ServerPlayerEntityMixin extends PlayerEntity {
        @Shadow @Final
        private ServerStatHandler statHandler;

        public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
            super(world, pos, yaw, profile);
        }

        @Inject(method = "<init>", at = @At("TAIL"))
        private void init(CallbackInfo ci) throws CommandSyntaxException {
            if (this.statHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_TIME)) == 0) {
                ItemStack item;
                if (SpeedrunnerMod.options().main.iCarusMode) {
                    item = this.itemStackFromString("minecraft:elytra{Unbreakable:1b}", 1);
                    ItemStack item2 = this.itemStackFromString("minecraft:firework_rocket{Fireworks:{Flight:3b}}", 64);
                    this.getInventory().armor.set(2, item);
                    this.getInventory().main.set(0, item2);
                }

                if (SpeedrunnerMod.options().main.infiniPearlMode) {
                    item = new ItemStack(Items.ENDER_PEARL, 1);
                    item.addEnchantment(Enchantments.INFINITY, 1);
                    item.getOrCreateNbt().putInt("HideFlags", 1);

                    LiteralText text = new LiteralText("InfiniPearl™");
                    text.setStyle(text.getStyle().withItalic(false));
                    item.setCustomName(text);

                    if (!SpeedrunnerMod.options().main.iCarusMode) {
                        this.getInventory().main.set(0, item);
                    } else {
                        this.getInventory().main.set(1, item);
                    }
                }
            }
        }

        private ItemStack itemStackFromString(String string, int count) throws CommandSyntaxException {
            return new ItemStackArgumentType().parse(new StringReader(string)).createStack(count, false);
        }
    }

    @Mixin(net.minecraft.world.biome.source.util.VanillaBiomeParameters.class)
    public static class VanillaBiomeParametersMixin {
        @Shadow @Final @Mutable
        private RegistryKey<Biome>[][] COMMON_BIOMES;

        @Inject(method = "<init>", at = @At("TAIL"))
        private void init(CallbackInfo ci) {
            this.COMMON_BIOMES = SpeedrunnerMod.COMMON_BIOMES;
        }
    }

    @Mixin(net.minecraft.world.gen.feature.ConfiguredStructureFeatures.class)
    public static class ConfiguredStructureFeaturesMixin {
        @Shadow @Final
        private static ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> VILLAGE_PLAINS;
        @Shadow @Final
        private static ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> VILLAGE_TAIGA;

        @Inject(method = "registerAll", at = @At("TAIL"))
        private static void registerAll(BiConsumer<ConfiguredStructureFeature<?, ?>, RegistryKey<Biome>> registrar, CallbackInfo ci) {
            if (SpeedrunnerMod.options().advanced.modifyBiomes) {
                ConfiguredStructureFeatures.register(registrar, VILLAGE_PLAINS, BiomeKeys.SUNFLOWER_PLAINS);
                ConfiguredStructureFeatures.register(registrar, VILLAGE_PLAINS, BiomeKeys.FOREST);
                ConfiguredStructureFeatures.register(registrar, VILLAGE_PLAINS, BiomeKeys.FLOWER_FOREST);
                ConfiguredStructureFeatures.register(registrar, VILLAGE_TAIGA, BiomeKeys.WINDSWEPT_HILLS);
            }
        }
    }

    @Mixin(net.minecraft.world.biome.TheNetherBiomeCreator.class)
    public static class TheNetherBiomeCreatorMixin {

        @Overwrite
        public static Biome createNetherWastes() {
            return SpeedrunnerMod.options().advanced.modifyBiomes ? SpeedrunnerMod.modifyNetherWastes() : TheNetherBiomeCreator.createNetherWastes();
        }

        @Overwrite
        public static Biome createSoulSandValley() {
            return SpeedrunnerMod.options().advanced.modifyBiomes ? SpeedrunnerMod.modifySoulSandValley() : TheNetherBiomeCreator.createSoulSandValley();
        }

        @Overwrite
        public static Biome createBasaltDeltas() {
            return SpeedrunnerMod.options().advanced.modifyBiomes ? SpeedrunnerMod.modifyBasaltDeltas() : SpeedrunnerMod.addDeltasOres();
        }

        @Overwrite
        public static Biome createCrimsonForest() {
            return SpeedrunnerMod.options().advanced.modifyBiomes ? SpeedrunnerMod.modifyCrimsonForest() : TheNetherBiomeCreator.createCrimsonForest();
        }

        @Overwrite
        public static Biome createWarpedForest() {
            return SpeedrunnerMod.options().advanced.modifyBiomes ? SpeedrunnerMod.modifyWarpedForest() : TheNetherBiomeCreator.createWarpedForest();
        }
    }

    @Mixin(net.minecraft.world.dimension.AreaHelper.class)
    public static class AreaHelperMixin {
        @Shadow
        private static final AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK = (state, world, pos) -> {
            return state.isOf(Blocks.OBSIDIAN) || state.isOf(Blocks.CRYING_OBSIDIAN);
        };
    }

    @Mixin(net.minecraft.world.gen.chunk.StrongholdConfig.class)
    public static class StrongholdConfigMixin {
        @Shadow @Final
        private int distance;
        @Shadow @Final
        private int count;

        @Overwrite
        public int getDistance() {
            return SpeedrunnerMod.options().main.commonStructures ? SpeedrunnerMod.options().advanced.getStrongholdDistance() : this.distance;
        }

        @Overwrite
        public int getCount() {
            return SpeedrunnerMod.options().main.commonStructures ? SpeedrunnerMod.options().main.getStrongholdCount() : this.count;
        }
    }

    @Mixin(net.minecraft.world.gen.feature.DefaultBiomeFeatures.class)
    public static class DefaultBiomeFeaturesMixin {

        @Inject(method = "addDefaultOres", at = @At("TAIL"))
        private static void addDefaultOres(GenerationSettings.Builder builder, CallbackInfo ci) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SPEEDRUNNER_UPPER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SPEEDRUNNER_MIDDLE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SPEEDRUNNER_SMALL);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_IGNEOUS);
        }

        @Inject(method = "addNetherMineables", at = @At("TAIL"))
        private static void addNetherMineables(GenerationSettings.Builder builder, CallbackInfo ci) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModOrePlacedFeatures.ORE_SPEEDRUNNER_NETHER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModOrePlacedFeatures.ORE_IGNEOUS_NETHER);
        }

        @Overwrite
        public static void addMonsters(net.minecraft.world.biome.SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, boolean drowned) {
            SpeedrunnerMod.modifyMonsters(builder, zombieWeight, zombieVillagerWeight, skeletonWeight, drowned);
        }

        @Overwrite
        public static void addFarmAnimals(net.minecraft.world.biome.SpawnSettings.Builder builder) {
            SpeedrunnerMod.makeAnimalsMoreCommon(builder);
        }

        @Overwrite
        public static void addWarmOceanMobs(net.minecraft.world.biome.SpawnSettings.Builder builder, int squidWeight, int squidMinGroupSize) {
            SpeedrunnerMod.makeDolphinsMoreCommon(builder, squidWeight, squidMinGroupSize);
        }

        @Overwrite
        public static void addEndMobs(net.minecraft.world.biome.SpawnSettings.Builder builder) {
            SpeedrunnerMod.modifyEndMobs(builder);
        }
    }

    @Mixin(net.minecraft.world.gen.feature.NetherFortressFeature.class)
    public static class NetherFortressFeatureMixin {
        @Shadow
        private static final Pool<SpawnSettings.SpawnEntry> MONSTER_SPAWNS = SpeedrunnerMod.NETHER_FORTRESS_MOB_SPAWNS;
    }

    @Mixin(net.minecraft.world.gen.feature.StrongholdFeature.class)
    public abstract static class StrongholdFeatureMixin extends MarginedStructureFeature<DefaultFeatureConfig> {

        public StrongholdFeatureMixin(Codec<DefaultFeatureConfig> codec, StructureGeneratorFactory<DefaultFeatureConfig> structureGeneratorFactory) {
            super(codec, structureGeneratorFactory);
        }

        @Overwrite
        public static void addPieces(StructurePiecesCollector collector, net.minecraft.structure.StructurePiecesGenerator.Context<DefaultFeatureConfig> context) {
            int var2 = 0;

            StrongholdGenerator.Start start;
            do {
                collector.clear();
                context.random().setCarverSeed(context.seed() + (long)(var2++), context.chunkPos().x, context.chunkPos().z);
                StrongholdGenerator.init();
                start = new StrongholdGenerator.Start(context.random(), context.chunkPos().getOffsetX(2), context.chunkPos().getOffsetZ(2));
                collector.addPiece(start);
                start.fillOpenings(start, collector, context.random());
                List list = start.pieces;

                while(!list.isEmpty()) {
                    int j = context.random().nextInt(list.size());
                    StructurePiece structurePiece = (StructurePiece)list.remove(j);
                    structurePiece.fillOpenings(start, collector, context.random());
                }

                final int topY = SpeedrunnerMod.options().main.doomMode ? 0 : 36;
                final int bottomY = SpeedrunnerMod.options().main.doomMode ? -48 : 25;
                collector.shiftInto(topY, bottomY, context.random(), 10);
            } while(collector.isEmpty() || start.portalRoom == null);

        }
    }

    @Mixin(net.minecraft.structure.NetherFortressGenerator.class)
    public static class NetherFortressGeneratorMixin {
        @Shadow
        private static final NetherFortressGenerator.PieceData[] ALL_BRIDGE_PIECES = SpeedrunnerMod.NETHER_FORTRESS_GENERATION_BRIDGE;
        @Shadow
        private static final NetherFortressGenerator.PieceData[] ALL_CORRIDOR_PIECES = SpeedrunnerMod.NETHER_FORTRESS_GENERATION_CORRIDOR;
    }

    @Mixin(net.minecraft.structure.StrongholdGenerator.class)
    public static class StrongholdGeneratorMixin {
        @Shadow
        private static final StrongholdGenerator.PieceData[] ALL_PIECES = SpeedrunnerMod.STRONGHOLD_GENERATION;

        @Mixin(StrongholdGenerator.PortalRoom.class)
        public abstract static class PortalRoomMixin extends StrongholdGenerator.Piece {
            @Shadow
            private boolean spawnerPlaced;

            public PortalRoomMixin(StructurePieceType structurePieceType, int i, BlockBox blockBox) {
                super(structurePieceType, i, blockBox);
            }

            @Overwrite
            public static StrongholdGenerator.PortalRoom create(StructurePiecesHolder holder, int x, int y, int z, Direction orientation, int chainLength) {
                BlockBox blockBox = BlockBox.rotated(x, y, z, -4, -1, 0, 11, 8, 16, orientation);
                return isInBounds(blockBox) && holder.getIntersecting(blockBox) == null ? new StrongholdGenerator.PortalRoom(chainLength, blockBox, orientation) : null;
            }

            public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pos) {
                this.fillWithOutline(world, chunkBox, 0, 0, 0, 10, 7, 15, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.generateEntrance(world, random, chunkBox, StrongholdGenerator.Piece.EntranceType.GRATES, 4, 1, 0);
                int i = 6;
                this.fillWithOutline(world, chunkBox, 1, i, 1, 1, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, chunkBox, 9, i, 1, 9, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, chunkBox, 2, i, 1, 8, i, 2, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, chunkBox, 2, i, 14, 8, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, chunkBox, 1, 1, 1, 2, 1, 4, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, chunkBox, 8, 1, 1, 9, 1, 4, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, chunkBox, 1, 1, 1, 1, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
                this.fillWithOutline(world, chunkBox, 9, 1, 1, 9, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
                this.fillWithOutline(world, chunkBox, 3, 1, 8, 7, 1, 12, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, chunkBox, 4, 1, 9, 6, 1, 11, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
                BlockState blockState = (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true);
                BlockState blockState2 = (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true)).with(PaneBlock.EAST, true);

                int j;
                for(j = 3; j < 14; j += 2) {
                    this.fillWithOutline(world, chunkBox, 0, 3, j, 0, 4, j, blockState, blockState, false);
                    this.fillWithOutline(world, chunkBox, 10, 3, j, 10, 4, j, blockState, blockState, false);
                }

                for(j = 2; j < 9; j += 2) {
                    this.fillWithOutline(world, chunkBox, j, 3, 15, j, 4, 15, blockState2, blockState2, false);
                }

                BlockState j1 = (BlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
                this.fillWithOutline(world, chunkBox, 4, 1, 5, 6, 1, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, chunkBox, 4, 2, 6, 6, 2, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, chunkBox, 4, 3, 7, 6, 3, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);

                for(int k = 4; k <= 6; ++k) {
                    this.addBlock(world, j1, k, 1, 4, chunkBox);
                    this.addBlock(world, j1, k, 2, 5, chunkBox);
                    this.addBlock(world, j1, k, 3, 6, chunkBox);
                }

                BlockState k = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
                BlockState blockState3 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
                BlockState blockState4 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
                BlockState blockState5 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);
                boolean bl = true;
                boolean[] bls = new boolean[12];

                for(int l = 0; l < bls.length; ++l) {
                    final float chance = SpeedrunnerMod.options().main.doomMode ? 0.99F : 0.7F;
                    bls[l] = random.nextFloat() > chance;
                    bl &= bls[l];
                }

                this.addBlock(world, (BlockState)k.with(EndPortalFrameBlock.EYE, bls[0]), 4, 3, 8, chunkBox);
                this.addBlock(world, (BlockState)k.with(EndPortalFrameBlock.EYE, bls[1]), 5, 3, 8, chunkBox);
                this.addBlock(world, (BlockState)k.with(EndPortalFrameBlock.EYE, bls[2]), 6, 3, 8, chunkBox);
                this.addBlock(world, (BlockState)blockState3.with(EndPortalFrameBlock.EYE, bls[3]), 4, 3, 12, chunkBox);
                this.addBlock(world, (BlockState)blockState3.with(EndPortalFrameBlock.EYE, bls[4]), 5, 3, 12, chunkBox);
                this.addBlock(world, (BlockState)blockState3.with(EndPortalFrameBlock.EYE, bls[5]), 6, 3, 12, chunkBox);
                this.addBlock(world, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[6]), 3, 3, 9, chunkBox);
                this.addBlock(world, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[7]), 3, 3, 10, chunkBox);
                this.addBlock(world, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[8]), 3, 3, 11, chunkBox);
                this.addBlock(world, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[9]), 7, 3, 9, chunkBox);
                this.addBlock(world, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[10]), 7, 3, 10, chunkBox);
                this.addBlock(world, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[11]), 7, 3, 11, chunkBox);
                if (bl) {
                    BlockState l = Blocks.END_PORTAL.getDefaultState();
                    this.addBlock(world, l, 4, 3, 9, chunkBox);
                    this.addBlock(world, l, 5, 3, 9, chunkBox);
                    this.addBlock(world, l, 6, 3, 9, chunkBox);
                    this.addBlock(world, l, 4, 3, 10, chunkBox);
                    this.addBlock(world, l, 5, 3, 10, chunkBox);
                    this.addBlock(world, l, 6, 3, 10, chunkBox);
                    this.addBlock(world, l, 4, 3, 11, chunkBox);
                    this.addBlock(world, l, 5, 3, 11, chunkBox);
                    this.addBlock(world, l, 6, 3, 11, chunkBox);
                }

                if (!this.spawnerPlaced) {
                    BlockPos l = this.offsetPos(5, 3, 6);
                    if (chunkBox.contains(l)) {
                        this.spawnerPlaced = true;
                        world.setBlockState(l, Blocks.SPAWNER.getDefaultState(), 2);
                        BlockEntity blockEntity = world.getBlockEntity(l);
                        if (blockEntity instanceof MobSpawnerBlockEntity) {
                            ((MobSpawnerBlockEntity)blockEntity).getLogic().setEntityId(EntityType.SILVERFISH);
                        }
                    }
                }

            }
        }
    }

    @Mixin(value = net.minecraft.world.MobSpawnerLogic.class, priority = 999)
    public abstract static class MobSpawnerLogicMixin {

        @Shadow
        private int spawnDelay;

        @Shadow
        private DataPool<MobSpawnerEntry> spawnPotentials;

        @Shadow @Final
        private Random random;

        @Shadow
        abstract void setSpawnEntry(@Nullable World world, BlockPos pos, MobSpawnerEntry spawnEntry);

        @Shadow
        abstract void sendStatus(World world, BlockPos pos, int i);

        int minSpawnDelayMixin = 200;
        int maxSpawnDelayMixin = 400;

        @Overwrite
        private void updateSpawns(World world, BlockPos pos) {
            if (this.maxSpawnDelayMixin <= this.minSpawnDelayMixin) {
                this.spawnDelay = this.minSpawnDelayMixin;
            } else {
                this.spawnDelay = this.minSpawnDelayMixin + this.random.nextInt(this.maxSpawnDelayMixin - this.minSpawnDelayMixin);
            }

            this.spawnPotentials.getOrEmpty(this.random).ifPresent((present) -> {
                this.setSpawnEntry(world, pos, (MobSpawnerEntry)present.getData());
            });
            this.sendStatus(world, pos, 1);
        }
    }
}