package net.dillon.speedrunnermod.mixin.main.entity.player;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Configuration for {@code iCarus mode} and {@code InfiniPearl mode}.
 */
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    @Shadow @Final
    private ServerStatHandler statHandler;
    @Shadow
    public abstract void sendMessage(Text message, boolean actionBar);

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
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
    @Author(Authors.DUNCANRUNS)
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) throws CommandSyntaxException {
        if (this.statHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_TIME)) == 0) {
            ItemStack item;
            if (options().main.iCarusMode) {
                item = this.itemStackFromString("minecraft:elytra{Unbreakable:1b}", 1);
                ItemStack item2 = this.itemStackFromString("minecraft:firework_rocket{Fireworks:{Flight:3b}}", 64);
                this.getInventory().armor.set(2, item);
                this.getInventory().main.set(0, item2);
            }

            if (options().main.infiniPearlMode) {
                item = new ItemStack(Items.ENDER_PEARL, 1);
                item.addEnchantment(Enchantments.INFINITY, 1);
                item.getOrCreateNbt().putInt("HideFlags", 1);

                LiteralText text = new LiteralText("InfiniPearl™");
                text.setStyle(text.getStyle().withItalic(false));
                item.setCustomName(text);

                if (!options().main.iCarusMode) {
                    this.getInventory().main.set(0, item);
                } else {
                    this.getInventory().main.set(1, item);
                }
            }
        }
    }

    @Unique
    private ItemStack itemStackFromString(String string, int count) throws CommandSyntaxException {
        return new ItemStackArgumentType().parse(new StringReader(string)).createStack(count, false);
    }
}