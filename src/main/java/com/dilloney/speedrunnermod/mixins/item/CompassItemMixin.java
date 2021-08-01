package com.dilloney.speedrunnermod.mixins.item;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import com.dilloney.speedrunnermod.util.Speedrunners;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CompassItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtOps;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Predicate;

@Mixin(CompassItem.class)
public class CompassItemMixin extends Item {

    public CompassItemMixin(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (SpeedrunnerMod.CONFIG.manhuntMode) {
            ItemStack stack = user.getStackInHand(hand);
            NbtCompound nbtCompound = stack.hasNbt() ? stack.getNbt().copy() : new NbtCompound();
            if (!world.isClient && nbtCompound.contains("huntercompass") && nbtCompound.getBoolean("huntercompass")) {
                PlayerEntity closestPlayer = this.getClosestRunner(user.getX(), user.getY(), user.getZ(), 1.3432324E7D, world, Speedrunners.getRunners(world, false), (Predicate) null);
                if (closestPlayer != null && closestPlayer.world.getDimension() == user.world.getDimension()) {
                    NbtCompound tempCompoundTag = new NbtCompound();
                    tempCompoundTag.putString("Name", "[{\"text\":\"Tracking \",\"italic\":false},{\"text\":\"" + closestPlayer.getEntityName() + "\",\"color\":\"gold\",\"italic\":false}]");
                    nbtCompound.put("display", tempCompoundTag);
                    nbtCompound.put("LodestonePos", NbtHelper.fromBlockPos(closestPlayer.getBlockPos()));
                }

                nbtCompound.putBoolean("LodestoneTracked", false);
                World.CODEC.encodeStart(NbtOps.INSTANCE, world.getRegistryKey()).result().ifPresent((tag) -> {
                    nbtCompound.put("LodestoneDimension", tag);
                });
            }

            stack.setNbt(nbtCompound);
            return TypedActionResult.success(stack, true);
        } else {
            return super.use(world, user, hand);
        }
    }

    private PlayerEntity getClosestRunner(double x, double y, double z, double maxDistance, World world, ArrayList<String> playerList, @Nullable Predicate<Entity> targetPredicate) {
        double d = -1.0D;
        PlayerEntity playerEntity = null;
        Iterator var13 = playerList.iterator();

        while(true) {
            PlayerEntity playerEntity2;
            double e;
            do {
                do {
                    do {
                        if (!var13.hasNext()) {
                            return playerEntity;
                        }

                        playerEntity2 = world.getPlayerByUuid(UUID.fromString((String)var13.next()));
                    } while(targetPredicate != null && !targetPredicate.test(playerEntity2));

                    e = playerEntity2.squaredDistanceTo(x, y, z);
                } while(!(maxDistance < 0.0D) && !(e < maxDistance * maxDistance));
            } while(d != -1.0D && !(e < d));

            d = e;
            playerEntity = playerEntity2;
        }
    }
}