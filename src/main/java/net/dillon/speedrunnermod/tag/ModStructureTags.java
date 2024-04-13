package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

public class ModStructureTags {
    public static final TagKey<Structure> ANCIENT_CITIES = TagKey.of(RegistryKeys.STRUCTURE, new Identifier(SpeedrunnerMod.MOD_ID, "ancient_cities"));
    public static final TagKey<Structure> BASTIONS = TagKey.of(RegistryKeys.STRUCTURE, new Identifier(SpeedrunnerMod.MOD_ID, "bastions"));
    public static final TagKey<Structure> DESERT_PYRAMIDS = TagKey.of(RegistryKeys.STRUCTURE, new Identifier(SpeedrunnerMod.MOD_ID, "desert_pyramids"));
    public static final TagKey<Structure> FORTRESSES = TagKey.of(RegistryKeys.STRUCTURE, new Identifier(SpeedrunnerMod.MOD_ID, "fortresses"));

    public static void init() {
        info("Registered structure tags.");
    }
}