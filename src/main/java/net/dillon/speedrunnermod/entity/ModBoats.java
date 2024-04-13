package net.dillon.speedrunnermod.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.mixin.main.entity.boat.BoatEntityMixin;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * See {@link BoatEntityMixin}, {@link net.dillon.speedrunnermod.mixin.main.entity.boat.TerraformBoatEntityMixin} and {@link net.dillon.speedrunnermod.mixin.main.entity.basic.EntityMixin} for modded boats functions and code.
 */
public class ModBoats {
    public static final Identifier SPEEDRUNNER_BOAT_ID = new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_boat");
    public static final Identifier CRIMSON_BOAT_ID = new Identifier(SpeedrunnerMod.MOD_ID, "crimson_boat");
    public static final Identifier WARPED_BOAT_ID = new Identifier(SpeedrunnerMod.MOD_ID, "warped_boat");

    public static final RegistryKey<TerraformBoatType> SPEEDRUNNER_BOAT_KEY = RegistryKey.of(TerraformBoatTypeRegistry.INSTANCE.getKey(), SPEEDRUNNER_BOAT_ID);
    public static final RegistryKey<TerraformBoatType> CRIMSON_BOAT_KEY = RegistryKey.of(TerraformBoatTypeRegistry.INSTANCE.getKey(), CRIMSON_BOAT_ID);
    public static final RegistryKey<TerraformBoatType> WARPED_BOAT_KEY = RegistryKey.of(TerraformBoatTypeRegistry.INSTANCE.getKey(), WARPED_BOAT_ID);

    public static final TerraformBoatType SPEEDRUNNER_BOAT = new TerraformBoatType.Builder().item(ModItems.SPEEDRUNNER_BOAT).build();
    public static final TerraformBoatType CRIMSON_BOAT = new TerraformBoatType.Builder().item(ModItems.CRIMSON_BOAT).build();
    public static final TerraformBoatType WARPED_BOAT = new TerraformBoatType.Builder().item(ModItems.WARPED_BOAT).build();

    public static void init() {
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, SPEEDRUNNER_BOAT_KEY, SPEEDRUNNER_BOAT);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, CRIMSON_BOAT_KEY, CRIMSON_BOAT);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, WARPED_BOAT_KEY, WARPED_BOAT);

        info("Initialized speedrunner mod boats (crimson, warped and speedrunner).");
    }

    /**
     * Determines {@code "fireproof"} boats.
     */
    public static boolean isFireproofBoat(TerraformBoatType terraformBoatType) {
        return terraformBoatType.getItem().getDefaultStack().isIn(ModItemTags.FIREPROOF_BOATS);
    }

    /**
     * <p>Determines {@code "faster"} boats, which is only the speedrunner boats.
     */
    public static boolean isFastBoat(TerraformBoatType terraformBoatType) {
        return terraformBoatType.getItem().getDefaultStack().isIn(ModItemTags.FASTER_BOATS);
    }
}