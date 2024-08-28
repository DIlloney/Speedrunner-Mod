package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Item.class)
public class ItemMixin {

    /**
     * Adds tooltips to items that can be used to craft the {@code piglin awakener.}
     */
    @Inject(method = "appendTooltip", at = @At("TAIL"))
    private void appendTooltipsForPiglinAwakenerItems(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        if (options().client.itemTooltips) {
            if (stack.isIn(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
                tooltip.add(Text.translatable("item.speedrunnermod.piglin_awakener_craftable").formatted(Formatting.GOLD));
            }
        }
        if (options().client.textureTooltips) {
            if (stack.isIn(ModItemTags.Block.TEXTURE_CREATOR_MANNYQUESO)) {
                tooltip.add(Text.translatable("speedrunnermod.texture_creator.mannyqueso"));
            }
        }
    }
}