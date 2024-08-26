package net.dillon.speedrunnermod.client.render;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.entity.ModBoats;
import net.dillon.speedrunnermod.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * Used to render special things.
 */
@Environment(EnvType.CLIENT)
public class ModRenderers {

    /**
     * Registers custom item renderers, such as the {@code speedrunner bow,} {@code speedrunner crossbow,} and the {@code speedrunner shield.}
     */
    private static void initializeItemRenderers() {
        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (entity.getActiveItem() != stack) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0f;
        });
        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_BOW, Identifier.ofVanilla("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);

        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (CrossbowItem.isCharged(stack)) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(stack, entity);
        });
        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, Identifier.ofVanilla("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !CrossbowItem.isCharged(stack) ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, Identifier.ofVanilla("charged"), (stack, world, entity, seed) -> CrossbowItem.isCharged(stack) ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_CROSSBOW, Identifier.ofVanilla("firework"), (stack, world, entity, seed) -> {
            ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
            return chargedProjectilesComponent != null && chargedProjectilesComponent.contains(Items.FIREWORK_ROCKET) ? 1.0f : 0.0f;
        });

        ModelPredicateProviderRegistry.register(ModItems.SPEEDRUNNER_SHIELD, Identifier.ofVanilla("blocking"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SPEEDRUNNER_SHIELD, new SpeedrunnerShieldRenderer());

        info("Initialized custom item models.");
    }

    /**
     * Registers block renderers.
     */
    private static void initializeBlockRenderers() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPEEDRUNNER_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEAD_SPEEDRUNNER_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPEEDRUNNER_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEAD_SPEEDRUNNER_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DOOM_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPEEDRUNNER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPEEDRUNNER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEAD_SPEEDRUNNER_BUSH, RenderLayer.getCutout());

        info("Initialized custom block models.");
    }

    /**
     * Registers other types of renderers.
     */
    private static void initializeOtherRenderers() {
        TerraformBoatClientHelper.registerModelLayers(ModBoats.SPEEDRUNNER_BOAT_ID, false);
        TerraformBoatClientHelper.registerModelLayers(ModBoats.DEAD_SPEEDRUNNER_BOAT_ID, false);
        TerraformBoatClientHelper.registerModelLayers(ModBoats.CRIMSON_BOAT_ID, false);
        TerraformBoatClientHelper.registerModelLayers(ModBoats.WARPED_BOAT_ID, false);

        info("Initialized custom renderers.");
    }

    /**
     * Initializes all {@code Speedrunner Mod} renderers.
     */
    public static void init() {
        initializeItemRenderers();
        initializeBlockRenderers();
        initializeOtherRenderers();
    }
}