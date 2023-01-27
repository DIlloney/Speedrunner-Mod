package net.dilloney.speedrunnermod.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.block.ModBlocks;
import net.dilloney.speedrunnermod.item.ModFoodComponents;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.tag.ModBlockTags;
import net.dilloney.speedrunnermod.util.entity.Giant;
import net.dilloney.speedrunnermod.util.entity.GiantAttackGoal;
import net.dilloney.speedrunnermod.world.gen.feature.ModFeatures;
import net.dilloney.speedrunnermod.world.gen.feature.ModOrePlacedFeatures;
import net.minecraft.advancement.criterion.Criteria;
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
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.loot.function.ApplyBonusLootFunction;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import net.minecraft.world.dimension.AreaHelper;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StrongholdConfig;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.*;
import java.util.function.BiConsumer;

import static net.minecraft.world.gen.feature.OrePlacedFeatures.modifiersWithCount;
import static net.minecraft.world.gen.feature.OrePlacedFeatures.modifiersWithRarity;

public class ModMixins {

    @Mixin(AbstractBlock.AbstractBlockState.class)
    public static class AbstractBlockMixin {

        @Inject(method = "getHardness", at = @At("HEAD"), cancellable = true)
        private void getHardness(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
            if (SpeedrunnerMod.options().main.modifiedBlockHardness) {
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

                if (SpeedrunnerMod.options().advanced.debugMode) {
                    SpeedrunnerMod.LOGGER.info("Applied modified block hardness!");
                }
            }
        }
    }

    @Mixin(AbstractFireBlock.class)
    public static class AbstractFireBlockMixin {

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

    @Mixin(AbstractSkeletonEntity.class)
    public static class AbstractSkeletonEntityMixin extends HostileEntity {

