package net.dillon.speedrunnermod.item;

import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.ShearsItem;
import net.minecraft.registry.tag.BlockTags;

import java.util.List;

/**
 * The {@link net.dillon.speedrunnermod.SpeedrunnerMod} shears, which can mine certain blocks faster.
 */
public class SpeedrunnerShearsItem extends ShearsItem {

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(ToolComponent.Rule.ofAlwaysDropping(List.of(Blocks.COBWEB), 15.0F), ToolComponent.Rule.of(BlockTags.LEAVES, 15.0F), ToolComponent.Rule.of(BlockTags.WOOL, 7.5F), ToolComponent.Rule.of(List.of(Blocks.VINE, Blocks.GLOW_LICHEN), 2.0F)), 1.0F, 1);
    }

    public SpeedrunnerShearsItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(476));
        DispenserBlock.registerBehavior(this, new ShearsDispenserBehavior());
    }
}