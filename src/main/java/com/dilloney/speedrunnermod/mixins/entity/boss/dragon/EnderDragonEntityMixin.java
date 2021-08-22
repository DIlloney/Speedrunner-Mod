package com.dilloney.speedrunnermod.mixins.entity.boss.dragon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;

import static com.dilloney.speedrunnermod.SpeedrunnerMod.OPTIONS;

@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonEntityMixin extends MobEntity {

    @Deprecated
    protected EnderDragonEntityMixin(EntityType<? extends EnderDragonEntity> entityType, World world) {
        super(EntityType.ENDER_DRAGON, world);
    }

    @Shadow EndCrystalEntity connectedCrystal;
    @Shadow int damageDuringSitting;
    @Shadow abstract boolean parentDamage(DamageSource source, float amount);
    @Shadow @Final static TargetPredicate CLOSE_PLAYER_PREDICATE;
    @Shadow @Final EnderDragonPart head;
    @Shadow @Final PhaseManager phaseManager;

    /**
     * Changes the Ender Dragons health.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createEnderDragonAttributes() {
        if (OPTIONS.doomMode) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 500.0D);
        } else if (OPTIONS.getModDifficulty() == 1) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D);
        } else if (OPTIONS.getModDifficulty() == 2) {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 150.0D);
        } else {
            return OPTIONS.getModDifficulty() >= 3 ? MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0D) : MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D);
        }
    }

    /**
     * Makes the Ender Dragon heal slower based on Mod Difficulty.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    private void tickWithEndCrystals() {
        if (this.connectedCrystal != null) {
            if (this.connectedCrystal.isRemoved()) {
                this.connectedCrystal = null;
            } else if (this.age % 10 == 0 && this.getHealth() < this.getMaxHealth()) {
                if (OPTIONS.doomMode) {
                    this.setHealth(this.getHealth() + 1.7F);
                } else if (OPTIONS.getModDifficulty() == 1) {
                    this.setHealth(this.getHealth() + 0.1F);
                } else if (OPTIONS.getModDifficulty() == 2) {
                    this.setHealth(this.getHealth() + 0.4F);
                } else if (OPTIONS.getModDifficulty() == 3) {
                    this.setHealth(this.getHealth() + 0.7F);
                } else if (OPTIONS.getModDifficulty() == 4) {
                    this.setHealth(this.getHealth() + 1.1F);
                } else {
                    this.setHealth(this.getHealth() + 0.1F);
                }
            }
        }

        if (this.random.nextInt(10) == 0) {
            List<EndCrystalEntity> list = this.world.getNonSpectatingEntities(EndCrystalEntity.class, this.getBoundingBox().expand(32.0D));
            EndCrystalEntity endCrystalEntity = null;
            double d = 1.7976931348623157E308D;
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                EndCrystalEntity endCrystalEntity2 = (EndCrystalEntity)var5.next();
                double e = endCrystalEntity2.squaredDistanceTo(this);
                if (e < d) {
                    d = e;
                    endCrystalEntity = endCrystalEntity2;
                }
            }

            this.connectedCrystal = endCrystalEntity;
        }

    }

    /**
     * Makes the Ender Dragon do more/less damage based on Mod Difficulty.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    private void damageLivingEntities(List<Entity> entities) {
        Iterator var2 = entities.iterator();

        while(var2.hasNext()) {
            Entity entity = (Entity)var2.next();
            if (entity instanceof LivingEntity) {
                if (OPTIONS.doomMode) {
                    ((LivingEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 1200, 0));
                    entity.damage(DamageSource.mob(this), 12.0F);
                } else if (OPTIONS.getModDifficulty() == 1) {
                    entity.damage(DamageSource.mob(this), 3.0F);
                } else if (OPTIONS.getModDifficulty() == 2) {
                    entity.damage(DamageSource.mob(this), 5.0F);
                } else if (OPTIONS.getModDifficulty() == 3) {
                    entity.damage(DamageSource.mob(this), 7.0F);
                } else if (OPTIONS.getModDifficulty() == 4) {
                    entity.damage(DamageSource.mob(this), 10.0F);
                } else {
                    entity.damage(DamageSource.mob(this), 10.0F);
                }
                this.applyDamageEffects(this, entity);
            }
        }
    }

    /**
     * Makes the Ender Dragon be able to be damaged more during it's sitting phase.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public boolean damagePart(EnderDragonPart part, DamageSource source, float amount) {
        if (this.phaseManager.getCurrent().getType() == PhaseType.DYING) {
            return false;
        } else {
            amount = this.phaseManager.getCurrent().modifyDamageTaken(source, amount);
            if (part != this.head) {
                amount = amount / 4.0F + Math.min(amount, 1.0F);
            }

            if (amount < 0.01F) {
                return false;
            } else {
                if (source.getAttacker() instanceof PlayerEntity || source.isExplosive()) {
                    float f = this.getHealth();
                    this.parentDamage(source, amount);
                    if (this.isDead() && !this.phaseManager.getCurrent().isSittingOrHovering()) {
                        this.setHealth(1.0F);
                        this.phaseManager.setPhase(PhaseType.DYING);
                    }

                    if (OPTIONS.doomMode) {
                        if (this.phaseManager.getCurrent().isSittingOrHovering()) {
                            this.damageDuringSitting = (int)((float)this.damageDuringSitting + (f - this.getHealth()));
                            if ((float)this.damageDuringSitting > 0.18F * this.getMaxHealth()) {
                                this.damageDuringSitting = 0;
                                this.phaseManager.setPhase(PhaseType.TAKEOFF);
                            }
                        }
                    } else if (OPTIONS.getModDifficulty() == 1) {
                        if (this.phaseManager.getCurrent().isSittingOrHovering()) {
                            this.damageDuringSitting = (int)((float)this.damageDuringSitting + (f - this.getHealth()));
                            if ((float)this.damageDuringSitting > 0.60F * this.getMaxHealth()) {
                                this.damageDuringSitting = 0;
                                this.phaseManager.setPhase(PhaseType.TAKEOFF);
                            }
                        }
                    } else if (OPTIONS.getModDifficulty() == 2) {
                        if (this.phaseManager.getCurrent().isSittingOrHovering()) {
                            this.damageDuringSitting = (int)((float)this.damageDuringSitting + (f - this.getHealth()));
                            if ((float)this.damageDuringSitting > 0.45F * this.getMaxHealth()) {
                                this.damageDuringSitting = 0;
                                this.phaseManager.setPhase(PhaseType.TAKEOFF);
                            }
                        }
                    } else if (OPTIONS.getModDifficulty() == 3) {
                        if (this.phaseManager.getCurrent().isSittingOrHovering()) {
                            this.damageDuringSitting = (int)((float)this.damageDuringSitting + (f - this.getHealth()));
                            if ((float)this.damageDuringSitting > 0.35F * this.getMaxHealth()) {
                                this.damageDuringSitting = 0;
                                this.phaseManager.setPhase(PhaseType.TAKEOFF);
                            }
                        }
                    } else if (OPTIONS.getModDifficulty() == 4) {
                        if (this.phaseManager.getCurrent().isSittingOrHovering()) {
                            this.damageDuringSitting = (int)((float)this.damageDuringSitting + (f - this.getHealth()));
                            if ((float)this.damageDuringSitting > 0.25F * this.getMaxHealth()) {
                                this.damageDuringSitting = 0;
                                this.phaseManager.setPhase(PhaseType.TAKEOFF);
                            }
                        }
                    }
                }

                return true;
            }
        }
    }

    /**
     * Makes the Ender Dragon take much more damage from end crystals.
     * @author Dilloney
     * @reason
     */
    @Overwrite
    public void crystalDestroyed(EndCrystalEntity crystal, BlockPos pos, DamageSource source) {
        PlayerEntity playerEntity2;
        if (source.getAttacker() instanceof PlayerEntity) {
            playerEntity2 = (PlayerEntity)source.getAttacker();
        } else {
            playerEntity2 = this.world.getClosestPlayer(CLOSE_PLAYER_PREDICATE, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        }

        if (crystal == this.connectedCrystal) {
            if (OPTIONS.doomMode) {
                damagePart(this.head, DamageSource.explosion(playerEntity2), 3.0F);
            } else if (OPTIONS.getModDifficulty() == 1) {
                damagePart(this.head, DamageSource.explosion(playerEntity2), 20.0F);
            } else if (OPTIONS.getModDifficulty() == 2) {
                damagePart(this.head, DamageSource.explosion(playerEntity2), 15.0F);
            } else if (OPTIONS.getModDifficulty() >= 3) {
                damagePart(this.head, DamageSource.explosion(playerEntity2), 10.0F);
            } else {
                damagePart(this.head, DamageSource.explosion(playerEntity2), 20.0F);
            }
        }

        this.phaseManager.getCurrent().crystalDestroyed(crystal, pos, source, playerEntity2);
    }
}