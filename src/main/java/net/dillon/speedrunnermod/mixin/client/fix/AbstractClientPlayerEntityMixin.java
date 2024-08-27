package net.dillon.speedrunnermod.mixin.client.fix;

import com.mojang.authlib.GameProfile;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {

    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    /**
     * Fixes the speedrunner bow not working with the FOV multiplier, and also changes it because it pulls faster.
     */
    @Overwrite
    public float getFovMultiplier() {
        float f = 1.0f;
        if (this.getAbilities().flying) {
            f *= 1.1f;
        }
        if (this.getAbilities().getWalkSpeed() == 0.0f || Float.isNaN(f *= ((float)this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) / this.getAbilities().getWalkSpeed() + 1.0f) / 2.0f) || Float.isInfinite(f)) {
            f = 1.0f;
        }
        ItemStack itemStack = this.getActiveItem();
        if (this.isUsingItem()) {
            if (itemStack.isIn(ModItemTags.BOWS)) {
                int i = getItemUseTime();
                float g;
                float dividedBy = itemStack.isOf(ModItems.SPEEDRUNNER_BOW) ? 15.0F : 20.0F; // Speedrunner Bow pull time is faster
                g = (float) i / dividedBy;
                g = g > 1.0F ? 1.0F : g * g;
                f *= 1.0F - g * 0.15F;
            } else if (MinecraftClient.getInstance().options.getPerspective().isFirstPerson() && this.isUsingSpyglass()) {
                return 0.1f;
            }
        }
        return MathHelper.lerp(MinecraftClient.getInstance().options.getFovEffectScale().getValue().floatValue(), 1.0f, f);
    }
}