        public AbstractSkeletonEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @ModifyArg(method = "createAbstractSkeletonAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
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

    @Mixin(ApplyBonusLootFunction.OreDrops.class)
    public static class ApplyBonusLootFunctionMixin {

        @Overwrite
        public int getValue(Random random, int initialCount, int enchantmentLevel) {
            return enchantmentLevel > 0 ? initialCount * (enchantmentLevel + 1) : initialCount;
        }
    }

    @Mixin(AreaHelper.class)
    public static class AreaHelperMixin {
        @Shadow
        private static final AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK = (state, world, pos) -> {
            return state.isOf(Blocks.OBSIDIAN) || state.isOf(Blocks.CRYING_OBSIDIAN);
        };
    }

    @Mixin(BeehiveBlock.class)
    public static class BeehiveBlockMixin {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
        private boolean onUse(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
        }
    }

    @Mixin(BlazeEntity.class)
    public static class BlazeEntityMixin extends HostileEntity {

        public BlazeEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 10 + EnchantmentHelper.getLooting(player) * 48;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createBlazeAttributes() {
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 48.0D : 16.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
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

    @Mixin(Block.class)
    public static class BlockMixin {

        @ModifyArg(method = "onLandedUpon", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;handleFallDamage(FFLnet/minecraft/entity/damage/DamageSource;)Z"), index = 1)
        private float onLandedUpon(float damageMultiplier) {
            return SpeedrunnerMod.options().main.doomMode ? 1.0F : 0.7F;
        }
    }

    @Mixin(CaveSpiderEntity.class)
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

    @Mixin(ConfiguredStructureFeatures.class)
    public static class ConfiguredStructureFeaturesMixin {
        @Shadow @Final
        private static ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> VILLAGE_PLAINS, VILLAGE_TAIGA;

        @Inject(method = "registerAll", at = @At("TAIL"))
        private static void registerAll(BiConsumer<ConfiguredStructureFeature<?, ?>, RegistryKey<Biome>> registrar, CallbackInfo ci) {
            if (SpeedrunnerMod.options().advanced.modifiedBiomes) {
                ConfiguredStructureFeatures.register(registrar, VILLAGE_PLAINS, BiomeKeys.SUNFLOWER_PLAINS);
                ConfiguredStructureFeatures.register(registrar, VILLAGE_PLAINS, BiomeKeys.FOREST);
                ConfiguredStructureFeatures.register(registrar, VILLAGE_PLAINS, BiomeKeys.FLOWER_FOREST);
                ConfiguredStructureFeatures.register(registrar, VILLAGE_TAIGA, BiomeKeys.WINDSWEPT_HILLS);
            }
        }
    }

    @Mixin(CreeperEntity.class)
    public abstract static class CreeperEntityMixin extends HostileEntity implements SkinOverlayOwner {
        @Shadow
        int explosionRadius;
        @Shadow
        abstract void ignite();

        public CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @ModifyArg(method = "createCreeperAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
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

    @Mixin(DefaultBiomeFeatures.class)
    public static class DefaultBiomeFeaturesMixin {

        @Inject(method = "addDefaultOres(Lnet/minecraft/world/biome/GenerationSettings$Builder;Z)V", at = @At("TAIL"))
        private static void addDefaultOres(GenerationSettings.Builder builder, boolean largeCopperOreBlob, CallbackInfo ci) {
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
            ModFeatures.modifyMonsterSpawns(builder, zombieWeight, zombieVillagerWeight, skeletonWeight, drowned);
        }

        @Overwrite
        public static void addFarmAnimals(net.minecraft.world.biome.SpawnSettings.Builder builder) {
            ModFeatures.makeAnimalsMoreCommon(builder);
        }

        @Overwrite
        public static void addWarmOceanMobs(net.minecraft.world.biome.SpawnSettings.Builder builder, int squidWeight, int squidMinGroupSize) {
            ModFeatures.makeDolphinsMoreCommon(builder, squidWeight, squidMinGroupSize);
        }

        @Overwrite
        public static void addEndMobs(net.minecraft.world.biome.SpawnSettings.Builder builder) {
            ModFeatures.modifyEndMonsterSpawning(builder);
        }
    }

    @Mixin(DolphinEntity.class)
    public static class DolphinEntityMixin {
        @Shadow
        private static final TargetPredicate CLOSE_PLAYER_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(20.0D).ignoreVisibility();

        @Mixin(DolphinEntity.SwimWithPlayerGoal.class)
        public static class SwimWithPlayerGoalMixin {

            @ModifyArg(method = "start", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;I)V"), index = 1)
            private int start(int x) {
                return 200;
            }
        }
    }

    @Mixin(DragonFireballEntity.class)
    public static class DragonFireballEntityMixin {

        @ModifyArg(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;II)V"), index = 2)
        private int onCollision(int x) {
            return SpeedrunnerMod.options().main.doomMode ? 1 : 0;
        }
    }

    @Mixin(EfficiencyEnchantment.class)
    public static class EfficiencyEnchantmentMixin {

        @Redirect(method = "isAcceptableItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean isAcceptableItem(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
        }
    }

    @Mixin(ElderGuardianEntity.class)
    public static class ElderGuardianEntityMixin extends GuardianEntity {

        public ElderGuardianEntityMixin(EntityType<? extends GuardianEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 10 + EnchantmentHelper.getLooting(player) * 72;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
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

    @Mixin(Enchantment.class)
    public static class EnchantmentMixin {

        @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
        private void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
            Enchantment enchantment = (Enchantment)(Object)this;
            if (stack.getItem() instanceof AxeItem) {
                if (enchantment instanceof FireAspectEnchantment) {
                    cir.setReturnValue(true);
                } else if (enchantment instanceof KnockbackEnchantment) {
                    cir.setReturnValue(true);
                } else if (enchantment instanceof LuckEnchantment) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Mixin(value = EnderDragonEntity.class, priority = 999)
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

        @ModifyArg(method = "createEnderDragonAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
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

    @Mixin(EnderDragonFight.class)
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
            if (SpeedrunnerMod.options().main.dragonPerchTime >= 21) {
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        enderDragonEntity.getPhaseManager().setPhase(PhaseType.LANDING);
                    }
                }, SpeedrunnerMod.options().main.dragonPerchTime * 1000);
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

    @Mixin(EndermanEntity.class)
    public static class EndermanEntityMixin extends HostileEntity {

        public EndermanEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 10 + EnchantmentHelper.getLooting(player) * 48;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createEndermanAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 60.0D : 25.0D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 64.0D : 12.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
        }
    }

    @Mixin(EndermiteEntity.class)
    public static class EndermiteEntityMixin extends HostileEntity {

        public EndermiteEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 16;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createEndermiteAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.25D : 0.15D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 2.0D : 0.01D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
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

    @Mixin(Entity.class)
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

    @Mixin(EvokerEntity.class)
    public abstract static class EvokerEntityMixin extends SpellcastingIllagerEntity {

        public EvokerEntityMixin(EntityType<? extends SpellcastingIllagerEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 63;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }
    }

    @Mixin(EyeOfEnderEntity.class)
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

    @Mixin(FoodComponents.class)
    public static class FoodComponentsMixin {
        @Shadow
        private static final FoodComponent APPLE, BAKED_POTATO, BEEF, BEETROOT, BREAD, CARROT, CHICKEN , CHORUS_FRUIT, COD, COOKED_BEEF, COOKED_CHICKEN, COOKED_COD, COOKED_MUTTON, COOKED_PORKCHOP, COOKED_RABBIT, COOKED_SALMON, COOKIE, DRIED_KELP, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE, GOLDEN_CARROT, HONEY_BOTTLE, MELON_SLICE, MUTTON, POISONOUS_POTATO, PORKCHOP, POTATO, PUFFERFISH, PUMPKIN_PIE, RABBIT, ROTTEN_FLESH, SALMON, SPIDER_EYE, SWEET_BERRIES, GLOW_BERRIES, TROPICAL_FISH;

        static {
            APPLE = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.APPLE : FoodComponents.APPLE;
            BAKED_POTATO = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.BAKED_POTATO : FoodComponents.BAKED_POTATO;
            BEEF = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.BEEF : FoodComponents.BEEF;
            BEETROOT = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.BEETROOT : FoodComponents.BEETROOT;
            BREAD = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.BREAD : FoodComponents.BREAD;
            CARROT = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.CARROT : FoodComponents.CARROT;
            CHICKEN = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.CHICKEN : FoodComponents.CHICKEN;
            CHORUS_FRUIT = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.CHORUS_FRUIT : FoodComponents.CHORUS_FRUIT;
            COD = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.COD : FoodComponents.COD;
            COOKED_BEEF = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.COOKED_BEEF : FoodComponents.COOKED_BEEF;
            COOKED_CHICKEN = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.COOKED_CHICKEN : FoodComponents.COOKED_CHICKEN;
            COOKED_COD = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.COOKED_COD : FoodComponents.COOKED_COD;
            COOKED_MUTTON = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.COOKED_MUTTON : FoodComponents.COOKED_MUTTON;
            COOKED_PORKCHOP = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.COOKED_PORKCHOP : FoodComponents.COOKED_PORKCHOP;
            COOKED_RABBIT = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.COOKED_RABBIT : FoodComponents.COOKED_RABBIT;
            COOKED_SALMON = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.COOKED_SALMON : FoodComponents.COOKED_SALMON;
            COOKIE = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.COOKIE : FoodComponents.COOKIE;
            DRIED_KELP = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.DRIED_KELP : FoodComponents.DRIED_KELP;
            ENCHANTED_GOLDEN_APPLE = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.ENCHANTED_GOLDEN_APPLE : FoodComponents.ENCHANTED_GOLDEN_APPLE;
            GOLDEN_APPLE = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.GOLDEN_APPLE : FoodComponents.GOLDEN_APPLE;
            GOLDEN_CARROT = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.GOLDEN_CARROT : FoodComponents.GOLDEN_CARROT;
            HONEY_BOTTLE = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.HONEY_BOTTLE : FoodComponents.HONEY_BOTTLE;
            MELON_SLICE = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.MELON_SLICE : FoodComponents.MELON_SLICE;
            MUTTON = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.MUTTON : FoodComponents.MUTTON;
            POISONOUS_POTATO = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.POISONOUS_POTATO : FoodComponents.POISONOUS_POTATO;
            PORKCHOP = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.PORKCHOP : FoodComponents.PORKCHOP;
            POTATO = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.POTATO : FoodComponents.POTATO;
            PUFFERFISH = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.PUFFERFISH : FoodComponents.PUFFERFISH;
            PUMPKIN_PIE = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.PUMPKIN_PIE : FoodComponents.PUMPKIN_PIE;
            RABBIT = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.RABBIT : FoodComponents.RABBIT;
            ROTTEN_FLESH = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.ROTTEN_FLESH : FoodComponents.ROTTEN_FLESH;
            SALMON = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.SALMON : FoodComponents.SALMON;
            SPIDER_EYE = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.SPIDER_EYE : FoodComponents.SPIDER_EYE;
            SWEET_BERRIES = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.SWEET_BERRIES : FoodComponents.SWEET_BERRIES;
            GLOW_BERRIES = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.GLOW_BERRIES : FoodComponents.GLOW_BERRIES;
            TROPICAL_FISH = SpeedrunnerMod.options().advanced.modifiedFoods ? ModFoodComponents.TROPICAL_FISH : FoodComponents.TROPICAL_FISH;
        }
    }

    @Mixin(GhastEntity.class)
    public static class GhastEntityMixin extends FlyingEntity {

        public GhastEntityMixin(EntityType<? extends FlyingEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createGhastAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 20.0D : 5.0D;
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 100.0D : 16.0D;
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
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

    @Mixin(GiantEntity.class)
    public static class GiantEntityMixin extends HostileEntity implements Giant {
        protected SwimNavigation waterNavigation;
        protected MobNavigation landNavigation;
        boolean targetingUnderwater;
        private ServerBossBar bossBar;

        public GiantEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 50 + EnchantmentHelper.getLooting(player) * 150;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
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
                    if (this.getY() < (double)(this.world.getBottomY() - 64)) {
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
    }

    @Mixin(GuardianEntity.class)
    public static class GuardianEntityMixin extends HostileEntity {

        public GuardianEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 10 + EnchantmentHelper.getLooting(player) * 36;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createGuardianAttributes() {
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 7.0D : 3.0D;
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 24.0D : 8.0;
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 35.0D : 15.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange).add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth);
        }
    }

    @Mixin(HoglinEntity.class)
    public abstract static class HoglinEntityMixin extends AnimalEntity {

        public HoglinEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            return this.experiencePoints + EnchantmentHelper.getLooting(player) * 36;
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createHoglinAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 60.0D : 25.0D;
            final double genericKnockbackResistance = SpeedrunnerMod.options().main.doomMode ? 0.7000000238518589D : 0.6000000238418579D;
            final double genericAttackKnockback = SpeedrunnerMod.options().main.doomMode ? 1.2D : 0.5D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, genericKnockbackResistance).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }
    }

    @Mixin(IronGolemEntity.class)
    public static class IronGolemEntityMixin extends GolemEntity {

        public IronGolemEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createIronGolemAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 100.0D : 50.0D;
            final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.3D : 0.25D;
            final double genericKnockbackResistance = SpeedrunnerMod.options().main.doomMode ? 0.7D : 0.5D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 20.0D : 7.0D;
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, genericKnockbackResistance).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
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

    @Mixin(ItemPredicate.class)
    public static class ItemPredicateMixin {

        @ModifyVariable(method = "test", at = @At("HEAD"))
        private ItemStack fixSpeedrunnerShears(ItemStack stack) {
            if (stack.getItem().getDefaultStack().isOf(ModItems.SPEEDRUNNER_SHEARS)) {
                ItemStack itemStack = new ItemStack(Items.SHEARS);
                itemStack.setCount(stack.getCount());
                itemStack.setNbt(stack.getOrCreateNbt());
                return itemStack;
            }

            return stack;
        }
    }

    @Mixin(LivingEntity.class)
    public abstract static class LivingEntityMixin extends Entity {
        @Shadow abstract ItemStack getStackInHand(Hand hand);
        @Shadow abstract void setHealth(float health);
        @Shadow abstract boolean clearStatusEffects();
        @Shadow @Final abstract boolean addStatusEffect(StatusEffectInstance effect);

        public LivingEntityMixin(EntityType<?> type, World world) {
            super(type, world);
        }

        @Overwrite
        public int getNextAirOnLand(int air) {
            return Math.min(air + 8, this.getMaxAir());
        }

        @Overwrite
        private boolean tryUseTotem(DamageSource source) {
            if (source.isOutOfWorld()) {
                return false;
            } else {
                ItemStack itemStack = null;
                Hand[] var4 = Hand.values();
                int var5 = var4.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Hand hand = var4[var6];
                    ItemStack itemStack2 = this.getStackInHand(hand);
                    if (itemStack2.isOf(Items.TOTEM_OF_UNDYING)) {
                        itemStack = itemStack2.copy();
                        itemStack2.decrement(1);
                        break;
                    }
                }

                if (itemStack != null) {
                    if ((LivingEntity)(Object)this instanceof ServerPlayerEntity) {
                        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)(Object)this;
                        serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING));
                        Criteria.USED_TOTEM.trigger(serverPlayerEntity, itemStack);
                    }

                    this.setHealth(1.0F);
                    this.clearStatusEffects();
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2400, 0));
                    this.world.sendEntityStatus(this, (byte)35);
                }

                return itemStack != null;
            }
        }

        @Redirect(method = "getPreferredEquipmentSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 2))
        private static boolean getPreferredEquipmentSlot(ItemStack stack, Item item) {
            return stack.isOf(Items.SHIELD) || stack.isOf(ModItems.SPEEDRUNNER_SHIELD);
        }
    }

    @Mixin(MagmaCubeEntity.class)
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

    @Mixin(value = MobSpawnerLogic.class, priority = 999)
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
        int maxSpawnDelayMixin = SpeedrunnerMod.options().advanced.mobSpawnerSpawnDuration * 10;

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

    @Mixin(NetherFortressFeature.class)
    public static class NetherFortressFeatureMixin {
        @Shadow
        private static final Pool<SpawnSettings.SpawnEntry> MONSTER_SPAWNS = ModFeatures.NETHER_FORTRESS_MOB_SPAWNS;
    }

    @Mixin(NetherFortressGenerator.class)
    public static class NetherFortressGeneratorMixin {
        @Shadow
        private static final NetherFortressGenerator.PieceData[] ALL_BRIDGE_PIECES = ModFeatures.NETHER_FORTRESS_GENERATION_BRIDGE;
        @Shadow
        private static final NetherFortressGenerator.PieceData[] ALL_CORRIDOR_PIECES = ModFeatures.NETHER_FORTRESS_GENERATION_CORRIDOR;
    }

    @Mixin(OreBlock.class)
    public static class OreBlockMixin extends Block {

        public OreBlockMixin(Settings settings) {
            super(settings);
        }

        @Inject(method = "onStacksDropped", at = @At("TAIL"))
        public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                int f;
                int i;
                if (state.isOf(Blocks.GOLD_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 36;
                    i = 2 + world.random.nextInt(5) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DEEPSLATE_GOLD_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 54;
                    i = 2 + world.random.nextInt(5) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.IRON_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 32;
                    i = 1 + world.random.nextInt(2) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 48;
                    i = 1 + world.random.nextInt(2) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.COAL_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 12;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DEEPSLATE_COAL_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 18;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.NETHER_GOLD_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 24;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.LAPIS_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 36;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DEEPSLATE_LAPIS_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 54;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DIAMOND_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 44;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 66;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.EMERALD_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 36;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DEEPSLATE_EMERALD_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 54;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.NETHER_QUARTZ_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 24;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(ModBlocks.SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 42;
                    i = 2 + world.random.nextInt(6) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 63;
                    i = 2 + world.random.nextInt(6) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.NETHER_SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 36;
                    i = 1 + world.random.nextInt(3) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 40;
                    i = 2 + world.random.nextInt(6) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.DEEPSLATE_IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 60;
                    i = 2 + world.random.nextInt(6) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.NETHER_IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 36;
                    i = 2 + world.random.nextInt(6) + f;
                    this.dropExperience(world, pos, i);
                }
            }
        }
    }

    @Mixin(OrePlacedFeatures.class)
    public static class OrePlacedFeaturesMixin {
        @Shadow
        private static final PlacedFeature ORE_DIAMOND, ORE_DIAMOND_LARGE, ORE_DIAMOND_BURIED, ORE_LAPIS, ORE_LAPIS_BURIED, ORE_ANCIENT_DEBRIS_LARGE, ORE_DEBRIS_SMALL;

        static {
            if (SpeedrunnerMod.options().advanced.makeOresMoreCommon) {
                ORE_DIAMOND = PlacedFeatures.register("ore_diamond_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_SMALL.withPlacement(CountPlacementModifier.of(4), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
                ORE_DIAMOND_LARGE = PlacedFeatures.register("ore_diamond_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_LARGE.withPlacement(CountPlacementModifier.of(4), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
                ORE_DIAMOND_BURIED = PlacedFeatures.register("ore_diamond_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_BURIED.withPlacement(CountPlacementModifier.of(4), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
                ORE_LAPIS = PlacedFeatures.register("ore_lapis_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS.withPlacement(CountPlacementModifier.of(2), HeightRangePlacementModifier.trapezoid(YOffset.fixed(-32), YOffset.fixed(32))));
                ORE_LAPIS_BURIED = PlacedFeatures.register("ore_lapis_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS_BURIED.withPlacement(CountPlacementModifier.of(2), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
                ORE_ANCIENT_DEBRIS_LARGE = PlacedFeatures.register("ore_ancient_debris_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_LARGE.withPlacement(new PlacementModifier[]{CountPlacementModifier.of(2), HeightRangePlacementModifier.trapezoid(YOffset.fixed(8), YOffset.fixed(24)), BiomePlacementModifier.of()}));
                ORE_DEBRIS_SMALL = PlacedFeatures.register("ore_debris_small_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_SMALL.withPlacement(new PlacementModifier[]{CountPlacementModifier.of(3), PlacedFeatures.EIGHT_ABOVE_AND_BELOW_RANGE, BiomePlacementModifier.of()}));
            } else {
                ORE_DIAMOND = PlacedFeatures.register("ore_diamond_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_SMALL.withPlacement(modifiersWithCount(7, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)))));
                ORE_DIAMOND_LARGE = PlacedFeatures.register("ore_diamond_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_LARGE.withPlacement(modifiersWithRarity(9, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)))));
                ORE_DIAMOND_BURIED = PlacedFeatures.register("ore_diamond_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_BURIED.withPlacement(modifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)))));
                ORE_LAPIS = PlacedFeatures.register("ore_lapis_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS.withPlacement(modifiersWithCount(2, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-32), YOffset.fixed(32)))));
                ORE_LAPIS_BURIED = PlacedFeatures.register("ore_lapis_buried_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_LAPIS_BURIED.withPlacement(modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64)))));
                ORE_ANCIENT_DEBRIS_LARGE = PlacedFeatures.register("ore_ancient_debris_large_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_LARGE.withPlacement(new PlacementModifier[]{SquarePlacementModifier.of(), HeightRangePlacementModifier.trapezoid(YOffset.fixed(8), YOffset.fixed(24)), BiomePlacementModifier.of()}));
                ORE_DEBRIS_SMALL = PlacedFeatures.register("ore_debris_small_" + SpeedrunnerMod.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_SMALL.withPlacement(new PlacementModifier[]{SquarePlacementModifier.of(), PlacedFeatures.EIGHT_ABOVE_AND_BELOW_RANGE, BiomePlacementModifier.of()}));
            }
        }
    }

    @Mixin(PiglinBrain.class)
    public static class PiglinBrainMixin {

        @Inject(method = "wearsGoldArmor(Lnet/minecraft/entity/LivingEntity;)Z", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"), cancellable = true,locals = LocalCapture.CAPTURE_FAILHARD)
        private static void wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir, Iterable<ItemStack> iterable, Iterator iterator, ItemStack stack, Item item) {
            if (getPiglinSafeArmor(stack)) {
                cir.setReturnValue(true);
            }
        }

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

        private static boolean getPiglinSafeArmor(ItemStack stack) {
            return stack.getItem() == ModItems.GOLDEN_SPEEDRUNNER_HELMET || stack.getItem() == ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE || stack.getItem() == ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS || stack.getItem() == ModItems.GOLDEN_SPEEDRUNNER_BOOTS;
        }
    }

    @Mixin(PiglinBruteEntity.class)
    public abstract static class PiglinBruteEntityMixin extends AbstractPiglinEntity {

        public PiglinBruteEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 20 + EnchantmentHelper.getLooting(player) * 72;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @ModifyArg(method = "createPiglinBruteAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", ordinal = 0), index = 1)
        private static double genericMaxHealth(double baseValue) {
            return SpeedrunnerMod.options().main.doomMode ? 25.0D : 50.0D;
        }
    }

    @Mixin(PiglinEntity.class)
    public abstract static class PiglinEntityMixin extends AbstractPiglinEntity {

        public PiglinEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createPiglinAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 24.0D : 16.0D;
            final double genericAttackDamage =  SpeedrunnerMod.options().main.doomMode ? 6.0D : 2.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }
    }

    @Mixin(PillagerEntity.class)
    public abstract static class PillagerEntityMixin extends IllagerEntity {

        public PillagerEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createPillagerAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 32.0D : 12.0D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0;
            final double genericFollowRange = SpeedrunnerMod.options().main.doomMode ? 32.0D : 16.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
        }
    }

    @Mixin(PlayerEntity.class)
    public abstract static class PlayerEntityMixin extends LivingEntity {
        @Shadow
        abstract ItemCooldownManager getItemCooldownManager();

        public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
            super(entityType, world);
        }

        @Inject(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getItemCooldownManager()Lnet/minecraft/entity/player/ItemCooldownManager;"))
        private void disableShield(CallbackInfo ci) {
            this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 80);
        }

        @Redirect(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean damageShield(ItemStack stack, Item item) {
            return stack.isOf(Items.SHIELD) || stack.isOf(ModItems.SPEEDRUNNER_SHIELD);
        }

        @Inject(method = "travel", at = @At("TAIL"))
        private void travel(Vec3d movementInput, CallbackInfo ci) {
            if (SpeedrunnerMod.options().advanced.modifiedItemEffects) {
                if (this.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.SPEEDRUNNER_BOOTS || this.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.GOLDEN_SPEEDRUNNER_BOOTS) {
                    int i = this.world.getDifficulty() != Difficulty.HARD ? 60 : 20;
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, i, 0, true, false, true));
                    FluidState fluidState = this.world.getFluidState(this.getBlockPos());
                    if (this.isInLava() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid())) {
                        this.updateVelocity(0.025F, movementInput);
                        if (!this.hasNoGravity()) {
                            this.setVelocity(this.getVelocity().add(0.0D, -0.02D, 0.0D));
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
        }

        protected void swimUpward(Tag<Fluid> fluid) {
            if (SpeedrunnerMod.options().advanced.modifiedItemEffects && this.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.SPEEDRUNNER_BOOTS && this.isInLava() || SpeedrunnerMod.options().advanced.modifiedItemEffects && this.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.GOLDEN_SPEEDRUNNER_BOOTS && this.isInLava()) {
                this.setVelocity(this.getVelocity().add(0.0D, 0.07999999910593034D, 0.0D));
            } else {
                this.setVelocity(this.getVelocity().add(0.0D, 0.03999999910593033D, 0.0D));
            }
        }

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

    @Mixin(PhantomEntity.class)
    public static class PhantomEntityMixin extends FlyingEntity {

        public PhantomEntityMixin(EntityType<? extends FlyingEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }
    }

    @Mixin(PumpkinBlock.class)
    public static class PumpkinBlockMixin {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onUse(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
        }
    }

    @Mixin(RavagerEntity.class)
    public abstract static class RavagerEntityMixin extends RaiderEntity {

        public RavagerEntityMixin(EntityType<? extends RaiderEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 72;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

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

    @Mixin(RedstoneOreBlock.class)
    public static class RedstoneOreBlockMixin extends Block {

        public RedstoneOreBlockMixin(Settings settings) {
            super(settings);
        }

        @Inject(method = "onStacksDropped", at = @At("TAIL"))
        private void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                int f;
                if (state.isOf(Blocks.REDSTONE_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 32;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DEEPSLATE_REDSTONE_ORE)) {
                    f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 48;
                    this.dropExperience(world, pos, f);
                }
            }
        }
    }

    @Mixin(RuinedPortalFeature.class)
    public static class RuinedPortalFeatureMixin {

        @ModifyVariable(method = "addPieces", at = @At(value = "STORE", ordinal = 0), index = 1)
        private static RuinedPortalStructurePiece.VerticalPlacement addPieces(RuinedPortalStructurePiece.VerticalPlacement verticalPlacement) {
            return RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
        }
    }

    @Mixin(ServerPlayerEntity.class)
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

    @Mixin(SheepEntity.class)
    public static abstract class SheepEntityMixin extends AnimalEntity {

        public SheepEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
            super(entityType, world);
        }

        @ModifyVariable(method = "sheared", at = @At("STORE"))
        private int sheared(int x) {
            return 6 + this.random.nextInt(4);
        }

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean interactMob(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
        }
    }

    @Mixin(ShulkerEntity.class)
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

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @ModifyArg(method = "createShulkerAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
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

    @Mixin(SilverfishEntity.class)
    public abstract static class SilverfishEntityMixin extends HostileEntity {

        public SilverfishEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 16;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createSilverfishAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.25D : 0.15D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 2.0D : 0.01D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }

        @Mixin(SilverfishEntity.CallForHelpGoal.class)
        public static class CallForHelpGoalMixin {
            @Shadow
            int delay;

            @Redirect(method = "onHurt", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/SilverfishEntity$CallForHelpGoal;delay:I", ordinal = 0))
            private int onHurt(SilverfishEntity.CallForHelpGoal callForHelpGoal) {
                return this.delay = SpeedrunnerMod.options().main.doomMode ? 20 : 100;
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
            if (persistentProjectileEntity instanceof ArrowEntity && SpeedrunnerMod.options().main.doomMode) {
                ((ArrowEntity)persistentProjectileEntity).addEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
            }

            return persistentProjectileEntity;
        }
    }

    @Mixin(SlimeEntity.class)
    public static class SlimeEntityMixin extends MobEntity {

        public SlimeEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public int getTicksUntilNextJump() {
            return SpeedrunnerMod.options().main.doomMode ? 20 : 100;
        }
    }

    @Mixin(SmallFireballEntity.class)
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

    @Mixin(SnowGolemEntity.class)
    public static class SnowGolemEntityMixin {

        @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean interactMob(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
        }
    }

    @Mixin(SpawnerBlock.class)
    public static abstract class SpawnerBlockMixin extends BlockWithEntity {

        public SpawnerBlockMixin(Settings settings) {
            super(settings);
        }

        @Inject(method = "onStacksDropped", at = @At("TAIL"))
        private void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
            int f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 172;
            int i = 512 + world.random.nextInt(524) + world.random.nextInt(128) + f;
            this.dropExperience(world, pos, i);
        }
    }

    @Mixin(SpawnRestriction.class)
    public static class SpawnRestrictionMixin {

        static {
            if (SpeedrunnerMod.options().main.doomMode) {
                SpawnRestriction.register(EntityType.PIGLIN_BRUTE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnRestrictionMixin::canPiglinBruteSpawn);
            }
        }

        private static boolean canPiglinBruteSpawn(EntityType<PiglinBruteEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
            return !world.getBlockState(pos.down()).isOf(Blocks.NETHER_WART_BLOCK);
        }
    }

    @Mixin(SpiderEntity.class)
    public static class SpiderEntityMixin extends HostileEntity {

        public SpiderEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
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

    @Mixin(StrongholdConfig.class)
    public static class StrongholdConfigMixin {
        @Shadow @Final
        private int distance;
        @Shadow @Final
        private int count;

        @Overwrite
        public int getDistance() {
            return SpeedrunnerMod.options().main.makeStructuresMoreCommon ? SpeedrunnerMod.options().advanced.strongholdDistance : this.distance;
        }

        @Overwrite
        public int getCount() {
            return SpeedrunnerMod.options().main.makeStructuresMoreCommon ? SpeedrunnerMod.options().main.strongholdCount : this.count;
        }
    }

    @Mixin(StrongholdFeature.class)
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

    @Mixin(StrongholdGenerator.class)
    public static class StrongholdGeneratorMixin {
        @Shadow
        private static final StrongholdGenerator.PieceData[] ALL_PIECES = ModFeatures.STRONGHOLD_GENERATION;

        @Mixin(StrongholdGenerator.PortalRoom.class)
        public abstract static class PortalRoomMixin extends StrongholdGenerator.Piece {
            @Shadow
            private boolean spawnerPlaced;

            public PortalRoomMixin(StructurePieceType structurePieceType, int i, BlockBox blockBox) {
                super(structurePieceType, i, blockBox);
            }

            @Overwrite
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

    @Mixin(TheNetherBiomeCreator.class)
    public static class TheNetherBiomeCreatorMixin {

        @Overwrite
        public static Biome createNetherWastes() {
            SpawnSettings spawnSettings;
            if (SpeedrunnerMod.options().main.doomMode) {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 20, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 50, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 100, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 20, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 100, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            } else {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 20, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 2, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 50, 2, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            }
            net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA);
            DefaultBiomeFeatures.addDefaultMushrooms(builder);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED);
            DefaultBiomeFeatures.addNetherMineables(builder);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3344392).skyColor(OverworldBiomeCreator.getSkyColor(2.0F)).loopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_NETHER_WASTES)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }

        @Overwrite
        public static Biome createSoulSandValley() {
            double d = 0.7D;
            double e = 0.15D;
            SpawnSettings spawnSettings;
            if (SpeedrunnerMod.options().main.doomMode) {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 4, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.SKELETON, 0.7D, 0.15D).spawnCost(EntityType.GHAST, 0.7D, 0.15D).spawnCost(EntityType.ENDERMAN, 0.7D, 0.15D).spawnCost(EntityType.STRIDER, 0.7D, 0.15D).build();
            } else {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 10, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 4, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.SKELETON, 0.7D, 0.15D).spawnCost(EntityType.GHAST, 0.7D, 0.15D).spawnCost(EntityType.ENDERMAN, 0.7D, 0.15D).spawnCost(EntityType.STRIDER, 0.7D, 0.15D).build();
            }
            net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA).feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, NetherPlacedFeatures.BASALT_PILLAR).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_CRIMSON_ROOTS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_SOUL_SAND);
            DefaultBiomeFeatures.addNetherMineables(builder);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1787717).skyColor(OverworldBiomeCreator.getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.ASH, 0.00625F)).loopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_SOUL_SAND_VALLEY)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }

        @Overwrite
        public static Biome createBasaltDeltas() {
            SpawnSettings spawnSettings;
            if (SpeedrunnerMod.options().main.doomMode) {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 40, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            } else {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 25, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 25, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            }
            net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.SURFACE_STRUCTURES, NetherPlacedFeatures.DELTA).feature(GenerationStep.Feature.SURFACE_STRUCTURES, NetherPlacedFeatures.SMALL_BASALT_COLUMNS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, NetherPlacedFeatures.LARGE_BASALT_COLUMNS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.BASALT_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.BLACKSTONE_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_DELTA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED_DOUBLE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_GOLD_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_QUARTZ_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModOrePlacedFeatures.ORE_SPEEDRUNNER_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModOrePlacedFeatures.ORE_IGNEOUS_DELTAS);
            DefaultBiomeFeatures.addAncientDebris(builder);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(6840176).skyColor(OverworldBiomeCreator.getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.118093334F)).loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_BASALT_DELTAS)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }

        @Overwrite
        public static Biome createCrimsonForest() {
            SpawnSettings spawnSettings;
            if (SpeedrunnerMod.options().main.doomMode) {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 50, 4, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 2, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            } else {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 1, 2)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 6, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 9, 2, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
            }
            net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA);
            DefaultBiomeFeatures.addDefaultMushrooms(builder);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.WEEPING_VINES).feature(GenerationStep.Feature.VEGETAL_DECORATION, TreePlacedFeatures.CRIMSON_FUNGI).feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.CRIMSON_FOREST_VEGETATION);
            DefaultBiomeFeatures.addNetherMineables(builder);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3343107).skyColor(OverworldBiomeCreator.getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.025F)).loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }

