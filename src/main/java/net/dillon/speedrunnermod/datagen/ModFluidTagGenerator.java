package net.dillon.speedrunnermod.datagen;

import net.dillon.speedrunnermod.tag.ModFluidTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.FluidTags;

/**
 * Contains the entries of all new or already existing fluid tags.
 */
public class ModFluidTagGenerator extends FabricTagProvider.FluidTagProvider {

    public ModFluidTagGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(ModFluidTags.BOAT_SAFE_FLUIDS)
                .forceAddTag(FluidTags.WATER)
                .forceAddTag(FluidTags.LAVA);
    }
}