package net.dilloney.speedrunnermod.mixin;

import com.google.common.collect.ImmutableMap;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.tag.ModBlockTags;
import net.dilloney.speedrunnermod.util.Giant;
import net.dilloney.speedrunnermod.util.ModHelper;
import net.dilloney.speedrunnermod.world.gen.feature.ModConfiguredFeatures;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.client.sound.MusicType;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.enchantment.*;
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
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.damage.DamageSource;
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
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.*;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.structure.*;
import net.minecraft.tag.Tag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.biome.layer.AddBaseBiomesLayer;
import net.minecraft.world.dimension.AreaHelper;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StrongholdConfig;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.addBatsAndMonsters;

/**
 * This class contains everything that needs to be changed in the game by using {@link Mixin}s.
 * <p> I realized that this mod had so many mixins and it was hard to keep track of all of them, especially on github. So, I have combined
 * all of the mixins into one class file for easier use and create less cluster. I would recommend that you do not touch or modify this class
 * in any way, shape or form. </p>
 * <p> This class contains all of the {@link Mixin}s for block hardness values, modified entity properties, modified world generation,
 * modded ore generation, modified item properties, utilities and more. </p>
 * <p> For easier access to all of the mixins, I have provided a list of all the mixins below (CTRL + left click will instantly take you to the mixin). </p>
 * <h2> "block" package </h2>
 * <p> {@linkplain AbstractBlockStateMixin}, {@linkplain AbstractFireBlockMixin}, {@linkplain BeehiveBlockMixin}, {@linkplain BlockMixin}, {@linkplain OreBlockMixin},
 * {@linkplain PumpkinBlockMixin}, {@linkplain SpawnerBlockMixin}, {@linkplain TntBlockMixin}, {@linkplain TripwireBlockMixin}. </p>
 * <h2> "enchantment" package </h2>
 * <p> {@linkplain EfficiencyEnchantmentMixin} </p>
 * <h2> "entity" package </h2>
 * <p> {@linkplain EnderDragonEntityMixin}, {@linkplain EnderDragonFightMixin} {@linkplain WitherEntityMixin}, {@linkplain AbstractSkeletonEntityMixin}, {@linkplain BlazeEntityMixin}, {@linkplain CreeperEntityMixin},
 * {@linkplain ElderGuardianEntityMixin}, {@linkplain EndermanEntityMixin}, {@linkplain EndermiteEntityMixin}, {@linkplain GhastEntityMixin}, {@linkplain GiantEntityMixin} {@linkplain GuardianEntityMixin},
 * {@linkplain HoglinEntityMixin}, {@linkplain MagmaCubeEntityMixin}, {@linkplain PiglinBrainMixin}, {@linkplain PiglinEntityMixin}, {@linkplain PillagerEntityMixin},
 * {@linkplain RavagerEntityMixin}, {@linkplain ShulkerEntityMixin}, {@linkplain SilverfishEntityMixin}, {@linkplain SlimeEntityMixin} {@linkplain SpiderEntityMixin}, {@linkplain VindicatorEntityMixin},
 * {@linkplain WitchEntityMixin}, {@linkplain WitherSkeletonEntityMixin}, {@linkplain ZoglinEntityMixin}, {@linkplain ZombieEntityMixin}, {@linkplain ZombifiedPiglinEntityMixin},
 * {@linkplain DolphinEntityMixin}, {@linkplain IronGolemEntityMixin}, {@linkplain SheepEntityMixin}, {@linkplain SnowGolemEntityMixin},
 * {@linkplain PlayerEntityMixin}, {@linkplain EnderPearlEntityMixin}, {@linkplain SmallFireballEntityMixin}, {@linkplain EntityMixin}, {@linkplain EyeOfEnderEntityMixin},
 * {@linkplain ItemEntityMixin}, {@linkplain LivingEntityMixin}. </p>
 * <h2> "item" package </h2>
 * <p> {@linkplain EnderPearlItemMixin}, {@linkplain FoodComponentsMixin} </p>
 * <h2> "predicate" package </h2>
 * <p> {@linkplain ItemPredicateMixin} </p>
 * <h2> "recipe" package </h2>
 * <p> {@linkplain ShapelessRecipeMixin} </p>
 * <h2> "server" package </h2>
 * <p> {@linkplain ServerPlayerEntityMixin} </p>
 * <h2> "village" package </h2>
 * <p> {@linkplain EnchantBookFactoryMixin} </p>
 * <h2> "world" package </h2>
 * <p> {@linkplain AddBaseBiomesLayerMixin}, {@linkplain DefaultBiomeCreatorMixin}, {@linkplain AreaHelperMixin}, {@linkplain StrongholdConfigMixin}, {@linkplain StructuresConfigMixin}, {@linkplain DefaultBiomeFeaturesMixin}, {@linkplain NetherFortressFeatureMixin},
 * {@linkplain StrongholdFeatureStartMixin}, {@linkplain NetherFortressGeneratorMixin}, {@linkplain RuinedPortalFeatureMixin}, {@linkplain StrongholdGeneratorMixin}. </p>
 * Hope that helps! Enjoy speedrunning!
 * <p> 3,224 lines of code! </p>
 * @author Dilloney
 */
public class ModMixins {

    @Mixin(AbstractBlock.AbstractBlockState.class)
    public static class AbstractBlockStateMixin {

