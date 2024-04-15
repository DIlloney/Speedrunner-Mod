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

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * See {@link net.dillon.speedrunnermod.mixin.main.entity.boat.BoatEntityMixin}, {@link net.dillon.speedrunnermod.mixin.main.entity.boat.TerraformBoatEntityMixin}, {@link net.dillon.speedrunnermod.mixin.main.entity.boat.TerraformChestBoatEntityMixin} and {@link net.dillon.speedrunnermod.mixin.main.entity.basic.EntityMixin} for modded boats functions and code.
 */
public class ModBoats {
    public static final Identifier SPEEDRUNNER_BOAT_ID = new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_boat");
    public static final Identifier SPEEDRUNNER_CHEST_BOAT_ID = new Identifier(SpeedrunnerMod.MOD_ID, "speedrunner_chest_boat");
    public static final Identifier DEAD_SPEEDRUNNER_BOAT_ID = new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_boat");
    public static final Identifier DEAD_SPEEDRUNNER_CHEST_BOAT_ID = new Identifier(SpeedrunnerMod.MOD_ID, "dead_speedrunner_chest_boat");
    public static final Identifier CRIMSON_BOAT_ID = new Identifier(SpeedrunnerMod.MOD_ID, "crimson_boat");
    public static final Identifier CRIMSON_CHEST_BOAT_ID = new Identifier(SpeedrunnerMod.MOD_ID, "crimson_chest_boat");
    public static final Identifier WARPED_BOAT_ID = new Identifier(SpeedrunnerMod.MOD_ID, "warped_boat");
    public static final Identifier WARPED_CHEST_BOAT_ID = new Identifier(SpeedrunnerMod.MOD_ID, "warped_chest_boat");

    public static final RegistryKey<TerraformBoatType> SPEEDRUNNER_BOAT_KEY = TerraformBoatTypeRegistry.createKey(SPEEDRUNNER_BOAT_ID);
    public static final RegistryKey<TerraformBoatType> DEAD_SPEEDRUNNER_BOAT_KEY = TerraformBoatTypeRegistry.createKey(DEAD_SPEEDRUNNER_BOAT_ID);
    public static final RegistryKey<TerraformBoatType> CRIMSON_BOAT_KEY = TerraformBoatTypeRegistry.createKey(CRIMSON_BOAT_ID);
    public static final RegistryKey<TerraformBoatType> WARPED_BOAT_KEY = TerraformBoatTypeRegistry.createKey(WARPED_BOAT_ID);

    public static final TerraformBoatType SPEEDRUNNER_BOAT = new TerraformBoatType.Builder().item(ModItems.SPEEDRUNNER_BOAT).chestItem(ModItems.SPEEDRUNNER_CHEST_BOAT).planks(ModBlocks.SPEEDRUNNER_PLANKS.asItem()).build();
    public static final TerraformBoatType DEAD_SPEEDRUNNER_BOAT = new TerraformBoatType.Builder().item(ModItems.DEAD_SPEEDRUNNER_BOAT).chestItem(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT).planks(ModBlocks.DEAD_SPEEDRUNNER_PLANKS.asItem()).build();
    public static final TerraformBoatType CRIMSON_BOAT = new TerraformBoatType.Builder().item(ModItems.CRIMSON_BOAT).chestItem(ModItems.CRIMSON_CHEST_BOAT).planks(Blocks.CRIMSON_PLANKS.asItem()).build();
    public static final TerraformBoatType WARPED_BOAT = new TerraformBoatType.Builder().item(ModItems.WARPED_BOAT).chestItem(ModItems.WARPED_CHEST_BOAT).planks(Blocks.WARPED_PLANKS.asItem()).build();

    public static void init() {
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, SPEEDRUNNER_BOAT_KEY, SPEEDRUNNER_BOAT);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, DEAD_SPEEDRUNNER_BOAT_KEY, DEAD_SPEEDRUNNER_BOAT);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, CRIMSON_BOAT_KEY, CRIMSON_BOAT);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, WARPED_BOAT_KEY, WARPED_BOAT);

        info("Initialized speedrunner mod boats (crimson, warped and speedrunner).");
    }

    /**
     * Determines {@code "fireproof"} boats.
     */
    public static boolean isFireproofBoat(TerraformBoatType terraformBoatType) {
        return terraformBoatType.getItem().getDefaultStack().isIn(ModItemTags.FIREPROOF_BOATS) || terraformBoatType.getChestItem().getDefaultStack().isIn(ModItemTags.FIREPROOF_CHEST_BOATS);
    }

    /**
     * <p>Determines {@code "faster"} boats, which is only the speedrunner boats.
     */
    public static boolean isFastBoat(TerraformBoatType terraformBoatType) {
        return terraformBoatType.getItem().getDefaultStack().isIn(ModItemTags.FASTER_BOATS) || terraformBoatType.getChestItem().getDefaultStack().isIn(ModItemTags.FASTER_CHEST_BOATS);
    }
}