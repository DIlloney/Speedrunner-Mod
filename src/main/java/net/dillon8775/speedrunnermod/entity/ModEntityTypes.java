package net.dillon8775.speedrunnermod.entity;

import net.dillon8775.speedrunnermod.tag.ModBlockTags;
import net.minecraft.entity.vehicle.BoatEntity;

/**
 * See {@link net.dillon8775.speedrunnermod.mixin.main.boat.BoatEntityMixin}, {@link net.dillon8775.speedrunnermod.mixin.main.boat.BoatEntityTypeMixin} and {@link net.dillon8775.speedrunnermod.mixin.main.entity.basic.EntityMixin} for modded boats functions and code.
 */
public class ModEntityTypes {
    public static BoatEntity.Type SPEEDRUNNER_BOAT;
    public static BoatEntity.Type CRIMSON_BOAT;
    public static BoatEntity.Type WARPED_BOAT;

    /**
     * <p>Determines {@code "fireproof"} boats.</p>
     * <p>See {@link net.dillon8775.speedrunnermod.mixin.main.boat.BoatEntityMixin} for more.</p>
     */
    public static boolean isFireproofBoat(BoatEntity.Type boatType) {
        return boatType.getBaseBlock().getDefaultState().isIn(ModBlockTags.FIREPROOF_BOAT_BASE_BLOCKS);
    }

    /**
     * <p>Determines {@code "faster"} boats.</p>
     * <p>This feature is still unconfirmed.</p>
     */
    public static boolean isFastBoat(BoatEntity.Type boatType) {
        return boatType.getBaseBlock().getDefaultState().isIn(ModBlockTags.FASTER_BOAT_BASE_BLOCKS);
    }
}