        @Inject(method = "getHardness", at = @At("HEAD"), cancellable = true)
        private void getHardness(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_HARDNESS)) {
                cir.setReturnValue(0.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_ONE_HARDNESS)) {
                cir.setReturnValue(0.1F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_TWO_HARDNESS)) {
                cir.setReturnValue(0.2F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_THREESEVEN_HARDNESS)) {
                cir.setReturnValue(0.37F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_FOUR_HARDNESS)) {
                cir.setReturnValue(0.4F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_FIVE_HARDNESS)) {
                cir.setReturnValue(0.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SIX_HARDNESS)) {
                cir.setReturnValue(0.6F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SIXFIVE_HARDNESS)) {
                cir.setReturnValue(0.65F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_SEVEN_HARDNESS)) {
                cir.setReturnValue(0.7F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ZERO_EIGHT_HARDNESS)) {
                cir.setReturnValue(0.8F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_ZERO_HARDNESS)) {
                cir.setReturnValue(1.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_THREE_HARDNESS)) {
                cir.setReturnValue(1.3F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_FOUR_HARDNESS)) {
                cir.setReturnValue(1.4F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_FIVE_HARDNESS)) {
                cir.setReturnValue(1.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.ONE_SIX_HARDNESS)) {
                cir.setReturnValue(1.6F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TWO_ZERO_HARDNESS)) {
                cir.setReturnValue(2.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TWO_FIVE_HARDNESS)) {
                cir.setReturnValue(2.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.THREE_ZERO_HARDNESS)) {
                cir.setReturnValue(3.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TEN_HARDNESS)) {
                cir.setReturnValue(10.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.TWENTY_FIVE_HARDNESS)) {
                cir.setReturnValue(25.0F);
            }
        }
    }

    @Mixin(AbstractFireBlock.class)
    public static class AbstractFireBlockMixin {

        /**
         * Makes the Fire Block work correctly when touching a {@code nether portal base block}.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        private static boolean shouldLightPortalAt(World world, BlockPos pos, Direction direction) {
            if (!isOverworldOrNether(world)) {
                return false;
            } else {
                BlockPos.Mutable mutable = pos.mutableCopy();
                boolean bl = false;
                Direction[] var5 = Direction.values();
                int var6 = var5.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    Direction direction2 = var5[var7];
                    if (world.getBlockState(mutable.set(pos).move(direction2)).isOf(Blocks.OBSIDIAN) || world.getBlockState(mutable.set(pos).move(direction2)).isOf(Blocks.CRYING_OBSIDIAN)) {
                        bl = true;
                        break;
                    }
                }

                if (!bl) {
                    return false;
                } else {
                    Direction.Axis axis = direction.getAxis().isHorizontal() ? direction.rotateYCounterclockwise().getAxis() : Direction.Type.HORIZONTAL.randomAxis(world.random);
                    return AreaHelper.getNewPortal(world, pos, axis).isPresent();
                }
            }
        }

        private static boolean isOverworldOrNether(World world) {
            return world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER;
        }
    }

    @Mixin(BeehiveBlock.class)
    public static class BeehiveBlockMixin {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
        private boolean onUse(ItemStack stack, Item item) {
            return ModHelper.fixBeehiveBlock(stack);
        }
    }

    @Mixin(Block.class)
    public static class BlockMixin {

        /**
         * Makes all entities take less fall damage.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
            entity.handleFallDamage(fallDistance, ModHelper.getFallDamageMultiplier(), DamageSource.FALL);
        }
    }

    @Mixin(OreBlock.class)
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

    @Mixin(PumpkinBlock.class)
    public static class PumpkinBlockMixin {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onUse(ItemStack stack, Item item) {
            return ModHelper.fixPumpkinBlock(stack);
        }
    }

    @Mixin(SpawnerBlock.class)
    public abstract static class SpawnerBlockMixin {

        @Inject(method = "onStacksDropped", at = @At("TAIL"))
        private void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                world.setBlockState(pos, Blocks.LAVA.getDefaultState());
            }
        }
    }

    @Mixin(TntBlock.class)
    public static class TntBlockMixin {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onUse(ItemStack stack, Item item) {
            return ModHelper.fixTntBlock(stack);
        }
    }

    @Mixin(TripwireBlock.class)
    public static class TripwireBlockMixin {

        @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onBreak(ItemStack stack, Item item) {
            return ModHelper.fixTripwireBlock(stack);
        }
    }

    @Mixin(EfficiencyEnchantment.class)
    public static class EfficiencyEnchantmentMixin {

        @Redirect(method = "isAcceptableItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean isAcceptableItem(ItemStack stack, Item item) {
            return ModHelper.fixEfficiencyEnchantment(stack);
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
        private int damageDuringSitting;

        @Shadow
        abstract boolean parentDamage(DamageSource source, float amount);

        @Shadow @Final
        private static TargetPredicate CLOSE_PLAYER_PREDICATE;

        @Shadow @Final
        private EnderDragonPart head;

        @Shadow @Final
        private PhaseManager phaseManager;

        /**
         * Changes the {@code Ender Dragon's} max health.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createEnderDragonAttributes() {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getEnderDragonMaxHealth());
        }

        /**
         * Makes the {@code Ender Dragon} heal slower when connected to an end crystal.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        private void tickWithEndCrystals() {
            if (this.connectedCrystal != null) {
                if (this.connectedCrystal.isRemoved()) {
                    this.connectedCrystal = null;
                } else if (this.age % 10 == 0 && this.getHealth() < this.getMaxHealth()) {
                    this.setHealth(this.getHealth() + ModHelper.getEndCrystalHealAmount());
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

        /**
         * Makes the {@code Ender Dragon} do less damage to it's attacking entities.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        private void damageLivingEntities(List<Entity> entities) {
            Iterator var2 = entities.iterator();

            while(var2.hasNext()) {
                Entity entity = (Entity)var2.next();
                if (entity instanceof LivingEntity) {
                    entity.damage(DamageSource.mob(this), ModHelper.getDragonDamageAmount());
                    this.applyDamageEffects(this, entity);
                }
            }
        }

        /**
         * Allows the {@code Ender Dragon} to be able to be damaged more during it's sitting phase.
         * @author Dilloney
         * @reason
         */
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
                            if ((float)this.damageDuringSitting > ModHelper.getDragonDamageDuringSittingAmount() * this.getMaxHealth()) {
                                this.damageDuringSitting = 0;
                                this.phaseManager.setPhase(PhaseType.TAKEOFF);
                            }
                        }
                    }

                    return true;
                }
            }
        }

        /**
         * Makes the {@code Ender Dragon} take much more damage from broken and connected end crystals.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void crystalDestroyed(EndCrystalEntity crystal, BlockPos pos, DamageSource source) {
            PlayerEntity playerEntity2;
            if (source.getAttacker() instanceof PlayerEntity) {
                playerEntity2 = (PlayerEntity)source.getAttacker();
            } else {
                playerEntity2 = this.world.getClosestPlayer(CLOSE_PLAYER_PREDICATE, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
            }

            if (crystal == this.connectedCrystal) {
                damagePart(this.head, DamageSource.explosion(playerEntity2), ModHelper.getEndCrystalDamageAmount());
            }

            this.phaseManager.getCurrent().crystalDestroyed(crystal, pos, source, playerEntity2);
        }
    }

    @Mixin(EnderDragonFight.class)
    public static class EnderDragonFightMixin {

        @Shadow @Final
        private ServerWorld world;

        @Inject(method = "createDragon", at = @At("RETURN"))
        private void createDragon(CallbackInfoReturnable cir) {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                GiantEntity giantEntity = (GiantEntity)EntityType.GIANT.create(this.world);
                giantEntity.refreshPositionAndAngles(0.0D, 96.0D, -0.0D, this.world.random.nextFloat() * 270.0F, 0.0F);
                this.world.spawnEntity(giantEntity);
            }
        }
    }

    @Mixin(WitherEntity.class)
    public static class WitherEntityMixin {

        /**
         * Changes the {@code Wither's} attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createWitherAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getWitherMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6000000238418579D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0D).add(EntityAttributes.GENERIC_ARMOR, 4.0D);
        }
    }

    @Mixin(AbstractSkeletonEntity.class)
    public static class AbstractSkeletonEntityMixin {

        /**
         * Changes the Abstract Skeletons generic movement speed.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createAbstractSkeletonAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, ModHelper.getAbstractSkeletonMovementSpeed());
        }
    }

    @Mixin(BlazeEntity.class)
    public static class BlazeEntityMixin {

        /**
         * Changes the Blazes attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createBlazeAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getBlazeAttackDamage()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, ModHelper.getBlazeFollowRange());
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
                                this.fireballCooldown = ModHelper.getBlazeFireballCooldownAmount();
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

    @Mixin(CaveSpiderEntity.class)
    public static class CaveSpiderEntityMixin extends SpiderEntity {

        public CaveSpiderEntityMixin(EntityType<? extends SpiderEntity> entityType, World world) {
            super(entityType, world);
        }

        /**
         * Makes the Cave Spider Entity give slowness if Doom Mode is enabled.
         * @author Dilloney
         * @reason
         */
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
                        ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, i * 20, 0), this);
                    }
                }

                if (target instanceof PlayerEntity && SpeedrunnerMod.OPTIONS.doomMode) {
                    ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
                }

                return true;
            } else {
                return false;
            }
        }
    }

    @Mixin(CreeperEntity.class)
    public abstract static class CreeperEntityMixin extends HostileEntity implements SkinOverlayOwner {

        public CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Shadow
        int explosionRadius;

        @Shadow
        abstract void ignite();

        /**
         * Changes the Creepers generic movement speed.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createCreeperAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, ModHelper.getCreeperMovementSpeed());
        }

        /**
         * Allows speedrunner flint and steels to work on creepers + some doom mode stuff :)
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public ActionResult interactMob(PlayerEntity player, Hand hand) {
            ItemStack itemStack = player.getStackInHand(hand);
            Explosion.DestructionType destructionType = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
            float f = this.shouldRenderOverlay() ? 2.0F : 1.0F;
            if (ModHelper.getCreeperIgnitions(player, hand)) {
                this.world.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
                if (!this.world.isClient && SpeedrunnerMod.OPTIONS.doomMode) {
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
    }

    @Mixin(ElderGuardianEntity.class)
    public static class ElderGuardianEntityMixin extends GuardianEntity {

        public ElderGuardianEntityMixin(EntityType<? extends GuardianEntity> entityType, World world) {
            super(entityType, world);
        }

        /**
         * Changes the Elder Guardians attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createElderGuardianAttributes() {
            return GuardianEntity.createGuardianAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getElderGuardianAttackDamage()).add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getElderGuardianMaxHealth());
        }

        /**
         * Makes the {@code Elder Guardian} take a whole 5 minutes to check if the player has mining fatigue. If it doesn't the effect, it will only be applied for 30 seconds. The player also has to be in a 25 block radius for the guardian to check.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void mobTick() {
            super.mobTick();
            if ((this.age + this.getId()) % ModHelper.getElderGuardianAgeAmount() == 0) {
                StatusEffect statusEffect = StatusEffects.MINING_FATIGUE;
                List<ServerPlayerEntity> list = ((ServerWorld)this.world).getPlayers((serverPlayerEntityx) -> {
                    return this.squaredDistanceTo(serverPlayerEntityx) < ModHelper.getElderGuardianSquaredDistanceTo() && serverPlayerEntityx.interactionManager.isSurvivalLike();
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
                    serverPlayerEntity.addStatusEffect(new StatusEffectInstance(statusEffect, ModHelper.getElderGuardianMiningFatigueAmount(), 2));
                }
            }

            if (!this.hasPositionTarget()) {
                this.setPositionTarget(this.getBlockPos(), 16);
            }
        }
    }

    @Mixin(EndermanEntity.class)
    public static class EndermanEntityMixin {

        /**
         * Changes the {@code Enderman's} attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createEndermanAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getEndermanMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getEndermanAttackDamage()).add(EntityAttributes.GENERIC_FOLLOW_RANGE, ModHelper.getEndermanFollowRange());
        }
    }

    @Mixin(EndermiteEntity.class)
    public static class EndermiteEntityMixin {

        /**
         * Changes the {@code Endermite's} attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createEndermiteAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.01D);
        }
    }

    @Mixin(GhastEntity.class)
    public static class GhastEntityMixin {

        /**
         * Changes the {@code Ghast} attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createGhastAttributes() {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getGhastMaxHealth()).add(EntityAttributes.GENERIC_FOLLOW_RANGE, ModHelper.getGhastFollowRange());
        }

        @Mixin(GhastEntity.ShootFireballGoal.class)
        public static class ShootFireballGoalMixin {

            @Shadow @Final
            private GhastEntity ghast;

            @Shadow
            private int cooldown;

            /**
             * Kills ghasts when they throw a fireball
             * @author Dilloney
             * @reason
             */
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
                        if (SpeedrunnerMod.MISC_OPTIONS.killGhastUponFireball) {
                            this.ghast.kill();
                        }
                        this.cooldown = ModHelper.getGhastFireballCooldown();
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
            }
        }

        /**
         * Changes the Giants attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createGiantAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getGiantMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, ModHelper.getGiantMovementSpeed()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getGiantAttackDamage()).add(EntityAttributes.GENERIC_ARMOR, 1.2D).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, ModHelper.getGiantAttackKnockback());
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
            }
        }

        public void attemptTickInVoid() {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
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
                    this.discard();
                } else {
                    this.despawnCounter = 0;
                }
            }
        }

        public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
            return !SpeedrunnerMod.OPTIONS.doomMode;
        }

        public boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source) {
            return !SpeedrunnerMod.OPTIONS.doomMode;
        }

        public boolean isFireImmune() {
            return SpeedrunnerMod.OPTIONS.doomMode;
        }

        public boolean isImmuneToExplosion() {
            return SpeedrunnerMod.OPTIONS.doomMode;
        }

        public boolean canStartRiding(Entity entity) {
            return SpeedrunnerMod.OPTIONS.doomMode ? false : super.canStartRiding(entity);
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
            return ModHelper.getGiantSoundVolume();
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

    @Mixin(GuardianEntity.class)
    public static class GuardianEntityMixin {

        /**
         * Changes the {@code Guardian's} health.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createGuardianAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getGuardianAttackDamage()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, ModHelper.getGuardianFollowRange()).add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getGuardianMaxHealth());
        }
    }

    @Mixin(HoglinEntity.class)
    public abstract static class HoglinEntityMixin {

        /**
         * Changes the {@code Hoglin's} health.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createHoglinAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getHoglinMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, ModHelper.getHoglinKnockbackResistance()).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, ModHelper.getHoglinAttackKnockback()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getHoglinAttackDamage());
        }
    }

    @Mixin(MagmaCubeEntity.class)
    public static class MagmaCubeEntityMixin extends SlimeEntity {

        public MagmaCubeEntityMixin(EntityType<? extends SlimeEntity> entityType, World world) {
            super(entityType, world);
        }

        /**
         * Changes how fast a {@code Magma Cube} can jump per tick.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public int getTicksUntilNextJump() {
            return ModHelper.getMagmaCubeTicksUntilNextJumpAmount();
        }

        /**
         * Changes how much damage a {@code Magma Cube} will do.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public float getDamageAmount() {
            return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * ModHelper.getMagmaCubeAttackDamage();
        }

        /**
         * Makes {@code Magma Cubes} drop magma cream when their size is <=2.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public Identifier getLootTableId() {
            return this.getSize() <= 2 ? this.getType().getLootTableId() : LootTables.EMPTY;
        }
    }

    @Mixin(PiglinBrain.class)
    public static class PiglinBrainMixin {

        /**
         * Changes the max distance a {@code Piglin} can be scared by a {@code Zombified Piglin}.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        private static boolean getNearestZombifiedPiglin(PiglinEntity piglin) {
            Brain<PiglinEntity> brain = piglin.getBrain();
            if (brain.hasMemoryModule(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED)) {
                LivingEntity livingEntity = (LivingEntity)brain.getOptionalMemory(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED).get();
                return piglin.isInRange(livingEntity, 2.0D);
            } else {
                return false;
            }
        }

        @Inject(method = "wearsGoldArmor(Lnet/minecraft/entity/LivingEntity;)Z", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        private static void wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir, Iterable<ItemStack> iterable, Iterator iterator, ItemStack stack, Item item) {
            if (ModHelper.getPiglinSafeArmor(stack)) {
                cir.setReturnValue(true);
            }
        }

        @Inject(method = "acceptsForBarter(Lnet/minecraft/item/ItemStack;)Z", at = @At("RETURN"), cancellable = true)
        private static void acceptsForBarter(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
            if (stack.isOf(Items.RAW_GOLD)) {
                cir.setReturnValue(true);
            }
        }
    }

    @Mixin(PiglinEntity.class)
    public abstract static class PiglinEntityMixin extends AbstractPiglinEntity {

        public PiglinEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
            super(entityType, world);
        }

        @Shadow
        abstract void equipAtChance(EquipmentSlot slot, ItemStack stack);

        /**
         * Changes the {@code Piglin's} attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createPiglinAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getPiglinMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getPiglinAttackDamage());
        }

        /**
         * Allows piglins to spawn with golden speedrunner armor.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void initEquipment(LocalDifficulty difficulty) {
            if (this.isAdult()) {
                if (SpeedrunnerMod.OPTIONS.doomMode) {
                    this.equipStack(EquipmentSlot.HEAD, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_HELMET));
                    this.equipStack(EquipmentSlot.CHEST, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE));
                    this.equipStack(EquipmentSlot.LEGS, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS));
                    this.equipStack(EquipmentSlot.FEET, new ItemStack(ModItems.GOLDEN_SPEEDRUNNER_BOOTS));
                } else {
                    this.equipAtChance(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
                    this.equipAtChance(EquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
                    this.equipAtChance(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
                    this.equipAtChance(EquipmentSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS));
                }
            }
        }
    }

    @Mixin(PillagerEntity.class)
    public static class PillagerEntityMixin {

        /**
         * Changes the pillagers attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createPillagerAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getPillagerMaxHealth()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getPillagerAttackDamage()).add(EntityAttributes.GENERIC_FOLLOW_RANGE, ModHelper.getPillagerFollowRange());
        }
    }

    @Mixin(RavagerEntity.class)
    public static class RavagerEntityMixin {

        /**
         * Changes the {@code Ravager's} attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createRavagerAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getRavagerMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.75D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getRavagerAttackDamage()).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, ModHelper.getRavagerAttackKnockback()).add(EntityAttributes.GENERIC_FOLLOW_RANGE, ModHelper.getRavagerFollowRange());
        }

        @Inject(method = "tryAttack", at = @At("RETURN"))
        private void tryAttack(Entity target, CallbackInfoReturnable cir) {
            if (target instanceof PlayerEntity && SpeedrunnerMod.OPTIONS.doomMode) {
                ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
            }
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
        abstract void spawnNewShulker();

        /**
         * Changes the Shulker's attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createShulkerAttributes() {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getShulkerMaxHealth());
        }

        /**
         * Prevents shulkers from randomly teleporting and makes shulkers only have a 25% chance to deflect incoming projectile sources when they are closed.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public boolean damage(DamageSource source, float amount) {
            Entity entity2;
            if (this.isClosed()) {
                entity2 = source.getSource();
                if (SpeedrunnerMod.OPTIONS.doomMode) {
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

        static {
            COVERED_ARMOR_BONUS = new EntityAttributeModifier(COVERED_ARMOR_BONUS_ID, "Covered armor bonus", 10.0D, EntityAttributeModifier.Operation.ADDITION);
        }
    }

    @Mixin(SilverfishEntity.class)
    public static class SilverfishEntityMixin {

        /**
         * Changes the {@code Silverfishes} attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createSilverfishAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.01D);
        }

        @Mixin(SilverfishEntity.CallForHelpGoal.class)
        public static class CallForHelpGoalMixin {

            @Shadow int delay;

            /**
             * Changes how fast a silverfish can spawn in reinforcements.
             * @author Dilloney
             * @reason
             */
            @Overwrite
            public void onHurt() {
                if (this.delay == 0) {
                    this.delay = ModHelper.getSilverfishDelayAmount();
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

        /**
         * Changes how fast a {@code Slime} can jump per tick.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public int getTicksUntilNextJump() {
            return ModHelper.getSlimeTicksUntilNextJumpAmount();
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

        /**
         * Changes the Vexes attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createVexAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getVexMaxHealth()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getVexAttackDamage());
        }

        /**
         * Changes how the vex functions.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void tick() {
            this.noClip = !SpeedrunnerMod.OPTIONS.doomMode;
            super.tick();
            this.noClip = false;
            this.setNoGravity(true);
            if (this.alive && --this.lifeTicks <= 0) {
                this.lifeTicks = 20;
                this.damage(DamageSource.STARVE, ModHelper.getVexDecayAmount());
            }
        }

        public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
            return !SpeedrunnerMod.OPTIONS.doomMode;
        }
    }

    @Mixin(VindicatorEntity.class)
    public abstract static class VindicatorEntityMixin extends IllagerEntity {

        public VindicatorEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
            super(entityType, world);
        }

        /**
         * Changes the {@code Vindicator's} attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createVindicatorAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, ModHelper.getVindicatorFollowRange()).add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getVindicatorMaxHealth()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D);
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

    @Mixin(WitchEntity.class)
    public static class WitchEntityMixin {

        /**
         * Changes the {@code Witch's} attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createWitchAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getWitchMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, ModHelper.getWitchMovementSpeed());
        }
    }

    @Mixin(WitherSkeletonEntity.class)
    public abstract static class WitherSkeletonEntityMixin extends AbstractSkeletonEntity {

        public WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world) {
            super(entityType, world);
        }

        /**
         * Changes how much damage {@code Wither Skeleton's} will do.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityTag) {
            EntityData entityData2 = super.initialize(world, difficulty, spawnReason, entityData, entityTag);
            this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(ModHelper.getWitherSkeletonAttackDamage());
            this.updateAttackType();
            return entityData2;
        }

        /**
         * Makes the wither skeleton inflict the player with the wither effects for only 3 seconds.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public boolean tryAttack(Entity target) {
            if (!super.tryAttack(target)) {
                return false;
            } else {
                if (target instanceof LivingEntity) {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, ModHelper.getWitherSkeletonEffectDuration()), this);
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

        /**
         * Changes the {@code Zoglin's} health.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createZoglinAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getZoglinMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, ModHelper.getZoglinKnockbackResistance()).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, ModHelper.getZoglinAttackKnockback()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getZoglinAttackDamage());
        }
    }

    @Mixin(ZombieEntity.class)
    public static class ZombieEntityMixin extends HostileEntity {

        public ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        /**
         * Changes the {@code Zombie's} health.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createZombieAttributes() {
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, ModHelper.getZombieFollowRange()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, ModHelper.getZombieMovementSpeed()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getZombieAttackDamage()).add(EntityAttributes.GENERIC_ARMOR, ModHelper.getZombieGenericArmor()).add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
        }

        /**
         * Makes the zombies spawn with certain weapons
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void initEquipment(LocalDifficulty difficulty) {
            super.initEquipment(difficulty);
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
            }
            if (this.random.nextFloat() < (this.world.getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
                int i = this.random.nextInt(3);
                if (i == 0) {
                    this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
                } else {
                    this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
                }
            }
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

    @Mixin(ZombifiedPiglinEntity.class)
    public static class ZombifiedPiglinEntityMixin extends ZombieEntity {

        public ZombifiedPiglinEntityMixin(EntityType<? extends ZombieEntity> entityType, World world) {
            super(entityType, world);
        }

        /**
         * Changes the {@code Zombified Piglin's} health.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createZombifiedPiglinAttributes() {
            return ZombieEntity.createZombieAttributes().add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS, ModHelper.getZombifiedPiglinSpawnReinforcements()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, ModHelper.getZombifiedPiglinMovementSpeed()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getZombifiedPiglinAttackDamage());
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
        private static final TargetPredicate CLOSE_PLAYER_PREDICATE;

        static {
            CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(20.0D).ignoreVisibility();
        }

        @Mixin(DolphinEntity.SwimWithPlayerGoal.class)
        public static class SwimWithPlayerGoalMixin {

            @Shadow @Final
            private DolphinEntity dolphin;

            @Shadow
            private PlayerEntity closestPlayer;

            /**
             * Makes the Dolphin Entity give dolphins grace for a longer period of time.
             * @author Dilloney
             * @reason
             */
            @Overwrite
            public void start() {
                this.closestPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200), this.dolphin);
            }
        }
    }

    @Mixin(IronGolemEntity.class)
    public static class IronGolemEntityMixin {

        /**
         * Changes the {@code Iron Golem's} attributes.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static DefaultAttributeContainer.Builder createIronGolemAttributes() {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, ModHelper.getIronGolemMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, ModHelper.getIronGolemMovementSpeed()).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, ModHelper.getIronGolemKnockbackResistance()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, ModHelper.getIronGolemAttackDamage());
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

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean interactMob(ItemStack stack, Item item) {
            return ModHelper.fixShearingSheep(stack);
        }
    }

    @Mixin(SnowGolemEntity.class)
    public static class SnowGolemEntityMixin {

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean interactMob(ItemStack stack, Item item) {
            return ModHelper.fixShearingSnowGolem(stack);
        }
    }

    @Mixin(PlayerEntity.class)
    public abstract static class PlayerEntityMixin extends LivingEntity {

        public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
            super(entityType, world);
        }

        @Shadow
        abstract ItemCooldownManager getItemCooldownManager();

        @Inject(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getItemCooldownManager()Lnet/minecraft/entity/player/ItemCooldownManager;"))
        private void disableShield(CallbackInfo ci) {
            this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 80);
        }

        @Redirect(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean damageShield(ItemStack stack, Item item) {
            return ModHelper.fixShields(stack);
        }

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
            boolean bl = stack.isOf(ModItems.SPEEDRUNNER_BULK);
            if (stack.isOf(Items.GOLDEN_CARROT) || stack.isOf(Items.GOLDEN_APPLE) || stack.isOf(Items.ENCHANTED_GOLDEN_APPLE) || stack.isOf(ModItems.SPEEDRUNNER_BULK)) {
                this.removeStatusEffect(StatusEffects.POISON);
                if (bl) {
                    this.heal(2.0F);
                }
            }
        }

        @Inject(method = "travel", at = @At("TAIL"))
        private void travel(Vec3d movementInput, CallbackInfo ci) {
            if (this.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.SPEEDRUNNER_BOOTS) || this.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.GOLDEN_SPEEDRUNNER_BOOTS)) {
                FluidState fluidState = this.world.getFluidState(this.getBlockPos());
                if (this.isInLava() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid())) {
                    this.updateVelocity(0.025F, movementInput);
                    if (!this.hasNoGravity()) {
                        this.setVelocity(this.getVelocity().add(0.0D, -0.08D / 4.0D, 0.0D));
                    }
                    if (this.getRandom().nextFloat() < 0.01F) {
                        this.getEquippedStack(EquipmentSlot.FEET).damage(1, this, (livingEntity) -> {
                            livingEntity.sendEquipmentBreakStatus(EquipmentSlot.FEET);
                        });
                    }
                } else if (this.isTouchingWater() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid())) {
                    this.updateVelocity(0.004F, movementInput);
                    if (this.getRandom().nextFloat() < 0.01F) {
                        this.getEquippedStack(EquipmentSlot.FEET).damage(1, this, (livingEntity) -> {
                            livingEntity.sendEquipmentBreakStatus(EquipmentSlot.FEET);
                        });
                    }
                }
            }
        }

        protected void swimUpward(Tag<Fluid> fluid) {
            if (this.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.SPEEDRUNNER_BOOTS) && this.isInLava() || this.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.GOLDEN_SPEEDRUNNER_BOOTS) && this.isInLava()) {
                this.setVelocity(this.getVelocity().add(0.0D, 0.07999999910593033D, 0.0D));
            } else {
                this.setVelocity(this.getVelocity().add(0.0D, 0.03999999910593033D, 0.0D));
            }
        }
    }

    @Mixin(EnderPearlEntity.class)
    public abstract static class EnderPearlEntityMixin extends ThrownItemEntity {

        public EnderPearlEntityMixin(EntityType<? extends EnderPearlEntity> entityType, World world) {
            super(entityType, world);
        }

        /**
         * Makes the Ender Pearl entity work correctly when {@code InfiniPearlMode} is {@code ON}. Also makes ender pearls do less damage to the entity when colliding onto a block.
         * @author Dilloney
         * @reason
         */
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
                            if (SpeedrunnerMod.OPTIONS.doomMode) {
                                if (!serverPlayerEntity.isCreative() || !serverPlayerEntity.isSpectator()) {
                                    ((ServerPlayerEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
                                }
                            }
                            entity.damage(DamageSource.FALL, ModHelper.getEnderPearlDamage());
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

    @Mixin(SmallFireballEntity.class)
    public static class SmallFireballEntityMixin extends AbstractFireballEntity {

        public SmallFireballEntityMixin(EntityType<? extends AbstractFireballEntity> entityType, World world) {
            super(entityType, world);
        }

        /**
         * Makes {@code Small Fireballs} do less damage to it's targeted entity.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void onEntityHit(EntityHitResult entityHitResult) {
            super.onEntityHit(entityHitResult);
            if (!this.world.isClient) {
                Entity entity = entityHitResult.getEntity();
                if (!entity.isFireImmune()) {
                    Entity entity2 = this.getOwner();
                    int i = entity.getFireTicks();
                    entity.setOnFireFor(ModHelper.getSmallFireballEntityFireTicks());
                    boolean bl = entity.damage(DamageSource.fireball(this, entity2), ModHelper.getSmallFireballDamageAmount());
                    if (!bl) {
                        entity.setFireTicks(i);
                    } else if (entity2 instanceof LivingEntity) {
                        this.applyDamageEffects((LivingEntity)entity2, entity);
                    }
                }
            }
        }
    }

    @Mixin(Entity.class)
    public static class EntityMixin {

        /**
         * Lowers the amount of damage taken when touching lava.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void setOnFireFromLava() {
            Entity entity = (Entity)(Object)this;
            if (!entity.isFireImmune()) {
                entity.setOnFireFor(ModHelper.getLavaFireTicks());
                if (entity.damage(DamageSource.LAVA, ModHelper.getLavaDamageAmount())) {
                    entity.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + entity.world.random.nextFloat() * 0.4F);
                }
            }
        }
    }

    @Mixin(EyeOfEnderEntity.class)
    public abstract static class EyeOfEnderEntityMixin extends Entity implements FlyingItemEntity {

        public EyeOfEnderEntityMixin(EntityType<? extends EyeOfEnderEntity> type, World world) {
            super(type, world);
        }

        @Shadow
        private double targetX, targetY, targetZ;

        @Shadow
        private int lifespan;

        /**
         * Changes how the {@code Eye of Ender} entity functions.
         * @author Dilloney
         * @reason
         */
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
                if (this.lifespan > SpeedrunnerMod.MISC_OPTIONS.enderEyesLifespan && !this.world.isClient) {
                    this.discard();
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
    }

    @Mixin(ItemEntity.class)
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

    @Mixin(LivingEntity.class)
    public static class LivingEntityMixin {

        @Redirect(method = "getPreferredEquipmentSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 2))
        private static boolean getPreferredEquipmentSlot(ItemStack stack, Item item) {
            return stack.isOf(Items.SHIELD) || stack.isOf(ModItems.SPEEDRUNNER_SHIELD);
        }
    }

    @Mixin(TntEntity.class)
    public abstract static class TntEntityMixin extends Entity {

        public TntEntityMixin(EntityType<?> type, World world) {
            super(type, world);
        }

        public boolean canExplosionDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float explosionPower) {
            return !(this.world instanceof ServerWorld) || this.world.getRegistryKey() != World.END || !SpeedrunnerMod.OPTIONS.doomMode;
        }
    }

    @Mixin(EnderPearlItem.class)
    public static class EnderPearlItemMixin extends Item {

        public EnderPearlItemMixin(Settings settings) {
            super(settings);
        }

        /**
         * Makes the Ender Pearl Item work correctly when {@code Infinity Pearl} Mode is {@code ON}.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            ItemStack itemStack = user.getStackInHand(hand);
            boolean bl = EnchantmentHelper.getLevel(Enchantments.INFINITY, itemStack) > 0;
            world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.random.nextFloat() * 0.4F + 0.8F));
            user.getItemCooldownManager().set(this, 20);
            if (!world.isClient) {
                EnderPearlEntity enderPearlEntity = new EnderPearlEntity(world, user);
                enderPearlEntity.setItem(itemStack);
                enderPearlEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(enderPearlEntity);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.getAbilities().creativeMode && !bl) {
                itemStack.decrement(1);
            }

            return TypedActionResult.success(itemStack, world.isClient());
        }
    }

    @Mixin(FoodComponents.class)
    public static class FoodComponentsMixin {

        @Shadow
        private static final FoodComponent APPLE, BAKED_POTATO, BEEF, BEETROOT, BREAD, CARROT, CHICKEN, CHORUS_FRUIT, COD, COOKED_BEEF, COOKED_CHICKEN, COOKED_COD, COOKED_MUTTON, COOKED_PORKCHOP, COOKED_RABBIT, COOKED_SALMON, COOKIE, DRIED_KELP, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE, GOLDEN_CARROT, HONEY_BOTTLE, MELON_SLICE, MUTTON, POISONOUS_POTATO, PORKCHOP, POTATO, PUFFERFISH, PUMPKIN_PIE, RABBIT, ROTTEN_FLESH, SALMON, SPIDER_EYE, SWEET_BERRIES, GLOW_BERRIES, TROPICAL_FISH;

        static {
            if (SpeedrunnerMod.MISC_OPTIONS.modifiedSaturationAndHungerValues) {
                APPLE = new FoodComponent.Builder().hunger(4).saturationModifier(0.8F).build();
                BAKED_POTATO = new FoodComponent.Builder().hunger(6).saturationModifier(0.9F).build();
                BEEF = new FoodComponent.Builder().hunger(4).saturationModifier(0.7F).meat().build();
                BEETROOT = new FoodComponent.Builder().hunger(2).saturationModifier(1.4F).build();
                BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(1.1F).build();
                CARROT = new FoodComponent.Builder().hunger(3).saturationModifier(1.2F).build();
                CHICKEN = new FoodComponent.Builder().hunger(2).saturationModifier(1.2F).build();
                CHORUS_FRUIT = new FoodComponent.Builder().hunger(4).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).alwaysEdible().build();
                COD = new FoodComponent.Builder().hunger(2).saturationModifier(1.2F).build();
                COOKED_BEEF = new FoodComponent.Builder().hunger(8).saturationModifier(0.9F).meat().build();
                COOKED_CHICKEN = new FoodComponent.Builder().hunger(6).saturationModifier(0.9F).meat().build();
                COOKED_COD = new FoodComponent.Builder().hunger(5).saturationModifier(1.1F).build();
                COOKED_MUTTON = new FoodComponent.Builder().hunger(6).saturationModifier(0.9F).meat().build();
                COOKED_PORKCHOP = new FoodComponent.Builder().hunger(8).saturationModifier(0.9F).meat().build();
                COOKED_RABBIT = new FoodComponent.Builder().hunger(5).saturationModifier(1.1F).meat().build();
                COOKED_SALMON = new FoodComponent.Builder().hunger(6).saturationModifier(0.9F).build();
                COOKIE = new FoodComponent.Builder().hunger(2).saturationModifier(1.3F).build();
                DRIED_KELP = new FoodComponent.Builder().hunger(1).saturationModifier(0.6F).snack().build();
                ENCHANTED_GOLDEN_APPLE = new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), 1.0F).alwaysEdible().build();
                GOLDEN_APPLE = new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F).alwaysEdible().build();
                GOLDEN_CARROT = new FoodComponent.Builder().hunger(6).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).build();
                HONEY_BOTTLE = new FoodComponent.Builder().hunger(6).saturationModifier(0.5F).build();
                MELON_SLICE = new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).build();
                MUTTON = new FoodComponent.Builder().hunger(2).saturationModifier(0.8F).meat().build();
                POISONOUS_POTATO = new FoodComponent.Builder().hunger(2).saturationModifier(0.7F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.6F).build();
                PORKCHOP = new FoodComponent.Builder().hunger(4).saturationModifier(0.4F).meat().build();
                POTATO = new FoodComponent.Builder().hunger(1).saturationModifier(1.0F).build();
                PUFFERFISH = new FoodComponent.Builder().hunger(1).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 1200, 3), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0), 1.0F).build();
                PUMPKIN_PIE = new FoodComponent.Builder().hunger(8).saturationModifier(0.9F).build();
                RABBIT = new FoodComponent.Builder().hunger(3).saturationModifier(0.9F).meat().build();
                ROTTEN_FLESH = new FoodComponent.Builder().hunger(4).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 0), 0.8F).meat().build();
                SALMON = new FoodComponent.Builder().hunger(2).saturationModifier(1.4F).build();
                SPIDER_EYE = new FoodComponent.Builder().hunger(2).saturationModifier(1.1F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 1.0F).build();
                SWEET_BERRIES = new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).build();
                GLOW_BERRIES = new FoodComponent.Builder().hunger(2).saturationModifier(1.0F).build();
                TROPICAL_FISH = new FoodComponent.Builder().hunger(2).saturationModifier(1.2F).build();
            } else {
                APPLE = new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build();
                BAKED_POTATO = new FoodComponent.Builder().hunger(5).saturationModifier(0.6F).build();
                BEEF = new FoodComponent.Builder().hunger(3).saturationModifier(0.3F).meat().build();
                BEETROOT = new FoodComponent.Builder().hunger(1).saturationModifier(0.6F).build();
                BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(0.6F).build();
                CARROT = new FoodComponent.Builder().hunger(3).saturationModifier(0.6F).build();
                CHICKEN = new FoodComponent.Builder().hunger(2).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.3F).meat().build();
                CHORUS_FRUIT = new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).alwaysEdible().build();
                COD = new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build();
                COOKED_BEEF = new FoodComponent.Builder().hunger(8).saturationModifier(0.8F).meat().build();
                COOKED_CHICKEN = new FoodComponent.Builder().hunger(6).saturationModifier(0.6F).meat().build();
                COOKED_COD = new FoodComponent.Builder().hunger(5).saturationModifier(0.6F).build();
                COOKED_MUTTON = new FoodComponent.Builder().hunger(6).saturationModifier(0.8F).meat().build();
                COOKED_PORKCHOP = new FoodComponent.Builder().hunger(8).saturationModifier(0.8F).meat().build();
                COOKED_RABBIT = new FoodComponent.Builder().hunger(5).saturationModifier(0.6F).meat().build();
                COOKED_SALMON = new FoodComponent.Builder().hunger(6).saturationModifier(0.8F).build();
                COOKIE = new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build();
                DRIED_KELP = new FoodComponent.Builder().hunger(1).saturationModifier(0.3F).snack().build();
                ENCHANTED_GOLDEN_APPLE = new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), 1.0F).alwaysEdible().build();
                GOLDEN_APPLE = new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F).alwaysEdible().build();
                GOLDEN_CARROT = new FoodComponent.Builder().hunger(6).saturationModifier(1.2F).build();
                HONEY_BOTTLE = new FoodComponent.Builder().hunger(6).saturationModifier(0.1F).build();
                MELON_SLICE = new FoodComponent.Builder().hunger(2).saturationModifier(0.3F).build();
                MUTTON = new FoodComponent.Builder().hunger(2).saturationModifier(0.3F).meat().build();
                POISONOUS_POTATO = new FoodComponent.Builder().hunger(2).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.6F).build();
                PORKCHOP = new FoodComponent.Builder().hunger(3).saturationModifier(0.3F).meat().build();
                POTATO = new FoodComponent.Builder().hunger(1).saturationModifier(0.3F).build();
                PUFFERFISH = new FoodComponent.Builder().hunger(1).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 1200, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0), 1.0F).build();
                PUMPKIN_PIE = new FoodComponent.Builder().hunger(8).saturationModifier(0.3F).build();
                RABBIT = new FoodComponent.Builder().hunger(3).saturationModifier(0.3F).meat().build();
                ROTTEN_FLESH = new FoodComponent.Builder().hunger(4).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.8F).meat().build();
                SALMON = new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build();
                SPIDER_EYE = new FoodComponent.Builder().hunger(2).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 1.0F).build();
                SWEET_BERRIES = new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build();
                GLOW_BERRIES = new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build();
                TROPICAL_FISH = new FoodComponent.Builder().hunger(1).saturationModifier(0.1F).build();
            }
        }
    }

    @Deprecated
    @Mixin(ItemPredicate.class)
    public static class ItemPredicateMixin {

        @ModifyVariable(method = "test", at = @At("HEAD"))
        public ItemStack makeSpeedrunnerItemsWorkLikeVanillaItems(ItemStack stack) {
            if (stack.getItem().getDefaultStack().isOf(ModItems.SPEEDRUNNER_SHEARS)) {
                ItemStack itemStack = new ItemStack(Items.SHEARS);
                itemStack.setCount(stack.getCount());
                itemStack.setTag(stack.getOrCreateTag());
                return itemStack;
            }

            return stack;
        }
    }

    @Mixin(ShapelessRecipe.class)
    public static class ShapelessRecipeMixin {

        @Shadow @Final
        private Identifier id;

        @Inject(method = "matches", at = @At("HEAD"), cancellable = true)
        private void matches(CraftingInventory craftingInventory, World world, CallbackInfoReturnable<Boolean> cir) {
            if (id.toString().equals("minecraft:ender_eye") || id.toString().equals("speedrunnermod:inferno_eye") || id.toString().equals("speedrunnermod:annul_eye")) {
                for (int i = 0; i < craftingInventory.size(); i++) {
                    ItemStack itemStack = craftingInventory.getStack(i);
                    if (itemStack.hasEnchantments()) {
                        cir.setReturnValue(false);
                    }
                }
            }
        }
    }

    @Mixin(ServerPlayerEntity.class)
    public abstract static class ServerPlayerEntityMixin extends PlayerEntity {

        public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
            super(world, pos, yaw, profile);
        }

        @Shadow @Final
        private ServerStatHandler statHandler;

        @Inject(method = "<init>", at = @At("TAIL"))
        private void init(CallbackInfo info) throws CommandSyntaxException {
            if (this.statHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_TIME)) == 0) {
                ItemStack item;
                if (SpeedrunnerMod.OPTIONS.iCarusMode) {
                    item = this.itemStackFromString("minecraft:elytra{Unbreakable:1b}", 1);
                    ItemStack item2 = this.itemStackFromString("minecraft:firework_rocket{Fireworks:{Flight:3b}}", 64);
                    this.getInventory().armor.set(2, item);
                    this.getInventory().main.set(0, item2);
                }

                if (SpeedrunnerMod.OPTIONS.infiniPearlMode) {
                    item = new ItemStack(Items.ENDER_PEARL, 1);
                    item.addEnchantment(Enchantments.INFINITY, 1);
                    item.getOrCreateTag().putInt("HideFlags", 1);

                    LiteralText text = new LiteralText("InfiniPearl™");
                    text.setStyle(text.getStyle().withItalic(false));
                    item.setCustomName(text);

                    if (!SpeedrunnerMod.OPTIONS.iCarusMode) {
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


    @Mixin(TradeOffers.EnchantBookFactory.class)
    public static class EnchantBookFactoryMixin {

        @Shadow @Final
        private int experience;

        /**
         * Lowers the price of enchanted books from villager trading.
         * @author Dilloney
         * @reason
         */
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
     * See {@linkplain BiomeIds} for more.
     */
    @Mixin(value = AddBaseBiomesLayer.class, priority = 999)
    public static class AddBaseBiomesLayerMixin {

        @Shadow
        private static final int[] DRY_BIOMES;

        @Shadow
        private static final int[] TEMPERATE_BIOMES;

        @Shadow
        private static final int[] COOL_BIOMES;

        @Shadow
        private static final int[] SNOWY_BIOMES;

        static {
            if (SpeedrunnerMod.MISC_OPTIONS.makeBiomesMoreCommon) {
                DRY_BIOMES = new int[]{2, 2, 2, 35, 35, 35, 36, 1, 1, 2, 35, 35, 1, 1, 2, 35, 2, 1, 35, 35, 2};
                TEMPERATE_BIOMES = new int[]{4, 29, 2, 2, 2, 1, 1, 35, 35, 2, 35, 1};
                COOL_BIOMES = new int[]{4, 5, 6, 1, 2, 2, 35};
                SNOWY_BIOMES = new int[]{12, 30, 3, 2, 1, 35, 2};
            } else {
                DRY_BIOMES = new int[]{2, 2, 2, 35, 35, 1};
                TEMPERATE_BIOMES = new int[]{4, 29, 3, 1, 27, 6};
                COOL_BIOMES = new int[]{4, 3, 5, 1};
                SNOWY_BIOMES = new int[]{12, 12, 12, 30};
            }
        }
    }

    @Mixin(DefaultBiomeCreator.class)
    public static class DefaultBiomeCreatorMixin {

        /**
         * Changes how the {@code Plains} biome generates.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static Biome createPlains(boolean sunflower) {
            SpawnSettings.Builder builder = new SpawnSettings.Builder();
            DefaultBiomeFeatures.addPlainsMobs(builder);
            if (!sunflower) {
                builder.playerSpawnFriendly();
            }

            net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
            builder2.structureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS).structureFeature(ConfiguredStructureFeatures.PILLAGER_OUTPOST);

            DefaultBiomeFeatures.addDefaultUndergroundStructures(builder2);
            builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
            DefaultBiomeFeatures.addLandCarvers(builder2);
            DefaultBiomeFeatures.addDefaultLakes(builder2);
            DefaultBiomeFeatures.addAmethystGeodes(builder2);
            DefaultBiomeFeatures.addDungeons(builder2);
            DefaultBiomeFeatures.addPlainsTallGrass(builder2);
            if (sunflower) {
                builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_SUNFLOWER);
            }

            DefaultBiomeFeatures.addMineables(builder2);
            DefaultBiomeFeatures.addDefaultOres(builder2);
            DefaultBiomeFeatures.addDefaultDisks(builder2);
            DefaultBiomeFeatures.addPlainsFeatures(builder2);
            if (sunflower) {
                builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_SUGAR_CANE);
            }

            DefaultBiomeFeatures.addDefaultMushrooms(builder2);
            if (sunflower) {
                builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_PUMPKIN);
            } else {
                DefaultBiomeFeatures.addDefaultVegetation(builder2);
            }

            DefaultBiomeFeatures.addSprings(builder2);
            DefaultBiomeFeatures.addFrozenTopLayer(builder2);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(0.8F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
        }

        /**
         * Lets end cities generate at the main End Islands if their spacing and separation values are low enough.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static Biome createTheEnd() {
            net.minecraft.world.biome.GenerationSettings.Builder builder;
            if (SpeedrunnerMod.WORLD_OPTIONS.getEndCitySpacing() <= 3 && SpeedrunnerMod.WORLD_OPTIONS.getEndCitySeparation() <= 1) {
                builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.END).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.END_SPIKE).structureFeature(ConfiguredStructureFeatures.END_CITY);
            } else {
                builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.END).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.END_SPIKE);
            }
            return composeEndSpawnSettings(builder);
        }

        /**
         * Changes how the {@code Forest} biomes generate.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        private static Biome createForest(float depth, float scale, boolean flower, SpawnSettings.Builder spawnSettings) {
            net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
            DefaultBiomeFeatures.addDefaultUndergroundStructures(builder);
            builder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL).structureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS);
            DefaultBiomeFeatures.addLandCarvers(builder);
            DefaultBiomeFeatures.addDefaultLakes(builder);
            DefaultBiomeFeatures.addAmethystGeodes(builder);
            DefaultBiomeFeatures.addDungeons(builder);
            if (flower) {
                builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.FOREST_FLOWER_VEGETATION_COMMON);
            } else {
                DefaultBiomeFeatures.addForestFlowers(builder);
            }

            DefaultBiomeFeatures.addMineables(builder);
            DefaultBiomeFeatures.addDefaultOres(builder);
            DefaultBiomeFeatures.addDefaultDisks(builder);
            if (flower) {
                builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.FOREST_FLOWER_TREES);
                builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.FLOWER_FOREST);
                DefaultBiomeFeatures.addDefaultGrass(builder);
            } else {
                DefaultBiomeFeatures.addForestTrees(builder);
                DefaultBiomeFeatures.addDefaultFlowers(builder);
                DefaultBiomeFeatures.addForestGrass(builder);
            }

            DefaultBiomeFeatures.addDefaultMushrooms(builder);
            DefaultBiomeFeatures.addDefaultVegetation(builder);
            DefaultBiomeFeatures.addSprings(builder);
            DefaultBiomeFeatures.addFrozenTopLayer(builder);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.MUSHROOM).depth(depth).scale(scale).temperature(0.7F).downfall(0.8F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(0.7F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(spawnSettings.build()).generationSettings(builder.build()).build();
        }

        /**
         * Changes how the {@code Mountain} biomes generate.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static Biome createMountains(float depth, float scale, ConfiguredSurfaceBuilder<TernarySurfaceConfig> surfaceBuilder, boolean extraTrees) {
            SpawnSettings.Builder builder = new SpawnSettings.Builder();
            DefaultBiomeFeatures.addFarmAnimals(builder);
            builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.LLAMA, 5, 4, 6));
            builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.GOAT, 10, 4, 6));
            DefaultBiomeFeatures.addBatsAndMonsters(builder);
            net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(surfaceBuilder);
            DefaultBiomeFeatures.addDefaultUndergroundStructures(builder2);
            builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_MOUNTAIN).structureFeature(ConfiguredStructureFeatures.VILLAGE_TAIGA);
            DefaultBiomeFeatures.addLandCarvers(builder2);
            DefaultBiomeFeatures.addDefaultLakes(builder2);
            DefaultBiomeFeatures.addAmethystGeodes(builder2);
            DefaultBiomeFeatures.addDungeons(builder2);
            DefaultBiomeFeatures.addMineables(builder2);
            DefaultBiomeFeatures.addDefaultOres(builder2);
            DefaultBiomeFeatures.addDefaultDisks(builder2);
            if (extraTrees) {
                DefaultBiomeFeatures.addExtraMountainTrees(builder2);
            } else {
                DefaultBiomeFeatures.addMountainTrees(builder2);
            }

            DefaultBiomeFeatures.addDefaultFlowers(builder2);
            DefaultBiomeFeatures.addDefaultGrass(builder2);
            DefaultBiomeFeatures.addDefaultMushrooms(builder2);
            DefaultBiomeFeatures.addDefaultVegetation(builder2);
            DefaultBiomeFeatures.addSprings(builder2);
            DefaultBiomeFeatures.addEmeraldOre(builder2);
            DefaultBiomeFeatures.addInfestedStone(builder2);
            DefaultBiomeFeatures.addFrozenTopLayer(builder2);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.EXTREME_HILLS).depth(depth).scale(scale).temperature(0.2F).downfall(0.3F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(0.2F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
        }

        /**
         * Changes how the {@code Nether Wastes} biome generates and what spawns.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static Biome createNetherWastes() {
            SpawnSettings spawnSettings;
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 20, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 50, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 100, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 20, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 100, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            } else {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 20, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 2, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 50, 2, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            }
            net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.NETHER).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
            DefaultBiomeFeatures.addDefaultMushrooms(builder);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED);
            DefaultBiomeFeatures.addNetherMineables(builder);
            return (new Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3344392).skyColor(getSkyColor(2.0F)).loopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_NETHER_WASTES)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }

        /**
         * Changes how the {@code Soul Sand Valley} generates and what spawns.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static Biome createSoulSandValley() {
            SpawnSettings spawnSettings;
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 4, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.SKELETON, 0.7D, 0.15D).spawnCost(EntityType.GHAST, 0.7D, 0.15D).spawnCost(EntityType.ENDERMAN, 0.7D, 0.15D).spawnCost(EntityType.STRIDER, 0.7D, 0.15D).build();
            } else {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 10, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 4, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.SKELETON, 0.7D, 0.15D).spawnCost(EntityType.GHAST, 0.7D, 0.15D).spawnCost(EntityType.ENDERMAN, 0.7D, 0.15D).spawnCost(EntityType.STRIDER, 0.7D, 0.15D).build();
            }
            net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.SOUL_SAND_VALLEY).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.NETHER_FOSSIL).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA).feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ConfiguredFeatures.BASALT_PILLAR).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_CRIMSON_ROOTS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_SOUL_SAND);
            DefaultBiomeFeatures.addNetherMineables(builder);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1787717).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.ASH, 0.00625F)).loopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_SOUL_SAND_VALLEY)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }

        /**
         * Changes how the {@code Basalt Deltas} biome generates and what spawns.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static Biome createBasaltDeltas() {
            SpawnSettings spawnSettings;
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 40, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            } else {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 25, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 25, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            }
            net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.BASALT_DELTAS).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).structureFeature(ConfiguredStructureFeatures.FORTRESS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.DELTA).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA_DOUBLE).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.SMALL_BASALT_COLUMNS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.LARGE_BASALT_COLUMNS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BASALT_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BLACKSTONE_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_DELTA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED_DOUBLE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_GOLD_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_QUARTZ_DELTAS);
            DefaultBiomeFeatures.addAncientDebris(builder);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(4341314).fogColor(6840176).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.118093334F)).loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_BASALT_DELTAS)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }

        /**
         * Changes how the {@code Crimson Forest} biome generates and what spawns.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static Biome createCrimsonForest() {
            SpawnSettings spawnSettings;
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 50, 4, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 2, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            } else {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 6, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 9, 2, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            }
            net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.CRIMSON_FOREST).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
            DefaultBiomeFeatures.addDefaultMushrooms(builder);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.WEEPING_VINES).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.CRIMSON_FUNGI).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.CRIMSON_FOREST_VEGETATION);
            DefaultBiomeFeatures.addNetherMineables(builder);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3343107).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.025F)).loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }

        /**
         * Changes how the {@code Warped Forest} biome generates and what spawns.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static Biome createWarpedForest() {
            SpawnSettings spawnSettings;
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 4, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.ENDERMAN, 1.0D, 0.12D).build();
            } else {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 5, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.ENDERMAN, 1.0D, 0.12D).build();
            }
            net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.WARPED_FOREST).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
            DefaultBiomeFeatures.addDefaultMushrooms(builder);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.WARPED_FUNGI).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.WARPED_FOREST_VEGETATION).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.NETHER_SPROUTS).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.TWISTING_VINES);
            DefaultBiomeFeatures.addNetherMineables(builder);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1705242).skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WARPED_SPORE, 0.01428F)).loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }

        private static int getSkyColor(float temperature) {
            float f = temperature / 3.0F;
            f = MathHelper.clamp(f, -1.0F, 1.0F);
            return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
        }

        private static Biome composeEndSpawnSettings(net.minecraft.world.biome.GenerationSettings.Builder builder) {
            SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
            DefaultBiomeFeatures.addEndMobs(builder2);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.THEEND).depth(0.1F).scale(0.2F).temperature(0.5F).downfall(0.5F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(10518688).skyColor(0).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder2.build()).generationSettings(builder.build()).build();
        }
    }

    @Mixin(AreaHelper.class)
    public static class AreaHelperMixin {

        @Shadow
        private static final AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK;

        static {
            IS_VALID_FRAME_BLOCK = (state, world, pos) -> {
                return state.isOf(Blocks.OBSIDIAN) || state.isOf(Blocks.CRYING_OBSIDIAN);
            };
        }
    }

    @Mixin(StrongholdConfig.class)
    public static class StrongholdConfigMixin {

        @Shadow @Final
        private int distance;

        @Shadow @Final
        private int count;

        /**
         * Changes the maximum distance a stronghold can generate.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public int getDistance() {
            return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 4 : this.distance;
        }

        /**
         * Changes the maximum amount of strongholds that can generate in a world.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public int getCount() {
            return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? SpeedrunnerMod.MISC_OPTIONS.getStrongholdCount() : this.count;
        }
    }

    @Mixin(StructuresConfig.class)
    public static class StructuresConfigMixin {

        @Shadow @Mutable
        private static final ImmutableMap<Object, Object> DEFAULT_STRUCTURES;

        static {
            if (SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon) {
                DEFAULT_STRUCTURES = ImmutableMap.builder().put(StructureFeature.VILLAGE, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getVillageSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getVillageSeparation(), 10387312)).put(StructureFeature.DESERT_PYRAMID, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getDesertPyramidSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getDesertPyramidSeparation(), 14357617)).put(StructureFeature.IGLOO, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getIglooSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getIglooSeparation(), 14357618)).put(StructureFeature.JUNGLE_PYRAMID, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getJunglePyramidSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getJunglePyramidSeparation(), 14357619)).put(StructureFeature.SWAMP_HUT, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getSwampHutSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getSwampHutSeparation(), 14357620)).put(StructureFeature.PILLAGER_OUTPOST, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getPillagerOutpostSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getPillagerOutpostSeparation(), 165745296)).put(StructureFeature.STRONGHOLD, new StructureConfig(1, 0, 0)).put(StructureFeature.MONUMENT, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getMonumentSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getMonumentSeparation(), 10387313)).put(StructureFeature.END_CITY, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getEndCitySpacing(), SpeedrunnerMod.WORLD_OPTIONS.getEndCitySeparation(), 10387313)).put(StructureFeature.MANSION, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getMansionSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getMansionSeparation(), 10387319)).put(StructureFeature.BURIED_TREASURE, new StructureConfig(1, 0, 0)).put(StructureFeature.MINESHAFT, new StructureConfig(1, 0, 0)).put(StructureFeature.RUINED_PORTAL, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getRuinedPortalSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getRuinedPortalSeparation(), 34222645)).put(StructureFeature.SHIPWRECK, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getShipwreckSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getShipwreckSeparation(), 165745295)).put(StructureFeature.OCEAN_RUIN, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getOceanRuinSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getOceanRuinSeparation(), 14357621)).put(StructureFeature.BASTION_REMNANT, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getBastionSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getBastionSeparation(), 30084232)).put(StructureFeature.FORTRESS, new StructureConfig(SpeedrunnerMod.WORLD_OPTIONS.getFortressSpacing(), SpeedrunnerMod.WORLD_OPTIONS.getFortressSeparation(), 30084232)).put(StructureFeature.NETHER_FOSSIL, new StructureConfig(2, 1, 14357921)).build();
            } else {
                DEFAULT_STRUCTURES = ImmutableMap.builder().put(StructureFeature.VILLAGE, new StructureConfig(32, 8, 10387312)).put(StructureFeature.DESERT_PYRAMID, new StructureConfig(32, 8, 14357617)).put(StructureFeature.IGLOO, new StructureConfig(32, 8, 14357618)).put(StructureFeature.JUNGLE_PYRAMID, new StructureConfig(32, 8, 14357619)).put(StructureFeature.SWAMP_HUT, new StructureConfig(32, 8, 14357620)).put(StructureFeature.PILLAGER_OUTPOST, new StructureConfig(32, 8, 165745296)).put(StructureFeature.STRONGHOLD, new StructureConfig(1, 0, 0)).put(StructureFeature.MONUMENT, new StructureConfig(32, 5, 10387313)).put(StructureFeature.END_CITY, new StructureConfig(20, 11, 10387313)).put(StructureFeature.MANSION, new StructureConfig(80, 20, 10387319)).put(StructureFeature.BURIED_TREASURE, new StructureConfig(1, 0, 0)).put(StructureFeature.MINESHAFT, new StructureConfig(1, 0, 0)).put(StructureFeature.RUINED_PORTAL, new StructureConfig(40, 15, 34222645)).put(StructureFeature.SHIPWRECK, new StructureConfig(24, 4, 165745295)).put(StructureFeature.OCEAN_RUIN, new StructureConfig(20, 8, 14357621)).put(StructureFeature.BASTION_REMNANT, new StructureConfig(27, 4, 30084232)).put(StructureFeature.FORTRESS, new StructureConfig(27, 4, 30084232)).put(StructureFeature.NETHER_FOSSIL, new StructureConfig(2, 1, 14357921)).build();
            }
        }
    }

    @Mixin(DefaultBiomeFeatures.class)
    public static class DefaultBiomeFeaturesMixin {

        @Inject(method = "addDefaultOres", at = @At("TAIL"))
        private static void addDefaultOres(GenerationSettings.Builder builder, CallbackInfo ci) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.SPEEDRUNNER_ORE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.IGNEOUS_ORE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.DIAMOND_ORE);
        }

        @Inject(method = "addNetherMineables", at = @At("TAIL"))
        private static void addNetherMineables(GenerationSettings.Builder builder, CallbackInfo ci) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModConfiguredFeatures.NETHER_SPEEDRUNNER_ORE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModConfiguredFeatures.NETHER_IGNEOUS_ORE);
        }

        @Inject(method = "addAncientDebris", at = @At("TAIL"))
        private static void addAncientDebris(GenerationSettings.Builder builder, CallbackInfo ci) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModConfiguredFeatures.ANCIENT_DEBRIS);
        }

        /**
         * Makes enderman spawn more commonly.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static void addMonsters(net.minecraft.world.biome.SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight) {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, zombieWeight, 1, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, skeletonWeight, 1, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 1, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 100, 1, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 75, 1, 5));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 50, 1, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 50, 1, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 25, 1, 4));
            } else {
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, zombieWeight, 4, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, skeletonWeight, 1, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 2, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 1, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 50, 4, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
            }
        }

        /**
         * Allows certain animals to spawn more often.
         * @author Dilloney
         * @reason.
         */
        @Overwrite
        public static void addFarmAnimals(net.minecraft.world.biome.SpawnSettings.Builder builder) {
            builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 16, 4, 8));
            builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 4, 8));
            builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 4, 8));
            builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 8, 4, 8));
        }

        /**
         * Allows dolphins to spawn more commonly.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static void addWarmOceanMobs(net.minecraft.world.biome.SpawnSettings.Builder builder, int squidWeight, int squidMinGroupSize) {
            builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, squidWeight, squidMinGroupSize, 4));
            builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 10, 4, 8));
            builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 15, 1, 2));
            addBatsAndMonsters(builder);
        }

        /**
         * Changes what mobs spawn in the end when Doom Mode is enabled.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public static void addEndMobs(net.minecraft.world.biome.SpawnSettings.Builder builder) {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 85, 1, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 75, 1, 4));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 75, 1, 2));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.RAVAGER, 50, 1, 1));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.EVOKER, 50, 1, 1));
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 25, 1, 1));
            } else {
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 4, 4));
            }
        }
    }

    @Mixin(NetherFortressFeature.class)
    public abstract static class NetherFortressFeatureMixin {

        @Shadow
        private static final Pool<SpawnSettings.SpawnEntry> MONSTER_SPAWNS;

        static {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                MONSTER_SPAWNS = Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 50, 1, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 75, 4, 12), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 20, 1, 4));
            } else {
                MONSTER_SPAWNS = Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4), new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 3), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 3), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 3));
            }
        }
    }

    @Mixin(StrongholdFeature.Start.class)
    public abstract static class StrongholdFeatureStartMixin extends MarginedStructureStart<DefaultFeatureConfig> {

        @Shadow @Final
        private long seed;

        public StrongholdFeatureStartMixin(StructureFeature<DefaultFeatureConfig> structureFeature, ChunkPos chunkPos, int i, long l) {
            super(structureFeature, chunkPos, i, l);
        }

        /**
         * Changes the Y-level a Stronghold generates at.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            int var8 = 0;

            net.minecraft.structure.StrongholdGenerator.Start start;
            do {
                this.clearChildren();
                this.random.setCarverSeed(this.seed + (long)(var8++), chunkPos.x, chunkPos.z);
                StrongholdGenerator.init();
                start = new net.minecraft.structure.StrongholdGenerator.Start(this.random, chunkPos.getOffsetX(2), chunkPos.getOffsetZ(2));
                this.addPiece(start);
                start.fillOpenings(start, this, this.random);
                List list = start.pieces;

                while(!list.isEmpty()) {
                    int j = this.random.nextInt(list.size());
                    StructurePiece structurePiece = (StructurePiece)list.remove(j);
                    structurePiece.fillOpenings(start, this, this.random);
                }

                this.randomUpwardTranslation(this.random, 25, 36);
            } while(this.hasNoChildren() || start.portalRoom == null);

        }
    }

    @Mixin(NetherFortressGenerator.class)
    public static class NetherFortressGeneratorMixin {

        @Shadow
        private static final NetherFortressGenerator.PieceData[] ALL_BRIDGE_PIECES;

        @Shadow
        private static final NetherFortressGenerator.PieceData[] ALL_CORRIDOR_PIECES;

        static {
            ALL_BRIDGE_PIECES = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 50, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 10, 2)};
            ALL_CORRIDOR_PIECES = new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 25, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 25, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 2, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 20, 2)};
        }
    }

    @Mixin(RuinedPortalFeature.Start.class)
    public static class RuinedPortalFeatureMixin extends StructureStart<RuinedPortalFeatureConfig> {

        public RuinedPortalFeatureMixin(StructureFeature<RuinedPortalFeatureConfig> feature, ChunkPos pos, int references, long seed) {
            super(feature, pos, references, seed);
        }

        /**
         * Changes how {@code Ruined Portals} generate and also fixes a few bugs.
         * @author Dilloney
         * @reason
         */
        @Overwrite
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, RuinedPortalFeatureConfig ruinedPortalFeatureConfig, HeightLimitView heightLimitView) {
            RuinedPortalStructurePiece.Properties properties = new RuinedPortalStructurePiece.Properties();
            RuinedPortalStructurePiece.VerticalPlacement verticalPlacement6;
            if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.DESERT) {
                verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
                properties.airPocket = false;
                properties.mossiness = 0.0F;
            } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.JUNGLE) {
                verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
                properties.airPocket = this.random.nextFloat() < 0.5F;
                properties.mossiness = 0.8F;
                properties.overgrown = true;
                properties.vines = true;
            } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.SWAMP) {
                verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_OCEAN_FLOOR;
                properties.airPocket = false;
                properties.mossiness = 0.5F;
                properties.vines = true;
            } else {
                boolean bl2;
                if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.MOUNTAIN) {
                    bl2 = this.random.nextFloat() < 0.5F;
                    verticalPlacement6 = bl2 ? RuinedPortalStructurePiece.VerticalPlacement.IN_MOUNTAIN : RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
                    properties.airPocket = bl2 || this.random.nextFloat() < 0.5F;
                } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.OCEAN) {
                    verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.ON_OCEAN_FLOOR;
                    properties.airPocket = false;
                    properties.mossiness = 0.8F;
                } else if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.NETHER) {
                    verticalPlacement6 = RuinedPortalStructurePiece.VerticalPlacement.IN_NETHER;
                    properties.airPocket = this.random.nextFloat() < 0.5F;
                    properties.mossiness = 0.0F;
                    properties.replaceWithBlackstone = true;
                } else {
                    bl2 = this.random.nextFloat() < 0.5F;
                    verticalPlacement6 = bl2 ? RuinedPortalStructurePiece.VerticalPlacement.UNDERGROUND : RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
                    properties.airPocket = bl2 || this.random.nextFloat() < 0.5F;
                }
            }

            Identifier identifier2;
            if (this.random.nextFloat() < 0.05F) {
                identifier2 = new Identifier(RuinedPortalFeature.RARE_PORTAL_STRUCTURE_IDS[this.random.nextInt(RuinedPortalFeature.RARE_PORTAL_STRUCTURE_IDS.length)]);
            } else {
                identifier2 = new Identifier(RuinedPortalFeature.COMMON_PORTAL_STRUCTURE_IDS[this.random.nextInt(RuinedPortalFeature.COMMON_PORTAL_STRUCTURE_IDS.length)]);
            }

            Structure structure = structureManager.getStructureOrBlank(identifier2);
            BlockRotation blockRotation = (BlockRotation) Util.getRandom(BlockRotation.values(), this.random);
            BlockMirror blockMirror = this.random.nextFloat() < 0.5F ? BlockMirror.NONE : BlockMirror.FRONT_BACK;
            BlockPos blockPos = new BlockPos(structure.getSize().getX() / 2, 0, structure.getSize().getZ() / 2);
            BlockPos blockPos2 = chunkPos.getStartPos();
            BlockBox blockBox = structure.calculateBoundingBox(blockPos2, blockRotation, blockPos, blockMirror);
            BlockPos blockPos3 = blockBox.getCenter();
            int i = blockPos3.getX();
            int j = blockPos3.getZ();
            int k = chunkGenerator.getHeight(i, j, RuinedPortalStructurePiece.getHeightmapType(verticalPlacement6), heightLimitView) - 1;
            int l = RuinedPortalFeature.getFloorHeight(this.random, chunkGenerator, verticalPlacement6, properties.airPocket, k, blockBox.getBlockCountY(), blockBox, heightLimitView);
            BlockPos blockPos4 = new BlockPos(blockPos2.getX(), l, blockPos2.getZ());
            if (ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.MOUNTAIN || ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.OCEAN || ruinedPortalFeatureConfig.portalType == RuinedPortalFeature.Type.STANDARD) {
                properties.cold = RuinedPortalFeature.isColdAt(blockPos4, biome);
            }

            this.addPiece(new RuinedPortalStructurePiece(structureManager, blockPos4, verticalPlacement6, properties, identifier2, structure, blockRotation, blockMirror, blockPos));
        }
    }

    @Mixin(StrongholdGenerator.class)
    public abstract static class StrongholdGeneratorMixin {

        @Shadow
        private static final StrongholdGenerator.PieceData[] ALL_PIECES;

        static {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 10), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 50, 10), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 75, 10), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, 6) {
                    public boolean canGenerate(int chainLength) {
                        return super.canGenerate(chainLength) && chainLength > 3;
                    }
                }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 20, 1) {
                    public boolean canGenerate(int chainLength) {
                        return super.canGenerate(chainLength) && chainLength > 5;
                    }
                }};
            } else {
                ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 20, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 10, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 10, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 2) {
                    public boolean canGenerate(int chainLength) {
                        return super.canGenerate(chainLength) && chainLength > 4;
                    }
                }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 200, 3) {
                    public boolean canGenerate(int chainLength) {
                        return super.canGenerate(chainLength) && chainLength > 2;
                    }
                }, new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 200, 2) {
                    public boolean canGenerate(int chainLength) {
                        return super.canGenerate(chainLength);
                    }
                }};
            }
        }

        @Mixin(StrongholdGenerator.PortalRoom.class)
        public abstract static class PortalRoomMixin extends StrongholdGenerator.Piece {

            @Shadow
            private boolean spawnerPlaced;

            public PortalRoomMixin(StructurePieceType structurePieceType, int i, BlockBox blockBox) {
                super(structurePieceType, i, blockBox);
            }

            /**
             * Changes the how {@code Portal Room} generates in a {@code Stronghold}.
             * @author Dilloney
             * @reason
             */
            @Overwrite
            public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
                this.fillWithOutline(world, boundingBox, 0, 0, 0, 10, 7, 15, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.generateEntrance(world, random, boundingBox, StrongholdGenerator.Piece.EntranceType.GRATES, 4, 1, 0);
                int i = 6;
                this.fillWithOutline(world, boundingBox, 1, i, 1, 1, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, boundingBox, 9, i, 1, 9, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, boundingBox, 2, i, 1, 8, i, 2, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, boundingBox, 2, i, 14, 8, i, 14, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, boundingBox, 1, 1, 1, 2, 1, 4, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, boundingBox, 8, 1, 1, 9, 1, 4, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, boundingBox, 1, 1, 1, 1, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
                this.fillWithOutline(world, boundingBox, 9, 1, 1, 9, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
                this.fillWithOutline(world, boundingBox, 3, 1, 8, 7, 1, 12, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, boundingBox, 4, 1, 9, 6, 1, 11, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
                BlockState blockState = (BlockState) ((BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true)).with(PaneBlock.SOUTH, true);
                BlockState blockState2 = (BlockState) ((BlockState) Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true)).with(PaneBlock.EAST, true);

                int k;
                for (k = 3; k < 14; k += 2) {
                    this.fillWithOutline(world, boundingBox, 0, 3, k, 0, 4, k, blockState, blockState, false);
                    this.fillWithOutline(world, boundingBox, 10, 3, k, 10, 4, k, blockState, blockState, false);
                }

                for (k = 2; k < 9; k += 2) {
                    this.fillWithOutline(world, boundingBox, k, 3, 15, k, 4, 15, blockState2, blockState2, false);
                }

                BlockState blockState3 = (BlockState) Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
                this.fillWithOutline(world, boundingBox, 4, 1, 5, 6, 1, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, boundingBox, 4, 2, 6, 6, 2, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);
                this.fillWithOutline(world, boundingBox, 4, 3, 7, 6, 3, 7, false, random, StrongholdGenerator.STONE_BRICK_RANDOMIZER);

                for (int l = 4; l <= 6; ++l) {
                    this.addBlock(world, blockState3, l, 1, 4, boundingBox);
                    this.addBlock(world, blockState3, l, 2, 5, boundingBox);
                    this.addBlock(world, blockState3, l, 3, 6, boundingBox);
                }

                BlockState blockState4 = (BlockState) Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
                BlockState blockState5 = (BlockState) Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
                BlockState blockState6 = (BlockState) Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
                BlockState blockState7 = (BlockState) Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);
                boolean bl = true;
                boolean[] bls = new boolean[12];

                for (int m = 0; m < bls.length; ++m) {
                    bls[m] = random.nextFloat() > ModHelper.getEndPortalFilledWithEyeChance();
                    bl &= bls[m];
                }

                this.addBlock(world, (BlockState) blockState4.with(EndPortalFrameBlock.EYE, bls[0]), 4, 3, 8, boundingBox);
                this.addBlock(world, (BlockState) blockState4.with(EndPortalFrameBlock.EYE, bls[1]), 5, 3, 8, boundingBox);
                this.addBlock(world, (BlockState) blockState4.with(EndPortalFrameBlock.EYE, bls[2]), 6, 3, 8, boundingBox);
                this.addBlock(world, (BlockState) blockState5.with(EndPortalFrameBlock.EYE, bls[3]), 4, 3, 12, boundingBox);
                this.addBlock(world, (BlockState) blockState5.with(EndPortalFrameBlock.EYE, bls[4]), 5, 3, 12, boundingBox);
                this.addBlock(world, (BlockState) blockState5.with(EndPortalFrameBlock.EYE, bls[5]), 6, 3, 12, boundingBox);
                this.addBlock(world, (BlockState) blockState6.with(EndPortalFrameBlock.EYE, bls[6]), 3, 3, 9, boundingBox);
                this.addBlock(world, (BlockState) blockState6.with(EndPortalFrameBlock.EYE, bls[7]), 3, 3, 10, boundingBox);
                this.addBlock(world, (BlockState) blockState6.with(EndPortalFrameBlock.EYE, bls[8]), 3, 3, 11, boundingBox);
                this.addBlock(world, (BlockState) blockState7.with(EndPortalFrameBlock.EYE, bls[9]), 7, 3, 9, boundingBox);
                this.addBlock(world, (BlockState) blockState7.with(EndPortalFrameBlock.EYE, bls[10]), 7, 3, 10, boundingBox);
                this.addBlock(world, (BlockState) blockState7.with(EndPortalFrameBlock.EYE, bls[11]), 7, 3, 11, boundingBox);
                if (bl) {
                    BlockState blockState8 = Blocks.END_PORTAL.getDefaultState();
                    this.addBlock(world, blockState8, 4, 3, 9, boundingBox);
                    this.addBlock(world, blockState8, 5, 3, 9, boundingBox);
                    this.addBlock(world, blockState8, 6, 3, 9, boundingBox);
                    this.addBlock(world, blockState8, 4, 3, 10, boundingBox);
                    this.addBlock(world, blockState8, 5, 3, 10, boundingBox);
                    this.addBlock(world, blockState8, 6, 3, 10, boundingBox);
                    this.addBlock(world, blockState8, 4, 3, 11, boundingBox);
                    this.addBlock(world, blockState8, 5, 3, 11, boundingBox);
                    this.addBlock(world, blockState8, 6, 3, 11, boundingBox);
                }

                if (!this.spawnerPlaced) {
                    BlockPos blockPos = this.offsetPos(5, 3, 6);
                    if (boundingBox.contains(blockPos)) {
                        this.spawnerPlaced = true;
                        world.setBlockState(blockPos, Blocks.SPAWNER.getDefaultState(), 2);
                        BlockEntity blockEntity = world.getBlockEntity(blockPos);
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
        private Pool<MobSpawnerEntry> spawnPotentials;

        @Shadow @Final
        private Random random;

        @Shadow
        abstract void setSpawnEntry(@Nullable World world, BlockPos pos, MobSpawnerEntry spawnEntry);

        @Shadow
        abstract void sendStatus(World world, BlockPos pos, int i);

        int minSpawnDelayMixin = 200;
        int maxSpawnDelayMixin = 400;

        /**
         * Makes mobs spawn faster (tried {@code @Inject}, but didn't work.)
         * @author Dilloney
         * @reason
         */
        @Overwrite
        private void updateSpawns(World world, BlockPos pos) {
            if (this.maxSpawnDelayMixin <= this.minSpawnDelayMixin) {
                this.spawnDelay = this.minSpawnDelayMixin;
            } else {
                this.spawnDelay = this.minSpawnDelayMixin + this.random.nextInt(this.maxSpawnDelayMixin - this.minSpawnDelayMixin);
            }

            this.spawnPotentials.getOrEmpty(this.random).ifPresent((mobSpawnerEntry) -> {
                this.setSpawnEntry(world, pos, mobSpawnerEntry);
            });
            this.sendStatus(world, pos, 1);
        }
    }
}
