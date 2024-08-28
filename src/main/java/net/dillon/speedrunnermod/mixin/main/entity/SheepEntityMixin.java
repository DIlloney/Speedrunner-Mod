package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity {

    public SheepEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the amount of wool dropped when shearing sheep.
     */
    @ModifyVariable(method = "sheared", at = @At("STORE"))
    private int sheared(int x) {
        return 6 + this.random.nextInt(4);
    }

    /**
     * Allows sheep to be sheared with {@code speedrunner shears.}
     */
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean interactMob(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.SHEARS);
    }
}