package net.dilloney.speedrunnermod;

import com.google.common.collect.ImmutableMap;
import net.dilloney.speedrunnermod.block.ModBlocks;
import net.dilloney.speedrunnermod.item.ModItems;
import net.dilloney.speedrunnermod.option.ModOptions;
import net.dilloney.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe;
import net.dilloney.speedrunnermod.tag.ModBlockTags;
import net.dilloney.speedrunnermod.tag.ModItemTags;
import net.dilloney.speedrunnermod.util.entity.ModVillagers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.mixin.structure.StructuresConfigAccessor;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class SpeedrunnerMod implements ModInitializer {
    public static final String MOD_ID = "speedrunnermod";
    public static final String MOD_VERSION = "v1.7.2";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Identifier SPEEDRUNNER_MOD_ICON = new Identifier("speedrunnermod:icon.png");

    public void onInitialize() {
        ModOptions.loadConfig();

        ModItems.init();
        ModBlocks.init();
        ModItemTags.init();
        ModBlockTags.init();
        ModVillagers.init();
        SpeedrunnerShieldDecorationRecipe.init();

        if (options().main.makeStructuresMoreCommon) {
            makeStructuresMoreCommon();
        }

        LOGGER.info("The Speedrunner Mod (" + MOD_VERSION + ")" + " has been loaded.");
        LOGGER.info("Consider subscribing to me, Dilloney, on YouTube. :)");
        LOGGER.info("https://www.youtube.com/channel/UCNZVI8pFpzn-eXEZsyDEagg");
    }

    private static void makeStructuresMoreCommon() {
        ServerWorldEvents.LOAD.register((server, world) -> {
            Map<StructureFeature<?>, StructureConfig> map = new HashMap<>(world.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());

            map.computeIfPresent(StructureFeature.VILLAGE, (structureFeature, structureConfig) -> {
                return new StructureConfig(16, 9, 10387312);
            });
            map.computeIfPresent(StructureFeature.DESERT_PYRAMID, (structureFeature, structureConfig) -> {
                return new StructureConfig(10, 8, 14357617);
            });
            map.computeIfPresent(StructureFeature.END_CITY, (structureFeature, structureConfig) -> {
                return new StructureConfig(7, 6, 10387313);
            });
            map.computeIfPresent(StructureFeature.RUINED_PORTAL, (structureFeature, structureConfig) -> {
                return new StructureConfig(9, 8, 34222645);
            });
            map.computeIfPresent(StructureFeature.SHIPWRECK, (structureFeature, structureConfig) -> {
                return new StructureConfig(10, 8, 165745295);
            });
            map.computeIfPresent(StructureFeature.BASTION_REMNANT, (structureFeature, structureConfig) -> {
                return new StructureConfig(9, 8, 30084232);
            });
            map.computeIfPresent(StructureFeature.FORTRESS, (structureFeature, structureConfig) -> {
                return new StructureConfig(8, 7, 30084232);
            });

            ImmutableMap<StructureFeature<?>, StructureConfig> immutableMap = ImmutableMap.copyOf(map);

            ((StructuresConfigAccessor)world.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(immutableMap);

            if (options().advanced.debugMode) {
                LOGGER.debug("Structures have been made more common");
            }
        });
    }

    public static ModOptions options() {
        return ModOptions.OPTIONS;
    }
}