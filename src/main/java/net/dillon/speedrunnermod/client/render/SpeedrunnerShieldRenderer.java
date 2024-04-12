package net.dillon.speedrunnermod.client.render;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The renderer class for the {@code speedrunner shield.}
 */
@Environment(EnvType.CLIENT)
public class SpeedrunnerShieldRenderer implements DynamicItemRenderer {
    private final ShieldEntityModel shieldModel;
    private static final SpriteIdentifier SPEEDRUNNER_SHIELD_BASE = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, new Identifier("entity/speedrunner_shield_base"));
    private static final SpriteIdentifier SPEEDRUNNER_SHIELD_BASE_NO_PATTERN = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, new Identifier("entity/speedrunner_shield_base_no_pattern"));

    public SpeedrunnerShieldRenderer() {
        ModelPart pl = new ModelPart(ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -11.0F, -2.0F, 12.0F, 22.0F, 1.0F).build().stream().map((modelCuboidData) -> modelCuboidData.createCuboid(64, 64)).collect(ImmutableList.toImmutableList()), Collections.emptyMap());
        ModelPart ha = new ModelPart(ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F).build().stream().map((modelCuboidData) -> modelCuboidData.createCuboid(64, 64)).collect(ImmutableList.toImmutableList()), Collections.emptyMap());
        Map<String, ModelPart> m = Map.of("plate", pl, "handle", ha);
        this.shieldModel = new ShieldEntityModel(new ModelPart(ModelPartBuilder.create().build().stream().map((modelCuboidData) -> modelCuboidData.createCuboid(64, 64)).collect(ImmutableList.toImmutableList()), m));
    }

    @Override
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        boolean bl = BlockItem.getBlockEntityNbt(stack) != null;
        matrices.push();
        matrices.scale(1.0F, -1.0F, -1.0F);
        SpriteIdentifier spriteIdentifier = bl ? SPEEDRUNNER_SHIELD_BASE : SPEEDRUNNER_SHIELD_BASE_NO_PATTERN;
        VertexConsumer vertexConsumer = spriteIdentifier.getSprite().getTextureSpecificVertexConsumer(ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.shieldModel.getLayer(spriteIdentifier.getAtlasId()), true, stack.hasGlint()));
        this.shieldModel.getHandle().render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        if (bl) {
            List<Pair<RegistryEntry<BannerPattern>, DyeColor>> list = BannerBlockEntity.getPatternsFromNbt(ShieldItem.getColor(stack), BannerBlockEntity.getPatternListNbt(stack));
            BannerBlockEntityRenderer.renderCanvas(matrices, vertexConsumers, light, overlay, this.shieldModel.getPlate(), spriteIdentifier, false, list, stack.hasGlint());
        } else {
            this.shieldModel.getPlate().render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        matrices.pop();
    }
}