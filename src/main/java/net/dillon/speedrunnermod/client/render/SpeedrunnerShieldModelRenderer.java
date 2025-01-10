package net.dillon.speedrunnermod.client.render;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.item.model.special.ShieldModelRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ModelTransformationMode;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * The renderer class for the {@code speedrunner shield.}
 */
@Environment(EnvType.CLIENT)
public class SpeedrunnerShieldModelRenderer implements SpecialModelRenderer<ComponentMap> {
    private final ShieldEntityModel modelShield;
    private static final SpriteIdentifier SPEEDRUNNER_SHIELD_BASE = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/speedrunner_shield_base"));
    private static final SpriteIdentifier SPEEDRUNNER_SHIELD_BASE_NO_PATTERN = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/speedrunner_shield_base_no_pattern"));

    public SpeedrunnerShieldModelRenderer(ShieldEntityModel model) {
        ModelPart pl = new ModelPart(ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -11.0F, -2.0F, 12.0F, 22.0F, 1.0F).build().stream().map((modelCuboidData) -> modelCuboidData.createCuboid(64, 64)).collect(ImmutableList.toImmutableList()), Collections.emptyMap());
        ModelPart ha = new ModelPart(ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F).build().stream().map((modelCuboidData) -> modelCuboidData.createCuboid(64, 64)).collect(ImmutableList.toImmutableList()), Collections.emptyMap());
        Map<String, ModelPart> m = Map.of("plate", pl, "handle", ha);
        this.modelShield = model;
    }

    /**
     * Copied over from {@link net.minecraft.client.render.item.model.special.ShieldModelRenderer}.
     * <p>Creates the renderer for the {@code speedrunner shield.}</p>
     */
    @Override
    public void render(@Nullable ComponentMap componentMap, ModelTransformationMode modelTransformationMode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int i, int j, boolean bl) {
        BannerPatternsComponent bannerPatternsComponent = componentMap != null ? componentMap.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT) : BannerPatternsComponent.DEFAULT;
        DyeColor dyeColor = componentMap != null ? componentMap.get(DataComponentTypes.BASE_COLOR) : null;
        boolean bl2 = !bannerPatternsComponent.layers().isEmpty() || dyeColor != null;
        matrices.push();
        matrices.scale(1.0F, -1.0F, -1.0F);
        SpriteIdentifier spriteIdentifier = bl2 ? SPEEDRUNNER_SHIELD_BASE : SPEEDRUNNER_SHIELD_BASE_NO_PATTERN;
        VertexConsumer vertexConsumer = spriteIdentifier.getSprite().getTextureSpecificVertexConsumer(ItemRenderer.getItemGlintConsumer(vertexConsumers, this.modelShield.getLayer(spriteIdentifier.getAtlasId()), modelTransformationMode == ModelTransformationMode.GUI, bl));
        this.modelShield.getHandle().render(matrices, vertexConsumer, i, j);
        if (bl2) {
            BannerBlockEntityRenderer.renderCanvas(matrices, vertexConsumers, i, j, this.modelShield.getPlate(), spriteIdentifier, false, Objects.requireNonNullElse(dyeColor, DyeColor.WHITE), bannerPatternsComponent, bl, false);
        } else {
            this.modelShield.getPlate().render(matrices, vertexConsumer, i, j);
        }

        matrices.pop();
    }

    @Override
    public @Nullable ComponentMap getData(ItemStack stack) {
        return stack.getImmutableComponents();
    }

    @Environment(EnvType.CLIENT)
    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final ShieldModelRenderer.Unbaked INSTANCE = new ShieldModelRenderer.Unbaked();
        public static final MapCodec<ShieldModelRenderer.Unbaked> CODEC = MapCodec.unit(INSTANCE);

        @Override
        public MapCodec<ShieldModelRenderer.Unbaked> getCodec() {
            return CODEC;
        }

        @Override
        public SpecialModelRenderer<?> bake(LoadedEntityModels entityModels) {
            return new SpeedrunnerShieldModelRenderer(new ShieldEntityModel(entityModels.getModelPart(EntityModelLayers.SHIELD)));
        }
    }
}