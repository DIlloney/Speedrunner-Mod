package net.dilloney.speedrunnermod.mod;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.block.ModBlocks;
import net.dilloney.speedrunnermod.item.ModFoodComponents;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.util.entity.Giant;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
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
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.util.collection.WeightedPicker;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeCreator;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.layer.SetBaseBiomesLayer;
import net.minecraft.world.dimension.AreaHelper;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StrongholdConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class contains everything that needs to be changed in the game by using {@link Mixin}s.
 * <p> I realized that this mod had so many mixins and it was hard to keep track of all of them, especially on github. So, I have combined
 * all of the mixins into one class file for easier use and create less cluster. I would recommend that you do not touch or modify this class
 * in any way, shape or form. </p>
 * <p> This class contains all of the {@link Mixin}s for block hardness values, modified entity properties, modified world generation,
 * modded ore generation, modified item properties, utilities and more. </p>
 * <p> For easier access to all of the mixins, I have provided a list of all the mixins below (CTRL + left click will instantly take you to the mixin). </p>
 * <h2> "block" package </h2>
 * <p> {@linkplain AbstractBlockStateMixin}, {@linkplain BlockMixin}, {@linkplain OreBlockMixin}. </p>
 * <h2> "entity" package </h2>
 * <p> {@linkplain EnderDragonEntityMixin}, {@linkplain WitherEntityMixin}, {@linkplain AbstractSkeletonEntityMixin}, {@linkplain BlazeEntityMixin}, {@linkplain CreeperEntityMixin},
 * {@linkplain ElderGuardianEntityMixin}, {@linkplain EndermanEntityMixin}, {@linkplain EndermiteEntityMixin}, {@linkplain GhastEntityMixin}, {@linkplain GiantEntityMixin} {@linkplain GuardianEntityMixin},
 * {@linkplain HoglinEntityMixin}, {@linkplain MagmaCubeEntityMixin}, {@linkplain PiglinBrainMixin}, {@linkplain PiglinEntityMixin}, {@linkplain PillagerEntityMixin},
 * {@linkplain RavagerEntityMixin}, {@linkplain ShulkerEntityMixin}, {@linkplain SilverfishEntityMixin}, {@linkplain SlimeEntityMixin} {@linkplain SpiderEntityMixin}, {@linkplain VindicatorEntityMixin},
 * {@linkplain WitchEntityMixin}, {@linkplain WitherSkeletonEntityMixin}, {@linkplain ZoglinEntityMixin}, {@linkplain ZombieEntityMixin}, {@linkplain ZombifiedPiglinEntityMixin},
 * {@linkplain DolphinEntityMixin}, {@linkplain IronGolemEntityMixin}, {@linkplain SheepEntityMixin}, {@linkplain PlayerEntityMixin},
 * {@linkplain EnderPearlEntityMixin}, {@linkplain SmallFireballEntityMixin}, {@linkplain EntityMixin}, {@linkplain EyeOfEnderEntityMixin}, {@linkplain ItemEntityMixin}. </p>
 * <h2> "item" package </h2>
 * <p> {@linkplain EnderPearlItemMixin}, {@linkplain FoodComponentsMixin} </p>
 * <h2> "server" package </h2>
 * <p> {@linkplain ServerPlayerEntityMixin} </p>
 * <h2> "village" package </h2>
 * <p> {@linkplain EnchantBookFactoryMixin} </p>
 * <h2> "world" package </h2>
 * <p> {@linkplain SetBaseBiomesLayerMixin}, {@linkplain DefaultBiomeCreatorMixin}, {@linkplain AreaHelperMixin}, {@linkplain StrongholdConfigMixin}, {@linkplain StructuresConfigMixin}, {@linkplain DefaultBiomeFeaturesMixin}, {@linkplain NetherFortressFeatureMixin},
 * {@linkplain StrongholdFeatureStartMixin}, {@linkplain NetherFortressGeneratorMixin}, {@linkplain StrongholdGeneratorMixin}. </p>
 * Hope that helps! Enjoy speedrunning!
 * <p> 2,148 lines of code! </p>
 */
public class ModFeatures {

    @Mixin(AbstractBlock.AbstractBlockState.class)
    public static class AbstractBlockStateMixin {

        @Inject(method = "getHardness", at = @At("HEAD"), cancellable = true)
        private void getHardness(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
            SpeedrunnerMod.applyBlockHardnessValues(world, pos, cir);
        }
    }

    @Mixin(Block.class)
    public static class BlockMixin {

        @Overwrite
        public void onLandedUpon(World world, BlockPos pos, Entity entity, float distance) {
            SpeedrunnerMod.applyFallDamage(world, pos, entity, distance);
        }
    }

    @Mixin(OreBlock.class)
    public static class OreBlockMixin extends Block {

        public OreBlockMixin(Settings settings) {
            super(settings);
        }

        @Overwrite
        public int getExperienceWhenMined(Random random) {
            if (this == Blocks.COAL_ORE) {
                return MathHelper.nextInt(random, 0, 2);
            } else if (this == Blocks.DIAMOND_ORE) {
                return MathHelper.nextInt(random, 3, 7);
            } else if (this == Blocks.EMERALD_ORE) {
                return MathHelper.nextInt(random, 3, 7);
            } else if (this == Blocks.LAPIS_ORE) {
                return MathHelper.nextInt(random, 2, 5);
            } else if (this == Blocks.IRON_ORE) {
                return MathHelper.nextInt(random, 1, 2);
            } else if (this == Blocks.GOLD_ORE) {
                return MathHelper.nextInt(random, 2, 6);
            } else if (this == Blocks.NETHER_QUARTZ_ORE) {
                return MathHelper.nextInt(random, 2, 5);
            } else if (this == ModBlocks.SPEEDRUNNER_ORE) {
                return MathHelper.nextInt(random, 1, 2);
            } else if (this == ModBlocks.IGNEOUS_ORE) {
                return MathHelper.nextInt(random, 2, 6);
            } else {
                return this == Blocks.NETHER_GOLD_ORE || this == ModBlocks.NETHER_SPEEDRUNNER_ORE || this == ModBlocks.NETHER_IGNEOUS_ORE ? MathHelper.nextInt(random, 0, 1) : 0;
            }
        }
    }

    @Mixin(value = EnderDragonEntity.class, priority = 999)
    public abstract static class EnderDragonEntityMixin extends MobEntity {

        public EnderDragonEntityMixin(EntityType<? extends EnderDragonEntity> entityType, World world) {
            super(entityType, world);
        }

        @Shadow
        private EndCrystalEntity connectedCrystal;

        @Shadow
        private int field_7029;

        @Shadow
        abstract boolean parentDamage(DamageSource source, float amount);

        @Shadow @Final
        private static TargetPredicate CLOSE_PLAYER_PREDICATE;

        @Shadow @Final
        private EnderDragonPart partHead;

        @Shadow @Final
        private PhaseManager phaseManager;

        @Overwrite
        public static DefaultAttributeContainer.Builder createEnderDragonAttributes() {
            return SpeedrunnerMod.applyEnderDragonAttributes();
        }

