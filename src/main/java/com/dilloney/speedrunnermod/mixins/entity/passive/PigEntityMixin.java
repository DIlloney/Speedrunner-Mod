package com.dilloney.speedrunnermod.mixins.entity.passive;

import com.dilloney.speedrunnermod.item.ModItems;
import com.dilloney.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PigEntity.class)
public abstract class PigEntityMixin extends AnimalEntity {

    protected PigEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    private void makePigsLikeSpeedrunnerCarrotOnStick(CallbackInfo callbackInfo) {
        this.goalSelector.add(4, new TemptGoal(this, 1.2D, Ingredient.ofItems(new ItemConvertible[]{ModItems.SPEEDRUNNER_CARROT_ON_A_STICK}), false));
    }

    @Redirect(method = "canBeControlledByRider", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean makePigsRideAlongWithSpeedrunnerCarrotOnStick(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.CARROT_ON_STICKS);
    }
}