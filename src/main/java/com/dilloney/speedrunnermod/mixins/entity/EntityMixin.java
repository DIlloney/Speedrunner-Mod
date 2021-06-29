package com.dilloney.speedrunnermod.mixins.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Entity.class)
public class EntityMixin {

    @Overwrite
    public void setOnFireFromLava() {
        Entity entity = (Entity)(Object)this;
        if (!entity.isFireImmune()) {
            entity.setOnFireFor(8);
            entity.damage(DamageSource.LAVA, 2.0F);
        }
    }
}