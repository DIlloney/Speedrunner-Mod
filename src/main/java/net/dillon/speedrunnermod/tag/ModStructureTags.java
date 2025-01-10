package net.dillon.speedrunnermod.tag;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.gen.structure.Structure;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

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
        return TagKey.of(RegistryKeys.STRUCTURE, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code structure tags.}
     */
    public static void initializeStructureTags() {}
}