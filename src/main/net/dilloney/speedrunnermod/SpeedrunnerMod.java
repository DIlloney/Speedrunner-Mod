package net.dilloney.speedrunnermod;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.dilloney.speedrunnermod.block.ModBlocks;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.option.ModOptions;
import net.dilloney.speedrunnermod.option.OptionsFileManager;
import net.dilloney.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe;
import net.dilloney.speedrunnermod.tag.ModBlockTags;
import net.dilloney.speedrunnermod.tag.ModItemTags;
import net.dilloney.speedrunnermod.util.UniqueItemRegistry;
import net.dilloney.speedrunnermod.world.gen.feature.ModConfiguredFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.structure.NetherFortressGenerator;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.dimension.AreaHelper;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.addBatsAndMonsters;

public class SpeedrunnerMod implements ModInitializer {
    public static final Identifier SPEEDRUNNER_BOOTS = new Identifier("speedrunnermod", "textures/item/speedrunner_boots.png");
    public static final Text SPEEDRUNNER_MOD_TITLE = new TranslatableText("speedrunnermod.title");
    public static final String MOD_VERSION = "v1.4.1";
    public static final String MINECRAFT_VERSION = "1.16.5";
    public static final Logger LOGGER = LogManager.getLogger();
    public static ModOptions OPTIONS = OptionsFileManager.get();

