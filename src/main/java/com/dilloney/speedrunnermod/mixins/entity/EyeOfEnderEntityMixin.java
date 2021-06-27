package com.dilloney.speedrunnermod.mixins.entity;

import com.dilloney.speedrunnermod.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EyeOfEnderEntity.class)
public abstract class EyeOfEnderEntityMixin extends Entity {

    @Shadow private double targetX;
    @Shadow private double targetY;
    @Shadow private double targetZ;

    @Shadow private int lifespan;

    @Shadow @Final private static TrackedData<ItemStack> ITEM;

    public EyeOfEnderEntityMixin(EntityType<? extends EyeOfEnderEntity> type, World world) { super(type, world); }

    @Overwrite
    public void tick() {
        super.tick();
        Vec3d vec3d = this.getVelocity();
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        float g = MathHelper.sqrt(squaredHorizontalLength(vec3d));
        this.pitch = EyeOfEnderEntityMixin.updateRotation(this.prevPitch, (float)(MathHelper.atan2(vec3d.y, (double)g) * 57.2957763671875D));
        this.yaw = EyeOfEnderEntityMixin.updateRotation(this.prevYaw, (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D));
        if (!this.world.isClient) {
            double h = this.targetX - d;
            double i = this.targetZ - f;
            float j = (float)Math.sqrt(h * h + i * i);
            float k = (float)MathHelper.atan2(i, h);
            double l = MathHelper.lerp(0.0025D, (double)g, (double)j);
            double m = vec3d.y;
            if (j < 1.0F) {
                l *= 0.8D;
                m *= 0.8D;
            }

            int n = this.getY() < this.targetY ? 1 : -1;
            vec3d = new Vec3d(Math.cos((double)k) * l, m + ((double)n - m) * 0.014999999664723873D, Math.sin((double)k) * l);
            this.setVelocity(vec3d);
        }

        float o = 0.25F;
        if (this.isTouchingWater()) {
            for(int p = 0; p < 4; ++p) {
                this.world.addParticle(ParticleTypes.BUBBLE, d - vec3d.x * 0.25D, e - vec3d.y * 0.25D, f - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
            }
        } else if (this.getStack().getItem() == Items.ENDER_EYE && !this.isTouchingWater() || this.getStack().getItem() == ModItems.EYE_OF_ANNUL && !this.isTouchingWater()) {
            this.world.addParticle(ParticleTypes.PORTAL, d - vec3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, e - vec3d.y * 0.25D - 0.5D, f - vec3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, vec3d.x, vec3d.y, vec3d.z);
        } else if (this.getStack().getItem() == ModItems.EYE_OF_INFERNO && !this.isTouchingWater()) {
            this.world.addParticle(ParticleTypes.SMOKE, this.getParticleX(0.5D), this.getRandomBodyY(), this.getParticleZ(0.5D), 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.isClient) {
            this.updatePosition(d, e, f);
            ++this.lifespan;
            if (this.lifespan > 40 && !this.world.isClient) {
                this.playSound(SoundEvents.ENTITY_ENDER_EYE_DEATH, 1.0F, 1.0F);
                this.remove();
                this.world.spawnEntity(new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), this.getStack()));
            }
        } else {
            this.setPos(d, e, f);
        }
    }

    private static float updateRotation(float f, float g) {
        while(g - f < -180.0F) {
            f -= 360.0F;
        }

        while(g - f >= 180.0F) {
            f += 360.0F;
        }

        return MathHelper.lerp(0.2F, f, g);
    }

    public ItemStack getStack() {
        ItemStack itemStack = this.getTrackedItem();
        return itemStack.isEmpty() ? new ItemStack(Items.ENDER_EYE) : itemStack;
    }

    private ItemStack getTrackedItem() {
        return (ItemStack)this.getDataTracker().get(ITEM);
    }
}
