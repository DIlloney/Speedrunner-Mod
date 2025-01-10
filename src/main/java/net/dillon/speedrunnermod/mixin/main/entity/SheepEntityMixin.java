package net.dillon.speedrunnermod.mixin.main.entity;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity {
    @Shadow
    public abstract void setSheared(boolean sheared);

    public SheepEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author Dillon8775
     * @reason Increases the amount of wool dropped when shearing sheep.
     */
    @Overwrite
    public void sheared(ServerWorld world, SoundCategory shearedSoundCategory, ItemStack shears) {
        world.playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.forEachShearedItem(
                world,
                LootTables.SHEEP_SHEARING,
                shears,
                (serverWorld, itemStack) -> {
                    for (int i = 0; i < itemStack.getCount(); i++) {
                        ItemEntity itemEntity = this.dropStack(serverWorld, itemStack.copyWithCount(6 + this.random.nextInt(4)), 1.0F);
                        if (itemEntity != null) {
                            itemEntity.setVelocity(
                                    itemEntity.getVelocity()
                                            .add(
                                                    (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F),
                                                    (double)(this.random.nextFloat() * 0.05F),
                                                    (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)
                                            )
                            );
                        }
                    }
                }
        );
        this.setSheared(true);
    }

    /**
     * Allows sheep to be sheared with {@code speedrunner shears.}
     */
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean interactMob(ItemStack stack, Item item) {
        return stack.isIn(ConventionalItemTags.SHEAR_TOOLS);
    }
}