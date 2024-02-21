package net.dillon8775.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(MagmaCubeEntity.class)
public class MagmaCubeEntityMixin extends SlimeEntity {

    public MagmaCubeEntityMixin(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
    }

    @Overwrite
    public int getTicksUntilNextJump() {
        return DOOM_MODE ? 20 : 100;
    }

    @Overwrite
    public float getDamageAmount() {
        float f = DOOM_MODE ? 2.2F : 1.5F;
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * f;
    }
}