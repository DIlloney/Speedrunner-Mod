package com.dilloney.speedrunnermod.mixins.client;

import com.dilloney.speedrunnermod.items.ModItems;
import com.mojang.authlib.GameProfile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {

    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Overwrite
    public float getSpeed() {
        float f = 1.0F;
        if (this.abilities.flying) {
            f *= 1.1F;
        }

        f = (float)((double)f * ((this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) / (double)this.abilities.getWalkSpeed() + 1.0D) / 2.0D));
        if (this.abilities.getWalkSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f)) {
            f = 1.0F;
        }

        if (this.isUsingItem() && this.getActiveItem().getItem() == Items.BOW || this.isUsingItem() && this.getActiveItem().getItem() == ModItems.SPEEDRUNNER_BOW) {
            int i = this.getItemUseTime();
            float g = (float)i / 20.0F;
            if (g > 1.0F) {
                g = 1.0F;
            } else {
                g *= g;
            }

            f *= 1.0F - g * 0.15F;
        }

        return MathHelper.lerp(MinecraftClient.getInstance().options.fovEffectScale, 1.0F, f);
    }
}
