package com.dilloney.speedrunnermod.mixins.entity;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    protected ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Shadow @Final ServerStatHandler statHandler;

    private ItemStack itemStackFromString(String string, int count) throws CommandSyntaxException {
        return new ItemStackArgumentType().parse(new StringReader(string)).createStack(count, false);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void icarusAndInfinityPearlMode(CallbackInfo info) throws CommandSyntaxException {
        if (this.statHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_TIME)) == 0) {
            ItemStack item;
            if (OPTIONS.iCarusMode) {
                item = this.itemStackFromString("minecraft:elytra{Unbreakable:1b}", 1);
                ItemStack item2 = this.itemStackFromString("minecraft:firework_rocket{Fireworks:{Flight:3b}}", 64);
                this.getInventory().armor.set(2, item);
                this.getInventory().main.set(0, item2);
            }

            if (OPTIONS.infiniPearlMode) {
                item = new ItemStack(Items.ENDER_PEARL, 1);
                item.addEnchantment(Enchantments.INFINITY, 1);
                item.getOrCreateTag().putInt("HideFlags", 1);

                LiteralText text = new LiteralText("InfiniPearl™");
                text.setStyle(text.getStyle().withItalic(false));
                item.setCustomName(text);

                if (!OPTIONS.iCarusMode) {
                    this.getInventory().main.set(0, item);
                } else {
                    this.getInventory().main.set(1, item);
                }
            }
        }
    }
}