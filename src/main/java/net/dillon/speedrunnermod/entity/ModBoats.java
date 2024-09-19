package net.dillon.speedrunnermod.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

/**
 * Used to create all the Speedrunner Mod {@code boats,} and these keys are used in several different mixins.
 * <p>See {@code directory} {@link net.dillon.speedrunnermod.mixin.main.entity.boat} for more.</p>
 */
public class ModBoats {
    public static final Identifier SPEEDRUNNER_BOAT_ID = Identifier.of(SpeedrunnerMod.MOD_ID, "speedrunner_boat");
    public static final Identifier SPEEDRUNNER_CHEST_BOAT_ID = Identifier.of(SpeedrunnerMod.MOD_ID, "speedrunner_chest_boat");
    public static final Identifier DEAD_SPEEDRUNNER_BOAT_ID = Identifier.of(SpeedrunnerMod.MOD_ID, "dead_speedrunner_boat");
    public static final Identifier DEAD_SPEEDRUNNER_CHEST_BOAT_ID = Identifier.of(SpeedrunnerMod.MOD_ID, "dead_speedrunner_chest_boat");
    public static final Identifier CRIMSON_BOAT_ID = Identifier.of(SpeedrunnerMod.MOD_ID, "crimson_boat");
    public static final Identifier CRIMSON_CHEST_BOAT_ID = Identifier.of(SpeedrunnerMod.MOD_ID, "crimson_chest_boat");
    public static final Identifier WARPED_BOAT_ID = Identifier.of(SpeedrunnerMod.MOD_ID, "warped_boat");
    public static final Identifier WARPED_CHEST_BOAT_ID = Identifier.of(SpeedrunnerMod.MOD_ID, "warped_chest_boat");

    public static final RegistryKey<TerraformBoatType> SPEEDRUNNER_BOAT_KEY = TerraformBoatTypeRegistry.createKey(SPEEDRUNNER_BOAT_ID);
    public static final RegistryKey<TerraformBoatType> DEAD_SPEEDRUNNER_BOAT_KEY = TerraformBoatTypeRegistry.createKey(DEAD_SPEEDRUNNER_BOAT_ID);
    public static final RegistryKey<TerraformBoatType> CRIMSON_BOAT_KEY = TerraformBoatTypeRegistry.createKey(CRIMSON_BOAT_ID);
    public static final RegistryKey<TerraformBoatType> WARPED_BOAT_KEY = TerraformBoatTypeRegistry.createKey(WARPED_BOAT_ID);

    public static final TerraformBoatType SPEEDRUNNER_BOAT = new TerraformBoatType.Builder().item(ModItems.SPEEDRUNNER_BOAT).chestItem(ModItems.SPEEDRUNNER_CHEST_BOAT).planks(ModBlocks.SPEEDRUNNER_PLANKS.asItem()).build();
    public static final TerraformBoatType DEAD_SPEEDRUNNER_BOAT = new TerraformBoatType.Builder().item(ModItems.DEAD_SPEEDRUNNER_BOAT).chestItem(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT).planks(ModBlocks.DEAD_SPEEDRUNNER_PLANKS.asItem()).build();
    public static final TerraformBoatType CRIMSON_BOAT = new TerraformBoatType.Builder().item(ModItems.CRIMSON_BOAT).chestItem(ModItems.CRIMSON_CHEST_BOAT).planks(Blocks.CRIMSON_PLANKS.asItem()).build();
    public static final TerraformBoatType WARPED_BOAT = new TerraformBoatType.Builder().item(ModItems.WARPED_BOAT).chestItem(ModItems.WARPED_CHEST_BOAT).planks(Blocks.WARPED_PLANKS.asItem()).build();

    /**
     * Registers all speedrunner mod {@code boats.}
     */
    public static void registerBoats() {
        registerBoat(SPEEDRUNNER_BOAT_KEY, SPEEDRUNNER_BOAT);
        registerBoat(DEAD_SPEEDRUNNER_BOAT_KEY, DEAD_SPEEDRUNNER_BOAT);
        registerBoat(CRIMSON_BOAT_KEY, CRIMSON_BOAT);
        registerBoat(WARPED_BOAT_KEY, WARPED_BOAT);
    }

    /**
     * Registers a {@code boat}.
     */
    private static void registerBoat(RegistryKey<TerraformBoatType> key, TerraformBoatType boat) {
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, key, boat);
    }

    /**
     * Determines {@code "fireproof"} boats.
     */
    public static boolean isFireproofBoat(TerraformBoatType terraformBoatType) {
        return terraformBoatType.getItem().getDefaultStack().isIn(ModItemTags.FIREPROOF_BOATS) || terraformBoatType.getChestItem().getDefaultStack().isIn(ModItemTags.FIREPROOF_CHEST_BOATS);
    }

    /**
     * <p>Determines {@code "faster"} boats, boats that ride slightly faster than normal boats.
     */
    public static boolean isFastBoat(TerraformBoatType terraformBoatType) {
        return terraformBoatType.getItem().getDefaultStack().isIn(ModItemTags.FASTER_BOATS) || terraformBoatType.getChestItem().getDefaultStack().isIn(ModItemTags.FASTER_CHEST_BOATS);
    }
}