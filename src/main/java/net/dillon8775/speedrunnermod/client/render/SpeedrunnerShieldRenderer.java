package net.dillon8775.speedrunnermod.client.render;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.registry.RegistryEntry;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The renderer class for the {@code speedrunner shield.}
 */
@Environment(EnvType.CLIENT)
public class SpeedrunnerShieldRenderer implements DynamicItemRenderer {
    private final ModelPart pl = new ModelPart((List) ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -11.0F, -2.0F, 12.0F, 22.0F, 1.0F).build().stream().map((modelCuboidData) -> {
        return modelCuboidData.createCuboid(64, 64);
    }).collect(ImmutableList.toImmutableList()), Collections.emptyMap());
    private final ModelPart ha = new ModelPart((List)ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 6.0F).build().stream().map((modelCuboidData) -> {
        return modelCuboidData.createCuboid(64, 64);
    }).collect(ImmutableList.toImmutableList()), Collections.emptyMap());
    private Map<String, ModelPart> m;
    private final ShieldEntityModel shieldModel;

    public SpeedrunnerShieldRenderer() {
        this.m = Map.of("plate", this.pl, "handle", this.ha);
        this.shieldModel = new ShieldEntityModel(new ModelPart((List)ModelPartBuilder.create().build().stream().map((modelCuboidData) -> {
            return modelCuboidData.createCuboid(64, 64);
        }).collect(ImmutableList.toImmutableList()), this.m));
    }

    @Override
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        boolean bl = stack.getSubNbt("BlockEntityTag") != null;
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(180.0F));
        if (!bl) {
            VertexConsumer vertexConsumer2 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.shieldModel.getLayer(new Identifier(SpeedrunnerMod.MOD_ID, "textures/entity/speedrunner_shield_base_nopattern.png")), false, stack.hasGlint());
            this.shieldModel.render(matrices, vertexConsumer2, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        } else {
            SpriteIdentifier spriteIdentifier = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(SpeedrunnerMod.MOD_ID, "entity/speedrunner_shield_base"));
            VertexConsumer vertexConsumer = spriteIdentifier.getSprite().getTextureSpecificVertexConsumer(ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.shieldModel.getLayer(ModelLoader.SHIELD_BASE_NO_PATTERN.getAtlasId()), true, stack.hasGlint()));
            this.shieldModel.getHandle().render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
            List<Pair<RegistryEntry<BannerPattern>, DyeColor>> list = BannerBlockEntity.getPatternsFromNbt(ShieldItem.getColor(stack), BannerBlockEntity.getPatternListNbt(stack));
            BannerBlockEntityRenderer.renderCanvas(matrices, vertexConsumers, light, overlay, this.shieldModel.getPlate(), spriteIdentifier, false, list, stack.hasGlint());
        }

        matrices.pop();
    }
}