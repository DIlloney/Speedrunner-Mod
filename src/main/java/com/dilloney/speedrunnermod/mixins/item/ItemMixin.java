package com.dilloney.speedrunnermod.mixins.item;

import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "appendTooltip", at = @At("HEAD"), cancellable = true)
    private void addHardnessTooltipsToModifiedBlockHardness(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo callbackInfo) {
        if (OPTIONS.hardnessTooltips) {
            if (stack.isIn(ModItemTags.ZEROTWO_TO_ZEROONE_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.02-01.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (!stack.isOf(Items.SEA_LANTERN) && !stack.isOf(Items.GLOWSTONE) && !stack.isOf(Items.HAY_BLOCK)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.03-02.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (!stack.isOf(Items.NETHERRACK) && !stack.isOf(Items.CRIMSON_NYLIUM) && !stack.isOf(Items.WARPED_NYLIUM)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.04-037.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.ZEROFIVE_TO_ZEROFOUR_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.05-04.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isOf(Items.MAGMA_BLOCK)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.15-04.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (!stack.isOf(Items.GRASS_BLOCK) && !stack.isOf(Items.GRAVEL) && !stack.isOf(Items.CLAY)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.06-05.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isOf(Items.DIRT_PATH)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.065-05.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (!stack.isOf(Items.NETHER_WART_BLOCK) && !stack.isOf(Items.WARPED_WART_BLOCK) && !stack.isOf(Items.SHROOMLIGHT)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.10-06.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.ZEROEIGHT_TO_ZEROSEVEN_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.08-07.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.ZEROEIGHT_TO_ZEROSIXFIVE_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.08-065.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (!stack.isOf(Items.PUMPKIN) && !stack.isOf(Items.CARVED_PUMPKIN) && !stack.isOf(Items.JACK_O_LANTERN) && !stack.isOf(Items.MELON)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.1-08.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.TWOZERO_TO_ZEROEIGHT_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.2-08.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (!stack.isOf(Items.BASALT) && !stack.isOf(Items.POLISHED_BASALT) && !stack.isOf(Items.SMOOTH_BASALT)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.125-1.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.ONEFIVE_TO_ONETHREE_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.15-13.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.TWOZERO_TO_ONETHREE_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.2-13.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.TWOZERO_TO_ONEFOUR_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.2-14.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isOf(Items.BLUE_ICE)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.28-14.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.TWOFIVE_TO_ONEFIVE_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.25-15.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.TWOZERO_TO_ONEFIVE_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.2-15.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.THREEZERO_TO_ONEFIVE_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.3-15.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isOf(Items.SMITHING_TABLE)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.25-16.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.THREEFIVE_TO_TWOZERO_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.35-2.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isOf(Items.DIAMOND_ORE)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.4-2.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isOf(Items.GOLD_BLOCK)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.3-25.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.THREEFIVE_TO_TWOFIVE_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.35-25.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isIn(ModItemTags.FIVEZERO_TO_THREEZERO_HARDNESS)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.5-3.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isOf(Items.CRYING_OBSIDIAN)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.50-10.hardness")).formatted(Formatting.LIGHT_PURPLE));
            } else if (stack.isOf(Items.OBSIDIAN)) {
                tooltip.add((new TranslatableText("item.speedrunnermod.50-20.hardness")).formatted(Formatting.LIGHT_PURPLE));
            }
        }
    }
}