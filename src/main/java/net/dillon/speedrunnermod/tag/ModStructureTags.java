package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * All Speedrunner Mod {@code structure tags.} These are only really used because vanilla Minecraft doesn't have a tag for these structures.
 */
public class ModStructureTags {
    public static final TagKey<Structure> ANCIENT_CITIES = of("ancient_cities");
    public static final TagKey<Structure> BASTIONS = of("bastions");
    public static final TagKey<Structure> DESERT_PYRAMIDS = of("desert_pyramids");
    public static final TagKey<Structure> FORTRESSES = of("fortresses");

    /**
     * Registers a {@code structure tag.}
     */
    private static TagKey<Structure> of(String path) {
        return TagKey.of(RegistryKeys.STRUCTURE, Identifier.of(SpeedrunnerMod.MOD_ID, path));
    }

    public static void init() {
        info("Registered structure tags.");
    }
}