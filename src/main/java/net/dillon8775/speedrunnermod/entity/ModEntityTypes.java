package net.dillon8775.speedrunnermod.entity;

import net.dillon8775.speedrunnermod.tag.ModBlockTags;
import net.minecraft.entity.vehicle.BoatEntity;

public class ModEntityTypes {
    /**
     * See {@link net.dillon8775.speedrunnermod.mixin.main.boat.BoatEntityMixin}, {@link net.dillon8775.speedrunnermod.mixin.main.boat.BoatEntityTypeMixin} and {@link net.dillon8775.speedrunnermod.mixin.main.entity.EntityMixin} for modded boats functions and code.
     */
    public static BoatEntity.Type SPEEDRUNNER_BOAT;
    public static BoatEntity.Type CRIMSON_BOAT;
    public static BoatEntity.Type WARPED_BOAT;

    public static boolean isFireproofBoat(BoatEntity.Type boatType) {
        return boatType.getBaseBlock().getDefaultState().isIn(ModBlockTags.FIREPROOF_BOAT_BASE_BLOCKS);
    }
}