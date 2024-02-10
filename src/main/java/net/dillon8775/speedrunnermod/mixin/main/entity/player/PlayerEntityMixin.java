package net.dillon8775.speedrunnermod.mixin.main.entity.player;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.enchantment.ModEnchantments;
import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    abstract ItemCooldownManager getItemCooldownManager();
    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot slot);
    @Shadow @Final
    private PlayerAbilities abilities;

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getItemCooldownManager()Lnet/minecraft/entity/player/ItemCooldownManager;"))
    private void disableShield(CallbackInfo ci) {
        int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ModEnchantments.COOL, (PlayerEntity)(Object)this);
        int cooldown = coolEnchantment > 5 ? 0 : coolEnchantment == 5 ? 5 : coolEnchantment == 4 ? 10 : coolEnchantment == 3 ? 20 : coolEnchantment == 2 ? 40 : coolEnchantment == 1 ? 60 : 80;
        this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, cooldown);
    }

    /**
     * Allows speedrunner shields to get disabled, as a shield should, when hit by an axe.
     */
    @Redirect(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean damageShield(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.SHIELDS);
    }

    @Inject(method = "takeShieldHit", at = @At("TAIL"))
    private void takeShieldHit(LivingEntity attacker, CallbackInfo ci) {
        if (SpeedrunnerMod.options().main.doomMode) {
            if (attacker instanceof GiantEntity) {
                int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ModEnchantments.COOL, (PlayerEntity)(Object)this);
                int shieldCooldown = coolEnchantment > 5 ? 0 : coolEnchantment == 5 ? 10 : coolEnchantment == 4 ? 25 : coolEnchantment == 3 ? 50 : coolEnchantment == 2 ? 100 : coolEnchantment == 1 ? 150 : 200;
                int speedrunnerShieldCooldown = coolEnchantment > 5 ? 0 : coolEnchantment == 5 ? 5 : coolEnchantment == 4 ? 15 : coolEnchantment == 3 ? 25 : coolEnchantment == 2 ? 75 : coolEnchantment == 1 ? 150 : 180;
                this.getItemCooldownManager().set(Items.SHIELD, shieldCooldown);
                this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD, speedrunnerShieldCooldown);
                this.clearActiveItem();
                this.world.sendEntityStatus(this, (byte)30);
            }
        }
    }

    /**
     * Changes the default nether portal cooldown to whatever the user sets it to.
     */
    @Overwrite
    public int getMaxNetherPortalTime() {
        return this.abilities.invulnerable || SpeedrunnerMod.options().advanced.netherPortalCooldown <= 0 ? 1 : SpeedrunnerMod.options().advanced.netherPortalCooldown * 20;
    }

    /**
     * Allows player to hold their breath for a longer period of time while underwater.
     */
    @Override
    protected int getNextAirUnderwater(int air) {
        int i = EnchantmentHelper.getRespiration(this);
        if (i > 0 && this.random.nextInt(i + 1) > 0) {
            return air;
        }

        int b = SpeedrunnerMod.options().advanced.playerBreathTime;
        if (b > 0 && this.random.nextInt(b + 1) > 0) {
            return air;
        }

        return air - 1;
    }

    /**
     * Allows players catch their breath faster after coming out of the water.
     */
    @Override
    public int getNextAirOnLand(int air) {
        return Math.min(air + SpeedrunnerMod.options().advanced.catchBreathTime, this.getMaxAir());
    }
}