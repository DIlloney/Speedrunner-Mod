package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.items.ModItems;
import com.dilloney.speedrunnermod.util.UniqueItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StriderEntity.class)
public abstract class StriderEntityMixin extends AnimalEntity {

    protected StriderEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow TemptGoal temptGoal;

    @Inject(method = "initGoals", at = @At("TAIL"))
    private void initGoalsMod(CallbackInfo callbackInfo) {
        this.temptGoal = new TemptGoal(this, 2.4D, Ingredient.ofItems(new ItemConvertible[]{ModItems.SPEEDRUNNER_CARROT_ON_A_STICK}), false);
    }

    @Redirect(method = "canBeControlledByRider", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean canBeControlledByRiderMod(ItemStack stack, Item item) {
        return UniqueItemRegistry.WARPED_FUNGUS_ON_A_STICK.isItemInRegistry(stack.getItem());
    }
}