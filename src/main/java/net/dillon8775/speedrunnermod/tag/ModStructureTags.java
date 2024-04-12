package net.dillon8775.speedrunnermod.tag;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.structure.Structure;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.info;

public class ModStructureTags {
    public static final TagKey<Structure> FORTRESSES = TagKey.of(Registry.STRUCTURE_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "fortresses"));
    public static final TagKey<Structure> BASTIONS = TagKey.of(Registry.STRUCTURE_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "bastions"));
    public static final TagKey<Structure> DESERT_PYRAMIDS = TagKey.of(Registry.STRUCTURE_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "desert_pyramids"));
    public static final TagKey<Structure> ANCIENT_CITIES = TagKey.of(Registry.STRUCTURE_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "ancient_cities"));

    public static void init() {
        info("Initialized configured structure feature tags.");
    }
}