        @Overwrite
        private void tickWithEndCrystals() {
            if (this.connectedCrystal != null) {
                if (this.connectedCrystal.removed) {
                    this.connectedCrystal = null;
                } else if (this.age % 10 == 0 && this.getHealth() < this.getMaxHealth()) {
                    this.setHealth(this.getHealth() + SpeedrunnerMod.getEndCrystalHealAmount());
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

        @Overwrite
        private void damageLivingEntities(List<Entity> entities) {
            Iterator var2 = entities.iterator();

            while(var2.hasNext()) {
                Entity entity = (Entity)var2.next();
                if (entity instanceof LivingEntity) {
                    entity.damage(DamageSource.mob(this), SpeedrunnerMod.getDragonDamageAmount());
                    this.dealDamage(this, entity);
                }
            }
        }

        @Overwrite
        public boolean damagePart(EnderDragonPart part, DamageSource source, float amount) {
            if (this.phaseManager.getCurrent().getType() == PhaseType.DYING) {
                return false;
            } else {
                amount = this.phaseManager.getCurrent().modifyDamageTaken(source, amount);
                if (part != this.partHead) {
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
                            this.field_7029 = (int)((float)this.field_7029 + (f - this.getHealth()));
                            if ((float)this.field_7029 > SpeedrunnerMod.getDragonDamageDuringSittingAmount() * this.getMaxHealth()) {
                                this.field_7029 = 0;
                                this.phaseManager.setPhase(PhaseType.TAKEOFF);
                            }
                        }
                    }

                    return true;
                }
            }
        }

        @Overwrite
        public void crystalDestroyed(EndCrystalEntity crystal, BlockPos pos, DamageSource source) {
            PlayerEntity playerEntity2;
            if (source.getAttacker() instanceof PlayerEntity) {
                playerEntity2 = (PlayerEntity)source.getAttacker();
            } else {
                playerEntity2 = this.world.getClosestPlayer(CLOSE_PLAYER_PREDICATE, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
            }

            if (crystal == this.connectedCrystal) {
                damagePart(this.partHead, DamageSource.explosion(playerEntity2), SpeedrunnerMod.getEndCrystalDamageAmount());
            }

            this.phaseManager.getCurrent().crystalDestroyed(crystal, pos, source, playerEntity2);
        }
    }

    @Mixin(EnderDragonFight.class)
    public static class EnderDragonFightMixin {

        @Shadow @Final
        private ServerWorld world;

        @Shadow
        private UUID dragonUuid;

        @Overwrite
        private EnderDragonEntity createDragon() {
            world.getWorldChunk(new BlockPos(0, 128, 0));
            EnderDragonEntity enderDragonEntity = (EnderDragonEntity)EntityType.ENDER_DRAGON.create(world);
            enderDragonEntity.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
            enderDragonEntity.refreshPositionAndAngles(0.0D, 128.0D, 0.0D, world.random.nextFloat() * 360.0F, 0.0F);
            world.spawnEntity(enderDragonEntity);
            dragonUuid = enderDragonEntity.getUuid();
            new Timer().schedule(new TimerTask() {
                public void run() {
                    enderDragonEntity.getPhaseManager().setPhase(PhaseType.LANDING);
                    cancel();
                }
            }, SpeedrunnerMod.OPTIONS.getDragonPerchTime() * 1000);
            return enderDragonEntity;
        }
    }

    @Mixin(WitherEntity.class)
    public static class WitherEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createWitherAttributes() {
            return SpeedrunnerMod.applyWitherAttributes();
        }
    }

    @Mixin(AbstractSkeletonEntity.class)
    public static class AbstractSkeletonEntityMixin extends HostileEntity {

        public AbstractSkeletonEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Shadow @Final
        private BowAttackGoal<AbstractSkeletonEntity> bowAttackGoal;

        @Shadow @Final
        private MeleeAttackGoal meleeAttackGoal;

        @Overwrite
        public static DefaultAttributeContainer.Builder createAbstractSkeletonAttributes() {
            return SpeedrunnerMod.applyAbstractSkeletonAttributes();
        }

