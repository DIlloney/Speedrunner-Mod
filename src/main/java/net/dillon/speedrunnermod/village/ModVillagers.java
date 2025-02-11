package net.dillon.speedrunnermod.village;

import com.google.common.collect.ImmutableSet;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code custom villager professions.}
 */
public class ModVillagers {
    protected static final PointOfInterestType RETIRED_SPEEDRUNNER_POI = registerPoi("speedrunner_poi", ModBlocks.SPEEDRUNNERS_WORKBENCH);
    public static final VillagerProfession RETIRED_SPEEDRUNNER = registerProfessions(RegistryKey.of(
            Registries.POINT_OF_INTEREST_TYPE.getKey(), ofSpeedrunnerMod("speedrunner_poi")));

    public static VillagerProfession registerProfessions(RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of("speedrunnermod", "retired_speedrunner"),
                VillagerProfessionBuilder.create().id(ofSpeedrunnerMod("retired_speedrunner")).workstation(type).workSound(SoundEvents.ENTITY_VILLAGER_WORK_ARMORER).build());
    }

    public static PointOfInterestType registerPoi(String name, Block block) {
        Identifier id = ofSpeedrunnerMod(name);
        return PointOfInterestHelper.register(id, 3, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    /**
     * Initializes the {@code Retired Speedrunner villager profession.}
     */
    public static void initializeVillagerProfessions() {}
}