package net.dillon8775.speedrunnermod.mixin.main.entity.player;

import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.enchantment.ModEnchantments;
import net.dillon8775.speedrunnermod.item.ModItems;
import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;
import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    public abstract ItemCooldownManager getItemCooldownManager();
    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot slot);
    @Shadow @Final
    private PlayerAbilities abilities;
    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Makes sure that the speedrunner shield actually gets disabled when hit with an axe, and also lowers this cooldown from the {@link net.dillon8775.speedrunnermod.enchantment.DashEnchantment}.
     */
    @Inject(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getItemCooldownManager()Lnet/minecraft/entity/player/ItemCooldownManager;"))
    private void disableShield(CallbackInfo ci) {
        int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ModEnchantments.COOLDOWN, (PlayerEntity)(Object)this);
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

    /**
     * A thing for {@code doom mode.} >:)
     */
    @Inject(method = "takeShieldHit", at = @At("TAIL"))
    private void takeShieldHit(LivingEntity attacker, CallbackInfo ci) {
        if (DOOM_MODE) {
            if (attacker instanceof GiantEntity) {
                int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ModEnchantments.COOLDOWN, (PlayerEntity)(Object)this);
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
     * Adds particles around the player if they are holding a {@code Dragon's Sword}.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void addDragonsSwordParticles(CallbackInfo ci) {
        if (this.getMainHandStack().isOf(ModItems.DRAGONS_SWORD) || this.getOffHandStack().isOf(ModItems.DRAGONS_SWORD)) {
            world.addParticle(ParticleTypes.PORTAL, this.getParticleX(0.5D), this.getRandomBodyY() - 0.25D, this.getParticleZ(0.5D), (world.random.nextDouble() - 0.5D) * 2.0D, -world.random.nextDouble(), (world.random.nextDouble() - 0.5D) * 2.0D);
        }
    }

    /**
     * Changes the default nether portal cooldown to whatever the user sets it to.
     */
    @Overwrite
    public int getMaxNetherPortalTime() {
        return this.abilities.invulnerable || options().main.netherPortalCooldown <= 0 ? 1 : options().main.netherPortalCooldown * 20;
    }

    /**
     * Allows player to hold their breath for a longer period of time while underwater.
     */
    @Override
    protected int getNextAirUnderwater(int air) {
        if (options().advanced.higherBreathTime && this.random.nextInt(4) > 0) {
            return air;
        }

        return super.getNextAirUnderwater(air);
    }

    /**
     * Allows the use of totems in the void.
     */
    @Override
    public void attemptTickInVoid() {
        if (this.getMainHandStack().isOf(Items.TOTEM_OF_UNDYING) || this.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING)) {
            if (this.getY() < (double)(this.world.getBottomY() - 64)) {
                int y = this.world.getTopY(Heightmap.Type.MOTION_BLOCKING, 0, 0);
                BlockPos pos = new BlockPos(0, y - 1, 0);
                if (this.world.getBlockState(pos).isOf(Blocks.WATER)) {
                    this.world.setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
                } else if (this.world.getBlockState(pos).isOf(Blocks.LAVA)) {
                    this.world.setBlockState(pos, Blocks.LAVA.getDefaultState());
                }
                this.teleport(0.5, y, 0.5, true);
                this.damage(DamageSource.GENERIC, 1000000.0F);
                this.world.playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 10.0F, 1.0F);
            }
        } else {
            super.attemptTickInVoid();
        }
    }

    /**
     * Allows players catch their breath faster after coming out of the water.
     */
    @Override
    public int getNextAirOnLand(int air) {
        final int breathTime = SpeedrunnerMod.getPlayerBreathTime();
        return Math.min(air + breathTime, this.getMaxAir());
    }
}