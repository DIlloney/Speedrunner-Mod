package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ShieldItem;

/**
 * <p>A shield which has a faster cooldown, and more durability.</p>
 * <p>See {@link net.dillon.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe}, SpeedrunnerShieldModelRenderer and {@link net.dillon.speedrunnermod.mixin.main.entity.player.PlayerEntityMixin} for more.</p>
 */
public class SpeedrunnerShieldItem extends ShieldItem {

    public SpeedrunnerShieldItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(672).repairable(ModItemTags.SPEEDRUNNER_SHIELD_REPAIRABLE).equippableUnswappable(EquipmentSlot.OFFHAND));
    }
}