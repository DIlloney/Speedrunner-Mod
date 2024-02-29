package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.dillon8775.speedrunnermod.tag.ModItemTags;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity implements SkinOverlayOwner {
    @Shadow
    private int explosionRadius;

    public CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getXpToDrop(PlayerEntity player) {
        this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
        return super.getXpToDrop(player);
    }

    /**
     * Lowers the creeper's max health.
     */
    @ModifyArg(method = "createCreeperAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
    private static double genericMovementSpeed(double baseValue) {
        return DOOM_MODE ? 0.3D : 0.25D;
    }

    /**
     * Allows creepers to be ignited with speedrunner flint and steels.
     */
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean creeperIgnitions(ItemStack itemStack, Item item) {
        return itemStack.isIn(ModItemTags.FLINT_AND_STEELS);
    }

    /**
     * A thing for doom mode. >:)
     */
    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"))
    private void explode(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        Explosion.DestructionType destructionType = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
        float o = this.shouldRenderOverlay() ? 2.0F : 1.0F;
        if (!this.world.isClient && DOOM_MODE) {
            this.discard();
            this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * o, destructionType);
            this.world.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ITEM_BREAK, this.getSoundCategory(), 1.5F, this.random.nextFloat() * 0.4F + 0.8F);
            itemStack.damage(1, player, (playerx) -> {
                playerx.sendToolBreakStatus(hand);
            });
        }
    }
}