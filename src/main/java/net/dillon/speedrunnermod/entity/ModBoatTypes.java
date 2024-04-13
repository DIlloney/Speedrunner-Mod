package net.dillon.speedrunnermod.entity;

import net.dillon.speedrunnermod.tag.ModBlockTags;
import net.minecraft.entity.vehicle.BoatEntity;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * See {@link net.dillon.speedrunnermod.mixin.main.boat.BoatEntityMixin}, {@link net.dillon.speedrunnermod.mixin.main.boat.BoatEntityTypeMixin} and {@link net.dillon.speedrunnermod.mixin.main.entity.basic.EntityMixin} for modded boats functions and code.
 */
public class ModBoatTypes {
    public static BoatEntity.Type SPEEDRUNNER;
    public static BoatEntity.Type CRIMSON;
    public static BoatEntity.Type WARPED;

    /**
     * <p>Determines {@code "fireproof"} boats.</p>
     * <p>See {@link net.dillon.speedrunnermod.mixin.main.boat.BoatEntityMixin} for more.</p>
     */
    public static boolean isFireproofBoat(BoatEntity.Type boatType) {
        return false; /*boatType.getBaseBlock().getDefaultState().isIn(ModBlockTags.FIREPROOF_BOAT_BASE_BLOCKS);*/
    }

    /**
     * <p>Determines {@code "faster"} boats, which is only the speedrunner boats.</p>
     */
    public static boolean isFastBoat(BoatEntity.Type boatType) {
        return false; /*boatType.getBaseBlock().getDefaultState().isIn(ModBlockTags.FASTER_BOAT_BASE_BLOCKS);*/
    }

    public static void init() {
        info("Initialized speedrunner boat types (crimson, warped and speedrunner).");
    }
}