    public void onInitialize() {
        OptionsFileManager.load();

        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_ingot"), ModItems.SPEEDRUNNER_INGOT);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_nugget"), ModItems.SPEEDRUNNER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_sword"), ModItems.SPEEDRUNNER_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shovel"), ModItems.SPEEDRUNNER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_pickaxe"), ModItems.SPEEDRUNNER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_axe"), ModItems.SPEEDRUNNER_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_hoe"), ModItems.SPEEDRUNNER_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_helmet"), ModItems.SPEEDRUNNER_HELMET);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_chestplate"), ModItems.SPEEDRUNNER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_leggings"), ModItems.SPEEDRUNNER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_boots"), ModItems.SPEEDRUNNER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_bow"), ModItems.SPEEDRUNNER_BOW);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_crossbow"), ModItems.SPEEDRUNNER_CROSSBOW);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shears"), ModItems.SPEEDRUNNER_SHEARS);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_flint_and_steel"), ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_shield"), ModItems.SPEEDRUNNER_SHIELD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_bulk"), ModItems.SPEEDRUNNER_BULK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "rotten_speedrunner_bulk"), ModItems.ROTTEN_SPEEDRUNNER_BULK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "igneous_rock"), ModItems.IGNEOUS_ROCK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "cooked_piglin_pork"), ModItems.COOKED_PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "piglin_pork"), ModItems.PIGLIN_PORK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "cooked_flesh"), ModItems.COOKED_FLESH);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "inferno_eye"), ModItems.INFERNO_EYE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "annul_eye"), ModItems.ANNUL_EYE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_block"), ModItems.SPEEDRUNNER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "speedrunner_ore"), ModItems.SPEEDRUNNER_ORE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "nether_speedrunner_ore"), ModItems.NETHER_SPEEDRUNNER_ORE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "igneous_ore"), ModItems.IGNEOUS_ORE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "nether_igneous_ore"), ModItems.NETHER_IGNEOUS_ORE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_sword"), ModItems.ANDESITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_shovel"), ModItems.ANDESITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_pickaxe"), ModItems.ANDESITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_axe"), ModItems.ANDESITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "andesite_hoe"), ModItems.ANDESITE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_sword"), ModItems.BLACKSTONE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_shovel"), ModItems.BLACKSTONE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_pickaxe"), ModItems.BLACKSTONE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_axe"), ModItems.BLACKSTONE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "blackstone_hoe"), ModItems.BLACKSTONE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_sword"), ModItems.DIORITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_shovel"), ModItems.DIORITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_pickaxe"), ModItems.DIORITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_axe"), ModItems.DIORITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "diorite_hoe"), ModItems.DIORITE_HOE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_sword"), ModItems.GRANITE_SWORD);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_shovel"), ModItems.GRANITE_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_pickaxe"), ModItems.GRANITE_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_axe"), ModItems.GRANITE_AXE);
        Registry.register(Registry.ITEM, new Identifier("speedrunnermod", "granite_hoe"), ModItems.GRANITE_HOE);

        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_block"), ModBlocks.SPEEDRUNNER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "speedrunner_ore"), ModBlocks.SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_speedrunner_ore"), ModBlocks.NETHER_SPEEDRUNNER_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "igneous_ore"), ModBlocks.IGNEOUS_ORE);
        Registry.register(Registry.BLOCK, new Identifier("speedrunnermod", "nether_igneous_ore"), ModBlocks.NETHER_IGNEOUS_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> speedrunnerOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "speedrunner_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, speedrunnerOre.getValue(), ModConfiguredFeatures.SPEEDRUNNER_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> netherSpeedrunnerOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "nether_speedrunner_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, netherSpeedrunnerOre.getValue(), ModConfiguredFeatures.NETHER_SPEEDRUNNER_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> igneousOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "igneous_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, igneousOre.getValue(), ModConfiguredFeatures.IGNEOUS_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> netherIgneousOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "nether_igneous_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, netherIgneousOre.getValue(), ModConfiguredFeatures.NETHER_IGNEOUS_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> diamondOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "diamond_ore_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, diamondOre.getValue(), ModConfiguredFeatures.DIAMOND_ORE);

        RegistryKey<ConfiguredFeature<?, ?>> ancientDebris = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("speedrunnermod", "ancient_debris_speedrunnermod"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ancientDebris.getValue(), ModConfiguredFeatures.ANCIENT_DEBRIS);

        ModItemTags.IRON_INGOTS = TagRegistry.item(new Identifier("speedrunnermod", "iron_ingots"));
        ModItemTags.IRON_NUGGETS = TagRegistry.item(new Identifier("speedrunnermod", "iron_nuggets"));
        ModItemTags.IRON_BLOCKS = TagRegistry.item(new Identifier("speedrunnermod", "iron_blocks"));
        ModItemTags.COBBLESTONES = TagRegistry.item(new Identifier("speedrunnermod", "cobblestones"));

        ModBlockTags.ZERO_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0_hardness"));
        ModBlockTags.ZERO_ONE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-1_hardness"));
        ModBlockTags.ZERO_TWO_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-2_hardness"));
        ModBlockTags.ZERO_THREESEVEN_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-37_hardness"));
        ModBlockTags.ZERO_FOUR_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-4_hardness"));
        ModBlockTags.ZERO_FIVE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-5_hardness"));
        ModBlockTags.ZERO_SIX_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-6_hardness"));
        ModBlockTags.ZERO_SIXFIVE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-65_hardness"));
        ModBlockTags.ZERO_SEVEN_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-7_hardness"));
        ModBlockTags.ZERO_EIGHT_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/0-8_hardness"));
        ModBlockTags.ONE_ZERO_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/1-0_hardness"));
        ModBlockTags.ONE_THREE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/1-3_hardness"));
        ModBlockTags.ONE_FOUR_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/1-4_hardness"));
        ModBlockTags.ONE_FIVE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/1-5_hardness"));
        ModBlockTags.ONE_SIX_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/1-6_hardness"));
        ModBlockTags.TWO_ZERO_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/2-0_hardness"));
        ModBlockTags.TWO_FIVE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/2-5_hardness"));
        ModBlockTags.THREE_ZERO_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/3-0_hardness"));
        ModBlockTags.TEN_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/10_hardness"));
        ModBlockTags.TWENTY_FIVE_HARDNESS = TagRegistry.block(new Identifier("speedrunnermod", "block_hardness/25_hardness"));

        Registry.register(Registry.RECIPE_SERIALIZER, "speedrunnermod:crafting_special_speedrunner_shield_decoration", SpeedrunnerShieldDecorationRecipe.SPEEDRUNNER_SHIELD_DECORATION_RECIPE);

        UniqueItemRegistry.SHEARS.addItemToRegistry(ModItems.SPEEDRUNNER_SHEARS);
        UniqueItemRegistry.SHEARS.addItemToRegistry(Items.SHEARS);
        UniqueItemRegistry.SHIELD.addItemToRegistry(ModItems.SPEEDRUNNER_SHIELD);
        UniqueItemRegistry.SHIELD.addItemToRegistry(Items.SHIELD);
        UniqueItemRegistry.BOW.addItemToRegistry(ModItems.SPEEDRUNNER_BOW);
        UniqueItemRegistry.BOW.addItemToRegistry(Items.BOW);
        UniqueItemRegistry.CROSSBOW.addItemToRegistry(ModItems.SPEEDRUNNER_CROSSBOW);
        UniqueItemRegistry.CROSSBOW.addItemToRegistry(Items.CROSSBOW);
        UniqueItemRegistry.FLINT_AND_STEEL.addItemToRegistry(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        UniqueItemRegistry.FLINT_AND_STEEL.addItemToRegistry(Items.FLINT_AND_STEEL);
        UniqueItemRegistry.TNT_BLOCK_IGNITERS.addItemToRegistry(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        UniqueItemRegistry.TNT_BLOCK_IGNITERS.addItemToRegistry(Items.FLINT_AND_STEEL);
        UniqueItemRegistry.TNT_BLOCK_IGNITERS.addItemToRegistry(Items.FIRE_CHARGE);

        LOGGER.info("The Speedrunner Mod has been loaded successfully! modVersion = " + MOD_VERSION + " on " + MINECRAFT_VERSION);
    }

    public static void applyBlockHardnessValues(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
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

    public static void applyFallDamage(World world, BlockPos pos, Entity entity, float distance) {
        entity.handleFallDamage(distance, getFallDamageMultiplier());
    }

    private static float getFallDamageMultiplier() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.0F : 0.7F;
    }

    public static DefaultAttributeContainer.Builder applyEnderDragonAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getEnderDragonMaxHealth());
    }

    private static double getEnderDragonMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 500.0D : 100.0D;
    }

    public static float getEndCrystalHealAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.7F : 0.1F;
    }

    public static float getDragonDamageAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 12.0F : 3.0F;
    }

    public static float getEndCrystalDamageAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 3.0F : 20.0F;
    }

    public static float getDragonDamageDuringSittingAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.18F : 0.60F;
    }

    public static DefaultAttributeContainer.Builder applyWitherAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getWitherMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6000000238418579D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0D).add(EntityAttributes.GENERIC_ARMOR, 4.0D);
    }

    private static double getWitherMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 300.0D : 100.0D;
    }

    public static DefaultAttributeContainer.Builder applyAbstractSkeletonAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, getAbstractSkeletonMovementSpeed());
    }

    private static double getAbstractSkeletonMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.3D : 0.25D;
    }

    public static int getAbstractSkeletonBowAttackSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 5 : 20;
    }

    public static int getAbstractSkeletonBowAttackSpeedHard() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 10 : 20;
    }

    public static DefaultAttributeContainer.Builder applyBlazeAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getBlazeAttackDamage()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, getBlazeFollowRange());
    }

    private static double getBlazeAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    private static double getBlazeFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 64.0D : 16.0D;
    }

    public static int getBlazeFireballCooldownAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 60 : 120;
    }

    public static DefaultAttributeContainer.Builder applyCreeperAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, getCreeperMovementSpeed());
    }

    private static double getCreeperMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.3D : 0.25D;
    }

    public static boolean getCreeperIgnitions(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        return itemStack.getItem() == Items.FLINT_AND_STEEL || itemStack.getItem() == ModItems.SPEEDRUNNER_FLINT_AND_STEEL;
    }

    public static DefaultAttributeContainer.Builder applyElderGuardianAttributes() {
        return GuardianEntity.createGuardianAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getElderGuardianAttackDamage()).add(EntityAttributes.GENERIC_MAX_HEALTH, getElderGuardianMaxHealth());
    }

    private static double getElderGuardianAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    private static double getElderGuardianMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 50.0D : 25.0D;
    }

    public static int getElderGuardianAgeAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 600 : 6000;
    }

    public static double getElderGuardianSquaredDistanceTo() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 3000.0D : 1250.0D;
    }

    public static int getElderGuardianMiningFatigueAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 6000 : 600;
    }

    public static DefaultAttributeContainer.Builder applyEndermanAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getEndermanMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getEndermanAttackDamage()).add(EntityAttributes.GENERIC_FOLLOW_RANGE, getEndermanFollowRange());
    }

    private static double getEndermanMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 60.0D : 25.0D;
    }

    private static double getEndermanAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    private static double getEndermanFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 72.0D : 12.0D;
    }

    public static DefaultAttributeContainer.Builder applyEndermiteAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.01D);
    }

    public static DefaultAttributeContainer.Builder applyGhastAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getGhastMaxHealth()).add(EntityAttributes.GENERIC_FOLLOW_RANGE, getGhastFollowRange());
    }

    private static double getGhastMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 20.0D : 5.0D;
    }

    private static double getGhastFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 100.0D : 16.0D;
    }

    public static int getGhastFireballCooldown() {
        return SpeedrunnerMod.OPTIONS.doomMode ? -5 : -40;
    }

    public static DefaultAttributeContainer.Builder applyGiantAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, getGiantMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, getGiantMovementSpeed()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getGiantAttackDamage()).add(EntityAttributes.GENERIC_ARMOR, 1.2D).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, getGiantAttackKnockback());
    }

    private static double getGiantMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 300.0D : 100.0D;
    }

    private static double getGiantMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.3500000528343624D : 0.5D;
    }

    private static double getGiantAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 10.0D : 50.0D;
    }

    private static double getGiantAttackKnockback() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.0D : 0.0D;
    }

    public static float getGiantSoundVolume() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 5.0F : 1.0F;
    }

    public static DefaultAttributeContainer.Builder applyGuardianAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getGuardianAttackDamage()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, getGuardianFollowRange()).add(EntityAttributes.GENERIC_MAX_HEALTH, getGuardianMaxHealth());
    }

    private static double getGuardianAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 7.0D : 3.0D;
    }

    private static double getGuardianFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 24.0D : 8.0D;
    }

    private static double getGuardianMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 50.0D : 15.0D;
    }

    public static DefaultAttributeContainer.Builder applyHoglinAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getHoglinMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, getHoglinKnockbackResistance()).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, getHoglinAttackKnockback()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getHoglinAttackDamage());
    }

    private static double getHoglinMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 60.0D : 25.0D;
    }

    private static double getHoglinKnockbackResistance() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.7000000238518589D : 0.6000000238418579D;
    }

    private static double getHoglinAttackKnockback() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.2D : 0.5D;
    }

    private static double getHoglinAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    public static int getMagmaCubeTicksUntilNextJumpAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 20 : 100;
    }

    public static float getMagmaCubeAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 2.2F : 1.5F;
    }

    public static boolean getPiglinInRangeOfZombifiedPiglin(PiglinEntity piglin) {
        Brain<PiglinEntity> brain = piglin.getBrain();
        if (brain.hasMemoryModule(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED)) {
            LivingEntity livingEntity = (LivingEntity)brain.getOptionalMemory(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED).get();
            return piglin.isInRange(livingEntity, 2.0D);
        } else {
            return false;
        }
    }

    public static DefaultAttributeContainer.Builder applyPiglinAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getPiglinMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getPiglinAttackDamage());
    }

    private static double getPiglinMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 32.0D : 16.0D;
    }

    private static double getPiglinAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 2.0D;
    }

    public static DefaultAttributeContainer.Builder applyPillagerAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_MAX_HEALTH, getPillagerMaxHealth()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getPillagerAttackDamage()).add(EntityAttributes.GENERIC_FOLLOW_RANGE, getPillagerFollowRange());
    }

    private static double getPillagerMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 32.0D : 12.0D;
    }

    private static double getPillagerAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    private static double getPillagerFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 32.0D : 16.0D;
    }

    public static DefaultAttributeContainer.Builder applyRavagerAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getRavagerMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.75D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getRavagerAttackDamage()).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, getRavagerAttackKnockback()).add(EntityAttributes.GENERIC_FOLLOW_RANGE, getRavagerFollowRange());
    }

    private static double getRavagerMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 100.0D : 50.0D;
    }

    private static double getRavagerAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 16.0D : 10.0D;
    }

    private static double getRavagerAttackKnockback() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.6D : 1.1D;
    }

    private static double getRavagerFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 64.0D : 32.0D;
    }

    public static void method_29999(Entity target, CallbackInfoReturnable cir) {
        if (target instanceof PlayerEntity) {
            if (SpeedrunnerMod.OPTIONS.doomMode) {
                ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
            }
        }
    }

    public static DefaultAttributeContainer.Builder applyShulkerAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getShulkerMaxHealth());
    }

    private static double getShulkerMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 32.0D : 20.0D;
    }

    public static DefaultAttributeContainer.Builder applySilverfishAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.01D);
    }

    public static int getSilverfishDelayAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 20 : 100;
    }

    public static int getSlimeTicksUntilNextJumpAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 20 : 100;
    }

    public static DefaultAttributeContainer.Builder applyVexAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getVexMaxHealth()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getVexAttackDamage());
    }

    private static double getVexMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 7.0D : 14.0D;
    }

    private static double getVexAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 3.0D : 4.0D;
    }

    public static float getVexDecayAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 100.0F : 1.0F;
    }

    public static DefaultAttributeContainer.Builder applyVindicatorAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, getVindicatorFollowRange()).add(EntityAttributes.GENERIC_MAX_HEALTH, getVindicatorMaxHealth()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D);
    }

    private static double getVindicatorFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 48.0D : 12.0D;
    }

    private static double getVindicatorMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 20.0D : 24.0D;
    }

    public static DefaultAttributeContainer.Builder applyWitchAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getWitchMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, getWitchMovementSpeed());
    }

    private static double getWitchMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 26.0D : 14.0D;
    }

    private static double getWitchMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.35D : 0.25D;
    }

    public static double getWitherSkeletonAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 10.0D : 1.0D;
    }

    public static int getWitherSkeletonEffectDuration() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 200 : 60;
    }

    public static DefaultAttributeContainer.Builder applyZoglinAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getZoglinMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, getZoglinKnockbackResistance()).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, getZoglinAttackKnockback()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getZoglinAttackDamage());
    }

    private static double getZoglinMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 60.0D : 25.0D;
    }

    private static double getZoglinKnockbackResistance() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.7000000238518589D : 0.6000000238418579D;
    }

    private static double getZoglinAttackKnockback() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.2D : 0.5D;
    }

    private static double getZoglinAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    public static DefaultAttributeContainer.Builder applyZombieAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, getZombieFollowRange()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, getZombieMovementSpeed()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getZombieAttackDamage()).add(EntityAttributes.GENERIC_ARMOR, getZombieGenericArmor()).add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    private static double getZombieFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 50.0D : 25.0D;
    }

    private static double getZombieMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.33000000417232513D : 0.23000000417232513D;
    }

    private static double getZombieAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 7.0D : 2.0D;
    }

    private static double getZombieGenericArmor() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 4.0D : 1.0D;
    }

    public static DefaultAttributeContainer.Builder applyZombifiedPiglinAttributes() {
        return ZombieEntity.createZombieAttributes().add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS, getZombifiedPiglinSpawnReinforcements()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, getZombifiedPiglinMovementSpeed()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getZombifiedPiglinAttackDamage());
    }

    private static double getZombifiedPiglinSpawnReinforcements() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.0D : 0.0D;
    }

    private static double getZombifiedPiglinMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.33000000427232513D : 0.23000000427232513D;
    }

    private static double getZombifiedPiglinAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 7.0D : 2.0D;
    }

    public static DefaultAttributeContainer.Builder applyIronGolemAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, getIronGolemMaxHealth()).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, getIronGolemMovementSpeed()).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, getIronGolemKnockbackResistance()).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, getIronGolemAttackDamage());
    }

    private static double getIronGolemMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 100.0D : 50.0D;
    }

    private static double getIronGolemMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.3D : 0.25D;
    }

    private static double getIronGolemKnockbackResistance() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.7D : 0.5D;
    }

    private static double getIronGolemAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 20.0D : 7.0D;
    }

    public static Item fixShearingSheep(ItemStack stack) {
        return UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
    }

    public static Item fixShearingSnowGolem(ItemStack stack) {
        return UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
    }

    public static Item fixShields(ItemStack stack) {
        return UniqueItemRegistry.SHIELD.getDefaultItem(stack.getItem());
    }

    public static float getEnderPearlDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 5.0F : 2.0F;
    }

    public static int getSmallFireballEntityFireTicks() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 6 : 3;
    }

    public static float getSmallFireballDamageAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 5.0F : 3.0F;
    }

    public static int getLavaFireTicks() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 15 : 7;
    }

    public static float getLavaDamageAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 4.0F : 2.0F;
    }

    public static float getEndPortalFilledWithEyeChance() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.99F : 0.7F;
    }

    public static int[] getDryBiomeIds() {
        return SpeedrunnerMod.OPTIONS.makeBiomesMoreCommon ? new int[]{2, 2, 2, 35, 35, 35, 36, 1, 1, 2, 35, 35, 1, 1, 2, 35, 2, 1, 35, 35, 2} : new int[]{2, 2, 2, 35, 35, 1};
    }

    public static int[] getTemperateBiomeIds() {
        return SpeedrunnerMod.OPTIONS.makeBiomesMoreCommon ? new int[]{4, 29, 2, 2, 2, 1, 1, 35, 35, 2, 35, 1} : new int[]{4, 29, 3, 1, 27, 6};
    }

    public static int[] getCoolBiomeIds() {
        return SpeedrunnerMod.OPTIONS.makeBiomesMoreCommon ? new int[]{4, 5, 6, 1, 2, 2, 35} : new int[]{4, 3, 5, 1};
    }

    public static int[] getSnowyBiomeIds() {
        return SpeedrunnerMod.OPTIONS.makeBiomesMoreCommon ? new int[]{12, 30, 3, 2, 1, 35, 2} : new int[]{12, 12, 12, 30};
    }

    public static Biome method_21000(boolean bl) {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addPlainsMobs(builder);
        if (!bl) {
            builder.playerSpawnFriendly();
        }

        net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        builder2.structureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS).structureFeature(ConfiguredStructureFeatures.PILLAGER_OUTPOST);

        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder2);
        builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
        DefaultBiomeFeatures.addLandCarvers(builder2);
        DefaultBiomeFeatures.addDefaultLakes(builder2);
        DefaultBiomeFeatures.addDungeons(builder2);
        DefaultBiomeFeatures.addPlainsTallGrass(builder2);
        if (bl) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_SUNFLOWER);
        }

        DefaultBiomeFeatures.addMineables(builder2);
        DefaultBiomeFeatures.addDefaultOres(builder2);
        DefaultBiomeFeatures.addDefaultDisks(builder2);
        DefaultBiomeFeatures.addPlainsFeatures(builder2);
        if (bl) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_SUGAR_CANE);
        }

        DefaultBiomeFeatures.addDefaultMushrooms(builder2);
        if (bl) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_PUMPKIN);
        } else {
            DefaultBiomeFeatures.addDefaultVegetation(builder2);
        }

        DefaultBiomeFeatures.addSprings(builder2);
        DefaultBiomeFeatures.addFrozenTopLayer(builder2);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(method_21009(0.8F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    public static Biome method_21002(float depth, float scale, boolean flower, SpawnSettings.Builder spawnSettings) {
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder);
        builder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL).structureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS);
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
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
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.MUSHROOM).depth(depth).scale(scale).temperature(0.7F).downfall(0.8F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(method_21009(0.7F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(spawnSettings.build()).generationSettings(builder.build()).build();
    }

    public static Biome method_21003(float depth, float scale, ConfiguredSurfaceBuilder<TernarySurfaceConfig> surfaceBuilder, boolean extraTrees) {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(builder);
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.LLAMA, 5, 4, 6));
        addBatsAndMonsters(builder);
        net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(surfaceBuilder);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder2);
        builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_MOUNTAIN).structureFeature(ConfiguredStructureFeatures.VILLAGE_TAIGA);
        DefaultBiomeFeatures.addLandCarvers(builder2);
        DefaultBiomeFeatures.addDefaultLakes(builder2);
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
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.EXTREME_HILLS).depth(depth).scale(scale).temperature(0.2F).downfall(0.3F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(method_21009(0.2F)).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    public static Biome method_21004() {
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
        return (new Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3344392).skyColor(method_21009(2.0F)).loopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_NETHER_WASTES)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome method_21005() {
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.OPTIONS.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 4, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 4, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.SKELETON, 0.7D, 0.15D).spawnCost(EntityType.GHAST, 0.7D, 0.15D).spawnCost(EntityType.ENDERMAN, 0.7D, 0.15D).spawnCost(EntityType.STRIDER, 0.7D, 0.15D).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 10, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 4, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).spawnCost(EntityType.SKELETON, 0.7D, 0.15D).spawnCost(EntityType.GHAST, 0.7D, 0.15D).spawnCost(EntityType.ENDERMAN, 0.7D, 0.15D).spawnCost(EntityType.STRIDER, 0.7D, 0.15D).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.SOUL_SAND_VALLEY).structureFeature(ConfiguredStructureFeatures.FORTRESS).structureFeature(ConfiguredStructureFeatures.NETHER_FOSSIL).structureFeature(ConfiguredStructureFeatures.BASTION_REMNANT).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA).feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ConfiguredFeatures.BASALT_PILLAR).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_CRIMSON_ROOTS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_SOUL_SAND);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1787717).skyColor(method_21009(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.ASH, 0.00625F)).loopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_SOUL_SAND_VALLEY)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome method_21006() {
        SpawnSettings spawnSettings;
        if (SpeedrunnerMod.OPTIONS.doomMode) {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 40, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 25, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 50, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        } else {
            spawnSettings = (new SpawnSettings.Builder()).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 25, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 25, 1, 4)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        }
        net.minecraft.world.biome.GenerationSettings.Builder builder = (new net.minecraft.world.biome.GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.BASALT_DELTAS).carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).structureFeature(ConfiguredStructureFeatures.FORTRESS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.DELTA).feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA_DOUBLE).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.SMALL_BASALT_COLUMNS).feature(GenerationStep.Feature.SURFACE_STRUCTURES, ConfiguredFeatures.LARGE_BASALT_COLUMNS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BASALT_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BLACKSTONE_BLOBS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_DELTA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPRING_CLOSED_DOUBLE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_GOLD_DELTAS).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_QUARTZ_DELTAS);
        DefaultBiomeFeatures.addAncientDebris(builder);
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(4341314).fogColor(6840176).skyColor(method_21009(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.118093334F)).loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_BASALT_DELTAS)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome method_21007() {
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
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(3343107).skyColor(method_21009(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.025F)).loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    public static Biome method_21008() {
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
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(1705242).skyColor(method_21009(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.WARPED_SPORE, 0.01428F)).loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP).moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D)).additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111D)).music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST)).build()).spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }

    private static int method_21009(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    public static ImmutableMap<Object, Object> makeStructuresMoreCommon() {
        return ImmutableMap.builder().put(StructureFeature.VILLAGE, new StructureConfig(getVillageSpacing(), getVillageSeparation(), 10387312)).put(StructureFeature.DESERT_PYRAMID, new StructureConfig(getDesertPyramidSpacing(), getDesertPyramidSeparation(), 14357617)).put(StructureFeature.IGLOO, new StructureConfig(getIglooSpacing(), getIglooSeparation(), 14357618)).put(StructureFeature.JUNGLE_PYRAMID, new StructureConfig(getJunglePyramidSpacing(), getJunglePyramidSeparation(), 14357619)).put(StructureFeature.SWAMP_HUT, new StructureConfig(getSwampHutSpacing(), getSwampHutSeparation(), 14357620)).put(StructureFeature.PILLAGER_OUTPOST, new StructureConfig(getPillagerOutpostSpacing(), getPillagerOutpostSeparation(), 165745296)).put(StructureFeature.STRONGHOLD, new StructureConfig(getStrongholdSpacing(), getStrongholdSeparation(), 0)).put(StructureFeature.MONUMENT, new StructureConfig(getMonumentSpacing(), getMonumentSeparation(), 10387313)).put(StructureFeature.END_CITY, new StructureConfig(getEndCitySpacing(), getEndCitySeparation(), 10387313)).put(StructureFeature.MANSION, new StructureConfig(getMansionSpacing(), getMansionSeparation(), 10387319)).put(StructureFeature.BURIED_TREASURE, new StructureConfig(getBuriedTreasureSpacing(), getBuriedTreasureSeparation(), 0)).put(StructureFeature.MINESHAFT, new StructureConfig(getMineshaftSpacing(), getMineshaftSeparation(), 0)).put(StructureFeature.RUINED_PORTAL, new StructureConfig(getRuinedPortalSpacing(), getRuinedPortalSeparation(), 34222645)).put(StructureFeature.SHIPWRECK, new StructureConfig(getShipwreckSpacing(), getShipwreckSeparation(), 165745295)).put(StructureFeature.OCEAN_RUIN, new StructureConfig(getOceanRuinSpacing(), getOceanRuinSeparation(), 14357621)).put(StructureFeature.BASTION_REMNANT, new StructureConfig(getBastionSpacing(), getBastionSeparation(), 30084232)).put(StructureFeature.FORTRESS, new StructureConfig(getFortressSpacing(), getFortressSeparation(), 30084232)).put(StructureFeature.NETHER_FOSSIL, new StructureConfig(getNetherFossilSpacing(), getNetherFossilSeparation(), 14357921)).build();
    }

    public static void addModdedOresAndMakeDiamondsMoreCommon(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.SPEEDRUNNER_ORE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.IGNEOUS_ORE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.DIAMOND_ORE);
    }

    public static void addModdedOresNether(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModConfiguredFeatures.NETHER_SPEEDRUNNER_ORE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModConfiguredFeatures.NETHER_IGNEOUS_ORE);
    }

    public static void makeAncientDebrisMoreCommon(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModConfiguredFeatures.ANCIENT_DEBRIS);
    }

    public static void method_21011(net.minecraft.world.biome.SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight) {
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

    public static void makeAnimalsMoreCommon(net.minecraft.world.biome.SpawnSettings.Builder builder) {
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 16, 4, 8));
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 4, 8));
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 4, 8));
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 8, 4, 8));
    }

    public static void makeDolphinsMoreCommon(net.minecraft.world.biome.SpawnSettings.Builder builder, int squidWeight, int squidMinGroupSize) {
        builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, squidWeight, squidMinGroupSize, 4));
        builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 10, 4, 8));
        builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 15, 1, 2));
        addBatsAndMonsters(builder);
    }

    public static void method_21012(net.minecraft.world.biome.SpawnSettings.Builder builder) {
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

    public static List<SpawnSettings.SpawnEntry> applyNetherFortressMobSpawns() {
        return SpeedrunnerMod.OPTIONS.doomMode ? ImmutableList.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 50, 1, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 75, 4, 12), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 20, 1, 4)) : ImmutableList.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4), new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4), new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2), new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 2), new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 2), new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 1));
    }

    public static NetherFortressGenerator.PieceData[] applyNetherFortressBridgeGeneration() {
        return new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 1), new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 50, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 10, 2)};
    }

    public static NetherFortressGenerator.PieceData[] applyNetherFortressCorridorGeneration() {
        return new NetherFortressGenerator.PieceData[]{new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 10, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 25, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 25, 3), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 2, true), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2), new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 20, 2)};
    }

    public static StrongholdGenerator.PieceData[] applyStrongholdGeneration() {
        return SpeedrunnerMod.OPTIONS.doomMode ? new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 10), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 50, 10), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 75, 10), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 50, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 5), new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, 6) {
            public boolean canGenerate(int chainLength) {
                return super.canGenerate(chainLength) && chainLength > 3;
            }
        }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 20, 1) {
            public boolean canGenerate(int chainLength) {
                return super.canGenerate(chainLength) && chainLength > 5;
            }
        }} : new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 20, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 10, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 10, 1), new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 10, 2), new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 2) {
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

    private static int getVillageSpacing() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 16 : 32;
    }

    private static int getVillageSeparation() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 9 : 8;
    }

    private static int getDesertPyramidSpacing() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 10 : 32;
    }

    private static int getDesertPyramidSeparation() {
        return 8;
    }

    private static int getIglooSpacing() {
        return 32;
    }

    private static int getIglooSeparation() {
        return 8;
    }

    private static int getJunglePyramidSpacing() {
        return 32;
    }

    private static int getJunglePyramidSeparation() {
        return 8;
    }

    private static int getSwampHutSpacing() {
        return 32;
    }

    private static int getSwampHutSeparation() {
        return 8;
    }

    private static int getPillagerOutpostSpacing() {
        return 32;
    }

    private static int getPillagerOutpostSeparation() {
        return 8;
    }

    private static int getStrongholdSpacing() {
        return 1;
    }

    private static int getStrongholdSeparation() {
        return 0;
    }

    private static int getMonumentSpacing() {
        return 32;
    }

    private static int getMonumentSeparation() {
        return 8;
    }

    private static int getEndCitySpacing() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 7 : 20;
    }

    private static int getEndCitySeparation() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 6 : 11;
    }

    private static int getMansionSpacing() {
        return 80;
    }

    private static int getMansionSeparation() {
        return 20;
    }

    private static int getBuriedTreasureSpacing() {
        return 1;
    }

    private static int getBuriedTreasureSeparation() {
        return 0;
    }

    private static int getMineshaftSpacing() {
        return 1;
    }

    private static int getMineshaftSeparation() {
        return 0;
    }

    private static int getRuinedPortalSpacing() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 9 : 40;
    }

    private static int getRuinedPortalSeparation() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 8 : 15;
    }

    private static int getShipwreckSpacing() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 10 : 24;
    }

    private static int getShipwreckSeparation() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 8 : 4;
    }

    private static int getOceanRuinSpacing() {
        return 20;
    }

    private static int getOceanRuinSeparation() {
        return 8;
    }

    private static int getBastionSpacing() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 9 : 27;
    }

    private static int getBastionSeparation() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 8 : 4;
    }

    private static int getFortressSpacing() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 8 : 27;
    }

    private static int getFortressSeparation() {
        return SpeedrunnerMod.OPTIONS.makeStructuresMoreCommon ? 7 : 4;
    }

    private static int getNetherFossilSpacing() {
        return 2;
    }

    private static int getNetherFossilSeparation() {
        return 1;
    }

    public static int getStrongholdMinY() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0 : 25;
    }

    public static int getStrongholdMaxY() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 19 : 36;
    }

    public static boolean fixNetherPortalBaseBlocks(World world, BlockPos blockPos, Direction direction) {
        if (!method_30366(world)) {
            return false;
        } else {
            BlockPos.Mutable mutable = blockPos.mutableCopy();
            boolean bl = false;
            Direction[] var5 = Direction.values();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Direction direction2 = var5[var7];
                if (world.getBlockState(mutable.set(blockPos).move(direction2)).isOf(Blocks.OBSIDIAN) || world.getBlockState(mutable.set(blockPos).move(direction2)).isOf(Blocks.CRYING_OBSIDIAN)) {
                    bl = true;
                    break;
                }
            }

            if (!bl) {
                return false;
            } else {
                Direction.Axis axis = direction.getAxis().isHorizontal() ? direction.rotateYCounterclockwise().getAxis() : Direction.Type.HORIZONTAL.randomAxis(world.random);
                return AreaHelper.method_30485(world, blockPos, axis).isPresent();
            }
        }
    }

    private static boolean method_30366(World world) {
        return world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER;
    }

    public static Item fixBeehiveBlock(ItemStack stack) {
        return UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
    }

    public static Item fixPumpkinBlock(ItemStack stack) {
        return UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
    }

    public static Item fixTntBlock(ItemStack stack) {
        return UniqueItemRegistry.TNT_BLOCK_IGNITERS.getDefaultItem(stack.getItem());
    }

    public static Item fixTripwireBlock(ItemStack stack) {
        return UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
    }

    public static Item fixEfficiencyEnchantment(ItemStack stack) {
        return UniqueItemRegistry.SHEARS.getDefaultItem(stack.getItem());
    }

    public static Item fixCrossbowItem(ItemStack stack) {
        return UniqueItemRegistry.CROSSBOW.getDefaultItem(stack.getItem());
    }

    @Deprecated
    public static ItemStack fixSpeedrunnerShears(ItemStack stack) {
        if (stack.getItem().getDefaultStack().getItem() == ModItems.SPEEDRUNNER_SHEARS) {
            ItemStack itemStack = new ItemStack(Items.SHEARS);
            itemStack.setCount(stack.getCount());
            itemStack.setTag(stack.getOrCreateTag());
            return itemStack;
        }

        return stack;
    }
}
