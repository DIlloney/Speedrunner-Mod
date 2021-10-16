package net.dilloney.speedrunnermod.util;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.mixin.ModMixins;
import net.dilloney.speedrunnermod.option.OptionsFileManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

/**
 * A helper class mainly used for {@linkplain ModMixins}.
 */
public final class ModHelper {

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

    public static boolean getOldStructureSettings() {
        return SpeedrunnerMod.WORLD_OPTIONS.getVillageSpacing() == 12 && SpeedrunnerMod.WORLD_OPTIONS.getVillageSeparation() == 9 && SpeedrunnerMod.WORLD_OPTIONS.getDesertPyramidSpacing() == 10 && SpeedrunnerMod.WORLD_OPTIONS.getDesertPyramidSeparation() == 8 && SpeedrunnerMod.WORLD_OPTIONS.getIglooSpacing() == 18 && SpeedrunnerMod.WORLD_OPTIONS.getIglooSeparation() == 14 && SpeedrunnerMod.WORLD_OPTIONS.getShipwreckSpacing() == 10 && SpeedrunnerMod.WORLD_OPTIONS.getShipwreckSeparation() == 8 && SpeedrunnerMod.WORLD_OPTIONS.getBastionSpacing() == 10 && SpeedrunnerMod.WORLD_OPTIONS.getBastionSeparation() == 8 && SpeedrunnerMod.WORLD_OPTIONS.getFortressSpacing() == 11 && SpeedrunnerMod.WORLD_OPTIONS.getFortressSeparation() == 9;
    }

    public static boolean getMakeWorldStructureSettings() {
        return SpeedrunnerMod.WORLD_OPTIONS.getVillageSpacing() == 5 && SpeedrunnerMod.WORLD_OPTIONS.getVillageSeparation() == 3 && SpeedrunnerMod.WORLD_OPTIONS.getDesertPyramidSpacing() == 8 && SpeedrunnerMod.WORLD_OPTIONS.getDesertPyramidSeparation() == 3 && SpeedrunnerMod.WORLD_OPTIONS.getIglooSpacing() == 2 && SpeedrunnerMod.WORLD_OPTIONS.getIglooSeparation() == 1 && SpeedrunnerMod.WORLD_OPTIONS.getJunglePyramidSpacing() == 3 && SpeedrunnerMod.WORLD_OPTIONS.getJunglePyramidSeparation() == 1 && SpeedrunnerMod.WORLD_OPTIONS.getSwampHutSpacing() == 2 && SpeedrunnerMod.WORLD_OPTIONS.getSwampHutSeparation() == 1 && SpeedrunnerMod.WORLD_OPTIONS.getPillagerOutpostSpacing() == 8 && SpeedrunnerMod.WORLD_OPTIONS.getPillagerOutpostSeparation() == 6 && SpeedrunnerMod.WORLD_OPTIONS.getMonumentSpacing() == 5 && SpeedrunnerMod.WORLD_OPTIONS.getMonumentSeparation() == 2 && SpeedrunnerMod.WORLD_OPTIONS.getEndCitySpacing() == 3 && SpeedrunnerMod.WORLD_OPTIONS.getEndCitySeparation() == 1 && SpeedrunnerMod.WORLD_OPTIONS.getMansionSpacing() == 2 && SpeedrunnerMod.WORLD_OPTIONS.getMansionSeparation() == 1 && SpeedrunnerMod.WORLD_OPTIONS.getRuinedPortalSpacing() == 6 && SpeedrunnerMod.WORLD_OPTIONS.getRuinedPortalSeparation() == 3 && SpeedrunnerMod.WORLD_OPTIONS.getOceanRuinSpacing() == 3 && SpeedrunnerMod.WORLD_OPTIONS.getOceanRuinSeparation() == 2 && SpeedrunnerMod.WORLD_OPTIONS.getBastionSpacing() == 5 && SpeedrunnerMod.WORLD_OPTIONS.getBastionSeparation() == 3 && SpeedrunnerMod.WORLD_OPTIONS.getFortressSpacing() == 4 && SpeedrunnerMod.WORLD_OPTIONS.getFortressSeparation() == 2;
    }

