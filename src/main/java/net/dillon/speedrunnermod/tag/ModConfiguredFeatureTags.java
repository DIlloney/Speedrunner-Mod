package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

public class ModConfiguredFeatureTags {
    public static final TagKey<ConfiguredStructureFeature<?, ?>> FORTRESSES = TagKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "fortresses"));
    public static final TagKey<ConfiguredStructureFeature<?, ?>> BASTIONS = TagKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "bastions"));
    public static final TagKey<ConfiguredStructureFeature<?, ?>> DESERT_PYRAMIDS = TagKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier(SpeedrunnerMod.MOD_ID, "desert_pyramids"));

    public static void init() {
        info("Registered configured structure feature tags.");
    }
}