package net.dillon8775.speedrunnermod.mixin.main.item;

import net.dillon8775.speedrunnermod.enchantment.ModEnchantments;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
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
import java.util.Map;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Mixin(EnchantedBookItem.class)
public class EnchantedBookItemMixin {

    /**
     * Adds tooltips to the {@link net.dillon8775.speedrunnermod.enchantment.DashEnchantment} and {@link net.dillon8775.speedrunnermod.enchantment.CooldownEnchantment}.
     */
    @Inject(method = "appendTooltip", at = @At("TAIL"))
    private void appendTooltipsForSpeedrunnerEnchantments(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        if (options().client.itemTooltips) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
            if (enchantments.containsKey(ModEnchantments.DASH)) {
                tooltip.add(new TranslatableText("enchantment.speedrunnermod.dash.tooltip").formatted(Formatting.GRAY));
            }
            if (enchantments.containsKey(ModEnchantments.COOLDOWN)) {
                tooltip.add(new TranslatableText("enchantment.speedrunnermod.cooldown.tooltip").formatted(Formatting.GRAY));
            }
        }
    }
}