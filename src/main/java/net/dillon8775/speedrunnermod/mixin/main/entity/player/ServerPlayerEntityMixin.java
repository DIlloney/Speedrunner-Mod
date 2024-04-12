<<<<<<< Updated upstream
package net.dillon8775.speedrunnermod.mixin.main.entity.player;

import com.mojang.authlib.GameProfile;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.util.Author;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * Configuration for {@code iCarus mode} and {@code InfiniPearl mode}.
 */
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    @Shadow @Final
    private ServerStatHandler statHandler;
    @Shadow
    public abstract void sendMessage(Text message, boolean actionBar);

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile, @Nullable PlayerPublicKey publicKey) {
        super(world, pos, yaw, gameProfile, publicKey);
    }

    /**
     * Sends the players coordinates to chat upon death.
     */
    @Inject(method = "onDeath", at = @At("TAIL"))
    private void sendCords(DamageSource source, CallbackInfo ci) {
        if (options().client.showDeathCords && this.world.getGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES)) {
            this.sendMessage(SpeedrunnerMod.deathCords(this.getX(), this.getY(), this.getZ()), false);
        }
    }

    /**
     * Implements the {@code iCarus Mode} and {@code InfiniPearl Mode}.
     */
    @Author("DuncanRuns")
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (this.statHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_TIME)) == 0) {
            ItemStack item;
            if (options().main.iCarusMode) {
                item = new ItemStack(Items.ELYTRA, 1);
                NbtCompound elytraTag = item.getOrCreateNbt();
                elytraTag.putBoolean("Unbreakable", true);

                ItemStack fireworks = new ItemStack(Items.FIREWORK_ROCKET, 64);
                NbtCompound fireworksTag = fireworks.getOrCreateSubNbt("Fireworks");
                fireworksTag.putByte("Flight", (byte)3);

                this.getInventory().armor.set(2, item);
                this.getInventory().main.set(0, fireworks);
            }

            if (options().main.infiniPearlMode) {
                item = new ItemStack(Items.ENDER_PEARL, 1);
                item.addEnchantment(Enchantments.INFINITY, 1);
                item.getOrCreateNbt().putInt("HideFlags", 1);

                Text text = Text.literal("InfiniPearl™");
                text.getWithStyle(text.getStyle().withItalic(false));
                item.setCustomName(text);

                if (!options().main.iCarusMode) {
                    this.getInventory().main.set(0, item);
                } else {
                    this.getInventory().main.set(1, item);
                }
            }
        }
    }
=======
package net.dillon8775.speedrunnermod.mixin.main.entity.player;

import com.mojang.authlib.GameProfile;
import net.dillon8775.speedrunnermod.SpeedrunnerMod;
import net.dillon8775.speedrunnermod.util.Author;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

/**
 * Configuration for {@code iCarus mode} and {@code InfiniPearl mode}.
 */
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    @Shadow @Final
    private ServerStatHandler statHandler;
    @Shadow
    public abstract void sendMessage(Text message, boolean actionBar);

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile, @Nullable PlayerPublicKey publicKey) {
        super(world, pos, yaw, gameProfile, publicKey);
    }

    /**
     * Sends the players coordinates to chat upon death.
     */
    @Inject(method = "onDeath", at = @At("TAIL"))
    private void sendCords(DamageSource source, CallbackInfo ci) {
        if (options().client.showDeathCords && this.world.getGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES)) {
            this.sendMessage(SpeedrunnerMod.deathCords(this.getX(), this.getY(), this.getZ()), false);
        }
    }

    /**
     * Implements the {@code iCarus Mode} and {@code InfiniPearl Mode}.
     */
    @Author("DuncanRuns")
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (this.statHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_TIME)) == 0) {
            ItemStack item;
            if (options().main.iCarusMode) {
                item = new ItemStack(Items.ELYTRA, 1);
                NbtCompound elytraTag = item.getOrCreateNbt();
                elytraTag.putBoolean("Unbreakable", true);

                ItemStack fireworks = new ItemStack(Items.FIREWORK_ROCKET, 64);
                NbtCompound fireworksTag = fireworks.getOrCreateSubNbt("Fireworks");
                fireworksTag.putByte("Flight", (byte)3);

                this.getInventory().armor.set(2, item);
                this.getInventory().main.set(0, fireworks);
            }

            if (options().main.infiniPearlMode) {
                item = new ItemStack(Items.ENDER_PEARL, 1);
                item.addEnchantment(Enchantments.INFINITY, 1);
                item.getOrCreateNbt().putInt("HideFlags", 1);

                Text text = Text.literal("InfiniPearl™");
                text.getWithStyle(text.getStyle().withItalic(false));
                item.setCustomName(text);

                if (!options().main.iCarusMode) {
                    this.getInventory().main.set(0, item);
                } else {
                    this.getInventory().main.set(1, item);
                }
            }
        }
    }
>>>>>>> Stashed changes
}