        @Overwrite
        public void updateAttackType() {
            if (this.world != null && !this.world.isClient) {
                this.goalSelector.remove(this.meleeAttackGoal);
                this.goalSelector.remove(this.bowAttackGoal);
                ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW));
                if (itemStack.getItem() == Items.BOW) {
                    int i = SpeedrunnerMod.getAbstractSkeletonBowAttackSpeed();
                    if (this.world.getDifficulty() != Difficulty.HARD) {
                        i = SpeedrunnerMod.getAbstractSkeletonBowAttackSpeedHard();
                    }

                    this.bowAttackGoal.setAttackInterval(i);
                    this.goalSelector.add(4, this.bowAttackGoal);
                } else {
                    this.goalSelector.add(4, this.meleeAttackGoal);
                }

            }
        }
    }

    @Mixin(BlazeEntity.class)
    public static class BlazeEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createBlazeAttributes() {
            return SpeedrunnerMod.applyBlazeAttributes();
        }

        @Mixin(BlazeEntity.ShootFireballGoal.class)
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

            /**
             * Changes the blazes fireball speed.
             * @author Dilloney
             * @reason
             */
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
                                this.fireballCooldown = SpeedrunnerMod.getBlazeFireballCooldownAmount();
                                this.fireballsFired = 0;
                                this.blaze.setFireActive(false);
                            }

                            if (this.fireballsFired > 1) {
                                float h = MathHelper.sqrt(MathHelper.sqrt(d)) * 0.5F;
                                if (!this.blaze.isSilent()) {
                                    this.blaze.world.syncWorldEvent((PlayerEntity)null, 1018, this.blaze.getBlockPos(), 0);
                                }

                                for(int i = 0; i < 1; ++i) {
                                    SmallFireballEntity smallFireballEntity = new SmallFireballEntity(this.blaze.world, this.blaze, e + this.blaze.getRandom().nextGaussian() * (double)h, f, g + this.blaze.getRandom().nextGaussian() * (double)h);
                                    smallFireballEntity.updatePosition(smallFireballEntity.getX(), this.blaze.getBodyY(0.5D) + 0.5D, smallFireballEntity.getZ());
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

    @Mixin(CaveSpiderEntity.class)
    public static class CaveSpiderEntityMixin extends SpiderEntity {

        public CaveSpiderEntityMixin(EntityType<? extends SpiderEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public boolean tryAttack(Entity target) {
            if (super.tryAttack(target)) {
                if (target instanceof LivingEntity) {
                    int i = 0;
                    if (this.world.getDifficulty() == Difficulty.NORMAL) {
                        i = 7;
                    } else if (this.world.getDifficulty() == Difficulty.HARD) {
                        i = 15;
                    }

                    if (i > 0) {
                        ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, i * 20, 0));
                    }
                }

                if (target instanceof PlayerEntity) {
                    if (SpeedrunnerMod.OPTIONS.doomMode) {
                        ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
                    }
                }

                return true;
            } else {
                return false;
            }
        }
    }

    @Mixin(CreeperEntity.class)
    public abstract static class CreeperEntityMixin extends HostileEntity {

        public CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Shadow
        int explosionRadius;

        @Shadow
        abstract void ignite();

        @Environment(EnvType.CLIENT)
        @Shadow
        abstract boolean shouldRenderOverlay();

        @Overwrite
        public static DefaultAttributeContainer.Builder createCreeperAttributes() {
            return SpeedrunnerMod.applyCreeperAttributes();
        }

        @Overwrite
        public ActionResult interactMob(PlayerEntity player, Hand hand) {
            ItemStack itemStack = player.getStackInHand(hand);
            Explosion.DestructionType destructionType = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
            float f = this.shouldRenderOverlay() ? 2.0F : 1.0F;
            if (SpeedrunnerMod.getCreeperIgnitions(player, hand)) {
                this.world.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
                if (!this.world.isClient && SpeedrunnerMod.OPTIONS.doomMode) {
                    this.remove();
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
    }

    @Mixin(ElderGuardianEntity.class)
    public static class ElderGuardianEntityMixin extends GuardianEntity {

        public ElderGuardianEntityMixin(EntityType<? extends GuardianEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createElderGuardianAttributes() {
            return SpeedrunnerMod.applyElderGuardianAttributes();
        }

        @Overwrite
        public void mobTick() {
            super.mobTick();
            if ((this.age + this.getEntityId()) % SpeedrunnerMod.getElderGuardianAgeAmount() == 0) {
                StatusEffect statusEffect = StatusEffects.MINING_FATIGUE;
                List<ServerPlayerEntity> list = ((ServerWorld)this.world).getPlayers((serverPlayerEntityx) -> {
                    return this.squaredDistanceTo(serverPlayerEntityx) < SpeedrunnerMod.getElderGuardianSquaredDistanceTo() && serverPlayerEntityx.interactionManager.isSurvivalLike();
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
                    serverPlayerEntity.addStatusEffect(new StatusEffectInstance(statusEffect, SpeedrunnerMod.getElderGuardianMiningFatigueAmount(), 2));
                }
            }

            if (!this.hasPositionTarget()) {
                this.setPositionTarget(this.getBlockPos(), 16);
            }
        }
    }

    @Mixin(EndermanEntity.class)
    public static class EndermanEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createEndermanAttributes() {
            return SpeedrunnerMod.applyEndermanAttributes();
        }
    }

    @Mixin(EndermiteEntity.class)
    public static class EndermiteEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createEndermiteAttributes() {
            return SpeedrunnerMod.applyEndermiteAttributes();
        }
    }

    @Mixin(GhastEntity.class)
    public static class GhastEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createGhastAttributes() {
            return SpeedrunnerMod.applyGhastAttributes();
        }

        @Mixin(GhastEntity.ShootFireballGoal.class)
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

                        FireballEntity fireballEntity = new FireballEntity(world, this.ghast, f, g, h);
                        fireballEntity.explosionPower = this.ghast.getFireballStrength();
                        fireballEntity.updatePosition(this.ghast.getX() + vec3d.x * 4.0D, this.ghast.getBodyY(0.5D) + 0.5D, fireballEntity.getZ() + vec3d.z * 4.0D);
                        world.spawnEntity(fireballEntity);
                        if (SpeedrunnerMod.OPTIONS.killGhastUponFireball) {
                            this.ghast.kill();
                        }
                        this.cooldown = SpeedrunnerMod.getGhastFireballCooldown();
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
    @Mixin(GiantEntity.class)
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
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                this.bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS));
                this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
                this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0.0F);
                this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0.0F);
                this.waterNavigation = new SwimNavigation(this, world);
                this.landNavigation = new MobNavigation(this, world);
                this.experiencePoints = 100;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createGiantAttributes() {
            return SpeedrunnerMod.applyGiantAttributes();
        }

        protected void initGoals() {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                this.goalSelector.add(1, new SwimGoal(this));
                this.goalSelector.add(2, new GiantAttackGoal((GiantEntity) (Object) this, 1.0D, false));
                this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
                this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 32.0F));
                this.goalSelector.add(8, new LookAroundGoal(this));
                this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
                this.targetSelector.add(1, new FollowTargetGoal(this, PlayerEntity.class, true));
                this.targetSelector.add(3, new FollowTargetGoal(this, MobEntity.class, true));
            }
        }

        public void tick() {
            super.tick();
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                if (this.age % 10 == 0) {
                    this.heal(0.6F);
                }

                this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

                if (this.world instanceof ServerWorld && this.world.getRegistryKey() == World.END) {
                    if (this.getY() <= -64) {
                        this.teleport(0, 96, 0, true);
                        if (!this.isSilent()) {
                            this.world.playSound((PlayerEntity) null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 10.0F, 1.0F);
                            this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 10.0F, 1.0F);
                        }
                    }
                }
            }
        }

        public void onDeath(DamageSource source) {
            super.onDeath(source);
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                this.onGiantDeath();
                if (!this.isSilent()) {
                    this.world.playSound((PlayerEntity) null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, SoundCategory.AMBIENT, 1.0F, 1.0F);
                    this.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0F, 1.0F);
                }
            }
        }

        public boolean damage(DamageSource source, float amount) {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
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
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                this.world.sendEntityStatus(this, (byte)4);
            }
            return SpeedrunnerMod.OPTIONS.doomMode ? Giant.tryAttack(this, (LivingEntity)target) : super.tryAttack(target);
        }

        protected void knockback(LivingEntity target) {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                Giant.knockback(this, target);
            }
        }

        public void travel(Vec3d movementInput) {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
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
            if (SpeedrunnerMod.OPTIONS.doomMode) {
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
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                if (this.world.getDifficulty() == Difficulty.PEACEFUL && this.isDisallowedInPeaceful()) {
                    this.remove();
                } else {
                    this.despawnCounter = 0;
                }
            }
        }

        public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
            return !SpeedrunnerMod.OPTIONS.doomMode;
        }

        public boolean addStatusEffect(StatusEffectInstance effect) {
            return !SpeedrunnerMod.OPTIONS.doomMode;
        }

        public boolean isFireImmune() {
            return SpeedrunnerMod.OPTIONS.doomMode;
        }

        public boolean isImmuneToExplosion() {
            return SpeedrunnerMod.OPTIONS.doomMode;
        }

        public boolean canStartRiding(Entity entity) {
            return !SpeedrunnerMod.OPTIONS.doomMode && super.canStartRiding(entity);
        }

        public boolean canUsePortals() {
            return !SpeedrunnerMod.OPTIONS.doomMode;
        }

        public void setCustomName(@Nullable Text name) {
            super.setCustomName(name);
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                this.bossBar.setName(this.getDisplayName());
            }
        }

        public void onStartedTrackingBy(ServerPlayerEntity player) {
            super.onStartedTrackingBy(player);
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                this.bossBar.addPlayer(player);
            }
        }

        public void onStoppedTrackingBy(ServerPlayerEntity player) {
            super.onStoppedTrackingBy(player);
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                this.bossBar.removePlayer(player);
            }
        }

        public EntityGroup getGroup() {
            return SpeedrunnerMod.OPTIONS.doomMode ? EntityGroup.UNDEAD : EntityGroup.DEFAULT;
        }

        public SoundCategory getSoundCategory() {
            return SpeedrunnerMod.OPTIONS.doomMode ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
        }

        public SoundEvent getAmbientSound() {
            return SpeedrunnerMod.OPTIONS.doomMode ? SoundEvents.ENTITY_ZOMBIE_AMBIENT : null;
        }

        public SoundEvent getHurtSound(DamageSource source) {
            return SpeedrunnerMod.OPTIONS.doomMode ? SoundEvents.ENTITY_ZOMBIE_HURT : SoundEvents.ENTITY_GENERIC_HURT;
        }

        public SoundEvent getDeathSound() {
            return SpeedrunnerMod.OPTIONS.doomMode ? SoundEvents.ENTITY_ZOMBIE_DEATH : SoundEvents.ENTITY_GENERIC_DEATH;
        }

        protected void playStepSound(BlockPos pos, BlockState state) {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.25F, 1.0F);
            }
        }

        public float getSoundVolume() {
            return SpeedrunnerMod.getGiantSoundVolume();
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
                this.giant.setAttacking(this.ticks >= 5 && this.method_28348() < this.method_28349() / 2);
            }
        }
    }

    @Mixin(GuardianEntity.class)
    public static class GuardianEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createGuardianAttributes() {
            return SpeedrunnerMod.applyGuardianAttributes();
        }
    }

    @Mixin(HoglinEntity.class)
    public abstract static class HoglinEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createHoglinAttributes() {
            return SpeedrunnerMod.applyHoglinAttributes();
        }
    }

    @Mixin(MagmaCubeEntity.class)
    public static class MagmaCubeEntityMixin extends SlimeEntity {

        public MagmaCubeEntityMixin(EntityType<? extends SlimeEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public int getTicksUntilNextJump() {
            return SpeedrunnerMod.getMagmaCubeTicksUntilNextJumpAmount();
        }

        @Overwrite
        public float getDamageAmount() {
            return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * SpeedrunnerMod.getMagmaCubeAttackDamage();
        }
    }

    @Mixin(PiglinBrain.class)
    public static class PiglinBrainMixin {

        @Overwrite
        private static boolean getNearestZombifiedPiglin(PiglinEntity piglin) {
            return SpeedrunnerMod.getPiglinInRangeOfZombifiedPiglin(piglin);
        }
    }

    @Mixin(PiglinEntity.class)
    public static class PiglinEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createPiglinAttributes() {
            return SpeedrunnerMod.applyPiglinAttributes();
        }
    }

    @Mixin(PillagerEntity.class)
    public static class PillagerEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createPillagerAttributes() {
            return SpeedrunnerMod.applyPillagerAttributes();
        }
    }

    @Mixin(RavagerEntity.class)
    public abstract static class RavagerEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createRavagerAttributes() {
            return SpeedrunnerMod.applyRavagerAttributes();
        }

        @Inject(method = "tryAttack", at = @At("RETURN"))
        private void tryAttack(Entity target, CallbackInfoReturnable cir) {
            SpeedrunnerMod.method_29999(target, cir);
        }
    }

    @Mixin(ShulkerEntity.class)
    public abstract static class ShulkerEntityMixin extends GolemEntity {

        public ShulkerEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
            super(entityType, world);
        }

        @Shadow @Final
        private static UUID COVERED_ARMOR_BONUS_ID;

        @Shadow
        private static final EntityAttributeModifier COVERED_ARMOR_BONUS;

        @Shadow
        abstract boolean isClosed();

        @Shadow
        abstract boolean tryTeleport();

        @Overwrite
        public static DefaultAttributeContainer.Builder createShulkerAttributes() {
            return SpeedrunnerMod.applyShulkerAttributes();
        }

        @Overwrite
        public boolean damage(DamageSource source, float amount) {
            if (this.isClosed()) {
                Entity entity = source.getSource();
                if (SpeedrunnerMod.OPTIONS.doomMode) {
                    if (entity instanceof PersistentProjectileEntity) {
                        return false;
                    }
                } else {
                    if (entity instanceof PersistentProjectileEntity && world.random.nextFloat() < 0.25F) {
                        return false;
                    }
                }
            }

            if (super.damage(source, amount)) {
                if ((double)this.getHealth() < (double)this.getMaxHealth() * 0.5D && this.random.nextInt(4) == 0) {
                    this.tryTeleport();
                }

                return true;
            } else {
                return false;
            }
        }

        static {
            COVERED_ARMOR_BONUS = new EntityAttributeModifier(COVERED_ARMOR_BONUS_ID, "Covered armor bonus", 10.0D, EntityAttributeModifier.Operation.ADDITION);
        }
    }

    @Mixin(SilverfishEntity.class)
    public static class SilverfishEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createSilverfishAttributes() {
            return SpeedrunnerMod.applySilverfishAttributes();
        }

        @Mixin(SilverfishEntity.CallForHelpGoal.class)
        public static class CallForHelpGoalMixin {

            @Shadow
            int delay;

            @Overwrite
            public void onHurt() {
                if (this.delay == 0) {
                    this.delay = SpeedrunnerMod.getSilverfishDelayAmount();
                }
            }
        }
    }

    @Mixin(SkeletonEntity.class)
    public abstract static class SkeletonEntityMixin extends AbstractSkeletonEntity {

        public SkeletonEntityMixin(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
            super(entityType, world);
        }

        protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
            PersistentProjectileEntity persistentProjectileEntity = super.createArrowProjectile(arrow, damageModifier);
            if (persistentProjectileEntity instanceof ArrowEntity && SpeedrunnerMod.OPTIONS.doomMode) {
                ((ArrowEntity)persistentProjectileEntity).addEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
            }

            return persistentProjectileEntity;
        }
    }

    @Mixin(SlimeEntity.class)
    public static class SlimeEntityMixin {

        @Overwrite
        public int getTicksUntilNextJump() {
            return SpeedrunnerMod.getSlimeTicksUntilNextJumpAmount();
        }
    }

    @Mixin(SpiderEntity.class)
    public static class SpiderEntityMixin extends HostileEntity {

        public SpiderEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        public boolean tryAttack(Entity target) {
            if (!super.tryAttack(target)) {
                return false;
            } else {
                if (target instanceof PlayerEntity) {
                    if (SpeedrunnerMod.OPTIONS.doomMode) {
                        ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
                    }
                }

                return true;
            }
        }
    }

    @Mixin(VexEntity.class)
    public static class VexEntityMixin extends HostileEntity {

        public VexEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Shadow
        boolean alive;

        @Shadow
        int lifeTicks;

        @Overwrite
        public static DefaultAttributeContainer.Builder createVexAttributes() {
            return SpeedrunnerMod.applyVexAttributes();
        }

        @Overwrite
        public void tick() {
            this.noClip = !SpeedrunnerMod.OPTIONS.doomMode;
            super.tick();
            this.noClip = false;
            this.setNoGravity(true);
            if (this.alive && --this.lifeTicks <= 0) {
                this.lifeTicks = 20;
                this.damage(DamageSource.STARVE, SpeedrunnerMod.getVexDecayAmount());
            }
        }

        public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
            return !SpeedrunnerMod.OPTIONS.doomMode;
        }
    }

    @Mixin(VindicatorEntity.class)
    public abstract static class VindicatorEntityMixin extends IllagerEntity {

        public VindicatorEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createVindicatorAttributes() {
            return SpeedrunnerMod.applyVindicatorAttributes();
        }

        public boolean tryAttack(Entity target) {
            if (!super.tryAttack(target)) {
                return false;
            } else {
                if (target instanceof PlayerEntity) {
                    if (SpeedrunnerMod.OPTIONS.doomMode) {
                        ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
                    }
                }

                return true;
            }
        }
    }

    @Mixin(WitchEntity.class)
    public static class WitchEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createWitchAttributes() {
            return SpeedrunnerMod.applyWitchAttributes();
        }
    }

    @Mixin(WitherSkeletonEntity.class)
    public abstract static class WitherSkeletonEntityMixin extends AbstractSkeletonEntity {

        public WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable CompoundTag entityTag) {
            EntityData entityData2 = super.initialize(world, difficulty, spawnReason, entityData, entityTag);
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(SpeedrunnerMod.getWitherSkeletonAttackDamage());
            this.updateAttackType();
            return entityData2;
        }

        @Overwrite
        public boolean tryAttack(Entity target) {
            if (!super.tryAttack(target)) {
                return false;
            } else {
                if (target instanceof LivingEntity) {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, SpeedrunnerMod.getWitherSkeletonEffectDuration()));
                }

                if (target instanceof PlayerEntity && SpeedrunnerMod.OPTIONS.doomMode) {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
                }

                return true;
            }
        }
    }

    @Mixin(ZoglinEntity.class)
    public static class ZoglinEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createZoglinAttributes() {
            return SpeedrunnerMod.applyZoglinAttributes();
        }
    }

    @Mixin(ZombieEntity.class)
    public static class ZombieEntityMixin extends HostileEntity {

        public ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createZombieAttributes() {
            return SpeedrunnerMod.applyZombieAttributes();
        }

        public boolean tryAttack(Entity target) {
            if (!super.tryAttack(target)) {
                return false;
            } else {
                if (target instanceof PlayerEntity && SpeedrunnerMod.OPTIONS.doomMode) {
                    ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
                }

                return true;
            }
        }
    }

    @Mixin(ZombifiedPiglinEntity.class)
    public static class ZombifiedPiglinEntityMixin extends ZombieEntity {

        public ZombifiedPiglinEntityMixin(EntityType<? extends ZombieEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createZombifiedPiglinAttributes() {
            return SpeedrunnerMod.applyZombifiedPiglinAttributes();
        }

        public boolean tryAttack(Entity target) {
            if (!super.tryAttack(target)) {
                return false;
            } else {
                if (target instanceof PlayerEntity && SpeedrunnerMod.OPTIONS.doomMode) {
                    ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
                }

                return true;
            }
        }
    }

    @Mixin(DolphinEntity.class)
    public static class DolphinEntityMixin {

        @Shadow
        private static final TargetPredicate CLOSE_PLAYER_PREDICATE = new TargetPredicate().setBaseMaxDistance(20.0D).includeTeammates().includeInvulnerable().includeHidden();

        @Mixin(DolphinEntity.SwimWithPlayerGoal.class)
        public static class SwimWithPlayerGoalMixin {

            @Shadow
            private PlayerEntity closestPlayer;

            @Overwrite
            public void start() {
                this.closestPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200));
            }
        }
    }

    @Mixin(IronGolemEntity.class)
    public static class IronGolemEntityMixin {

        @Overwrite
        public static DefaultAttributeContainer.Builder createIronGolemAttributes() {
            return SpeedrunnerMod.applyIronGolemAttributes();
        }
    }

    @Mixin(SheepEntity.class)
    public abstract static class SheepEntityMixin extends AnimalEntity {

        public SheepEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
            super(entityType, world);
        }

        @ModifyVariable(method = "sheared", at = @At("STORE"))
        private int sheared(int x) {
            return 6 + this.random.nextInt(4);
        }
    }

    @Mixin(PlayerEntity.class)
    public abstract static class PlayerEntityMixin extends LivingEntity {

        public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
            super(entityType, world);
        }

        @Shadow
        abstract ItemCooldownManager getItemCooldownManager();

        @Inject(method = "takeShieldHit", at = @At("TAIL"))
        private void takeShieldHit(LivingEntity attacker, CallbackInfo ci) {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                if (attacker instanceof GiantEntity) {
                    this.getItemCooldownManager().set(Items.SHIELD, 200);
                    this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 180);
                    this.clearActiveItem();
                    this.world.sendEntityStatus(this, (byte)30);
                }
            }
        }

        @Inject(method = "eatFood", at = @At("RETURN"))
        private void eatFood(World world, ItemStack stack, CallbackInfoReturnable cir) {
            boolean bl = stack.getItem() == ModItems.SPEEDRUNNER_BULK;
            if (stack.getItem() == Items.GOLDEN_CARROT || stack.getItem() == Items.GOLDEN_APPLE || stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE || stack.getItem() == ModItems.SPEEDRUNNER_BULK) {
                this.removeStatusEffect(StatusEffects.POISON);
                if (bl) {
                    this.heal(2.0F);
                }
            }
        }
    }

    @Mixin(EnderPearlEntity.class)
    public abstract static class EnderPearlEntityMixin extends ThrownItemEntity {

        public EnderPearlEntityMixin(EntityType<? extends EnderPearlEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public void onCollision(HitResult hitResult) {
            super.onCollision(hitResult);
            Entity entity = this.getOwner();
            boolean ifb = EnchantmentHelper.getLevel(Enchantments.INFINITY, super.getItem()) > 0;

            for(int i = 0; i < 32; ++i) {
                this.world.addParticle(ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * 2.0D, this.getZ(), this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
            }

            if (!this.world.isClient && !this.removed) {
                if (entity instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
                    if (serverPlayerEntity.networkHandler.getConnection().isOpen() && serverPlayerEntity.world == this.world && !serverPlayerEntity.isSleeping()) {
                        if (this.random.nextFloat() < 0.05F && this.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
                            EndermiteEntity endermiteEntity = (EndermiteEntity)EntityType.ENDERMITE.create(this.world);
                            endermiteEntity.setPlayerSpawned(true);
                            endermiteEntity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.yaw, entity.pitch);
                            this.world.spawnEntity(endermiteEntity);
                        }

                        if (entity.hasVehicle()) {
                            entity.stopRiding();
                        }

                        entity.requestTeleport(this.getX(), this.getY(), this.getZ());
                        entity.fallDistance = 0.0F;
                        if (!ifb) {
                            if (SpeedrunnerMod.OPTIONS.doomMode) {
                                if (!serverPlayerEntity.isCreative() || !serverPlayerEntity.isSpectator()) {
                                    ((ServerPlayerEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
                                }
                            }
                            entity.damage(DamageSource.FALL, SpeedrunnerMod.getEnderPearlDamage());
                        }
                    }
                } else if (entity != null) {
                    entity.requestTeleport(this.getX(), this.getY(), this.getZ());
                    entity.fallDistance = 0.0F;
                }

                this.remove();
            }
        }
    }

    @Mixin(SmallFireballEntity.class)
    public static class SmallFireballEntityMixin extends AbstractFireballEntity {

        public SmallFireballEntityMixin(EntityType<? extends AbstractFireballEntity> entityType, World world) {
            super(entityType, world);
        }

        @Overwrite
        public void onEntityHit(EntityHitResult entityHitResult) {
            super.onEntityHit(entityHitResult);
            if (!this.world.isClient) {
                Entity entity = entityHitResult.getEntity();
                if (!entity.isFireImmune()) {
                    Entity entity2 = this.getOwner();
                    int i = entity.getFireTicks();
                    entity.setOnFireFor(SpeedrunnerMod.getSmallFireballEntityFireTicks());
                    boolean bl = entity.damage(DamageSource.fireball(this, entity2), SpeedrunnerMod.getSmallFireballDamageAmount());
                    if (!bl) {
                        entity.setFireTicks(i);
                    } else if (entity2 instanceof LivingEntity) {
                        this.dealDamage((LivingEntity)entity2, entity);
                    }
                }
            }
        }
    }

    @Mixin(Entity.class)
    public static class EntityMixin {

        @Overwrite
        public void setOnFireFromLava() {
            Entity entity = (Entity)(Object)this;
            if (!entity.isFireImmune()) {
                entity.setOnFireFor(SpeedrunnerMod.getLavaFireTicks());
                entity.damage(DamageSource.LAVA, SpeedrunnerMod.getLavaDamageAmount());
            }
        }
    }

    @Mixin(EyeOfEnderEntity.class)
    public abstract static class EyeOfEnderEntityMixin extends Entity {

        public EyeOfEnderEntityMixin(EntityType<? extends EyeOfEnderEntity> type, World world) {
            super(type, world);
        }

        @Shadow
        private double targetX, targetY, targetZ;

        @Shadow
        private int lifespan;

        @Shadow @Final
        static TrackedData<ItemStack> ITEM;

        @Overwrite
        public void tick() {
            super.tick();
            Vec3d vec3d = this.getVelocity();
            Explosion.DestructionType destructionType = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
            double d = this.getX() + vec3d.x;
            double e = this.getY() + vec3d.y;
            double f = this.getZ() + vec3d.z;
            float g = MathHelper.sqrt(squaredHorizontalLength(vec3d));
            this.pitch = ProjectileEntity.updateRotation(this.prevPitch, (float)(MathHelper.atan2(vec3d.y, (double)g) * 57.2957763671875D));
            this.yaw = ProjectileEntity.updateRotation(this.prevYaw, (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D));
            if (!this.world.isClient) {
                double h = this.targetX - d;
                double i = this.targetZ - f;
                float j = (float)Math.sqrt(h * h + i * i);
                float k = (float)MathHelper.atan2(i, h);
                double l = MathHelper.lerp(0.0025D, (double)g, (double)j);
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
                this.updatePosition(d, e, f);
                ++this.lifespan;
                if (this.lifespan > 40 && !this.world.isClient) {
                    this.remove();
                    this.world.spawnEntity(new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), this.getStack()));
                    if (SpeedrunnerMod.OPTIONS.doomMode) {
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

        public ItemStack getStack() {
            ItemStack itemStack = this.getTrackedItem();
            return itemStack.isEmpty() ? new ItemStack(Items.ENDER_EYE) : itemStack;
        }

        private ItemStack getTrackedItem() {
            return (ItemStack)this.getDataTracker().get(ITEM);
        }
    }

    @Mixin(ItemEntity.class)
    public static class ItemEntityMixin {

        @Inject(method = "isFireImmune", at = @At("RETURN"))
        public boolean isFireImmune(CallbackInfoReturnable cir) {
            ItemEntity item = (ItemEntity)(Object)this;
            ItemStack stack = item.getStack();

            if (stack.getItem() == Items.BLAZE_ROD || stack.getItem() == Items.BLAZE_POWDER) {
                return true;
            }

            return cir.getReturnValueZ();
        }
    }

    @Mixin(EnderPearlItem.class)
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
                enderPearlEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
                world.spawnEntity(enderPearlEntity);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.abilities.creativeMode && !bl) {
                itemStack.decrement(1);
            }

            return TypedActionResult.success(itemStack, world.isClient());
        }
    }

    @Mixin(FoodComponents.class)
    public static class FoodComponentsMixin {

        @Shadow
        private static final FoodComponent APPLE = ModFoodComponents.APPLE, BAKED_POTATO = ModFoodComponents.BAKED_POTATO, BEEF = ModFoodComponents.BEEF, BEETROOT = ModFoodComponents.BEETROOT, BREAD = ModFoodComponents.BREAD, CARROT = ModFoodComponents.CARROT, CHICKEN = ModFoodComponents.CHICKEN, CHORUS_FRUIT = ModFoodComponents.CHORUS_FRUIT, COD = ModFoodComponents.COD, COOKED_BEEF = ModFoodComponents.COOKED_BEEF, COOKED_CHICKEN = ModFoodComponents.COOKED_CHICKEN, COOKED_COD = ModFoodComponents.COOKED_COD, COOKED_MUTTON = ModFoodComponents.COOKED_MUTTON, COOKED_PORKCHOP = ModFoodComponents.COOKED_PORKCHOP, COOKED_RABBIT = ModFoodComponents.COOKED_RABBIT, COOKED_SALMON = ModFoodComponents.COOKED_SALMON, COOKIE = ModFoodComponents.COOKIE, DRIED_KELP = ModFoodComponents.DRIED_KELP, ENCHANTED_GOLDEN_APPLE = ModFoodComponents.ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE = ModFoodComponents.GOLDEN_APPLE, GOLDEN_CARROT = ModFoodComponents.GOLDEN_CARROT, HONEY_BOTTLE = ModFoodComponents.HONEY_BOTTLE, MELON_SLICE = ModFoodComponents.MELON_SLICE, MUTTON = ModFoodComponents.MUTTON, POISONOUS_POTATO = ModFoodComponents.POISONOUS_POTATO, PORKCHOP = ModFoodComponents.PORKCHOP, POTATO = ModFoodComponents.POTATO, PUFFERFISH = ModFoodComponents.PUFFERFISH, PUMPKIN_PIE = ModFoodComponents.PUMPKIN_PIE, RABBIT = ModFoodComponents.RABBIT, ROTTEN_FLESH = ModFoodComponents.ROTTEN_FLESH, SALMON = ModFoodComponents.SALMON, SPIDER_EYE = ModFoodComponents.SPIDER_EYE, SWEET_BERRIES = ModFoodComponents.SWEET_BERRIES, TROPICAL_FISH = ModFoodComponents.TROPICAL_FISH;
    }

    @Mixin(ServerPlayerEntity.class)
    public abstract static class ServerPlayerEntityMixin extends PlayerEntity {

        public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
            super(world, pos, yaw, profile);
        }

        @Shadow @Final
        private ServerStatHandler statHandler;

        @Inject(method = "<init>", at = @At("TAIL"))
        private void init(CallbackInfo ci) throws CommandSyntaxException {
            if (this.statHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_ONE_MINUTE)) == 0) {
                ItemStack item;
                if (SpeedrunnerMod.OPTIONS.iCarusMode) {
                    item = this.itemStackFromString("minecraft:elytra{Unbreakable:1b}", 1);
                    ItemStack item2 = this.itemStackFromString("minecraft:firework_rocket{Fireworks:{Flight:3b}}", 64);
                    this.inventory.armor.set(2, item);
                    this.inventory.main.set(0, item2);
                }

                if (SpeedrunnerMod.OPTIONS.infiniPearlMode) {
                    item = new ItemStack(Items.ENDER_PEARL, 1);
                    item.addEnchantment(Enchantments.INFINITY, 1);
                    item.getOrCreateTag().putInt("HideFlags", 1);

                    LiteralText text = new LiteralText("InfiniPearl™");
                    text.setStyle(text.getStyle().withItalic(false));
                    item.setCustomName(text);

                    if (!SpeedrunnerMod.OPTIONS.iCarusMode) {
                        this.inventory.main.set(0, item);
                    } else {
                        this.inventory.main.set(1, item);
                    }
                }
            }
        }

        private ItemStack itemStackFromString(String string, int count) throws CommandSyntaxException {
            return new ItemStackArgumentType().parse(new StringReader(string)).createStack(count, false);
        }
    }

    @Mixin(TradeOffers.EnchantBookFactory.class)
    public static class EnchantBookFactoryMixin {

        @Shadow @Final
        private int experience;

        @Overwrite
        public TradeOffer create(Entity entity, Random random) {
            List<Enchantment> list = (List) Registry.ENCHANTMENT.stream().filter(Enchantment::isAvailableForEnchantedBookOffer).collect(Collectors.toList());
            Enchantment enchantment = (Enchantment)list.get(random.nextInt(list.size()));
            int i = MathHelper.nextInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel());
            ItemStack itemStack = EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, i));
            int j = 2 + random.nextInt(1 + i * 3) * i;

            if (j > 64) {
                j = 64;
            }

            return new TradeOffer(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemStack, 16, this.experience, 0.01F);
        }
    }

    /**
     * See {@linkplain net.minecraft.datafixer.fix.LevelDataGeneratorOptionsFix} for more.
     */
    @Mixin(value = SetBaseBiomesLayer.class, priority = 999)
    public static class SetBaseBiomesLayerMixin {

        @Shadow
        private static final int[] DRY_BIOMES = SpeedrunnerMod.getDryBiomeIds();

        @Shadow
        private static final int[] TEMPERATE_BIOMES = SpeedrunnerMod.getTemperateBiomeIds();

        @Shadow
        private static final int[] COOL_BIOMES = SpeedrunnerMod.getCoolBiomeIds();

        @Shadow
        private static final int[] SNOWY_BIOMES = SpeedrunnerMod.getSnowyBiomeIds();
    }

    @Mixin(DefaultBiomeCreator.class)
    public static class DefaultBiomeCreatorMixin {

        @Overwrite
        public static Biome createPlains(boolean bl) {
            return SpeedrunnerMod.method_21000(bl);
        }

        @Overwrite
        private static Biome createForest(float depth, float scale, boolean flower, SpawnSettings.Builder spawnSettings) {
            return SpeedrunnerMod.method_21002(depth, scale, flower, spawnSettings);
        }

        @Overwrite
        public static Biome createMountains(float depth, float scale, ConfiguredSurfaceBuilder<TernarySurfaceConfig> surfaceBuilder, boolean extraTrees) {
            return SpeedrunnerMod.method_21003(depth, scale, surfaceBuilder, extraTrees);
        }

        @Overwrite
        public static Biome createNetherWastes() {
            return SpeedrunnerMod.method_21004();
        }

        @Overwrite
        public static Biome createSoulSandValley() {
            return SpeedrunnerMod.method_21005();
        }

        @Overwrite
        public static Biome createBasaltDeltas() {
            return SpeedrunnerMod.method_21006();
        }

        @Overwrite
        public static Biome createCrimsonForest() {
            return SpeedrunnerMod.method_21007();
        }

        @Overwrite
        public static Biome createWarpedForest() {
            return SpeedrunnerMod.method_21008();
        }
    }

    @Mixin(AreaHelper.class)
    public static class AreaHelperMixin {

        @Shadow
        private static final AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK = (state, world, pos) -> {
            return state.isOf(Blocks.OBSIDIAN) || state.isOf(Blocks.CRYING_OBSIDIAN);
        };
    }

    @Mixin(StrongholdConfig.class)
    public static class StrongholdConfigMixin {

        @Shadow @Final
        private int distance;

        @Shadow @Final
        private int count;

        @Overwrite
        public int getDistance() {
            return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 4 : this.distance;
        }

        @Overwrite
        public int getCount() {
            return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? SpeedrunnerMod.OPTIONS.getStrongholdCount() : this.count;
        }
    }

    @Mixin(StructuresConfig.class)
    public static class StructuresConfigMixin {

        @Shadow
        private static final ImmutableMap<Object, Object> DEFAULT_STRUCTURES = SpeedrunnerMod.makeStructuresMoreCommon();
    }

    @Mixin(DefaultBiomeFeatures.class)
    public static class DefaultBiomeFeaturesMixin {

        @Inject(method = "addDefaultOres", at = @At("TAIL"))
        private static void addDefaultOres(GenerationSettings.Builder builder, CallbackInfo ci) {
            SpeedrunnerMod.addModdedOresAndMakeDiamondsMoreCommon(builder, ci);
        }

        @Inject(method = "addNetherMineables", at = @At("TAIL"))
        private static void addNetherMineables(GenerationSettings.Builder builder, CallbackInfo ci) {
            SpeedrunnerMod.addModdedOresNether(builder, ci);
        }

        @Inject(method = "addAncientDebris", at = @At("TAIL"))
        private static void addAncientDebris(GenerationSettings.Builder builder, CallbackInfo ci) {
            SpeedrunnerMod.makeAncientDebrisMoreCommon(builder, ci);
        }

        @Overwrite
        public static void addMonsters(net.minecraft.world.biome.SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight) {
            SpeedrunnerMod.method_21011(builder, zombieWeight, zombieVillagerWeight, skeletonWeight);
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
            SpeedrunnerMod.method_21012(builder);
        }
    }

    @Mixin(NetherFortressFeature.class)
    public abstract static class NetherFortressFeatureMixin {

        @Shadow
        private static final List<SpawnSettings.SpawnEntry> MONSTER_SPAWNS = SpeedrunnerMod.applyNetherFortressMobSpawns();
    }

    @Mixin(StrongholdFeature.Start.class)
    public static class StrongholdFeatureStartMixin extends StructureStart<DefaultFeatureConfig> {

        public StrongholdFeatureStartMixin(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
            super(feature, chunkX, chunkZ, box, references, seed);
        }

        @Shadow @Final
        private long seed;

        @Overwrite
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            int var8 = 0;

            net.minecraft.structure.StrongholdGenerator.Start start;
            do {
                this.children.clear();
                this.boundingBox = BlockBox.empty();
                this.random.setCarverSeed(this.seed + (long)(var8++), i, j);
                StrongholdGenerator.init();
                start = new net.minecraft.structure.StrongholdGenerator.Start(this.random, (i << 4) + 2, (j << 4) + 2);
                this.children.add(start);
                start.fillOpenings(start, this.children, this.random);
                List list = start.pieces;

                while(!list.isEmpty()) {
                    int l = this.random.nextInt(list.size());
                    StructurePiece structurePiece = (StructurePiece)list.remove(l);
                    structurePiece.fillOpenings(start, this.children, this.random);
                }

                this.setBoundingBoxFromChildren();
                this.randomUpwardTranslation(this.random, SpeedrunnerMod.getStrongholdMinY(), SpeedrunnerMod.getStrongholdMaxY());
            } while(this.children.isEmpty() || start.portalRoom == null);

        }
    }

    @Mixin(NetherFortressGenerator.class)
    public static class NetherFortressGeneratorMixin {

        @Shadow
        private static final NetherFortressGenerator.PieceData[] ALL_BRIDGE_PIECES = SpeedrunnerMod.applyNetherFortressBridgeGeneration();

        @Shadow
        private static final NetherFortressGenerator.PieceData[] ALL_CORRIDOR_PIECES = SpeedrunnerMod.applyNetherFortressCorridorGeneration();
    }

    @Mixin(StrongholdGenerator.class)
    public static class StrongholdGeneratorMixin {

        @Shadow
        private static final StrongholdGenerator.PieceData[] ALL_PIECES = SpeedrunnerMod.applyStrongholdGeneration();

        @Mixin(StrongholdGenerator.PortalRoom.class)
        public static class PortalRoomMixin extends StrongholdGenerator.Piece {

            public PortalRoomMixin(StructurePieceType structurePieceType, int i) {
                super(structurePieceType, i);
            }

            @Shadow
            private boolean spawnerPlaced;

            @Overwrite
            public boolean generate(StructureWorldAccess structureWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
                this.fillWithOutline(structureWorldAccess, boundingBox, 0, 0, 0, 10, 7, 15, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.generateEntrance(structureWorldAccess, random, boundingBox, StrongholdGenerator.Piece.EntranceType.GRATES, 4, 1, 0);
                int i = 6;
                this.fillWithOutline(structureWorldAccess, boundingBox, 1, i, 1, 1, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(structureWorldAccess, boundingBox, 9, i, 1, 9, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(structureWorldAccess, boundingBox, 2, i, 1, 8, i, 2, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(structureWorldAccess, boundingBox, 2, i, 14, 8, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(structureWorldAccess, boundingBox, 1, 1, 1, 2, 1, 4, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(structureWorldAccess, boundingBox, 8, 1, 1, 9, 1, 4, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(structureWorldAccess, boundingBox, 1, 1, 1, 1, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
                this.fillWithOutline(structureWorldAccess, boundingBox, 9, 1, 1, 9, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
                this.fillWithOutline(structureWorldAccess, boundingBox, 3, 1, 8, 7, 1, 12, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(structureWorldAccess, boundingBox, 4, 1, 9, 6, 1, 11, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
                BlockState blockState = (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true);
                BlockState blockState2 = (BlockState)((BlockState)Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true)).with(PaneBlock.EAST, true);

                int k;
                for(k = 3; k < 14; k += 2) {
                    this.fillWithOutline(structureWorldAccess, boundingBox, 0, 3, k, 0, 4, k, blockState, blockState, false);
                    this.fillWithOutline(structureWorldAccess, boundingBox, 10, 3, k, 10, 4, k, blockState, blockState, false);
                }

                for(k = 2; k < 9; k += 2) {
                    this.fillWithOutline(structureWorldAccess, boundingBox, k, 3, 15, k, 4, 15, blockState2, blockState2, false);
                }

                BlockState blockState3 = (BlockState)Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
                this.fillWithOutline(structureWorldAccess, boundingBox, 4, 1, 5, 6, 1, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(structureWorldAccess, boundingBox, 4, 2, 6, 6, 2, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(structureWorldAccess, boundingBox, 4, 3, 7, 6, 3, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);

                for(int l = 4; l <= 6; ++l) {
                    this.addBlock(structureWorldAccess, blockState3, l, 1, 4, boundingBox);
                    this.addBlock(structureWorldAccess, blockState3, l, 2, 5, boundingBox);
                    this.addBlock(structureWorldAccess, blockState3, l, 3, 6, boundingBox);
                }

                BlockState blockState4 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
                BlockState blockState5 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
                BlockState blockState6 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
                BlockState blockState7 = (BlockState)Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);
                boolean bl = true;
                boolean[] bls = new boolean[12];

                for(int m = 0; m < bls.length; ++m) {
                    bls[m] = random.nextFloat() > SpeedrunnerMod.getEndPortalFilledWithEyeChance();
                    bl &= bls[m];
                }

                this.addBlock(structureWorldAccess, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[0]), 4, 3, 8, boundingBox);
                this.addBlock(structureWorldAccess, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[1]), 5, 3, 8, boundingBox);
                this.addBlock(structureWorldAccess, (BlockState)blockState4.with(EndPortalFrameBlock.EYE, bls[2]), 6, 3, 8, boundingBox);
                this.addBlock(structureWorldAccess, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[3]), 4, 3, 12, boundingBox);
                this.addBlock(structureWorldAccess, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[4]), 5, 3, 12, boundingBox);
                this.addBlock(structureWorldAccess, (BlockState)blockState5.with(EndPortalFrameBlock.EYE, bls[5]), 6, 3, 12, boundingBox);
                this.addBlock(structureWorldAccess, (BlockState)blockState6.with(EndPortalFrameBlock.EYE, bls[6]), 3, 3, 9, boundingBox);
                this.addBlock(structureWorldAccess, (BlockState)blockState6.with(EndPortalFrameBlock.EYE, bls[7]), 3, 3, 10, boundingBox);
                this.addBlock(structureWorldAccess, (BlockState)blockState6.with(EndPortalFrameBlock.EYE, bls[8]), 3, 3, 11, boundingBox);
                this.addBlock(structureWorldAccess, (BlockState)blockState7.with(EndPortalFrameBlock.EYE, bls[9]), 7, 3, 9, boundingBox);
                this.addBlock(structureWorldAccess, (BlockState)blockState7.with(EndPortalFrameBlock.EYE, bls[10]), 7, 3, 10, boundingBox);
                this.addBlock(structureWorldAccess, (BlockState)blockState7.with(EndPortalFrameBlock.EYE, bls[11]), 7, 3, 11, boundingBox);
                if (bl) {
                    BlockState blockState8 = Blocks.END_PORTAL.getDefaultState();
                    this.addBlock(structureWorldAccess, blockState8, 4, 3, 9, boundingBox);
                    this.addBlock(structureWorldAccess, blockState8, 5, 3, 9, boundingBox);
                    this.addBlock(structureWorldAccess, blockState8, 6, 3, 9, boundingBox);
                    this.addBlock(structureWorldAccess, blockState8, 4, 3, 10, boundingBox);
                    this.addBlock(structureWorldAccess, blockState8, 5, 3, 10, boundingBox);
                    this.addBlock(structureWorldAccess, blockState8, 6, 3, 10, boundingBox);
                    this.addBlock(structureWorldAccess, blockState8, 4, 3, 11, boundingBox);
                    this.addBlock(structureWorldAccess, blockState8, 5, 3, 11, boundingBox);
                    this.addBlock(structureWorldAccess, blockState8, 6, 3, 11, boundingBox);
                }

                if (!this.spawnerPlaced) {
                    i = this.applyYTransform(3);
                    BlockPos blockPos2 = new BlockPos(this.applyXTransform(5, 6), i, this.applyZTransform(5, 6));
                    if (boundingBox.contains(blockPos2)) {
                        this.spawnerPlaced = true;
                        structureWorldAccess.setBlockState(blockPos2, Blocks.SPAWNER.getDefaultState(), 2);
                        BlockEntity blockEntity = structureWorldAccess.getBlockEntity(blockPos2);
                        if (blockEntity instanceof MobSpawnerBlockEntity) {
                            ((MobSpawnerBlockEntity)blockEntity).getLogic().setEntityId(EntityType.SILVERFISH);
                        }
                    }
                }

                return true;
            }
        }
    }

    @Mixin(MobSpawnerLogic.class)
    public abstract static class MobSpawnerLogicMixin {

        @Shadow
        private int spawnDelay;

        @Shadow
        private final List<MobSpawnerEntry> spawnPotentials = Lists.newArrayList();

        @Shadow
        abstract void setSpawnEntry(MobSpawnerEntry spawnEntry);

        @Shadow
        abstract void sendStatus(int status);

        @Shadow
        abstract World getWorld();

        int minSpawnDelayMixin = 200;
        int maxSpawnDelayMixin = 400;

        @Overwrite
        private void updateSpawns() {
            if (this.maxSpawnDelayMixin <= this.minSpawnDelayMixin) {
                this.spawnDelay = this.minSpawnDelayMixin;
            } else {
                int var10003 = this.maxSpawnDelayMixin - this.minSpawnDelayMixin;
                this.spawnDelay = this.minSpawnDelayMixin + this.getWorld().random.nextInt(var10003);
            }

            if (!this.spawnPotentials.isEmpty()) {
                this.setSpawnEntry((MobSpawnerEntry) WeightedPicker.getRandom(this.getWorld().random, this.spawnPotentials));
            }

            this.sendStatus(1);
        }
    }
}