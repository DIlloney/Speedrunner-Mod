package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.option.ModOptions;
import net.minecraft.structure.StructureSets;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * This mixin makes structures generate more commonly, as it was changed in the Minecraft 1.18.2 update.
 * <p>With {@link ModifyArgs}, we can get the current spacing and separation values of each structure, using {@code ordinals}, and replace them with our {@link net.dillon8775.speedrunnermod.SpeedrunnerMod} values.</p>
 */
@Mixin(StructureSets.class)
public interface StructureSetsMixin {
    // ORDINALS (for I;I;SpreadType):
    // 0 = village
    // 1 = desert pyramid
    // 3 = jungle pyramid
    // 6 = ocean monument
    // 7 = woodland mansion
    // 8 = ruined portal
    // 9 = shipwreck
    // 11 = nether complexes
    // 13 = end city

    // no ordinal for stronghold because it uses ConcentricRingsStructurePlacement

    /**
     * Changes the villages {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 0))
    private static void modifyVillageConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, SpeedrunnerMod.getVillageSpacing());
            args.set(1, SpeedrunnerMod.getVillageSeparation());
        }
    }

    /**
     * Changes the desert pyramids {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 1))
    private static void modifyDesertPyramidConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, SpeedrunnerMod.getDesertPyramidSpacing());
            args.set(1, SpeedrunnerMod.getDesertPyramidSeparation());
        }
    }

    /**
     * Changes the jungle pyramids {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 3))
    private static void modifyJunglePyramidConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, SpeedrunnerMod.getJunglePyramidSpacing());
            args.set(1, SpeedrunnerMod.getJunglePyramidSeparation());
        }
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 5))
    private static void modifyAncientCitiesConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, SpeedrunnerMod.getAncientCitySpacing());
            args.set(1, SpeedrunnerMod.getAncientCitySeparation());
        }
    }

    /**
     * Changes the pillager outposts {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(Lnet/minecraft/util/math/Vec3i;Lnet/minecraft/world/gen/chunk/placement/StructurePlacement$FrequencyReductionMethod;FILjava/util/Optional;IILnet/minecraft/world/gen/chunk/placement/SpreadType;)V", ordinal = 0))
    private static void modifyPillagerOutpostConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(5, SpeedrunnerMod.getPillagerOutpostSpacing());
            args.set(6, SpeedrunnerMod.getPillagerOutpostSeparation());
        }
    }

    /**
     * Changes the end city {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 13))
    private static void modifyEndCityConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, SpeedrunnerMod.getEndCitySpacing());
            args.set(1, SpeedrunnerMod.getEndCitySeparation());
        }
    }

    /**
     * Changes the woodland mansions {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 7))
    private static void modifyWoodlandMansionConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, SpeedrunnerMod.getWoodlandMansionSpacing());
            args.set(1, SpeedrunnerMod.getWoodlandMansionSeparation());
        }
    }

    /**
     * Changes the ruined portals {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 8))
    private static void modifyRuinedPortalConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, SpeedrunnerMod.getRuinedPortalSpacing());
            args.set(1, SpeedrunnerMod.getRuinedPortalSeparation());
        }
    }

    /**
     * Changes the shipwrecks {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 9))
    private static void modifyShipwreckSpacing(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, SpeedrunnerMod.getShipwreckSpacing());
            args.set(1, SpeedrunnerMod.getShipwreckSeparation());
        }
    }

    /**
     * Changes the nether complexes (fortresses and bastions) {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 11))
    private static void modifyNetherComplexesConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, SpeedrunnerMod.getNetherComplexesSpacing());
            args.set(1, SpeedrunnerMod.getNetherComplexesSeparation());
        }
    }

    /**
     * Changes the stronghold config directly.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/ConcentricRingsStructurePlacement;<init>(IIILnet/minecraft/util/registry/RegistryEntryList;)V"))
    private static void modifyStrongholdConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, options().main.strongholdDistance);
            args.set(1, options().main.strongholdSpread);
            args.set(2, options().main.strongholdCount);
        }
    }

    /**
     * Makes sure {@link net.dillon8775.speedrunnermod.option.ModOptions.StructureSpawnRates} isn't {@code off} before changing the config values.
     */
    @Unique
    private static boolean isStructureSpawnRates() {
        return options().main.structureSpawnRates != ModOptions.StructureSpawnRates.DISABLED;
    }
}