    public static float getFallDamageMultiplier() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.0F : 0.7F;
    }

    public static double getEnderDragonMaxHealth() {
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

    public static double getWitherMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 300.0D : 100.0D;
    }

    public static double getAbstractSkeletonMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.3D : 0.25D;
    }

    public static int getAbstractSkeletonShootSpeedBow() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 10 : 20;
    }

    public static int getAbstractSkeletonShootSpeedSpeedrunnerBow() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 5 : 15;
    }

    public static double getCreeperMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.3D : 0.25D;
    }

    public static boolean getCreeperIgnitions(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        return itemStack.getItem() == Items.FLINT_AND_STEEL || itemStack.getItem() == ModItems.SPEEDRUNNER_FLINT_AND_STEEL;
    }

    public static double getBlazeAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    public static double getBlazeFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 64.0D : 16.0D;
    }

    public static int getBlazeFireballCooldownAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 60 : 120;
    }

    public static double getElderGuardianAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    public static double getElderGuardianMaxHealth() {
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

    public static double getEndermanMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 60.0D : 25.0D;
    }

    public static double getEndermanAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    public static double getEndermanFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 72.0D : 12.0D;
    }

    public static double getGhastMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 20.0D : 5.0D;
    }

    public static double getGhastFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 100.0D : 16.0D;
    }

    public static int getGhastFireballCooldown() {
        return SpeedrunnerMod.OPTIONS.doomMode ? -5 : -40;
    }

    public static double getGiantMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 300.0D : 100.0D;
    }

    public static double getGiantMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.3500000528343624D : 0.5D;
    }

    public static double getGiantAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 10.0D : 50.0D;
    }

    public static double getGiantAttackKnockback() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.0D : 0.0D;
    }

    public static float getGiantSoundVolume() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 5.0F : 1.0F;
    }

    public static double getGuardianAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 7.0D : 3.0D;
    }

    public static double getGuardianFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 24.0D : 8.0D;
    }

    public static double getGuardianMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 50.0D : 15.0D;
    }

    public static double getHoglinMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 60.0D : 25.0D;
    }

    public static double getHoglinKnockbackResistance() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.7000000238518589D : 0.6000000238418579D;
    }

    public static double getHoglinAttackKnockback() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.2D : 0.5D;
    }

    public static double getHoglinAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    public static int getMagmaCubeTicksUntilNextJumpAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 20 : 100;
    }

    public static float getMagmaCubeAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 2.2F : 1.5F;
    }

    public static boolean getPiglinSafeArmor(ItemStack stack) {
        return stack.getItem() == ModItems.GOLDEN_SPEEDRUNNER_HELMET || stack.getItem() == ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE || stack.getItem() == ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS || stack.getItem() == ModItems.GOLDEN_SPEEDRUNNER_BOOTS;
    }

    public static double getPiglinMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 32.0D : 16.0D;
    }

    public static double getPiglinAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 2.0D;
    }

    public static double getPillagerMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 32.0D : 12.0D;
    }

    public static double getPillagerAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    public static double getPillagerFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 32.0D : 16.0D;
    }

    public static double getRavagerMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 100.0D : 50.0D;
    }

    public static double getRavagerAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 16.0D : 10.0D;
    }

    public static double getRavagerAttackKnockback() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.6D : 1.1D;
    }

    public static double getRavagerFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 64.0D : 32.0D;
    }

    public static double getShulkerMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 32.0D : 20.0D;
    }

    public static int getSilverfishDelayAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 20 : 100;
    }

    public static int getSlimeTicksUntilNextJumpAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 20 : 100;
    }

    public static double getVexMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 7.0D : 14.0D;
    }

    public static double getVexAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 3.0D : 4.0D;
    }

    public static float getVexDecayAmount() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 100.0F : 1.0F;
    }

    public static double getVindicatorFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 48.0D : 12.0D;
    }

    public static double getVindicatorMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 20.0D : 24.0D;
    }

    public static double getWitchMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 26.0D : 14.0D;
    }

    public static double getWitchMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.35D : 0.25D;
    }

    public static double getWitherSkeletonAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 10.0D : 1.0D;
    }

    public static int getWitherSkeletonEffectDuration() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 200 : 60;
    }

    public static double getZoglinMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 60.0D : 25.0D;
    }

    public static double getZoglinKnockbackResistance() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.7000000238518589D : 0.6000000238418579D;
    }

    public static double getZoglinAttackKnockback() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.2D : 0.5D;
    }

    public static double getZoglinAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 8.0D : 4.0D;
    }

    public static double getZombieFollowRange() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 50.0D : 25.0D;
    }

    public static double getZombieMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.33000000417232513D : 0.23000000417232513D;
    }

    public static double getZombieAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 7.0D : 2.0D;
    }

    public static double getZombieGenericArmor() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 4.0D : 1.0D;
    }

    public static double getZombifiedPiglinSpawnReinforcements() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 1.0D : 0.0D;
    }

    public static double getZombifiedPiglinMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.33000000427232513D : 0.23000000427232513D;
    }

    public static double getZombifiedPiglinAttackDamage() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 7.0D : 2.0D;
    }

    public static double getIronGolemMaxHealth() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 100.0D : 50.0D;
    }

    public static double getIronGolemMovementSpeed() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.3D : 0.25D;
    }

    public static double getIronGolemKnockbackResistance() {
        return SpeedrunnerMod.OPTIONS.doomMode ? 0.7D : 0.5D;
    }

    public static double getIronGolemAttackDamage() {
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

    public static Item fixCrossbowItem(ItemStack stack) {
        return UniqueItemRegistry.CROSSBOW.getDefaultItem(stack.getItem());
    }

    public static String getGameTimeText() {
        return SpeedrunnerMod.TIMER_OPTIONS.boldText ? "§lGame Time§r" : "Game Time";
    }

    public static String getRealTimeText() {
        return SpeedrunnerMod.TIMER_OPTIONS.boldText ? "§lReal Time§r" : "Real Time";
    }

    public static String getOverworldText() {
        return SpeedrunnerMod.TIMER_OPTIONS.boldText ? "§lOverworld§r" : "Overworld";
    }

    public static String getNetherText() {
        return SpeedrunnerMod.TIMER_OPTIONS.boldText ? "§lNether§r" : "Nether";
    }

    public static String getStrongholdText() {
        return SpeedrunnerMod.TIMER_OPTIONS.boldText ? "§lStronghold§r" : "Stronghold";
    }

    public static String getFinishedText() {
        return SpeedrunnerMod.TIMER_OPTIONS.boldText ? "§lFinished§r" : "Finished";
    }

    public static String getSeedText() {
        return SpeedrunnerMod.TIMER_OPTIONS.boldText ? "§lSeed:§r %s" : "Seed: %s";
    }

    public static void fixOptions() {
        if (SpeedrunnerMod.MISC_OPTIONS.getStrongholdCount() < 64) {
            SpeedrunnerMod.MISC_OPTIONS.setStrongholdCount(128);
            SpeedrunnerMod.LOGGER.info("You cannot set the Stronghold Count below 64! Setting it to 128.");
            OptionsFileManager.saveMisc();
        }

        if (SpeedrunnerMod.MISC_OPTIONS.getStrongholdCount() > 256) {
            SpeedrunnerMod.MISC_OPTIONS.setStrongholdCount(128);
            SpeedrunnerMod.LOGGER.info("You cannot set the Stronghold Count above 256! Setting it to 128.");
            OptionsFileManager.saveMisc();
            OptionsFileManager.loadMisc();
        }

        if (SpeedrunnerMod.MISC_OPTIONS.getEnderEyesLifespan() < 40) {
            SpeedrunnerMod.MISC_OPTIONS.setEnderEyesLifespan(40);
            SpeedrunnerMod.LOGGER.info("You cannot set the Ender Eyes Lifespan below 40! Setting it to 40.");
            OptionsFileManager.saveMisc();
            OptionsFileManager.loadMisc();
        }

        if (SpeedrunnerMod.MISC_OPTIONS.getEnderEyesLifespan() > 120) {
            SpeedrunnerMod.MISC_OPTIONS.setEnderEyesLifespan(40);
            SpeedrunnerMod.LOGGER.info("You cannot set the Ender Eyes Lifespan above 120! Setting it to 40.");
            OptionsFileManager.saveMisc();
            OptionsFileManager.loadMisc();
        }
    }
}
