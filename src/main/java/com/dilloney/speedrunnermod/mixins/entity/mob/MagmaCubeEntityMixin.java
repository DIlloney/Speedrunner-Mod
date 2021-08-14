package com.dilloney.speedrunnermod.mixins.entity.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(MagmaCubeEntity.class)
public class MagmaCubeEntityMixin extends SlimeEntity {

    protected MagmaCubeEntityMixin(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
    }

    @Overwrite
    public int getTicksUntilNextJump() {
        if (OPTIONS.getModDifficulty() == 1) {
            return this.random.nextInt(60) + 120;
        } else if (OPTIONS.getModDifficulty() == 2) {
            return this.random.nextInt(40) + 60;
        } else if (OPTIONS.getModDifficulty() == 3) {
            return this.random.nextInt(30) + 30;
        } else {
            return OPTIONS.getModDifficulty() == 4 ? this.random.nextInt(20) + 20 : this.random.nextInt(60) + 120;
        }
    }

    @Overwrite
    public float getDamageAmount() {
        if (OPTIONS.getModDifficulty() == 1) {
            return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 1.5F;
        } else if (OPTIONS.getModDifficulty() == 2 || OPTIONS.getModDifficulty() == 3) {
            return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 2.0F;
        } else {
            return OPTIONS.getModDifficulty () == 4 ? (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 2.5F : (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 2.0F;
        }
    }

    @Overwrite
    public Identifier getLootTableId() {
        return this.isSmall() ? LootTables.EMPTY : this.getType().getLootTableId();
    }
}