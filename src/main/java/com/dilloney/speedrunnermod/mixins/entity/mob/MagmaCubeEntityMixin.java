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

    @Deprecated
    protected MagmaCubeEntityMixin(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Changes how fast a Magma Cube can jump based on Mod Difficulty.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public int getTicksUntilNextJump() {
        if (OPTIONS.doomMode) {
            return 20;
        } else if (OPTIONS.getModDifficulty() == 1) {
            return 100;
        } else if (OPTIONS.getModDifficulty() == 2) {
            return 80;
        } else if (OPTIONS.getModDifficulty() == 3) {
            return 60;
        } else {
            return OPTIONS.getModDifficulty() == 4 ? 40 : 100;
        }
    }

    /**
     * Changes the Magma Cubes damage amount.
     * @author Dilloney
     * @reason
     */
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

    /**
     * Makes Magma Cubes almost always drop magma cream.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public Identifier getLootTableId() {
        return this.getSize() <= 2 ? this.getType().getLootTableId() : LootTables.EMPTY;
    }
}