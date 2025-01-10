package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.FireChargeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(FireChargeItem.class)
public class FirechargeItemMixin extends Item {

    public FirechargeItemMixin(Settings settings) {
        super(settings);
    }

    /**
     * Allows fireballs to be thrown.
     */
    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (options().main.throwableFireballs) {
            ItemStack stack = player.getStackInHand(hand);
            if (!world.isClient) {
                Vec3d lookVec = player.getRotationVec(1.0F);
                FireballEntity fireball = new FireballEntity(world, player, lookVec.normalize(), options().advanced.fireballExplosionPower);
                fireball.updatePosition(player.getX(), player.getEyeY() - 0.235, player.getZ());
                fireball.setOwner(player);
                world.spawnEntity(fireball);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                player.getItemCooldownManager().set(this.getDefaultStack(), TickCalculator.seconds(1));
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }

                return ActionResult.SUCCESS;
            }
        }

        return super.use(world, player, hand);
    }
}