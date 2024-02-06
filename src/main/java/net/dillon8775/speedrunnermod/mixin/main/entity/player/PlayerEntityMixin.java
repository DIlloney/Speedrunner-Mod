package net.dillon8775.speedrunnermod.mixin.main.entity.player;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    abstract ItemCooldownManager getItemCooldownManager();
    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getItemCooldownManager()Lnet/minecraft/entity/player/ItemCooldownManager;"))
    private void disableShield(CallbackInfo ci) {
        this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 80);
    }

    @Redirect(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean damageShield(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.SHIELDS);
    }

    /**
     * Allows speedrunner shields to get disabled, as a shield should, when hit by an axe.
     */
    @Inject(method = "takeShieldHit", at = @At("TAIL"))
    private void takeShieldHit(LivingEntity attacker, CallbackInfo ci) {
        if (SpeedrunnerMod.options().main.doomMode) {
            if (attacker instanceof GiantEntity) {
                this.getItemCooldownManager().set(Items.SHIELD, 200);
                this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, 180);
                this.clearActiveItem();
                this.world.sendEntityStatus(this, (byte)30);
            }
        }
    }

    /**
     * Allows players to hold their breath for a longer period of time.
     */
    @Override
    public int getNextAirOnLand(int air) {
        return Math.min(air + 8, this.getMaxAir());
    }
}