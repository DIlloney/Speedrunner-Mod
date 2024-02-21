package net.dillon8775.speedrunnermod.village;

import com.google.common.collect.ImmutableSet;
import net.dillon8775.speedrunnermod.block.ModBlocks;
import net.fabricmc.fabric.mixin.object.builder.PointOfInterestTypeAccessor;
import net.fabricmc.fabric.mixin.object.builder.VillagerProfessionAccessor;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

public class ModVillagerProfessions {
    protected static final PointOfInterestType RETIRED_SPEEDRUNNER_POI = registerPOI("speedrunner_poi", ModBlocks.SPEEDRUNNERS_WORKBENCH);
    public static final VillagerProfession RETIRED_SPEEDRUNNER = registerProfessions(RETIRED_SPEEDRUNNER_POI);

    public static VillagerProfession registerProfessions(PointOfInterestType type) {
        return Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("speedrunnermod", "retired_speedrunner"), VillagerProfessionAccessor.create("retired_speedrunner", type, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_ARMORER));
    }

    public static PointOfInterestType registerPOI(String name, Block block) {
        return Registry.register(Registry.POINT_OF_INTEREST_TYPE, new Identifier("speedrunnermod", name), PointOfInterestTypeAccessor.callCreate(name, ImmutableSet.copyOf(block.getStateManager().getStates()), 3, 1));
    }

    public static void init() {
        info("Initialized villager professions.");
    }
}