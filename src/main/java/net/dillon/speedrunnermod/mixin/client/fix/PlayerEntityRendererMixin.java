package net.dillon.speedrunnermod.mixin.client.fix;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fixes some incorrect rendering with speedrunner crossbows.
 */
@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Inject(method = "updateHandState", at = @At("TAIL"))
    private void renderCrossbowsCorrectly(AbstractClientPlayerEntity player, PlayerEntityRenderState.HandState handState, Hand hand, CallbackInfo ci) {
        ItemStack stack = player.getStackInHand(hand);
        handState.hasChargedCrossbow = stack.isIn(ConventionalItemTags.CROSSBOW_TOOLS) && CrossbowItem.isCharged(stack);
    }
}