        @Overwrite
        public static Biome createWarpedForest() {
            SpawnSettings spawnSettings;
            if (SpeedrunnerMod.options().main.doomMode) {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 25, 4, 6)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.ENDERMAN, 1.0D, 0.12D).build();
            } else {
                spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 5, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.ENDERMAN, 1.0D, 0.12D).build();
            }
            net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA);
            DefaultBiomeFeatures.addDefaultMushrooms(builder);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.VEGETAL_DECORATION, TreePlacedFeatures.WARPED_FUNGI).feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.WARPED_FOREST_VEGETATION).feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.NETHER_SPROUTS).feature(GenerationStep.Feature.VEGETAL_DECORATION, NetherPlacedFeatures.TWISTING_VINES);
            DefaultBiomeFeatures.addNetherMineables(builder);
            return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1705242).skyColor(OverworldBiomeCreator.getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WARPED_SPORE, 0.01428F)).loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
        }
    }

    @Mixin(TntBlock.class)
    public static class TntBlockMixin {

        @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onUse(ItemStack stack, Item item) {
            return stack.isOf(Items.FLINT_AND_STEEL) || stack.isOf(ModItems.SPEEDRUNNER_FLINT_AND_STEEL) || stack.isOf(Items.FIRE_CHARGE);
        }
    }

    @Mixin(TripwireBlock.class)
    public static class TripwireBlockMixin {

        @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
        private boolean onBreak(ItemStack stack, Item item) {
            return stack.isOf(Items.SHEARS) || stack.isOf(ModItems.SPEEDRUNNER_SHEARS);
        }
    }

    @Mixin(UndergroundPlacedFeatures.class)
    public static class UndergroundPlacedFeaturesMixin {
        @Shadow
        private static final PlacedFeature MONSTER_ROOM, MONSTER_ROOM_DEEP;

        static {
            MONSTER_ROOM = SpeedrunnerMod.options().main.makeStructuresMoreCommon ? PlacedFeatures.register("monster_room_" + SpeedrunnerMod.MOD_ID, UndergroundConfiguredFeatures.MONSTER_ROOM.withPlacement(new PlacementModifier[]{CountPlacementModifier.of(16), HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()), BiomePlacementModifier.of()})) : PlacedFeatures.register("monster_room_" + SpeedrunnerMod.MOD_ID, UndergroundConfiguredFeatures.MONSTER_ROOM.withPlacement(new PlacementModifier[]{CountPlacementModifier.of(10), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()), BiomePlacementModifier.of()}));
            MONSTER_ROOM_DEEP = SpeedrunnerMod.options().main.makeStructuresMoreCommon ? PlacedFeatures.register("monster_room_deep_" + SpeedrunnerMod.MOD_ID, UndergroundConfiguredFeatures.MONSTER_ROOM.withPlacement(new PlacementModifier[]{CountPlacementModifier.of(16), HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.fixed(-1)), BiomePlacementModifier.of()})) : PlacedFeatures.register("monster_room_deep_" + SpeedrunnerMod.MOD_ID, UndergroundConfiguredFeatures.MONSTER_ROOM.withPlacement(new PlacementModifier[]{CountPlacementModifier.of(4), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.fixed(-1)), BiomePlacementModifier.of()}));
        }
    }

    @Mixin(VanillaBiomeParameters.class)
    public static class VanillaBiomeParametersMixin {
        @Shadow @Final @Mutable
        private RegistryKey<Biome>[][] COMMON_BIOMES;

        @Inject(method = "<init>", at = @At("TAIL"))
        private void init(CallbackInfo ci) {
            this.COMMON_BIOMES = SpeedrunnerMod.options().main.makeBiomesMoreCommon ? new RegistryKey[][]{{BiomeKeys.PLAINS, BiomeKeys.PLAINS, BiomeKeys.PLAINS, BiomeKeys.DESERT, BiomeKeys.SAVANNA}, {BiomeKeys.PLAINS, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA}, {BiomeKeys.FLOWER_FOREST, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.DESERT, BiomeKeys.DARK_FOREST}, {BiomeKeys.SAVANNA, BiomeKeys.SAVANNA, BiomeKeys.FOREST, BiomeKeys.JUNGLE, BiomeKeys.PLAINS}, {BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT}} : new RegistryKey[][]{{BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.TAIGA}, {BiomeKeys.PLAINS, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA}, {BiomeKeys.FLOWER_FOREST, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.DARK_FOREST}, {BiomeKeys.SAVANNA, BiomeKeys.SAVANNA, BiomeKeys.FOREST, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE}, {BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT}};
        }
    }

    @Mixin(VexEntity.class)
    public static class VexEntityMixin extends HostileEntity {
        @Shadow
        boolean alive;
        @Shadow
        int lifeTicks;

        public VexEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
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

    @Mixin(VindicatorEntity.class)
    public abstract static class VindicatorEntityMixin extends IllagerEntity {

        public VindicatorEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
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

    @Mixin(WitchEntity.class)
    public abstract static class WitchEntityMixin extends RaiderEntity {

        public WitchEntityMixin(EntityType<? extends RaiderEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createWitchAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 26.0D : 14.0D;
            final double genericMovementSpeed = SpeedrunnerMod.options().main.doomMode ? 0.35D : 0.25D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed);
        }
    }

    @Mixin(WitherEntity.class)
    public static class WitherEntityMixin extends HostileEntity {

        public WitherEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 50 + EnchantmentHelper.getLooting(player) * 150;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @ModifyArg(method = "createWitherAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
        private static double genericMaxHealth(double baseValue) {
            return SpeedrunnerMod.options().main.doomMode ? 150.0D : 100.0D;
        }
    }

    @Mixin(WitherSkeletonEntity.class)
    public abstract static class WitherSkeletonEntityMixin extends AbstractSkeletonEntity {

        public WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
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

    @Mixin(ZoglinEntity.class)
    public static class ZoglinEntityMixin extends HostileEntity {

        public ZoglinEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 36;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
        }

        @Overwrite
        public static DefaultAttributeContainer.Builder createZoglinAttributes() {
            final double genericMaxHealth = SpeedrunnerMod.options().main.doomMode ? 60.0D : 25.0D;
            final double genericKnockbackResistance = SpeedrunnerMod.options().main.doomMode ? 0.7000000238518589D : 0.6000000238418579D;
            final double genericAttackKnockback = SpeedrunnerMod.options().main.doomMode ? 1.2D : 0.5D;
            final double genericAttackDamage = SpeedrunnerMod.options().main.doomMode ? 8.0D : 4.0D;
            return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, genericKnockbackResistance).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage);
        }
    }

    @Mixin(ZombieEntity.class)
    public static class ZombieEntityMixin extends HostileEntity {

        public ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
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

    @Mixin(ZombifiedPiglinEntity.class)
    public static class ZombifiedPiglinEntityMixin extends ZombieEntity {

        public ZombifiedPiglinEntityMixin(EntityType<? extends ZombieEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        public int getXpToDrop(PlayerEntity player) {
            this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
            if (this.experiencePoints > 0) {
                int i = this.experiencePoints;

                int j;
                for(j = 0; j < this.armorItems.size(); ++j) {
                    if (!((ItemStack)this.armorItems.get(j)).isEmpty() && this.armorDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                for(j = 0; j < this.handItems.size(); ++j) {
                    if (!((ItemStack)this.handItems.get(j)).isEmpty() && this.handDropChances[j] <= 1.0F) {
                        i += 1 + this.random.nextInt(3);
                    }
                }

                return i;
            } else {
                return this.experiencePoints;
            }
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
}