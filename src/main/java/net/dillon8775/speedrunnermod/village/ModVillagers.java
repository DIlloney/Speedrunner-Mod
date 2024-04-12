<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.village;

import com.google.common.collect.ImmutableSet;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

public class ModVillagers {
    protected static final PointOfInterestType RETIRED_SPEEDRUNNER_POI = registerPOI("speedrunner_poi", ModBlocks.SPEEDRUNNERS_WORKBENCH);
    public static final VillagerProfession RETIRED_SPEEDRUNNER = registerProfessions(RegistryKey.of(
            Registry.POINT_OF_INTEREST_TYPE_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_poi")));

    public static VillagerProfession registerProfessions(RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("speedrunnermod", "retired_speedrunner"),
                VillagerProfessionBuilder.create().id(new Identifier(SpeedrunnerMod.MOD_ID, "retired_speedrunner")).workstation(type).workSound(SoundEvents.ENTITY_VILLAGER_WORK_ARMORER).build());
    }

    public static PointOfInterestType registerPOI(String name, Block block) {
        Identifier id = new Identifier(SpeedrunnerMod.MOD_ID, name);
        return PointOfInterestHelper.register(id, 3, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    public static void init() {
        info("Initialized villager professions.");
    }
=======
package net.dillon8775.speedrunnermod.village;

import com.google.common.collect.ImmutableSet;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

public class ModVillagers {
    protected static final PointOfInterestType RETIRED_SPEEDRUNNER_POI = registerPOI("speedrunner_poi", ModBlocks.SPEEDRUNNERS_WORKBENCH);
    public static final VillagerProfession RETIRED_SPEEDRUNNER = registerProfessions(RegistryKey.of(
            Registry.POINT_OF_INTEREST_TYPE_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_poi")));

    public static VillagerProfession registerProfessions(RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("speedrunnermod", "retired_speedrunner"),
                VillagerProfessionBuilder.create().id(new Identifier(SpeedrunnerMod.MOD_ID, "retired_speedrunner")).workstation(type).workSound(SoundEvents.ENTITY_VILLAGER_WORK_ARMORER).build());
    }

    public static PointOfInterestType registerPOI(String name, Block block) {
        Identifier id = new Identifier(SpeedrunnerMod.MOD_ID, name);
        return PointOfInterestHelper.register(id, 3, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    public static void init() {
        info("Initialized villager professions.");
    }
>>>>>>> Stashed changes
}